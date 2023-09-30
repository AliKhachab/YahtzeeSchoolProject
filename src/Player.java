import java.util.HashMap;

public class Player implements Constants {
    private String name;
    private int score;
    private Die[] dice;
    private final HashMap<String, Integer> pointsMap = new HashMap<>();

    private boolean hasUpdatedYahtzeeCategory = false;

    private boolean finishedAllCategories = false;

    private String[] list_completed_categories;

    private boolean hasBonus = false;

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
        this.dice = new Die[Constants.MAX_DICE];
        for (int i = 0; i < Constants.MAX_DICE; i++) {
            this.dice[i] = new Die(1);
        }

        for (int i = 0; i < Constants.LIST_CATEGORIES.length; i++) {
            this.pointsMap.put(Constants.LIST_CATEGORIES[i], -1);
        }

        list_completed_categories = new String[Constants.LIST_CATEGORIES.length];
    }

    public String getName() {
        return name;
    }

    public boolean isHasBonus() {
        return hasBonus;
    }

    public void setHasBonus(boolean hasBonus) {
        this.hasBonus = hasBonus;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setHasUpdatedYahtzeeCategoryToTrue() {
        this.hasUpdatedYahtzeeCategory = true; // this case will never be false. if the player has clicked
        //the button to select their roll as a yahtzee, this must be true.
    }

    public boolean isHasUpdatedYahtzeeCategory() {
        return hasUpdatedYahtzeeCategory;
    }

    public void updatePointsAtCategory(String s, int points) {
        this.pointsMap.put(s, points);

    }

    public Die[] getDice() {
        Die[] temp = new Die[this.dice.length];
        for (int i = 0; i < this.dice.length; i++){
            temp[i] = new Die(this.dice[i]);
        }
        return temp;
    }
    public void setDice(Die[] listDice) {
        for (int i = 0; i < 5; i++) {
            this.dice[i] = new Die(listDice[i]);
        }
    }

    public void rollDice() {
        for (int i = 0; i < this.dice.length; i++) {
            this.dice[i].roll();
        }
    }

    public HashMap<String, Integer> getPointsMap() {
        HashMap<String, Integer> tempHashMap = new HashMap<>();
        for (String s : Constants.LIST_CATEGORIES) {
            tempHashMap.put(s, this.pointsMap.get(s));
        }
        return tempHashMap;
    }

    public boolean checkIfPlayerHasFinished() {
        for (int i = 0; i < Constants.LIST_CATEGORIES.length; i++) {
            if (this.pointsMap.get(Constants.LIST_CATEGORIES[i]) == -1) {
                return false;
            }
        }

        return true;
    }

    public int calculateCurrentScore() {
        int x = 0;
        for (String s : Constants.LIST_CATEGORIES) {
            if (this.getPointsMap().get(s) == -1) {
                x += 0;
            } else {
                x+=this.getPointsMap().get(s);
            }
        }
        return x;
    }

    public void checkIfBonus() {
        int sum = 0;
        for (int i = 0; i < 6; i++) {
            sum+=this.pointsMap.get(Constants.LIST_CATEGORIES[i]);
        }
        if (sum >= 35) { this.hasBonus = true; this.score+=35; }
        else { this.hasBonus = false; }
    }



    @Override
    public String toString() {
        return "Player " + this.name + " has a total of " + this.score + " points!\n";
    }
}
