package Oppgave_3;

import java.lang.reflect.Array;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;


/**
 * Measures the four sorting algorithms in this package.
 * Both the size of the test array and the number of loops can be adjusted as parameters.
 */
public class SortingBenchmark
{
    private final static int NUMBER_OF_LOOPS = 10;
    private final static int SIZE_OF_LARGE_ARRAY = 32_00;
    private final static int SIZE_OF_LARGER_ARRAY = 64_00;
    private final static int SIZE_OF_LARGEST_ARRAY = 128_00;

    public static void main(String[] args)
    {
        Integer[] arrayToBeSorted = generateRandomInts(SIZE_OF_LARGE_ARRAY);
        System.out.println("Selection Sort:");
        System.out.print("N          -  Loops         - Average time (ms)  -  Theoretical time\n");
        benchmarkAlgorithm("", SelectionSorter::sort, arrayToBeSorted, NUMBER_OF_LOOPS);
        arrayToBeSorted = generateRandomInts(SIZE_OF_LARGER_ARRAY);
        benchmarkAlgorithm("", SelectionSorter::sort, arrayToBeSorted, NUMBER_OF_LOOPS);
        arrayToBeSorted = generateRandomInts(SIZE_OF_LARGEST_ARRAY);
        benchmarkAlgorithm("", SelectionSorter::sort, arrayToBeSorted, NUMBER_OF_LOOPS);
        
        System.out.println("\nInsertion Sort:");
        System.out.print("N          -  Loops         - Average time (ms)  -  Theoretical time\n");
        arrayToBeSorted = generateRandomInts(SIZE_OF_LARGE_ARRAY);
        benchmarkAlgorithm("Insertion sort", InsertionSorter::insertionSort, arrayToBeSorted, NUMBER_OF_LOOPS);
        arrayToBeSorted = generateRandomInts(SIZE_OF_LARGER_ARRAY);
        benchmarkAlgorithm("Insertion sort", InsertionSorter::insertionSort, arrayToBeSorted, NUMBER_OF_LOOPS);
        arrayToBeSorted = generateRandomInts(SIZE_OF_LARGEST_ARRAY);
        benchmarkAlgorithm("Insertion sort", InsertionSorter::insertionSort, arrayToBeSorted, NUMBER_OF_LOOPS);

        System.out.println("\nQuick Sort:");
        System.out.print("N          -  Loops         - Average time (ms)  -  Theoretical time\n");
        arrayToBeSorted = generateRandomInts(SIZE_OF_LARGE_ARRAY);
        measureQuickSort(arrayToBeSorted, NUMBER_OF_LOOPS);
        arrayToBeSorted = generateRandomInts(SIZE_OF_LARGER_ARRAY);
        measureQuickSort(arrayToBeSorted, NUMBER_OF_LOOPS);
        arrayToBeSorted = generateRandomInts(SIZE_OF_LARGEST_ARRAY);
        measureQuickSort(arrayToBeSorted, NUMBER_OF_LOOPS);

        System.out.println("\nMerge Sort:");
        System.out.print("N          -  Loops         - Average time (ms)  -  Theoretical time\n");
        arrayToBeSorted = generateRandomInts(SIZE_OF_LARGE_ARRAY);
        benchmarkAlgorithm("Merge sort", MergeSorter::sort, arrayToBeSorted, NUMBER_OF_LOOPS);
        arrayToBeSorted = generateRandomInts(SIZE_OF_LARGER_ARRAY);
        benchmarkAlgorithm("Merge sort", MergeSorter::sort, arrayToBeSorted, NUMBER_OF_LOOPS);
        arrayToBeSorted = generateRandomInts(SIZE_OF_LARGEST_ARRAY);
        benchmarkAlgorithm("Merge sort", MergeSorter::sort, arrayToBeSorted, NUMBER_OF_LOOPS);
    }

    // Used by main() to initialize and reset the test array.
    private static Integer[] generateRandomInts(int size)
    {
        Integer[] array = new Integer[size];
        Random generator = new Random();
        for (int i = 0; i < array.length; i++) {array[i] = generator.nextInt();}
        return array;
    }

    // A helper method containing println() logic, as to avoid duplicate code in main().
    private static <T> void benchmarkAlgorithm(String algorithmName, Consumer<T[]> sortingAlgorithm, T[] array, int numberOfLoops)
    {
   
     // System.out.print("N          -  Loops         - Average time (ms)  -  Theoretical time\n");
        measureAlgorithm(sortingAlgorithm, array, numberOfLoops);
        
    }

    // Loops through a specified sorting algorithm, and prints out the average runtime to the console.
    private static <T> void measureAlgorithm(Consumer<T[]> sortingAlgorithm, T[] array, int numberOfLoops)
    {
        long totalDuration = 0;

        for (int i = 0; i < numberOfLoops; i++) {
            Instant start = Instant.now();
            sortingAlgorithm.accept(array);
            Instant stop = Instant.now();
            totalDuration += Duration.between(start, stop).toMillis();
        }

        long averageDuration = totalDuration / numberOfLoops;

        float constant = (float) (averageDuration / (Math.pow(array.length, 2)));
        long theoreticalTime = (long) (constant * (Math.pow(array.length, 2)));

        System.out.println(
                array.length + "      -  " + numberOfLoops + "            - " + averageDuration + "               -  " + theoreticalTime);
    }

    // QuickSorter.sort() has too many parameters for measureAlgorithm(), and requires its own measuring method.
    private static <T extends Comparable<? super T>> void measureQuickSort(T[] array, int numberOfLoops) {
        long totalDuration = 0;

        for (int i = 0; i < numberOfLoops; i++) {
            Instant start = Instant.now();
            QuickSorter.sortIteratively(array, 0, array.length - 1); // Call QuickSort.sort() directly here
            Instant stop = Instant.now();
            totalDuration += Duration.between(start, stop).toMillis();
        }

        long averageDuration = totalDuration / numberOfLoops;

        float c = (float) (averageDuration / (Math.pow(array.length, 2)));
        long theoreticalTime = (long) (c * (Math.pow(array.length, 2)));

      
        System.out.println(
                array.length + " 		-  " + numberOfLoops + "		    -	" + averageDuration + "	               -  " + theoreticalTime);
    }
}