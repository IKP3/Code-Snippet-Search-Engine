module com.igor.codesnippet.codesnippetsearchengine {
  requires javafx.controls;
  requires javafx.fxml;

  opens com.igor.codesnippet.codesnippetsearchengine to javafx.fxml;
  exports com.igor.codesnippet.codesnippetsearchengine;
}