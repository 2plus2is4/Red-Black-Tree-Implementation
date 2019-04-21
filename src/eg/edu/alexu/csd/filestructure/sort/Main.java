package eg.edu.alexu.csd.filestructure.sort;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(4, 10, 3, 5, 1));
        Sort sort = new Sort();
        sort.heapSort(arrayList);
        for (Integer integer : arrayList) {
            System.out.print (integer+" ");
        }
    }
}
