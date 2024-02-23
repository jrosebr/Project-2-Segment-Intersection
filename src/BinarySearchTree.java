import java.util.LinkedList;
import java.util.List;
import java.util.function.BiPredicate;

/**
 * TODO: This is your first major task.
 * <p>
 * This class implements a generic unbalanced binary search tree (BST).
 */

public class BinarySearchTree<K> implements OrderedSet<K> {

    /**
     * A Node<K> is a Location (defined in OrderedSet.java), which
     * means that it can be the return value of a search on the tree.
     */

    static class Node<K> implements Location<K> {

        protected K data;
        protected Node<K> left, right;
        protected Node<K> parent;
        protected int height;

        /**
         * Constructs a leaf Node<K> with the given key.
         */
        public Node(K key) {
            this(key, null, null);
        }

        /**
         * TODO
         * <p>
         * Constructs a new Node<K> with the given values for fields.
         */

        //Assigns the values to the inputed variables
        public Node(K data, Node<K> left, Node<K> right)
        {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        /**
         * Provide the get() method required by the Location interface.
         */
        @Override
        public K get() {
            return data;
        }

        /**
         * Return true iff this Node<K> is a leaf in the tree.
         */
        protected boolean isLeaf() {
            return left == null && right == null;
        }

        /**
         * TODO
         * <p>
         * Performs a local update on the height of this Node<K>. Assumes that the
         * heights in the child Node<K>s are correct. Returns true iff the height
         * actually changed. This function *must* run in O(1) time.
         */
        protected boolean updateHeight()
        {
            int left_height = get_height(left);
            int right_height = get_height(right);

            //Checks if the Nodes children are smaller than this Node's current height - 1
            if (left_height < this.height - 1 && right_height < this.height - 1)
            {
                this.height = 1 + Math.max(left_height, right_height);
                return true;
            }

            //Checks if either of the Node's children are larger than this Node's current height
            else if (left_height >= this.height || right_height >= this.height)
            {
                this.height = 1 + Math.max(left_height, right_height);
                return true;
            }

            return false;
        }

        /**
         * TODO
         * <p>
         * Returns the location of the Node<K> containing the inorder predecessor
         * of this Node<K>.
         */

        //BinaryTree Code Lab 3

        // Return the last node wrt. inorder in this subtree.
        public Node last() {


            Node current = this;

            //Check if right is null
            if (current.right == null)
            {
                return current;
            }

            else
            {
                return current.right.last();
            }
        }

        public Node first() {

            Node current = this;

            if (current.left == null)
            {
                return current;
            }

            else
            {
                return current.left.first();
            }
        }

        public Node prevAncestor() {

            Node current = this;

            if (current.parent != null && current == parent.left)
            {
                return parent.prevAncestor();
            }

            else
            {
                return parent;
            }
        }

        public Node nextAncestor()
        {

            Node current = this;

            if (current.parent != null && current == parent.right)
            {
                return parent.nextAncestor();
            }

            else
            {
                return parent;
            }
        }

        @Override
        public Location<K> previous()
        {

            Node current = this;

            if (current.left != null)
            {
                return left.last();
            }

            else
            {
                return current.prevAncestor();
            }
        }

        /**
         * TODO
         * <p>
         * Returns the location of the Node<K> containing the inorder successor
         * of this Node<K>.
         */
        @Override
        public Location<K> next() {

            Node current = this;

            if (current.right != null)
            {
                return right.first();
            }

            else
            {
                return current.nextAncestor();
            }
        }

        public boolean isAVL() {
            int h1, h2;
            h1 = get_height(left);
            h2 = get_height(right);
            return Math.abs(h2 - h1) < 2;
        }

        public String toString() {
            return toStringPreorder(this);
        }

    }

    protected Node<K> root;
    protected int numNodes;
    protected BiPredicate<K, K> lessThan;

    /**
     * Constructs an empty BST, where the data is to be organized according to
     * the lessThan relation.
     */
    public BinarySearchTree(BiPredicate<K, K> lessThan) {
        this.lessThan = lessThan;
    }

    /**
     * TODO
     * <p>
     * Looks up the key in this tree and, if found, returns the
     * location containing the key.
     */

    protected Node<K> find(K key, Node<K> curr, Node<K> parent)
    {
        if (curr == null)
            return parent;

        else if (lessThan.test(key, curr.data))
            return find(key, curr.left, curr);

        else if (lessThan.test(curr.data, key))
            return find(key, curr.right, curr);

        else
            return curr;
    }

    public Node<K> search(K key) {
        Node<K> n = find(key, root, null);

        if (n == null)
            return null;

        else if (n.data.equals(key))
            return n;

        else
            return null;
    }

    /**
     * TODO
     * <p>
     * Returns the height of this tree. Runs in O(1) time!
     */
    public int height() {
        return 0;  // delete this line and add your code
    }

    /**
     * TODO
     * <p>
     * Clears all the keys from this tree. Runs in O(1) time!
     */
    public void clear() {
        root.left = null;
        root.right = null;
        numNodes = 0;
    }

    /**
     * Returns the number of keys in this tree.
     */
    public int size() {
        return numNodes;
    }

    /**
     * TODO
     * <p>
     * Inserts the given key into this BST, as a leaf, where the path
     * to the leaf is determined by the predicate provided to the tree
     * at construction time. The parent pointer of the new Node<K> and
     * the heights in all Node<K> along the path to the root are adjusted
     * accordingly.
     * <p>
     * Note: we assume that all keys are unique. Thus, if the given
     * key is already present in the tree, nothing happens.
     * <p>
     * Returns the location where the insert occurred (i.e., the leaf
     * Node<K> containing the key), or null if the key is already present.
     */
    public Node<K> insert(K key) {
        return null;  // delete this line and add your code
    }

    /**
     * Returns a textual representation of this BST.
     */
    public String toString() {
        return toStringPreorder(root);
    }

    /**
     * Returns true iff the given key is in this BST.
     */
    public boolean contains(K key) {
        Node<K> p = search(key);
        return p != null;
    }

    /**
     * TODO
     * <p>
     * Removes the key from this BST. If the key is not in the tree,
     * nothing happens.
     */
    public void remove(K key) {
        // delete this line and add your code
    }

    /**
     * TODO * <p> * Returns a sorted list of all the keys in this tree.
     */
    public List<K> keys() {
        return null;  // delete this line and add your code
    }

    static private <K> String toStringPreorder(Node<K> p) {
        if (p == null) return ".";
        String left = toStringPreorder(p.left);
        if (left.length() != 0) left = " " + left;
        String right = toStringPreorder(p.right);
        if (right.length() != 0) right = " " + right;
        String data = p.data.toString();
        return "(" + data + "[" + p.height + "]" + left + right + ")";
    }

    /**
     * The get_height method returns the height of the Node<K> n, which may be null.
     */
    static protected <K> int get_height(Node<K> n) {
        if (n == null) return -1;
        else return n.height;
    }
}
