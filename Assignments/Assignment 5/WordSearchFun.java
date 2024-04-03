import java.util.Arrays;
import java.util.TreeSet;
import java.util.Scanner;
import java.util.Iterator;
import java.io.File;
import java.util.SortedSet;
import java.util.List;
import java.io.FileReader;
import java.io.BufferedReader;
import java.lang.Math;
import java.util.ArrayList;

public class WordSearchFun implements WordSearchGame {

   private String[][] gameBoard; //actual gameboard
   private int width, height;
   private boolean[][] visited; //to mark path visited for dfs
   private TreeSet<String> wordList; //list of words
   private final int MAX_NEIGHBORS = 8;
   private int order;
   private SortedSet<String> allWords; //stores all scorable words on board
   private String prefix; //part of word to validate
   private ArrayList<Integer> path; //row-major path
   private ArrayList<Position> positionPath; //x,y path
   
   public WordSearchFun() {
      
      wordList = null;
      gameBoard = new String[4][4];
      width = gameBoard.length;
      height = gameBoard[0].length;
   }
      
      /**
    * Loads the lexicon into a data structure for later use. 
    * 
    * @param fileName A string containing the name of the file to be opened.
    * @throws IllegalArgumentException if fileName is null
    * @throws IllegalArgumentException if fileName cannot be opened.
    */
   public void loadLexicon(String fileName) {
   
      if (fileName == null) {
         throw new IllegalArgumentException();
      }
        
      wordList = new TreeSet<String>();
        
      try {
         Scanner scan = new Scanner(new BufferedReader(new FileReader(new File(fileName))));
            
         while (scan.hasNext()) {
            String str = scan.next();
            boolean added = wordList.add(str.toUpperCase());
            scan.nextLine();
         }
      }
        
      catch (Exception e) {
         throw new IllegalArgumentException("Error loading word list: " + fileName + ": " + e);
      }
   }
   
   /**
    * Stores the incoming array of Strings in a data structure that will make
    * it convenient to find words.
    * 
    * @param letterArray This array of length N^2 stores the contents of the
    *     game board in row-major order. Thus, index 0 stores the contents of board
    *     position (0,0) and index length-1 stores the contents of board position
    *     (N-1,N-1). Note that the board must be square and that the strings inside
    *     may be longer than one character.
    * @throws IllegalArgumentException if letterArray is null, or is  not
    *     square.
    */
   public void setBoard(String[] letterArray) {
   
      if (letterArray == null) {
         throw new IllegalArgumentException();
      }
      
      int n = (int)Math.sqrt(letterArray.length); //n^2 length so n = sqrt of length
      
      if ((n*n) != letterArray.length) {
         throw new IllegalArgumentException();
      }
      
      gameBoard = new String[n][n]; //square board;
      width = n;
      height = n;
      
      //add elements from letterArray to 2D board
      int index = 0; //position in letterArray
      for (int i = 0; i < height; i++) {
         for (int j = 0; i < width; i++) {
         
            gameBoard[i][j] = letterArray[index]; //add to board
            index++; //move to next element in letterArray
         }
      }
   }
   
   /**
    * Creates a String representation of the board, suitable for printing to
    *   standard out. Note that this method can always be called since
    *   implementing classes should have a default board.
    */
   public String getBoard() {
   
      String stringBoard = ""; //instantiate return string
   
      for (int i = 0; i < height; i++) {
         for (int j = 0; j < width; j++) {
         
            stringBoard += gameBoard[i][j] + " ";
         }
         stringBoard += "\n";
      }
   
      return stringBoard; 
   }
   
