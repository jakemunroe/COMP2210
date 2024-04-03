import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class MinOfThreeTest {


   @Test
   public void minOfThreeTest1() {
      int expected = 1;
      int actual = MinOfThree.min1(1, 2, 3);
      assertEquals(expected, actual);
   }
   
   @Test
   public void minOfThreeTest2() {
      int expected = 5;
      int actual = MinOfThree.min1(7, 5, 8);
      assertEquals(expected, actual);
   }
   
   @Test
   public void minOfThreeTest3() {
      int expected = 5;
      int actual = MinOfThree.min1(5, 5, 6);
      assertEquals(expected, actual);
   }
   
   @Test
   public void minOfThreeTest4() {
      int expected = 2;
      int actual = MinOfThree.min1(2, 2, 2);
      assertEquals(expected, actual);
   }
   
   @Test
   public void minOfThreeTest5() {
      int expected = 1;
      int actual = MinOfThree.min2(1, 2, 3);
      assertEquals(expected, actual);
   }
   
   @Test
   public void minOfThreeTest6() {
      int expected = 5;
      int actual = MinOfThree.min2(7, 5, 8);
      assertEquals(expected, actual);
   }
   
   @Test
   public void minOfThreeTest7() {
      int expected = 5;
      int actual = MinOfThree.min2(5, 5, 6);
      assertEquals(expected, actual);
   }
   
   @Test
   public void minOfThreeTest8() {
      int expected = 2;
      int actual = MinOfThree.min2(2, 2, 2);
      assertEquals(expected, actual);
   }
   
   @Test
   public void minOfThreeTest4() {
      int expected = 2;
      int actual = MinOfThree.min1(2, 2, 2);
      assertEquals(expected, actual);
   }
}
