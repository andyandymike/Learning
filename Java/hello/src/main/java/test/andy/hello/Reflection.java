package test.andy.hello;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class Reflection {
	
	public class Farther {

	    public int a;

	    private int b;

	    public Farther() {
	        super();
	        // TODO Auto-generated constructor stub
	    }


	}

	public class Son extends Farther {
	    int c;

	    private String d;

	    protected float e;

	    public List<Car> cars;

	    public HashMap<Integer,String> map;

	    private Son() {
	        super();
	        // TODO Auto-generated constructor stub
	    }



	    public Son(int c, String d) {
	        super();
	        this.c = c;
	        this.d = d;
	    }

	}
	

	public static void main(String[] args) {
		TestMethod t = new TestMethod();
		Class clz = t.getClass();
		
		try {
			Method mStatic = clz.getMethod("testStatic", null);
			mStatic.invoke(null, null);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
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
		
		try {
			Method mAdd = clz.getDeclaredMethod("add", int.class, int.class);
			mAdd.setAccessible(true);
			int result = (int) mAdd.invoke(t, 1, 2);
			System.out.println("add method result: "+result);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
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
		
		try {
			Method testException = clz.getDeclaredMethod("testException", null);
			testException.invoke(t, null);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			System.out.println("testException occur some error,Error type is: "+e.getCause().getClass().getName());
			System.out.println("Error message is: "+e.getCause().getMessage());
		}
		
	}

}
