package pl.edu.pw.ee;

import java.io.*;


public class FileUtils {

    public static void generateOutputFile(String fileName, byte[] content) throws IOException {
        if(fileName == null){
            throw new IllegalArgumentException("File name cannot be null");
        }
        FileOutputStream outputStream = new FileOutputStream(fileName);
        outputStream.write(content);
        outputStream.close();
    }


    public static String getFileNameFromFile(File file) {
        if(file == null){
            throw new IllegalArgumentException("File cannot be null");
        }
        String[] parts = file.getName().split("\\.");
        String fileName = "";
        for (int i = 0; i < parts.length - 1; i++) {
            fileName = fileName.concat(parts[i]);
        }
        return fileName;
    }

    public static File generateFile(File file, String content) throws IOException {
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(content);
        bw.close();
        return file;
    }



}
