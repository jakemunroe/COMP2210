import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


public class SelectorTest {


   /** Fixture initialization (common initialization
    *  for all tests).
   @Before public void setUp() {
   }
   */


   /** A test that always fails.
   @Test public void defaultTest() {
      Assert.assertEquals("Default test added by jGRASP. Delete "
            + "this test once you have added your own.", 0, 1);
   }
   */
   
   static Comparator<Integer> ascendingInteger =
        new Comparator<Integer>() {
           public int compare(Integer i1, Integer i2) {
              return i1.compareTo(i2);
           }
        };
   
   @Test public void minTest1() {
      ArrayList<Integer> coll = new ArrayList<Integer>();
      coll.add(2);
      coll.add(8);
      coll.add(7);
      coll.add(3);
      coll.add(4);
      Integer expected = 2;
      Integer actual = Selector.min(coll, ascendingInteger);
      assertEquals(expected, actual);
   }
   
   @Test public void minTest2() {
      ArrayList<Integer> coll = new ArrayList<Integer>();
      coll.add(5);
      coll.add(9);
      coll.add(1);
      coll.add(7);
      coll.add(3);
      Integer expected = 1;
      Integer actual = Selector.min(coll, ascendingInteger);
      assertEquals(expected, actual);
   }
   
   @Test public void minTest3() {
      ArrayList<Integer> coll = new ArrayList<Integer>();
      coll.add(8);
      coll.add(7);
      coll.add(6);
      coll.add(5);
      coll.add(4);
      Integer expected = 4;
      Integer actual = Selector.min(coll, ascendingInteger);
      assertEquals(expected, actual);
   }
   
   @Test public void minTest4() {
      ArrayList<Integer> coll = new ArrayList<Integer>();
      coll.add(8);
      coll.add(2);
      coll.add(8);
      coll.add(7);
      coll.add(3);
      coll.add(3);
      coll.add(4);
      Integer expected = 2;
      Integer actual = Selector.min(coll, ascendingInteger);
      assertEquals(expected, actual);
   }
   
   @Test public void maxTest1() {
      ArrayList<Integer> coll = new ArrayList<Integer>();
      coll.add(2);
      coll.add(8);
      coll.add(7);
      coll.add(3);
      coll.add(4);
      Integer expected = 8;
      Integer actual = Selector.max(coll, ascendingInteger);
      assertEquals(expected, actual);
   }
   
   @Test public void maxTest2() {
      ArrayList<Integer> coll = new ArrayList<Integer>();
      coll.add(5);
      coll.add(9);
      coll.add(1);
      coll.add(7);
      coll.add(3);
      Integer expected = 9;
      Integer actual = Selector.max(coll, ascendingInteger);
      assertEquals(expected, actual);
   }
   
   @Test public void maxTest3() {
      ArrayList<Integer> coll = new ArrayList<Integer>();
      coll.add(8);
      coll.add(7);
      coll.add(6);
      coll.add(5);
      coll.add(4);
      Integer expected = 8;
      Integer actual = Selector.max(coll, ascendingInteger);
      assertEquals(expected, actual);
   }
   
   @Test public void maxTest4() {
      ArrayList<Integer> coll = new ArrayList<Integer>();
      coll.add(8);
      coll.add(2);
      coll.add(8);
      coll.add(7);
      coll.add(3);
      coll.add(3);
      coll.add(4);
      Integer expected = 8;
      Integer actual = Selector.max(coll, ascendingInteger);
      assertEquals(expected, actual);
   }
   
   @Test public void kminTest1() {
      ArrayList<Integer> coll = new ArrayList<Integer>();
      coll.add(2);
      coll.add(8);
      coll.add(7);
      coll.add(3);
      coll.add(4);
      Integer expected = 2;
      Integer actual = Selector.kmin(coll, 1, ascendingInteger);
      assertEquals(expected, actual);
   }
   
