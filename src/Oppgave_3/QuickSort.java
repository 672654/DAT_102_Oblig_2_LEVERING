package Oppgave_3;

public class QuickSort {

	
	static <T extends Comparable<? super T>> void quickSort(T[] arr, int start, int end) {

		if (end <= start) { // base.
			return;
		}
		int pivot = finnPivot(arr, start, end);
		quickSort(arr, start, pivot - 1);
		quickSort(arr, pivot + 1, end);

	}

	private static <T extends Comparable<? super T>> int finnPivot(T[] arr, int start, int end) {

		T pivot = arr[end];
		int i = start - 1;

		for (int j = start; j <= end - 1; j++) {
			if (arr[j].compareTo(pivot) < 0) {
				i++;
				T temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		i++;
		T temp = arr[i];
		arr[i] = arr[end];
		arr[end] = temp;

		return i;
	}
}
