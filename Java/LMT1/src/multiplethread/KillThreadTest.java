package multiplethread;

import org.testng.Assert;
import org.testng.annotations.Test;

import character.Hero;

public class KillThreadTest {

  @Test
  public void testKillThread() {
    Hero gareen = new Hero("gareen", 100, 20);
    Hero teemo = new Hero("teemo", 200, 10);
    KillThread killThread1 = new KillThread(gareen, teemo);
    killThread1.start();
    try {
		killThread1.join();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    Assert.assertEquals(teemo.hp, 0.0, 0.00001);
  }
}
