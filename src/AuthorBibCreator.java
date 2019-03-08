import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AuthorBibCreator {

	public static void main(String[] args) {

		// Variables
		int       scannerCount       = 0;                           // Stores how many scanners were opened
		int       writerCount        = 0;                           // Stores how many writerIndex were opened
		Scanner[] readFiles          = new Scanner[10];             // Stores the scanner for reach
		boolean   deleteCreatedFiles = false;						// Whether the program should delete created files
		final String[] inputFiles = new String[] {"Latex1.bib", 	// Stores the files to be read
												  "Latex2.bib",
												  "Latex3.bib",
												  "Latex4.bib",
												  "Latex5.bib",
												  "Latex6.bib",
												  "Latex7.bib",
												  "Latex8.bib",
												  "Latex9.bib",
												  "Latex10.bib"};

		displayWelcomeMessage();

		// TODO Prompt for an author's name
		String authorName = promptUserForAuthorName();
		String [] writeFiles = new String[]{authorName+"-IEEE", authorName+"-ACM", authorName+"-NJ"};
		String writeFileExtension = ".json";
		// TODO Attempt to open all 10 input files
		for (int i = 0; i < inputFiles.length; i++)
		{
			try
			{
				readFiles[i] = new Scanner(new FileInputStream(inputFiles[i]));
				scannerCount++;
			} catch (FileNotFoundException e)
			{
				System.out.println(e.getMessage() + "\nFile" + inputFiles[i] + " not found.");
				break;
			}
		}

		// Only run if all Scanners were successfully opened
		if (scannerCount == inputFiles.length)
		{
			// TODO Attempt to create all 3 writing files

			// TODO Find delimiters for Strings

		}

		// TODO Close Writers

		// Close Scanner
		for (int i = 0; i < scannerCount; i++)
		{
			readFiles[i].close();
		}

		displayExitMessage();

	}

	public static void processBibFiles() {
	}

	public static String promptUserForAuthorName(){
		Scanner kb = new Scanner(System.in);
		System.out.println("Please enter the author name you are targeting: ");

		return kb.nextLine();
	}

	public static void displayWelcomeMessage(){
		System.out.println("Welcome to Laurent's BibCreator!\n");
	}

	public static void displayExitMessage(){
		System.out.println("\nGoodbye! Hope you have enjoyed creating the needed files using AuthorBibCreator.");
	}
}
