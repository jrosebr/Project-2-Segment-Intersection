import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.Random;

import static java.lang.Math.log;

public class StudentTest {

    @Test
    public void insertSmallBST() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>((Integer x, Integer y) -> x < y);
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int[] a = new int[]{4, 8, 0, 2, 6, 10};
        /*
         *       4       [2]
         *     /  \
         *    /    \
         *   0      8    [1]
         *    \    / \
         *     2  6   10 [0]
         */
        for (Integer key : a) {
            bst.insert(key);
            map.put(key, key);
        }

        System.out.println(bst.root); // 4
        System.out.println(bst.root.left); // 0
        System.out.println(bst.root.left.right); // 2
        System.out.println(bst.root.right); // 8
        System.out.println(bst.root.right.left); // 6
        System.out.println(bst.root.right.right); // 10


        for (int i = 0; i != 11; ++i) {
            assertEquals(bst.contains(i), map.containsKey(i));
        }
    }

    @Test
    public void removeSmallBST()
    {

        BinarySearchTree<Integer> bst = new BinarySearchTree<>((Integer x, Integer y) -> x < y);
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int[] a = new int[]{4, 8, 0, 2, 6, 10};
        /*
         *       4
         *     /  \
         *    /    \
         *   0      8
         *    \    / \
         *     2  6   10
         */
        for (Integer key : a) {
            bst.insert(key);
            map.put(key, key);
        }

        bst.remove(10);
        bst.remove(100); // Shouldn't remove anything

        System.out.println(bst.root); // 4
        System.out.println(bst.root.left); // 0
        System.out.println(bst.root.left.right); // 2
        System.out.println(bst.root.right); // 8
        System.out.println(bst.root.right.left); // 6
        System.out.println(bst.root.right.right); // null

        for (int i = 0; i != 10; ++i) {
            assertEquals(bst.contains(i), map.containsKey(i));
        }
    }

    @Test
    public void insertSmallAVL()
    {
        AVLTree<Integer> avl = new AVLTree<>((Integer x, Integer y) -> x < y);
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int[] a = new int[]{4, 8, 0, 2, 6, 10};
        /*
         *       4
         *     /  \
         *    /    \
         *   0      8
         *    \    / \
         *     2  6   10
         */
        for (Integer key : a) {
            avl.insert(key);
            map.put(key, key);
        }

        avl.insert(11);
        avl.insert(12);
        avl.insert(10); // Shouldn't insert anything

        //System.out.println(avl.isAVL());

        System.out.println(avl.root); // 4
        /*System.out.println(avl.root.left.parent); // 0
        System.out.println(avl.root.left.right.parent); // 2
        System.out.println(avl.root.right.parent); // 8
        System.out.println(avl.root.right.left.parent); // 6
        System.out.println(avl.root.right.right.parent); // 10
        System.out.println(avl.root.right.right.right.parent); // 11*/

        for (int i = 0; i != 10; ++i) {
            assertEquals(avl.contains(i), map.containsKey(i));
        }
    }

    @Test
    public void removeSmallAVL()
    {
        AVLTree<Integer> avl = new AVLTree<>((Integer x, Integer y) -> x < y);
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int[] a = new int[]{4, 8, 0, 2, 6, 10};
        /*
         *       4
         *     /  \
         *    /    \
         *   0      8
         *    \    / \
         *     2  6   10
         */
        for (Integer key : a) {
            avl.insert(key);
            map.put(key, key);
        }

        avl.remove(4);
        map.remove(4);
        avl.remove(11); // Shouldn't remove anything

        //System.out.println(avl.isAVL());

        System.out.println(avl.root); // 4
        /*System.out.println(avl.root.left); // 0
        System.out.println(avl.root.left.right); // 2
        System.out.println(avl.root.right); // 8
        System.out.println(avl.root.right.left); // 6
        System.out.println(avl.root.right.right); // null*/

        for (int i = 0; i != 11; ++i) {
            assertEquals(avl.contains(i), map.containsKey(i));
        }
    }

    /**
     * TODO: Test cases
     */
    @Test
    public void test() {
        insertSmallBST();
        removeSmallBST();
    }

}
