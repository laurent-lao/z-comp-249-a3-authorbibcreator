# Documentation

## Assignment Specification Summary

* The tool requires the user to enter an author name
* The tool then searches all .bib files for any articles for which that name is an author
* The tool generates 3 different files in the reference formats for IEEE, ACM and NJ with the records of the articles found with that name
    * These files must be named in this format (although they are json files, they simply contain the reference in the specified format):
        * author-name-IEEE.json
        * author-name-ACM.json
        * author-name-NJ.json
   
## Assignment Specification Details

* 10 .bib files are provided containing zero or more articles
* If when generating author-name-FORMAT.json files, they already exist, these files should be renamed with suffix -BU.json (and if any BU files exist, they are deleted)

 1. Assumptions
    1. Each files may have zero, or more articles
    2. Each articles starts with @ARTICLE followed by the body of the article enclosed between { }
    3. Every body of articles's fields always start with the field name followed by "=" and then { }, 
    4. There is a DOI field
    5. The order of the fields is unimportant
    6. Some extra empty lines may be in these files
    7. Some authors may have the same family name, some authors may have multiple articles
2. The FileExistsException class
    1. It must have a constructor for a default message "Exception: There is already an existing file for that author. File will be renamed as with suffix -BU, and older -BU files will be deleted!"
    2. It must have a parametized constructor for a specified message
3.  Entering author name
    1. Require the user to enter an author name, then attempt to open all 10 input files for reading
    2. If "any" of these files does not exist, the program must display an error message
    3. These files must close all opened files before exiting the program.
4. After all 10 input files are opened
    1. Check if any files already exists and throw FileExistsException
        * Handling these exceptions:
            1. First find if any other files with the names -BU.json, if so, delete these files
            2. Using PrintWriter to open the 3 output files (author-name-FORMAT.json)
                * If "any" of these output files cannot be created
                    1. Display a message to the user indicating which file could not be opened/created
                    2. Delete all other created output files (if any)
                    3. Close all opened input files; then exist the program
5. The processBibFiles method
    * This method cannot declare an exception
        * The method should work on the already opened files
        * The method must process each of the files to detect all articles that belong to any user with the name being searched
        * For all found articles, the method must create the proper records in each of the 3 formats (IEEE, ACM and NJ) and store the records in the correct output files
        * Once all input files have been processed, and the records are stored in the output files, the tool terminates.
6. Others
    * The program has several different behaviours depending if
        * A file already exists for the given author name
        * There are no records for the author
    * Can use StringTokenizer
    * Should minimize opening and closing the files as much as possible
    