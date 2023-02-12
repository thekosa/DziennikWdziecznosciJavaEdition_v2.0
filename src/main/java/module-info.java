module pl.kosieradzki.dw {
    requires javafx.controls;
    requires javafx.fxml;


    opens pl.kosieradzki.dw to javafx.fxml;
    exports pl.kosieradzki.dw;
}