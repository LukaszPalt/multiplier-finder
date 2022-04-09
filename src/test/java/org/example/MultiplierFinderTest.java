package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MultiplierFinderTest {

    private MultiplierFinder multiplierFinder;

    @BeforeEach
    void setUpMultiplierFinder() {
        this.multiplierFinder = new MultiplierFinder();
    }

    @Test
    void shouldThrowExceptionWhenNegativeNumberIsProvided() {
        // Given
        var upperBound = -1;

        // When
        var illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> multiplierFinder.findSumOfNaturalMultipliesLessThan(upperBound));

        // Then
        assertEquals("Negative number is used as upper bound", illegalArgumentException.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCalculatedSumIsGreaterThanMaxInteger() {
        // Given
        var upperBound = Integer.MAX_VALUE - 1;

        // When
        var illegalStateException = assertThrows(IllegalStateException.class,
                () -> multiplierFinder.findSumOfNaturalMultipliesLessThan(upperBound));

        // Then
        assertEquals("Calculated sum is greater than Integer.MAX_VALUE which causes value rolling", illegalStateException.getMessage());
    }

    @ParameterizedTest
    @MethodSource("provideUpperBoundAndExpectedSum")
    void shouldReturnExpectedSumForTheUpperBound(int upperBound, int expectedSum) {
        // When
        var actualSum = multiplierFinder.findSumOfNaturalMultipliesLessThan(upperBound);

        // Then
        assertEquals(expectedSum, actualSum);
    }

    private static Stream<Arguments> provideUpperBoundAndExpectedSum() {
        return Stream.of(
                Arguments.of(0, 0),
                Arguments.of(10, 23),
                Arguments.of(12, 33),
                Arguments.of(15, 45)
        );
    }
}
