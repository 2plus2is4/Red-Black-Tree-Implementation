package eg.edu.alexu.csd.filestructure.redblacktree;

import javax.management.RuntimeErrorException;

public class RedBlackTree implements IRedBlackTree {

    private INode root = nil;
    private static INode nil = new Node();

    @Override
    public INode getRoot() {
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
    public Object search(Comparable key) {
        if(key==null) throw new RuntimeErrorException(new Error());
        INode temp = root;
        while (temp != nil) {
            if (temp.getKey().compareTo(key)==0)
                return temp.getValue();
            if(temp.getKey().compareTo(key)>0)
                temp = temp.getLeftChild();
            else
                temp = temp.getRightChild();
//            if (temp.getLeftChild() != nil)
//                l = temp.getLeftChild().getKey();
//            if (temp.getRightChild() != nil)
//                r = temp.getRightChild().getKey();
//            if(l==null&&r==null)
//                return null;
//            if (l != null && r != null) {
//                if(l.compareTo(key) > 0)
//                    temp=temp.getLeftChild();
//                else
//                    temp=temp.getRightChild();
//
//            } else if (l != null)
//                temp = temp.getLeftChild();
//            else
//                temp = temp.getRightChild();

        }
        return null;
    }

    @Override
    public boolean contains(Comparable key) {
        if(key==null) throw new RuntimeErrorException(new Error());
        return (this.search(key) != null);

    }

    @Override
    public void insert(Comparable key, Object value){
        if(key==null || value==null) {
            throw new RuntimeErrorException(new Error());
        }
            INode z = new Node();
            z.setKey(key);
            z.setValue(value);
            INode x = this.root;
            INode y = nil;
            while (!x.isNull()) {
                y = x;
                if(z.getKey().compareTo(x.getKey()) == 0) {
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

    void insertFix(INode z) {
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
                        }else {
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
                        }else {
                            z.getParent().setColor(false);
                            if (!z.getParent().isNull() && !z.getParent().getParent().isNull()) {
                                z.getParent().getParent().setColor(true);
                                leftRotate(z.getParent().getParent());
                            }
                        }
                    }
                }else break;
            }else break;
        }
        this.root.setColor(false);
    }

/*
    @Override
    public void insert(Comparable key, Object value) {
        //if the tree is empty
        if (root.isNull()) {
            root = new Node();
            root.setParent(new Node());
            root.setRightChild(new Node());
            root.setLeftChild(new Node());
            root.setKey(key);
            root.setValue(value);
            root.setColor(false);
            return;
        }
        //insert the node
        INode node = BSTInsertion(key, value, root);

        //some Nodes
        INode parent = node.getParent();
        if (parent.getParent() == null)
            parent.setParent(new Node());
        INode grandpa = parent.getParent();
        INode uncle = (grandpa.getLeftChild() == parent) ?
                grandpa.getRightChild() : grandpa.getLeftChild();

        //Red Black Tree Coloring Class
        ColoringRedBlackTree coloring = new ColoringRedBlackTree();


        //if the parent is red
        if (parent.getColor()) {
            //red uncle
            if (uncle.getColor()) {
                //recurrence
                coloring.RedUncle(node);
            } else {
                //black uncle
                if (grandpa.getLeftChild() == parent) {
                    if (parent.getLeftChild() == node)
                        //left left
                        coloring.LeftLeft(node, parent, grandpa);
                    else
                        //left right
                        coloring.LeftRight(node, parent, grandpa);
                } else {

                    if (parent.getLeftChild() == node) {
                        //right left
                        coloring.RightLeft(node, parent, grandpa);
                    } else {
                        //right right
                        coloring.RightRight(node, parent, grandpa);
                    }
                }
            }
        }
    }

    private INode BSTInsertion(Comparable key, Object value, INode node) {
        if (node.isNull()) {
            INode newNode = new Node();
            newNode.setKey(key);
            newNode.setValue(value);
            return newNode;
        }
        if (key.compareTo(node.getKey()) < 0) {
            node.setLeftChild(BSTInsertion(key, value, node.getLeftChild()));
        } else {
            node.setRightChild(BSTInsertion(key, value, node.getRightChild()));
        }
        return node;
    }
*/
    @Override
    public boolean delete(Comparable key) {
        INode z = (INode) search(key);
        if (z.isNull())
            return false;
        INode y = (INode) search(key);
        boolean originalColor = y.getColor();

        INode x;
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

    private void fix(INode x) {
        while (x != getRoot() && !x.getColor()) {
            if (x.getParent().getLeftChild() == x) {
                INode w = x.getParent().getRightChild();
                if (w.getColor()) {
                    w.setColor(false);
                    x.getParent().setColor(true);
                    leftRotate(x);
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
                INode w = x.getParent().getLeftChild();
                if (w.getColor()) {
                    w.setColor(false);
                    x.getParent().setColor(true);
                    rightRotate(x);
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
    }

    private void leftRotate(INode x) {
        INode y = x.getRightChild();
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

    private void rightRotate(INode x) {
        INode y = x.getLeftChild();
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

    private INode min(INode n) {
        INode current = n;
        while (!current.getLeftChild().isNull())
            current = current.getLeftChild();
        return current;
    }

    private void transplant(INode n, INode m) {
        if (n.getParent().isNull())
            root = m;
        else if (n == n.getParent().getLeftChild())
            n.getParent().setLeftChild(m);
        else
            n.getParent().setRightChild(m);
        m.setParent(n.getParent());
    }

}
