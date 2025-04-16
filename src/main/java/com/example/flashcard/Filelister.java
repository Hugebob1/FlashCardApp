package com.example.flashcard;

import java.io.*;
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
        try {
            File file = new File(folderPath + "/" + fileName + ".txt");

            // Upewniamy się, że folder istnieje
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            // Tworzymy plik
            file.createNewFile();

            // Zapisujemy treść
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(fileContent);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Failed to save file: " + e.getMessage());
        }
    }

    public static void removeFile(String folderPath, String fileName) {
        try {
            File file = new File(folderPath+"/"+fileName+".txt");
            if(file.delete()){
                System.out.println("File deleted successfully");
            }
        }catch (Exception e) {
            System.out.println("Failed to delete file: " + e.getMessage());
        }
    }
    public static String readFromFileToString(String folderPath, String fileName) throws IOException {
        File file = new File(folderPath + File.separator + fileName + ".txt");
        StringBuilder all = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                all.append(line).append("\n");
            }
        }
        return all.toString();
    }
    public static void writeToFile(String folderPath, String fileName, String all) throws IOException {
        File file = new File(folderPath+"/"+fileName+".txt");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write(all);
        bufferedWriter.close();
    }

}
