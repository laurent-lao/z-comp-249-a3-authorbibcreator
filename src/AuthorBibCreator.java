import java.io.*;
import java.util.Scanner;

public class AuthorBibCreator {

	public static void main(String[] args) {

		// Variables
		int exitMessageNumber = 0;
		final String[] inputFiles = new String[] {"Latex1.bib",         // Stores the files to be read
												  "Latex2.bib",
												  "Latex3.bib",
												  "Latex4.bib",
												  "Latex5.bib",
												  "Latex6.bib",
												  "Latex7.bib",
												  "Latex8.bib",
												  "Latex9.bib",
												  "Latex10.bib"};

		// Display Welcome Message
		displayWelcomeMessage();

		// Prompt for an author's name
		String authorName = promptUserForAuthorName();

		// Prepares the program's output files name
		String[]      outputFileAuthorFormat = new String[] {authorName + "-IEEE", authorName + "-ACM", authorName + "-NJ"};
		String        outputFileExtension    = ".json";
		PrintWriter[] writeFiles             = new PrintWriter[outputFileAuthorFormat.length];        // Stores the writer for each json file
		File[]        outputFiles            = new File[outputFileAuthorFormat.length];            // Stores the file for each json file;
		File[]        backupFile             = new File[outputFileAuthorFormat.length];                // Stores the backup file for each json file;
		for (int i = 0; i < outputFileAuthorFormat.length; i++)
		{
			// Create File objects with author's name
			outputFiles[i] = new File(outputFileAuthorFormat[i] + outputFileExtension);
			String backupFileName = outputFileAuthorFormat[i] + "-BU" + outputFileExtension;
			backupFile[i] = new File(backupFileName);
		}

		// Attempt to open all 10 input files
		int       scannerCount = 0;                                // Stores how many scanners were opened
		Scanner[] readFiles    = new Scanner[inputFiles.length];    // Stores the scanner for each bib file
		for (int i = 0; i < inputFiles.length; i++)
		{
			try
			{
				readFiles[i] = new Scanner(new FileInputStream(inputFiles[i]));
				scannerCount++;
			} catch (FileNotFoundException e)
			{
				System.out.println("Could not open file " + inputFiles[i] + " for reading.");
				exitMessageNumber = 1;
				break;
			}
		}

		// Only run if all Scanners were successfully opened
		int     writerCount              = 0;                     // Stores how many writerIndex were opened
		boolean haveToDeleteCreatedFiles = false;                 // Whether the program should delete created files
		if (scannerCount == inputFiles.length)
		{
			for (int i = 0; i < outputFiles.length; i++)
			{
				try
				{
					if (outputFiles[i].exists())
					{
						throw new FileExistsException(outputFileAuthorFormat[i]);
					}
				} catch (FileExistsException f)
				{
					// The File already exists
					System.out.println(f.getMessage());

					// Creating backup file
					try
					{
						if (backupFile[i].exists())
						{
							// If there is already a backup file, delete it
							if (!backupFile[i].delete())
							{
								// Throw exception if unable to delete
								throw new Exception("File " + backupFile[i] + "unsuccessfully deleted.");
							}
						}

						// Rename the existing file into -BU
						if (!outputFiles[i].renameTo(backupFile[i]))
						{
							// Throw exception if unable to create backup file
							throw new Exception("Cannot create backup file");
						}
					} catch (Exception h)
					{
						System.out.println("Error: " + h.getMessage());
						haveToDeleteCreatedFiles = true;
						exitMessageNumber = 2;
						break;
					}
				} finally // run this afterwards
				{
					try
					{
						// Open the PrintWriter files
						writeFiles[i] = new PrintWriter(new FileOutputStream(outputFiles[i]));
						writerCount++;
					} catch (FileNotFoundException e)
					{
						// If unable to Printwriter files, delete files, notify user
						haveToDeleteCreatedFiles = true;
						System.out.println(e.getMessage() + "\nPrintWriter could not create " + outputFiles[i]);
						exitMessageNumber = 3;
						break;
					}
				}
			}

			// Only run if all PrintWriters were initialized correctly
			if (writerCount == outputFileAuthorFormat.length)
			{
				// Find the author among the citations
				if (!processBibFiles(readFiles, writeFiles, authorName))
				{
					// If author was not found in the articles
					System.out.println("\nNo records were found for author(s) with name: " + authorName);
					exitMessageNumber = 4;
				}
			}

		}

		// Debug
		//haveToDeleteCreatedFiles = true; // Use this to delete created files
		// Delete previously created files if flag is true
		if (haveToDeleteCreatedFiles)
		{
			System.out.println("Deleting created files.");

			for (int i = 0; i < outputFileAuthorFormat.length; i++)
			{
				if (outputFiles[i].exists())
				{
					outputFiles[i].delete();
				}

				if (backupFile[i].exists())
				{
					backupFile[i].delete();
				}
			}
		}

		// Close Writer
		for (int i = 0; i < writerCount; i++)
		{
			writeFiles[i].close();
			//System.out.println("Debug PrintWriter closer: Closing PrintWriter " + i);
		}

		// Close Scanner
		for (int i = 0; i < scannerCount; i++)
		{
			readFiles[i].close();
			//System.out.println("Debug Scanner closer: Closing Scanner " + i);
		}

		// Display Exit message
		displayExitMessage(exitMessageNumber);

	}

