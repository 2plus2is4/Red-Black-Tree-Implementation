package eg.edu.alexu.csd.filestructure.redblacktree;

public class Node<T extends Comparable<T>, V> implements INode<T,V> {
    private INode<T,V> p;
    private INode<T,V> left;
    private INode<T,V> right;
    private T key;
    private V value;
    private boolean color;
    @Override
    public void setParent(INode<T, V> parent) {
        this.p = parent;
    }

    @Override
    public INode<T, V> getParent() {
        return this.p;
    }

    @Override
    public void setLeftChild(INode<T, V> leftChild) {
        this.left = leftChild;
    }

    @Override
    public INode<T, V> getLeftChild() {
        return this.left;
    }

    @Override
    public void setRightChild(INode<T, V> rightChild) {
        this.right = rightChild;
    }

    @Override
    public INode<T, V> getRightChild() {
        return this.right;
    }

    @Override
    public T getKey() {
        return this.key;
    }

    @Override
    public void setKey(T key) {
        this.key = key;
    }

    @Override
    public V getValue() {
        return this.value;
    }

    @Override
    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public boolean getColor() {
        return this.color;
    }

    @Override
    public void setColor(boolean color) {
        this.color = color;
    }

    @Override
    public boolean isNull() {
        return (this.key == null || this.value == null);
    }
}
