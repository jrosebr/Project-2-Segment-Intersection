import java.util.ArrayList;
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
            int original_height = get_height(this);

            int left_height = get_height(left);
            int right_height = get_height(right);

            if (original_height == 1 + Math.max(left_height, right_height))
                return false;

            else
            {
                height = 1 + Math.max(left_height, right_height);
                return true;
            }

        }

        protected boolean updateAncestorHeight()
        {
            boolean height_change = false;

            if (this.parent == null)
            {
                return height_change;
            }

            else
            {
                height_change = true;
                this.updateHeight();
                this.parent.updateAncestorHeight();
            }

            return height_change;
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

        public void findParent()
        {
            return findParent(this.root, node);
        }

        private Node<K> findParent(Node<K> curr, Node<K> node)
        {
            if (curr == null || curr == node)
                return null; //Node not found, or current Node is the root

            else if (curr.left != null || curr.left == node)
            {
                node.parent = curr;
                return curr;
            }

            Node<K> leftParent = findParent(curr.left, node);
            if (leftParent != null)
            {
                node.parent = leftParent;
                return leftParent; //Node found in the left subtree
            }

            node.parent = findParent(curr.right, node);
            return findParent(curr.right, node); //Node found in the right subree
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
        return get_height(root);
    }

    public Node<K> getParent()
    {

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

        Node<K> n = find(key, root, null);

        n.findParent();

        if (n == null)
        {
            root = new Node<K>(key, null, null);
            root.updateHeight();

            ++ numNodes;
            return root;
        }

        else if (lessThan.test(key, n.data))
        {
            n.left = new Node<K>(key, null, null);
            if (n.updateHeight())
                n.updateAncestorHeight();

            ++ numNodes;
            return n.left;
        }

        else if (lessThan.test(n.data, key))
        {
            n.right = new Node<K>(key, null, null);
            if (n.updateHeight())
                n.updateAncestorHeight();

            ++ numNodes;
            return n.right;
        }

        else // duplicate, return null
            return null;
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
        root = remove_helper(root, key);
    }

    private Node remove_helper(Node<K> curr, K key)
    {
        if (curr == null)
        {
            root.updateHeight();
            return null;
        }

        else if (lessThan.test(key, curr.data))
        { // remove in left subtree
            curr.left = remove_helper(curr.left, key);
            if (curr.updateHeight())
                curr.updateAncestorHeight();

            return curr;
        }

        else if (lessThan.test(curr.data, key))
        { // remove in right subtree
            curr.right = remove_helper(curr.right, key);
            if (curr.updateHeight())
                curr.updateAncestorHeight();

            return curr;
        }

        else
        { // remove this node
            if (curr.left == null)
            {
                if (curr.updateHeight())
                    curr.updateAncestorHeight();

                return curr.right;
            }

            else if (curr.right == null)
            {
                if (curr.updateHeight())
                    curr.updateAncestorHeight();
                return curr.left;
            }

            else // two children, replace with first of right subtree
            {
                Node<K> min = curr.right.first();
                curr.data = min.data;
                curr.right = remove_helper(curr.right, min.data);
                if (curr.updateHeight())
                    curr.updateAncestorHeight();
                return curr;
            }
        }
    }

    /**
     * TODO * <p> * Returns a sorted list of all the keys in this tree.
     */
    public List<K> keys() {

        List<K> keys = new ArrayList<>();
        inorderTraversal(root, keys);
        return keys;
    }

    private void inorderTraversal(Node<K> curr, List<K> keys)
    {
        if (curr != null)
        {
            inorderTraversal(curr.left, keys);
            keys.add(curr.data);
            inorderTraversal(curr.right, keys);
        }
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
