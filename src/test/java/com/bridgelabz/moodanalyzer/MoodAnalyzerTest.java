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

    @Test
    public void givenMoodAnalyzerClass_WhenProper_ShouldReturnObject() {
        //created method in new class to return the message
        MoodAnalyzer moodAnalyzer = MoodAnalyzerFactory.createMoodAnalyzer("I am in a happy mood");   //created method in new class
        try {
            String mood = moodAnalyzer.analyzeMood();
            Assert.assertEquals("HAPPY", mood);
        } catch (MoodAnalyzerException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void givenMoodAnalysisClass_WhenProper_ShouldReturnObject() {
        try {
            MoodAnalyzer moodAnalyzer = MoodAnalyzerFactory.createMoodAnalyzer("I am in a happy mood");
            Assert.assertEquals(new MoodAnalyzer("I am in a happy mood"), moodAnalyzer);
        } catch (MoodAnalyzerException ex) {
            ex.printStackTrace();
        }
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
            MoodAnalyzerFactory.getConstructor("com.bridgelabz.moodAnalyzer.MoodAnalyze", String.class);
        } catch (MoodAnalyzerException e) {
            Assert.assertEquals(MoodAnalyzerException.moodException.NO_SUCH_CLASS, e.type);
        }
    }

    @Test
    public void givenWrongConstructorName_WhenImproper_Should_Return_Exception() {
        try {
            MoodAnalyzerFactory.getConstructor("com.bridgelabz.moodanalyzer.MoodAnalyzer", Integer.class);
        } catch (MoodAnalyzerException e) {
            Assert.assertEquals(MoodAnalyzerException.moodException.NO_SUCH_METHOD, e.type);
        }
    }

    @Test
    public void givenMethod_WhenProper_ShuldInvoke() {
        try {
            MoodAnalyzer moodAnalyzer = MoodAnalyzerFactory.createMoodAnalyzer("I am in happy mood");
            String analyzemood = MoodAnalyzerFactory.getMethod(moodAnalyzer, "analyzeMood");
            Assert.assertEquals("HAPPY", analyzemood);

            //  method=obj.getClass().getMethod()
        } catch (MoodAnalyzerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMethod_WhenWrong_ShouldReturnException() {
        try{
            MoodAnalyzer moodAnalyzer= MoodAnalyzerFactory.createMoodAnalyzer("I am in happy mood");
            String analyzeMood=MoodAnalyzerFactory.getMethod(moodAnalyzer,"moodAnalyze");

        }
        catch (MoodAnalyzerException e){
            Assert.assertEquals(MoodAnalyzerException.moodException.NO_SUCH_METHOD,e.type);

        }
    }

    @Test
    public void givenHappyThroughReflection_ShouldReturnHappy() {
        try{
            MoodAnalyzer moodAnalyzer=MoodAnalyzerFactory.createMoodAnalyzer("I am in happy mood");

            String analyzeMood=MoodAnalyzerFactory.setField(moodAnalyzer,"message","I Am In happy Mood");
            Assert.assertEquals("HAPPY",analyzeMood);
        }catch (MoodAnalyzerException e){
            e.printStackTrace();
        }
    }
    @Test
    public void givenHappyThroughReflection_ShouldReturnException() {
        try{
            MoodAnalyzer moodAnalyzer=MoodAnalyzerFactory.createMoodAnalyzer("I am in happy mood");

            String analyzeMood=MoodAnalyzerFactory.setField(moodAnalyzer,"mesage","I Am In happy Mood");

        }catch (MoodAnalyzerException e){
            e.printStackTrace();
            Assert.assertEquals(MoodAnalyzerException.moodException.NO_SUCH_FIELD,e.type);
        }
    }

    @Test
    public void givenHappyThroughReflection_ShouldReturnExceptionNull() {
        try{
            MoodAnalyzer moodAnalyzer=MoodAnalyzerFactory.createMoodAnalyzer("I am in happy mood");

            String analyzeMood=MoodAnalyzerFactory.setField(moodAnalyzer,"message",null);

        }catch (MoodAnalyzerException e){
            e.printStackTrace();
            Assert.assertEquals(MoodAnalyzerException.moodException.OBJECT_CREATION_ISSUE,e.type);
        }
    }

}