   /**
    * Retrieves all scorable words on the game board, according to the stated game
    * rules.
    * 
    * @param minimumWordLength The minimum allowed length (i.e., number of
    *     characters) for any word found on the board.
    * @return java.util.SortedSet which contains all the words of minimum length
    *     found on the game board and in the lexicon.
    * @throws IllegalArgumentException if minimumWordLength < 1
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public SortedSet<String> getAllScorableWords(int minimumWordLength) {
      allWords = new TreeSet<String>();
      prefix = "";
      
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException();
      }
      
      if (wordList == null) {
         throw new IllegalStateException();
      }
      
      for (int i = 0; i < height; i++) {
         for (int j = 0; j < width; j++) {
            prefix = gameBoard[i][j];
            
            //case for valid word
            if (isValidWord(prefix) && prefix.length() >= minimumWordLength) {
               allWords.add(prefix); //add to list
            }
            
            //have start to word, do dfs
            if (isValidPrefix(prefix)) {
               dfsRecursive(i, j, minimumWordLength);
            }
         }
      
      }
      return allWords;
   }
   
 /**
   * Computes the cummulative score for the scorable words in the given set.
   * To be scorable, a word must (1) have at least the minimum number of characters,
   * (2) be in the lexicon, and (3) be on the board. Each scorable word is
   * awarded one point for the minimum number of characters, and one point for 
   * each character beyond the minimum number.
   *
   * @param words The set of words that are to be scored.
   * @param minimumWordLength The minimum number of characters required per word
   * @return the cummulative score of all scorable words in the set
   * @throws IllegalArgumentException if minimumWordLength < 1
   * @throws IllegalStateException if loadLexicon has not been called.
   */  
   public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
      
      int score = 0;
      
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException();
      }
      
      if (wordList == null) {
         throw new IllegalStateException();
      }
      
      //loop thru given set of words
      for (String word : words) {
      
         //check if this word is scorable
         if (isValidWord(word) && word.length() >= minimumWordLength) {
            score += (word.length() - minimumWordLength) + 1; //increment score based on case given
         }
      }
      
      return score;
   }
   
   /**
    * Determines if the given word is in the lexicon.
    * 
    * @param wordToCheck The word to validate
    * @return true if wordToCheck appears in lexicon, false otherwise.
    * @throws IllegalArgumentException if wordToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public boolean isValidWord(String wordToCheck) {
      
      if (wordToCheck == null) {
         throw new IllegalArgumentException();
      }
      
      if (wordList == null) {
         throw new IllegalStateException();
      }
      
      return wordList.contains(wordToCheck);
   }
   
   /**
    * Determines if there is at least one word in the lexicon with the 
    * given prefix.
    * 
    * @param prefixToCheck The prefix to validate
    * @return true if prefixToCheck appears in lexicon, false otherwise.
    * @throws IllegalArgumentException if prefixToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public boolean isValidPrefix(String prefixToCheck) {
   
      if (prefixToCheck == null) {
         throw new IllegalArgumentException();
      }
      
      if (wordList == null) {
         throw new IllegalStateException();
      }
      
      String prefix = wordList.ceiling(prefixToCheck); //check if prefix is in lexicon
      
      if (prefix != null) {
         return true;
      }
      
      return false;
   }
       
   /**
    * Determines if the given word is in on the game board. If so, it returns
    * the path that makes up the word.
    * @param wordToCheck The word to validate
    * @return java.util.List containing java.lang.Integer objects with  the path
    *     that makes up the word on the game board. If word is not on the game
    *     board, return an empty list. Positions on the board are numbered from zero
    *     top to bottom, left to right (i.e., in row-major order). Thus, on an NxN
    *     board, the upper left position is numbered 0 and the lower right position
    *     is numbered N^2 - 1.
    * @throws IllegalArgumentException if wordToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public List<Integer> isOnBoard(String wordToCheck) {
      prefix = "";
      path = new ArrayList<Integer>();
      
      if (wordToCheck == null) {
         throw new IllegalArgumentException();
      }
      
      if (wordList == null) {
         throw new IllegalStateException();
      }
      
      for (int i = 0; i < height; i++) {
         for (int j = 0; j < width; j++) {
         
            Position position = new Position(i , j);
         
           //case if first pos works
            if (wordToCheck.equalsIgnoreCase(gameBoard[i][j])) {
            
               path.add(i * width + j); //row-major coords i.e. 1D array
               
               return path;
            }
            
            //check if wordToCheck has prefix of board pos
            if (wordToCheck.startsWith(gameBoard[i][j])) {
               
               if (dfsRecursive2(position, prefix, wordToCheck, path)) {
                  return path;
               }
            }
         }
      } 
      return path;  
   }

    ///////////////////////////////////////////
    ////////// Depth-First Searches ///////////
    ///////////////////////////////////////////

/*
 *Depth-First search using recursion for gathering all scorable words
 */
   private void dfsRecursive(int x, int y, int min) {
      Position start = new Position(x, y);
   
   //loop through all neighbors for given position
      for (Position pos : start.neighbors()) {
      
      //have we been here?
         if (!isVisited(pos)) {
            visit(pos); //if not mark as visited
         
         //will this neighbor make a valid prefix?
            if (isValidPrefix(prefix + gameBoard[pos.x][pos.y])) {
               prefix = prefix + gameBoard[pos.x][pos.y]; //if yes, then add to prefix
            }
         
         //do we have a valid word?
            if (isValidWord(prefix) && prefix.length() >= min) {
               allWords.add(prefix); //add prefix to word list
            }
         
            dfsRecursive(pos.x, pos.y, min);
         }
      }
   
   }
   
   /*
    *Depth-First search for isOnBoard
    */
   private boolean dfsRecursive2(Position pos, String prefix, 
      String wordToCheck, List<Integer> path) {
      
      if (!isValid(pos)) {
         return false;
      }
      
      if (isVisited(pos)) {
         return false;
      }
      
      if (!wordToCheck.startsWith(prefix)) {
         return false;
      }
      
      visit(pos);
      prefix += gameBoard[pos.x][pos.y];
      path.add(pos.x * width + pos.y);
      
      if (wordToCheck.equalsIgnoreCase(prefix)) {
         return true;
      }
      
      for (Position p : pos.neighbors()) {
         if (dfsRecursive2(p, prefix, wordToCheck, path)) {
            return true;
         }
      }
      
      visited[pos.x][pos.y] = false;
      prefix = prefix.replace(gameBoard[pos.x][pos.y], "");
      path.remove(pos.x * width + pos.y);
      return false;
   }

