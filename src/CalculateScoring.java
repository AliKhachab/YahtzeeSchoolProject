public class CalculateScoring {
    public static int calculatePlayerScore(String s, Player p) {
        if (s.equals("ones") || s.equals("aces") || s.equals("twos") || s.equals("threes") || s.equals("fours") || s.equals("fives") ||s.equals("sixes")) {
            return calculateUpperScoringPoints(s, p.getDice());
        } else if (s.equals("three of a kind") || s.equals("four of a kind") || s.equals("full house") || s.equals("yahtzee") || s.equals("large straight") || s.equals("small straight") || s.equals("chance")) {
            return calculateLowerScoringPoints(s, p.getDice());
        }
        return -1;
    }
    private static int calculateUpperScoringPoints(String s, Die[] listDice) {
        switch (s) {
            case "ones":
            case "aces": {
                int count = 0;
                for (int i = 0; i < listDice.length; i++) {
                    if (listDice[i].getValue() == 1) count++;

                }
                return count;
            }
            case "twos": {
                int count = 0;
                for (int i = 0; i < listDice.length; i++) {
                    if (listDice[i].getValue() == 2) count++;

                }
                return count*2;
            }
            case "threes": {
                int count = 0;
                for (int i = 0; i < listDice.length; i++) {
                    if (listDice[i].getValue() == 3) count++;
                }

                return count*3;
            }
            case "fours": {
                int count = 0;
                for (int i = 0; i < listDice.length; i++) {
                    if (listDice[i].getValue() == 4) count++;

                }
                return count*4;
            }
            case "fives": {
                int count = 0;
                for (int i = 0; i < listDice.length; i++) {
                    if (listDice[i].getValue() == 5) count++;

                }
                return count*5;
            }
            case "sixes": {
                int count = 0;
                for (int i = 0; i < listDice.length; i++) {
                    if (listDice[i].getValue() == 6) count++;

                }
                return count*6;
            }

            default:
                return -1;
        }
    }

    private static int calculateLowerScoringPoints(String s, Die[] listDice) {
        switch (s) {
            case "no special roll":
                return 0;
            case "chance": {
                int sum = 0;
                for (Die d : listDice) {
                    sum += d.getValue();
                }
                return sum;
            }
            case "three of a kind": {
                String[] list_valid_3OfAKindable = new String[]{"three of a kind", "four of a kind", "yahtzee", "full house"};
                boolean test_for_valid_3OfAKind = false;
                String x = YahtzeeScoring.calculateHand(listDice);
                for (String t : list_valid_3OfAKindable) {
                    if (x.equals(t)) {
                        test_for_valid_3OfAKind = true;
                        break;
                    }
                }
                if (!test_for_valid_3OfAKind) {
                    return 0;
                }
                int sum = 0;
                for (Die d : listDice) {
                    sum += d.getValue();
                }
                return sum;
            }
            case "four of a kind": {
                String[] list_valid_4OfAKindable = new String[]{"four of a kind", "yahtzee"};
                if (!YahtzeeScoring.calculateHand(listDice).equals(list_valid_4OfAKindable[0]) && !YahtzeeScoring.calculateHand(listDice).equals(list_valid_4OfAKindable[1])) {
                    return 0;
                }
                int sum = 0;
                for (Die d : listDice) {
                    sum += d.getValue();
                }
                return sum;
            }
            case "large straight":
                if (YahtzeeScoring.calculateHand(listDice).equals("large straight")) {
                    return 40;
                }
                return 0;
            case "small straight":
                if (YahtzeeScoring.calculateHand(listDice).equals("small straight")) {
                    return 40;
                }
                return 0;
            case "yahtzee":
                if (YahtzeeScoring.calculateHand(listDice).equals("yahtzee")) {
                    return 40;
                }
                return 0;
            case "full house":
                if (YahtzeeScoring.calculateHand(listDice).equals("full house")) {
                    return 40;
                }
                return 0;
        }
        return -1;
    }
}
