package eg.edu.alexu.csd.filestructure.redblacktree;

public class RotatingRedBlackTree {

    private INode nil = new Node();

    private void leftRotate(INode x,INode root) {
        INode y = x.getRightChild();
        x.setRightChild(y.getLeftChild());
        if (y.getLeftChild() != nil) {
            y.getLeftChild().setParent(x);
        }
        y.setParent(x.getParent());
        if (x.getParent() == nil)
            root = y;
        else if (x == x.getParent().getLeftChild())
            x.getParent().setLeftChild(y);
        else
            x.getParent().setRightChild(y);

        y.setLeftChild(x);
        x.setParent(y);
    }

    private void rightRotate(INode x,INode root) {
        INode y = x.getLeftChild();
        x.setLeftChild(y.getRightChild());
        if (y.getRightChild() != nil)
            y.getRightChild().setParent(x);
        y.setParent(x.getParent());
        if (x.getParent() == nil)
            root = y;
        else if (x == x.getParent().getRightChild())
            x.getParent().setRightChild(y);
        else
            x.getParent().setLeftChild(y);

        y.setRightChild(x);
        x.setParent(y);
    }
}
