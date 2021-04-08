import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;

class Runes {
    Runes() {
        ArrayList<String> lines = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        // # of lines
        int numOfLines = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numOfLines; i++) {
            lines.add(scanner.nextLine());
        }
        scanner.close();

        for (String line : lines) {
            Equation eq = parseInput(line);
            System.out.println(eq.solve());
        }
        // This is our answer

        // // Say that we're given a list of variables - how do we represent 4 random
        // // numbers?

        // // We can represent 4 random numbers quite literally using an int of four
        // int amountOfRandoms = 4;

        // // Then we can loop through these scenarios, for each number we must generate
        // a
        // // digit between 0 and 9

        // ArrayList<Integer[]> listOfPossibleRandoms = new ArrayList<Integer[]>();
        // for (int i = 0; i < amountOfRandoms; i++) {
        // // generate array of possibilities of number between one and nine
        // int[] digits = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        // // let's, for example, append it to an array list
        // }
        // // Ok so now let's take a simple case, say 1+?=2
        // // We could have a function that takes in, for example, result,

        // // Ok so we make the two numbers out of arraylists, then
    }

    // How do we parse the input?
    static Equation parseInput(String in) {
        // Say in is `1+1=2`
        // We would split by either +,-,or *
        // Jk, we would just use regex
        Pattern p = Pattern.compile("([-+]?[\\d\\?]+)([*+-])([-+]?[\\d\\?]+)=([-+]?[\\d\\?]+)");
        Matcher m = p.matcher(in);

        if (m.find()) {
            // Ok so we're going to do it the brute force way -- we already have the numbers
            // in their usual forms, so we're just going to for, group.replace('?', i)
            return new Equation(m.group(1), m.group(2).charAt(0), m.group(3), m.group(4));
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        new Runes();
    }
}

class Equation {
    String first;
    Character operand;
    String second;
    String result;

    Equation(String first, Character operand, String second, String result) {
        this.first = first;
        this.operand = operand;
        this.second = second;
        this.result = result;
    }

    int solve() {
        // Basically from 0-9 (possible digits)
        for (int d = 0; d <= 9; d++) {
            // Replace digits of first, second, and result with our current digit
            String f = first.replaceAll("\\?", String.valueOf(d));
            int fNum = Integer.parseInt(f);
            String s = second.replaceAll("\\?", String.valueOf(d));
            int sNum = Integer.parseInt(s);
            String r = result.replaceAll("\\?", String.valueOf(d));
            int rNum = Integer.parseInt(r);
            // System.out.printf("%d%d%d", fNum, sNum, rNum);

            // Then switch operand, if true update out and return
            switch (operand) {
            case '+': {
                if (fNum + sNum == rNum) {
                    return d;
                }
                break;
            }
            case '-': {
                if (fNum - sNum == rNum) {
                    return d;
                }
                break;
            }
            case '*': {
                if (fNum * sNum == rNum) {
                    return d;
                }
                break;
            }
            }
        }
        return -1;
    }
}