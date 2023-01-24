package Task2_Anagrams;
import java.util.*;

import DictionaryPackage.*;
import HashingPackage.*;

import java.io.*;

public class AnagramsUsingDictionaryInterface {
	public static void main(String[] args) throws IOException {
		
		// comment out one of these lines to test
		
		DictionaryInterface<String, ArrayList<String>> anagrams = new HashedDictionary<String, ArrayList<String>>();
		//	DictionaryInterface<String, ArrayList<String>> anagrams = new HashMapDictionary<String, ArrayList<String>>();
		
		String[] testWords = {
				"thesis",
				"server",
				"sublet",
				"reverse",
				"retraced",
				"stripes",
				"evils",
				"nailset",
				"reliant",
				"demo",
				"deer",
				"rifles",
				"baritone",
				"pointer",
				"cobra",
				"strip",
				"sterling",
				"earliest",
				"rowth",
				"reshoot",
				"names",
				"presplit",
				"teach",
				"scare",
				"bread",
				"steal"
		};

		// get all anagrams for all words in the file.
		createAnagrams("src\\Task2_Anagrams\\dictionary.txt", anagrams);

		// display all anagrams for the list of test words to the console
		
		System.out.println("******** Showing anagrams for test words");
		
		// insert your code here to display on the console the anagrams for the test words above.
		// the output should match anagramsTestWordsOutput.txt exactly
		
		for (String words : testWords) {
			System.out.println("Anagrams for " + words + " " 
		+ anagrams.getValue( sortString(words).toString() ) );

		}
		
		
		// open the output file to list all of the anagrams in the dictionary
		
		//PrintWriter outputFile = new PrintWriter("src\\Task2_Anagrams\\anagrams.txt");
		
		//outputFile.println("********* Showing all keys and anagrams - total = " + anagrams.size());
		
		// insert your code here to write all keys and values to the anagrams.txt file.
		// only use the iterators to do this.
		// the output should match anagramsAssignmentOutput.txt exactly
		
		//outputFile.close();
		anagramsTestWordsOutput(anagrams);

	}

	/**
	 * Create a list of anagrams from a dictionary file and place into a Dictionary. An anagram has key which is a set
	 * of sorted characters, and a value consisting of an array of strings that are legal words in the dictionary that match
	 * the characters in the key.
	 * 
	 * anagramsMap for this assignment must use a hash table.
	 * @param fileName for dictionary words. Each word in the file should be on a separate line.
	 * @param anagramsMap resulting anagrams table
	 * @throws IOException
	 */
	private static void createAnagrams(String fileName, DictionaryInterface<String, ArrayList<String>> anagramsMap)
			throws IOException {
		Scanner scanner = new Scanner(new File(fileName));
		String dictionaryWord = scanner.next().trim();

		while (scanner.hasNext()) {

			String key = sortString(dictionaryWord);
			ArrayList<String> value = new ArrayList<String>();
			value.add(dictionaryWord);

			if (anagramsMap.contains(key)) {
				ArrayList<String> values = anagramsMap.getValue(key);
				values.add(dictionaryWord);
				anagramsMap.add(key, values);
			} else {
				anagramsMap.add(key, value);
			}

			dictionaryWord = scanner.next().trim();
		}
	}
	
	/**
	 * convert the word to arrays of character sort it accordingly to characters and return as a string 
	 * @param word to be sorted
	 * @return
	 */
	private static String sortString(String word) {
		char[] wordCharacters = word.toCharArray();
		Arrays.sort(wordCharacters);
		return new String(wordCharacters);
	}
	
	/**
	 * writing all the keys and value to output file i.e. anagrams.txt
	 * 
	 * @param <K> key
	 * @param <V> value
	 * @param dictionary anagrams table
	 * @throws IOException
	 */
	
	public static <K, V> void anagramsTestWordsOutput(DictionaryInterface<K, V> dictionary) throws IOException {
		PrintWriter outputFile = new PrintWriter("src\\Task2_Anagrams\\anagrams.txt");
		
		outputFile.println("********* Showing all keys and anagrams - total = " + (dictionary.size() + 1));
		Iterator<K> keyIterator = dictionary.getKeyIterator();
		Iterator<V> valueIterator = dictionary.getValueIterator();

		while (keyIterator.hasNext() && valueIterator.hasNext()) {
			K key = keyIterator.next();
			V value = valueIterator.next();

			outputFile.println(key + ": " + value);

		}
		System.out.println();
		outputFile.close();
	}

}
