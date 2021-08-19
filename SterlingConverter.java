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
}
