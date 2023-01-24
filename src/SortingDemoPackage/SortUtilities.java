package SortingDemoPackage;

import java.util.Arrays;

import ListPackage.*;

/**
 * Set of utility sort methods.
 * 
 * Although utility methods are not recommended in OO design, this keeps the code simple
 * and in one place for demonstrating all of the sorting algorithms.
 * 
 * All work against any class that implements ListInterface with a type that is Comparable
 * 
 * All of the sort methods use the list methods rather than the typical array, list, ... .
 * 
 * Note that most methods are inefficient unless arrays are used. getEntry() and replace()
 * are efficient when used with arrays. These are the only methods actually used from ListInterface.
 * 
 * This is a different approach from the textbook where a new class is created for each sort algorithm.
 * 
 * 
 * @author mhrybyk
 *
 */
public final class SortUtilities <T extends Comparable<? super T>>  {
	
	private SortUtilities() {}; // to prevent instantiation

	/**
	 * Swap the data in the given positions in the list using getEntry()
	 * and replace() methods from ListInterface.
	 * 
	 * Need to catch IndexOutOfBoundsException
	 * 
	 * @param list
	 * @param first  position to swap
	 * @param second position to swap
	 */
	static public <T> void swap(ListInterface<T> list, int first, int second) {

		if (first == second || list == null)
			return;
		
		try {
			T firstEntry = list.getEntry(first);
			T secondEntry = list.getEntry(second);

			list.replace(first, secondEntry);
			list.replace(second, firstEntry);
		} catch (IndexOutOfBoundsException e) {
			return;
		}
	}

	/**
	 * Selection sort
	 * 
	 * iterate through the list, finding the smallest in the rest of the list then swapping
	 * @param list
	 * @param first beginning of range to sort
	 * @param last end of range to sort
	 */	
	static public <T extends Comparable<? super T>> void selectionSort(ListInterface<T> list, int first, int last) {

		for (int index = first; index <= last; index++) {
			// find the smallest in the rest of the list, then swap
			int nextSmallest = findSmallest(list, index, last);
			swap(list, index, nextSmallest);
		}
	}
	
	/**
	 * Recursive Selection sort
	 * 
	 * finding the smallest in the rest of the list swap, then recursively call again
	 * @param list
	 * @param first beginning of range to sort
	 * @param last end of range to sort
	 */	
	static public <T extends Comparable<? super T>> void recursiveSelectionSort(ListInterface<T> list, int first,
			int last) {

		if (first < last) {
			int nextSmallest = findSmallest(list, first, last);
			swap(list, first, nextSmallest);
			recursiveSelectionSort(list, first + 1, last);

		}
	}
	/**
	 * return the index (position) of the smallest entry in the list
	 * @param list
	 * @param first
	 * @param last
	 * @return
	 */
	static private <T extends Comparable<? super T>> int findSmallest(ListInterface<T> list, int first, int last) {
		T minimum = list.getEntry(first);

		int indexOfMinimum = first;

		for (int index = first + 1; index <= last; index++) {
			T temp = list.getEntry(index);
			if (temp.compareTo(minimum) < 0) {
				minimum = temp;
				indexOfMinimum = index;
			}
		}

		return indexOfMinimum;

	}


	/**
	 * Insertion sort
	 * 
	 * Get the next entry in a list. A sub list then exists of all prior entries.
	 * 
	 * Iterate through the sub list of prior entries, and then insert the current entry in
	 * the correct sorted position in the sub list.
	 * 
	 * Then iteratively get the next entry.
	 * 
	 * 
	 * @param list
	 * @param first
	 * @param last
	 */
	static public <T extends Comparable<? super T>> void insertionSort(ListInterface<T> list, int first, int last) {

		// iterate through the list, start with the second entry
		
		for (int unsorted = first + 1; unsorted <= last; unsorted++) {
			T current = list.getEntry(unsorted);
			// compare the current entry with the sub list and insert in order. Note
			// that the sub list ends prior to the current entry
			insertInOrder(current, list, first, unsorted - 1);
		}
	}

