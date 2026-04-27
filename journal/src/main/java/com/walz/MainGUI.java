package com.walz;

import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainGUI {
    private VBox rootLayout = new VBox();
    private Label title = new Label("Journal.");
    private Button newEntryBtn = new Button("New Entry");
    private Button configBtn = new Button("Config");

    public MainGUI() {
        rootLayout.setAlignment(Pos.CENTER);
        rootLayout.setSpacing(15);

        title.setStyle("-fx-font-family: 'Tahoma'; "
                     + "-fx-font-weight: bold; "
                     + "-fx-font-size: 72;");

        rootLayout.getChildren().addAll(title, newEntryBtn, configBtn);
    }

    public VBox getLayout() {
        return rootLayout;
    }

    public Button getNewEntryBtn() {
        return newEntryBtn;
    }

    public Button getConfigBtn() {
        return configBtn;
    }
}
