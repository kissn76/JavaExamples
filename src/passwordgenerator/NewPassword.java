package passwordgenerator;

public class NewPassword {

    public static void main(String[] args) {
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        System.out.println(passwordGenerator.generate());
    }

}
