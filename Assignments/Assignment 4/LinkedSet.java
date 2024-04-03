import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Provides an implementation of the Set interface.
 * A doubly-linked list is used as the underlying data structure.
 * Although not required by the interface, this linked list is
 * maintained in ascending natural order. In those methods that
 * take a LinkedSet as a parameter, this order is used to increase
 * efficiency.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @author YOUR NAME (you@auburn.edu)
 *
 */
public class LinkedSet<T extends Comparable<T>> implements Set<T> {

   //////////////////////////////////////////////////////////
   // Do not change the following three fields in any way. //
   //////////////////////////////////////////////////////////

   /** References to the first and last node of the list. */
   Node front;
   Node rear;

   /** The number of nodes in the list. */
   int size;

   /////////////////////////////////////////////////////////
   // Do not change the following constructor in any way. //
   /////////////////////////////////////////////////////////

   /**
    * Instantiates an empty LinkedSet.
    */
   public LinkedSet() {
      front = null;
      rear = null;
      size = 0;
   }


   //////////////////////////////////////////////////
   // Public interface and class-specific methods. //
   //////////////////////////////////////////////////

   ///////////////////////////////////////
   // DO NOT CHANGE THE TOSTRING METHOD //
   ///////////////////////////////////////
   /**
    * Return a string representation of this LinkedSet.
    *
    * @return a string representation of this LinkedSet
    */
   @Override
   public String toString() {
      if (isEmpty()) {
         return "[]";
      }
      StringBuilder result = new StringBuilder();
      result.append("[");
      for (T element : this) {
         result.append(element + ", ");
      }
      result.delete(result.length() - 2, result.length());
      result.append("]");
      return result.toString();
   }


   ///////////////////////////////////
   // DO NOT CHANGE THE SIZE METHOD //
   ///////////////////////////////////
   /**
    * Returns the current size of this collection.
    *
    * @return  the number of elements in this collection.
    */
   public int size() {
      return size;
   }

   //////////////////////////////////////
   // DO NOT CHANGE THE ISEMPTY METHOD //
   //////////////////////////////////////
   /**
    * Tests to see if this collection is empty.
    *
    * @return  true if this collection contains no elements, false otherwise.
    */
   public boolean isEmpty() {
      return (size == 0);
   }


   /**
    * Ensures the collection contains the specified element. Neither duplicate
    * nor null values are allowed. This method ensures that the elements in the
    * linked list are maintained in ascending natural order.
    *
    * @param  element  The element whose presence is to be ensured.
    * @return true if collection is changed, false otherwise.
    */
   public boolean add(T element) {
      Node n1 = new Node(element);
      Node n2 = orderLocate(element);
   
      if(element == null) {
         throw new NullPointerException();
      }
          
      if(this.contains(element)) {
         return false;
      }
   
      if(isEmpty()) {
         front = n1;
         rear = n1;
         size++;
         return true;
      }
      
      else if (front.element.compareTo(element) > 0) {
         n1.next = front;
         front.prev = n1;
         front = n1;
         size++;
         return true;
      }
      
      else if (rear.element.compareTo(element) < 0) {
         rear.next = n1;
         n1.prev = rear;
         rear = n1;
         size++;
         return true;
      }
      
      else {
         n2.next.prev = n1;
         n1.next = n2.next;
         n2.next = n1;
         n1.prev = n2;
         size++;
         return true;
      }
   }

   /**
    * Ensures the collection does not contain the specified element.
    * If the specified element is present, this method removes it
    * from the collection. This method, consistent with add, ensures
    * that the elements in the linked lists are maintained in ascending
    * natural order.
    *
    * @param   element  The element to be removed.
    * @return  true if collection is changed, false otherwise.
    */
   public boolean remove(T element) {
      if (element == null) {
         throw new NullPointerException();
      }
   
      Node n = locate(element);
      
      if (isEmpty() || n == null) { //if given empty set or no match
         return false;
      }
   
      if (size == 1) {
         front = null;
         rear = null;
         size = 0;
         return true;
      }
   
      if (n == front) {
         front = front.next;
         front.prev = null;
         size--;
         return true;
      }
      
      if (n == rear) {
         rear.prev.next = null;
         rear = rear.prev;
         size--;
         return true;
      }
      
      else {
         n.prev.next = n.next;
         if (n.next != null) {
            n.next.prev = n.prev;
         }
         size--;
         return true;
      }
   }


