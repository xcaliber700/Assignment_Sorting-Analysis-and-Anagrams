package ListPackage;

/**
 * A linked implemention of the ADT list.
 * 
 * @author Babanjot Singh
 * 
 *         modified to use a zero offset for position Uses Node class which is
 *         doubly-linked, but only use forward links here
 */
public class LListWithTail<T extends Comparable<? super T>> implements ListInterface<T> {
	private Node<T> firstNode; // Reference to first node of chain
	private Node<T> lastNode; // Reference to last node of chain
	private int numberOfEntries;

	public LListWithTail() {
		initializeDataFields();
	}

	/**
	 * Reset all data in the list.
	 */
	private void initializeDataFields() {
		firstNode = null;
		lastNode = null;
		numberOfEntries = 0;
	}

	/**
	 * Return the first node in the list. Violates data hiding.
	 * 
	 * Only used by iterator. In production, the subclass would be final, or the
	 * iterator would be built in from the start.
	 * 
	 * @return
	 */
	protected Node<T> getFirstNode() {
		return firstNode;
	}

	@Override
	public boolean add(T newEntry) {
		Node<T> newNode = new Node<>(newEntry);

		if (isEmpty()) {
			firstNode = newNode;
		} else {
			lastNode.setNextNode(newNode);
		}

		lastNode = newNode;
		++numberOfEntries;

		return true;
	}

	@Override
	public boolean add(int givenPosition, T newEntry) {

		if (isInRange(givenPosition) || givenPosition == numberOfEntries) {

			Node<T> newNode = new Node<>(newEntry);

			if (isEmpty()) {
				firstNode = newNode;
				lastNode = newNode;
			} else if (givenPosition == 0) {
				newNode.setNextNode(firstNode);
				firstNode = newNode;
			} else if (givenPosition == numberOfEntries) {
				lastNode.setNextNode(newNode);
				lastNode = newNode;
			} else {
				Node<T> nodeBefore = getNodeAt(givenPosition - 1);
				Node<T> nodeAfter = nodeBefore.getNextNode();

				newNode.setNextNode(nodeAfter);
				nodeBefore.setNextNode(newNode);
			}
			numberOfEntries++;
			return true;
		} else
			return false;
	}

	@Override
	public T remove(int givenPosition) {
		if (!isInRange(givenPosition))
			throw new IndexOutOfBoundsException();

		T result = null; // Return value

		if (givenPosition == 0) {
			// first entry
			result = firstNode.getData();
			firstNode = firstNode.getNextNode();
		} else {

			Node<T> nodeBefore = getNodeAt(givenPosition - 1);
			Node<T> nodeToRemove = nodeBefore.getNextNode();

			result = nodeToRemove.getData();

			Node<T> nodeAfter = nodeToRemove.getNextNode();
			nodeBefore.setNextNode(nodeAfter);
		}
		--numberOfEntries;

		return result;
	}

	@Override
	public boolean removeEntry(T anEntry) {
		int position = findEntry(anEntry);
		if (position < 0)
			// entry does not exist
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
		initializeDataFields();
	}

	@Override
	public T replace(int givenPosition, T newEntry) {
		if (!isInRange(givenPosition))
			throw new IndexOutOfBoundsException();
		T result = null;
		if (givenPosition == 0) {
			// removing first entry
			result = firstNode.getData();
			firstNode = firstNode.getNextNode();
		} else {
			Node<T> nodeBefore = getNodeAt(givenPosition - 1);
			Node<T> nodeToRemove = nodeBefore.getNextNode();

			result = nodeToRemove.getData();

			Node<T> nodeAfter = nodeToRemove.getNextNode();
			nodeBefore.setNextNode(nodeAfter);
			// last node
			if (givenPosition == numberOfEntries - 1)
				lastNode = nodeBefore;
		}
		--numberOfEntries;

		return result;
	}

	@Override
	public T getEntry(int givenPosition) {
		if (!isInRange(givenPosition))
			throw new IndexOutOfBoundsException();

		return getNodeAt(givenPosition).getData();
	}

	@Override
	public int findEntry(T anEntry) {
		boolean found = false;

		int position = 0;
		Node<T> currentNode = firstNode;

		while (!found && (currentNode != null)) {
			if (anEntry.equals(currentNode.getData()))
				found = true;
			else {
				currentNode = currentNode.getNextNode();
				position++;
			}
		}
		if (found == true)
			return position;
		else
			return -1;
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Comparable[numberOfEntries];

		int index = 0;
		Node<T> currentNode = firstNode;
		while ((index < numberOfEntries) && (currentNode != null)) {
			result[index] = currentNode.getData();
			currentNode = currentNode.getNextNode();
			index++;
		}

		return result;
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
	 * Returns a reference to the Node<T> at a given position.
	 * 
	 * @param givenPosition
	 * @return
	 */
	private Node<T> getNodeAt(int givenPosition) {
		if (isEmpty())
			return null;
		if (!isInRange(givenPosition))
			return null;

		// last node
		if (givenPosition == numberOfEntries - 1)
			return lastNode;

		Node<T> currentNode = firstNode;
		for (int counter = 0; counter < givenPosition; counter++)
			currentNode = currentNode.getNextNode();

		return currentNode;
	}

}
