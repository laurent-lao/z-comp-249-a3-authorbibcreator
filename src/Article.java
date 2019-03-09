import java.util.StringTokenizer;

/**
 * Article class that contains the information of a reference
 */
public class Article {

	private String[] authors;
	private String   journal;
	private String   title;
	private String   year;
	private String   volume;
	private String   number;
	private String   pages;
	private String   doi;
	private String   issn;
	private String   month;
	private String   keywords;

	/**
	 * Default constructor for Article -> Notifies the user that it ran as default
	 */
	public Article() {
		System.out.println("NOTE: The default constructor has been triggered. All Article attributes are null.");
	}

	/**
	 * Parameterized constructor for Article
	 *
	 * @param jsonData a String representing the JSON data of an article (in curly braces after @ARTICLE)
	 */
	public Article(String jsonData) {
		// use helper method to initialize the Article
		parseInput(jsonData);
	}

	/**
	 * Helper method to initialize Article attributes
	 *
	 * @param stringToParse a String representing the data of an article (in curly braces after @ARTICLE)
	 */
	private void parseInput(String stringToParse) {

		// Creating a StringTokenizer separated by ","
		StringTokenizer dataToParse = new StringTokenizer(stringToParse, ",");

		// While there are more tokens
		while (dataToParse.hasMoreTokens())
		{
			// Get the Next Token and put it into an array to be decrypted
			String   jsonElement          = dataToParse.nextToken();
			String[] jsonAttributeAndData = separateJsonAttributeFromData(jsonElement);
			if (jsonAttributeAndData != null)
			{
				if (jsonAttributeAndData[0].contains("author"))
				{
					authors = parseAuthor(jsonAttributeAndData[1]);
				}
				else if (jsonAttributeAndData[0].contains("journal"))
				{
					journal = jsonAttributeAndData[1];
				}
				else if (jsonAttributeAndData[0].contains("title"))
				{
					title = jsonAttributeAndData[1];
				}
				else if (jsonAttributeAndData[0].contains("year"))
				{
					year = jsonAttributeAndData[1];
				}
				else if (jsonAttributeAndData[0].contains("volume"))
				{
					volume = jsonAttributeAndData[1];
				}
				else if (jsonAttributeAndData[0].contains("number"))
				{
					number = jsonAttributeAndData[1];
				}
				else if (jsonAttributeAndData[0].contains("pages"))
				{
					pages = jsonAttributeAndData[1];
				}
				else if (jsonAttributeAndData[0].contains("doi"))
				{
					doi = jsonAttributeAndData[1];
				}
				else if (jsonAttributeAndData[0].contains("ISSN"))
				{
					issn = jsonAttributeAndData[1];
				}
				else if (jsonAttributeAndData[0].contains("month"))
				{
					month = jsonAttributeAndData[1];
				}
				else if (jsonAttributeAndData[0].contains("keywords"))
				{
					keywords = jsonAttributeAndData[1];
				}
			}
		}
	}

	/**
	 * Separates JSON Attribute from its Data
	 *
	 * @param jsonElements
	 *
	 * @return A string array containing the separated Attribute from Data
	 */
	private String[] separateJsonAttributeFromData(String jsonElements) {
		String[]        separated   = new String[2];
		StringTokenizer jsonElement = new StringTokenizer(jsonElements, "=");

		separated[0] = cleanInput(jsonElement.nextToken());

		try
		{
			separated[1] = cleanInput(jsonElement.nextToken());
		} catch (Exception e)
		{
			separated = null;
		}

		return separated;
	}

	/**
	 * Clean up superficial characters from string
	 *
	 * @param input a String to be cleaned
	 *
	 * @return cleaned-up String
	 */
	private String cleanInput(String input) {

		String cleanedUp   = "";
		char[] toBeCleaned = input.toCharArray();

		for (int i = 0; i < toBeCleaned.length; i++)
		{
			char charToCheck = toBeCleaned[i];

			// Remove curly braces
			boolean isCurlyBraces = charToCheck == '{' || charToCheck == '}';
			if (!isCurlyBraces)
			{
				// Only add the character to the string if it is one of the accepted characters
				cleanedUp += charToCheck;
			}
		}

		return cleanedUp;
	}

