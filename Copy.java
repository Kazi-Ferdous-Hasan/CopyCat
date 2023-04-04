import java.io.*;
import java.util.*;

public class Copy {

    public static void main(String args[]) throws IOException {
        FileInputStream in = null;
        FileOutputStream out = null;
        File source = null;
        File dest = null;
        String filePath = null;
        //File progress = null;
        try {
            in = new FileInputStream(args[0]);
            source = new File(args[0]);
            dest = new File(args[1]);

            if (source.isFile()) {
                System.out.println("Source is a file.");
            } else {
                System.out.println("Source is not a file.");
            }

            if (isDir(dest)) {
                System.out.println("Destination is a directory.");
            } else {
                System.out.println("Destination is not a directory.");
            }
			System.out.println();
			
            if (isDir(dest)) {
                if (!dest.exists()) {
					System.out.println("(Given directory does not exists)");
					System.out.println("(Creating directory...)");
                    dest.mkdir();
					System.out.println("(Directory has been created)");
                }

                filePath = args[1] + "\\" + source.getName();
                File destinationFile = new File(filePath);

                if (destinationFile.exists()) {
                    int choice;
                    do {
                        System.out.println("(File already exists)");
                        System.out.println("Enter (1) to Keep both files.");
                        System.out.println("Enter (2) to Overwrite.");

                        Scanner sc = new Scanner(System.in);
                        choice = sc.nextInt();

                        if (choice == 1) {
                            String[] tokens = source.getName().split("\\.(?=[^\\.]+$)");
                            //String path = args[1].substring(0,args[1].lastIndexOf("\\"));
                            //System.out.println(path);
                            filePath = args[1] + "\\" + tokens[0] + "_copy" + "." + tokens[1];
                            File f = new File(filePath);
                            f.createNewFile();
                            copyFile(filePath, args[0]);
                            break;
                        } else if (choice == 2) {
                            filePath = args[1] + "\\" + source.getName();
                            File f = new File(filePath);
                            f.createNewFile();
                            copyFile(filePath, args[0]);
                            break;
                        }

                    } while (choice != 1 || choice != 2);

                } else {
                    destinationFile.createNewFile();

                    copyFile(filePath, args[0]);
                }
            } else {
                String path = args[1].substring(0, args[1].lastIndexOf("\\"));
                filePath = path + "\\" + source.getName();
                File f = new File(filePath);
                f.createNewFile();
                copyFile(filePath, args[0]);
            }

        } catch (IOException e) {
            System.out.println("Exception is: " + e);
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

    private static boolean isDir(File file) {
        if (!file.exists()) {
            return file.getName().lastIndexOf('.') == -1;
        } else {
            return file.isDirectory();
        }
    }

    public static void copyFile(String filePath, String sourceFilePath) throws IOException {
        FileOutputStream out = new FileOutputStream(filePath);
        FileInputStream in = new FileInputStream(sourceFilePath);
        File progress = new File(sourceFilePath);
        //File inf = new File(filePath);
        long size = progress.length();
        long bufferSize = size / 10;
        int byteSize = (int) bufferSize;

        System.out.println("(Size of the file is: " + size + " bytes)\n");
        //System.out.println("Size of file: "+bufferSize);
        //System.out.println("Path: "+filePath);
        byte buffer[] = new byte[byteSize];

        System.out.println("Copying on progress...\n");
        int i, j = 0;
        while ((i = in.read(buffer)) != -1) {
            System.out.println(j + "% copying done...");
            j += 10;
            out.write(buffer, 0, i);
        }
        System.out.println("\n(Successfully copied)\n");
    }
}
