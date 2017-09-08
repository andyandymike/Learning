package test.andy.hello;

import java.awt.Color;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DriveTest {

	public static void main(String[] args) {
        AutoDrive car = new AutoDrive();
        car.drive();
        
        Class clz = AutoDrive.class;
        
        try {
			Constructor con = clz.getConstructor(String.class, AutoDrive.Color.class);
			AutoDrive ad = (AutoDrive) con.newInstance("Tesla", AutoDrive.Color.RED);
			Method md = clz.getMethod("drive");
			md.invoke(ad, null);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
