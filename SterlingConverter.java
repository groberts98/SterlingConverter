import java.util.Scanner;

public class SterlingConverter {
    public static void main(String[] args) {
        SterlingConverter sterlingConverter = new SterlingConverter();

        Scanner sc = new Scanner(System.in);
        String input, output;
        try {
            while(true) {
                input = sc.nextLine();
                output = sterlingConverter.convert(Integer.parseInt(input));
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
        StringBuilder result = new StringBuilder("");
        int[] coinValues = {100, 50, 20, 10, 5, 2, 1};
        int coinAmount;
        for(int i=0; i<coinValues.length; i++) {
            if(total == 0) {
                break;
            }
            coinAmount = total/coinValues[i];
            if(coinAmount != 0) {
                if(coinValues[i] == 100) {
                    result.append(coinAmount + " x £1, ");        
                } else {
                    result.append(coinAmount + " x " + coinValues[i] + "p, ");
                }
            }

            total = total % coinValues[i];
        }

        String output = result.toString();
        output = output == "" ? output : output.substring(0, output.length()-2); //remove the final ", " at the end of the string

        return output;
    }
}
