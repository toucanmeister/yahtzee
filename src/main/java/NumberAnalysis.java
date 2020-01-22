import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class NumberAnalysis {

    private int numAllDice;
    private int numRemainingThrows;
    private List<Integer> outDice;
    private List<Integer> outDiceInitial;
    private int numIterations = 100;
    private int targetNum;


    NumberAnalysis(int targetNum, int numAllDice, int numRemainingThrows, List<Integer> outDice) {
        this.targetNum = targetNum;
        this.numAllDice = numAllDice;
        this.numRemainingThrows = numRemainingThrows;
        this.outDiceInitial = outDice;
    }

    public void doAnalysis() {
        List<Integer> numOfHitsEachRound = new ArrayList<>();
        for (int i = 0; i < numIterations; i++) {
            outDice = new ArrayList<>(outDiceInitial);
            for (int roll = 0; roll < numRemainingThrows; roll ++) {
                int[] thrownDice = rollDice();
                for (int die : thrownDice) {
                    if (die == targetNum) {
                        outDice.add(die);
                    }
                }
            }
            numOfHitsEachRound.add(outDice.size());
        }
        System.out.println();
        System.out.println("Average: " + numOfHitsEachRound.stream().mapToInt(Integer::intValue).sum() / numIterations);
        System.out.println("In " + numIterations + " Rounds");
    }

    private int[] rollDice() {
        int[] dice = new int[numAllDice - outDice.size()];
        for (int i=0; i < dice.length; i++) {
            dice[i] = ThreadLocalRandom.current().nextInt(1,7);
        }
        return dice;
    }
}
