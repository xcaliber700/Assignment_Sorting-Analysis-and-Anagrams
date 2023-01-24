package HashingPackage;

import java.util.Iterator;

import DictionaryPackage.*;

/**
 * A driver that demonstrates the class HashedDictionary. Note: The class Name
 * overrides hashCode in a way that causes collisions within the method
 * testHashTable.
 * 
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 * @version 5.0
 * 
 * @author mhrybyk
 * 
 * Moved dictionary creation to main code, and have test methods call as arguments.
 * Allows for easy changing of dictionary implementation for test
 * Removed use of internal display() method for dictionary, not wise to use anyway.
 * Just interferes with testing interface implementation.
 * Modified display() method to include hash code.
 * 
 */
public class HashedDictionaryTestDriver

{
	public static void main(String[] args) {
		// uncomment out Completed to test implementation

//		DictionaryInterface<String, String> telephoneDirectory = new CompletedHashMapDictionary<>();
//		DictionaryInterface<String, String> telephoneDirectory = new CompletedHashedDictionary<>();
//		DictionaryInterface<String, String> telephoneDirectory = new HashMapDictionary<>();
		DictionaryInterface<String, String> telephoneDirectory = new HashedDictionary<>();
		testDictionary(telephoneDirectory);

//		DictionaryInterface<Name, String> nameList = new CompletedHashMapDictionary<>();
//		DictionaryInterface<Name, String> nameList = new CompletedHashedDictionary<>();
//		DictionaryInterface<Name, String> nameList = new HashMapDictionary<>();
		DictionaryInterface<Name, String> nameList = new HashedDictionary<>();
		testHashTable(nameList);
		System.out.println("\n\nDone.");
	}