	/**
	 * Recursive insertion sort
	 * 
	 * Recursively call insertion sort until we only have a list of one, then begin to insert
	 * the current entry into the sorted sub list portion.
	 * @param list
	 * @param first
	 * @param last
	 */
	static public <T extends Comparable<? super T>> void recursiveInsertionSort(ListInterface<T> list, int first,
			int last) {
		if (first < last) {
			recursiveInsertionSort(list, first, last - 1);
			T next = list.getEntry(last);
			insertInOrder(next, list, first, last - 1);
		}
	}

	/**
	 * Compare an item to each list entry and insert it in the proper position. 
	 * 
	 * 
	 * @param item
	 * @param list
	 * @param first
	 * @param last
	 */
	static private <T extends Comparable<? super T>> void insertInOrder(T item, ListInterface<T> list, int first,
			int last) {
		
		// work from the last to first, since we have to shift items
		
		int index = last;

		for (; index >= first; index--) {
			T current = list.getEntry(index);
			// shift the item to the right if it is larger
			if (current.compareTo(item) > 0)
				list.replace(index + 1, current);
			else
				break;
		}
		// went one too far, replace current item in the right slot.
		list.replace(index + 1, item);

	}

	/**
	 * Shell Sort
	 * 
	 * Variation on insertion sort by using a set of intervals and then gradually decreasing the intervals
	 * @param list
	 * @param first
	 * @param last
	 */

	static public <T extends Comparable<? super T>> void shellSort(ListInterface<T> list, int first, int last) {

		int space = (last - first + 1) / 2; // initial interval is array size divided by 2

		while (space > 0) {
			// move begin up iteratively until end of interval
			for (int begin = first; begin <= (first + space - 1); begin++) {
				incrementalInsertionSort(list, begin, last, space);
			}
			// decrease interval
			space /= 2;
		}
	}

	/**
	 * Sort an interval sublist where each item position in the array to be sorted is offset by space.
	 * @param list
	 * @param first
	 * @param last
	 * @param space
	 */
	static private <T extends Comparable<? super T>> void incrementalInsertionSort(ListInterface<T> list, int first,
			int last, int space) {

		// basically the same as an insertion sort, but use 'space' intervals
		for (int unsorted = (first + space); unsorted <= last; unsorted += space) {
			int index = unsorted - space;
			T nextToInsert = list.getEntry(unsorted);
			for (; index >= first; index -= space) {
				T current = list.getEntry(index);
				if (current.compareTo(nextToInsert) > 0)
					list.replace(index + space, current);
				else
					break;
			}

			list.replace(index + space, nextToInsert);

		}

	}

	/**
	 * Merge sort
	 * 
	 * Divide the list in half, and then recursively sort until a list of size 2 is found.
	 * Sort each list, then merge the respective small lists together until the entire list
	 * is sorted.
	 * @param list
	 * @param first
	 * @param last
	 */
	@SuppressWarnings("unchecked")
	static public <T extends Comparable<? super T>> void mergeSort(ListInterface<T> list, int first, int last) {
		
		// uses a temp array for the merge. 
		// create it now, then call a helper method to actually implement the sort
		
		Object[] tempArray = list.toArray();  // because we need an object that implements Comparable

		T[] temp = (T[]) tempArray;

		// this is probably not necessary
		
		for (int i = 0; i < temp.length; i++)
			temp[i] = null;

		mergeSort(list, temp, first, last);
	}
	
	/**
	 * Helper method that implements recursive merge sort
	 * @param list
	 * @param temp temp list to use so it does not have to be recreated on each recursive call
	 * @param first
	 * @param last
	 */

	static private <T extends Comparable<? super T>> void mergeSort(ListInterface<T> list, T[] temp, int first,
			int last) {
		if (first < last) {
			
			// find the midpoint
			int middle = first + (last - first) / 2;
			
			// sort each 
			
			mergeSort(list, temp, first, middle);
			
			mergeSort(list, temp, middle + 1, last);
			
//			DemoUtilities.display(list, " >> MergeSort " + first + " " + middle + " " + last);
			
			// then merge the two 
			
			merge(list, temp, first, middle, last);
		}
	}