   /**
    * Searches for specified element in this collection.
    *
    * @param   element  The element whose presence in this collection is to be tested.
    * @return  true if this collection contains the specified element, false otherwise.
    */
   public boolean contains(T element) {
     
      if (isEmpty()) {
         return false;
      }
     
      return locate(element) != null;
   }


   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order.
    *
    * @return  true if this set contains exactly the same elements as
    *               the parameter set, false otherwise
    */
   public boolean equals(Set<T> s) {
      if (size == s.size() && complement(s).size() == 0) {
         return true;
      }
      
      else {
         return false;
      }
   }


   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order.
    *
    * @return  true if this set contains exactly the same elements as
    *               the parameter set, false otherwise
    */
   public boolean equals(LinkedSet<T> s) {
      if (size == s.size() && complement(s).size() == 0) {
         return true;
      }
      
      else {
         return false;
      }
   }


   /**
    * Returns a set that is the union of this set and the parameter set.
    *
    * @return  a set that contains all the elements of this set and the parameter set
    */
   public Set<T> union(Set<T> s){
      if (s == null) {
         return this; //for null param
      }
      
      if (this == null) {
         return s;
      }
      
      if (this == null && s == null) {
         throw new NullPointerException();
      }
      
      LinkedSet<T> unionSet = new LinkedSet<T>(); //return set
      
      Node n1 = front;
      Iterator<T> i = s.iterator(); //iterator for loop
      
      while(n1 != null) { //loop thru first
         unionSet.add(n1.element); //add elements
         n1 = n1.next;
      }
      
      while (i.hasNext()) { //loop thru second
         unionSet.add(i.next()); //add elements
      }
      
      return unionSet;
   }


   /**
    * Returns a set that is the union of this set and the parameter set.
    *
    * @return  a set that contains all the elements of this set and the parameter set
    */
   public Set<T> union(LinkedSet<T> s){
      if (s == null) {
         throw new NullPointerException();
      }
      
      LinkedSet<T> unionSet = new LinkedSet<T>();
      
      Node n = front;
      Node sN = s.front;
      
      while (n != null) {
         unionSet.add(n.element);
         n = n.next;
      }
      
      while (sN != null) {
         unionSet.add(sN.element);
         sN = sN.next;
      }
      
      return unionSet;
   }


   /**
    * Returns a set that is the intersection of this set and the parameter set.
    *
    * @return  a set that contains elements that are in both this set and the parameter set
    */
   public Set<T> intersection(Set<T> s) {
      if (s == null) {
         throw new NullPointerException();
      }
      
      LinkedSet<T> intersectSet = new LinkedSet<T>();
      
      Node n = front;
      
      while(n != null) { //loop thru list
         if (s.contains(n.element)) { //is this present?
            intersectSet.add(n.element); //add if mutual elements
         }
         n = n.next;
      }
      
      return intersectSet;
   }

   /**
    * Returns a set that is the intersection of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in both
    *            this set and the parameter set
    */
   public Set<T> intersection(LinkedSet<T> s) {
      if (s == null) {
         throw new NullPointerException();
      }
      
      LinkedSet<T> intersectSet = new LinkedSet<T>(); //set to store qualifying values
      
      Node n = front;
      
      while (n != null) { //loop thru this LinkedSet
         if (s.contains(n.element)) { //check if param contains current element
            intersectSet.add(n.element); //add mutual element
         }
         n = n.next;
      }
      
      return intersectSet; //return set
   }


   /**
    * Returns a set that is the complement of this set and the parameter set.
    *
    * @return  a set that contains elements that are in this set but not the parameter set
    */
   public Set<T> complement(Set<T> s) {
      if (s == null) {
         throw new NullPointerException();
      }
      
      LinkedSet<T> complementSet = new LinkedSet<T>(); //set to store qualifying values
      
      Node n = front;
      
      while(n != null) { //loop thru this LinkedSet
         if (!s.contains(n.element)) { //check if param doesn't contain current element
            complementSet.add(n.element); //if unique add to return set
         }
         n = n.next;
      }
      
      return complementSet; //return set
   }


