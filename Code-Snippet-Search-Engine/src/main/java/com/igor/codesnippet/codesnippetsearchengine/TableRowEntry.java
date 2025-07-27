package com.igor.codesnippet.codesnippetsearchengine;

public class TableRowEntry {
  private final String fileName;
  private final int keywordCount;

  public TableRowEntry(String fileName, int keywordCount) {
    this.fileName = fileName;
    this.keywordCount = keywordCount;
  }

  public String getFileName() {
    return fileName;
  }

  public int getKeywordCount() {
    return keywordCount;
  }
}

