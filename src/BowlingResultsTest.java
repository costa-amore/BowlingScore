// A simple example to get you started
// JUnit assertion - the default Java assertion library
// https://junit.org/junit5/

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BowlingResultsTest {

    @Test
    void full_strike_series_score_is_300() {
        int expected = 300;
        int actual = BowlingResults.answer("X|X|X|X|X|X|X|X|X|X||XX");
        assertEquals(expected, actual);
    }

    @Test
    void full_nine_series_score_is_90() {
        int expected = 90;
        int actual = BowlingResults.answer("9-|9-|9-|9-|9-|9-|9-|9-|9-|9-||");
        assertEquals(expected, actual);
    }

    @Test
    void full_five_plus_spare_series_score_is_150() {
        int expected = 150;
        int actual = BowlingResults.answer("5/|5/|5/|5/|5/|5/|5/|5/|5/|5/||5");
        assertEquals(expected, actual);
    }

    @Test
    void twoframes_with_strike_and_7_is_24() {
        int expected = 24;
        int actual = BowlingResults.answer("X|-7|--|--|--|--|--|--|--|--||");
        assertEquals(expected, actual);
    }

    @Test
    void scoring_is_frame_number_results_in_55() {
        int expected = 55;
        int actual = BowlingResults.answer("1-|2-|3-|4-|5-|6-|7-|8-|9-|X||--");
        assertEquals(expected, actual);
    }

    @Test
    void all_misses_results_in_0() {
        int expected = 0;
        int actual = BowlingResults.answer("--|--|--|--|--|--|--|--|--|--||");
        assertEquals(expected, actual);
    }

    @Test
    void frameOne_is_spare_frameTwo_has_value_five_result_is_20() {
        int expected = 20;
        int actual = BowlingResults.answer("5/|5-|--|--|--|--|--|--|--|--||");
        assertEquals(expected, actual);
    }

    @Test
    void oneframe_five_two_followed_by_all_misses_is_7() {
        int expected = 7;
        int actual = BowlingResults.answer("52|--|--|--|--|--|--|--|--|--||");
        assertEquals(expected, actual);
    }

    @Test
    void firstframe_one_two_secondframe_one_two_followed_by_all_misses_is_6() {
        int expected = 6;
        int actual = BowlingResults.answer("12|12|--|--|--|--|--|--|--|--||");
        assertEquals(expected, actual);
    }

    @Test
    void one_spare_all_misses_is_10() {
        int expected = 10;
        int actual = BowlingResults.answer("-/|--|--|--|--|--|--|--|--|--||");
        assertEquals(expected, actual);
    }
    @Test
    void frameOne_is_spare_frameTwo_has_value_three_result_is_16() {
        int expected = 16;
        int actual = BowlingResults.answer("5/|3-|--|--|--|--|--|--|--|--||");
        assertEquals(expected, actual);
    }

    @Test
    void one_strike_all_misses_is_10() {
        int expected = 10;
        int actual = BowlingResults.answer("X|--|--|--|--|--|--|--|--|--||");
        assertEquals(expected, actual);
    }
    @Test
    @Disabled ("next step in our weekly mob")
    void one_strike_fremeTwo_has_value_three_followed_by_all_misses_is_22() {
        int expected = 10;
        int actual = BowlingResults.answer("X|33|--|--|--|--|--|--|--|--||");
        assertEquals(expected, actual);
    }

}