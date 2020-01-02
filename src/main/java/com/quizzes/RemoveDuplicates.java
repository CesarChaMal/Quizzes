package com.quizzes;

import java.util.*;
import java.util.stream.Collectors;

//input: [1,4,4,1,2,5,3]
//expected output: [1,2,3,4,5]
//Collection<Long> removeDuplicatesAndSortAscending(Collection<Long> input)
public class RemoveDuplicates {

    public static void main(String[] args) {
        List<Long> input = new ArrayList<>();
        input.add(new Long(1));
        input.add(new Long(4));
        input.add(new Long(4));
        input.add(new Long(1));
        input.add(new Long(2));
        input.add(new Long(5));
        input.add(new Long(3));
        Collection<Long> result = RemoveDuplicates.removeDuplicatesAndSortAscending(input);
        System.out.println(Arrays.toString(result.toArray()));
    }

    @SuppressWarnings("unchecked")
    private static Collection<Long> removeDuplicatesAndSortAscending(Collection<Long> input) {

// Removing duplicates
// Java 7 
// Ascending 
// Set<Long> uniquesList = new HashSet<>();
//
// for (Long l : input) {
// uniquesList.add(l);
// }
// System.out.println(Arrays.toString(uniquesList.toArray())) ;
// Java 8 
// Ascending 
// Descending 
//        Set<Long> uniquesList = input.stream().collect(Collectors.toSet());
        Set<Long> uniquesList = new HashSet<>(input);
// uniquesList.stream().forEach(System.out::println);

// Ordering
// Java 7 
// Ascending 
// List<Long> orderedList = new ArrayList<>(uniquesList);
// Collections.sort(orderedList);
// Descending 
// Collections.sort(orderedList, new Comparator() {
// @Override
// public int compare(Object o1, Object o2) {
//             Long sb = (Long)o2;
//             Long sa = (Long)o1;
//
//                return sb.compareTo(sa);
// }
// });
// System.out.println(Arrays.toString(orderedList.toArray())) ;

// Java 8 
// Ascending 
// List<Long> orderedList = uniquesList.stream().sorted().collect(Collectors.toList());;
//        List<Long> orderedList = uniquesList.stream().sorted((o1, o2) -> Long.compare(o1, o2)).collect(Collectors.toList());
//        List<Long> orderedList = uniquesList.stream().sorted(Long::compare).collect(Collectors.toList());
        List<Long> orderedList = uniquesList.stream().sorted(Comparator.comparingLong(o -> o)).collect(Collectors.toList());
// Descending
// List<Long> orderedList = uniquesList.stream().sorted((o1, o2) -> Long.compare(o2, o1)).collect(Collectors.toList());
// orderedList.stream().forEach(System.out::println);
        return orderedList;
    }

}
