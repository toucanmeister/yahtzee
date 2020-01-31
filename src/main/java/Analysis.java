import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Analysis {
    int numAllDice;
    int numRemainingThrows;
    List<Integer> outDice;
    List<Integer> outDiceInitial;
    List<List<Integer>> dataEachRound;
    int numIterations;

    public void doAnalysis() {
        dataEachRound = new ArrayList<>();
        for (int i = 0; i < numIterations; i++) { // Going through the iterations
            outDice = new ArrayList<>(outDiceInitial); // For saving the dice that have been taken out-of-game in this iteration
            for (int roll = 0; roll < numRemainingThrows; roll ++) { // Going through the rolls in each iteration
                int[] thrownDice = rollDice();
                for (int die : thrownDice) {
                    if (dieShouldBeKept(die)) {
                        outDice.add(die);
                    }
                }
            }
            dataEachRound.add(outDice); // Save data of this iteration
        }
    }

    int[] rollDice() {
        int[] dice = new int[numAllDice - outDice.size()];
        for (int i=0; i < dice.length; i++) {
            dice[i] = ThreadLocalRandom.current().nextInt(1,7);
        }
        return dice;
    }

    abstract public void printResults();
    abstract boolean dieShouldBeKept(int die);
}
