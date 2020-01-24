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
    private int numIterations = 10000;
    private int targetNum;


    NumberAnalysis(int targetNum, int numAllDice, int numRemainingThrows, List<Integer> outDice) {
        this.targetNum = targetNum;
        this.numAllDice = numAllDice;
        this.numRemainingThrows = numRemainingThrows;
        this.outDiceInitial = outDice;
    }

    public void doAnalysis() {
        numOfHitsEachRound = new ArrayList<>();
        for (int i = 0; i < numIterations; i++) { // Going through the iterations
            outDice = new ArrayList<>(outDiceInitial); // For saving the dice that have been taken out-of-game in this iteration
            for (int roll = 0; roll < numRemainingThrows; roll ++) { // Going through the rolls in each iteration
                int[] thrownDice = rollDice();
                for (int die : thrownDice) {
                    if (die == targetNum) {
                        outDice.add(die);
                    }
                }
            }
            numOfHitsEachRound.add(outDice.size()); // Save data of this iteration
        }
        printResults();
    }

    private void printResults() {
        System.out.println();
        System.out.println("Average: " + ((double) numOfHitsEachRound.stream().mapToInt(Integer::intValue).sum()) / ((double) numIterations));
        System.out.println("In " + numIterations + " Rounds");
        printDiagram();
    }

    private void printDiagram() {
        long[] hitlist = new long[numAllDice+1];
        for (int numHits = 0; numHits < hitlist.length; numHits++) {
            int finalNumHits = numHits;
            hitlist[numHits] = numOfHitsEachRound.stream()
                    .filter(x->x == finalNumHits).count();
        }

        double maximum = Arrays.stream(hitlist).max().orElse(1);
        for (int numHits = 0; numHits < hitlist.length; numHits++) {
            System.out.print(numHits + "  ");
            int numOfCharsToPrint = (int) ( (double) hitlist[numHits] / (numIterations / 100));
            System.out.printf("%-50s | %s \n", "#".repeat(numOfCharsToPrint), ((double) hitlist[numHits]) / ((double) numIterations) * 100);
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
