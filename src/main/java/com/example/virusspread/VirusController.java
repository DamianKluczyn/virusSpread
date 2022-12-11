package com.example.virusspread;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class VirusController {
    @FXML
    private Pane pane;
    @FXML
    private Button startButton;
    @FXML
    private Button stopButton;
    @FXML
    private Button stepButton;
    @FXML
    private CheckBox immunityBox;
    @FXML
    private ChoiceBox<Integer> snapshotBox;
    @FXML
    private TextField timeText;

    Simulation simulation;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}