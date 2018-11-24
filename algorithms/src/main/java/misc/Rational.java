package misc;

public class Rational {
    private final int numerator;
    private final int denominator;

    public Rational(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    /**
     * Greatest common divider.
     *
     * @param left
     * @param right
     * @return
     */
    public int findGCD(int left, int right) {
        int b = Math.max(left, right) % Math.min(left, right);
        if (b == 0) {
            return right;
        }
        return findGCD(Math.min(left, right), b);
    }

    /**
     * Least common multiplier.
     *
     * @param left
     * @param right
     * @return
     */
    public int findLCM(int left, int right) {
        return (left * right) / findGCD(left, right);
    }

    private Rational normalize() {
        int gcd = findGCD(this.numerator, this.denominator);
        return new Rational(
                this.numerator / gcd,
                this.denominator / gcd
        );
    }

    public Rational plus(Rational value) {
        final int lcm = findLCM(getDenominator(), value.getDenominator());
        final int left = lcm / getDenominator();
        final int right = lcm / value.getDenominator();
        return new Rational(
                (getNumerator() * left) + (value.getNumerator() * right),
                lcm
        );
    }

    public Rational minus(Rational value) {
        final int lcm = findLCM(getDenominator(), value.getDenominator());
        final int left = lcm / getDenominator();
        final int right = lcm / value.getDenominator();
        return new Rational(
                (getNumerator() * left) - (value.getNumerator() * right),
                lcm
        );
    }

    public Rational divide(Rational value) {
        return new Rational(
                getNumerator() * value.getDenominator(),
                getDenominator() * value.getNumerator()
        );
    }

    public Rational times(Rational value) {
        return new Rational(
                getNumerator() * value.getNumerator(),
                getDenominator() * value.getDenominator()
        );
    }

    public boolean equals(Object value) {
        if (value.getClass() != Rational.class) {
            return false;
        }
        final Rational r = (Rational) value;
        final Rational normalizedLeft = normalize();
        final Rational normalizedRight = r.normalize();
        return normalizedLeft.getNumerator() == normalizedRight.getNumerator() &&
                normalizedLeft.getDenominator() == normalizedRight.getDenominator();
    }
}
