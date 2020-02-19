package com.dummy;

public class Demo {
    public static void main(String[] args) {
    Demo demo=new Demo();
    demo.method1();
    }
    public void method1(){
        method2();
    }
    public void method2(){
        try {
            System.out.println(1 / 0);
        }
        catch(ArithmeticException ex)
        {
            System.out.println(ex);
        }
    }

}
