module pl.kosieradzki.dw {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens pl.kosieradzki.dw to javafx.fxml;
    exports pl.kosieradzki.dw;
}