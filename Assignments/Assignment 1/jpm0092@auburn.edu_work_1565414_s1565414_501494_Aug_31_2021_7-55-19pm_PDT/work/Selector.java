import java.util.Arrays;

/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   Jacob Munroe (jpm0092@auburn.edu)
* @version  8/31/2021
*
*/
public final class Selector {

   /**
    * Can't instantiate this class.
    *
    * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
    *
    */
   public Selector() { }


   /**
    * Selects the minimum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int min(int[] a) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int min = a[0];
      for (int i = 0; i < a.length; i++) {
         if (a[i] < min) {
            min = a[i];
         }
      }
      return min;
   }


   /**
    * Selects the maximum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int max(int[] a) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int max = a[0];
      for (int i = 0; i < a.length; i++) {
         if (a[i] > max) {
            max = a[i];
         }
      }
      return max;
   }


   /**
    * Selects the kth minimum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth minimum value. Note that there is no kth
    * minimum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmin(int[] a, int k) {
      if (a == null || a.length == 0 || k < 1 || k > a.length) {
         throw new IllegalArgumentException();
      }
         //new array
      int[] b = Arrays.copyOf(a, a.length);
      Arrays.sort(b); //sort new array
      int t = 1; //tracking
      int min = min(b); //default min
      for (int i = 0; i < b.length; i++) {
         if (min != b[i] && t < k) { //checking if qualifying
            t++;
            min = b[i]; //new min if qualifies
         }
         if (t == k) {
            return min; //return kth min
         }
      }
      throw new IllegalArgumentException();
   }


   /**
    * Selects the kth maximum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth maximum value. Note that there is no kth
    * maximum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmax(int[] a, int k) {
      if (a == null || a.length == 0 || k < 1 || k > a.length) {
         throw new IllegalArgumentException();
      }
      int[] b = Arrays.copyOf(a, a.length);
      Arrays.sort(b);
      int t = 1; //tracking
      int max = max(b); //max value
      for (int i = b.length - 1; i > -1; i--) {
            //checking for larger qualifying value
         if (max != b[i] && t < k) {
            t++;
            max = b[i];
         }
         if (t == k) {
            return max;
         }
      }
      throw new IllegalArgumentException();
   }


   /**
    * Returns an array containing all the values in a in the
    * range [low..high]; that is, all the values that are greater
    * than or equal to low and less than or equal to high,
    * including duplicate values. The length of the returned array
    * is the same as the number of values in the range [low..high].
    * If there are no qualifying values, this method returns a
    * zero-length array. Note that low and high do not have
    * to be actual values in a. This method throws an
    * IllegalArgumentException if a is null or has zero length.
    * The array a is not changed by this method.
    */
   public static int[] range(int[] a, int low, int high) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int size = 0;
      int[] b = new int[0];
      for (int i = 0; i < a.length; i++) {
         if (a[i] >= low && a[i] <= high) {
            size++;
            b = Arrays.copyOf(b, size);
            b[size - 1] = a[i];
         }
      }
      return b;
   }


   /**
    * Returns the smallest value in a that is greater than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int ceiling(int[] a, int key) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int[] b = new int[0];
      int size = 0;
      for (int i = 0; i < a.length; i++) {
         if (a[i] >= key) {
            size++;
            b = Arrays.copyOf(b, size);
            b[size - 1] = a[i];
         }
      }
      if (b == null) {
         throw new IllegalArgumentException();
      }
      int min = min(b);
      return min;
   }


   /**
    * Returns the largest value in a that is less than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int floor(int[] a, int key) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int[] b = new int[0];
      int size = 0;
      for (int i = 0; i < a.length; i++) {
         if (a[i] <= key) {
            size++;
            b = Arrays.copyOf(b, size);
            b[size - 1] = a[i];
         }
      }
      if (b == null) {
         throw new IllegalArgumentException();
      }
      int max = max(b);
      return max;
   }

}
