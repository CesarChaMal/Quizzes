package com.quizzes;

/*

FizzBuzz Program in Java
FizzBuzz is a game that is popular among kids. By playing this, kids learn the division. Now, the FizzBuzz game has become a popular programming question that is frequently asked in Java programming interviews. In this section, we will learn how to create a FizzBuzz program in Java.

Rules of the FizzBuzz Game
The rules of the FizzBuzz game are very simple.

Say Fizz if the number is divisible by 3.
Say Buzz if the number is divisible by 5.
Say FizzBuzz if the number is divisible by both 3 and 5.
Return the number itself, if the number is not divisible by 3 and 5.

*/

import java.util.Scanner;
import java.util.stream.IntStream;

public class FizzBuzzExample2 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number:");
        //reads an integer from the user
        int num = sc.nextInt();
        //the rangeClosed() method returns a sequential IntStream for the specified range of int elements
        //for-each iterate over the Stream and prints the elements
        IntStream.rangeClosed(1, num).mapToObj(i -> i % 3 == 0 ? (i % 5 == 0 ? "FizzBuzz " : "Fizz ") : (i % 5 == 0 ? "Buzz " : i + " ")).forEach(System.out::print);
        //close the Scanner
        sc.close();
    }
}  