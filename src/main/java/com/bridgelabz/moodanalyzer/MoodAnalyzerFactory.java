package com.bridgelabz.moodanalyzer;

import com.bridgelabz.moodAnalyzerException.MoodAnalyzerException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MoodAnalyzerFactory {

    public static MoodAnalyzer createMoodAnalyzer() {
        try {
            //constructor obj to get the constructor of that class
            Constructor constructor = Class.forName("com.bridgelabz.moodanalyzer.MoodAnalyzer").getConstructor();
            try {
                //object of class object created to pass the message to that constructor.
                Object objMood = constructor.newInstance();
                //type casted the result of object to type of Moodanalyzer class.
                MoodAnalyzer moodAnalyzer = (MoodAnalyzer) objMood;
                //returning the object of MoodAnalyzer to the test method where it is called.
                return moodAnalyzer;
                //the different exception that can occur are provided by default.
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static MoodAnalyzer createMoodAnalyzer(String message) {
        try {
            //constructor obj to get the constructor of that class
            Constructor constructor = Class.forName("com.bridgelabz.moodanalyzer.MoodAnalyzer").getConstructor(String.class);
            try {
                //object of class object created to pass the message to that constructor.
                Object objMood = constructor.newInstance(message);
                //type casted the result of object to type of Moodanalyzer class.
                MoodAnalyzer moodAnalyzer = (MoodAnalyzer) objMood;
                //returning the object of MoodAnalyzer to the test method where it is called.
                return moodAnalyzer;
                //the different exception that can occur are provided by default.
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace(); //thr new MoodAnalyzerEx(moodException.NO_SUCH_METHOD,e.msg)
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //if no message found then it will return null
        return null;
    }

    //4.1 and 4.2
    public static void getConstructor(String stringClass, Class stringMethod) throws MoodAnalyzerException {
        try {
            Class<?> classVar = Class.forName(stringClass);                  //<?> is used because the classname is unknown
            classVar.getConstructor(stringMethod);
        } catch (NoSuchMethodException e) {
            throw new MoodAnalyzerException(MoodAnalyzerException.moodException.NO_SUCH_METHOD, "Wrong method");
        } catch (ClassNotFoundException e) {
            throw new MoodAnalyzerException(MoodAnalyzerException.moodException.NO_SUCH_CLASS, "Wrong class");
        }
    }

    public static String getMethod(MoodAnalyzer obj, String methodName)  {
        try {
           Method methodObject = obj.getClass().getDeclaredMethod(methodName);
            return (String) methodObject.invoke(obj);
        } catch (NoSuchMethodException e) {
            throw new MoodAnalyzerException(MoodAnalyzerException.moodException.NO_SUCH_METHOD,"Method not found!");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String setField(MoodAnalyzer obj, String fieldName,String message) {
        try {
           // Method methodObject = obj.getClass().getDeclaredMethod(methodName);
            Field declaredField = obj.getClass().getDeclaredField(fieldName);
            declaredField.setAccessible(true);
            declaredField.set(obj,message);
            return (String) obj.getClass().getDeclaredMethod("analyzeMood").invoke(obj);
            //declaredField = "I am in HAPPY mood";
        } catch (NoSuchFieldException e) {
            throw new MoodAnalyzerException(MoodAnalyzerException.moodException.NO_SUCH_FIELD,"Method not found!");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new MoodAnalyzerException(MoodAnalyzerException.moodException.OBJECT_CREATION_ISSUE,"Method not found!");
        }
        return null;
    }
}
