// A simple example to get you started
// JUnit assertion - the default Java assertion library
// https://junit.org/junit5/

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BowlingResultsTest {
    @Test
    void all_misses_results_in_0() {
        int expected = 0;
        int actual = BowlingResults.answer("--|--|--|--|--|--|--|--|--|--||");
        assertEquals(expected, actual);
    }

    public static Stream<Arguments> numbersOnlyExamples(){
        return Stream.of(
                Arguments.of("52|--|--|--|--|--|--|--|--|--||", 7)
                ,Arguments.of("12|12|--|--|--|--|--|--|--|--||", 6)
                ,Arguments.of("--|12|12|--|--|--|--|--|--|--||", 6)
                ,Arguments.of("9-|9-|9-|9-|9-|9-|9-|9-|9-|9-||", 90)
        );
    }

    public static Stream<Arguments> spareExamples(){
        return Stream.of(
                Arguments.of("-/|--|--|--|--|--|--|--|--|--||", 10)
                ,Arguments.of("5/|5-|--|--|--|--|--|--|--|--||", 20)
                ,Arguments.of("5/|-3|--|--|--|--|--|--|--|--||", 13)
                ,Arguments.of("5/|5/|5/|5/|5/|5/|5/|5/|5/|5/||5", 150)

        );
    }

    public static Stream<Arguments> strikeExamples(){
        return Stream.of(
                Arguments.of("X|--|--|--|--|--|--|--|--|--||", 10)
                ,Arguments.of("X|3-|--|--|--|--|--|--|--|--||", 16)
                ,Arguments.of("X|-7|--|--|--|--|--|--|--|--||", 24)
                ,Arguments.of("X|X|X|X|X|X|X|X|X|X||XX", 300)
                ,Arguments.of("1-|2-|3-|4-|5-|6-|7-|8-|9-|X||--",55)
        );
    }

    public static Stream<Arguments> mixedExamples() {
        return Stream.of(
                Arguments.of("12|3/|X|--|--|--|--|--|--|--||", 33)
               ,Arguments.of("12|X|3/|--|--|--|--|--|--|--||", 33)
        );
    }

    public static Stream<Arguments> invalidExamples() {
        return Stream.of(
                Arguments.of("9-|9-|9-|9-|9-|9-|9-|9-|9-|9-||9-", 90)
                //todo: handle this invalid input               ,Arguments.of("", -1)
                //todo: handle not enough frames
                //todo: handle to many frames
                //todo:  not ending with ||
                //todo: last frame strike, but no bonus results
                //todo: last frame spare, but no bonus result
        );
    }

    @ParameterizedTest
    @MethodSource("numbersOnlyExamples")
    void numbersOnlySeries(String formattedInput, int expectedTotalScore) {
        assertEquals(expectedTotalScore, BowlingResults.answer(formattedInput));
    }

    @ParameterizedTest
    @MethodSource("spareExamples")
    void spareSeries(String formattedInput, int expectedTotalScore) {
        assertEquals(expectedTotalScore, BowlingResults.answer(formattedInput));
    }

    @ParameterizedTest
    @MethodSource("strikeExamples")
    void strikeSeries(String formattedInput, int expectedTotalScore) {
        assertEquals(expectedTotalScore, BowlingResults.answer(formattedInput));
    }

    @ParameterizedTest
    @MethodSource("mixedExamples")
    void mixedSeries(String formattedInput, int expectedTotalScore) {
        assertEquals(expectedTotalScore, BowlingResults.answer(formattedInput));
    }

    @ParameterizedTest
    @MethodSource("invalidExamples")
    void invalidSeries(String formattedInput, int expectedTotalScore) {
        assertEquals(expectedTotalScore, BowlingResults.answer(formattedInput));
    }

}