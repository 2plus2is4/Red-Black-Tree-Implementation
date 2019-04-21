package eg.edu.alexu.csd.filestructure.redblacktree;

public class RedBlackTree implements IRedBlackTree {
    private INode root;
    private INode nil = new Node();

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
        root = null;
    }

    @Override
    public Object search(Comparable key) {
        INode temp = root;
        Comparable l = null;
        Comparable r = null;
        while (temp != null) {
            if (temp.getKey() == key)
                return temp.getValue();
            if (temp.getLeftChild() != null)
                l = temp.getLeftChild().getKey();
            if (temp.getRightChild() != null)
                r = temp.getRightChild().getKey();
            if (l != null && r != null) {
                if (l.compareTo(r) > 0)
                    temp = temp.getLeftChild();
                else
                    temp = temp.getRightChild();
            } else if (l != null && r == null)
                temp = temp.getLeftChild();
            else
                temp = temp.getRightChild();

        }
        return null;
    }

    @Override
    public boolean contains(Comparable key) {
        return (this.search(key) != null);

    }

    @Override
    public void insert(Comparable key, Object value) {
        //if the tree is empty
        if (root == null) {
            root = new Node();
            root.setKey(key);
            root.setValue(value);
            root.setColor(false);
            return;
        }
        //insert the node
        INode node = BSTInsertion(key, value, root);

        //some Nodes
        INode parent = node.getParent();
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
                        coloring.RightLeft(node,parent,grandpa);
                    } else {
                        //right right
                        coloring.RightRight(node,parent,grandpa);
                    }
                }
            }
        }
    }

    private INode BSTInsertion(Comparable key, Object value, INode node) {
        if (node == null) {
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

    @Override
    public boolean delete(Comparable key) {
        return false;
    }

}
