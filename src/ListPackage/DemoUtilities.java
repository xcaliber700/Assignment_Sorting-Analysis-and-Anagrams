package ListPackage;
import java.util.Random;

public class DemoUtilities {

	/**
	 * display a list using the toArray method to retrieve items
	 * 
	 * show a header line with a message and list size
	 * 
	 * @param list
	 * @param message message to display before the list is shown
	 */
	static public <T> void display(ListInterface<T> list, String message) {
		
		// call the truncated method but show the entire list
		
		displayTruncated(list, message, list.size());
	}
	
	/**
	 * display a list using the toArray method to retrieve items
	 * 
	 * show a header line with a message and list size, but only display
	 * the first set of items up to a limit
	 * 
	 * @param list
	 * @param message message to display before the list is shown
	 * @param limit number of items in the list to show
	 */	
	static public <T> void displayTruncated(ListInterface<T> list, String message, int limit) {
		System.out.println("Display: " + message + ", size = " + list.size());
		
		if(list.size() == 0)
			return;

		Object[] tempArray = list.toArray();

		@SuppressWarnings("unchecked")
		T[] listCopy = (T[]) tempArray;

		int numberDisplayed = 0;
		for (T item : listCopy) {

			System.out.print(item + ", ");
			numberDisplayed++;
			
			if(numberDisplayed >= limit)
				break;
		}
		
		System.out.println();
		
	}

	/**
	 * display a list using getEntry method to retrieve items
	 * 
	 * show a header line with a message and list size
	 * 
	 * @param list
	 * @param message message header to be displayed along with list size
	 */
	static public <T> void displayUsingGetEntry(ListInterface<T> list, String message) {
		System.out.println("Display using getEntry(): " + message + ", size = " + list.size());

		for (int i = 0; i < list.size(); i++)
			System.out.print(list.getEntry(i) + ", ");
		System.out.println();
	}

	/**
	 * generate a list of strings, each one as an integer
	 * 
	 * Do this by generating a string list of random integers starting at some value
	 * 
	 * Display the list as it is created.
	 * 
	 * @param list String list
	 * @param start starting integer value
	 * @param max upper bound of the range of integers to create
	 */
	static public void generateListOfNumbers(ListInterface<String> list, int start, int max) {
		Random rand = new Random();
		
		list.clear();

		for (int i = 0; i < max; i++) {
			int number = rand.nextInt(max) + start;
			list.add(String.valueOf(number));
		}
	}
	
	/**
	 * Copy a list of integers.
	 * @param fromList
	 * @param toList
	 */
	static public void copyListOfNumbers(ListInterface<String> fromList, ListInterface<String> toList) {
		if(fromList == null || toList == null)
			return;
		toList.clear();

		for (int i = 0; i < fromList.size(); i++)
			toList.add(fromList.getEntry(i));

		
	}

}
