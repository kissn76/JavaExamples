package passwordgenerator;

import java.util.Random;

public class PasswordGenerator {

    private final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String DIGITS = "0123456789";
    private final String PUNCTUATION = "!@#$%&*()_+-=[]|,./?><";
    private boolean allowedLower;
    private boolean allowedUpper;
    private boolean allowedDigit;
    private boolean allowedPunctuation;

    public PasswordGenerator() {
        this(false);
    }

    public PasswordGenerator(boolean allowedPunctuation) {
        this(true, true, true, allowedPunctuation);
    }

    public PasswordGenerator(boolean allowedLower, boolean allowedUpper, boolean allowedDigit, boolean allowedPunctuation) {
        this.allowedLower = allowedLower;
        this.allowedUpper = allowedUpper;
        this.allowedDigit = allowedDigit;
        this.allowedPunctuation = allowedPunctuation;
    }

    public void enableLower() {
        allowedLower = true;
    }

    public void disableLower() {
        allowedLower = false;
    }

    public void enableUpper() {
        allowedUpper = true;
    }

    public void disableUpper() {
        allowedUpper = false;
    }

    public void enableDigit() {
        allowedDigit = true;
    }

    public void disableDigit() {
        allowedDigit = false;
    }

    public void enablePunctuation() {
        allowedPunctuation = true;
    }

    public void disablePunctuation() {
        allowedPunctuation = false;
    }

    public String generate() {
        return generate(32);
    }

    public String generate(int length) {
        String password = "";

        for (int i = 0; i < length; i++) {
            password += getNextChar();
        }

        return password;
    }

    private char getNextChar() {
        boolean[] possibility = {allowedLower, allowedUpper, allowedDigit, allowedPunctuation};
        Random random = new Random();
        int i;

        do {
            i = random.nextInt(4);
        } while (!possibility[i]);

        switch (i) {
            case 0:
                return getNextLowerChar();
            case 1:
                return getNextUpperChar();
            case 2:
                return getNextDigitChar();
            case 3:
                return getNextPunctuationChar();
            default:
                return getNextLowerChar();
        }
    }

    private char getNextLowerChar() {
        Random random = new Random();
        return LOWER.charAt(random.nextInt(LOWER.length()));
    }

    private char getNextUpperChar() {
        Random random = new Random();
        return UPPER.charAt(random.nextInt(UPPER.length()));
    }

    private char getNextDigitChar() {
        Random random = new Random();
        return DIGITS.charAt(random.nextInt(DIGITS.length()));
    }

    private char getNextPunctuationChar() {
        Random random = new Random();
        return PUNCTUATION.charAt(random.nextInt(PUNCTUATION.length()));
    }

}
