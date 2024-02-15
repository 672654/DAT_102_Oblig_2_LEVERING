package Oppgave_3;

public class MergeSorter {
	
	public static <T extends Comparable<? super T>> void mergeSort(T[] arr) {

		int length = arr.length;
		if (length <= 1) { // base
			return;
		}

		int mid = length / 2;

		T[] leftArr = (T[]) new Comparable[mid];
		T[] rightArr = (T[]) new Comparable[length - mid];

		int i = 0;
		int j = 0;

		for (; i < length; i++) {
			if (i < mid) {
				leftArr[i] = arr[i];
			} else {
				rightArr[j] = arr[i];
				j++;
			}
		}
		mergeSort(leftArr);
		mergeSort(rightArr);
		merge(leftArr, rightArr, arr);
	}

	private static <T extends Comparable<? super T>> void merge(T[] leftArr, T[] rightArr, T[] arr) {

		int leftS = arr.length / 2;
		int rightS = arr.length - leftS;
		int i = 0, l = 0, r = 0;

		while (l < leftS && r < rightS) {
			if (leftArr[l].compareTo(rightArr[r]) < 0) {
				arr[i] = leftArr[l];
				i++;
				l++;
			} else {
				arr[i] = rightArr[r];
				i++;
				r++;
			}
		}
		while (l < leftS) {
			arr[i] = leftArr[l];
			i++;
			l++;
		}
		while (r < rightS) {
			arr[i] = rightArr[r];
			i++;
			r++;
		}
	}
}
