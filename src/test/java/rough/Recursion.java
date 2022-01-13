package rough;

public class Recursion {
	public static void main(String[] args) {

		int n = 7;
		
		// find factorial
		System.out.println("fact of " + n + "  = " + fact(n) + "\n");

		// finbonacci
		System.out.print("Fibonacci series of " + n + " numbers is: ");

		// for loop to print the fibonacci series.
		for (int i = 0; i < n; i++) {
			System.out.print(fib(i) + " ");
		}

		// other examples: fins max/min, add array elements, sort asc or desc array elements
	}

	static int fib(int n) {
		// Stop condition
		if (n == 0)
			return 0;

		// Stop condition
		if (n == 1 || n == 2)
			return 1;

		// Recursion function
		else
			return (fib(n - 1) + fib(n - 2));

	}

	static int fact(int n) {
		if (n == 1)
			return 1;
		else
			return (n * fact(n - 1));
	}
}