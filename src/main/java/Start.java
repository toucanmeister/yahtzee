import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Start {
    public Yahtzee yahtzee;

    Start(String[] args) {
        Yahtzee yahtzee = new Yahtzee();
        List<Integer> outDice = evaluateOptions(args, yahtzee); // Setup yahtzee with options in args, return list of all other arguments (which are the out-of-game dice values)
        yahtzee.setOutDice(outDice);
        yahtzee.analyze();
    }

    public static void main(String[] args) {
        new Start(args);
    }

    public List<Integer> evaluateOptions(String[] args, Yahtzee yahtzee) {
        int endOfOptions = 0;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-n")) { // OPTION: Number of Dice, default is 5
                try {
                    yahtzee.setNumAllDice(Integer.parseUnsignedInt(args[i + 1]));
                } catch (Exception e) {
                    System.err.println("Invalid / No argument for number of dice.\n");
                    System.exit(1);
                }
                i++; // skip the argument specifying the number of dice
                continue; // dont check for other matches in this argument
            }
            if (args[i].equals("-r")) { // OPTION: Number of remaining throws, default is 3
                try {
                    yahtzee.setNumRemainingThrows(Integer.parseUnsignedInt(args[i + 1]));
                } catch (Exception e) {
                    System.err.println("Invalid / No argument for number of remaining throws.\n");
                    System.exit(1);
                }
                i++;
                continue;
            }
            endOfOptions = i; // At this point, i is where the Numbers for the dice that are out the game start
            break;
        }

        checkForOutOfBounds(args.length, endOfOptions, yahtzee); // Check if too many out-of-game-dice have been specified
        List<Integer> outDice = cutArrayToListOfDiceValues(args, endOfOptions);
        checkForInvalidDice(outDice);
        return outDice;
    }

    public static void checkForInvalidDice(List<Integer> dice) {
        for (Integer die: dice) {
            if (die < 1 || die > 6) {
                System.err.println("Invalid argument for out-of-game die: " + die);
                System.exit(1);
            }
        }
    }

    public static void checkForOutOfBounds(int argslength, int startOfDice, Yahtzee yahtzee) {
        if (argslength - startOfDice > yahtzee.getNumAllDice()) {
            System.err.println("Can't have " + (argslength - startOfDice) + " dice out when there's only " + yahtzee.getNumAllDice() + " total.");
            System.exit(1);
        }
    }
    public static List<Integer> cutArrayToListOfDiceValues(String[] args, int startOfDice) {
        String[] diceStrings = Arrays.copyOfRange(args, startOfDice, args.length);
        List<Integer> outDice = new ArrayList<>();
        try {
            outDice = Arrays.stream(diceStrings)
                    .map(Integer::parseUnsignedInt)
                    .collect(Collectors.toList());
        } catch(Exception e) {
            System.err.println("Non-numerical value for out-of-game die.");
            System.exit(1);
        }
        return outDice;
    }
}


