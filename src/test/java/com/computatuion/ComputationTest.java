package com.computatuion;

import com.computation.Computation;
import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ComputationTest {

    @Test
    public void filterRecordsByOddIndicesShouldReturnProperStringResultForRecordsWith4Elements(){
        List<String> records = Arrays.asList("John", "Tom", "Bob","Mark");
        String result = Computation.filterRecordsByOddIndices(records);
        Assert.assertThat(result, is(equalTo("1.John, 3.Bob")));
    }

    @Test(expected = NoSuchElementException.class)
    public void filterRecordsByOddIndicesShouldThrowNoSuchElementExceptionIfRecordIsEmpty(){
        List<String> records = Collections.emptyList();
        Computation.filterRecordsByOddIndices(records);
    }

    @Test
    public void filterRecordsByOddIndicesShouldReturnProperStringResultForRecordsWith1Element(){
        List<String> records = Collections.singletonList("John");
        String result = Computation.filterRecordsByOddIndices(records);
        Assert.assertThat(result, is(equalTo("1.John")));
    }

    @Test
    public void parseListOfRecordsToStringShouldReturnProperStringValueFor5Elements(){
        List<String> records = Arrays.asList("1, 2, 0", "4, 5");
        String result = Computation.parseListOfRecordsToString(records);
        Assert.assertThat(result, is(equalTo("1, 2, 0, 4, 5")));
    }

    @Test
    public void parseListOfRecordsToStringShouldReturnProperStringValueFor1Element(){
        List<String> records = Collections.singletonList("1");
        String result = Computation.parseListOfRecordsToString(records);
        Assert.assertThat(result, is(equalTo("1")));
    }

    @Test(expected = NoSuchElementException.class)
    public void parseListOfRecordsToStringShouldThrowNoSuchElementExceptionForEmptyList(){
        List<String> records = Collections.emptyList();
        Computation.parseListOfRecordsToString(records);
    }

    @Test
    public void zipShouldReturnProperConcatenationOfTwoStreams(){
        Stream<String> firstStream = Stream.of("1","3","5");
        Stream<String> secondStream = Stream.of("2","4","6");
        List<String> result = Computation.zip(firstStream, secondStream).collect(Collectors.toList());
        Assert.assertThat(result, is(equalTo(Arrays.asList("1","2","3","4","5","6"))));
    }

    @Test
    public void zipShouldReturnProperConcatenationOfTwoNotEqualsByLengthStreams(){
        Stream<String> firstStream = Stream.of("1");
        Stream<String> secondStream = Stream.of("2","4","6");
        List<String> result = Computation.zip(firstStream, secondStream).collect(Collectors.toList());
        Assert.assertThat(result, is(equalTo(Arrays.asList("1","2"))));
    }

    @Test
    public void zipShouldReturnEmptyStreamWhenOneStreamIsEmpty(){
        Stream<String> firstStream = Stream.of("2","4","6");
        Stream<String> secondStream = Stream.of();
        List<String> result = Computation.zip(firstStream, secondStream).collect(Collectors.toList());
        Assert.assertThat(result, is(equalTo(Collections.emptyList())));
    }

    @Test
    public void generateRandomLinearCongruentialStreamShouldGenerateStreamNumbers(){
        List<Long> result = Computation
                .generateRandomLinearCongruentialStream(0)
                .limit(100).collect(Collectors.toList());
        Assert.assertThat(result.size(), is(equalTo(100)));
    }

    @Test
    public void sortUppercaseRecordsByDescendingShouldReturnUppercaseListSortedByDescending(){
        List<String> records = Arrays.asList("Aa", "BC", "Ac","dA");
        List<String> result = Computation.sortUppercaseRecordsByDescending(records);
        Assert.assertThat(result, is(equalTo(Arrays.asList("DA", "BC", "AC", "AA"))));
    }

    @Test
    public void sortUppercaseRecordsByDescendingShouldReturnSameListUppercaseIfItIsAlreadySorted(){
        List<String> records = Arrays.asList("d","c","b","a");
        List<String> result = Computation.sortUppercaseRecordsByDescending(records);
        Assert.assertThat(result, is(equalTo(Arrays.asList("D", "C", "B", "A"))));
    }
    @Test
    public void sortUppercaseRecordsByDescendingShouldEmptyListIfParameterIsEmptyList(){
        List<String> records = Collections.emptyList();
        List<String> result = Computation.sortUppercaseRecordsByDescending(records);
        Assert.assertThat(result, is(equalTo(Collections.emptyList())));
    }
}
