public class Article {

	private String [] authors;
	private String journal;
	private String title;
	private String year;
	private String volume;
	private String number;
	private String pages;
	private String doi;
	private String Issn;
	private String month;
	private String keywords;

	/**
	 * Default constructor for Article -> Notifies the user that it ran as default
	 */
	public Article(){
		System.out.println("NOTE: The default constructor has been triggered. All Article attributes are null.");
	}

	/**
	 * Parameterized constructor for Article
	 * @param jsonData a String representing the JSON data of an article (in curly braces after @ARTICLE)
	 */
	public Article(String jsonData){
		// use helper method to initialize the Article
		parseInput(jsonData);
	}

	/**
	 * Class debugger
	 * @param args String for main arguments
	 */
	public static void main(String[] args){

	}

	/**
	 * Helper method to initialize Article attributes
	 * @param jsonData a String representing the JSON data of an article (in curly braces after @ARTICLE)
	 */
	private void parseInput(String jsonData){

	}

	/**
	 * Helper method to parse Authors into an array
	 * @param author
	 * @return
	 */
	private String [] parseAuthor(String author){
		String [] tempAuthor = new String[0];

		// TODO algorithm to parseAuthor
		String newAuthor = "";
		tempAuthor = Helper.appendToArray(tempAuthor, newAuthor);

		return tempAuthor;
	}
}
