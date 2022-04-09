package org.example;

import java.util.stream.IntStream;

public class MultiplierFinder {

    private static final int MAX_VALUE_FOR_CORRECT_CALCULATION = Integer.MAX_VALUE / 100000 * 4 + 10039;

    public static void main(String[] args) {
        var exclusiveUpperBound = Integer.parseInt(args[0]);
        var sum = new MultiplierFinder().findSumOfNaturalMultipliesLessThan(exclusiveUpperBound);
        System.out.println("Sum of natural numbers divisible by 3 or 5 for the provided upper bound is: " + sum);
    }

    int findSumOfNaturalMultipliesLessThan(int exclusiveUpperBound) {
        if (isNegativeNumber(exclusiveUpperBound)) {
            throw new IllegalArgumentException("Negative number is used as upper bound");
        }
        if (isGreaterThanMaxVALUEForCorrectCalculation(exclusiveUpperBound)) {
            throw new IllegalStateException("Calculated sum is greater than Integer.MAX_VALUE which causes value rolling");
        }
        if (exclusiveUpperBound == 0) {
            return 0;
        }

        return IntStream.range(1, exclusiveUpperBound)
                .filter(naturalNumber -> isDivisibleByThree(naturalNumber) || isDivisibleByFive(naturalNumber))
                .sum();
    }

    private boolean isNegativeNumber(int inclusiveUpperBound) {
        return inclusiveUpperBound < 0;
    }

    private boolean isGreaterThanMaxVALUEForCorrectCalculation(int exclusiveUpperBound) {
        return MAX_VALUE_FOR_CORRECT_CALCULATION < exclusiveUpperBound;
    }

    private boolean isDivisibleByThree(long naturalNumber) {
        return naturalNumber % 3 == 0;
    }

    private boolean isDivisibleByFive(long naturalNumber) {
        return naturalNumber % 5 == 0;
    }
}
