package com.walz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class StorageManagerTest {
    
    @TempDir
    Path tempDir;

    @Test
    public void testDirectoryInitialization() {
        // arrange
        String testConfigPath = tempDir.resolve("JournalApp").resolve("config.json").toString();
    
    
        // act
        new StorageManager(testConfigPath);
       
        // assert
        File expectedDir =  new File(tempDir.resolve("JournalApp").toString());

        assertTrue(expectedDir.exists(), "StorageManager should create the parent directory if it doesn't exist.");
    }
    
    @Test
    public void testSaveConfigSuccess() {
        // arrange
        String testConfigPath = tempDir.resolve("config.json").toString();
        StorageManager storage = new StorageManager(testConfigPath);
        Config testConfig = new Config();
        testConfig.setAccent("#FFFFFF"); //just changing one to see if it changes

        // act
        boolean result = storage.saveConfig(testConfig);

        // assert
        assertTrue(result, "saveConfig should return true upon success");
        File savedFile = new File(testConfigPath);
        assertTrue(savedFile.exists(), "The config.json file should be written to the disk");
    }

    @Test
    public void testLoadConfigSuccess() throws IOException {
        // arrange
        Path testConfigPath = tempDir.resolve("config.json");
        StorageManager storage = new StorageManager(testConfigPath.toString());
        
        String dummyJson = "{\n" +
                "  \"saveDir\": \"Fake/Path/\",\n" +
                "  \"gratitudeCount\": 4\n" +
                "}";
        Files.writeString(testConfigPath, dummyJson);

        // act
        Config loadedConfig = storage.loadConfig();

        // assert
        assertNotNull(loadedConfig, "Loaded config should not be null.");
        assertEquals(4, loadedConfig.getGratitudeCount(), "Should read the custom gratitude count from the file.");
        assertEquals("Fake/Path/", loadedConfig.getSaveDir(), "Should read the custom save directory from the file.");
    }
}

