package SortingDemoPackage;

import ListPackage.*;

/**
 * Demonstrates use of sort algorithms.
 * 
 * Change the SortUtilities method to demonstrate a particular algorithm.
 * 
 * @author Babanjot Singh
 *
 */

public class SortDemo {

	public static void main(String[] args) {

		ListInterface<String> originalListOfIntegers = new AList<>();
		ListInterface<String> aListOfIntegers = new AList<>();

		DemoUtilities.generateListOfNumbers(originalListOfIntegers, 10, 8);
		DemoUtilities.display(originalListOfIntegers, "Original List of random Integers");
		System.out.println();

		DemoUtilities.copyListOfNumbers(originalListOfIntegers, aListOfIntegers);
		SortUtilities.selectionSort(aListOfIntegers, 0, originalListOfIntegers.size() - 1);
		DemoUtilities.display(aListOfIntegers, "Integers Selection Sort");
		System.out.println();

		DemoUtilities.copyListOfNumbers(originalListOfIntegers, aListOfIntegers);
		SortUtilities.insertionSort(aListOfIntegers, 0, originalListOfIntegers.size() - 1);
		DemoUtilities.display(aListOfIntegers, "Integers Insertion Sort");
		System.out.println();

		DemoUtilities.copyListOfNumbers(originalListOfIntegers, aListOfIntegers);
		SortUtilities.shellSort(aListOfIntegers, 0, originalListOfIntegers.size() - 1);
		DemoUtilities.display(aListOfIntegers, "Integers Shell Sort");
		System.out.println();

		DemoUtilities.copyListOfNumbers(originalListOfIntegers, aListOfIntegers);
		SortUtilities.sortUsingLibrary(aListOfIntegers, 0, originalListOfIntegers.size() - 1);
		DemoUtilities.display(aListOfIntegers, "Integers Java Library Sort");
		System.out.println();

		DemoUtilities.copyListOfNumbers(originalListOfIntegers, aListOfIntegers);
		SortUtilities.quickSort(aListOfIntegers, 0, originalListOfIntegers.size() - 1);
		DemoUtilities.display(aListOfIntegers, "Integers Quick Sort");
		System.out.println();

		DemoUtilities.copyListOfNumbers(originalListOfIntegers, aListOfIntegers);
		SortUtilities.mergeSort(aListOfIntegers, 0, originalListOfIntegers.size() - 1);
		DemoUtilities.display(aListOfIntegers, "Integers Merge Sort");
		System.out.println();

	}

}
