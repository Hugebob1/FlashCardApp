package com.example.flashcard;

import java.io.File;
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
}
