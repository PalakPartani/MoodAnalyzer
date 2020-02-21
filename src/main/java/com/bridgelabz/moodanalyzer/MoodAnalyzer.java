package com.bridgelabz.moodanalyzer;

import com.bridgelabz.moodAnalyzerException.MoodAnalyzerException;

import java.util.EmptyStackException;

public class MoodAnalyzer {

    private String message;

    public MoodAnalyzer() {
        this.message="default";
        //default constructor for null and empty mood
    }

    public MoodAnalyzer(String message) {
        this.message = message;
    }

    public String analyzeMood(String message) throws MoodAnalyzerException {
        this.message = message;
        return analyzeMood();
    }


    public String analyzeMood() throws MoodAnalyzerException {

        try {
            if (message.length() == 0) {
                throw new MoodAnalyzerException(MoodAnalyzerException.moodException.ENTERED_EMPTY, "This is empty");
            } else if (message.contains("happy")) {
                return "HAPPY";
            }
            return "SAD";
        } catch (NullPointerException ex) {
            throw new MoodAnalyzerException(MoodAnalyzerException.moodException.ENTERED_NULL, "This mood is invalid!");
        }
    }

    public boolean equals(Object another) {
        if (this.message.equals(((MoodAnalyzer) another).message))
            return true;
        return false;
    }
}