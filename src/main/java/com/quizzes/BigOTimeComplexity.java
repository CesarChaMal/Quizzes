package com.quizzes;

import java.util.Scanner;

class BigOTimeComplexity {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
//		int n = s.nextInt();
		int n = 16;

		System.out.println("N: " + n);

		// O(1)
		double constant = 1;
		System.out.println("O(1): " + constant);

		// O(logN)
		double logarithmBase2 = Math.log(n) / Math.log(2);
		System.out.println("O(logN): " + logarithmBase2);

		// O(N)
		System.out.println("O(N): " + n);

		// O(NlogN)
		double nlogarithmBase2 = n * (Math.log(n) / Math.log(2));
		System.out.println("O(NlogN): " + nlogarithmBase2);

		// O(N^2)
		double quadratic = Math.pow(n, 2);
		System.out.println("O(N^2): " + quadratic);

		// O(2^N)
		double exponential = Math.pow(2, n);
		System.out.println("O(2^N): " + exponential);
		
		// O(N!)
		double factorial = factorial(n);
		System.out.println("O(N!): " + factorial);
	}
	
	public static double factorial(double n) {
		if (n == 0)
			return 1;
		else
			return n* factorial(n-1);
	}
}
