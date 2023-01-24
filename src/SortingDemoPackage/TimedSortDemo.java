package SortingDemoPackage;

import ListPackage.*;

/**
 * Time the running of each sort method.
 * 
 * Generate a list of numbers to sort.
 * 
 * For each sort method
 * 1. Copy the list to a list to sort
 * 2. Get the start time in milliseconds
 * 3. Run a particular sort method on the copied list.
 * 4. Get the end time in milliseconds
 * 5. Show the elapsed time and a truncated list
 * 
 * @author mhrybyk
 *
 */

public class TimedSortDemo {

	public static void main(String[] args) {

		// make two lists of integers, each the same, so we can compare sort methods
		long startTime;
		long endTime;
		int listSize = 10000;
		int displaySize = 20;
		
		ListInterface<String> originalListOfIntegers = new AList<>(listSize);
		ListInterface<String> aListOfIntegers = new AList<>(listSize);
		
//		// as noted, the sort utility methods are very inefficient using LList.
//		// do not use for large lists
//		ListInterface<String> originalListOfIntegers = new CompletedLList<>();
//		ListInterface<String> aListOfIntegers = new CompletedLList<>();

			
		// first generate a list of integers, and display them
		
		DemoUtilities.generateListOfNumbers(originalListOfIntegers, listSize, listSize);
		DemoUtilities.displayTruncated(originalListOfIntegers, "Original List of random Integers", displaySize);
		
		DemoUtilities.copyListOfNumbers(originalListOfIntegers, aListOfIntegers);	
		startTime = System.currentTimeMillis();
		SortUtilities.selectionSort(aListOfIntegers, 0, aListOfIntegers.size() - 1);	
		endTime = System.currentTimeMillis();
		DemoUtilities.displayTruncated(aListOfIntegers, "Integers Selection Sort, time = " + (endTime - startTime), displaySize);
		
		DemoUtilities.copyListOfNumbers(originalListOfIntegers, aListOfIntegers);
		startTime = System.currentTimeMillis();
		SortUtilities.insertionSort(aListOfIntegers, 0, aListOfIntegers.size() - 1);		
		endTime = System.currentTimeMillis();
		DemoUtilities.displayTruncated(aListOfIntegers, "Integers Insertion Sort, time = " + (endTime - startTime), displaySize);

		DemoUtilities.copyListOfNumbers(originalListOfIntegers, aListOfIntegers);
		startTime = System.currentTimeMillis();
		SortUtilities.quickSort(aListOfIntegers, 0, aListOfIntegers.size() - 1);		
		endTime = System.currentTimeMillis();
		DemoUtilities.displayTruncated(aListOfIntegers, "Integers Quick Sort, time = " + (endTime - startTime), displaySize);	
		
		DemoUtilities.copyListOfNumbers(originalListOfIntegers, aListOfIntegers);
		startTime = System.currentTimeMillis();
		SortUtilities.mergeSort(aListOfIntegers, 0, aListOfIntegers.size() - 1);		
		endTime = System.currentTimeMillis();
		DemoUtilities.displayTruncated(aListOfIntegers, "Integers Merge Sort, time = " + (endTime - startTime), displaySize);	
		
		DemoUtilities.copyListOfNumbers(originalListOfIntegers, aListOfIntegers);
		startTime = System.currentTimeMillis();
		SortUtilities.sortUsingLibrary(aListOfIntegers, 0, aListOfIntegers.size() - 1);		
		endTime = System.currentTimeMillis();
		DemoUtilities.displayTruncated(aListOfIntegers, "Integers Library Sort, time = " + (endTime - startTime), displaySize);	
		
		// recursive insertion sort will fail at larger numbers
//		DemoUtilities.copyListOfNumbers(originalListOfIntegers, aListOfIntegers);
//		startTime = System.currentTimeMillis();
//		SortUtilities.recursiveInsertionSort(aListOfIntegers, 0, aListOfIntegers.size() - 1);		
//		endTime = System.currentTimeMillis();
//		DemoUtilities.displayTruncated(aListOfIntegers, "Integers Recursive Insertion Sort, time = " + (endTime - startTime), displaySize);	
	}

}
