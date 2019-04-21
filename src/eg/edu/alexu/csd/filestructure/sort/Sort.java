package eg.edu.alexu.csd.filestructure.sort;


import java.util.ArrayList;

public class Sort<T extends Comparable<T>> implements ISort {

    @Override
    public IHeap heapSort(ArrayList unordered) {
        IHeap heap = new Heap();
        ArrayList<Comparable> ans = new ArrayList<>();
        if (unordered != null) {
            int n = unordered.size();
            heap.build(unordered);
            ((Heap) heap).sort();
        }
        return heap;
    }

    @Override
    public void sortSlow(ArrayList unordered) {
        //BubbleSort
        if (unordered != null) {
            int n = unordered.size();
            for (int i = 0; i < n - 1; i++) {
                boolean flag = false;
                for (int j = 0; j < n - i - 1; j++)
                    if (((Comparable) unordered.get(j)).compareTo(unordered.get(j + 1)) > 0) {
                        flag = true;
                        // swap arr[j+1] and arr[i]
                        Object temp = unordered.get(j);
                        unordered.set(j, unordered.get(j + 1));
                        unordered.set(j + 1, temp);
                    }
                if (!flag) {
                    return;
                }
            }
        }
    }

    @Override
    public void sortFast(ArrayList unordered) {
        if (unordered != null)
            sort(unordered, 0, unordered.size() - 1);
    }

    private void sort(ArrayList unordered, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            sort(unordered, l, m);
            sort(unordered, m + 1, r);
            merge(unordered, l, m, r);
        }
    }

    private void merge(ArrayList unordered, int l, int m, int r) {
        //sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;
        ArrayList L = new ArrayList();
        ArrayList R = new ArrayList();
        //Copy data to temp arrays
        for (int i = 0; i < n1; ++i)
            L.add(i, unordered.get(l + i));
        for (int j = 0; j < n2; ++j)
            R.add(j, unordered.get(m + 1 + j));
        /* Merge the temp arrays */
        int i = 0, j = 0;
        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2) {
            if (((Comparable) L.get(i)).compareTo(R.get(j)) <= 0) {
                unordered.set(k, L.get(i));
                i++;
            } else {
                unordered.set(k, R.get(j));
                j++;
            }
            k++;
        }
        while (i < n1) {
            unordered.set(k, L.get(i));
            i++;
            k++;
        }
        while (j < n2) {
            unordered.set(k, R.get(j));
            j++;
            k++;
        }
    }


}