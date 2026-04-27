package com.walz;

public class MainController {

    private MainGUI view;
    private JournalApp app;
    private StorageManager storage;

    public MainController(MainGUI view, JournalApp app, StorageManager storage) {
        this.view = view;
        this.app = app;
        this.storage = storage;

        view.getNewEntryBtn().setOnAction(e -> openNewEntry());
        view.getConfigBtn().setOnAction(e -> openConfig());
    }

    public void openNewEntry() {
        EntryGUI entryView = new EntryGUI();
        new EntryController(entryView, app, storage);

        app.switchScene(entryView.getLayout());
    }

    public void openConfig() {
        Config loadedConfig = storage.loadConfig();
        ConfigGUI configView = new ConfigGUI(loadedConfig);
        ConfigController configController = new ConfigController(configView, app, storage);
   
        configController.loadCurrentSettings();
        
        app.switchScene(configView.getLayout());
    }
}
