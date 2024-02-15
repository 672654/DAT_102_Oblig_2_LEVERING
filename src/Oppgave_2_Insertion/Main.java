package Oppgave_2_Insertion;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.function.Consumer;

public class Main
{
    public static void main(String[] args)
    {
        Integer[] integers = generateTwoHundredThousandRandomIntegers();
        measureAlgorithm(InsertionSorter::setMinIndexAndSort, integers, 10);            // 7571 ms
        integers = generateTwoHundredThousandRandomIntegers();
        measureAlgorithm(InsertionSorter::sortTwoByTwo, integers, 10);                  // 5937 ms
        integers = generateTwoHundredThousandRandomIntegers();
        measureAlgorithm(InsertionSorter::setMinIndexAndSortTwoByTwo, integers, 10);    // 5841 ms

        // Conclusion: Our optimizations have a noticeable impact, which will only grow as the input data grows!
    }

    private static Integer[] generateTwoHundredThousandRandomIntegers()
    {
        Integer[] array = new Integer[200_000];
        Random generator = new Random();
        for (int i= 0; i < array.length; i++) {array[i] = generator.nextInt();}
        return array;
    }

    private static <T> void measureAlgorithm(Consumer<T[]> sortingAlgorithm, T[] array, int numberOfLoops)
    {
        long totalDuration = 0;

        for (int i = 0; i < numberOfLoops; i++)
        {
            Instant start = Instant.now();
            sortingAlgorithm.accept(array);
            Instant stop = Instant.now();
            totalDuration += Duration.between(start, stop).toMillis();
        }

        long averageDuration = totalDuration / numberOfLoops;
        System.out.println("Average sorting time for selected algorithm: " + averageDuration + " ms");
    }
}
