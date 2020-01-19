
import lombok.Data;

import java.util.List;

@Data
public class Yahtzee {

    private int numAllDice;
    private int numRemainingThrows;
    private List<Integer> outDice;

    Yahtzee() {
        this.setNumAllDice(5);  // Defaults, can be changed by using Optional Parameters
        this.setNumRemainingThrows(3);
    }

    // Called from main, carries out analysis
    public void analyze() {
        printBasicInfo();

    }


    private void printBasicInfo() {
        System.out.println(
                        "Number of Dice: " + numAllDice + "\n" +
                        "Remaining Throws: " + numRemainingThrows + "\n" +
                        "Dice that are out: ");
        for (Integer die: outDice) {
            System.out.print(die + " ");
        }
        System.out.println("\n");
    }
}
