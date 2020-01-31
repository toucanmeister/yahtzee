import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class runs simulations for
 * the upper part of a Yahtzee scoreboard.
 * It also prints the results of it's simulations as a diagram.
 */
public class NumberAnalysis extends Analysis {

    private int targetNum;

    NumberAnalysis(int targetNum, int numAllDice, int numRemainingThrows, List<Integer> outDice) {
        this.targetNum = targetNum;
        this.numAllDice = numAllDice;
        this.numRemainingThrows = numRemainingThrows;
        this.outDiceInitial = outDice;
        this.numIterations = 10000;
    }

    @Override
    public void printResults() {
        System.out.println("Analysis for target number: " + targetNum);

        double numOfHitsTotal = dataEachRound.stream()
                .mapToInt(list-> (int) list.stream()
                        .filter(x->x==targetNum)
                        .count())
                .sum();
        System.out.println("Average: " + numOfHitsTotal / (double) numIterations);
        System.out.println("In " + numIterations + " Rounds");
        printDiagram();
    }

    @Override
    boolean dieShouldBeKept(int die) {
        return die == targetNum;
    }

    private void printDiagram() {
        long[] hitlist = new long[numAllDice+1];
        for (int numHits = 0; numHits < hitlist.length; numHits++) { // This loop fills hitlist[] (which is then used for printing the histogram)
            int finalNumHits = numHits;
            hitlist[numHits] = dataEachRound.stream()
                    .mapToInt(list -> (int) list.stream()
                            .filter(x->x==targetNum)
                            .count())
                    .filter(x->x == finalNumHits).count();
        }
        for (int numHits = 0; numHits < hitlist.length; numHits++) { // This loop actually prints the diagram
            System.out.print(numHits + "  ");
            int numOfCharsToPrint = (int) ( (double) hitlist[numHits] / (numIterations / 100));
            System.out.printf("%-50s | %.2f%s \n", "#".repeat(numOfCharsToPrint), ((double) hitlist[numHits]) / ((double) numIterations) * 100, "%");
        }
        System.out.println();
    }
}
