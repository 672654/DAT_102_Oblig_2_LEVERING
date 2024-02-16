package Oppgave_3;

import java.util.Stack;

public class QuickSorter
{
    public static <T extends Comparable<? super T>> void sortRecursively(T[] arr, int start, int end)
    {
        if (start < end)
        {
            int pivotIndex = partition(arr, start, end);
            sortRecursively(arr, start, pivotIndex - 1);
            sortRecursively(arr, pivotIndex + 1, end);
        }
    }

    public static <T extends Comparable<? super T>> void sortIteratively(T[] arr, int start, int end)
    {
        if (start >= end) {return;}

        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        stack.push(end);

        while (!stack.isEmpty())
        {
            end = stack.pop();
            start = stack.pop();

            int pivotIndex = partition(arr, start, end);

            if (pivotIndex - 1 > start)
            {
                stack.push(start);
                stack.push(pivotIndex - 1);
            }

            if (pivotIndex + 1 < end)
            {
                stack.push(pivotIndex + 1);
                stack.push(end);
            }
        }
    }

    private static <T extends Comparable<? super T>> int partition(T[] arr, int start, int end)
    {
        T pivot = arr[start]; // Choosing the first element as the pivot
        int i = start;
        int j = end;

        while (i < j)
        {
            while (i < j && arr[j].compareTo(pivot) >= 0) {j--;}
            if (i < j)
            {
                arr[i] = arr[j];
                i++;
            }
            while (i < j && arr[i].compareTo(pivot) <= 0) {i++;}
            if (i < j)
            {
                arr[j] = arr[i];
                j--;
            }
        }
        arr[i] = pivot;
        return i;
    }

    private static <T> void swap(T[] arr, int i, int j)
    {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}