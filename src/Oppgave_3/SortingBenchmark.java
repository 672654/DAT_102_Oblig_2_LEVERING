package Oppgave_3;

import java.lang.reflect.Array;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Measures the four sorting algorithms in this package. Both the size of the
 * test array and the number of loops can be adjusted as parameters.
 */
public class SortingBenchmark {
	private final static int NUMBER_OF_LOOPS = 10;
	private final static int SIZE_OF_LARGE_ARRAY = 32_000;
	private final static int SIZE_OF_LARGER_ARRAY = 64_000;
	private final static int SIZE_OF_LARGEST_ARRAY = 128_000;

	public static void main(String[] args) {
		Integer[] arrayToBeSorted = generateRandomInts(SIZE_OF_LARGE_ARRAY);

		System.out.println("Selection Sort:");
		System.out.print("N          -  Loops         - Average time (ms)  -  Theoretical time\n");
		benchmarkAlgorithm("Selection sort", BigO.QUADRATIC, SelectionSorter::sort, arrayToBeSorted, NUMBER_OF_LOOPS);
		// Average time (ms): 534 - Theoretical time (ms): 533
		arrayToBeSorted = generateRandomInts(SIZE_OF_LARGER_ARRAY);
		benchmarkAlgorithm("Selection sort", BigO.QUADRATIC, SelectionSorter::sort, arrayToBeSorted, NUMBER_OF_LOOPS);
		// Average time (ms): 2_190 - Theoretical time (ms): 2_189
		arrayToBeSorted = generateRandomInts(SIZE_OF_LARGEST_ARRAY);
		benchmarkAlgorithm("Selection sort", BigO.QUADRATIC, SelectionSorter::sort, arrayToBeSorted, NUMBER_OF_LOOPS);
		// Average time (ms): 10_252 - Theoretical time (ms): 10_251

		System.out.println("\nInsertion Sort:");
		System.out.print("N          -  Loops         - Average time (ms)  -  Theoretical time\n");
		arrayToBeSorted = generateRandomInts(SIZE_OF_LARGE_ARRAY);
		benchmarkAlgorithm("Insertion sort", BigO.QUADRATIC, InsertionSorter::insertionSort, arrayToBeSorted,
				NUMBER_OF_LOOPS);
		// Average time (ms): 177 - Theoretical time (ms): 176
		arrayToBeSorted = generateRandomInts(SIZE_OF_LARGER_ARRAY);
		benchmarkAlgorithm("Insertion sort", BigO.QUADRATIC, InsertionSorter::insertionSort, arrayToBeSorted,
				NUMBER_OF_LOOPS);
		// Average time (ms): 725 - Theoretical time (ms): 724
		arrayToBeSorted = generateRandomInts(SIZE_OF_LARGEST_ARRAY);
		benchmarkAlgorithm("Insertion sort", BigO.QUADRATIC, InsertionSorter::insertionSort, arrayToBeSorted,
				NUMBER_OF_LOOPS);
		// Average time (ms): 3_249 - Theoretical time (ms): 3_249

		System.out.println("\nQuick Sort:");
		System.out.print("N          -  Loops         - Average time (ms)  -  Theoretical time\n");
		arrayToBeSorted = generateRandomInts(SIZE_OF_LARGE_ARRAY);
		measureQuickSort(arrayToBeSorted, NUMBER_OF_LOOPS);
		// Average time (ms): 508 - Theoretical time (ms): 402
		arrayToBeSorted = generateRandomInts(SIZE_OF_LARGER_ARRAY);
		measureQuickSort(arrayToBeSorted, NUMBER_OF_LOOPS);
		// Average time (ms): 6_174 - Theoretical time (ms): 4_279
		arrayToBeSorted = generateRandomInts(SIZE_OF_LARGEST_ARRAY);
		measureQuickSort(arrayToBeSorted, NUMBER_OF_LOOPS);
		// Average time (ms): 17_181 - Theoretical time (ms): 11_908

		System.out.println("\nMerge Sort:");
		System.out.print("N          -  Loops         - Average time (ms)  -  Theoretical time\n");
		arrayToBeSorted = generateRandomInts(SIZE_OF_LARGE_ARRAY);
		benchmarkAlgorithm("Merge sort", BigO.QUASILINEAR, MergeSorter::sort, arrayToBeSorted, NUMBER_OF_LOOPS);
		// Average time (ms): 20 - Theoretical time (ms): 13
		arrayToBeSorted = generateRandomInts(SIZE_OF_LARGER_ARRAY);
		benchmarkAlgorithm("Merge sort", BigO.QUASILINEAR, MergeSorter::sort, arrayToBeSorted, NUMBER_OF_LOOPS);
		// Average time (ms): 36 - Theoretical time (ms): 24
		arrayToBeSorted = generateRandomInts(SIZE_OF_LARGEST_ARRAY);
		benchmarkAlgorithm("Merge sort", BigO.QUASILINEAR, MergeSorter::sort, arrayToBeSorted, NUMBER_OF_LOOPS);
		// Average time (ms): 29 - Theoretical time (ms): 20

		// Trying to quick sort an array where all entries are the same.
		System.out.println("\nQuick Sort an array where all entries are the same.:");
		System.out.print("N          -  Loops         - Average time (ms)  -  Theoretical time\n");
		arrayToBeSorted = generateIdenticalInts(7, SIZE_OF_LARGE_ARRAY);
		measureQuickSort(arrayToBeSorted, 1);
		// Average time (ms): 550 - Theoretical time (ms): 381
	}

