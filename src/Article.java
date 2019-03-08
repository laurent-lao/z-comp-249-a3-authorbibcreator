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

	public Article(){
		System.out.println("NOTE: The default constructor has been triggered.");
	}


	public Article(String input){
		// use helper method to initialize the Article
		parseInput(input);
	}

	private void parseInput(String input){

	}

	private String [] parseAuthor(String author){

	}
}
