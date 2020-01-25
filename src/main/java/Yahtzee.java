
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class connects the input-managing Start class
 * with the simulation-doing classes. It calls all the
 * simulation-doing classes with the input from Start.
 */
@Data
public class Yahtzee {

    private int numAllDice;
    private int numRemainingThrows;
    private int[] outDice;
    private int targetNum;

    Yahtzee() {
        this.setNumAllDice(5);  // Defaults, can be changed by using Optional Parameters
        this.setNumRemainingThrows(3);
        this.targetNum = -1;
    }

    // Called from main, carries out analysis
    public void analyze() {
        printBasicInfo();
        doNumbersAnalysis();
    }

    private void doNumbersAnalysis() {
        if (targetNum == -1) { // If the targetNum wasn't specified, do analysis for 1 through 6
            for(targetNum = 1; targetNum <= 6; targetNum++) {
                NumberAnalysis numberAnalysis = new NumberAnalysis(targetNum, numAllDice, numRemainingThrows, Arrays.stream(outDice).boxed().collect(Collectors.toList()));
                numberAnalysis.doAnalysis();
            }
        } else { // If the targetNum was specified, only do analysis for that number
            NumberAnalysis numberAnalysis = new NumberAnalysis(targetNum, numAllDice, numRemainingThrows, Arrays.stream(outDice).boxed().collect(Collectors.toList()));
            numberAnalysis.doAnalysis();
        }

    }


    private void printBasicInfo() {
        System.out.print(
                        "Number of Dice: " + numAllDice + "\n" +
                        "Number of remaining Throws: " + numRemainingThrows + "\n" +
                        "Values of Dice that are out: ");
        for (int die: outDice) {
            System.out.print(die + " ");
        }
        System.out.println("\n");
    }
}
