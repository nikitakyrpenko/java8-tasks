package com.computation;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Computation {

    private Computation(){}

    public static String filterRecordsByOddIndices(List<String> records) {
       String result = IntStream.range(0, records.size())
                .mapToObj(value -> Map.entry(value + 1, records.get(value)))
                .filter(pair -> pair.getKey() % 2 != 0)
                .map(pair -> String.format("%d.%s, ", pair.getKey(), pair.getValue()))
                .reduce((x, y) -> x + y)
                .get();
        return result.substring(0, result.length()-2);

    }

    public static List<String> sortUppercaseRecordsByDescending(List<String> records){
        return records
                .stream()
                .map(String::toUpperCase)
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());
    }

    public static String parseListOfRecordsToString(List<String> records){
        return records.stream()
                .flatMap(s -> Stream.of(s.split(",")))
                .map(String::trim)
                .reduce((x, y) -> String.format("%s, %s", x, y))
                .get();
    }

    public static Stream<Long> generateRandomLinearCongruentialStream(long seed){
        long a = 25214903917L;
        long c = 11L;
        long m = (long) Math.pow(2, 48);
        return Stream.iterate(seed, x -> (a * x + c) % m );
    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second){
        Stream.Builder<T> resultStream = Stream.builder();

        Iterator<T> firstIterator = first.iterator();
        Iterator<T> secondIterator = second.iterator();

        while (firstIterator.hasNext() && secondIterator.hasNext()){
            resultStream.add(firstIterator.next());
            resultStream.add(secondIterator.next());
        }
        return resultStream.build();
    }

}
