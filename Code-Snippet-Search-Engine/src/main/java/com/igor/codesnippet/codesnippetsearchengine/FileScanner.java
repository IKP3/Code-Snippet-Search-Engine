package com.igor.codesnippet.codesnippetsearchengine;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileScanner {

  private final List<String> directories;
  /**
   * Initializes the FileScanner with an empty list of directories.
   */
  public FileScanner() {
    directories = new ArrayList<>();
  }
  /**
   * Returns a set of absolute file paths in the specified directory.
   *
   * @param dir the directory to scan
   * @return a set of absolute file paths
   */
  private Set<String> getAllFilePaths(String dir) {
    File[] files = new File(dir).listFiles();
    if (files == null) return Set.of();

    return Stream.of(files)
        .filter(file -> !file.isDirectory())
        .map(File::getAbsolutePath)
        .collect(Collectors.toSet());
  }
  /**
   * Returns the absolute paths of immediate subdirectories within the given directory.
   *
   * @param dir the directory to scan
   * @return an array of absolute paths to subdirectories
   */
  private String[] listDirectories(String dir) {
    File[] directories = new File(dir).listFiles(File::isDirectory);
    if (directories == null) return new String[0];

    return Arrays.stream(directories)
        .map(File::getAbsolutePath)
        .toArray(String[]::new);
  }
  /**
   * Recursively explores all subdirectories starting from the given root directory.
   * Discovered directories are added to an internal list.
   *
   * @param dir the root directory to begin traversal
   */
  private void exploreDirectories(String dir) {
    for(String directory : listDirectories(dir)){
      directories.add(directory);
      exploreDirectories(directory);
    }
  }
  /**
   * Scans the given root directory and all its subdirectories for files,
   * and returns a list of their absolute paths.
   *
   * @param dir the root directory to scan
   * @return a list of absolute file paths from the entire directory tree
   */
  public List<String> getFileNames(String dir) {
    List<String> fileNames = new ArrayList<>();
    directories.clear();
    directories.add(dir);
    exploreDirectories(dir);
    for(String directory : directories){
      fileNames.addAll(getAllFilePaths(directory));
    }
    return fileNames;
  }
}
