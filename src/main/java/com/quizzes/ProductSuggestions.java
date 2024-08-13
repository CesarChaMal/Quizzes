package com.quizzes;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import io.reactivex.rxjava3.core.Observable;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/*
 * Original Problem Statement:
 *
 * You are given an array of strings `products` representing product names,
 * and a `search` string that represents the prefix being typed by a user.
 * Implement a function that provides product suggestions as the user types
 * each character of the `search` string.
 *
 * The function should return a list of lists, where each inner list contains
 * up to three product suggestions that start with the current prefix formed
 * by typing the characters in `search`. The suggestions for each prefix
 * should be returned in lexicographical (alphabetical) order.
 *
 * Function Signature:
 * public static List<List<String>> getProductSuggestions(List<String> products, String search)
 *
 * Parameters:
 * - List<String> products: A list of strings representing the product names. 
 * - String search: A string representing the search query typed by the user.
 *
 * Returns:
 * - List<List<String>>: A list of lists of strings where each inner list
 *   contains up to three product names starting with the corresponding
 *   prefix in the `search` string.
 *
 * Constraints:
 * - 1 <= products.size() <= 1000
 * - 1 <= products[i].length() <= 300
 * - 1 <= search.length() <= 1000
 * - All characters in `products[i]` and `search` are lowercase English letters.
 *
 * Example:
 *
 * Input:
 * products = ["mobile","mouse","moneypot","monitor","mousepad"]
 * search = "mouse"
 *
 * Output:
 * [
 *     ["mobile","moneypot","monitor"],
 *     ["mobile","moneypot","monitor"],
 *     ["mouse","mousepad"],
 *     ["mouse","mousepad"],
 *     ["mouse","mousepad"]
 * ]
 *
 * Test Cases:
 * 1. Input: products = ["car", "carpet", "cartoon", "cart", "carrot"], search = "car"
 *    Expected Output: [["car", "carpet", "cart"], ["car", "carpet", "carrot"], ["car", "carpet", "carrot"]]
 *
 * 2. Input: products = ["apple", "app", "application", "apricot", "banana"], search = "ap"
 *    Expected Output: [["app", "apple", "application"], ["app", "apple", "application"]]
 *
 * 3. Input: products = ["mobile", "moneypot", "monitor", "mouse", "mousepad"], search = "mo"
 *    Expected Output: [["mobile", "moneypot", "monitor"], ["mobile", "moneypot", "monitor"]]
 *
 * 4. Input: products = ["abc", "ab", "abcd", "abcde", "a"], search = "ab"
 *    Expected Output: [["a", "ab", "abc"], ["ab", "abc", "abcd"]]
 *
 * 5. Input: products = ["dog", "deer", "deal"], search = "de"
 *    Expected Output: [["deal", "deer", "dog"], ["deal", "deer"]]
 */

class ResultProductSuggestions {

    // Version 1: Traditional Loop-based approach
    public static List<List<String>> getProductSuggestions_V1(List<String> products, String search) {
        Collections.sort(products);
        List<List<String>> result = new ArrayList<>();
        String prefix = "";

        System.out.println("Products: " + products);

        for (char c : search.toCharArray()) {
            prefix += c;
            List<String> suggestions = new ArrayList<>();
            for (String product : products) {
                if (product.startsWith(prefix)) {
				//if (product.startsWith(prefix) && product.length() > prefix.length() - 1) {
                    suggestions.add(product);
                }
                if (suggestions.size() == 3)
                    break;
            }
            System.out.println("Prefix: " + prefix + ", Suggestions: " + suggestions);
            result.add(suggestions);
        }
        return result;
    }

