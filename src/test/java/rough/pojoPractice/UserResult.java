package rough.pojoPractice;

public class UserResult {

	private String firtname;
	private String lastname;
	private String city;
	private int age;
	private boolean isMale;

	private Links ln;
	
	public UserResult(String firtname, String lastname, String city, int age, 
			boolean isMale, Links ln) {
		this.firtname = firtname;
		this.lastname = lastname;
		this.city = city;
		this.age = age;
		this.isMale = isMale;
		this.ln = ln;
	}

	public String getFirtname() {
		return firtname;
	}

	public void setFirtname(String firtname) {
		this.firtname = firtname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isMale() {
		return isMale;
	}

	public void setMale(boolean isMale) {
		this.isMale = isMale;
	}

	public Links getLn() {
		return ln;
	}

	public void setLn(Links ln) {
		this.ln = ln;
	}
	
	
	
}
