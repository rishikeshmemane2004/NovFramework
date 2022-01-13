package rough.pojoPractice;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MainObjectClass {
	@Test
	public void Tesst1() {

		Self s = new Self("https://www.self.com");
		Edit e = new Edit("https://www.edit.com");
		Avatar a = new Avatar("https://www.avatar.com");

		Links ln = new Links(s, e, a);
		UserResult ur = new UserResult("Virat", "Kohli", "Delhi", 33, true, ln);

		ObjectMapper mapper = new ObjectMapper();
		
		String jSonString=null;
		try {
			jSonString = mapper.writeValueAsString(ur);
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}
		
		System.out.println(jSonString);

	}
};
