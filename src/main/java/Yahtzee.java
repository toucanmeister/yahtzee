import java.util.List;

public class Yahtzee {

    private int numAllDice;
    private List<Integer> outDice;

    Yahtzee() {
        this.numAllDice = 5;
    }

    public void analyze() {
        System.out.println(
                "Number of Dice: " + numAllDice + "\n" +
                "Dice that are out: ");
        for (Integer die: outDice) {
            System.out.print(die + " ");
        }

        System.out.println("\n");
    }

    // Getters and Setters

    public int getNumAllDice() {
        return numAllDice;
    }

    public void setNumAllDice(int numAllDice) {
        this.numAllDice = numAllDice;
    }

    public List<Integer> getOutDice() {
        return outDice;
    }

    public void setOutDice(List<Integer> outDice) {
        this.outDice = outDice;
    }
}
