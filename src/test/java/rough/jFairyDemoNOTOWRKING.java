package rough;

import org.testng.annotations.Test;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;

 

public class jFairyDemoNOTOWRKING {

@Test 
public void jFairyExamples()
{
	
	Person person = Fairy.create().person();

	System.out.println(person.getAddress());
//	System.out.println(person.getAddress().getApartmentNumber());
	
/*	
	Fairy fairy = Fairy.create();
	
	for(int i=0;i<4;i++)
	{
			System.out.println("name  : "  + fairy.person().getFullName());
			System.out.println("email : "  + fairy.person().getEmail());
			System.out.println("Gender: " + fairy.person().getSex());
			if (TestUtil.generateRandomnumber() % 2 ==0)
				System.out.println("status:  active");
			else
				System.out.println("status:  inactive");
	}
*/	
}
}