	// Used by main() to initialize and reset the test array.
	private static Integer[] generateRandomInts(int size) {
		Integer[] array = new Integer[size];
		Random generator = new Random();
		for (int i = 0; i < array.length; i++) {
			array[i] = generator.nextInt();
		}
		return array;
	}

	// Used by main() to generate a large array where all entries are the same.
	private static Integer[] generateIdenticalInts(int entry, int size) {
		Integer[] array = new Integer[size];
		for (int i = 0; i < array.length; i++) {
			array[i] = entry;
		}
		return array;
	}

	// A helper method containing println() logic, as to avoid duplicate code in
	// main().
	private static <T> void benchmarkAlgorithm(String algorithmName, BigO growthOfAlgorithm,
			Consumer<T[]> sortingAlgorithm, T[] array, int numberOfLoops) {

		measureAlgorithm(sortingAlgorithm, growthOfAlgorithm, array, numberOfLoops);
//      System.out.print("N          -  Loops         - Average time (ms)  -  Theoretical time\n");
	}

	// Loops through a specified sorting algorithm, and prints out the average
	// runtime to the console.
	private static <T> void measureAlgorithm(Consumer<T[]> sortingAlgorithm, BigO growthOfAlgorithm, T[] array,
			int numberOfLoops) {
		long totalDuration = 0;

		for (int i = 0; i < numberOfLoops; i++) {
			Instant start = Instant.now();
			sortingAlgorithm.accept(array);
			Instant stop = Instant.now();
			totalDuration += Duration.between(start, stop).toMillis();
		}

		long averageDuration = totalDuration / numberOfLoops;
		float constant;
		long theoreticalTime;

		switch (growthOfAlgorithm) {
		case QUASILINEAR: // Another word for n log n
			double binaryLogarithm = Math.log(array.length) / Math.log(2);
			constant = (float) (averageDuration / (array.length * binaryLogarithm));
			theoreticalTime = (long) (constant * (array.length * Math.log(array.length)));
			break;
		case QUADRATIC: // Another word for n^2
			constant = (float) (averageDuration / (Math.pow(array.length, 2)));
			theoreticalTime = (long) (constant * (Math.pow(array.length, 2)));
			break;
		default:
			constant = -1;
			theoreticalTime = -1;
			System.out.println("Error! Unsupported growthOfAlgorithm detected!");
		}

		System.out.println(array.length + "      -  " + numberOfLoops + "            - " + averageDuration
				+ "               -  " + theoreticalTime);
	}

	// QuickSorter.sort() has too many parameters for measureAlgorithm(), and
	// requires its own measuring method.
	private static <T extends Comparable<? super T>> void measureQuickSort(T[] array, int numberOfLoops) {
		long totalDuration = 0;

		for (int i = 0; i < numberOfLoops; i++) {
			Instant start = Instant.now();
			QuickSorter.sortIteratively(array, 0, array.length - 1); // Call QuickSort.sort() directly here
			Instant stop = Instant.now();
			totalDuration += Duration.between(start, stop).toMillis();
		}

		long averageDuration = totalDuration / numberOfLoops;

		double binaryLogarithm = Math.log(array.length) / Math.log(2);
		float constant = (float) (averageDuration / (array.length * binaryLogarithm));
		long theoreticalTime = (long) (constant * (array.length * Math.log(array.length)));

		System.out.println(array.length + " 		-  " + numberOfLoops + "		    -	" + averageDuration
				+ "	               -  " + theoreticalTime);

	}
}
