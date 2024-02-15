package Oppgave_2_Insertion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InsertionSorterTest {

    // Final arrays for comparison
    private final Integer[] SORTED_NUMBERS = {3, 7, 11, 13, 22, 42, 69, 404};
    private final Character[] SORTED_VOWELS = {'a', 'e', 'i', 'o', 'u'};
    private final String[] SORTED_FOODS = {"Apple", "Banana", "Pear", "Sandwich", "Yogurt"};

    // Arrays for testing
    private Integer[] favoriteNumbers;
    private Character[] vowels;
    private String[] favoriteFoods;

    @BeforeEach
    void setUp()
    {
        Integer[] newNumbers = {7, 22, 69, 11, 13, 42, 404, 3};
        favoriteNumbers = newNumbers;
        Character[] newVowels = {'o', 'e', 'u', 'a', 'i'};
        vowels = newVowels;
        String[] newFoods = {"Banana", "Sandwich", "Apple", "Yogurt", "Pear"};
        favoriteFoods = newFoods;
    }

    @Test
    void testSetMinIndexAndSort()
    {
        Integer[] sortedIntegers = InsertionSorter.setMinIndexAndSort(favoriteNumbers);
        assertArrayEquals(SORTED_NUMBERS, sortedIntegers);
        Character[] sortedCharacters = InsertionSorter.setMinIndexAndSort(vowels);
        assertArrayEquals(SORTED_VOWELS, sortedCharacters);
        String[] sortedStrings = InsertionSorter.setMinIndexAndSort(favoriteFoods);
        assertArrayEquals(SORTED_FOODS, sortedStrings);
    }

    @Test
    void testSortTwoByTwo()
    {
        Integer[] justSorted = InsertionSorter.sortTwoByTwo(favoriteNumbers);
        assertArrayEquals(SORTED_NUMBERS, justSorted);
        Character[] sortedCharacters = InsertionSorter.setMinIndexAndSort(vowels);
        assertArrayEquals(SORTED_VOWELS, sortedCharacters);
        String[] sortedStrings = InsertionSorter.setMinIndexAndSort(favoriteFoods);
        assertArrayEquals(SORTED_FOODS, sortedStrings);
    }

    @Test
    void testSetMinIndexAndSortTwoByTwo()
    {
        Integer[] justSorted = InsertionSorter.setMinIndexAndSortTwoByTwo(favoriteNumbers);
        assertArrayEquals(SORTED_NUMBERS, justSorted);
        Character[] sortedCharacters = InsertionSorter.setMinIndexAndSort(vowels);
        assertArrayEquals(SORTED_VOWELS, sortedCharacters);
        String[] sortedStrings = InsertionSorter.setMinIndexAndSort(favoriteFoods);
        assertArrayEquals(SORTED_FOODS, sortedStrings);
    }
}

