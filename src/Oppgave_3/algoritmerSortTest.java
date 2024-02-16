package Oppgave_3;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class algoritmerSortTest {

	Integer[] tab;
	Integer[] sortertTab;

	@BeforeEach
	void setUp() throws Exception {
		Integer[] tabell = { 5, 2, 7, 3, 9 };
		this.tab = tabell;
		Integer[] sortert = { 2, 3, 5, 7, 9 };
		this.sortertTab = sortert;
	}

	@Test
	void testInsertionSort() {
		assertNotEquals(tab[0], sortertTab[0]);

		InsertionSorter.insertionSort(tab);
		assertEquals(tab[tab.length - 1], 9);

		for (int i = 0; i < tab.length; i++) {
			assertEquals(tab[i], sortertTab[i]);
		}
	}

	@Test
	void testSelectionSort() {
		assertNotEquals(tab[0], sortertTab[0]);

		SelectionSorter.sort(tab);
		assertEquals(tab[tab.length - 1], 9);

		for (int i = 0; i < tab.length; i++) {
			assertEquals(tab[i], sortertTab[i]);
		}
	}

	@Test
	void testQuickSort() {
		assertNotEquals(tab[0], sortertTab[0]);
		QuickSorter.sortIteratively(tab, 0, tab.length - 1);
		assertEquals(tab[tab.length - 1], 9);

		for (int i = 0; i < tab.length; i++) {
			assertEquals(tab[i], sortertTab[i]);
		}
	}

	@Test
	void testMergeSort() {
		assertNotEquals(tab[0], sortertTab[0]);
		MergeSorter.sort(tab);
		assertEquals(tab[tab.length - 1], 9);

		for (int i = 0; i < tab.length; i++) {
			assertEquals(tab[i], sortertTab[i]);
		}
	}

}