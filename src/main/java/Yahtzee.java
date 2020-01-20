
import lombok.Data;

import java.util.List;

@Data
public class Yahtzee {

    private int numAllDice;
    private int numRemainingThrows;
    private int[] outDice;

    Yahtzee() {
        this.setNumAllDice(5);  // Defaults, can be changed by using Optional Parameters
        this.setNumRemainingThrows(3);
    }

    // Called from main, carries out analysis
    public void analyze() {
        printBasicInfo();
        doNumbersAnalysis();
    }

    private void doNumbersAnalysis() {
        NumberAnalysis numberAnalysis = new NumberAnalysis(numAllDice, numRemainingThrows, outDice);
        numberAnalysis.doAnalysis();
    }


    private void printBasicInfo() {
        System.out.println(
                        "Number of Dice: " + numAllDice + "\n" +
                        "Number of remaining Throws: " + numRemainingThrows + "\n" +
                        "Values of Dice that are out: ");
        for (int die: outDice) {
            System.out.print(die + " ");
        }
    }
}
