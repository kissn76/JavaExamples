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
    private int minNumberLower;
    private int minNumberUpper;
    private int minNumberDigit;
    private int minNumberPunctuation;

    private StringBuffer lowers;
    private StringBuffer uppers;
    private StringBuffer digits;
    private StringBuffer punctuations;

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

        this.minNumberLower = 0;
        this.minNumberUpper = 0;
        this.minNumberDigit = 0;
        this.minNumberPunctuation = 0;

        this.lowers = new StringBuffer();
        this.uppers = new StringBuffer();
        this.digits = new StringBuffer();
        this.punctuations = new StringBuffer();
    }

    public void enableLower() {
        this.allowedLower = true;
    }

    public void disableLower() {
        this.allowedLower = false;
    }

    public void enableUpper() {
        this.allowedUpper = true;
    }

    public void disableUpper() {
        this.allowedUpper = false;
    }

    public void enableDigit() {
        this.allowedDigit = true;
    }

    public void disableDigit() {
        this.allowedDigit = false;
    }

    public void enablePunctuation() {
        this.allowedPunctuation = true;
    }

    public void disablePunctuation() {
        this.allowedPunctuation = false;
    }

    public void setEnabledCharacters(boolean allowedLower, boolean allowedUpper, boolean allowedDigit, boolean allowedPunctuation) {
        this.allowedLower = allowedLower;
        this.allowedUpper = allowedUpper;
        this.allowedDigit = allowedDigit;
        this.allowedPunctuation = allowedPunctuation;
    }

    public void setMinNumberLower(int minNumberLower) {
        this.minNumberLower = minNumberLower;
    }

    public void setMinNumberUpper(int minNumberUpper) {
        this.minNumberUpper = minNumberUpper;
    }

    public void setMinNumberDigit(int minNumberDigit) {
        this.minNumberDigit = minNumberDigit;
    }

    public void setMinNumberPunctuation(int minNumberPunctuation) {
        this.minNumberPunctuation = minNumberPunctuation;
    }

    public void setMinNumberCharacters(int minNumberLower, int minNumberUpper, int minNumberDigit, int minNumberPunctuation) {
        this.minNumberLower = minNumberLower;
        this.minNumberUpper = minNumberUpper;
        this.minNumberDigit = minNumberDigit;
        this.minNumberPunctuation = minNumberPunctuation;
    }

    public String generate() {
        return generate(32);
    }

    public String generate(int length) {
        String password = getMinChars();

        for (int i = 0; i < (length - password.length()); i++) {
            password += getNextChar();
        }

        StringBuffer sb = new StringBuffer(password);
        // shuffle strings
        sb.s

        return password;
    }

    private String getMinChars() {
        generateLowers();
        generateUppers();
        generateDigits();
        generatePunctuations();

        StringBuffer minChars = new StringBuffer();
        minChars.append(this.lowers);
        minChars.append(this.uppers);
        minChars.append(this.digits);
        minChars.append(this.punctuations);

        return minChars.toString();
    }

    private void generateLowers() {
        if (this.allowedLower) {
            for (int i = 0; i < this.minNumberLower; i++) {
                this.lowers.append(getNextLowerChar());
            }
        }
    }

    private void generateUppers() {
        if (this.allowedUpper) {
            for (int i = 0; i < this.minNumberUpper; i++) {
                this.uppers.append(getNextUpperChar());
            }
        }
    }

    private void generateDigits() {
        if (this.allowedDigit) {
            for (int i = 0; i < this.minNumberDigit; i++) {
                this.digits.append(getNextDigitChar());
            }
        }
    }

    private void generatePunctuations() {
        if (this.allowedPunctuation) {
            for (int i = 0; i < this.minNumberPunctuation; i++) {
                this.punctuations.append(getNextPunctuationChar());
            }
        }
    }

    private char getNextChar() {
        boolean[] possibility = { this.allowedLower, this.allowedUpper, this.allowedDigit, this.allowedPunctuation };
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
        return this.LOWER.charAt(random.nextInt(this.LOWER.length()));
    }

    private char getNextUpperChar() {
        Random random = new Random();
        return this.UPPER.charAt(random.nextInt(this.UPPER.length()));
    }

    private char getNextDigitChar() {
        Random random = new Random();
        return this.DIGITS.charAt(random.nextInt(this.DIGITS.length()));
    }

    private char getNextPunctuationChar() {
        Random random = new Random();
        return this.PUNCTUATION.charAt(random.nextInt(this.PUNCTUATION.length()));
    }

}
