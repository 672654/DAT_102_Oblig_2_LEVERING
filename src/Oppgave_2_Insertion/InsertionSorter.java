package Oppgave_2_Insertion;

/**
 * Sorts an array in ascending order by insertion.
 * 
 * @author David Svardal
 */
public class InsertionSorter {
	/**
	 * Uses a few tricks to speed up the standard insertion algorithm! It sets the
	 * smallest entry first in the array, and then sorts two and two entries at a
	 * time.
	 * 
	 * @param array A homogenous array of any type.
	 * @return An array sorted in ascending order.
	 */
	public static <T extends Comparable<? super T>> T[] setMinIndexAndSortTwoByTwo(T[] array) {
		setSmallestEntryFirst(array);
		return sortTwoByTwo(array);
	}

	/**
	 * A slightly optimized version of standard insertion sort, that sorts two
	 * entries at a time.
	 * 
	 * @param array A homogenous array.
	 * @return An array sorted in ascending order.
	 */
	public static <T extends Comparable<? super T>> T[] sortTwoByTwo(T[] array) {
		for (int i = 1; i < array.length - 1; i += 2) {
			T firstEntry = array[i];
			T secondEntry = array[i + 1];

			if (firstEntry.compareTo(secondEntry) > 0) {
				swapEntries(array, i, i + 1);
			}

			// Insert firstEntry and secondEntry into their correct positions
			int j = i - 1;
			while (j >= 0 && secondEntry.compareTo(array[j]) < 0) {
				array[j + 2] = array[j];
				j--;
			}
			array[j + 2] = secondEntry;

			while (j >= 0 && firstEntry.compareTo(array[j]) < 0) {
				array[j + 1] = array[j];
				j--;
			}
			array[j + 1] = firstEntry;
		}

		// Extra steps in case the last entry hasn't been sorted.
		if (array[array.length - 2].compareTo(array[array.length - 1]) > 0) {
			T lastEntry = array[array.length - 1];
			int j = array.length - 2;
			while (j >= 0 && lastEntry.compareTo(array[j]) < 0) {
				array[j + 1] = array[j];
				j--;
			}
			array[j + 1] = lastEntry;
		}

		return array;
	}

	/**
	 * A slightly optimized version of standard insertion sort. The smallest entry
	 * is swapped with the first index before sorting.
	 * 
	 * @param array A homogenous array.
	 * @return An array sorted in ascending order.
	 */
	public static <T extends Comparable<? super T>> T[] setMinIndexAndSort(T[] array) {
		setSmallestEntryFirst(array);
		return standardSort(array);
	}

	// Standard insertion sort, without any optimization.
	private static <T extends Comparable<? super T>> T[] standardSort(T[] array) {
		for (int i = 1; i < array.length; i++) {
			T currentEntry = array[i];
			int j = i - 1;

			while (j >= 0 && currentEntry.compareTo(array[j]) < 0) {
				array[j + 1] = array[j];
				j--;
			}
			array[j + 1] = currentEntry;
		}

		return array;
	}

	// Locates the smallest entry in an array and swaps it with the first index.
	private static <T extends Comparable<? super T>> void setSmallestEntryFirst(T[] array) {
		// Assertion: array is not empty
		int indexOfSmallestEntry = 0;
		for (int i = 1; i < array.length; i++) {
			if (array[i].compareTo(array[indexOfSmallestEntry]) < 0) {
				indexOfSmallestEntry = i;
			}
		}
		swapEntries(array, 0, indexOfSmallestEntry);
	}

	// Swaps two entries in an array with each other.
	private static <T> void swapEntries(T[] array, int firstIndex, int secondIndex) {
		T temp = array[firstIndex];
		array[firstIndex] = array[secondIndex];
		array[secondIndex] = temp;
	}
}
