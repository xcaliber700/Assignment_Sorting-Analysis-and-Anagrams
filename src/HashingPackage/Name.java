package HashingPackage;

/**
 * This class is like the one in the folder Miscellaneous/Name/Comparable, but
 * adds a constructor and the method hashCode.
 * 
 * A mutable class that represents a person's comparable name. As given in the
 * answer to Study Question 1 in Java Interlude 5 with the addition of the
 * equals method.
 * 
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 * @version 5.0
 */
public class Name implements NameInterface, Comparable<Name> {
	private String first; // First name
	private String last; // Last name

	public Name(String firstName) // For testing ***************
	{
		setName(firstName, "");
	} 

	public Name() {
		this("", "");
	} 

	public Name(String firstName, String lastName) {
		first = firstName;
		last = lastName;
	} 

	public int hashCode() // For testing--causes collisions ***************
	{
		int h = 0;

		for (int i = 0; i < first.length(); i++) {
			h = h + first.charAt(i);
		} 

		return h; // This hash code causes collisions

		// return first.hashCode() + last.hashCode(); // This would be a reasonable hash
		// code
	} 

	public void setName(String firstName, String lastName) {
		setFirst(firstName);
		setLast(lastName);
	} 

	public String getName() {
		return toString();
	} 

	public void setFirst(String firstName) {
		first = firstName;
	} 

	public String getFirst() {
		return first;
	} 

	public void setLast(String lastName) {
		last = lastName;
	} 

	public String getLast() {
		return last;
	} 

	public void giveLastNameTo(NameInterface aName) {
		aName.setLast(last);
	} 

	public String toString() {
		return "hash " + hashCode() + " " + first + " " + last;
	} 

	public int compareTo(Name other) // As given in the answer to Study Question 1 in Java Interlude 5
	{
		int result = last.compareTo(other.last);

		// If last names are equal, check first names
		if (result == 0)
			result = first.compareTo(other.first);

		return result;
	} 

	public boolean equals(Object other) {
		boolean result;

		if ((other == null) || (getClass() != other.getClass()))
			result = false;
		else {
			Name otherName = (Name) other;
			result = first.equals(otherName.first) && last.equals(otherName.last);
		} 

		return result;
	} 
} 