   @Test public void kminTest2() {
      ArrayList<Integer> coll = new ArrayList<Integer>();
      coll.add(5);
      coll.add(9);
      coll.add(1);
      coll.add(7);
      coll.add(3);
      Integer expected = 5;
      Integer actual = Selector.kmin(coll, 3, ascendingInteger);
      assertEquals(expected, actual);
   }
   
   @Test public void kminTest3() {
      ArrayList<Integer> coll = new ArrayList<Integer>();
      coll.add(8);
      coll.add(7);
      coll.add(6);
      coll.add(5);
      coll.add(4);
      Integer expected = 8;
      Integer actual = Selector.kmin(coll, 5, ascendingInteger);
      assertEquals(expected, actual);
   }
   
   @Test public void kminTest4() {
      ArrayList<Integer> coll = new ArrayList<Integer>();
      coll.add(8);
      coll.add(2);
      coll.add(8);
      coll.add(7);
      coll.add(3);
      coll.add(3);
      coll.add(4);
      Integer expected = 4;
      Integer actual = Selector.kmin(coll, 3, ascendingInteger);
      assertEquals(expected, actual);
   }
   
   @Test public void kmaxTest1() {
      ArrayList<Integer> coll = new ArrayList<Integer>();
      coll.add(2);
      coll.add(8);
      coll.add(7);
      coll.add(3);
      coll.add(4);
      Integer expected = 8;
      Integer actual = Selector.kmax(coll, 1, ascendingInteger);
      assertEquals(expected, actual);
   }
   
   @Test public void kmaxTest2() {
      ArrayList<Integer> coll = new ArrayList<Integer>();
      coll.add(5);
      coll.add(9);
      coll.add(1);
      coll.add(7);
      coll.add(3);
      Integer expected = 5;
      Integer actual = Selector.kmax(coll, 3, ascendingInteger);
      assertEquals(expected, actual);
   }
   
   @Test public void kmaxTest3() {
      ArrayList<Integer> coll = new ArrayList<Integer>();
      coll.add(8);
      coll.add(7);
      coll.add(6);
      coll.add(5);
      coll.add(4);
      Integer expected = 5;
      Integer actual = Selector.kmax(coll, 4, ascendingInteger);
      assertEquals(expected, actual);
   }
   
   @Test public void kmaxTest4() {
      ArrayList<Integer> coll = new ArrayList<Integer>();
      coll.add(8);
      coll.add(2);
      coll.add(8);
      coll.add(7);
      coll.add(3);
      coll.add(3);
      coll.add(4);
      Integer expected = 3;
      Integer actual = Selector.kmax(coll, 4, ascendingInteger);
      assertEquals(expected, actual);
   }
   
   @Test public void rangeTest1() {
      ArrayList<Integer> coll = new ArrayList<Integer>();
      coll.add(2);
      coll.add(8);
      coll.add(7);
      coll.add(3);
      coll.add(4);
      ArrayList<Integer> expected = new ArrayList<Integer>();
      expected.add(2);
      expected.add(3);
      expected.add(4);
      Integer low = 1;
      Integer high = 5;
      Collection<Integer> actual = Selector.range(coll, low, high, ascendingInteger);
      assertEquals(expected, actual);
   }
   
   @Test public void rangeTest2() {
      ArrayList<Integer> coll = new ArrayList<Integer>();
      coll.add(5);
      coll.add(9);
      coll.add(1);
      coll.add(7);
      coll.add(3);
      ArrayList<Integer> expected = new ArrayList<Integer>();
      expected.add(5);
      expected.add(3);
      Integer low = 3;
      Integer high = 5;
      Collection<Integer> actual = Selector.range(coll, low, high, ascendingInteger);
      assertEquals(expected, actual);
   }
   
   @Test public void rangeTest3() {
      ArrayList<Integer> coll = new ArrayList<Integer>();
      coll.add(8);
      coll.add(7);
      coll.add(6);
      coll.add(5);
      coll.add(4);
      ArrayList<Integer> expected = new ArrayList<Integer>();
      expected.add(8);
      expected.add(7);
      expected.add(6);
      expected.add(5);
      expected.add(4);
      Integer low = 4;
      Integer high = 8;
      Collection<Integer> actual = Selector.range(coll, low, high, ascendingInteger);
      assertEquals(expected, actual);
   }
   
