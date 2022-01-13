package rough.pojoPractice;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;
import org.json.*;

public class NestedJSONObjectTest {

	@Test
	public void Test1() {
		String jsonDataString = "{userInfo : [{username:abc123}, {username:xyz123},{username:pqr123},   {username:mno123},{username:jkl123}]}";

		JSONObject jsonObject = new JSONObject(jsonDataString);
		
		List<String> list = new ArrayList<String>();
		JSONArray jsonArray = jsonObject.getJSONArray("userInfo");
		
		for (int i = 0; i < jsonArray.length(); i++) {
			list.add(jsonArray.getJSONObject(i).getString("username"));
			System.out.println(jsonArray.getJSONObject(i));
			System.out.println(jsonArray.getJSONObject(i).getString("username")); // display usernames
		}
	}
}
