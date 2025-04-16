package com.example.flashcard;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Filelister {
    public static List<String> getFileNames(String folderPath) {
        List<String> fileNames = new ArrayList<>();
        File folder = new File(folderPath);

        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {

                    fileNames.add(file.getName().replace(".txt", ""));
                }
            }
        }

        return fileNames;
    }
    public static void addFile(String folderPath, String fileName, String fileContent) {
        try{
            File file = new File(folderPath+"/"+fileName+".txt");
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(fileContent);
            fileWriter.close();
        }
        catch (IOException e) {
            System.out.println("Failed to save file: " + e.getMessage());
        }
    }
}
