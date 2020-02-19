package com.bridgelabz.moodAnalyzerException;

public class MoodAnalyzerException extends Exception {
    public enum moodException{
        ENTERED_NULL,ENTERED_EMPTY;
    }
    public moodException type;
    public MoodAnalyzerException(moodException type, String message) {
        super(message);
        this.type=type;
    }

}