    // Version 2: Functional Programming approach with Java Streams
    public static List<List<String>> getProductSuggestions_V2(List<String> products, String search) {
        products.sort(Comparator.naturalOrder());
        List<String> prefixes = new ArrayList<>();
        String[] prefixHolder = {""};

        System.out.println("Products: " + products);

        search.chars().mapToObj(c -> {
            prefixHolder[0] += (char) c;
            return prefixHolder[0];
        }).forEach(prefixes::add);

        List<List<String>> result = prefixes.stream()
            .map(prefix -> {
                List<String> suggestions = products.stream()
                    .filter(product -> product.startsWith(prefix))
					//.filter(product -> product.startsWith(prefix) && product.length() > prefix.length() - 1)
                    .limit(3)
                    .collect(toList());
                System.out.println("Prefix: " + prefix + ", Suggestions: " + suggestions);
                return suggestions;
            })
            .collect(toList());

        return result;
    }

    // Version 3: Using RxJava for Reactive Programming
	public static List<List<String>> getProductSuggestions_RxJava(List<String> products, String search) {
		Collections.sort(products);

        System.out.println("Products: " + products);

		return Observable.fromIterable(search.chars().mapToObj(c -> Character.toString((char) c)).collect(Collectors.toList()))
			.scan("", (prefix, c) -> prefix + c)
			.skip(1)  // Skip the first empty prefix created by scan
			.map(prefix -> {
				List<String> suggestions = products.stream()
					.filter(product -> product.startsWith(prefix))
					//.filter(product -> product.startsWith(prefix) && product.length() > prefix.length() - 1)
					.limit(3)
					.collect(Collectors.toList());
				System.out.println("Prefix: " + prefix + ", Suggestions: " + suggestions);
				return suggestions;
			})
			.toList()
			.blockingGet();
	}

    // Improved Version 1: On-the-Fly Prefix Processing with StringBuilder
    public static List<List<String>> getProductSuggestions_Improved1(List<String> products, String search) {
        products.sort(Comparator.naturalOrder());
        StringBuilder prefixBuilder = new StringBuilder();

        System.out.println("Products: " + products);

        return search.chars()
            .mapToObj(c -> {
                prefixBuilder.append((char) c);
                String prefix = prefixBuilder.toString();

                List<String> suggestions = products.stream()
                    .filter(product -> product.startsWith(prefix))
					//.filter(product -> product.startsWith(prefix) && product.length() > prefix.length() - 1)
                    .limit(3)
                    .collect(toList());
                System.out.println("Prefix: " + prefix + ", Suggestions: " + suggestions);
                return suggestions;
            })
            .collect(toList());
    }

    // Improved Version 2: Parallel Stream Processing for Large Datasets
    public static List<List<String>> getProductSuggestions_Improved2(List<String> products, String search) {
        products.sort(Comparator.naturalOrder());
        StringBuilder prefixBuilder = new StringBuilder();

        System.out.println("Products: " + products);

        return search.chars()
            .mapToObj(c -> {
                prefixBuilder.append((char) c);
                String prefix = prefixBuilder.toString();

                List<String> suggestions = products.parallelStream()  // Use parallel stream for large datasets
                    .filter(product -> product.startsWith(prefix))
					//.filter(product -> product.startsWith(prefix) && product.length() > prefix.length() - 1)
                    .limit(3)
                    .collect(toList());
                System.out.println("Prefix: " + prefix + ", Suggestions: " + suggestions);
                return suggestions;
            })
            .collect(toList());
    }
}

public class ProductSuggestions {
    public static void main(String[] args) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.txt"));

        int productsCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> products = IntStream.range(0, productsCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .collect(toList());

        String search = bufferedReader.readLine();

        // You can call any version of the getProductSuggestions method here:
        // List<List<String>> result = Result.getProductSuggestions_V1(products, search); // for version 1
        // List<List<String>> result = Result.getProductSuggestions_V2(products, search); // for version 2
        // List<List<String>> result = Result.getProductSuggestions_RxJava(products, search); // for RxJava version
        // List<List<String>> result = Result.getProductSuggestions_Improved1(products, search); // for improved version 1
        List<List<String>> result = ResultProductSuggestions.getProductSuggestions_Improved2(products, search); // for improved version 2

        result.stream()
            .map(
                r -> r.stream()
                    .collect(joining(" "))
            )
            .map(r -> r + "\n")
            .collect(toList())
            .forEach(e -> {
                try {
                    bufferedWriter.write(e);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
