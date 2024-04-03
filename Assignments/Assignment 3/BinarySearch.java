import java.util.Comparator;

public class BinarySearch {

   /**
    * Returns the index of the first key in a[] that equals the search key, 
    * or -1 if no such key exists. This method throws a NullPointerException
    * if any parameter is null.
    */
   public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
      
      if (a == null || key == null || comparator == null) {
         throw new NullPointerException();
      }
      
      int min = 0;
      int max = a.length - 1;
      
      if (comparator.compare(key, a[min]) == 0) {
         return min;
      }
      
      while (min <= max) {
         
         int middle = min + (max - min) / 2; //find middle value
         
         if (comparator.compare(key, a[middle]) < 0) { //check if key is less than middle value
            max = middle - 1;                             //Eliminate right half of list
         }
         
         else if (comparator.compare(key, a[middle]) > 0) { //check if key is greater than middle value
            min = middle + 1;                                  //Eliminate left half of list
         }
         
         else if (comparator.compare(a[middle - 1], a[middle]) == 0) { //check if value to left is equivalent
            max = middle - 1;                                          //Shrink list to only contain lesser index value
         }
         
         else {
            return middle; //return correct value
         }
      }
      
      return -1; //no matching values
   }

   /**
    * Returns the index of the last key in a[] that equals the search key, 
    * or -1 if no such key exists. This method throws a NullPointerException
    * if any parameter is null.
    */
   public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
   
      if (a == null || key == null || comparator == null) {
         throw new NullPointerException();
      }
   
      int min = 0;
      int max = a.length - 1;
      
      if (comparator.compare(key, a[max]) == 0) {
         return max;
      }
      
      while (min <= max) {
         
         int middle = min + (max - min) / 2; //find middle value
         
         if (comparator.compare(key, a[middle]) < 0) { //check if key is less than middle value
            max = middle - 1;                             //Eliminate right half of list
         }
         
         else if (comparator.compare(key, a[middle]) > 0) { //check if key is greater than middle value
            min = middle + 1;                                  //Eliminate left half of list
         }
         
         else if (comparator.compare(a[middle + 1], a[middle]) == 0) { //check if value to right is equivalent
            min = middle + 1;                                          //Shrink list to only contain higher index value
         }
         
         else {
            return middle; //return correct value
         }
      }
      
      return -1; //no matching values
   }
}