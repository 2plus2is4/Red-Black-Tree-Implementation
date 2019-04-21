package eg.edu.alexu.csd.filestructure.redblacktree;

public class Node implements INode {
    private INode p;
    private INode left;
    private INode right;
    private Comparable key;
    private Object value;
    private boolean color;
    @Override
    public void setParent(INode parent) {
        this.p=parent;
    }

    @Override
    public INode getParent() {
        return this.p;
    }

    @Override
    public void setLeftChild(INode leftChild) {
        this.left=leftChild;
    }

    @Override
    public INode getLeftChild() {
        return this.left;
    }

    @Override
    public void setRightChild(INode rightChild) {
        this.right=rightChild;
    }

    @Override
    public INode getRightChild() {
        return this.right;
    }

    @Override
    public Comparable getKey() {
        return this.key;
    }

    @Override
    public void setKey(Comparable key) {
        this.key=key;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public void setValue(Object value) {
        this.value=value;
    }

    @Override
    public boolean getColor() {
        return this.color;
    }

    @Override
    public void setColor(boolean color) {
        this.color=color;
    }

    @Override
    public boolean isNull() {
        if(this.value==null&&left==null&&right==null)
            return true;
        return false;
    }
}
