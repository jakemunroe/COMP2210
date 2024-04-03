import java.util.Comparator;
   
public class Term implements Comparable<Term> {

   private String queryIn;
   private long weightIn;
    /**
     * Initialize a term with the given query and weight.
     * This method throws a NullPointerException if query is null,
     * and an IllegalArgumentException if weight is negative.
     */
   public Term(String query, long weight) {
      if (query == null) {
         throw new NullPointerException();
      }
      
      if (weight < 0) {
         throw new IllegalArgumentException();
      }
      
      queryIn = query;
      weightIn = weight;
   }
   
   public String getQuery() {
      return queryIn;
   }
   
   public long getWeight() {
      return weightIn;
   }

    /**
     * Compares the two terms in descending order of weight.
     */
   public static Comparator<Term> byDescendingWeightOrder() {
      return 
         new Comparator<Term>() {
            @Override
            public int compare(Term a, Term b) {
            
               return Long.compare(b.getWeight(), a.getWeight());
            }
         };
   }

    /**
     * Compares the two terms in ascending lexicographic order of query,
     * but using only the first length characters of query. This method
     * throws an IllegalArgumentException if length is less than or equal
     * to zero.
     */
   public static Comparator<Term> byPrefixOrder(int length) {
   
      if (length <= 0) {
         throw new IllegalArgumentException();
      }
      return 
         new Comparator<Term>() {
            @Override
            public int compare(Term a, Term b) {
            
               String queryA;
               String queryB;
               
               if (a.getQuery().length() < length) {
                  queryA = a.getQuery();
               }
               
               else {
                  queryA = a.getQuery().substring(0, length);
               }
               
               if (b.getQuery().length() < length) {
                  queryB = b.getQuery();
               }
               
               else {
                  queryB = b.getQuery().substring(0, length);
               }
               
               return queryA.compareTo(queryB);
            }
         };
   }

    /**
     * Compares this term with the other term in ascending lexicographic order
     * of query.
     */
   @Override
    public int compareTo(Term other) {
      
      String a = this.getQuery();
      String b = other.getQuery();
      
      return a.compareTo(b);
   }

    /**
     * Returns a string representation of this term in the following format:
     * query followed by a tab followed by weight
     */
   @Override
    public String toString(){
      return this.getQuery() + "\t" + this.getWeight();
   }
}