package com.walz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Manages the configuration settings for the journaling application.
 * This class stores preferences related to file storage, UI aesthetics, 
 * and gratitude journaling prompts.
 */

public class Config {
    
    /**
     * The relative directory path where journal entries are stored.
     */
    private String saveDir = "JournalApp/entries/";

    /**
     * The primary foreground (text) hex color code for the UI.
     */    
    private String fg = "#0a211b";
    
    /**
     * The background hex color code for the UI.
     */
    private String bg = "#1e5f43";
    
    /**
     * The accent hex color code used for highlights or buttons in the UI.
     */
    private String accent = "#92ffc1";
    
    /**
     * The default number of gratitude prompts to display per session.
     */
    private int gratitudeCount = 2;

    /**
     * A list of predefined prompts used to inspire gratitude entries.
     */
    private List<String> gratitudePrompts = new ArrayList<>(Arrays.asList(
      "What abilities do I have that I'm grateful for?",
            "What life experiences (good or bad) have I had that I'm grateful for?",
            "What material possessions am I thankful for?",
            "What about my surroundings (home/neighborhood/city, etc.) am I thankful for?",
            "What made me laugh or smile recently?",
            "How am I fortunate?",
            "Who do I appreciate? Why?",
            "What is something fun I get to do later?",
            "What's something I've learned recently that has improved my life?",
            "What happened today/this week/this month/this year for which I'm grateful?",
            "What activities (hobbies, events, etc.) am I grateful for?",
            "How can I be thankful for the challenges I've experienced in my life? What did I learn from them?",
            "What's an opportunity I'm thankful for?",
            "What in nature inspires me and why?",
            "What have others done for me for which I'm thankful?",
            "What is different today than it was a year ago that I'm grateful for?",
            "What insights and discoveries have I personally gained that I'm thankful for?",
            "In what ways can I help others today or sometime soon?",
            "How have my spiritual beliefs or practices fulfilled me recently?",
            "What's one thing I enjoyed about my job recently?",
            "Have you had an opportunity to help someone recently? How did you feel about it?",
            "What's something you're looking forward to in the future?",
            "What movie, book, blog, or article affected your life for the better recently?",
            "Who in your life has survived something difficult and grown from the experience?",
            "What's one kind or thoughtful thing someone did for me recently?"));
            //https://www.decideyourlegacy.com/blog/25-MORE-gratitude-questions-that-can-change-your-life-forever 
            //these are just quick and dirty sample questions i found that would give a good variation. obviously can be changed by the user so just serving as a default. thank you decideyourlegacy.com. yall the best.

    /**
     * Constructs a new Config object with default settings.
     */
    public Config() {
    }
    
    /**
     * Gets the current foreground color hex code.
     * @return A string representing the hex color (e.g., "#0a211b").
     */
    public String getFg() {
        return fg;
    }

    /**
     * Gets the current foreground color hex code.
     * @return A string representing the hex color (e.g., "#0a211b").
     */
    public void setFg(String fg) {
        this.fg = fg;
    }

    /**
     * Gets the current background color hex code.
     * @return A string representing the hex color.
     */
    public String getBg() {
        return bg;
    }

    /**
     * Sets the background color hex code.
     * @param bg The new hex color string.
     */
    public void setBg(String bg) {
        this.bg = bg;
    }

    /**
     * Gets the current accent color hex code.
     * @return A string representing the hex color.
     */
    public String getAccent() {
        return accent;
    }

    /**
     * Sets the accent color hex code.
     * @param accent The new hex color string.
     */
    public void setAccent(String accent) {
        this.accent = accent;
    }

    /**
     * Retrieves the list of gratitude prompts.
     * @return A List of strings containing prompts.
     */
    public List<String> getGratitudePrompts() {
        return gratitudePrompts;
    }
    
    /**
     * Updates the list of gratitude prompts.
     * @param gratitudePrompts The new list of prompt strings.
     */
    public void setGratitudePrompts(List<String> gratitudePrompts) {
        this.gratitudePrompts = gratitudePrompts;
    }

    /**
     * Gets the number of prompts intended for each entry session.
     * @return The count of prompts to be shown.
     */
    public int getGratitudeCount() {
        return gratitudeCount;
    }

    /**
     * Sets the number of prompts to be shown during a session.
     * @param gratitudeCount The integer count of prompts.
     */
    public void setGratitudeCount(int gratitudeCount) {
        this.gratitudeCount = gratitudeCount;
    }

    /**
     * Gets the directory path where journal files are saved.
     * @return The directory path string.
     */
    public String getSaveDir() {
        return saveDir;
    }

    /**
     * Sets the directory path for saving journal files.
     * @param saveDir The new directory path string.
     */
    public void setSaveDir(String saveDir) {
        this.saveDir = saveDir;
    }
}
