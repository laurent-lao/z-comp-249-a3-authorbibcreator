public class FileExistsException extends Exception {

	public FileExistsException(){
		super("Exception: There is already an existing file for that author. File will be renamed as BU, and older BU files will be deleted!");
	}

	public FileExistsException(String message){
		super(message);
	}

	// Class tester
	//public static void main(String[] args)
	//{
	//	FileExistsException e = new FileExistsException();
	//	FileExistsException b = new FileExistsException("Test");
	//
	//	System.out.println(e.getMessage());
	//	System.out.println(b.getMessage());
	//}
}
