/**
 * Interface for generating Fibonacci series.
 * Implements Interface Segregation and Dependency Inversion Principles.
 */
package solidJava;

import java.util.function.Consumer;

interface Fibonacci {
	void generate(int n);
}
/**
 * Generates Fibonacci series and outputs via a Consumer function.
 * Follows SOLID principles: SRP, OCP, LSP, ISP, and DIP.
 */
class FibonacciGenerator implements Fibonacci {
	private final Consumer<String> output;
	
	public FibonacciGenerator(Consumer<String> output) {
		this.output = output;
	}
	
	@Override
	public void generate(int n) {
		if (n <= 0) {
			output.accept("Invalid input");
			return;
		}
		int a = 0, b = 1, c;
        StringBuilder series = new StringBuilder(a + " " + b);

        for (int i = 2; i < n; i++) {
            c = a + b;
            series.append(" ").append(c);
            a = b;
            b = c;
        }
        output.accept(series.toString()); // Send output to the provided Consumer function
	}     
}

public class FibonacciSeries {
	public static void main(String[] args) {
		FibonacciGenerator generator = new FibonacciGenerator(System.out::println);
		generator.generate(10);
	}
}