	/**
	 * Merge two halves of the array
	 * @param list
	 * @param temp
	 * @param first
	 * @param middle
	 * @param last
	 */
	static private <T extends Comparable<? super T>> void merge(ListInterface<T> list, T[] temp, int first, int middle,
			int last) {
		
		// copy items to temp array
		
		for (int i = first; i <= last; i++) {
			temp[i] = list.getEntry(i);
		}
		
		// merge the first with the second half
		// set up the indices
		
		int beginFirstHalf = first;
		int beginSecondHalf = middle + 1;
		int index = first;   // slot to place the merged item into
		
		// iterate through each half, comparing items
		// put the smallest one back on the original list in the next slot
		
		while (beginFirstHalf <= middle && beginSecondHalf <= last) {
			
			T firstHalfItem = temp[beginFirstHalf];
			T secondHalfItem = temp[beginSecondHalf];
			
			// the first half has the smaller item
			
			if (firstHalfItem.compareTo(secondHalfItem) <= 0) {
				list.replace(index, temp[beginFirstHalf]);
				beginFirstHalf++;
			} else {        // or else the second half does
				list.replace(index, temp[beginSecondHalf]);
				beginSecondHalf++;
			}
			index++;  
		}
		
		// are there any more in the first half?, if so add them in
		
		while (beginFirstHalf <= middle) {
			list.replace(index, temp[beginFirstHalf]);
			index++;
			beginFirstHalf++;
		}
	}

	/**
	 * Quick sort
	 * 
	 * Sort using a pivot value. In this implementation, we use the last element as the initial pivot
	 * @param list
	 * @param first
	 * @param last
	 */
	static public <T extends Comparable<? super T>> void quickSort(ListInterface<T> list, int first, int last) {
		if (first < last) {
			int pivotIndex = partition(list, first, last);
			// sort around the pivot index, splitting the list
			quickSort(list, first, pivotIndex - 1);
			quickSort(list, pivotIndex + 1, last);
		}

	}

	/**
	 * Partition the list with a new pivot, starting with the last
	 * @param list
	 * @param first
	 * @param last
	 * @return
	 */
	static private <T extends Comparable<? super T>> int partition(ListInterface<T> list, int first, int last) {
	
		T pivotValue = list.getEntry(last);
		int pivotIndex = last;
		
		// index to compare to pivot
		
		int indexFromLeft = first;       // start at the beginning of the list
		int indexFromRight = last - 1;   // pivot is at the back, so start one before it

		boolean done = false;
		
		while(!done) {
			
			// compare the left half of the array to the pivot until
			// we find one equal or greater
			
			T leftValue = list.getEntry(indexFromLeft);
			
			while(leftValue.compareTo(pivotValue) < 0) {
				// this can generate an index out of range, so if it happens, break out of the loop
				indexFromLeft++;
				try {
					leftValue = list.getEntry(indexFromLeft);
				} catch (IndexOutOfBoundsException e) {
					break;
				}
			}
			
			// do the same for right half, but find one less than or equal to
			
			T rightValue = list.getEntry(indexFromRight);
			
			while(rightValue.compareTo(pivotValue) > 0) {
				// this can generate an index out of range, so if it happens, break out of the loop
				indexFromRight--;
				try {
					rightValue = list.getEntry(indexFromRight);
				} catch (IndexOutOfBoundsException e) {
					break;
				}
			}

			// we now have one that is greater on the left, and less on the right
			// if the indices aren't equal, swap the values
			
			if(indexFromLeft < indexFromRight) {
				swap(list, indexFromLeft, indexFromRight);
				indexFromLeft++;
				indexFromRight--;
			}
			else done = true;
		}
		
		// finally, the left index value is the largest, so swap with pivot
		
		swap(list, pivotIndex, indexFromLeft);
		
//		 DemoUtilities.display(list, ">> Quick Sort left index " + indexFromLeft );
		
		pivotIndex = indexFromLeft;
		
		return pivotIndex;
	}
	
	/**
	 * sort using the Java Library method.
	 * 
	 * Uses merge sort
	 * 
	 * @param list
	 * @param first
	 * @param last
	 */
	static public <T extends Comparable<? super T>> void sortUsingLibrary(ListInterface<T> list, int first, int last) {
		
		// get the array
		
		Object[] tempArray = list.toArray();

		@SuppressWarnings("unchecked")
		T[] listCopy = (T[]) tempArray;
		
		// uses merge sort. hover over to see implementation notes
		// note that the third arg is toIndex exclusive, which effectively means the next index
		//   or for the entire array, array size
		Arrays.sort(listCopy, first, last + 1);
		
		// now copy it back
		list.clear();
		for(T item : listCopy)
			list.add(item);
	}

}
