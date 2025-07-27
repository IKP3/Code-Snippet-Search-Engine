package com.igor.codesnippet.codesnippetsearchengine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class FileScanner {
  /**
   * Scans a list of file paths and counts how many times a specified keyword appears in each file.
   *
   * @param filePaths List of absolute file paths to scan
   * @param keyword The word to search for within each file
   * @return A HashMap mapping each file name to the number of times the keyword appears in it
   */
  public static HashMap<String, Integer> scanFiles(List<String> filePaths, String keyword){
    int counter;
    HashMap<String, Integer> fileWordCount = new HashMap<>();
    for(String filePath : filePaths){
      counter = 0;
      File file = new File(filePath);
      try(Scanner myReader = new Scanner(file)){
        // Read each line in the file
        while (myReader.hasNextLine()) {
          String line = myReader.nextLine();
          // Check if the line contains the keyword
          if (line.contains(keyword)) {
            counter++;
          }
        }
      }catch (FileNotFoundException e) {
        System.out.println("File not found: " + filePath);
      }
      // Store the file name and count if the keyword was found
      if(counter > 0){
        fileWordCount.put(file.getName(), counter);
      }
    }
    return fileWordCount;
  }
}
