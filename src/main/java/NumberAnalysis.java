import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class NumberAnalysis {

    private int numAllDice;
    private int numRemainingThrows;
    private List<Integer> outDice;
    private List<Integer> outDiceInitial;
    private List<Integer> numOfHitsEachRound;
    private int numIterations = 1000;
    private int targetNum;


    NumberAnalysis(int targetNum, int numAllDice, int numRemainingThrows, List<Integer> outDice) {
        this.targetNum = targetNum;
        this.numAllDice = numAllDice;
        this.numRemainingThrows = numRemainingThrows;
        this.outDiceInitial = outDice;
    }

    public void doAnalysis() {
        numOfHitsEachRound = new ArrayList<>();
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
        printResults();
    }

    private void printResults() {
        System.out.println();
        System.out.println("Average: " + numOfHitsEachRound.stream().mapToInt(Integer::intValue).sum() / numIterations);
        System.out.println("In " + numIterations + " Rounds");

        long[] histlist = new long[(numAllDice - outDiceInitial.size()) + 1];
        for (int numHits = 0; numHits < histlist.length; numHits++) {
            int finalNumHits = numHits;
            histlist[numHits] = numOfHitsEachRound.stream()
                    .filter(x->x == finalNumHits).count();
            System.out.println("Iterations with " + numHits + " Hits: " + histlist[numHits]);
        }
    }

    private int[] rollDice() {
        int[] dice = new int[numAllDice - outDice.size()];
        for (int i=0; i < dice.length; i++) {
            dice[i] = ThreadLocalRandom.current().nextInt(1,7);
        }
        return dice;
    }
}
