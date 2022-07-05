package PerfectPolicy;

import java.util.ArrayList;

public class Sorts {



    public static void InsertionSort(ArrayList<Object[]> arr){

        int j;
        Object[] key;
        int i;
        for(j = 1; j < arr.size(); j++)
        {
            key = arr.get(j);
            for(i = j - 1; (i >= 0) && (arr.get(i)[1].toString().compareToIgnoreCase(key[1].toString()) > 0); i--) // Ignores case when sorting
            {
                arr.set(i + 1, arr.get(i));

            }
            arr.set(i+1, key);
        }
    }


    public static void SelectionSort(ArrayList<Object[]> arr){
        int i, j, first;
        Object[] temp;
        for ( i = arr.size() - 1; i > 0; i --) {
            first = 0; //initialize to subscript of first element
            for (j = 1; j <= i; j++) //locate smallest element between positions 1 and i.
            {
                if (arr.get(j)[2].toString().compareToIgnoreCase(arr.get(first)[2].toString()) < 0) // Ignores case when sorting
                    first = j;
            }
            temp = arr.get(first); //swap smallest found with element in position i.
            arr.set(first, arr.get(i));
            arr.set(i, temp);
        }

    }



    public static void bubbleSort(ArrayList<Object[]> arr)
    {

        for(int j=0; j<arr.size(); j++)
        {
            for(int i=j+1; i<arr.size(); i++)
            {
                if((arr.get(i)[0]).toString().compareToIgnoreCase(arr.get(j)[0].toString())>0) // Ignores case when sorting
                {
                    Object[] temp = arr.get(j);
                    arr.set(j, arr.get(i));
                    arr.set(i, temp);
                }
            }

        }
    }
}
