/**
 * Contains some helper methods used by both AuthorBibCreator.class and Article.class
 */
public class Helper {

	/**
	 * Appends an element to an array and return that array
	 * @param stringArray a String array containing the original array
	 * @param toAppend a String containing the element to be appended
	 * @return a String array with the element appended
	 */
	public static String [] appendToArray(String[] stringArray, String toAppend){
		// Creating an array with one more element
		String [] newArray = new String[stringArray.length + 1];

		// Copying previous elements reference into new array (Shallow copy)
		int index = 0;
		for (; index < stringArray.length; index++){
			newArray[index] = stringArray[index];
		}

		// when it reaches here, index is at stringArray max index + 1
		newArray[index] = toAppend;

		// return the array
		return newArray;
	}
}
