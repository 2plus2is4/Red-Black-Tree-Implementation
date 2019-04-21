package eg.edu.alexu.csd.filestructure.redblacktree;

public class RedBlackTree implements IRedBlackTree {
    private INode root;
    @Override
    public INode getRoot() {
        return this.root;
    }

    @Override
    public boolean isEmpty() {
        if(root.isNull())
            return true;
        return false;
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
        if(this.search(key)!=null){
            return true;
        }
        return false;
    }

    @Override
    public void insert(Comparable key, Object value) {

    }

    @Override
    public boolean delete(Comparable key) {
        return false;
    }

}
