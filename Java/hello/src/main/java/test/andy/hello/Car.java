package test.andy.hello;

import java.util.HashMap;
import java.util.List;

import test.andy.hello.Reflection.Son;

public class Car {

    private String mBand;

    private Color mColor;

    public enum Color {
        RED,
        WHITE,
        BLACK,
        BLUE,
        YELLOR
    }



    public Car() {
        super();
        // TODO Auto-generated constructor stub
    }


    public Car(String mBand) {
        this.mBand = mBand;
    }


    public void drive() {
        System.out.println("di di di,开车了！");
    }

    @Override
    public String toString() {
        return "Car [mBand=" + mBand + ", mColor=" + mColor + "]";
    }

    public void test(String[] paraa,List<String> b,HashMap<Integer,Son> maps) {}


}