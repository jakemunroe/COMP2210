import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author  YOUR NAME HERE (you@auburn.edu)
 *
 */
public final class Selector {

/**
* Can't instantiate this class.
*
* D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
*
*/
   private Selector() { }


   /**
    * Returns the minimum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the minimum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T min(Collection<T> coll, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
     
      if (coll.size() == 0) {
         throw new NoSuchElementException();
      }
      
      Iterator<T> itr = coll.iterator();
      T min = itr.next();
      
      while (itr.hasNext()) {
         T test = itr.next();
         if (comp.compare(min, test) > 0) {
            min = test;
         }
      }
      return min;
   }


   /**
    * Selects the maximum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the maximum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T max(Collection<T> coll, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
     
      if (coll.size() == 0) {
         throw new NoSuchElementException();
      }
      
      Iterator<T> itr = coll.iterator();
      T max = itr.next();
      
      while (itr.hasNext()) {
         T test = itr.next();
         if (comp.compare(max, test) < 0) {
            max = test;
         }
      }
      return max;
   }


   /**
    * Selects the kth minimum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth minimum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth minimum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
     
      if (coll.size() == 0) {
         throw new NoSuchElementException();
      }
      
      ArrayList<T> b = new ArrayList<T>();
      
      for (T element : coll) {
         b.add(element);
      }
      
      java.util.Collections.sort(b, comp);
      
      T distinctVal = b.get(0);
      int distinctK = 1;
      
      for (T element : b) {
         if (comp.compare(element, distinctVal) != 0) {
            distinctVal = element;
            distinctK++;
         }
         if (distinctK == k) {
            return distinctVal;
         }
      }
      throw new NoSuchElementException();
   }


   /**
    * Selects the kth maximum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth maximum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth maximum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
     
      if (coll.size() == 0) {
         throw new NoSuchElementException();
      }
     
      ArrayList<T> b = new ArrayList<T>();
      
      for (T element : coll) {
         b.add(element);
      }
      
      java.util.Collections.sort(b, comp);
      
      T distinctVal = b.get(b.size() - 1);
      int distinctK = 1;
      
      for (int i = b.size() - 1; i >= 0; i--) {
         if (comp.compare(b.get(i), distinctVal) != 0) {
            distinctVal = b.get(i);
            distinctK++;
         }
         if (distinctK == k) {
            return distinctVal;
         }
      }
      throw new NoSuchElementException();
   }


   /**
    * Returns a new Collection containing all the values in the Collection coll
    * that are greater than or equal to low and less than or equal to high, as
    * defined by the Comparator comp. The returned collection must contain only
    * these values and no others. The values low and high themselves do not have
    * to be in coll. Any duplicate values that are in coll must also be in the
    * returned Collection. If no values in coll fall into the specified range or
    * if coll is empty, this method throws a NoSuchElementException. If either
    * coll or comp is null, this method throws an IllegalArgumentException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the range values are selected
    * @param low     the lower bound of the range
    * @param high    the upper bound of the range
    * @param comp    the Comparator that defines the total order on T
    * @return        a Collection of values between low and high
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> Collection<T> range(Collection<T> coll, T low, T high,
                                                     Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
     
      if (coll.size() == 0) {
         throw new NoSuchElementException();
      }
      
      Iterator<T> itr = coll.iterator();
      ArrayList<T> range = new ArrayList<T>();
      
      while (itr.hasNext()) {
         T test = itr.next();
         if (comp.compare(test, low) > 0 | comp.compare(test, low) == 0 && comp.compare(test, high) < 0 | comp.compare(test, high) == 0) {
            range.add(test);
         }
      }
      
      if (range.size() == 0) {
         throw new NoSuchElementException();
      }
      return range;
   }


   /**
    * Returns the smallest value in the Collection coll that is greater than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the ceiling value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the ceiling value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
     
      if (coll.size() == 0) {
         throw new NoSuchElementException();
      }
      
      ArrayList<T> qualify = new ArrayList<T>();
      
      for (T element : coll) {
         if (comp.compare(element, key) == 0) {
            return key;
         }
         if (comp.compare(element, key) > 0) {
            qualify.add(element);
         }
      }
      
      if (qualify.size() == 0) {
         throw new NoSuchElementException();
      }
      
      T min = qualify.get(0);
      
      for (T element : qualify) {
         if (comp.compare(element, key) == 0) {
            return key;
         }
         if (comp.compare(element, key) >= 0 && comp.compare(min, element) > 0) {
            min = element;
         }
      }
      return min;
   }


   /**
    * Returns the largest value in the Collection coll that is less than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the floor value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the floor value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
     
      if (coll.size() == 0) {
         throw new NoSuchElementException();
      }
   
      ArrayList<T> qualify = new ArrayList<T>();
      
      for (T element : coll) {
         if (comp.compare(element, key) == 0) {
            return key;
         }
         if (comp.compare(element, key) < 0) {
            qualify.add(element);
         }
      }
      
      if (qualify.size() == 0) {
         throw new NoSuchElementException();
      }
      
      T max = qualify.get(0);
      
      for (T element : qualify) {
         if (comp.compare(element, max) > 0) {
            max = element;
         }
      }
      return max;
   }

}