   /**
    * Returns a set that is the complement of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in this
    *            set but not the parameter set
    */
   public Set<T> complement(LinkedSet<T> s) {
      if (s == null) {
         throw new NullPointerException();
      }
      
      LinkedSet<T> complementSet = new LinkedSet<T>(); //set to store qualifying values
      
      Node n = front;
      
      while (n != null) { // loop through this LinkedSet
         if (!s.contains(n.element)) { //check if param doesn't contain current element
            complementSet.add(n.element);  //if unique add to return set
         }
         n = n.next;
      }
      
      return complementSet; //return set
   }


   /**
    * Returns an iterator over the elements in this LinkedSet.
    * Elements are returned in ascending natural order.
    *
    * @return  an iterator over the elements in this LinkedSet
    */
   public Iterator<T> iterator() {
      return new SetIterator();
   }


   /**
    * Returns an iterator over the elements in this LinkedSet.
    * Elements are returned in descending natural order.
    *
    * @return  an iterator over the elements in this LinkedSet
    */
   public Iterator<T> descendingIterator() {
      return new DescendingIterator();
   }


   /**
    * Returns an iterator over the members of the power set
    * of this LinkedSet. No specific order can be assumed.
    *
    * @return  an iterator over members of the power set
    */
   public Iterator<Set<T>> powerSetIterator() {
      return new PowerIterator();
   }



   //////////////////////////////
   // Private utility methods. //
   //////////////////////////////

   // Feel free to add as many private methods as you need.
   
   private Node locate(T element) {
      Node n = front;
      while (n != null) {
         if (n.element.equals(element)) {
            return n;
         }
         n = n.next;
      }
      return null;
   }
      
   private Node orderLocate(T element) {
      Node n = front;
      while (n != null) {
         if (n.element.compareTo(element) > 0) {
            return n.prev;
         }
         n = n.next;
      }
      return n;
   }
      
   ////////////////////
   // Nested classes //
   ////////////////////

   //////////////////////////////////////////////
   // DO NOT CHANGE THE NODE CLASS IN ANY WAY. //
   //////////////////////////////////////////////

   /**
    * Defines a node class for a doubly-linked list.
    */
   class Node {
      /** the value stored in this node. */
      T element;
      /** a reference to the node after this node. */
      Node next;
      /** a reference to the node before this node. */
      Node prev;
   
      /**
       * Instantiate an empty node.
       */
      public Node() {
         element = null;
         next = null;
         prev = null;
      }
   
      /**
       * Instantiate a node that containts element
       * and with no node before or after it.
       */
      public Node(T e) {
         element = e;
         next = null;
         prev = null;
      }
   }
   
   private class SetIterator implements Iterator<T> {
   
      private Node n = front;
      T element;
      
      public boolean hasNext() {
         return n != null;
      }
      
      public T next() {
      
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         
         element = n.element;
         n = n.next;
         return element;
      }
   }
   
   private class DescendingIterator implements Iterator<T> {
   
      private Node n = rear;
      T element;
      
      public boolean hasNext() {
         return n != null;
      }
      
      public T next() {
      
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         
         element = n.element;
         n = n.prev;
         return element;
      }
   }
   
   private class PowerIterator implements Iterator<Set<T>> {
      //Problem: Returning an extra null array for next()
      //Possible Solutions: 
      
      private Node n = front;
      int current = 0;
      
      public boolean hasNext() {
         return current < Math.pow(2, size); //power iterator has 2^N elements
      }
      
      public Set<T> next() {
      
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         
         Set<T> t = new LinkedSet<T>(); //linked set for members
         
         for (int i = 0; i < size; i++) {
            
            if ((current & (1 << i)) > 0) {
               t.add(n.element);
            }
            
            if (n.next != null) {
               n = n.next;
            }
         }
         current++;
         return t;
      }
   }
}
