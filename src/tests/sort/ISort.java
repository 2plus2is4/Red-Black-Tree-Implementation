package tests.sort;

import eg.edu.alexu.csd.filestructure.sort.IHeap;

import java.util.ArrayList;

public abstract interface ISort<T extends Comparable<T>>
{
  public abstract IHeap<T> heapSort(ArrayList<T> paramArrayList);
  
  public abstract void sortSlow(ArrayList<T> paramArrayList);
  
  public abstract void sortFast(ArrayList<T> paramArrayList);
}
