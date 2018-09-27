package passwordgenerator;

public class NewPassword {

    public static void main(String[] args) throws Exception {
        PasswordGenerator passwordGenerator = new PasswordGenerator(4, 4, 4, 4);
        passwordGenerator.disablePunctuation();
        System.out.println(passwordGenerator.generate());
    }

}
