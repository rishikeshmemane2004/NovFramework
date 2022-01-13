package rough;

public class Student implements Cloneable {

	int rollNo;
	String name;

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	// constructor
	public Student(int rollNo, String name) {
		super();
		this.rollNo = rollNo;
		this.name = name;
	}

	// getters and setters
	public int getRollNo() {
		return rollNo;
	}

	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
