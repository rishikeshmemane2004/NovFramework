package rough;

public class CloningOfObjectDemo {

	public static void main(String[] args) {
		
		Student s1 = new Student(100,"Virat");
		Student s2=null;

		System.out.println("Memory address s1 : " + System.identityHashCode(s1));
		System.out.println("Memory address s2 : " + System.identityHashCode(s2));
		
		try {
			s2 = (Student) s1.clone();
		} catch (CloneNotSupportedException e) {
				System.out.println("Something has gone wrong in clonning object !!!");
		}
		
		System.out.println("After cloning Memory address s1 : " + System.identityHashCode(s1));
		System.out.println("After cloning Memory address s2 : " + System.identityHashCode(s2));
		
		System.out.println("Original values of s1 => " + s1.getRollNo() + " ==> " + s1.getName());
		System.out.println("After cloning from s1 to s2 => " + s2.getRollNo() + " ==> " + s2.getName());
		
		s2.setName("Sachin");
		s2.setRollNo(200);
		
		System.out.println("After changing values, s1 => " + s1.getRollNo() + " ==> " + s1.getName());
		System.out.println("After changing values, s2 => " + s2.getRollNo() + " ==> " + s2.getName());
		
		
	}
}
