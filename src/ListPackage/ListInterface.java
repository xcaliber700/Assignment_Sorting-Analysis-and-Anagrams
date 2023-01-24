package ListPackage;
/**
 * An interface for the ADT list. Entries in a list have positions that begin
 * with 0.
 * 
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 * @version 5.0
 * 
 * @author mhrybyk
 * 
 * Included other methods for add and replace.
 * Spec changed for positions to be 0..size()-1
 */
public interface ListInterface<T> {
	/**
	 * Adds a new entry to the end of this list. Entries currently in the list are
	 * unaffected. The list's size is increased by 1.
	 * 
	 * @param newEntry The object to be added as a new entry.
	 * @return false if not enough room in the list to add the entry
	 */
	public boolean add(T newEntry);

	/**
	 * Adds a new entry at a specified position within this list. Entries originally
	 * at and above the specified position are at the next higher position within
	 * the list. The list's size is increased by 1.
	 * 
	 * If the position is equal to the size of the list, simply add the entry to the list.
	 * 
	 * Entries start at position 0 and continue to (size() - 1)
	 * 
	 * @param newPosition An integer that specifies the desired position of the new
	 *                    entry.
	 * @param newEntry    The object to be added as a new entry.
	 * @return false if not enough room in the list to add the entry
	 */
	public boolean add(int newPosition, T newEntry);

	/**
	 * Removes the entry at a given position from this list. Entries originally at
	 * positions higher than the given position are at the next lower position
	 * within the list, and the list's size is decreased by 1.
	 * 
	 * Entries start at position 0 and continue to (size() - 1)
	 *  
	 * @param givenPosition An integer that indicates the position of the entry to
	 *                      be removed.
	 * @return A reference to the removed entry
	 * 
	 * @throws  IndexOutOfBoundsException if 
	 * 		givenPosition is outside of the range where 0 <= givenPosition < size()
	 */
	public T remove(int givenPosition);

	/**
	 * Removes the first or only occurrence of a specified entry from this list.
	 * 
	 * @param anEntry The object to be removed.
	 * @return True if anEntry was located and removed; otherwise returns false.
	 */
	public boolean removeEntry(T anEntry);
	
	/**
	 * Removes all entries from the list
	 */
	public void clear();

	/**
	 * Replaces the entry at a given position in this list.
	 * 
	 * Entries start at position 0 and continue to (size() - 1)
	 * 
	 * @param givenPosition An integer that indicates the position of the entry to
	 *                      be replaced.
	 * @param newEntry      The object that will replace the entry at the position
	 *                      givenPosition.
	 * @return A reference to the original entry that was replaced
	 * 
	 * @throws  IndexOutOfBoundsException if 
	 * 		givenPosition is outside of the range where 0 <= givenPosition < size()
	 */
	public T replace(int givenPosition, T newEntry);

	/**
	 * Retrieves the entry at a given position in this list.
	 * 
	 * Entries start at position 0 and continue to (size() - 1)
	 * 
	 * @param givenPosition An integer that indicates the position of the desired
	 *                      entry, where 0 <= givenPosition < size()
	 * @return A reference to the entry
	 * 
	 * @throws  IndexOutOfBoundsException if 
	 * 		givenPosition is outside of the range where 0 <= givenPosition < size()
	 */
	public T getEntry(int givenPosition);
	
	/**
	 * Find the first occurence of an entry in the list, and return its position
	 * @param anEntry
	 * @return position or -1 if not found
	 */
	public int findEntry(T anEntry);

	/**
	 * Retrieves all entries that are in this list in the order in which they occur
	 * in the list. The array has a size of the number of entries in the list,
	 * and not the internal array size.
	 * 
	 * Make sure to use Object[] as a return value when using this, then cast due
	 * to type erasure in Java.
	 * 
	 * @return A newly allocated array of all the entries in the list. If the list
	 *         is empty, the returned array is empty.
	 */
	public T[] toArray();

	/**
	 * Sees whether this list contains a given entry.
	 * 
	 * @param anEntry The object that is the desired entry.
	 * @return True if the list contains anEntry, or false if not.
	 */
	public boolean contains(T anEntry);

	/**
	 * Gets the size of this list.
	 * 
	 * @return The integer number of entries currently in the list.
	 */
	public int size();

	/**
	 * Determines whether the list is empty, or size() == 0
	 * 
	 * @return True if the list is empty, or false if not.
	 */
	public boolean isEmpty();
}
