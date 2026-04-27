package com.walz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Config {

    
    private String saveDir = "JournalApp/entries/";
    
    private String fg = "#0a211b";
    private String bg = "#1e5f43";
    private String accent = "#92ffc1";
    

    private int gratitudeCount = 2;

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


    public Config() {
    }

    public String getFg() {
        return fg;
    }

    public void setFg(String fg) {
        this.fg = fg;
    }

    public String getBg() {
        return bg;
    }

    public void setBg(String bg) {
        this.bg = bg;
    }

    public String getAccent() {
        return accent;
    }

    public void setAccent(String accent) {
        this.accent = accent;
    }

    public List<String> getGratitudePrompts() {
        return gratitudePrompts;
    }

    public void setGratitudePrompts(List<String> gratitudePrompts) {
        this.gratitudePrompts = gratitudePrompts;
    }

    public int getGratitudeCount() {
        return gratitudeCount;
    }

    public void setGratitudeCount(int gratitudeCount) {
        this.gratitudeCount = gratitudeCount;
    }

    public String getSaveDir() {
        return saveDir;
    }

    public void setSaveDir(String saveDir) {
        this.saveDir = saveDir;
    }
}
