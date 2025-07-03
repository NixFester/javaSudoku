module com.abicand.sudoku {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.abicand.sudoku to javafx.fxml;
    exports com.abicand.sudoku;
}
