public class ArticleProcessingException extends Exception {

	private String[] authors;
	private String   journal;
	private String   title;
	private String   year;
	private String   volume;
	private String   number;
	private String   pages;
	private String   doi;
	private String   month;
	private Article  article;
	private String   jsonData;

	public ArticleProcessingException(Article a, String jsonData) {
		this.article = a;
		this.jsonData = jsonData;

		authors = article.getAuthors();
		journal = article.getJournal();
		title = article.getTitle();
		year = article.getYear();
		volume = article.getVolume();
		number = article.getNumber();
		pages = article.getPages();
		doi = article.getDoi();
		month = article.getMonth();
	}

	public String getMessage() {
		String message = "Exception occured while processing this article\n" +
				"jsonData: " + jsonData + "\n" +
				"Please verify that these tags are defined: \n";

		if (authors == null)
		{
			message += "Authors is null\n";
		}
		if (journal == null)
		{
			message += "Journal is null\n";
		}
		if (title == null)
		{
			message += "Title is null\n";
		}
		if (year == null)
		{
			message += "Year is null\n";
		}
		if (volume == null)
		{
			message += "Volume is null\n";
		}
		if (number == null)
		{
			message += "Number is null\n";
		}
		if (pages == null)
		{
			message += "Pages is null\n";
		}
		if (doi == null)
		{
			message += "DOI is null\n";
		}
		if (month == null)
		{
			message += "Month is null\n";
		}

		return message;
	}
}
