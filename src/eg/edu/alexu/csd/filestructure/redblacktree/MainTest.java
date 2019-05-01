package eg.edu.alexu.csd.filestructure.redblacktree;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class MainTest {
    public static void main(String[] args) {
//        IRedBlackTree<Integer,String> redBlackTree = new RedBlackTree();
//        Integer x = 15;
//        Class c = x.getClass();
//        System.out.println(redBlackTree.isEmpty());
//        redBlackTree.insert(5, "one");
//        redBlackTree.insert(3, "two");
//        redBlackTree.insert(1, "three");
//        redBlackTree.insert(7, "four");
//        System.out.println(redBlackTree.getRoot().getKey());
//        redBlackTree.insert(5, "5");
//        redBlackTree.insert(2, "2");
//        redBlackTree.insert(7, "7");
//        redBlackTree.insert(4, "4");
//        redBlackTree.insert(12, "12");
//        redBlackTree.insert(1, "1");
//        redBlackTree.insert(8, "8");
//        redBlackTree.insert(20, "20");
//        redBlackTree.insert(10, "10");
//        redBlackTree.insert(9, "9");
//        redBlackTree.insert(16, "16");
        ITreeMap treeMap = new TreeMap();
        treeMap.put(5, "5");
        treeMap.put(2, "2");
        treeMap.put(7, "7");
        treeMap.put(4, "4");
        treeMap.put(12, "12");
        treeMap.put(1, "1");
        treeMap.put(8, "8");
        treeMap.put(20, "20");
        treeMap.put(10, "10");
        treeMap.put(9, "9");
        treeMap.put(16, "16");
        Collection arrayList = treeMap.values();
        java.util.TreeMap<Integer, String> t = new java.util.TreeMap<Integer, String>();
        Map.Entry entry = t.firstEntry();
        System.out.println(treeMap.containsValue("9"));

    }
}
