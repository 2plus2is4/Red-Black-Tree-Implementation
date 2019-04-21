package eg.edu.alexu.csd.filestructure.sort;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


public class Heap implements IHeap {
    private INode root;
    private ArrayList<INode> heap = new ArrayList<>();
    private int size = 0;

    private class Node<T extends Comparable<T>> implements INode {
        int index;
        private T value;


        public Node(int index) {
            this.index = index;
        }

        @Override
        public INode getLeftChild() {
            if (2 * index > size) {
                return null;
            }
            return heap.get(2 * index);
        }

        @Override
        public INode getRightChild() {
            if (2 * index + 1 > size) {
                return null;
            }
            try {
                return heap.get(2 * index + 1);
            }catch (ArrayIndexOutOfBoundsException e){
                return null;
            }

        }

        @Override
        public INode getParent() {
            if (index / 2 < 0) {
                return null;
            }
            return heap.get(index / 2);
        }

        @Override
        public Comparable getValue() {
            return value;
        }

        @Override
        public void setValue(Comparable value) {
            this.value = (T) value;
        }


    }

    public Heap() {

        heap.add(null);
        root = heap.get(0);//.setValue(Integer.MIN_VALUE));
    }

    @Override
    public INode getRoot() {
        if (size< 1) {
            return null;
        }
//        return heap.get(1);
        return heap.get(1);
    }

    @Override
    public int size() {
        return this.size<0 ? 0:this.size;
    }

    @Override
    public void heapify(INode Node) {
        if (Node == null)
            return;
        Comparable larger;
        Comparable l = null;
        Comparable r = null;
        if (Node.getLeftChild() != null) {
            l = Node.getLeftChild().getValue();
        }
        if (Node.getRightChild() != null) {
            r = Node.getRightChild().getValue();
        }
        if (Node.getLeftChild() != null && l.compareTo(Node.getValue()) > 0) {
            larger = l;

        } else {
            larger = Node.getValue();
        }
        if (Node.getRightChild() != null && r.compareTo(larger) > 0) {
            larger = r;
        }
        if (larger != Node.getValue()) {
            if (larger == l) {
                swap(Node.getLeftChild(), Node);
                Node = Node.getLeftChild();
            } else {
                swap(Node.getRightChild(), Node);
                Node = Node.getRightChild();
            }

            heapify(Node);
        }
        root = heap.get(1);
    }

    @Override
    public Comparable extract() {
        if (this.size < 1) {
            return null;
        }
        Comparable max = (Comparable) heap.get(1).getValue();
        swap(heap.get(1), heap.get(size--));
        heapify(heap.get(1));
        return max;
    }

    @Override
    public void insert(Comparable element) {
        if (element == null)
            return;
        INode n = new Node(++size);
        n.setValue(element);
        heap.add(size, n);
        int current = size;
        if (size == 1)
            return;
        while (heap.get(current).getValue().compareTo(heap.get(current).getParent().getValue()) > 0) {
            swap(heap.get(current), heap.get(current).getParent());
            if (current / 2 == 1)
                return;
            current /= 2;
        }

    }

    @Override
    public void build(Collection unordered) {
        //TODO check the complexity
        if (unordered != null && unordered.size() > 0) {
            this.heap = new ArrayList<>();
            this.size = unordered.size();
//            Node n = new Node(0);
//            n.setValue(Integer.MAX_VALUE);
            heap.add(null);//.setValue(Integer.MIN_VALUE));
        } else {
            this.heap = new ArrayList<>();
            size = 0;
            return;
        }
        int i = 1;
        Iterator itr = unordered.iterator();
        while (itr.hasNext()) {
            Node n = new Node(i++);
            n.setValue((Comparable) itr.next());
            heap.add(n);
        }
        size = unordered.size();
        for (int ii = size / 2; ii >= 1; ii--) {
            heapify(heap.get(ii));
        }
    }

    public void sort(){
        // One by one extract an element from heap
        for (int i = heap.size() - 1; i >= 0; i--) {
            extract();
        }
        size = heap.size()-1;
    }

    // Function to swap two Nodes of the heap
    private void swap(INode fpos, INode spos) {
        Comparable tmp;
        tmp = fpos.getValue();
        fpos.setValue(spos.getValue());
        spos.setValue(tmp);
    }

}
