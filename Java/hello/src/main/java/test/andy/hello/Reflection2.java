package test.andy.hello;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Reflection2 {

	public static void main(String[] args) {
		Class clz = TestConstructor.class;
		
		try {
			TestConstructor tc = (TestConstructor) clz.newInstance();
			System.out.println(tc.toString());
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Constructor con = clz.getConstructor(String.class);
			TestConstructor tc = (TestConstructor) con.newInstance(" Andy ");
			System.out.println(tc.toString());
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