	public static void testDictionary(DictionaryInterface<String, String> telephoneDirectory) {
		String dirk = "Dirk";
		String abel = "Abel";
		String miguel = "Miguel";
		String tabbie = "Tabatha";
		String tom = "Tom";
		String sam = "Sam";
		String reiss = "Reiss";
		String bette = "Bette";
		String carole = "Carole";
		String derek = "Derek";
		String nancy = "Nancy";
		String bogus = "Bo";

		// clear the dictionary first

		telephoneDirectory.clear();

		System.out.println("testDictionary(): Create a hashed dictionary of names and phone numbers");
		System.out.println("Initial dictionary should be empty; isEmpty() returns " + telephoneDirectory.isEmpty());

		// Test add
		System.out.println("Testing add():");
		telephoneDirectory.add(dirk, "555-1234");
		telephoneDirectory.add(abel, "555-5678");
		telephoneDirectory.add(miguel, "555-9012");
		telephoneDirectory.add(tabbie, "555-3456");
		telephoneDirectory.add(tom, "555-5555");
		telephoneDirectory.add(sam, "555-7890");
		telephoneDirectory.add(reiss, "555-2345");
		telephoneDirectory.add(bette, "555-7891");
		telephoneDirectory.add(carole, "555-7892");
		telephoneDirectory.add(derek, "555-7893");
		telephoneDirectory.add(nancy, "555-7894");

		System.out.println(telephoneDirectory.size() + " (should be 11) items in dictionary, as follows:");
		display(telephoneDirectory);

//		telephoneDirectory.displayHashTableStats(nameList);

		// Test getValue
		System.out.println("*** Testing getValue(): ***");
		System.out.println("Abel:   " + telephoneDirectory.getValue(abel) + " should be 555-5678");
		System.out.println("Sam:    " + telephoneDirectory.getValue(sam) + " should be 555-7890");
		System.out.println("Tom:    " + telephoneDirectory.getValue(tom) + " should be 555-5555");
		System.out.println("Reiss:  " + telephoneDirectory.getValue(reiss) + " should be 555-2345");
		System.out.println("Miguel: " + telephoneDirectory.getValue(miguel) + " should be 555-9012");

		// Test contains
		System.out.println("*** Testing contains(): ***");

		if (telephoneDirectory.contains(sam))
			System.out.println("Sam is in dictionary - OK");
		else
			System.out.println("Error in contains()");

		if (telephoneDirectory.contains(abel))
			System.out.println("Abel is in dictionary - OK");
		else
			System.out.println("Error in contains()");

		if (telephoneDirectory.contains(miguel))
			System.out.println("Miguel is in dictionary - OK");
		else
			System.out.println("Error in contains()");

		if (telephoneDirectory.contains(tom))
			System.out.println("Tom is in dictionary - OK");
		else
			System.out.println("Error in contains()");

		if (!telephoneDirectory.contains(bogus))
			System.out.println("Bo is not in dictionary - OK");
		else
			System.out.println("Error in contains()");

		// Remove first item
		System.out.println("Removing first item Abel - Abel's number is " + telephoneDirectory.remove(abel)
				+ " should be 555-5678");

		// Test replacing value
		System.out.println("Replacing phone number of Reiss and Miguel:");
		String oldNumber = telephoneDirectory.add(reiss, "555-5432");
		System.out.println("Reiss's old number " + oldNumber + " is replaced by 555-5432");
		oldNumber = telephoneDirectory.add(miguel, "555-2109");
		System.out.println("Miguel's old number " + oldNumber + " is replaced by 555-2109");

		System.out.println(
				"Dictionary size is " + telephoneDirectory.size() + " (should be 10) items in dictionary, as follows:");
		display(telephoneDirectory);

		// Remove interior and last items
		System.out.println("Removing interior item Reiss:");
		telephoneDirectory.remove(reiss);
		System.out.println(
				"Dictionary size is " + telephoneDirectory.size() + " (should be 9) items in dictionary, as follows:");
		display(telephoneDirectory);
		System.out.println("Removing Nancy:");
		telephoneDirectory.remove(nancy);
		System.out.println(
				"Dictionary size is " + telephoneDirectory.size() + " (should be 8) items in dictionary, as follows:");
		display(telephoneDirectory);

		// Remove Bo (not in dictionary)
		System.out.println("Removing Bo (not in dictionary):");
		String result = telephoneDirectory.remove(bogus);
		if (result == null)
			System.out.println("Bo was not found in the dictionary.");
		else
			System.out.println("Error in remove().");

		System.out.println(
				"Dictionary size is " + telephoneDirectory.size() + " (should be 8) items in dictionary, as follows:");
		display(telephoneDirectory);

		// Test clear
		System.out.println("Testing clear():");
		telephoneDirectory.clear();

		System.out.println("Dictionary should be empty; isEmpty() returns " + telephoneDirectory.isEmpty());
	} // testDictionary

