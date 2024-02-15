package Oppgave_3;

public class SelectionSort {

	
	public static <T extends Comparable<? super T>> void selectionSort(T[] arr) {
		// step 1: start til venstre i tab. opprett smallest som index pos med minste
		// verdi.
		// step 2: gå igjennom tabellen og let etter mindre verdi. oppdater smallest
		// underveis.
		// step 3: erstatt minste med verdien i index step 1.

		for (int i = 0; i < arr.length; i++) {
			int smallest = i;

			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j].compareTo(arr[smallest]) < 1) {
					smallest = j;
				}
			}
			T temp = arr[i];
			arr[i] = arr[smallest];
			arr[smallest] = temp;
		}

	}
}
