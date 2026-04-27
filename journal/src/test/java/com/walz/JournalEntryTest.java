package com.walz;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class JournalEntryTest {
    
    @Test
    public void testJournalEntryCreationAndGetters() {
        //arrange data
        List<String> gratitudes = Arrays.asList("Family", "Coffee");
        List<String> tasks = Arrays.asList("Finish Homework", "Clean office");
        String thoughts = "Should focus on accuracy over speed";
        List<String> prompts = Arrays.asList("What inspires you", "Where do you get your motion?", "Where do you see yourself in 5 years?");

        LocalDateTime time = LocalDateTime.now();

        // create journal entry
        JournalEntry entry = new JournalEntry(gratitudes, tasks, thoughts, prompts);

        //test

        assertNotNull(entry.getDate(), "Date should be initialized on creation");
        assertEquals(gratitudes, entry.getGratitudes(), "Gratitudes list should match input");
        assertEquals(tasks, entry.getTasks(), "Tasks list should match input");
        assertEquals(thoughts, entry.getThoughts(), "Thoughts string should match input");
        assertTrue(time.isBefore(entry.getDate()) || time.isEqual(entry.getDate()), "Timestamps should match creation timestamp");
        System.out.println(time + entry.getDate().toString());
    }
}
