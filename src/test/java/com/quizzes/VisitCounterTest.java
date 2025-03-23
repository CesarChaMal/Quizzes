package com.quizzes;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class VisitCounterTest {

    private final VisitCounter counter = new VisitCounter();

    @Test
    public void testShouldReturnEmptyMapForNoInput() {
        assertTrue(counter.count().isEmpty());
    }

    @Test
    public void testShouldReturnEmptyMapForNullInput() {
        assertTrue(counter.count((Map<String, UserStats>[]) null).isEmpty());
    }

    @Test
    public void testShouldReturnEmptyMapForNullOnlyVisits() {
        assertTrue(counter.count(null, null).isEmpty());
    }

    @Test
    public void testShouldReturnEmptyMapForVisitsWithEmptyMaps() {
        assertTrue(counter.count(Collections.emptyMap(), Collections.emptyMap()).isEmpty());
    }

    @Test
    public void testShouldReturnEmptyMapForInputsWithUnparseableID() {
        Map<String, UserStats> map = new HashMap<>();
        map.put("abc", new UserStats(Optional.of(3L)));
        assertTrue(counter.count(map).isEmpty());
    }

    @Test
    public void testShouldReturnEmptyMapForInputsWithEmptyOptional() {
        Map<String, UserStats> map = new HashMap<>();
        map.put("1", new UserStats(Optional.empty()));
        assertTrue(counter.count(map).isEmpty());
    }

    @Test
    public void testShouldCountVisitsCorrectly() {
        Map<String, UserStats> map1 = new HashMap<>();
        map1.put("1", new UserStats(Optional.of(5L)));
        map1.put("2", new UserStats(Optional.of(3L)));

        Map<String, UserStats> map2 = new HashMap<>();
        map2.put("2", new UserStats(Optional.of(7L)));
        map2.put("3", new UserStats(Optional.of(4L)));

        Map<Long, Long> result = counter.count(map1, map2);

        assertEquals(Long.valueOf(5), result.get(1L));
        assertEquals(Long.valueOf(10), result.get(2L));
        assertEquals(Long.valueOf(4), result.get(3L));
    }
}
