package test.andy.hello;

import java.lang.reflect.Field;

import org.testng.annotations.Test;

public class TestPermissionTest {
  @Test
  public void f() {
	  TestPermission test = new TestPermission();
      test.setValue(12);
      System.out.println(" value is :"+test.getValue());

      Class testclass = test.getClass();
      try {
          Field field = testclass.getDeclaredField("value");
          field.setAccessible(true);
          field.set(testclass, 30);

          System.out.println(" value is :"+test.getValue());
      } catch (NoSuchFieldException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
      } catch (SecurityException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
      } catch (IllegalArgumentException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      } catch (IllegalAccessException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }
  }
}
