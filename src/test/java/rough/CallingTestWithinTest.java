package rough;

import org.testng.annotations.Test;

public class CallingTestWithinTest {

	@Test
	public void t1()
	{
		System.out.println("t1 TC is getting called...");
	}
	
	@Test
	public void t2()
	{
		System.out.println("t2 TC is getting called...");
		t3();
	}

	@Test
	public void t3()
	{
		System.out.println("t3 TC is getting called...");
	}
}
