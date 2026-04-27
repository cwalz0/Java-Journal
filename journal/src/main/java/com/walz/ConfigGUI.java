package com.walz;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;

public class ConfigGUI {

    private GridPane rootLayout = new GridPane();
    private ColorPicker fgColorPicker = new ColorPicker();
    private ColorPicker bgColorPicker = new ColorPicker();
    private ColorPicker accentColorPicker = new ColorPicker();
    private TextArea promptList = new TextArea();
    private Spinner<Integer> promptCountSpinner = new Spinner<>(1,6,1);
    private TextField saveDir = new TextField();
    private Button browseBtn = new Button("\uD83D\uDCC2");
    private Button saveBtn = new Button("\uD83d\uDCBE");
    private Button cancelBtn = new Button("\u2716");


    public ConfigGUI(Config config) {

        // todo: set gridpane padding and hgap/vgap
        rootLayout.setAlignment(Pos.CENTER);
        rootLayout.setPadding(new Insets(50));
        rootLayout.setHgap(15);
        rootLayout.setVgap(15);
        // todo: add labels for foreground, background and save location
        Label fgLabel = new Label("Text Color: ");
        Label bgLabel = new Label("Background Color: ");
        Label accentLabel = new Label("Accent Color: ");


        Label promptLabel = new Label("Gratitude Prompt(s): ");
        
        Label promptCountLabel = new Label("Prompt Count: ");

        Label entrySaveLocationLabel = new Label("Entry Save Location: ");
        saveDir.setEditable(false);
        
        ColumnConstraints col0 = new ColumnConstraints(); //right align labels
        col0.setHalignment(HPos.RIGHT); 

        ColumnConstraints col1 = new ColumnConstraints(); //left align inputs
        col1.setHalignment(HPos.LEFT);

        ColumnConstraints col2 = new ColumnConstraints(); //left align button
        col2.setHalignment(HPos.LEFT);

        rootLayout.getColumnConstraints().addAll(col0, col1, col2); // apply rules to grid


        // todo: arrange color pickers and savedir textfield in the grid
        rootLayout.add(fgLabel, 0,0);
        rootLayout.add(fgColorPicker, 1,0);

        rootLayout.add(bgLabel, 0,1);
        rootLayout.add(bgColorPicker, 1,1);
        
        rootLayout.add(accentLabel, 0,2);
        rootLayout.add(accentColorPicker, 1,2);

        rootLayout.add(promptLabel, 0,3);
        rootLayout.add(promptList, 1,3);
        // GridPane.setColumnSpan(promptList, 2);
        // GridPane.setVgrow(promptList, Priority.ALWAYS);

        rootLayout.add(promptCountLabel, 0,4);
        rootLayout.add(promptCountSpinner, 1,4);

        promptList.setPrefRowCount(6);
        promptList.setMaxWidth(350);
        promptList.setWrapText(true);

        // todo: place browseBtn next to the saveDir field
        rootLayout.add(entrySaveLocationLabel, 0,5);
        
        HBox pathBox = new HBox(10);
        pathBox.setAlignment(Pos.CENTER_LEFT);
        pathBox.getChildren().addAll(browseBtn, saveDir);
        rootLayout.add(pathBox, 1, 5);

        browseBtn.setMinWidth(Button.USE_PREF_SIZE);
        GridPane.setColumnSpan(pathBox, 2);

        HBox.setHgrow(saveDir, Priority.ALWAYS);
        saveDir.setMaxWidth(350);

        HBox hboxBtns = new HBox();
        hboxBtns.setSpacing(10);
        hboxBtns.setAlignment(Pos.CENTER);
        hboxBtns.getChildren().addAll(saveBtn, cancelBtn);

        rootLayout.add(hboxBtns, 0, 6);
        GridPane.setColumnSpan(hboxBtns,3);

        loadConfigData(config);
    }

    public void loadConfigData(Config config){
        Color fgColor = Color.web(config.getFg());
        Color bgColor = Color.web(config.getBg());
        Color accentColor = Color.web(config.getAccent());

        int promptCount = config.getGratitudeCount();
        promptCountSpinner.getValueFactory().setValue(promptCount);


        fgColorPicker.setValue(fgColor);
        bgColorPicker.setValue(bgColor);
        accentColorPicker.setValue(accentColor);

        


        saveDir.setText(config.getSaveDir());

        String joinPrompts = String.join("\n", config.getGratitudePrompts());
        promptList.setText(joinPrompts);
    }

    public GridPane getLayout() {
        return rootLayout;
    }

    public ColorPicker getFgColorPicker() {
        return fgColorPicker;
    }

    public ColorPicker getBgColorPicker() {
        return bgColorPicker;
    }

    public ColorPicker getAccentColorPicker() {
        return accentColorPicker;
    }

    public TextArea getPromptList() {
        return promptList;
    }

    public Spinner<Integer> getPromptCount() {

        return promptCountSpinner;
    }

    public TextField getSaveDir() {
        return saveDir;
    }

    public Button getBrowseBtn() {
        return browseBtn;
    }

    public Button getSaveBtn() {
        return saveBtn;
    }

    public Button getCancelBtn() {
        return cancelBtn;
    }

}
