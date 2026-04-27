package com.walz;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class JournalApp extends Application {

    private Stage primaryStage;
    private StorageManager storage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        this.storage = new StorageManager();

        //load font (reg and bold)
        Font.loadFont(getClass().getResourceAsStream("/fonts/tahoma.ttf"), 14);

        Font.loadFont(getClass().getResourceAsStream("/fonts/tahomabd.ttf"), 14);

        MainGUI view = new MainGUI();
        new MainController(view, this, this.storage);

        applyTheme(view.getLayout());

        Scene scene = new Scene(view.getLayout(), 800, 600);
        
        Image icon = new Image(getClass().getResourceAsStream("/icons/icons8-notepad-96.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Journal.");
        primaryStage.setResizable(false);
    
        primaryStage.show();

    }

    public void switchScene(Parent newLayout) {
        applyTheme(newLayout);
        if (primaryStage.getScene() != null) {
            primaryStage.getScene().setRoot(newLayout);
        } else {
            primaryStage.setScene(new Scene(newLayout, 800, 600));
        }
    }

    public void applyTheme(Parent rootLayout) {
        Config currentConfig = this.storage.loadConfig();
        String fgColor = currentConfig.getFg();
        String bgColor = currentConfig.getBg();
        String accentColor = currentConfig.getAccent();

        Color originalBgColor = Color.web(bgColor);
        Color originalAccentColor = Color.web(bgColor);

        String lighterBgHex = originalBgColor.brighter().toString().replace("0x", "#");
        String darkerAccentHex = originalAccentColor.darker().toString().replace("0x", "#");

        String style = "-fx-text-background-color: " + fgColor + "; " // all text
                     + "-fx-text-inner-color: " + fgColor + "; " // textbox text color
                     + "-fx-background-color: " + bgColor + "; " // application background color
                     + "-fx-control-inner-background: " + lighterBgHex + "; " // text box background color
                     + "-fx-accent: " + accentColor + "; " // accent
                     + "-fx-base: " + accentColor + "; " // accent
                     + "-fx-focus-color: " + darkerAccentHex + "; " // focused item color
                     + "-fx-font-family: 'Tahoma'; "
                     + "-fx-font-size: 14px;";
                     
        rootLayout.setStyle(style); 
    }
    public static void main(String[] args) {
        launch(args);
    }
}