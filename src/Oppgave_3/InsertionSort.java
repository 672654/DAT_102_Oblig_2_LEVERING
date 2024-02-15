package Oppgave_3;

public class InsertionSort {

	static <T extends Comparable<? super T>> void insertionSort(T[] arr) {
		// 1: første element er sortert. Begynn i index 0+1. opprett variabel med index
		// smallest.
		// 2: gå igjennom tabellen og sjekk om elementene til venstre er mindre.
		// 3: er de mindre må de byttes.

		for (int i = 1; i < arr.length; i++) {
			int smallest = i;

			while (smallest > 0 && arr[smallest].compareTo(arr[smallest - 1]) < 1) {
				T temp = arr[smallest];
				arr[smallest] = arr[smallest - 1];
				arr[smallest - 1] = temp;

				smallest = smallest - 1;
			}
		}
	}
}
