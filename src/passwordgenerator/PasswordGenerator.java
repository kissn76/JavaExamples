package passwordgenerator;

import java.util.Random;

import tools.StringTools;

/**
 * Wide configurable password generator.
 *
 * @author nn
 */
public class PasswordGenerator {

    private final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String DIGITS = "0123456789";
    private final String PUNCTUATION = "!@#$%&*()_+-=[]|,./?><";
    private int defaultLength = 32;
    private boolean allowedLower;
    private boolean allowedUpper;
    private boolean allowedDigit;
    private boolean allowedPunctuation;
    private int minNumberLower;
    private int minNumberUpper;
    private int minNumberDigit;
    private int minNumberPunctuation;

    /**
     * Create a password generator object. Punctuation is disabled by default.
     */
    public PasswordGenerator() {
        this(-1);
    }

    /**
     * Enable or disable and set the minimum number of punctuation in password.
     * -1 disable punctuation
     * 0 enable punctuation, but doesn't matter the number
     * >0 the minimum number of punctuation
     *
     * @param numberOfPunctuation
     */
    public PasswordGenerator(int numberOfPunctuation) {
        this(0, 0, 0, numberOfPunctuation);
    }

    /**
     * Enable or disable and set the minimum number of each character type in password.
     * -1 disable character type
     * 0 enable character type, but doesn't matter the number
     * >0 the minimum number of character type
     *
     * @param numberOfLower
     * @param numberOfUpper
     * @param numberOfDigit
     * @param numberOfPunctuation
     */
    public PasswordGenerator(int numberOfLower, int numberOfUpper, int numberOfDigit, int numberOfPunctuation) {
        this.allowedLower = numberOfLower < 0 ? false : true;
        this.minNumberLower = this.allowedLower ? numberOfLower : 0;

        this.allowedUpper = numberOfUpper < 0 ? false : true;
        this.minNumberUpper = this.allowedUpper ? numberOfUpper : 0;

        this.allowedDigit = numberOfDigit < 0 ? false : true;
        this.minNumberDigit = this.allowedDigit ? numberOfDigit : 0;

        this.allowedPunctuation = numberOfPunctuation < 0 ? false : true;
        this.minNumberPunctuation = this.allowedPunctuation ? numberOfPunctuation : 0;
    }

    /**
     * Enable lower case characters in password.
     */
    public void enableLower() {
        this.allowedLower = true;
    }

    /**
     * Disable lower case characters in password.
     */
    public void disableLower() {
        this.allowedLower = false;
    }

    /**
     * Enable upper case characters in password.
     */
    public void enableUpper() {
        this.allowedUpper = true;
    }

    /**
     * Disable upper case characters in password.
     */
    public void disableUpper() {
        this.allowedUpper = false;
    }

    /**
     * Enable digit characters in password.
     */
    public void enableDigit() {
        this.allowedDigit = true;
    }

    /**
     * Disable digit characters in password.
     */
    public void disableDigit() {
        this.allowedDigit = false;
    }

    /**
     * Enable punctuation characters in password.
     */
    public void enablePunctuation() {
        this.allowedPunctuation = true;
    }

    /**
     * Disable punctuation characters in password.
     */
    public void disablePunctuation() {
        this.allowedPunctuation = false;
    }

    /**
     * Enable or disable each character type in password.
     *
     * @param allowedLower
     * @param allowedUpper
     * @param allowedDigit
     * @param allowedPunctuation
     */
    public void setEnabledCharacterTypes(boolean allowedLower, boolean allowedUpper, boolean allowedDigit, boolean allowedPunctuation) {
        this.allowedLower = allowedLower;
        this.allowedUpper = allowedUpper;
        this.allowedDigit = allowedDigit;
        this.allowedPunctuation = allowedPunctuation;
    }

    /**
     * Setting the minimum number of lower case charater in password.
     *
     * @param minNumberLower
     */
    public void setMinNumberLower(int minNumberLower) {
        this.minNumberLower = minNumberLower;
    }

    /**
     * Setting the minimum number of upper case charater in password.
     *
     * @param minNumberUpper
     */
    public void setMinNumberUpper(int minNumberUpper) {
        this.minNumberUpper = minNumberUpper;
    }

