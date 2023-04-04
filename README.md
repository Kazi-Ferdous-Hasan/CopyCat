# CopyCat
A simple Java program that allows users to copy a file from one location to another. The program takes two command-line arguments, the source file and the destination file or directory. It checks if the source is a file or not and if the destination is a directory or a file using the isFile() and isDirectory() methods.

If the destination folder does not exist, the program creates a folder with the same name using mkdir() and copies the source file there. If the destination folder exists, the program prompts the user to choose between overwriting the existing file or keeping both files. If the destination is a file that does not exist, the program creates the file using createNewFile().

The program also displays the byte size of the source file and the copying progress in percentage. Finally, after successfully copying the file, the program displays a message indicating that the file was copied successfully.
