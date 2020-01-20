import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class NumberAnalysis {

    private int numAllDice;
    private int numRemainingThrows;
    private int[] outDice;
    private int numIterations = 1;


    NumberAnalysis(int numAllDice, int numRemainingThrows, int[] outDice) {
        this.numAllDice = numAllDice;
        this.numRemainingThrows = numRemainingThrows;
        this.outDice = outDice;
    }

    public void doAnalysis() {
        for (int i = 0; i < numIterations; i++) {
            for (int roll = 1; i <= numRemainingThrows; i++) {
                int[] thrownDice = rollDice();
                for (int die: thrownDice) {
                    System.out.println(die + " ");
                }
                System.out.println();
            }
        }
    }

    private int[] rollDice() {
        int[] dice = new int[numAllDice - outDice.length];
        for (int i=0; i < dice.length; i++) {
            dice[i] = ThreadLocalRandom.current().nextInt(1,7);
        }
        return dice;
    }
}
