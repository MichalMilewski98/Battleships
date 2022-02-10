package game;

import java.util.List;
import java.util.Scanner;

public class Input {
    public int x;
    public int y;

    public void getInput(List<Character> columns) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        boolean askForInput = false;
        while (!askForInput) {
            System.out.print("Enter the coordinates: ");
            input = scanner.nextLine();
            askForInput = validateInput(input, columns);
        }
        this.x = Character.getNumericValue(input.charAt(1));
        this.y = columns.indexOf(input.charAt(0));
    }

    private boolean validateInput(String input, List<Character> columns) {
        return input.length() == 2 && columns.contains(input.charAt(0)) && Character.isDigit(input.charAt(1));
    }

}
