package com.quizzes;
/*
A positive integer N is called prime if it has exactly two distinct divisors: 1 and N. The first few prime integers are
2, 3, 5 and 7.

Write a function:
	class Solution { public int solution(int A, int B); }

that, given two positive integers A and B, returns the number of prime numbers within the range [a..B]
(both ends are the included in the range).

For example, give A = 11 and B = 19 the function should return 4, because there are four prime numbers within the
range [11..19], namely 11, 13, 17, 19.

Assume that:
- A and B are integers within the range [1..500,000];
- A <= B.

Complexity:
- expected worst-case time complexity is O((B- A) * sqrt(B));
- expected worst-case space complexity is O(1).
the function should return 2 because:

- indices 1 and 3 adjacent because A[1] <> A[3} and the array does not contain any value that lies strictly between
A[1] = 4 and A[3] = 3;
- the distance between these indices is abs(1 - 3) =2;
- no other pair of adjacent indices that has a smaller distance exists.

test case [0, 3, 3, 7, 5, 3, 11, 1]
should return 0;
*/
import java.util.Scanner;

//you can also use imports, for example:
//import java.util.*;

//you can write to stdout for debugging purposes, e.g.
//System.out.println("this is a debug message");

class PrimeNumbers {

	public static void main(String args[]) {
		int s1 = 11, s2 = 19;
		System.out.println(PrimeNumbers.solution(s1, s2));
	}

	public static int solution(int A, int B) {
		boolean flag = false;
		int primes = 0;
		for (int i = A; i <= B; i++) {
			if (isPrime(i)) {
				primes++;
//				System.out.println(i);
			}
		}
		return primes;
	}
	
	public static boolean isPrime(int num){
		boolean flag = false;
		for (int j = 2; j < num; j++) {
			if (num % j == 0) {
				flag = false;
				break;
			} else {
				flag = true;
			}
		}
		return flag;
	}
}