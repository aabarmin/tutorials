package misc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RationalTest {
    @Test
    void createRational() {
        final Rational rational = new Rational(1, 2);
        assertEquals(1, rational.getNumerator());
        assertEquals(2, rational.getDenominator());
    }

    @Test
    void testPlusObvious() {
        final Rational rational1 = new Rational(1, 2);
        final Rational sum = rational1.plus(new Rational(1, 2));
        assertTrue(sum.equals(new Rational(2, 2)));
    }

    @Test
    void testPlusComplex() {
        final Rational rational1 = new Rational(1, 2);
        final Rational sum = rational1.plus(new Rational(1, 2));
        assertTrue(sum.equals(new Rational(1, 1)));
    }

    @Test
    void testPlusMoreComplex() {
        final Rational first = new Rational(1, 2);
        final Rational result = first.plus(new Rational(1, 3));
        assertTrue(result.equals(new Rational(5, 6)));
    }

    @Test
    void testMinusComplex() {
        final Rational first = new Rational(1, 2);
        final Rational result = first.minus(new Rational(2, 3));
        assertTrue(result.equals(new Rational(2, 6)));
    }

    @Test
    void testDivideComplex() {
        final Rational first = new Rational(1, 2);
        final Rational result = first.divide(new Rational(1, 3));
        assertTrue(result.equals(new Rational(3, 2)));
    }

    @Test
    void testMultiplication() {
        final Rational first = new Rational(1, 2);
        final Rational result = first.times(new Rational(1, 3));
        assertTrue(result.equals(new Rational(1, 6)));
    }

    @Test
    void testFindGreatestCommonDivider() {
        final Rational rational = new Rational(1, 2);
        assertEquals(21, rational.findGCD(1071, 462));
    }

    @Test
    void testFindLeastCommonMultiple() {
        final Rational rational = new Rational(10, 20);
        assertEquals(6, rational.findLCM(2, 3));
    }
}