	/**
	 * Tests the hash table when no locations contain null
	 * 
	 * @param nameList
	 */
	public static void testHashTable(DictionaryInterface<Name, String> nameList) {

		System.out.println("-----------------------------------------------------------------------");
		System.out.println("testHashTable() using Name object as key:");

		nameList.clear();
		System.out.println("Create a dictionary whose initial hash table has 5 locations:");
		System.out.println("Initial dictionary should be empty; isEmpty() returns " + nameList.isEmpty());

		// Add 5 names - rehashing will not occur, since the load factor will be < 0.5
		System.out.println("Testing add() - add 5 names:");
		nameList.add(new Name("Tabatha"), "555-1234");
		nameList.add(new Name("Toni"), "555-1235");
		nameList.add(new Name("Tobbie"), "555-1236");
		nameList.add(new Name("Tabbie"), "555-1237");
		nameList.add(new Name("Tim"), "555-1238");

		System.out.println("Dictionary size is " + nameList.size() + " (should be 5) items, as follows:\n");
		display(nameList);

		displayHashTableStats(nameList); // Display hash table [METHOD ADDED TO CLASS FOR TESTING PURPOSES]

		// We now remove a name and add a name, so the table size remains the same. Our
		// goal is to remove
		// null from all table locations. Then we will test the method contains() in
		// this situation.

		System.out.println("Remove Tabatha, add Nancy:");
		nameList.remove(new Name("Tabatha"));
		nameList.add(new Name("Nancy"), "555-1239");
		System.out.println("Dictionary contains " + nameList.size() + " (should be 5) items, as follows:\n");
		display(nameList);

		displayHashTableStats(nameList);
		System.out.println("....................................");

		System.out.println("Remove Toni, add Derek:");
		nameList.remove(new Name("Toni"));
		nameList.add(new Name("Derek"), "555-1240");
		System.out.println("Dictionary contains " + nameList.size() + " (should be 5) items, as follows:\n");
		display(nameList);

		displayHashTableStats(nameList);
		System.out.println("....................................");

		System.out.println("Remove Tabbie, add Carole:");
		nameList.remove(new Name("Tabbie"));
		nameList.add(new Name("Carole"), "555-1241");
		System.out.println("Dictionary contains " + nameList.size() + " (should be 5) items, as follows:\n");
		display(nameList);

		displayHashTableStats(nameList);
		System.out.println("....................................");

		System.out.println("Remove Tobbie, add Bette:");
		nameList.remove(new Name("Tobbie"));
		nameList.add(new Name("Bette"), "555-1242");
		System.out.println("Dictionary contains " + nameList.size() + " (should be 5) items, as follows:\n");
		display(nameList);

		displayHashTableStats(nameList);
		System.out.println("....................................");

		System.out.println("Remove Tim, add Reiss:");
		nameList.remove(new Name("Tim"));
		nameList.add(new Name("Reiss"), "555-1243");
		System.out.println("Dictionary contains " + nameList.size() + " (should be 5) items, as follows:\n");
		display(nameList);

		displayHashTableStats(nameList);
		System.out.println("....................................");

		System.out.println("Remove Derek, add Miguel:");
		nameList.remove(new Name("Derek"));
		nameList.add(new Name("Miguel"), "555-1244");
		System.out.println("Dictionary contains " + nameList.size() + " (should be 5) items, as follows:\n");
		display(nameList);

		displayHashTableStats(nameList);
		System.out.println("....................................");

		System.out.println("Remove Bette, add Tom:");
		nameList.remove(new Name("Bette"));
		nameList.add(new Name("Tom"), "555-1245");
		System.out.println("Dictionary contains " + nameList.size() + " (should be 5) items, as follows:\n");
		display(nameList);

		System.out.println("Add moT and oTm - results in increasing table size and rehashing");
		nameList.add(new Name("moT"), "555-1250");
		nameList.add(new Name("oTm"), "555-1251");
		System.out.println("Dictionary contains " + nameList.size() + " (should be 7) items, as follows:\n");
		display(nameList);

		displayHashTableStats(nameList);
		System.out.println("....................................");

		System.out.println("Locate Reiss, Carole, Nancy, Tom, oTm, and Zeke: ");
		if (nameList.contains(new Name("Reiss")))
			System.out.println("Reiss is in the dictionary ");
		else
			System.out.println("Reiss is NOT in the dictionary: ERROR ");

		if (nameList.contains(new Name("Carole")))
			System.out.println("Carole is in the dictionary ");
		else
			System.out.println("Carole is NOT in the dictionary: ERROR ");

		if (nameList.contains(new Name("Nancy")))
			System.out.println("Nancy is in the dictionary ");
		else
			System.out.println("Nancy is NOT in the dictionary: ERROR ");

		if (nameList.contains(new Name("Tom")))
			System.out.println("Tom is in the dictionary ");
		else
			System.out.println("Tom is NOT in the dictionary: ERROR ");

		if (nameList.contains(new Name("oTm")))
			System.out.println("oTm is in the dictionary ");
		else
			System.out.println("oTm is NOT in the dictionary: ERROR ");

		if (nameList.contains(new Name("Zeke")))
			System.out.println("Zeke is in the dictionary: ERROR ");
		else
			System.out.println("Zeke is NOT in the dictionary ");
	}