	/**
	 * Creates appropriate formatted references of a specific author's article in the .json files
	 *
	 * @param bibFiles    an array containing the Scanners of all bibFiles
	 * @param outputFiles an array containing the PrintWriter of each reference style
	 * @param author      a String containing the specific author's name
	 */
	public static boolean processBibFiles(Scanner[] bibFiles, PrintWriter[] outputFiles, String author) {

		boolean   foundAuthor     = false;
		int       numberOfRecords = 0;
		Article[] articles        = new Article[0];

		// Store the articles into a array of Article objects
		for (int i = 0; i < bibFiles.length; i++)
		{
			// Use @ARTICLE as the separator
			bibFiles[i].useDelimiter("@ARTICLE");

			while (bibFiles[i].hasNext())
			{
				try
				{
					String jsonData = bibFiles[i].next();

					// Make sure to use a valid jsonData (contains at least an equal sign somewhere)
					if (jsonData.contains("="))
					{
						// Create an Article object which parses the jsonData properly
						Article currentArticle = new Article(jsonData);
						articles = Helper.appendToArticleArray(articles, currentArticle);
					}

				} catch (Exception e)
				{
					// Ignore exception
					continue;
					// bibFiles[i].next();
				}
			}
		}

		// Check to see if there is a matching author
		PrintWriter ieee = outputFiles[0];
		PrintWriter acm = outputFiles[1];
		PrintWriter nj = outputFiles[2];
		for (int i = 0; i < articles.length; i++)
		{
			if (articles[i].authorMatches(author))
			{
				foundAuthor = true;
				numberOfRecords++;

				// Print to file
				ieee.println(articles[i].getIEEEformat() + "\n");
				acm.println("[" + numberOfRecords + "]\t\t" + articles[i].getACMformat() + "\n");
				nj.println(articles[i].getNJformat() + "\n");

			}
		}

		if (foundAuthor)
		{
			// Runs if author was found
			System.out.println("\nA total of " + numberOfRecords + " records were found for author(s) with name: " + author);
			System.out.println("Files " + author + "-IEEE.json, " + author + "-ACM.json, and " + author + "-NJ.json have been created!" + "\n\n");
		}
		return foundAuthor;
	}

	/**
	 * Prompts user for the author name they want to search for
	 *
	 * @return a String representing the author name the user has inputted
	 */
	public static String promptUserForAuthorName() {
		Scanner kb = new Scanner(System.in);
		System.out.print("Please enter the author name you are targeting (case sensitive): ");
		String authorName = kb.nextLine();
		System.out.print("\n");
		return authorName;
	}

	/**
	 * Displays a welcome message
	 */
	public static void displayWelcomeMessage() {
		System.out.println("Welcome to Laurent's BibCreator!\n");
	}

	/**
	 * Display an exit message
	 */
	public static void displayExitMessage(int exitMessageNumber) {
		switch (exitMessageNumber)
		{
			case 1:
				System.out.println("Please check if file exists! Program has closed any opened files and is terminating.");
				break;
			case 2:
				System.out.println("The backup files (-BU.json) are causing some problems. Please delete them manually. The Program has closed any opened files and is terminating.");
				break;
			case 3:
				System.out.println("There were some problems with writing into the output files. The Program has deleted and closed any opened files and is terminating.");
			case 4:
				System.out.println("No files have been created!\n");
			default:
				System.out.println("\nGoodbye! Hope you have enjoyed creating the needed files using AuthorBibCreator.");
				break;
		}

	}
}
