package com.bridgelabz.moodAnalyzerException;

import java.security.PublicKey;

public class MoodAnalyzerException extends RuntimeException {
    public enum moodException{
        ENTERED_NULL,ENTERED_EMPTY,NO_SUCH_FIELD,NO_SUCH_METHOD,NO_SUCH_CLASS,OBJECT_CREATION_ISSUE,FIELD_SETTING_ISSUE;
    }
    public moodException type;
    public MoodAnalyzerException(moodException type, String message) {
        super(message);
        this.type=type;
    }
    public MoodAnalyzerException(moodException type,Throwable cause){
        super(cause);
        this.type=type;
    }
    public MoodAnalyzerException(moodException type,String message,Throwable cause){
        super(message,cause);
        this.type=type;
    }

}

