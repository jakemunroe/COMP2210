import java.util.Arrays;

public class Autocomplete {

   private Term[] termsIn;

	/**
	 * Initializes a data structure from the given array of terms.
	 * This method throws a NullPointerException if terms is null.
	 */
   public Autocomplete(Term[] terms) { 
      if (terms == null) {
         throw new NullPointerException();
      }
      
      termsIn = Arrays.copyOf(terms, terms.length); //copy of array
      Arrays.sort(termsIn); //new sorted array
   }

	/** 
	 * Returns all terms that start with the given prefix, in descending order of weight. 
	 * This method throws a NullPointerException if prefix is null.
	 */
   public Term[] allMatches(String prefix) {
   
      if (prefix == null) {
         throw new NullPointerException();
      }
      
      int first = BinarySearch.<Term>firstIndexOf(termsIn, new Term(prefix, 0),
         Term.byPrefixOrder(prefix.length())); //finding first index with given prefix from array
         
         
      int last = BinarySearch.<Term>lastIndexOf(termsIn, new Term(prefix, 0),
         Term.byPrefixOrder(prefix.length())); //finding last index with given prefix from array
         
      if (first == -1 || last == -1) { //if no matches return empty array
         return new Term[0];
      }
      
      int quantity = last - first + 1; //find quantity of matches
      
      Term[] matches = new Term[quantity]; //array to store matches
      
      int j = first;
      for (int i = 0; i < quantity; i++) { //copy qualifying elements into new array
         matches[i] = termsIn[j];
         j++;
      }
      
      Arrays.sort(matches, Term.byDescendingWeightOrder()); //sort array by descending weight
      
      return matches; //return correctly sorted array
   }
}