	/**
	 * Helper method to parse Authors into an array
	 *
	 * @param authors a String representing all the authors
	 *
	 * @return a String array containing the authors separated
	 */
	private String[] parseAuthor(String authors) {

		String[]        separatedAuthor = new String[0];
		StringTokenizer readAuthors     = new StringTokenizer(authors);

		while (readAuthors.hasMoreTokens())
		{
			String newAuthor = readAuthors.nextToken();

			// Capitalize firstLetter
			String firstLetter   = newAuthor.substring(0, 1).toUpperCase();
			String restOfLetters = newAuthor.substring(1);
			newAuthor = firstLetter + restOfLetters;

			// Append the next strings to "newAuthor" until it hits an "and"
			while (readAuthors.hasMoreTokens())
			{
				String nextString = readAuthors.nextToken();

				// Capitalize first letter
				firstLetter = nextString.substring(0, 1).toUpperCase();
				restOfLetters = nextString.substring(1);
				nextString = firstLetter + restOfLetters;

				if (nextString.equalsIgnoreCase("and"))
				{
					break;
				}
				else
				{
					newAuthor += " " + nextString;
				}
			}

			// Append this newAuthor into the separatedAuthor array
			separatedAuthor = Helper.appendToStringArray(separatedAuthor, newAuthor);

		}

		return separatedAuthor;
	}

	/**
	 * Checks whether a string is matches "authors" attribute
	 * @param authorToFind a String representing the search term
	 * @return a bool representing whether the string was found or not
	 */
	public boolean authorMatches(String authorToFind) {
		for (int i = 0; i < authors.length; i++)
		{
			if (authors[i].contains(authorToFind))
			{
				return true;
			}
		}

		// When it hits here, it was not found.
		return false;
	}

	/**
	 * Get the IEEE citation format
	 * @return a String containing the IEEE citation format for the article
	 */
	public String getIEEEformat() {
		String ieeeFormat = "";

		// Formatting authors
		String authorToString = "";
		for (int i = 0; i < authors.length; i++)
		{
			if (i < (authors.length - 1))
			{
				authorToString += authors[i] + ", ";
			}
			else
			{
				authorToString += authorToString + ".";
			}
		}
		// Add all information
		ieeeFormat += authorToString + " " + "\"" + title + "\"" + ", " + journal + ", " + "vol. " + volume + ", no. " + number + ", p. " + pages + ", " + month + " " + year;

		return ieeeFormat;
	}

	/**
	 * Get the NJ citation format
	 * @return a String containing the NJ citation format for the article
	 */
	public String getNJformat() {
		String njFormat = "";

		// Formatting authors
		String authorToString = "";
		for (int i = 0; i < authors.length; i++)
		{
			if (i < (authors.length - 1))
			{
				authorToString += authors[i] + " & ";
			}
			else
			{
				authorToString += authorToString + ".";
			}
		}
		njFormat += authorToString + ". " + title + ". " + journal + ". " + volume + ", " + pages + "(" + year + ").";
		return njFormat;
	}

	/**
	 * Get the ACM citation format
	 * @return a String containing the ACM citation format for the article
	 */
	public String getACMformat() {
		String acmFormat = "";

		// Formatting authors
		String authorToString = "";
		authorToString += authors[0] + " et al.";

		// Add all information
		acmFormat += authorToString + " " + year + ". " + title + ". " + journal + ". " + volume + ", " + number + " (" + year + "), " + pages + ". DOI: https://doi.org/" + doi + ".";
		return acmFormat;
	}

	/**
	 * Basic to String
	 * @return a String printing the attributes of the object
	 */
	public String toString() {

		String authorToString = "";
		for (int i = 0; i < authors.length; i++)
		{
			if (i != (authors.length - 1))
			{
				authorToString += authors[i] + ", ";
			}
			else
			{
				authorToString += authors[i];
			}
		}

		return "Article{" +
				"authors=" + authorToString +
				", journal='" + journal + '\'' +
				", title='" + title + '\'' +
				", year='" + year + '\'' +
				", volume='" + volume + '\'' +
				", number='" + number + '\'' +
				", pages='" + pages + '\'' +
				", doi='" + doi + '\'' +
				", issn='" + issn + '\'' +
				", month='" + month + '\'' +
				", keywords='" + keywords + '\'' +
				'}';
	}
}
