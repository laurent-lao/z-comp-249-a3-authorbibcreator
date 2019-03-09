/**
 * Contains some helper methods used by both AuthorBibCreator.class and Article.class
 */
public class Helper {

	/**
	 * Appends a String to a String array and return that array
	 * @param stringArray a String array containing the original array
	 * @param toAppend a String containing the element to be appended
	 * @return a String array with the element appended
	 */
	public static String [] appendToStringArray(String[] stringArray, String toAppend){
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

	/**
	 * Appends an Article to an Article array and return that array
	 * @param articleArray an Article array containing the original array
	 * @param toAppend an Article containing the element to be appended
	 * @return an Article array with the element appended
	 */
	public static Article [] appendToArticleArray(Article [] articleArray, Article toAppend)
	{
		// Creating an array with one more element
		Article[] newArray = new Article[articleArray.length + 1];

		// Copying previous elements into new array (Shallow copy)
		int index = 0;
		for (; index < articleArray.length; index++)
		{
			newArray[index] = articleArray[index];
		}

		// when it reaches here, index is at articleArray max index + 1
		newArray[index] = toAppend;

		// return the array
		return newArray;
	}
}
