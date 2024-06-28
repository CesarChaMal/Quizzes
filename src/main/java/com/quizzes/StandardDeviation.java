package com.quizzes;
/*
A zer-indexed array A consisting of N integers is given. The average value of array is A is defined as:
(A[0] + A[1] + .. A[N-1]) / N

The deviation of element A[P] (where 0 <= P < N) is defined as |A[P] - M|, where M is the average value of array A.
Element A[P] is called extreme if its deviation is maximal among all the elements of A.

For example, in array A such that:
A[0] = 9
A[1] = 4
A[2] = 3
A[3] = 10

The average value of this array is (9 + 4 +(-3) +(-10)) /4 = 0. The deviation of element A[2] is
|(-3) - 0| = |0 - (-3)| = 3. The deviation of element A[3] is 10. It is an extreme element of array A, since
no other element has a deviation greater than 10. There are no other extreme elements in this array.

Write a function:
	class Solution { public int solution(int[] A); }

that, given a zero-indexed array A consisting of N integers, returns the index of an extreme element. If more than
one extreme element exists, the function may return the index of any of them. If the array is empty (and hence no
extreme element exists), the function should return -1.

For example, given Array shown above, the function should return 3, since A[3] is the only extreme element.

Assume that
- N is an integer within the rance [0 .. 100,000];
- each element of array A is an integer within the rance [-2,147,483,648 .. 2,147,483,647].

Complexity:
- expected worst-case time complexity is O(N);
- expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input
arguments).

Elements of input arrays can be modified.
*/

public class StandardDeviation {
	public static int solution(int[] A) {
		int N = A.length;

		if (N == 0)
			return -1;

		double M = 0;
		for (int i = 0; i < N; i++) {
			M += A[i];
		}
		M = M / N;
//		System.out.println(M);

		double maxDev = 0;
		int extremeEle = 0;

		for (int i = 0; i < N; i++) {
			double dev = Math.abs(A[i] - M);
//			System.out.println(dev);
			if (dev > maxDev) {
				maxDev = dev;
				extremeEle = i;
			}
		}
		return extremeEle;
	}

	public static void main(String[] args) {
		int[] A = new int[] { 9, 4, -3, -10 };
		System.out.println(StandardDeviation.solution(A));
	}

}