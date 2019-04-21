package eg.edu.alexu.csd.filestructure.redblacktree;

public class RedBlackTree implements IRedBlackTree {
    private INode root;
    private INode nil=new Node();

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
        root=null;
    }

    @Override
    public Object search(Comparable key) {
        INode temp=root;
        Comparable l = null;
        Comparable r = null;
        while(temp!=null){
            if(temp.getKey()==key)
                return temp.getValue();
            if(temp.getLeftChild()!=null)
                l=temp.getLeftChild().getKey();
            if(temp.getRightChild()!=null)
                r=temp.getRightChild().getKey();
            if(l!=null&&r!=null){
                if(l.compareTo(r) > 0)
                    temp=temp.getLeftChild();
                else
                    temp=temp.getRightChild();
            }else if(l!=null&&r==null)
                temp=temp.getLeftChild();
            else
                temp=temp.getRightChild();

        }
        return null;
    }

    @Override
    public boolean contains(Comparable key) {
        return (this.search(key)!=null);

    }

    @Override
    public void insert(Comparable key, Object value) {
        if(root==null){
            root = new INode();
            root.setKey(key);
            root.setValue(value);
            return;
        } 
        BSTInsertion(key, value, node);
    }

    private void BSTInsertion(Comparable key, Object value, INode node){
        
    }

    @Override
    public boolean delete(Comparable key) {
        return false;
    }

}
