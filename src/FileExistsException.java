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
	 * @param author
	 */
	public FileExistsException(String author){
		super("Exception: There is already an existing file for that author. File will be renamed as " + author+"-BU.json, and older BU files will be deleted!");
	}

	 //Class tester
	public static void main(String[] args)
	{
		FileExistsException e = new FileExistsException();
		FileExistsException b = new FileExistsException("Test");

		System.out.println(e.getMessage());
		System.out.println(b.getMessage());
	}
}
