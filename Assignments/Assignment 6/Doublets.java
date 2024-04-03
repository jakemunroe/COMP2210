import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

import java.util.stream.Collectors;

/**
 * Provides an implementation of the WordLadderGame interface. 
 *
 * @author Your Name (you@auburn.edu)
 */
public class Doublets implements WordLadderGame {

   // The word list used to validate words.
   // Must be instantiated and populated in the constructor.
   /////////////////////////////////////////////////////////////////////////////
   // DECLARE A FIELD NAMED lexicon HERE. THIS FIELD IS USED TO STORE ALL THE //
   // WORDS IN THE WORD LIST. YOU CAN CREATE YOUR OWN COLLECTION FOR THIS     //
   // PURPOSE OF YOU CAN USE ONE OF THE JCF COLLECTIONS. SUGGESTED CHOICES    //
   // ARE TreeSet (a red-black tree) OR HashSet (a closed addressed hash      //
   // table with chaining).
   /////////////////////////////////////////////////////////////////////////////

   private TreeSet<String> lexicon = new TreeSet<String>();

   /**
    * Instantiates a new instance of Doublets with the lexicon populated with
    * the strings in the provided InputStream. The InputStream can be formatted
    * in different ways as long as the first string on each line is a word to be
    * stored in the lexicon.
    */
   public Doublets(InputStream in) {
      try {
         //////////////////////////////////////
         // INSTANTIATE lexicon OBJECT HERE  //
         //////////////////////////////////////
         Scanner s =
            new Scanner(new BufferedReader(new InputStreamReader(in)));
         while (s.hasNext()) {
            String str = s.next();
            
            lexicon.add(str.toLowerCase());
            
            s.nextLine();
         }
         in.close();
      }
      catch (java.io.IOException e) {
         System.err.println("Error reading from InputStream.");
         System.exit(1);
      }
   }


   //////////////////////////////////////////////////////////////
   // ADD IMPLEMENTATIONS FOR ALL WordLadderGame METHODS HERE  //
   //////////////////////////////////////////////////////////////
   
   /**
    * Returns the total number of words in the current lexicon.
    *
    * @return number of words in the lexicon
    */
   public int getWordCount() {
      return lexicon.size();
   }


   /**
    * Checks to see if the given string is a word.
    *
    * @param  str the string to check
    * @return     true if str is a word, false otherwise
    */
   public boolean isWord(String str) {
      
      if (str == null) {
         return false;
      }
      
      return lexicon.contains(str.toLowerCase());
   }


   /**
    * Returns the Hamming distance between two strings, str1 and str2. The
    * Hamming distance between two strings of equal length is defined as the
    * number of positions at which the corresponding symbols are different. The
    * Hamming distance is undefined if the strings have different length, and
    * this method returns -1 in that case. See the following link for
    * reference: https://en.wikipedia.org/wiki/Hamming_distance
    *
    * @param  str1 the first string
    * @param  str2 the second string
    * @return      the Hamming distance between str1 and str2 if they are the
    *                  same length, -1 otherwise
    */
   public int getHammingDistance(String str1, String str2) {
      
      str1 = str1.toLowerCase();
      str2 = str2.toLowerCase();
      
      if (str1.length() != str2.length()) {
         return -1;
      }
      
      int count = 0;
      char[] c1 = str1.toCharArray();
      char[] c2 = str2.toCharArray();
      
      for (int i = 0; i < c1.length; i++) {
         if (c1[i] != c2[i]) {
            count++;
         }
      }
      
      return count;
   }


   /**
    * Returns all the words that have a Hamming distance of one relative to the
    * given word.
    *
    * @param  word the given word
    * @return      the neighbors of the given word
    */
   public List<String> getNeighbors(String word) {
      
      if (word == null) {
         throw new IllegalArgumentException();
      }
      
      word = word.toLowerCase();
      
      
      List<String> nbrs = new ArrayList<String>();
      
      for (int i = 0; i < word.length(); i++) {
         for (char c = 'a'; c < 'z'; c++) {
            StringBuilder charWord = new StringBuilder(word);
         
            charWord.setCharAt(i, c);
            String newWord = charWord.substring(0);
            
            if (isWord(newWord)) {
               if (!newWord.equals(word)) {
                  nbrs.add(newWord);
               }
            }
         }
      }
      
      return nbrs;
   }


   /**
    * Checks to see if the given sequence of strings is a valid word ladder.
    *
    * @param  sequence the given sequence of strings
    * @return          true if the given sequence is a valid word ladder,
    *                       false otherwise
    */
   public boolean isWordLadder(List<String> sequence) {
      
      if (sequence == null || sequence.size() == 0) {
         return false;
      }
      
      String[] words = new String[sequence.size()];
      words = sequence.toArray(words);
      
      if (sequence.size() == 1) {
         return isWord(words[0]);
      }
      
      for (int i = 0; i < words.length - 1; i++) {
         if (isWord(words[i]) != true || getHammingDistance(words[i], words[i + 1]) != 1) {
            return false;
         }
      }
      
      return true;
   }


  /**
   * Returns a minimum-length word ladder from start to end. If multiple
   * minimum-length word ladders exist, no guarantee is made regarding which
   * one is returned. If no word ladder exists, this method returns an empty
   * list.
   *
   * Breadth-first search must be used in all implementing classes.
   *
   * @param  start  the starting word
   * @param  end    the ending word
   * @return        a minimum length word ladder from start to end
   */
   public List<String> getMinLadder(String start, String end) {
      List<String> ladder = new ArrayList<String>();
      
      if (!isWord(start) || !isWord(end) || getHammingDistance(start, end) == -1) {
         return ladder;
      }
      
      if (start.equals(end)) {
         ladder.add(start);
         return ladder;
      }
      
      TreeSet<String> tried = new TreeSet<String>();
      tried.add(start);
      Deque<Node> queue = new ArrayDeque<Node>();
      queue.add(new Node(start, null));
      
      return bfs(start, end);
   }
   
   //////////////////////////////
   /////Breadth-First Search/////
   //////////////////////////////
   private List<String> bfs(String start, String end) {
   
      TreeSet<String> tried = new TreeSet<String>();
      tried.add(start);
      
      Deque<Node> queue = new ArrayDeque<Node>();
      queue.addLast(new Node(start, null));
      
      Node endNode = new Node(end, null);
      
      List<String> ladder = new ArrayList<String>();
      
      while(!queue.isEmpty()) {
         Node currentNode = queue.removeFirst();
         
         for (String nbr : getNeighbors(currentNode.element)) {
            if (!tried.contains(nbr)) {
               tried.add(nbr);
            
               queue.addLast(new Node(nbr, currentNode));
            
               if (nbr.equals(end)) {
                  endNode.prev = currentNode;
                  break;
               }
            }
         }
      
      }
      
      if (endNode.prev == null) {
         return ladder;
      }
      
      while (endNode != null) {
         ladder.add(0, endNode.element);
         endNode = endNode.prev;
      }
      
      return ladder;
   }

   //////////////////////////////
   //////Private Node Class//////
   //////////////////////////////
   private class Node {
      String element;
      Node prev;
      
      public Node(String elementIn, Node previous) {
         element = elementIn;
         prev = previous;
      }
   
   }
}