	public static <K, V> void display(DictionaryInterface<K, V> dictionary) {
		Iterator<K> keyIterator = dictionary.getKeyIterator();
		Iterator<V> valueIterator = dictionary.getValueIterator();

		int size = dictionary.size();

		System.out.println("**** Displaying dictionary size = " + size);
		while (keyIterator.hasNext() && valueIterator.hasNext()) {
			K key = keyIterator.next();
			V value = valueIterator.next();

			int hash = key.hashCode();
			System.out.println(key + " (" + hash + "): " + value);

			// only use the below if we know the hash table size
//			int index = hash % 23;			
//			System.out.println(key + " (" + hash + ") @" + index + ": " + value);

		}
		System.out.println();
	}

	/**
	 * This is unused. Only used by ChainedHashDictionary implementation.
	 * 
	 * @param <K>
	 * @param <V>
	 * @param dictionary
	 */
	public static <K, V> void displayHashTableStats(DictionaryInterface<K, V> dictionary) {
		// not used

	}

} 
/*
testDictionary(): Create a hashed dictionary of names and phone numbers
Initial dictionary should be empty; isEmpty() returns true
Testing add():
11 (should be 11) items in dictionary, as follows:
**** Displaying dictionary size = 11
Tom (84274): 555-5555
Dirk (2130334): 555-1234
Derek (65921111): 555-7893
Miguel (-1990368815): 555-9012
Sam (82879): 555-7890
Abel (2033832): 555-5678
Tabatha (111435105): 555-3456
Bette (64076450): 555-7891
Reiss (78842198): 555-2345
Carole (2011240948): 555-7892
Nancy (75033265): 555-7894

*** Testing getValue(): ***
Abel:   555-5678 should be 555-5678
Sam:    555-7890 should be 555-7890
Tom:    555-5555 should be 555-5555
Reiss:  555-2345 should be 555-2345
Miguel: 555-9012 should be 555-9012
*** Testing contains(): ***
Sam is in dictionary - OK
Abel is in dictionary - OK
Miguel is in dictionary - OK
Tom is in dictionary - OK
Bo is not in dictionary - OK
Removing first item Abel - Abel's number is 555-5678 should be 555-5678
Replacing phone number of Reiss and Miguel:
Reiss's old number 555-2345 is replaced by 555-5432
Miguel's old number 555-9012 is replaced by 555-2109
Dictionary size is 10 (should be 10) items in dictionary, as follows:
**** Displaying dictionary size = 10
Tom (84274): 555-5555
Dirk (2130334): 555-1234
Derek (65921111): 555-7893
Miguel (-1990368815): 555-2109
Sam (82879): 555-7890
Tabatha (111435105): 555-3456
Bette (64076450): 555-7891
Reiss (78842198): 555-5432
Carole (2011240948): 555-7892
Nancy (75033265): 555-7894

Removing interior item Reiss:
Dictionary size is 9 (should be 9) items in dictionary, as follows:
**** Displaying dictionary size = 9
Tom (84274): 555-5555
Dirk (2130334): 555-1234
Derek (65921111): 555-7893
Miguel (-1990368815): 555-2109
Sam (82879): 555-7890
Tabatha (111435105): 555-3456
Bette (64076450): 555-7891
Carole (2011240948): 555-7892
Nancy (75033265): 555-7894

Removing Nancy:
Dictionary size is 8 (should be 8) items in dictionary, as follows:
**** Displaying dictionary size = 8
Tom (84274): 555-5555
Dirk (2130334): 555-1234
Derek (65921111): 555-7893
Miguel (-1990368815): 555-2109
Sam (82879): 555-7890
Tabatha (111435105): 555-3456
Bette (64076450): 555-7891
Carole (2011240948): 555-7892

Removing Bo (not in dictionary):
Bo was not found in the dictionary.
Dictionary size is 8 (should be 8) items in dictionary, as follows:
**** Displaying dictionary size = 8
Tom (84274): 555-5555
Dirk (2130334): 555-1234
Derek (65921111): 555-7893
Miguel (-1990368815): 555-2109
Sam (82879): 555-7890
Tabatha (111435105): 555-3456
Bette (64076450): 555-7891
Carole (2011240948): 555-7892

Testing clear():
Dictionary should be empty; isEmpty() returns true
-----------------------------------------------------------------------
testHashTable() using Name object as key:
Create a dictionary whose initial hash table has 5 locations:
Initial dictionary should be empty; isEmpty() returns true
Testing add() - add 5 names:
Dictionary size is 5 (should be 5) items, as follows:

**** Displaying dictionary size = 5
hash 693 Tabatha  (693): 555-1234
hash 583 Tabbie  (583): 555-1237
hash 298 Tim  (298): 555-1238
hash 410 Toni  (410): 555-1235
hash 597 Tobbie  (597): 555-1236

Remove Tabatha, add Nancy:
Dictionary contains 5 (should be 5) items, as follows:

**** Displaying dictionary size = 5
hash 583 Tabbie  (583): 555-1237
hash 298 Tim  (298): 555-1238
hash 410 Toni  (410): 555-1235
hash 597 Tobbie  (597): 555-1236
hash 505 Nancy  (505): 555-1239

....................................
Remove Toni, add Derek:
Dictionary contains 5 (should be 5) items, as follows:

**** Displaying dictionary size = 5
hash 583 Tabbie  (583): 555-1237
hash 298 Tim  (298): 555-1238
hash 597 Tobbie  (597): 555-1236
hash 491 Derek  (491): 555-1240
hash 505 Nancy  (505): 555-1239

....................................
Remove Tabbie, add Carole:
Dictionary contains 5 (should be 5) items, as follows:

**** Displaying dictionary size = 5
hash 298 Tim  (298): 555-1238
hash 597 Tobbie  (597): 555-1236
hash 598 Carole  (598): 555-1241
hash 491 Derek  (491): 555-1240
hash 505 Nancy  (505): 555-1239

....................................
Remove Tobbie, add Bette:
Dictionary contains 5 (should be 5) items, as follows:

**** Displaying dictionary size = 5
hash 298 Tim  (298): 555-1238
hash 598 Carole  (598): 555-1241
hash 500 Bette  (500): 555-1242
hash 491 Derek  (491): 555-1240
hash 505 Nancy  (505): 555-1239

....................................
Remove Tim, add Reiss:
Dictionary contains 5 (should be 5) items, as follows:

**** Displaying dictionary size = 5
hash 518 Reiss  (518): 555-1243
hash 598 Carole  (598): 555-1241
hash 500 Bette  (500): 555-1242
hash 491 Derek  (491): 555-1240
hash 505 Nancy  (505): 555-1239

....................................
Remove Derek, add Miguel:
Dictionary contains 5 (should be 5) items, as follows:

**** Displaying dictionary size = 5
hash 518 Reiss  (518): 555-1243
hash 598 Carole  (598): 555-1241
hash 500 Bette  (500): 555-1242
hash 611 Miguel  (611): 555-1244
hash 505 Nancy  (505): 555-1239

....................................
Remove Bette, add Tom:
Dictionary contains 5 (should be 5) items, as follows:

**** Displaying dictionary size = 5
hash 518 Reiss  (518): 555-1243
hash 598 Carole  (598): 555-1241
hash 611 Miguel  (611): 555-1244
hash 304 Tom  (304): 555-1245
hash 505 Nancy  (505): 555-1239

Add moT and oTm - results in increasing table size and rehashing
Dictionary contains 7 (should be 7) items, as follows:

**** Displaying dictionary size = 7
hash 598 Carole  (598): 555-1241
hash 304 Tom  (304): 555-1245
hash 304 moT  (304): 555-1250
hash 304 oTm  (304): 555-1251
hash 518 Reiss  (518): 555-1243
hash 611 Miguel  (611): 555-1244
hash 505 Nancy  (505): 555-1239

....................................
Locate Reiss, Carole, Nancy, Tom, oTm, and Zeke: 
Reiss is in the dictionary 
Carole is in the dictionary 
Nancy is in the dictionary 
Tom is in the dictionary 
oTm is in the dictionary 
Zeke is NOT in the dictionary 


Done.



  */
