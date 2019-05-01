package eg.edu.alexu.csd.filestructure.redblacktree;

import javax.management.RuntimeErrorException;

public class RedBlackTree<T extends Comparable<T>, V> implements IRedBlackTree<T,V> {

    private INode<T,V> nil = new Node<>();
    private INode<T,V> root = nil;

    @Override
    public INode<T, V> getRoot() {
        return this.root;
    }

    @Override
    public boolean isEmpty() {
        return (root.isNull());
    }

    @Override
    public void clear() {
        root = nil;
    }

    @Override
    public V search(T key) {
        if (key == null) throw new RuntimeErrorException(new Error());
        INode<T,V> temp = root;
        while (temp != nil) {
            if (temp.getKey().compareTo(key) == 0)
                return temp.getValue();
            if (temp.getKey().compareTo(key) > 0)
                temp = temp.getLeftChild();
            else
                temp = temp.getRightChild();
        }
        return null;
    }

    @Override
    public boolean contains(T key) {
        if (key == null) throw new RuntimeErrorException(new Error());
        return (this.search(key) != null);
    }

    @Override
    public void insert(T key, V value) throws RuntimeErrorException {
        if (key == null || value == null) {
            throw new RuntimeErrorException(new Error());
        }
        INode<T,V> z = new Node<>();
        z.setKey(key);
        z.setValue(value);
        INode<T,V> x = this.root;
        INode<T,V> y = nil;
        while (!x.isNull()) {
            y = x;
            if (z.getKey().compareTo(x.getKey()) == 0) {
                x.setValue(value);
                return;
            }
            if (z.getKey().compareTo(x.getKey()) < 0)
                x = x.getLeftChild();
            else x = x.getRightChild();
        }
        z.setParent(y);
        if (y.isNull())
            this.root = z;
        else if (z.getKey().compareTo(y.getKey()) < 0)
            y.setLeftChild(z);
        else y.setRightChild(z);
        z.setLeftChild(nil);
        z.setRightChild(nil);
        z.setColor(true);
        insertFix(z);
    }

    private void insertFix(INode<T,V> z) {
        while (z.getParent().getColor()) {
            if (!z.getParent().isNull()) {
                if (!z.getParent().getParent().isNull()) {
                    if (z.getParent() == z.getParent().getParent().getLeftChild()) {
                        INode y = z.getParent().getParent().getRightChild();
                        if (y.getColor()) {
                            z.getParent().setColor(false);
                            y.setColor(false);
                            z.getParent().getParent().setColor(true);
                            z = z.getParent().getParent();
                        } else if (z == z.getParent().getRightChild()) {
                            z = z.getParent();
                            leftRotate(z);
                        } else {
                            z.getParent().setColor(false);
                            if (!z.getParent().isNull() && !z.getParent().getParent().isNull()) {
                                z.getParent().getParent().setColor(true);
                                rightRotate(z.getParent().getParent());
                            }
                        }
                    } else {
                        INode y = z.getParent().getParent().getLeftChild();
                        if (y.getColor()) {
                            z.getParent().setColor(false);
                            y.setColor(false);
                            z.getParent().getParent().setColor(true);
                            z = z.getParent().getParent();
                        } else if (z == z.getParent().getLeftChild()) {
                            z = z.getParent();
                            rightRotate(z);
                        } else {
                            z.getParent().setColor(false);
                            if (!z.getParent().isNull() && !z.getParent().getParent().isNull()) {
                                z.getParent().getParent().setColor(true);
                                leftRotate(z.getParent().getParent());
                            }
                        }
                    }
                } else break;
            } else break;
        }
        this.root.setColor(false);
    }

    @Override
    public boolean delete(T key) {
        if(key == null) throw new RuntimeErrorException(new Error());
        INode<T,V> z= getNode(key);
        if (z.isNull())
            return false;
        INode<T,V> y = getNode(key);
        boolean originalColor = y.getColor();

        INode<T,V> x;
        if (z.getLeftChild().isNull()) {
            x = z.getRightChild();
            transplant(z, z.getRightChild());
        } else if (z.getRightChild().isNull()) {
            x = z.getLeftChild();
            transplant(z, z.getLeftChild());
        } else {
            y = min(z.getRightChild());
            originalColor = y.getColor();
            x = y.getRightChild();
            if (y.getParent() == z)
                x.setParent(y);
            else {
                transplant(y, y.getRightChild());
                y.setRightChild(z.getRightChild());
                y.getRightChild().setParent(y);
            }
            transplant(z, y);
            y.setLeftChild(z.getLeftChild());
            y.getLeftChild().setParent(y);
            y.setColor(z.getColor());
        }
        if (!originalColor) {
            fix(x);
        }

        return true;

    }

