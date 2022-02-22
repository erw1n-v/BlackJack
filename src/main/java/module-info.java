module com.blackjack.blackjack {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.blackjack.blackjack to javafx.fxml;
    exports com.blackjack.blackjack;
}