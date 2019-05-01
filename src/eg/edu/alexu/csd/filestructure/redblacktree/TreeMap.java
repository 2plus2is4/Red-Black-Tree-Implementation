package eg.edu.alexu.csd.filestructure.redblacktree;

import javax.management.RuntimeErrorException;
import java.util.*;

public class TreeMap<T extends Comparable<T>, V> implements ITreeMap<T, V> {

    private IRedBlackTree<T, V> redBlackTree = new RedBlackTree<>();
    private int size = 0;

    @Override
    public Map.Entry<T, V> ceilingEntry(T key) {
        if (key == null) throw new RuntimeErrorException(new Error());
        INode<T, V> x = binarySearch(redBlackTree.getRoot(), key);
        return new AbstractMap.SimpleEntry<>(x.getKey(), x.getValue());
    }

    @Override
    public T ceilingKey(T key) {
        if (key == null) throw new RuntimeErrorException(new Error());
        return binarySearch(redBlackTree.getRoot(), key).getKey();
    }

    @Override
    public void clear() {
        redBlackTree.clear();
        size = 0;
    }

    @Override
    public boolean containsKey(T key) {
        return redBlackTree.contains(key);
    }

    @Override
    public boolean containsValue(V value) {
        if (value == null) throw new RuntimeErrorException(new Error());
        for (V v : values())
            if (v.equals(value))
                return true;
        return false;
    }

    @Override
    public Set<Map.Entry<T, V>> entrySet() {
        arrayList = new ArrayList<>();
        inline(redBlackTree.getRoot(), null);
        return new LinkedHashSet<>(arrayList);
    }

    private ArrayList<Map.Entry<T, V>> arrayList = new ArrayList<>();

    private void inline(INode<T, V> node, T key) {
        if (node.isNull()) return;
        inline(node.getLeftChild(), key);
        if (key == null)
//            arrayList.add(node);
            arrayList.add(new AbstractMap.SimpleEntry<>(node.getKey(), node.getValue()));
        else if (node.getKey().compareTo(key) <= 0)
//            arrayList.add(node);
            arrayList.add(new AbstractMap.SimpleEntry<>(node.getKey(), node.getValue()));
        inline(node.getRightChild(), key);
    }

    @Override
    public Map.Entry<T, V> firstEntry() {
        if (size == 0)
            return null;
        INode<T, V> node = redBlackTree.getRoot();
        if (node.getLeftChild() != null)
            while (!node.getLeftChild().isNull())
                node = node.getLeftChild();
        return new AbstractMap.SimpleEntry<>(node.getKey(), node.getValue());
    }

    @Override
    public T firstKey() {
        Map.Entry entry = firstEntry();
        if (entry != null)
            return firstEntry().getKey();
        else return null;
    }

    @Override
    public Map.Entry<T, V> floorEntry(T key) {
        V temp = redBlackTree.search(key);
        if (temp == null) {
            arrayList = new ArrayList<>();
            inline(redBlackTree.getRoot(), key);
            return arrayList.get(arrayList.size() - 1);
        } else return new AbstractMap.SimpleEntry<>(key, temp);
    }

    @Override
    public T floorKey(T key) {
        return floorEntry(key).getKey();
    }

    @Override
    public V get(T key) {
        if (key == null) throw new RuntimeErrorException(new Error());
        return redBlackTree.search(key);
    }

    @Override
    public ArrayList<Map.Entry<T, V>> headMap(T toKey) {
        if (toKey == null) throw new RuntimeErrorException(new Error());
        arrayList = new ArrayList<>();
        inline(redBlackTree.getRoot(), toKey);
        if (arrayList.get(arrayList.size() - 1).getKey().compareTo(toKey) == 0)
            arrayList.remove(arrayList.size() - 1);
        return arrayList;
    }

    @Override
    public ArrayList<Map.Entry<T, V>> headMap(T toKey, boolean inclusive) {
        if (toKey == null) throw new RuntimeErrorException(new Error());
        arrayList = new ArrayList<>();
        inline(redBlackTree.getRoot(), toKey);
        return inclusive ? arrayList : headMap(toKey);
    }

    @Override
    public Set<T> keySet() {
        Set<T> keys = new LinkedHashSet<>();
        arrayList = new ArrayList<>();
        inline(redBlackTree.getRoot(), null);
        for (Map.Entry<T, V> anArrayList : arrayList) {
            keys.add(anArrayList.getKey());
        }
        return keys;
    }

    @Override
    public Map.Entry<T, V> lastEntry() {
        if (size == 0)
            return null;
        INode<T, V> node = redBlackTree.getRoot();
        if (node.getRightChild() != null)
            while (!node.getRightChild().isNull())
                node = node.getRightChild();
        return new AbstractMap.SimpleEntry<>(node.getKey(), node.getValue());
    }

    @Override
    public T lastKey() {
        Map.Entry<T, V> entry = lastEntry();
        if (entry != null)
            return lastEntry().getKey();
        else return null;
    }

    @Override
    public Map.Entry<T, V> pollFirstEntry() {
        Map.Entry<T, V> entry = firstEntry();
        if (entry != null)
            remove(entry.getKey());
        return entry;
    }

    @Override
    public Map.Entry<T, V> pollLastEntry() {
        Map.Entry<T, V> entry = lastEntry();
        if (entry != null)
            remove(entry.getKey());
        return entry;
    }

    @Override
    public void put(T key, V value) {
        if (key == null || value == null) throw new RuntimeErrorException(new Error());
        if(!redBlackTree.contains(key))
            size++;
        redBlackTree.insert(key, value);
    }

    @Override
    public void putAll(Map<T, V> map) {
        if (map == null) throw new RuntimeErrorException(new Error());
        for (Map.Entry<T, V> entry : map.entrySet()) {
            redBlackTree.insert(entry.getKey(), entry.getValue());
            size++;
        }
    }

    @Override
    public boolean remove(T key) {
        if (key == null) throw new RuntimeErrorException(new Error());
        boolean ans = redBlackTree.delete(key);
        if (ans) size--;
        return ans;

    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Collection<V> values() {
        arrayList = new ArrayList<>();
        inline(redBlackTree.getRoot(), null);
        ArrayList<V> ans = new ArrayList<>();
        for (Map.Entry<T, V> entry : arrayList) {
            ans.add(entry.getValue());
        }
        return ans;
    }

    private Stack<INode<T, V>> stack = new Stack<>();

    private INode<T, V> binarySearch(INode<T, V> node, T key) {
        stack.push(node);
        if (node.getKey() == null)
            if (node != redBlackTree.getRoot() && node == redBlackTree.getRoot().getParent()) {
                stack.pop();
                INode<T, V> x = stack.pop();
                stack = new Stack<>();
                return x;
            } else throw new RuntimeErrorException(new Error());
        if (node.getKey().compareTo(key) == 0)
            return node;
        if (node.getKey().compareTo(key) > 0)
            return binarySearch(node.getLeftChild(), key);
        else
            return binarySearch(node.getRightChild(), key);
    }
}
