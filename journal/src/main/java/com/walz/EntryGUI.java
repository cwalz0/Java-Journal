package com.walz;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class EntryGUI {

    private BorderPane rootLayout = new BorderPane();
    private Label gratitudeHeader = new Label();
    private TextArea gratitudeInput = new TextArea();
    private TextArea tasksInput = new TextArea();
    private TextArea thoughtsInput = new TextArea();
    private Button saveBtn = new Button("\uD83d\uDCBE");
    private Button cancelBtn = new Button("\u2716");

    private VBox gratitudeArea = new VBox();

    public EntryGUI() {
        VBox vboxLeft = new VBox();
        vboxLeft.setSpacing(15);

        Label tasksHeader = new Label("Tasks");
        tasksHeader.setStyle("-fx-font-family: 'Tahoma'; "
                           + "-fx-font-weight: bold; "
                           + "-fx-font-size: 22;");
                                   
        gratitudeHeader.setText("Gratitudes");
        
        gratitudeHeader.setStyle("-fx-font-family: 'Tahoma'; "
                           + "-fx-font-weight: bold; "
                           + "-fx-font-size: 22;");       
        
        vboxLeft.getChildren().addAll(gratitudeHeader, gratitudeArea, tasksHeader, tasksInput);

        VBox vboxRight = new VBox();
        vboxRight.setSpacing(15);

        Label thoughtsHeader = new Label("Thoughts");

        thoughtsHeader.setStyle("-fx-font-family: 'Tahoma'; "
                           + "-fx-font-weight: bold; "
                           + "-fx-font-size: 22;");

        vboxRight.getChildren().addAll(thoughtsHeader, thoughtsInput);

        
        tasksInput.setWrapText(true);
        thoughtsInput.setWrapText(true);

        VBox.setVgrow(tasksInput, Priority.ALWAYS);
        VBox.setVgrow(thoughtsInput, Priority.ALWAYS);
        

        HBox hboxCenter = new HBox();
        hboxCenter.setSpacing(10);

        hboxCenter.getChildren().addAll(vboxLeft, vboxRight);
        HBox.setHgrow(vboxLeft, Priority.ALWAYS);
        HBox.setHgrow(vboxRight, Priority.ALWAYS);
        
        HBox hboxBtns = new HBox();

        hboxBtns.setSpacing(10);
        hboxBtns.setAlignment(Pos.CENTER);
        hboxBtns.getChildren().addAll(saveBtn, cancelBtn);

        hboxBtns.setPadding(new Insets(20, 0, 0, 0));

        rootLayout.setCenter(hboxCenter);
        rootLayout.setBottom(hboxBtns);
        
        rootLayout.setPadding(new Insets(20));
    }

    public BorderPane getLayout() {
        return rootLayout;
    }

    public Label getGratitudeHeader() {
        return gratitudeHeader;
    }

    public VBox getGratitudeArea() {
        return gratitudeArea;
    }

    public TextArea getGratitudeInput() {
        return gratitudeInput;
    }

    public TextArea getTasksInput() {
        return tasksInput;
    }

    public TextArea getThoughtsInput() {
        return thoughtsInput;
    }

    public Button getSaveBtn() {
        return saveBtn;
    }

    public Button getCancelBtn() {
        return cancelBtn;
    }

}
