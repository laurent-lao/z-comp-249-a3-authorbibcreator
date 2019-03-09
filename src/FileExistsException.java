/**
 * FileExistsException is thrown if the file already exists
 */
public class FileExistsException extends Exception {

	/**
	 * FileExistsException constructor with a default message
	 */
	public FileExistsException(){
		super("Exception: There is already an existing file for that author. File will be renamed as BU, and older BU files will be deleted!");
	}

	/**
	 * FileExistsException constructor with the name of the file
	 * @param filename String containing the author's name to be used for the name of the file
	 */
	public FileExistsException(String filename){
		super("\nA file already exists with the name: " + filename + ".json\n" +
				"File will be renamed as: " + filename + "-BU.json and any old BUs will be deleted.");
	}
}
