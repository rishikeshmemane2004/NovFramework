package rough;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProvideMultiple {

	@Test(dataProvider = "getUserName")
	public void test001(String username) {
		System.out.println("username: " + username);
	}
	
	@Test(dataProvider = "getPassword")
	public void test002(String password) {
		System.out.println("password: " + password);
	}
	
	@DataProvider
	public Object[][] getUserName()
	{
		return new Object[][] {
			{"Rohit"},
			{"Virat"}
		};
	}
	
	@DataProvider
	public Object[][] getPassword()
	{
		return new Object[][] {
			{"RohitPwd"},
			{"ViratPwd"}
		};
	}
	
	
}