   @Test public void rangeTest4() {
      ArrayList<Integer> coll = new ArrayList<Integer>();
      coll.add(8);
      coll.add(2);
      coll.add(8);
      coll.add(7);
      coll.add(3);
      coll.add(3);
      coll.add(4);
      ArrayList<Integer> expected = new ArrayList<Integer>();
      expected.add(7);
      expected.add(3);
      expected.add(3);
      expected.add(4);
      Integer low = 3;
      Integer high = 7;
      Collection<Integer> actual = Selector.range(coll, low, high, ascendingInteger);
      assertEquals(expected, actual);
   }
   
   @Test public void ceilingTest1() {
      ArrayList<Integer> coll = new ArrayList<Integer>();
      coll.add(2);
      coll.add(8);
      coll.add(7);
      coll.add(3);
      coll.add(4);
      Integer key = 1;
      Integer expected = 2;
      Integer actual = Selector.ceiling(coll, key, ascendingInteger);
      assertEquals(expected, actual);
   }
   
   @Test public void ceilingTest2() {
      ArrayList<Integer> coll = new ArrayList<Integer>();
      coll.add(5);
      coll.add(9);
      coll.add(1);
      coll.add(7);
      coll.add(3);
      Integer key = 7;
      Integer expected = 7;
      Integer actual = Selector.ceiling(coll, key, ascendingInteger);
      assertEquals(expected, actual);
   }
   
   @Test public void ceilingTest3() {
      ArrayList<Integer> coll = new ArrayList<Integer>();
      coll.add(8);
      coll.add(7);
      coll.add(6);
      coll.add(5);
      coll.add(4);
      Integer key = 0;
      Integer expected = 4;
      Integer actual = Selector.ceiling(coll, key, ascendingInteger);
      assertEquals(expected, actual);
   }
   
   @Test public void ceilingTest4() {
      ArrayList<Integer> coll = new ArrayList<Integer>();
      coll.add(8);
      coll.add(2);
      coll.add(8);
      coll.add(7);
      coll.add(3);
      coll.add(3);
      coll.add(4);
      Integer key = 5;
      Integer expected = 7;
      Integer actual = Selector.ceiling(coll, key, ascendingInteger);
      assertEquals(expected, actual);
   }
   
   @Test public void floorTest1() {
      ArrayList<Integer> coll = new ArrayList<Integer>();
      coll.add(2);
      coll.add(8);
      coll.add(7);
      coll.add(3);
      coll.add(4);
      Integer key = 6;
      Integer expected = 4;
      Integer actual = Selector.floor
         (coll, key, ascendingInteger);
      assertEquals(expected, actual);
   }
   
   @Test public void floorTest2() {
      ArrayList<Integer> coll = new ArrayList<Integer>();
      coll.add(5);
      coll.add(9);
      coll.add(1);
      coll.add(7);
      coll.add(3);
      Integer key = 1;
      Integer expected = 1;
      Integer actual = Selector.floor(coll, key, ascendingInteger);
      assertEquals(expected, actual);
   }
   
   @Test public void floorTest3() {
      ArrayList<Integer> coll = new ArrayList<Integer>();
      coll.add(8);
      coll.add(7);
      coll.add(6);
      coll.add(5);
      coll.add(4);
      Integer key = 9;
      Integer expected = 8;
      Integer actual = Selector.floor(coll, key, ascendingInteger);
      assertEquals(expected, actual);
   }
   
   @Test public void floorTest4() {
      ArrayList<Integer> coll = new ArrayList<Integer>();
      coll.add(8);
      coll.add(2);
      coll.add(8);
      coll.add(7);
      coll.add(3);
      coll.add(3);
      coll.add(4);
      Integer key = 5;
      Integer expected = 4;
      Integer actual = Selector.floor(coll, key, ascendingInteger);
      assertEquals(expected, actual);
   }
}
