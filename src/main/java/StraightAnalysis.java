import java.util.ArrayList;
import java.util.List;

public class StraightAnalysis extends Analysis {

    StraightAnalysis(int targetNum, int numAllDice, int numRemainingThrows, List<Integer> outDice) {
        this.numAllDice = numAllDice;
        this.numRemainingThrows = numRemainingThrows;
        this.outDiceInitial = outDice;
        this.numIterations = 10000;
    }

    @Override
    public void printResults() {

    }

    @Override
    boolean dieShouldBeKept(int die) {
        return true;
    }
}
