package com.bridgelabz.moodanalyzer;

import com.bridgelabz.moodAnalyzerException.MoodAnalyzerException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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
    public void givenSadMood_Should_ReturnSad() {
        try {
            MoodAnalyzer moodanalyzer = new MoodAnalyzer("I am in sad mood ");
            String mood = moodanalyzer.analyzeMood();
            Assert.assertEquals("SAD", mood);
        } catch (MoodAnalyzerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenNullMood_ShouldReturnException() {
        try {
            MoodAnalyzer moodanalyzer = new MoodAnalyzer();
            String mood = moodanalyzer.analyzeMood();

        } catch (MoodAnalyzerException ex) {
            Assert.assertEquals(MoodAnalyzerException.moodException.ENTERED_NULL, ex.type);
        }
    }

    @Test
    public void givenEmptyMood_ShouldReturnException() {
        String mood;
        MoodAnalyzer moodanalyzer = new MoodAnalyzer("");

        try {

            mood = moodanalyzer.analyzeMood();
            // Assert.assertEquals("HAPPY", mood);  //will give exp
        } catch (MoodAnalyzerException ex) {
            Assert.assertEquals(MoodAnalyzerException.moodException.ENTERED_EMPTY, ex.type);
            return;
        }
        Assert.assertEquals("This is empty", mood);
    }
    //Reflection
    @Test
    public void givenMoodAnalyzer_WhenProper_ShouldReturnObject() {
        try {
            Constructor constructor = Class.forName("com.bridgelabz.moodanalyzer.MoodAnalyzer").getConstructor(String.class);
            Object objMood = constructor.newInstance("I am in a happy mood");   //created obj
            MoodAnalyzer objMood1 = (MoodAnalyzer) objMood;     //type casting
            String mood = objMood1.analyzeMood();
            Assert.assertEquals("HAPPY", mood);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void givenMoodAnalyzerClass_WhenImproper_Should_ReturnException() {      //default cons 4.1
        MoodAnalyzer moodAnalyzer = null;
        try {
            moodAnalyzer = MoodAnalyzerFactory.createMoodAnalyzer();
            Assert.assertEquals(new MoodAnalyzer(), moodAnalyzer);
        } catch (MoodAnalyzerException ex) {
            ex.printStackTrace();
        }
    }
    @Test
    public void givenWrongClassName_ShouldReturnException() {
        try {
            MoodAnalyzerFactory.getConstructor("com.bridgelabz.moodAnalyzer.MoodAnalyze",String.class);
        }catch(MoodAnalyzerException e){
            Assert.assertEquals(MoodAnalyzerException.moodException.NO_SUCH_CLASS,e.type);
        }
    }

   @Test
    public void givenWrongConstructorName_WhenImproper_Should_Return_Exception() {
       try {
           MoodAnalyzerFactory.getConstructor("com.bridgelabz.moodanalyzer.MoodAnalyzer",Integer.class);
       } catch (MoodAnalyzerException e) {
           Assert.assertEquals(MoodAnalyzerException.moodException.NO_SUCH_METHOD,e.type);
       }
   }
}
