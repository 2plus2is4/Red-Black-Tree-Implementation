package eg.edu.alexu.csd.filestructure.redblacktree;

public class ColoringRedBlackTree {

    public void LeftLeft(INode x, INode p, INode g) {
            g.setLeftChild(p.getRightChild());
            p.setRightChild(g);
            g.setColor(true);
            p.setColor(false);
            x.setColor(true);
    }

    public void LeftRight(INode x, INode p, INode g) {
        p.setRightChild(x.getLeftChild());
        x.setLeftChild(p);
        LeftLeft(p,x,g);
    }

    public void RightLeft(INode x, INode p, INode g) {
        p.setLeftChild(x.getRightChild());
        x.setRightChild(p);
        RightRight(p,x,g);
    }

    public void RightRight(INode x, INode p, INode g) {
        g.setRightChild(p.getLeftChild());
        p.setLeftChild(g);
        g.setColor(true);
        p.setColor(false);
        x.setColor(true);
    }

    public void RedUncle(INode x) {
        INode p = x.getParent();
        if (p == null) return;
        INode g = p.getParent();
        if (g == null) return;
        INode u = (g.getLeftChild() == p) ?
                g.getRightChild() : g.getLeftChild();
        if(u==null) return;
        if (u.getColor()) {
            p.setColor(false);
            u.setColor(false);
            g.setColor(true);
            x.setColor(true);
            RedUncle(g);
        }

    }
}
