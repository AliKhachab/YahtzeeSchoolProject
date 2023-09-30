import java.util.Arrays;
import java.util.HashMap;


public class YahtzeeScoring {

    public static String calculateHand(Die[] listDice)  {
        YahtzeeScoring.forceFunctionExitIfError(listDice);
        HashMap<Integer, Integer> counterHashMap = getDieValuesAndAmount(listDice);
        int[] keyList = getDieInHand(counterHashMap);
        String x = YahtzeeScoring.calculateHandType(keyList, counterHashMap);
        switch(x) {
            case "one pair":
            case "two pair":
                x = "no special roll";
                break;
            default:
                break;
        }
        return x;
    }

    private static String calculateHandType(int[] keyList, HashMap <Integer, Integer> counterHashMap) {
        switch (counterHashMap.size()) {
            case 1: {
                if (counterHashMap.get(keyList[0]) == 5) {
                    return "yahtzee";
                } else {
                    System.err.println("ERROR: Only 1 item in the hash map but with a count that is not 5. " +
                            "There is a bug revolving the die, accessibility of the die, or the counting. Forced fix.");
                    System.exit(0);
                }
                break;
            }
            case 2: {
                for (int i : keyList) { //rewrite for brevity
                    if (counterHashMap.get(i) == 2 || counterHashMap.get(i) == 3) {
                        return "full house";
                    } else if (counterHashMap.get(i) == 1 || counterHashMap.get(i) == 4) {
                        return "four of a kind";
                    } else {
                        System.err.println("ERROR: There is a bug in case 2 of YahtzeeScoring.java. Fix it");
                        System.exit(0);
                    }
                }
                break;
            }
            case 3: {
                boolean three_checker = false;
                int two_checker = 0;
                for (int i : keyList) {
                    if (counterHashMap.get(i) == 3) {
                        three_checker = true;
                    }
                    if (counterHashMap.get(i) == 2) {
                        two_checker++;
                    }
                }
                if (two_checker == 2 && !three_checker) {
                    return "two pair";
                } else if (two_checker != 2 && three_checker) {
                    return "three of a kind";
                } else {
                    System.err.println("ERROR: Somehow this hand has bypassed the full house checker. Fix it.");
                    System.exit(0);
                }
                break;
            }
            case 4: {
                int two_checker = 0;
                for (int i : keyList) {
                    if (counterHashMap.get(i) == 2) {
                        two_checker++;
                    }
                }
                if (two_checker == 1) {
                    return "one pair";
                } else {
                    System.err.println("ERROR: error found in case 4 of YahtzeeScoring.java.");
                    System.exit(0);
                }
                break;
            }
            case 5: {
                Arrays.sort(keyList);
                try {
                    if (keyList[keyList.length - 1] > 6 || keyList[keyList.length - 1] < 1) {
                        throw new IncompleteListException("ERROR: When hashmap contains 5 unique numbers," +
                                "list of die somehow have values that are less than 1 or more than 6.");
                    }
                } catch(IncompleteListException e) {
                    e.printStackTrace();
                }
                if (keyList[keyList.length-1] - keyList[0] == 4) {
                    return "large straight";
                } else {
                    return "small straight";
                }
            }
            default:
                return "-1";


        }
        return "-1";
    }


    public static int[] getDieInHand(HashMap<Integer, Integer> hm) {
        int[] keyList = new int[hm.size()];
        var keySet = hm.entrySet();
        int i = 0;
        for (var key : keySet) {
            keyList[i] = key.getKey();
            i++;
        }
        return keyList;

    }


    public static HashMap<Integer, Integer> getDieValuesAndAmount(Die[] listDice) {
        /*
            key, value: int, int -- die value, count
                    */
        HashMap<Integer, Integer> counterHashMap = new HashMap<Integer, Integer>();
    /*
    for every die in the list:
    check if the key is in the hash map.
    if it is, increase the value of the count by 1
    if not, set the key:value pair to be die_value:count = 1.

    this is done to decrease big O if possible to a more efficient speed and space complexity while also
    allowing for the hash map to contain less key value pairs which are (number_1_to_6):0, so that we can
    treat the hash map keys a lot more like a set data type/structure and thus have an easier time
    working with computing the best hand.
    */
        for (Die d : listDice) {
            if (counterHashMap.containsKey(d.getValue())) {
                counterHashMap.put(d.getValue(), counterHashMap.get(d.getValue()) + 1 );

            } else {
                counterHashMap.put(d.getValue(), 1);
            }
        }
        return counterHashMap;
    }

    private static int checkListError(final Die[] listDice) {
        try {
            if (listDice.length != Constants.MAX_DICE) {
                throw new IncompleteListException("ERROR: List is not 5 units long.");
            }
            for (int i = 0; i < listDice.length; i++) {
                if (listDice[i] == null || listDice[i].getValue() < 1 || listDice[i].getValue() > 6) {
                    throw new IncompleteListException("ERROR: The die at the following index is null or has an invalid die value: " + i);
                }
            }
        } catch (IncompleteListException e) {
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    private static void forceFunctionExitIfError(final Die[] listDice) {
        if (YahtzeeScoring.checkListError(listDice) == -1) {
            System.err.println("Forcefully ending program. Fix the bug.");
            System.exit(0);
        }
    }
}

class IncompleteListException extends Exception {
    public IncompleteListException(final String message) {
        super(message);
    }

    public IncompleteListException(final IncompleteListException e) {
        super(e.getMessage());
    }
}

