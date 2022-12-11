package com.example.virusspread;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class VirusController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}