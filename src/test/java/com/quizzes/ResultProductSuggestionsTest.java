package com.quizzes;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResultProductSuggestionsTest {

    // Helper method for testing getProductSuggestions methods
    private void runTest(List<String> products, String search, List<List<String>> expected,
                         java.util.function.BiFunction<List<String>, String, List<List<String>>> function) {
        List<List<String>> actual = function.apply(products, search);
        Assert.assertEquals(expected, actual);
    }

    // Test Case 1
	private final List<String> products1 = Arrays.asList("car", "carpet", "cartoon", "cart", "carrot");
	private final String search1 = "car";
	private final List<List<String>> expected1 = Arrays.asList(
			Arrays.asList("car", "carpet", "carrot"),
			Arrays.asList("car", "carpet", "carrot"),
			Arrays.asList("car", "carpet", "carrot")
	);

	// Test Case 2
    private final List<String> products2 = Arrays.asList("apple", "app", "application", "apricot", "banana");
    private final String search2 = "ap";
    private final List<List<String>> expected2 = Arrays.asList(
            Arrays.asList("app", "apple", "application"),
            Arrays.asList("app", "apple", "application")
    );

    // Test Case 3
    private final List<String> products3 = Arrays.asList("mobile", "moneypot", "monitor", "mouse", "mousepad");
    private final String search3 = "mo";
    private final List<List<String>> expected3 = Arrays.asList(
            Arrays.asList("mobile", "moneypot", "monitor"),
            Arrays.asList("mobile", "moneypot", "monitor")
    );

    // Test Case 4
    private final List<String> products4 = Arrays.asList("abc", "ab", "abcd", "abcde", "a");
    private final String search4 = "ab";
    private final List<List<String>> expected4 = Arrays.asList(
			Arrays.asList("a", "ab", "abc"),
			Arrays.asList("ab", "abc", "abcd")
	);

    // Test Case 5
    private final List<String> products5 = Arrays.asList("dog", "deer", "deal");
    private final String search5 = "de";
    private final List<List<String>> expected5 = Arrays.asList(
            Arrays.asList("deal", "deer", "dog"),
            Arrays.asList("deal", "deer")
    );

    @Test
    public void testGetProductSuggestions_V1() {
        runTest(products1, search1, expected1, ResultProductSuggestions::getProductSuggestions_V1);
        runTest(products2, search2, expected2, ResultProductSuggestions::getProductSuggestions_V1);
        runTest(products3, search3, expected3, ResultProductSuggestions::getProductSuggestions_V1);
        runTest(products4, search4, expected4, ResultProductSuggestions::getProductSuggestions_V1);
        runTest(products5, search5, expected5, ResultProductSuggestions::getProductSuggestions_V1);
    }

    @Test
    public void testGetProductSuggestions_RxJava() {
        runTest(products1, search1, expected1, ResultProductSuggestions::getProductSuggestions_RxJava);
        runTest(products2, search2, expected2, ResultProductSuggestions::getProductSuggestions_RxJava);
        runTest(products3, search3, expected3, ResultProductSuggestions::getProductSuggestions_RxJava);
        runTest(products4, search4, expected4, ResultProductSuggestions::getProductSuggestions_RxJava);
        runTest(products5, search5, expected5, ResultProductSuggestions::getProductSuggestions_RxJava);
    }

    @Test
    public void testGetProductSuggestions_V2() {
        runTest(products1, search1, expected1, ResultProductSuggestions::getProductSuggestions_V2);
        runTest(products2, search2, expected2, ResultProductSuggestions::getProductSuggestions_V2);
        runTest(products3, search3, expected3, ResultProductSuggestions::getProductSuggestions_V2);
        runTest(products4, search4, expected4, ResultProductSuggestions::getProductSuggestions_V2);
        runTest(products5, search5, expected5, ResultProductSuggestions::getProductSuggestions_V2);
    }

    @Test
    public void testGetProductSuggestions_Improved1() {
        runTest(products1, search1, expected1, ResultProductSuggestions::getProductSuggestions_Improved1);
        runTest(products2, search2, expected2, ResultProductSuggestions::getProductSuggestions_Improved1);
        runTest(products3, search3, expected3, ResultProductSuggestions::getProductSuggestions_Improved1);
        runTest(products4, search4, expected4, ResultProductSuggestions::getProductSuggestions_Improved1);
        runTest(products5, search5, expected5, ResultProductSuggestions::getProductSuggestions_Improved1);
    }

    @Test
    public void testGetProductSuggestions_Improved2() {
        runTest(products1, search1, expected1, ResultProductSuggestions::getProductSuggestions_Improved2);
        runTest(products2, search2, expected2, ResultProductSuggestions::getProductSuggestions_Improved2);
        runTest(products3, search3, expected3, ResultProductSuggestions::getProductSuggestions_Improved2);
        runTest(products4, search4, expected4, ResultProductSuggestions::getProductSuggestions_Improved2);
        runTest(products5, search5, expected5, ResultProductSuggestions::getProductSuggestions_Improved2);
    }

    // Stress Test Helper
    private void stressTestGetProductSuggestions(String search,
                                                 java.util.function.BiFunction<List<String>, String, List<List<String>>> function,
                                                 String version) {
        List<String> products = generateLargeProductList();
        long startTime = System.currentTimeMillis();
        function.apply(products, search);
        long endTime = System.currentTimeMillis();
        System.out.println(version + " Execution Time: " + (endTime - startTime) + " ms");
    }

    @Test
    public void stressTestGetProductSuggestions_V1() {
        stressTestGetProductSuggestions("product", ResultProductSuggestions::getProductSuggestions_V1, "V1");
    }

    @Test
    public void stressTestGetProductSuggestions_V2() {
        stressTestGetProductSuggestions("product", ResultProductSuggestions::getProductSuggestions_V2, "V2");
    }

    @Test
    public void stressTestGetProductSuggestions_RxJava() {
        stressTestGetProductSuggestions("product", ResultProductSuggestions::getProductSuggestions_RxJava, "RxJava");
    }

    @Test
    public void stressTestGetProductSuggestions_Improved1() {
        stressTestGetProductSuggestions("product", ResultProductSuggestions::getProductSuggestions_Improved1, "Improved1");
    }

    @Test
    public void stressTestGetProductSuggestions_Improved2() {
        stressTestGetProductSuggestions("product", ResultProductSuggestions::getProductSuggestions_Improved2, "Improved2");
    }

    // Helper method to generate a large list of product names
    private List<String> generateLargeProductList() {
        return IntStream.range(0, 1000)
                .mapToObj(i -> "product" + i)
                .collect(Collectors.toList());
    }
}
