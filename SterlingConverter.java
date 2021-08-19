import java.util.Scanner;

public class SterlingConverter {
    public static void main(String[] args) {
        SterlingConverter sterlingConverter = new SterlingConverter();

        Scanner sc = new Scanner(System.in);
        String input, output;
        try {
            while(true) {
                input = sc.nextLine();
                output = input;
                System.out.println(output);
            }
        } finally {
            sc.close();
        }
    }

    //function to check that the user input is of a valid form
    public boolean checkValidInput(String input) {
        return(true);
    }

    //function to format the user input
    public int formatInput(String input) {               
        return 0;
    }

    //function to convert pennies to sterling coins
    public String convert(int total) {
        return "output";
    }
}
