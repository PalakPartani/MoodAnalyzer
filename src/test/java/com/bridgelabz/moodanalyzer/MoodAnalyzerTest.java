package com.bridgelabz.moodanalyzer;

import com.bridgelabz.moodAnalyzerException.MoodAnalyzerException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MoodAnalyzerTest {
    private MoodAnalyzer moodanalyzer;

    @Test
    public void givenHappyMood_ShouldReturnHappy() {

        try {
            MoodAnalyzer moodanalyzer = new MoodAnalyzer("I am in happy mood ");
            String mood = moodanalyzer.analyzeMood();
            Assert.assertEquals("HAPPY", mood);

        } catch (MoodAnalyzerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenNotHappyMood_Should_ReturnSad() {

        try {
            MoodAnalyzer moodanalyzer = new MoodAnalyzer("I am in sad mood ");
            String mood = moodanalyzer.analyzeMood();
            Assert.assertEquals("SAD", mood);
        } catch (MoodAnalyzerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenNullMood_ShouldReturnMessage() {

        try {

            MoodAnalyzer moodanalyzer = new MoodAnalyzer();
            String mood = moodanalyzer.analyzeMood();

        } catch (MoodAnalyzerException ex) {
            Assert.assertEquals(MoodAnalyzerException.moodException.ENTERED_NULL, ex.type);
        }
    }

    @Test
    public void givenEmptyMood_ShouldReturnMessage() {

        try {
            MoodAnalyzer moodanalyzer = new MoodAnalyzer("");
            String mood = moodanalyzer.analyzeMood();
            Assert.assertEquals("HAPPY", mood);  //will give exp
        } catch (MoodAnalyzerException ex) {
            Assert.assertEquals(MoodAnalyzerException.moodException.ENTERED_EMPTY,ex.type);
        }
    }


}
