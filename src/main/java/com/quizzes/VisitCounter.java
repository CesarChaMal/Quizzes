package com.quizzes;

import java.util.*;
import java.util.stream.*;

// Task:
// The system you work with on a daily basis runs multiple microservices.
// You have been asked to prepare an aggregation that represents the number of user visits
// to all microservices, for use by data analysts.
//
// API:
// VisitCounter class with a single method "count" that returns a map with user visits aggregated.
//
// - The key (userId) as String should be parsable to Long, otherwise skip it.
// - UserStats object might be null, skip such entries.
// - UserStats has an Optional<Long> visitCount field, if not present skip such entries.
// - Input array might contain null or empty maps, handle gracefully.

class UserStats {
    private final Optional<Long> visitCount;

    UserStats(Optional<Long> visitCount) {
        this.visitCount = visitCount;
    }

    public Optional<Long> getVisitCount() {
        return visitCount;
    }
}

class VisitCounter {

    Map<Long, Long> count(Map<String, UserStats>... visits) {
        if (visits == null || visits.length == 0) {
            return Collections.emptyMap();
        }

        return Arrays.stream(visits)
                .filter(Objects::nonNull)
                .flatMap(m -> m.entrySet().stream())
                .filter(e -> e.getKey() != null && e.getKey().matches("\\d+"))
                .filter(e -> e.getValue() != null && e.getValue().getVisitCount().isPresent())
                .collect(Collectors.groupingBy(
                        e -> Long.parseLong(e.getKey()),
                        Collectors.summingLong(e -> e.getValue().getVisitCount().get())
                ));
    }
}
