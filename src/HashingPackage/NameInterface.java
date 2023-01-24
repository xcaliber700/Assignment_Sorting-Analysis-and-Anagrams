package HashingPackage;

/**
   An interface for a class of names.
   Listing P-2 in Segment P.15 of the Prelude.
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public interface NameInterface
{
   /** Sets the first and last names.
       @param firstName  A string that is the desired first name.
       @param lastName   A string that is the desired last name. */
   public void setName(String firstName, String lastName);
   
   /** Gets the full name.
       @return  A string containing the first and last names. */
   public String getName();
   
   /** Sets the first name.
       @param firstName  A string that is the desired first name. */
   public void setFirst(String firstName);
   
   /** Gets the first name.
       @return  A string containing the first name. */
   public String getFirst();
   
   /** Sets the last name.
       @param lastName  A string that is the desired last name. */
   public void setLast(String lastName);
   
   /** Gets the last name.
       @return  A string containing the last name. */
   public String getLast();
   
   /** Changes the last name of the given Name object to the last name of this Name object.
       @param aName  A given Name object whose last name is to be changed. */
   public void giveLastNameTo(NameInterface aName);
   
   /** Gets the full name.
       @return  A string containing the first and last names. */
   public String toString();
} 