    private void fix(INode<T,V> x) {
        while (x != getRoot() && !x.getColor()) {
            if (x.getParent().getLeftChild() == x) {
                INode<T,V> w = x.getParent().getRightChild();
                if (w.getColor()) {
                    w.setColor(false);
                    x.getParent().setColor(true);
                    leftRotate(x.getParent());
                    w = x.getParent().getRightChild();
                }
                if (!w.getLeftChild().getColor() && !w.getRightChild().getColor()) {
                    w.setColor(true);
                    x = x.getParent();
                } else {
                    if (!w.getRightChild().getColor()) {
                        w.getLeftChild().setColor(false);
                        w.setColor(true);
                        rightRotate(w);
                        w = x.getParent().getRightChild();
                    }
                    // Case 4, w = black, w.right = red
                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor(false);
                    w.getRightChild().setColor(false);
                    leftRotate(x.getParent());
                    x = root;
                }
            } else {
                INode<T,V> w = x.getParent().getLeftChild();
                if (w.getColor()) {
                    w.setColor(false);
                    x.getParent().setColor(true);
                    rightRotate(x.getParent());
                    w = x.getParent().getLeftChild();
                }
                if (!w.getRightChild().getColor() && !w.getLeftChild().getColor()) {
                    w.setColor(true);
                    x = x.getParent();
                } else {
                    if (!w.getLeftChild().getColor()) {
                        w.getRightChild().setColor(false);
                        w.setColor(true);
                        leftRotate(w);
                        w = x.getParent().getLeftChild();
                    }
                    // Case 4, w = black, w.right = red
                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor(false);
                    w.getLeftChild().setColor(false);
                    rightRotate(x.getParent());
                    x = root;
                    //anothorone
                }
            }
        }
        x.setColor(false);
    }

    private void leftRotate(INode<T,V> x) {
        INode<T,V> y = x.getRightChild();
        x.setRightChild(y.getLeftChild());
        if (!y.getLeftChild().isNull()) {
            y.getLeftChild().setParent(x);
        }
        y.setParent(x.getParent());
        if (x.getParent().isNull())
            root = y;
        else if (x == x.getParent().getLeftChild())
            x.getParent().setLeftChild(y);
        else
            x.getParent().setRightChild(y);

        y.setLeftChild(x);
        x.setParent(y);
    }

    private void rightRotate(INode<T,V> x) {
        INode<T,V> y = x.getLeftChild();
        x.setLeftChild(y.getRightChild());
        if (!y.getRightChild().isNull())
            y.getRightChild().setParent(x);
        y.setParent(x.getParent());
        if (x.getParent().isNull())
            root = y;
        else if (x == x.getParent().getRightChild())
            x.getParent().setRightChild(y);
        else
            x.getParent().setLeftChild(y);

        y.setRightChild(x);
        x.setParent(y);
    }

    private INode<T,V> min(INode<T,V> n) {
        INode<T,V> current = n;
        while (!current.getLeftChild().isNull())
            current = current.getLeftChild();
        return current;
    }

    private void transplant(INode<T,V> n, INode<T,V> m) {
        if (n.getParent().isNull())
            root = m;
        else if (n == n.getParent().getLeftChild())
            n.getParent().setLeftChild(m);
        else
            n.getParent().setRightChild(m);
        m.setParent(n.getParent());
    }

    private INode<T,V> getNode(T key){
        INode<T,V> y = root;
        if (key==null)
            return null;
        while(key.compareTo(y.getKey())!=0){
            if(key.compareTo(y.getKey())>0)
                y=y.getRightChild();
            else
                y=y.getLeftChild();
        }
        return y;
    }
}
