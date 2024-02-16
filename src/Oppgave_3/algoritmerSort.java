package Oppgave_3;

import java.lang.reflect.Array;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;

public class algoritmerSort {

	public static void main(String[] args) {

		Integer[] arrayToBeSorted = generateRandomInts(32_00);
		System.out.println("SelectionSort:");
		System.out.print("N  		-  Antall Maalinger -	Gj.snitt tid  -  Teoretisk tid:\n");
		measureAlgorithm(SelectionSort::selectionSort, arrayToBeSorted, 10);
		arrayToBeSorted = generateRandomInts(64_00);
		measureAlgorithm(SelectionSort::selectionSort, arrayToBeSorted, 10);
		arrayToBeSorted = generateRandomInts(128_00);
		measureAlgorithm(SelectionSort::selectionSort, arrayToBeSorted, 10);
		System.out.println();
		
		
		System.out.println("InsertionSort:");
		System.out.print("N  		-  Antall Maalinger -	Gj.snitt tid  -  Teoretisk tid:\n");
		measureAlgorithm(InsertionSort::insertionSort, arrayToBeSorted, 10);
		arrayToBeSorted = generateRandomInts(64_00);
		measureAlgorithm(InsertionSort::insertionSort, arrayToBeSorted, 10);
		arrayToBeSorted = generateRandomInts(128_00);
		measureAlgorithm(InsertionSort::insertionSort, arrayToBeSorted, 10);
		System.out.println();
		
		System.out.println("QuickSort:");
		System.out.print("N  		-  Antall Maalinger -	Gj.snitt tid  -  Teoretisk tid:\n");
		measureAlgorithm(QuickSort::quickSort, arrayToBeSorted, 10);
		arrayToBeSorted = generateRandomInts(64_00);
		measureAlgorithm(InsertionSort::insertionSort, arrayToBeSorted, 10);
		arrayToBeSorted = generateRandomInts(128_00);
		measureAlgorithm(InsertionSort::insertionSort, arrayToBeSorted, 10);
		System.out.println();
		
		
		
		
		System.out.println("Mergesort:");
		System.out.print("N  		-  Antall Maalinger -	Gj.snitt tid  -  Teoretisk tid:\n");
		measureAlgorithm(MergeSorter::mergeSort, arrayToBeSorted, 10);
		arrayToBeSorted = generateRandomInts(64_00);
		measureAlgorithm(MergeSorter::mergeSort, arrayToBeSorted, 10);
		arrayToBeSorted = generateRandomInts(128_00);
		measureAlgorithm(MergeSorter::mergeSort, arrayToBeSorted, 10);
		
//		for (int i = 0; i < tab.length; i++) {
//			tab[i] = tilfeldig.nextInt(0, 10);
//		}

//		measureAlgorithm(insertionSort, tab, 10);

//		Random tilfeldig = new Random();
//		int n = 32_000; // antall elementer i tabell.
//		System.out.println("Insertion Sort: O(n^2)");
//		System.out.print("N  		-  Antall Maalinger -	Gj.snitt tid  -  Teoretisk tid:\n");
//
//		for (int j = 0; j < 3; j++) {
//
//			int antallMaalinger = 10;
//			long totalTid = 0;
//			long maltTidGj = 0;
//			float c = 0;
//			long teoretiskTid;
//			Integer[] tab = new Integer[n];
//
//			for (int i = 0; i < tab.length; i++) {
//				tab[i] = tilfeldig.nextInt(0, 10);
//			}
//
//			for (int i = 0; i <= antallMaalinger; i++) {
//				long startTime = System.nanoTime();
//				insertionSort(tab); // O(n^2)
//				// selectionSort(tab);
//				// quickSort(tab, 0, tab.length - 1);
//				// mergeSort(tab);
//				long endTime = System.nanoTime();
//				long duration = ((endTime - startTime) / 1_000_000);// 1_000_000); // 1_000_000_000 for sekunder.
//				totalTid = totalTid + duration;
//			}
//			maltTidGj = totalTid / antallMaalinger;
//			c = (float) (maltTidGj / (Math.pow(n, 2)));
//			teoretiskTid = (long) (c * (Math.pow(n, 2)));
//
//			System.out.print(
//					n + " 		-  " + antallMaalinger + "		    -	" + maltTidGj + "	      -	 " + teoretiskTid);
//			System.out.println();
//			n *= 2;
//		}
//		System.out.println();
//		for (Integer i : tab) {
//			System.out.print(i + "  ");
//		}

	}

	
	
	private static Integer[] generateRandomInts(int size) {
		Integer[] array = new Integer[size];
		Random generator = new Random();
		for (int i = 0; i < array.length; i++) {
			array[i] = generator.nextInt();
		}
		return array;
	}

	private static <T> void measureAlgorithm(Consumer<T[]> sortingAlgorithm, T[] array, int numberOfLoops) {
		long totalDuration = 0;

		for (int i = 0; i < numberOfLoops; i++) {
			Instant start = Instant.now();
			sortingAlgorithm.accept(array);
			Instant stop = Instant.now();
			totalDuration += Duration.between(start, stop).toMillis();
		}

		long averageDuration = totalDuration / numberOfLoops;
		
		float c = (float) (averageDuration / (Math.pow(array.length, 2)));
		long teoretiskTid = (long) (c * (Math.pow(array.length, 2)));
		
		System.out.println(
					array.length + " 		-  " + numberOfLoops + "		    -	" + averageDuration + "	      -	 " + teoretiskTid);
		
	}
	
	
	
	private static <T> void measureQuickSort(Function<T[], Integer, Integer> sortingAlgorithm, T[] array, int numberOfLoops) {
		long totalDuration = 0;

		for (int i = 0; i < numberOfLoops; i++) {
			Instant start = Instant.now();
			sortingAlgorithm.accept(array);
			Instant stop = Instant.now();
			totalDuration += Duration.between(start, stop).toMillis();
		}

		long averageDuration = totalDuration / numberOfLoops;
		
		float c = (float) (averageDuration / (Math.pow(array.length, 2)));
		long teoretiskTid = (long) (c * (Math.pow(array.length, 2)));
		
		//System.out.print("N  		-  Antall Maalinger -	Gj.snitt tid  -  Teoretisk tid:\n");
		System.out.println(
					array.length + " 		-  " + numberOfLoops + "		    -	" + averageDuration + "	      -	 " + teoretiskTid);
		
	}
}
