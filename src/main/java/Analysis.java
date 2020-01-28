import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Analysis {
    int numAllDice;
    int numRemainingThrows;
    List<Integer> outDice;
    List<Integer> outDiceInitial;
    List<Integer> numOfHitsEachRound;
    int numIterations = 10000;

    int[] rollDice() {
        int[] dice = new int[numAllDice - outDice.size()];
        for (int i=0; i < dice.length; i++) {
            dice[i] = ThreadLocalRandom.current().nextInt(1,7);
        }
        return dice;
    }

    abstract public void doAnalysis();
    abstract void printResults();
}
