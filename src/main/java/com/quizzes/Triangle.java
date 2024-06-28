package com.quizzes;

/*

Triangle(7)
*******
 *****
  ***
   *

*/

public class Triangle {
    public static void main(String[] args) {
        Triangle.triangle(7);
        Triangle.triangle(9);
        Triangle.triangle(11);
    }

    private static void triangle(int n) {
        for(int i=0; i<n; i++){
            for(int j=i; j<n; j++ ) {
                System.out.print("*");
            }
            n--;
            System.out.println();
        }
    }
}
