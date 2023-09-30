public interface Constants { //inspiration to use an interface for the constants taken by a Stanford project
    //demonstrating how to make a Yahtzee game in Java. https://github.com/samsondav/Yahtzee/blob/master/YahtzeeConstants.java
    int MAX_DICE = 5; // to make sure that there will be 5 die

    /*
    constants for the scoreboard
     */



    int SCORING_CATEGORIES = 13;
    int GENERAL_CATEGORIES = 17;
    int BONUS_POINTS = 35;

    String[] LIST_CATEGORIES = new String[]{"ones", "twos", "threes", "fours", "fives", "sixes", "three of a kind",
            "four of a kind", "full house", "small straight", "large straight", "yahtzee", "chance"};
    //note to team: caps lock = final variable (as in a constant)

}
