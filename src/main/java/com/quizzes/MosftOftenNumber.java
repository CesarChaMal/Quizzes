package com.quizzes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MosftOftenNumber {
    private static final Logger logger = LoggerFactory.getLogger(new Object() { }.getClass().getEnclosingClass());

    // for O(N) + map O(1) = O(N) 
    public static int mostOftenNumber(int[] a)
    {
        final Map m = new HashMap<Integer,Integer>();
        int max = 0;
        int element = 0;

        for (int i=0; i<a.length; i++){
            //initializing value for the map the value will have the counter of each element
            //first time one new number its found will be initialize with zero 
            if (m.get(a[i]) == null)
                m.put(a[i],0);

            //save each value from the array and increment the count each time its found
            m.put(a[i] , (Integer) m.get(a[i]) + 1);

            //check the value from each element and comparing with max
            if ( (Integer) m.get(a[i]) > max){
                max = (Integer) m.get(a[i]);
                element = a[i];
            }

        }
//        System.out.println("Times repeated: " + max);
        logger.info("Times repeated: " + max);
        return element;
    }

    public static int mostOftenNumberWithLambdas(int[] a)
    {
        Integer max1 = IntStream.of(a).boxed().mapToInt(value -> value).max().orElseThrow(NoSuchElementException::new);
        Integer max2 = IntStream.of(a).boxed().mapToInt(value -> value).max().orElse(Integer.MIN_VALUE);
        Integer max3 = IntStream.of(a).boxed().max((Integer number1, Integer number2) -> number1 - number2).get();
        Integer max4 = IntStream.of(a).boxed().max(MosftOftenNumber::compare).get();
        Integer max5 = IntStream.of(a).boxed().max(Comparator.comparingInt((Integer num) -> num)).get();
        Integer max6 = IntStream.of(a).boxed().max(Comparator.naturalOrder()).get();
        Integer max8 = IntStream.of(a).boxed().max(Integer::compare).get();
        Integer max9 = IntStream.of(a).boxed().max(Integer::compareTo).get();
        Integer max10 = IntStream.of(a).boxed().reduce(Integer::max).get();
        Integer max11 = IntStream.of(a).boxed().reduce(Integer.MIN_VALUE, Integer::max);
        Integer max12 = IntStream.of(a).boxed().collect(Collectors.maxBy(Comparator.naturalOrder())).get();
        Integer max13 = IntStream.of(a).boxed().collect(Collectors.summarizingInt(Integer::intValue)).getMax();
        Integer max14 = IntStream.of(a).boxed().collect(Collectors.reducing(Integer::max)).get();
        Integer max15 = IntStream.of(a).boxed().sorted(Comparator.reverseOrder()).findFirst().get();

        Integer coumtMax = Math.toIntExact(IntStream.of(a).boxed().filter(number -> number.equals(max14)).count());

//        System.out.println("Times repeated: " + coumtMax);
        logger.info("Times repeated: " + coumtMax);
        return max14;
    }

    public static void main(String args[]) {
//      int[] array = {1,1,2,1,1};
//      int[] array = {2,2,1,2,2};
        int[] array = {1,2,3,4,5,6,7,7,7,7};
//        System.out.println("Most often number with loops: " + mostOftenNumber(array));
//        System.out.println("Most often number with lambdas: " + mostOftenNumberWithLambdas(array));
        logger.info("Most often number with loops: " + mostOftenNumber(array));
        logger.info("Most often number with lambdas: " + mostOftenNumberWithLambdas(array));
    }

    private static int compare(Integer number1, Integer number2) {
        return number1 - number2;
    }
}
