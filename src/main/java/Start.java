import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class Start {
    public static void main(String[] args) {

        Yahtzee yahtzee = new Yahtzee();

        for (int i = 0; i < args.length; i++) {

            if (args[i].equals("-n")) { // OPTION: Number of Dice, Default is 5
                try {
                    yahtzee.setNumAllDice(Integer.parseInt(args[i + 1]));
                } catch (Exception e) {
                    System.err.println("Invalid / No Argument for Number of Dice");
                }

                i++; // skip the argument specifying the number of dice
                continue; // dont check for other matches in this argument
            }

            // At this point the argument is not a switch (-"..."), so all other arguments are the dice that are out of the game

            if (args.length - i > yahtzee.getNumAllDice()) {
                System.err.println("Can't have " + (args.length - i) + " dice out when there are only " + yahtzee.getNumAllDice());
                System.exit(1);
            }

            String[] outDiceStrings = Arrays.copyOfRange(args, i, args.length); // Get only the arguments containing the dice that are out of game
            List<Integer> outDice = Arrays.stream(outDiceStrings)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            yahtzee.setOutDice(outDice);
            yahtzee.analyze();
            break;
        }
    }
}
