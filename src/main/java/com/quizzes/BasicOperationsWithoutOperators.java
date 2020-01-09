package com.quizzes;

// Java Program to add two numbers
// without using arithmetic operator

class BasicOperationsWithoutOperators {
    static int add(int x, int y) {
        // Iterate till there is no carry
        while (y != 0) {
            // carry now contains common
            // set bits of x and y
            int carry = x & y;

            // Sum of bits of x and
            // y where at least one
            // of the bits is not set
            x = x ^ y;

            // Carry is shifted by
            // one so that adding it
            // to x gives the required sum
            y = carry << 1;
        }
        return x;
    }

    static int addRecursive(int x, int y) {
        if (y == 0)
            return x;
        else
            return addRecursive(x ^ y, (x & y) << 1);
    }

    // Driver code
    public static void main(String arg[]) {
        System.out.println(add(15, 10));
        System.out.println(addRecursive(-5, 10));
        System.out.println(subtract(10, 5));
        System.out.println(divide(10, 2));
        System.out.println(multiply(10, 2));
    }

    private static int subtract(int x, int y) {
        // Iterate till there is no carry
        while (y != 0) {
            // borrow contains common
            // set bits of y and unset
            // bits of x
            int borrow = (~x) & y;

            // Subtraction of bits of x
            // and y where at least one
            // of the bits is not set
            x = x ^ y;

            // Borrow is shifted by one
            // so that subtracting it from
            // x gives the required sum
            y = borrow << 1;
        }
        return x;
    }

    // Function to divide a by b and
    // return floor value it
    static int divide(int dividend, int divisor) {

        // Calculate sign of divisor i.e.,
        // sign will be negative only iff
        // either one of them is negative
        // otherwise it will be positive
        int sign = ((dividend < 0) ^ (divisor < 0)) ? -1 : 1;

        // Update both divisor and
        // dividend positive
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);

        // Initialize the quotient
        int quotient = 0;

        while (dividend >= divisor) {
            dividend -= divisor;
            ++quotient;
        }

        return sign * quotient;
    }

    /* function to multiply two numbers x and y*/
    static int multiply(int x, int y) {

        /* 0 multiplied with anything gives 0 */
        if (y == 0)
            return 0;

        /* Add x one by one */
        if (y > 0)
            return (x + multiply(x, y - 1));

        /* the case where y is negative */
        if (y < 0)
            return -multiply(x, -y);

        return -1;
    }
}
