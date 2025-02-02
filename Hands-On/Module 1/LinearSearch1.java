/**
 * LinearSearch1.java
 * Provides an (incorrect) implementation of linear search.
 */

public final class LinearSearch1 {

   /**
    * Returns the index of the first occurence of target in a or
    * -1 if target is not present. This method implements the
    * linear search algorithm.
    *
    * @param   a  the array to be searched through
    * @param   target  the value to be searched for
    * @return  the index target in a or -1
    */
   public static int search(int[] a, int target) {
      int t = -1;
      for (int i = 0; i < a.length; i++) {
         if (a[i] == target) {
            t = i;
         }
      }
      return t;
   }

}
