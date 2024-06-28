package com.quizzes;

import java.io.*;
import java.util.Arrays;

class Result {

    /*
     * Complete the 'longestEvenWord' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING sentence as parameter.
     */

    public static String longestEvenWord(final String sentence) {
        String[] words = sentence.split(" ");
        String longestStr = "";
        int longest = 0;

        for (int i = 0; i < words.length; i++) {
            boolean isAlpha = true;

            for (int j = 0; j < words[i].length(); j++) {
                char ch = words[i].charAt(j);
                if (!((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z'))) {
                    isAlpha = false;
                    break;
                }
            }

            if (isAlpha && words[i].length() % 2 == 0) {
                if (words[i].length() > longest) {
                    longest = words[i].length();
                    longestStr = words[i];
                }
            }
        }
        return longestStr.isEmpty() ? "00" : longestStr;
    }

    public static String longestEvenWord2(final String sentence) {
        String[] words = sentence.split(" ");
        String longestStr = "";

        for (String word : words) {
            // Check if the word contains only English alphabets
            boolean isAlpha = true;
            for (char ch : word.toCharArray()) {
                if (!Character.isLetter(ch)) {
                    isAlpha = false;
                    break;
                }
            }

            if (isAlpha && word.length() % 2 == 0) {
                if (word.length() > longestStr.length()) {
                    longestStr = word;
                }
            }
        }
        return longestStr.isEmpty() ? "00" : longestStr;
    }

    public static String longestEvenWord3(final String sentence) {
        String[] words = sentence.split(" ");
        String longestStr = "";
        for (String word : words) {
            if (word.matches("[a-zA-Z]+") && word.length() % 2 == 0) {
                if (word.length() > longestStr.length()) {
                    longestStr = word;
                }
            }
        }
        return longestStr.isEmpty() ? "00" : longestStr;
    }

    public static String longestEvenWordJava8(final String sentence) {
        return Arrays.stream(sentence.split(" "))
                .filter(word -> word.chars().allMatch(Character::isLetter))
                .filter(word -> word.length() % 2 == 0)
                .max((word1, word2) -> Integer.compare(word1.length(), word2.length()))
                .orElse("00");
    }
}

public class longestEvenWord {
    public static void main(final String[] args) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.txt"));


        final String sentence = bufferedReader.readLine();

//        final String result = Result.longestEvenWord(sentence);
//        final String result = Result.longestEvenWord2(sentence);
//        final String result = Result.longestEvenWord3(sentence);
        final String result = Result.longestEvenWordJava8(sentence);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
