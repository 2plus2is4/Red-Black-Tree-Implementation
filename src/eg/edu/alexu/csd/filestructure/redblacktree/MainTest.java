package eg.edu.alexu.csd.filestructure.redblacktree;


import java.util.Random;

public class MainTest {
    public static void main(String[] args) {
        IRedBlackTree<Integer,String> redBlackTree = new RedBlackTree();
        Integer x = 15;
        Class c = x.getClass();
//        System.out.println(redBlackTree.isEmpty());
//        redBlackTree.insert(5, "one");
//        redBlackTree.insert(3, "two");
//        redBlackTree.insert(1, "three");
//        redBlackTree.insert(7, "four");
//        System.out.println(redBlackTree.getRoot().getKey());
        redBlackTree.insert(5, "5");
        redBlackTree.insert(2, "2");
        redBlackTree.insert(7, "7");
        redBlackTree.insert(4, "4");
        redBlackTree.insert(12, "12");
        redBlackTree.insert(1, "1");
        redBlackTree.insert(8, "8");
        redBlackTree.insert(20, "20");
        redBlackTree.insert(10, "10");
        redBlackTree.insert(9, "9");
        redBlackTree.insert(16, "16");
        System.out.println();
    }
}
