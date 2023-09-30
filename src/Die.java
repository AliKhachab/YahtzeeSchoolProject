import java.awt.*;
import javax.swing.*;

public class Die {
    private int value = 1;

    public Die(int value) {
        this.value = value;
    }

    public Die(Die die) {
        if (die == null || die.getValue() < 1 || die.getValue() > 6) {
            System.out.println("ERROR: die is null or die value is invalid.");
            System.exit(1);
        }
        this.value = die.getValue();
    }

    public int getValue() {
        return this.value;
    }

    private void setValue(int value) {
        this.value = value;
    }

    private static int generateRandomDieValue() {
        return (int) (Math.random() * (6 - 1 + 1)) + 1;
    }

    public void roll() {
        this.setValue(generateRandomDieValue());
    }

    @Override
    public String toString() {
        return "Die with value [" + this.value + "].";
    }
}