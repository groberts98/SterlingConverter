import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SterlingConverter {
    public static void main(String[] args) {
        SterlingConverter sterlingConverter = new SterlingConverter();

        Scanner sc = new Scanner(System.in);
        String input, output;
        while(sc.hasNextLine()) {
            input = sc.nextLine();
            output = sterlingConverter.checkValidInput(input) ?
                        input + " = " + sterlingConverter.convert(sterlingConverter.formatInput(input)) :
                        "Invalid input";
            System.out.println(output);
        }
        sc.close();
    }

    /**
     * checks that the user input is of a valid form
     * 
     * @param input command line input from user
     * @return whether or not the input is valid
     */
    public boolean checkValidInput(String input) {
        Pattern pattern = Pattern.compile("^[£]?[0-9][0-9]*[.]?[0-9]*[p]?$|^[£]?[0-9]*[.]?[0-9][0-9]*[p]?$");
        Matcher matcher = pattern.matcher(input);
        return(matcher.find());
    }

    /**
     * formats the user input by converting it to pence
     * 
     * @param input validated user input
     * @return input value converted to pence
     */
    public int formatInput(String input) {               
        int value = 0;
        input = input.charAt(input.length()-1) == 'p' ? input.substring(0, input.length()-1) : input;
        boolean containsPound = input.charAt(0) == '\u00A3';
        if(input.contains(".")) {
            input = containsPound ? input.substring(1) : input;
            String[] parts = input.split("\\.");
            int pounds = 0, pennies = 0;
            if (parts.length > 0) {
                //if pounds section exists and is non empty, extract the value
                pounds = parts[0] == "" ? 0 : Integer.parseInt(parts[0]);
            }
            if(parts.length == 2) {
                if(parts[1].length() == 1) {
                    //if the pence section has only 1 digit, multiply it by 10
                    pennies = 10*Integer.parseInt(parts[1]);
                } else if(parts[1].length() > 2) {
                    pennies = Integer.parseInt(parts[1].substring(0, 2));
                    if(Character.getNumericValue(parts[1].charAt(2)) > 4) {
                        //if the pence section is longer than two digits and the third digit is greater than 4, round up.
                        pennies ++;
                    }
                } else {
                    pennies = Integer.parseInt(parts[1]);
                }
            }
            value = 100*pounds + pennies;
        } else {
            //if the input has no point and has a pound symbol, multiply value by 100
            value = containsPound ? 100*Integer.parseInt(input.substring(1)) : Integer.parseInt(input);
        }

        return value;
    }

    /**
     * converts value in pence to sterling coins
     * 
     * @param total value of the user input in pence 
     * @return String of user input converted to sterling coin
     */
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
        output = output == "" ? "0" : output.substring(0, output.length()-2); //remove the final ", " at the end of the string

        return output;
    }
}
