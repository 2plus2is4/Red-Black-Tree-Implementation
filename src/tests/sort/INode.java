package tests.sort;

public abstract interface INode<T extends Comparable<T>>
{
  public abstract INode<T> getLeftChild();
  
  public abstract INode<T> getRightChild();
  
  public abstract INode<T> getParent();
  
  public abstract T getValue();
  
  public abstract void setValue(T paramT);
}
