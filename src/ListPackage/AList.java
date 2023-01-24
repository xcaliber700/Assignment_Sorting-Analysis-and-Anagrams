package ListPackage;

import java.util.Arrays;

/**
 * A class that implements the ADT list by using a fixed array. Duplicate
 * entries are allowed.
 * 
 * 
 * @author Babanjot Singh
 * 
 *         Redone to simplify and make everything zero index based
 */
public class AList<T extends Comparable<? super T>> implements ListInterface<T> {
	private T[] list; // Array of list entries
	private int numberOfEntries;

	private static final int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 10000;

	public AList() {
		this(DEFAULT_CAPACITY);
	} // end default constructor

	public AList(int initialCapacity) {

		// Is initialCapacity too small?
		if (initialCapacity < DEFAULT_CAPACITY)
			initialCapacity = DEFAULT_CAPACITY;

		// cap it at maximum capacity

		if (initialCapacity >= MAX_CAPACITY)
			initialCapacity = MAX_CAPACITY;

		// The cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		T[] tempList = (T[]) new Comparable[initialCapacity];
		list = tempList;

		numberOfEntries = 0;

	}

	@Override
	public boolean add(T newEntry) {
		try {
			ensureCapacity();
		} catch (Exception e) {
			return false;
		}
		// set entry into the next slot of the array
		list[numberOfEntries] = newEntry;
		numberOfEntries++;
		return true;
	}

	private void ensureCapacity() {
		if (isFull()) {
			int newCapacity = 2 * size();
			checkCapacity(newCapacity); // Is capacity too big?
			list = Arrays.copyOf(list, newCapacity);
		}
	}

	private void checkCapacity(int capacity) {
		if (capacity < DEFAULT_CAPACITY)
			throw new IllegalStateException(
					"Attempt to create an array " + "whose capacity is smaller than " + DEFAULT_CAPACITY);
		else if (capacity > MAX_CAPACITY)
			throw new IllegalStateException(
					"Attempt to create an array " + "whose capacity is larger than " + MAX_CAPACITY);
	}

	@Override
	public boolean add(int newPosition, T newEntry) {
		// make sure we have room
		if (hasRoom() == false)
			return false;
		// if list is empty, ignore the position and add
		if (isEmpty()) {
			list[0] = newEntry;
			numberOfEntries = 1;
			return true;
		}
		// needs to be in range or in the last position (not greater)
		if (newPosition < 0 || newPosition > numberOfEntries)
			return false;
		// shift all entries starting at the desired position for the new entry
		// up by one to make room
		// notice we are starting from the back and shifting each to a new position
		for (int index = numberOfEntries - 1; index >= newPosition; index--) {
			list[index + 1] = list[index];
		}
		// now set new entry into the vacated position and increast the list count
		list[newPosition] = newEntry;
		numberOfEntries++;
		return true;
	}

	@Override
	public T remove(int givenPosition) {
		if (!isInRange(givenPosition))
			throw new IndexOutOfBoundsException();
		// Get entry to be removed
		T result = list[givenPosition];
		// shift all of the elements of the array down
		// to cover the removed slot.
		for (int index = givenPosition; index < numberOfEntries; index++)
			list[index] = list[index + 1];
		// decrement size
		numberOfEntries--;
		return result;
	}

	@Override
	public boolean removeEntry(T anEntry) {
		// find the entry, then remove it if it exists
		int position = findEntry(anEntry);
		if (position < 0)
			return false;
		else {
			if (remove(position) != null)
				return true;
			else
				return false;
		}
	}

	@Override
	public void clear() {
		// Clear entries but retain array; no need to create a new array
		for (int index = 0; index < numberOfEntries; index++)
			list[index] = null;
		numberOfEntries = 0;
	}

	@Override
	public T replace(int givenPosition, T newEntry) {
		if (!isInRange(givenPosition))
			throw new IndexOutOfBoundsException();
		T oldEntry = list[givenPosition];
		list[givenPosition] = newEntry;
		return oldEntry;
	}

	@Override
	public T getEntry(int givenPosition) {
		// if a legal position, return the data in the array slot
		if (!isInRange(givenPosition))
			throw new IndexOutOfBoundsException();
		return list[givenPosition];
	}

	@Override
	public int findEntry(T anEntry) {
		int position = 0;
		// if the entry exists, loop will end when found.
		while ((position < numberOfEntries) && !anEntry.equals(getEntry(position))) {
			position++;
		}
		// return -1 if not found as the entire list has been traversed
		return position == numberOfEntries ? -1 : position;
	}

	@Override
	public T[] toArray() {
		return (T[]) Arrays.copyOf(list, numberOfEntries);
	}

	@Override
	public boolean contains(T anEntry) {
		return findEntry(anEntry) >= 0;
	}

	@Override
	public int size() {
		return numberOfEntries;
	}

	@Override
	public boolean isEmpty() {
		return numberOfEntries == 0;
	}

	/**
	 * Determines if a position is in the proper range of the list
	 * 
	 * @param givenPosition
	 * @return
	 */
	public boolean isInRange(int givenPosition) {
		return (givenPosition >= 0) && (givenPosition < numberOfEntries);
	}

	/**
	 * Determine if there is no more room in the list to add entries
	 * 
	 * @return true if the list is full
	 */
	public boolean isFull() {
		if (numberOfEntries >= list.length)
			return true;
		else
			return false;
	}

	/**
	 * Determines if there is room in the list to add entries
	 * 
	 * @return true if there is room in the list
	 */
	public boolean hasRoom() {
		if (numberOfEntries < list.length)
			return true;
		else
			return false;
	}

}
