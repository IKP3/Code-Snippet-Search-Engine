package com.igor.codesnippet.codesnippetsearchengine;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

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

  @FXML
  public void initialize() {
    fileColumn.setCellValueFactory(rowData -> new ReadOnlyStringWrapper(rowData.getValue().getFileName()));
    countColumn.setCellValueFactory(rowData -> new ReadOnlyObjectWrapper<>(rowData.getValue().getKeywordCount()));
  }
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