    /**
     * Setting the minimum number of digit charater in password.
     *
     * @param minNumberDigit
     */
    public void setMinNumberDigit(int minNumberDigit) {
        this.minNumberDigit = minNumberDigit;
    }

    /**
     * Setting the minimum number of punctuation charater in password.
     *
     * @param minNumberPunctuation
     */
    public void setMinNumberPunctuation(int minNumberPunctuation) {
        this.minNumberPunctuation = minNumberPunctuation;
    }

    /**
     * Setting the minimum number of each character type in password.
     *
     * @param minNumberLower
     * @param minNumberUpper
     * @param minNumberDigit
     * @param minNumberPunctuation
     */
    public void setMinNumberCharacters(int minNumberLower, int minNumberUpper, int minNumberDigit, int minNumberPunctuation) {
        this.minNumberLower = minNumberLower;
        this.minNumberUpper = minNumberUpper;
        this.minNumberDigit = minNumberDigit;
        this.minNumberPunctuation = minNumberPunctuation;
    }

    /**
     * Generate a random password with the adjusted parameters. The length of password will be 32
     * characters.
     *
     * @return                         Specified random password.
     * @throws PasswordLengthException
     */
    public String generate() throws PasswordLengthException {
        return generate(this.defaultLength);
    }

    /**
     * Generate a random password with the adjusted parameters. The length of password will be the value
     * of parameter.
     *
     * @param  length                  The length of the generated password.
     * @return                         Specified random password.
     * @throws PasswordLengthException
     */
    public String generate(int length) throws PasswordLengthException {
        int minLength = getMinLength();
        if (minLength > length) {
            throw new PasswordLengthException(minLength, length);
        }
        // Set minimum characters
        String password = getAllowedRandomString(this.minNumberLower, this.minNumberUpper, this.minNumberDigit, this.minNumberPunctuation);

        int x = length - password.length(); // Ennyi karakter kell még a beállított hosszhoz
        for (int i = 0; i < x; i++) {
            password += getAllowedRandomChar();
        }

        return StringTools.shuffle(password);   // Shuffle the string
    }

    /*
     * Get minimum length of password specified by minmum number of each character type.
     */
    private int getMinLength() {
        int minLength = 0;

        minLength += this.allowedLower ? this.minNumberLower : 0;
        minLength += this.allowedUpper ? this.minNumberUpper : 0;
        minLength += this.allowedDigit ? this.minNumberDigit : 0;
        minLength += this.allowedPunctuation ? this.minNumberPunctuation : 0;

        return minLength;
    }

    /*
     * Return a random character from all allowed list.
     */
    private char getAllowedRandomChar() {
        String str = getAllowedRandomString(1, 1, 1, 1);    // egy string 1-1 véletlen karakterrel minden engedélyezett típusból
        int index = new Random().nextInt(str.length());     // random index: szám 0-tól az előbbi string hosszáig tartományból
        return str.charAt(index);                           // a stringből az előbbi random indexü karakter
    }

    /*
     * Return a random String with specified number of each character type.
     */
    private String getAllowedRandomString(int numberOfLower, int numberOfUpper, int numberOfDigit, int numberOfPunctuation) {
        String minChars = "";

        if (this.allowedLower) {
            minChars += getRandomCharacters(numberOfLower, this.LOWER);
        }

        if (this.allowedUpper) {
            minChars += getRandomCharacters(numberOfUpper, this.UPPER);
        }

        if (this.allowedDigit) {
            minChars += getRandomCharacters(numberOfDigit, this.DIGITS);
        }

        if (this.allowedPunctuation) {
            minChars += getRandomCharacters(numberOfPunctuation, this.PUNCTUATION);
        }

        return minChars;
    }

    /*
     * Return a random generated string. The length and used character list from the parameters.
     */
    private String getRandomCharacters(int minNumberOfCharacter, String charList) {
        String str = "";
        Random random = new Random();
        for (int i = 0; i < minNumberOfCharacter; i++) {
            str += charList.charAt(random.nextInt(charList.length()));
        }
        return str;
    }

    /**
     * Password minimum length, specified minimum number of each character type is longer then parameter
     * represented length.
     */
    @SuppressWarnings ("serial")
    class PasswordLengthException extends Exception {

        public PasswordLengthException(int minLength, int length) {
            super("Sum of minimum number of character type is higher (" + minLength + ") then password length (" + length + ")!");
        }

    }

}
