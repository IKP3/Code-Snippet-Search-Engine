package com.igor.codesnippet.codesnippetsearchengine;

import java.io.File;
import java.util.HashMap;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

public class MainController {
  @FXML
  private TextField fileExplorerField;
  @FXML
  private TextField keywordField;
  @FXML
  private TableView<TableRowEntry> resultTable;
  @FXML
  private TableColumn<TableRowEntry, String> fileColumn;
  @FXML
  private TableColumn<TableRowEntry, Integer> countColumn;

  /*Initializes the TableView columns with property mappings.*/
  @FXML
  public void initialize() {
    fileColumn.setCellValueFactory(rowData -> new ReadOnlyStringWrapper(rowData.getValue().getFileName()));
    countColumn.setCellValueFactory(rowData -> new ReadOnlyObjectWrapper<>(rowData.getValue().getKeywordCount()));
  }
  /**
   * Called when the search button is clicked.
   * Retrieves the folder path and keyword, scans files, and updates the TableView.
   */
  @FXML
  protected void searchButtonClick() {
    String filepath = fileExplorerField.getText();
    String keyword = keywordField.getText();
    if(keywordField.getText().isEmpty()) {
      return;
    }

    HashMap<String, Integer> result = FileScanner.scanFiles(FilePathExplorer.getFileNames(filepath), keyword);
    ObservableList<TableRowEntry> data = FXCollections.observableArrayList();

    for (HashMap.Entry<String, Integer> entry : result.entrySet()) {
      data.add(new TableRowEntry(entry.getKey(), entry.getValue()));
    }

    resultTable.setItems(data);
  }
  /**
   * Called when the fileExplorerField is clicked.
   * Opens a folder selection dialog and updates the field with the selected folder path.
   */
  @FXML
  protected void openSelectDirectory(){
    DirectoryChooser directoryChooser = new DirectoryChooser();
    directoryChooser.setTitle("Select Folder to Scan");

    File selectedDirectory = directoryChooser.showDialog(null);

    if (selectedDirectory != null) {
      fileExplorerField.setText(selectedDirectory.getAbsolutePath());
    }
  }
}