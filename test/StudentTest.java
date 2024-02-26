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

    /**
     * TODO: Test cases
     */
    @Test
    public void test() {
        insertSmallBST();
        // your tests go here
    }

}
