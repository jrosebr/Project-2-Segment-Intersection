import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

/**
 * TODO: This is your second major task.
 * <p>
 * This class implements a height-balanced binary search tree,
 * using the AVL algorithm. Beyond the constructor, only the insert()
 * and remove() methods need to be implemented. All other methods are unchanged.
 */

public class AVLTree<K> extends BinarySearchTree<K> {

    /**
     * Creates an empty AVL tree as a BST organized according to the
     * lessThan predicate.
     */
    public AVLTree(BiPredicate<K, K> lessThan) {
        super(lessThan);
    }

    public boolean isAVL() {
        if (root == null)
            return true;
        else
            return root.isAVL();
    }

    /**
     * TODO
     * Inserts the given key into this AVL tree such that the ordering
     * property for a BST and the balancing property for an AVL tree are
     * maintained.
     */

    public Node insert(K key) {
        root = insert(root, key);
        return root;
    }

    private Node insert(Node<K> curr, K key)
    {
        if (curr == null)
            return new Node(key);

        else if (lessThan.test(key, curr.data))
            curr.left = insert(curr.left, key);

        else if (lessThan.test(curr.data, key))
            curr.right = insert(curr.right, key);

        else
            return curr;


        curr.height = 1 + Math.max(height(curr.left), height(curr.right));

        int balance = getBalance(curr);

        // Left Left Case
        if (balance > 1 && lessThan.test(key, curr.data))
        {
            return rightRotate(curr);
        }

        // Right Right Case
        else if (balance < -1 && lessThan.test(curr.data, key))
        {
            return leftRotate(curr);
        }

        // Left Right Case
        else if (balance > 1 && lessThan.test(curr.data, key))
        {
            curr.left = leftRotate(curr.left);
            return rightRotate(curr);
        }


        // Right Left Case
        else if (balance < -1 && lessThan.test(key, curr.data))
        {
            curr.right = rightRotate(curr.right);
            return leftRotate(curr);
        }

        else // Return the unchanged node pointer
            return curr;
    }

    private Node<K> leftRotate(Node<K> y)
    {
        Node<K> x = y.right;
        Node<K> T2 = x.left;

        // Perform Rotation
        x.left = y;
        y.right = T2;

        // Update heights
        y.height = 1 + Math.max(height(y.left), height(y.right));
        x.height = 1 + Math.max(height(x.left), height(x.right));

        return x;
    }

    private Node<K> rightRotate(Node<K> x)
    {
        Node<K> y = x.left;
        Node<K> T2 = y.right;

        // Perform Rotation
        y.right = x;
        x.left = T2;

        // Update heights
        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));

        return y;
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private int getBalance(Node<K> curr)
    {
        if (curr == null)
            return 0;

        else
            return height(curr.left) - height(curr.right);
    }

    /**
     * TODO
     * <p>
     * Removes the key from this BST. If the key is not in the tree,
     * nothing happens.
     */
    public void remove(K key) {
        root = remove(root, key);
    }

    private Node<K> remove(Node<K> curr, K key)
    {
        if (curr == null)
        {
            root.updateHeight();
            return null;
        }

        else if (lessThan.test(key, curr.data))
        { // remove in left subtree
            curr.left = remove(curr.left, key);
        }

        else if (lessThan.test(curr.data, key))
        { // remove in right subtree
            curr.right = remove(curr.right, key);
        }

        else // Remove this node
        {
            // Node with only one child or no child
            if (curr.left == null || curr.right == null)
            {
                Node<K> temp = null;

                if (temp == curr.left)
                    temp = curr.right;

                else
                    temp = curr.left;

                // No child case
                if (temp == null)
                {
                    temp = curr;
                    curr = null;
                }

                else // One child case
                    curr = temp;
            }

            else
            {
                // Node with two children
                Node<K> temp = minValueNode(curr.right);

                curr.data = temp.data;

                curr.right = remove(curr.right, temp.data);
            }
        }

        if (curr == null)
            return curr;

        curr.height = 1 + Math.max(height(curr.left), height(curr.right));

        int balance = getBalance(curr);

        // Left Left Case
        if (balance > 1 && getBalance(curr.left) >= 0)
        {
            return rightRotate(curr);
        }

        // Right Right Case
        else if (balance < -1 && getBalance(curr.right) <= 0)
        {
            return leftRotate(curr);
        }

        // Left Right Case
        else if (balance > 1 && getBalance(curr.left) < 0)
        {
            curr.left = leftRotate(curr.left);
            return rightRotate(curr);
        }


        // Right Left Case
        else if (balance < -1 && getBalance(curr.right) > 0)
        {
            curr.right = rightRotate(curr.right);
            return leftRotate(curr);
        }

        else // Return the unchanged node pointer
            return curr;
    }


    private Node<K> minValueNode (Node<K> node)
    {
        Node<K> curr = node;

        while (curr.left != null)
        {
            curr = curr.left;
        }

        return curr;
    }
}
