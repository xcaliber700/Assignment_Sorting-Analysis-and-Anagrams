package ListPackage;
/**
 * 
 * @author mhrybyk
 *
 * Class that holds data in a doubly linked list
 * @param <T>
 */

public class Node<T extends Comparable<? super T>> {
	private T data; // object with data to be held in the node
	private Node<T> next; // Link to next node
	private Node<T> previous; // Link to previous node

	public Node(T dataPortion) {
		data = dataPortion;
		next = null;
		previous = null;
	}

	public Node(T dataPortion, Node<T> nextNode) {
		data = dataPortion;
		next = nextNode;
		previous = null;
	}

	public Node(T dataPortion, Node<T> nextNode, Node<T> previousNode) {
		data = dataPortion;
		next = nextNode;
		previous = previousNode;
	}
	
	public T getData() {
		return data;
	} 

	public void setData(T newData) {
		data = newData;
	} 

	public Node<T> getNextNode() {
		return next;
	}

	public void setNextNode(Node<T> nextNode) {
		next = nextNode;
	} 
	
	public Node<T> getPreviousNode() {
		return previous;
	}
	
	public void setPreviousNode(Node<T> previousNode) {
		previous = previousNode;
	}
} 
