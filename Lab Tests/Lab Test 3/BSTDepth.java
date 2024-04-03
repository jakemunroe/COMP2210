/**
 * Complete the depth method in the nested BinarySearchTree class below.
 *
 */
public class BSTDepth {

   /** Provides an example. */
   public static void main(String[] args) {
      BinarySearchTree<Integer> iBst = new BinarySearchTree<>();
      iBst.add(9);
      iBst.add(7);
      iBst.add(13);
      iBst.add(11);
      iBst.add(15);
      int height = iBst.height();
      int depth = iBst.depth(7);
      // The following statement should print -1.
      System.out.println(depth);
      depth = iBst.depth(9);
      // The following statement should print 1.
      System.out.println(depth);
      depth = iBst.depth(13);
      // The following statement should print 4.
      System.out.println(depth);
   
      BinarySearchTree<Integer> sBst = new BinarySearchTree<>();
      sBst.add(18);
      sBst.add(14);
      sBst.add(20);
      sBst.add(12);
      sBst.add(16);
      depth = sBst.depth(20);
      // The following statement should print 1.
      System.out.println(depth);
   
      depth = sBst.depth(14);
      // The following statement should print 2.
      System.out.println(depth);
   
      depth = sBst.depth(16);
      // The following statement should print 5.
      System.out.println(depth);
   }



   /** Defines a binary search tree. */
   static class BinarySearchTree<T extends Comparable<T>> {
   
      // the root of this binary search tree
      private Node root;
   
      // the number of nodes in this binary search tree
      private int size;
   
      /** Defines the node structure for this binary search tree. */
      private class Node {
         T element;
         Node left;
         Node right;
      
         /** Constructs a node containing the given element. */
         public Node(T elem) {
            element = elem;
            left = null;
            right = null;
         }
      }
   
   
      /* >>>>>>>>>>>>>>>>>> YOUR WORK STARTS HERE <<<<<<<<<<<<<<<< */
   
   
      ///////////////////////////////////////////////////////////////////////////////
      //      I M P L E M E N T   T H E   D E P T H   M E T H O D   B E L O W      //
      ///////////////////////////////////////////////////////////////////////////////
   
      /**
       * Returns the depth of the node containing value 
       * or -1 if value not present.
       */
      public int depth(T value) {
         
         if (value == null || isEmpty()) {
            return -1;
         }
         
         return search(root, value);
      }
      
      public int search(Node n, T value) {
         int depth = 1;
         if (n == null)
            return -1;
         else {
            if (value.compareTo(n.element) == 0) {
               return depth;
            }
            else if (value.compareTo(n.element) < 0) {
               depth++;
               return search(n.left, value);
            }
            else {
               depth++;
               return search(n.right, value);
            }
         }
      }
      
      public boolean contains(T value) {
         if (isEmpty() || value == null) {
            return false;
         }
      
         if (value.compareTo(root.element) == 0) {
            return true;   
         }
         
         else if (root.left != null && value.compareTo(root.element) < 0) {
            root = root.left;
            return contains(value);
         }
         
         else if (root.right !=null && value.compareTo(root.element) > 0) {
            root = root.right;
            return contains(value);
         }
         
         return false;
      }
   
   
      /* >>>>>>>>>>>>>>>>>> YOUR WORK ENDS HERE <<<<<<<<<<<<<<<< */
   
   
   
      ////////////////////////////////////////////////////////////////////
      //  D O   N O T   M O D I F Y   B E L O W   T H I S   P O I N T   //
      ////////////////////////////////////////////////////////////////////
   
   
   
      ////////////////////
      // M E T R I C S  //
      ////////////////////
   
      /**
       * Returns the number of elements in this bst.
       */
      public int size() {
         return size;
      }
   
      /**
       * Returns true if this bst is empty, false otherwise.
       */
      public boolean isEmpty() {
         return size == 0;
      }
   
      /**
       * Returns the height of this bst.
       */
      public int height() {
         return height(root);
      }
   
      /**
       * Returns the height of node n in this bst.
       */
      private int height(Node n) {
         if (n == null) {
            return 0;
         }
         int leftHeight = height(n.left);
         int rightHeight = height(n.right);
         return 1 + Math.max(leftHeight, rightHeight);
      }
   
   
      ////////////////////////////////////
      // A D D I N G   E L E M E N T S  //
      ////////////////////////////////////
   
      /**
       * Ensures this bst contains the specified element. Uses an iterative implementation.
       */
      public void add(T element) {
         // special case if empty
         if (root == null) {
            root = new Node(element);
            size++;
            return;
         }
      
         // find where this element should be in the tree
         Node n = root;
         Node parent = null;
         int cmp = 0;
         while (n != null) {
            parent = n;
            cmp = element.compareTo(parent.element);
            if (cmp == 0) {
               // don't add a duplicate
               return;
            } else if (cmp < 0) {
               n = n.left;
            } else {
               n = n.right;
            }
         }
      
         // add element to the appropriate empty subtree of parent
         if (cmp < 0) {
            parent.left = new Node(element);
         } else {
            parent.right = new Node(element);
         }
         size++;
      }
   
   }

}
