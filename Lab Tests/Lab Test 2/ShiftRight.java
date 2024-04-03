import java.util.Arrays;
/**
 * Implements shift-right behavior in an array.
 *
 */

public class ShiftRight {


   /**
    * Shifts the elements at a[index] through a[a.length - 2] one
    * position to the right. 
    */
   public static void shiftRight(Object[] array, int index) {
   
      Object[] b = Arrays.copyOf(array, array.length);
        
      array[index] = null;
        
      for (int i = index + 1; i < array.length - 2; i++) {
         array[i] = b[i];
      }
   
   }

}

