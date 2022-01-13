package rough;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.ITestResult;

public class PathDemo {

	public static void main(String[] args) {
 
	 
		String str= "./build";
		Path path = Paths.get(str);
		System.out.println("path:" + path);
		if (Files.exists(path))
			System.out.println(path + " exists");
		else
		{
			System.out.println(path + " does not exist");
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				System.out.println("Some issue found while creating "+ path);
			}
		}
		
		String qualifiedName = "com.qa.api.gorest.tests.CreateUser.createUserAPIPOSTTest";
		System.out.println("qualifiedName: "+ qualifiedName);
		int last = qualifiedName.lastIndexOf(".");
		System.out.println("last : " + last);
		
		int mid = qualifiedName.substring(0, last).lastIndexOf(".");
		System.out.println("mid  : " + mid);
		String className = qualifiedName.substring(mid + 1, last);
		System.out.println("className: " + className);
	}
 
}












