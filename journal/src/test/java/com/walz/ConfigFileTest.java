package com.walz;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;


public class ConfigFileTest {

    @TempDir
    Path tempDir;
    
    @Test
    public void testSaveandLoadConfig(){
        File tempConfigFile = tempDir.resolve("test_config.json").toFile();

        StorageManager storage = new StorageManager(tempConfigFile.getAbsolutePath());
        Config configToSave = new Config();
        
        configToSave.setFg("#654321");
        configToSave.setBg("#123456");
        configToSave.setAccent("#660088");
        configToSave.setGratitudeCount(2);
        List<String> prompts = Arrays.asList("What's made you smile recently", "Where do you see yourself in 5 years", "What have you felt proud of recently");
        configToSave.setGratitudePrompts(prompts);
        configToSave.setSaveDir("\"JournalApp/new_entries/\"");

        boolean isSaved = storage.saveConfig(configToSave);

        assertTrue(isSaved, "StorageManager should return true when saving config successfully.");

        Config loadedConfig = storage.loadConfig();
        

        assertNotNull(loadedConfig, "Loaded config should not be null.");
        assertEquals("#654321", loadedConfig.getFg(), "Foreground color should match the saved value.");
        assertEquals("#123456", loadedConfig.getBg(), "Background color should match the saved value.");
        assertEquals("#660088", loadedConfig.getAccent(), "Accent should match the saved value.");
        assertEquals(2, loadedConfig.getGratitudeCount(), "Should set amount of prompts displayed to 2.");
        assertEquals(3, loadedConfig.getGratitudePrompts().size(), "Amount of prompts should be equal to the amount of prompts input (3)");
        assertEquals("\"JournalApp/new_entries/\"", loadedConfig.getSaveDir(), "New save directory should be applied");

    }
}