/*
   private boolean dfsRecursive2(Position position) {
   
      if (isVisited(position)) {
         return false;
      }
        
      visit(position);
        
      if (process(position)) {
         return true;
      }
        
      for (Position neighbor : position.neighbors()) {
         if (dfsRecursive2(neighbor)) {
            return true;
         }
      }
      return false;
   }
   */

    ///////////////////////////////////////////
    // Position class and associated methods //
    ///////////////////////////////////////////

    /**
     * Models an (x,y) position in the maze.
     */
   private class Position {
      int x;
      int y;
   
        /** Constructs a Position with coordinates (x,y). */
      public Position(int x, int y) {
         this.x = x;
         this.y = y;
      }
   
        /** Returns a string representation of this Position. */
      @Override
        public String toString() {
         return "(" + x + ", " + y + ")";
      }
   
        /** Returns true if this Position equals the given object. */
      @Override
        public boolean equals(Object obj) {
         if (obj == null) {
            return false;
         }
         if (obj == this) {
            return true;
         }
         if (obj.getClass() != this.getClass()) {
            return false;
         }
         Position other = (Position) obj;
         return this.x == other.x && this.y == other.y;
      }
   
        /** Returns all the neighbors of this Position. */
      public Position[] neighbors() {
         Position[] nbrs = new Position[MAX_NEIGHBORS];
         int count = 0;
         Position p;
            // generate all eight neighbor positions
            // add to return value if valid
         for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
               if (!((i == 0) && (j == 0))) {
                  p = new Position(x + i, y + j);
                  if (isValid(p)) {
                     nbrs[count++] = p;
                  }
               }
            }
         }
         return Arrays.copyOf(nbrs, count);
      }
   }

    /**
     * Is this position valid in the search area?
     */
   private boolean isValid(Position p) {
      return (p.x >= 0) && (p.x < width) && 
               (p.y >= 0) && (p.y < height);
   }

    /**
     * Has this valid position been visited?
     */
   private boolean isVisited(Position p) {
      return visited[p.x][p.y];
   }

    /**
     * Mark this valid position as having been visited.
     */
   private void visit(Position p) {
      visited[p.x][p.y] = true;
   }

}