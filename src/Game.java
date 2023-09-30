import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.InvalidPropertiesFormatException;
import java.util.HashMap;

public class Game extends JFrame implements Constants, ActionListener {

    private Player[] listPlayers;
    JToggleButton jFirstDieTButton, jSecondDieTButton, jThirdDieTButton, jFourthDieTButton, jFifthDieTButton;
    JToggleButton[] tButtons;

    JButton jOnesButton, jTwosButton, jThreesButton, jFoursButton, jFivesButton, jSixesButton, j3OfAKindButton, j4OfAKindButton, jFullHouseButton, jSmallStrButton, jLargeStrButton, jYahtzeeButton, jChanceButton, jRollButton;
    JButton[] buttons;

    JTextArea jBonusTArea, jGrandTotalTField, jOnesTArea, jTwosTArea, jThreesTArea, jFoursTArea, jFivesTArea, jSixesTArea, j3OfAKindTArea, j4OfAKindTArea, jFullHouseTArea, jSmallStrTArea, jLargeStrTArea, jYahtzeeTArea, jChanceTArea;
    JTextArea[] textAreas;
    String current_pick;
    boolean[] current_die_chosen = new boolean[]{false, false, false, false, false};
    int curr_player;
    JPanel topPanel, centerPanel, leftPanel, rightPanel, bottomPanel;

    int num_rolls = 0;

    JLabel jBonusLabel, jGrandTotalLabel, JDieLabel;

    JTextArea[] category_text_areas;
    private boolean bonus_check_checked = false;

    private void setup() {
        //game begins
        this.setTitle("Yahtzee!");
        this.setCurrent_pick("empty");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        this.setResizable(true);

        //Panels, Toggle Buttons, and respective Text Fields, Areas, & Labels

        topPanel = new JPanel();

        jBonusLabel = new JLabel("Bonus (If > 62): ");
        jBonusLabel.setPreferredSize(new Dimension(100, 60));
        jBonusTArea = new JTextArea("0");
        jBonusTArea.setPreferredSize(new Dimension(20, 20));
        jBonusTArea.setEditable(false);

        //Creating Left Panel and Assigning Buttons & TextFields
        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
        leftPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.getContentPane().add(leftPanel);

        jOnesButton = new JButton("Ones");
        jOnesButton.setPreferredSize(new Dimension(200, 60));
        jOnesButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        jOnesTArea = new JTextArea("0");
        jOnesTArea.setPreferredSize(new Dimension(100, 60));
        jOnesTArea.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        jOnesTArea.setEditable(false);
        jOnesButton.addActionListener(this);

        jTwosButton = new JButton("Twos");
        jTwosButton.setPreferredSize(new Dimension(200, 60));
        jTwosButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        jTwosTArea = new JTextArea("0");
        jTwosTArea.setPreferredSize(new Dimension(100, 60));
        jTwosTArea.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        jTwosTArea.setEditable(false);
        jTwosButton.addActionListener(this);

        jThreesButton = new JButton("Threes");
        jThreesButton.setPreferredSize(new Dimension(200, 60));
        jThreesButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        jThreesTArea = new JTextArea("0");
        jThreesTArea.setPreferredSize(new Dimension(100, 60));
        jThreesTArea.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        jThreesTArea.setEditable(false);
        jThreesButton.addActionListener(this);

        jFoursButton = new JButton("Fours");
        jFoursButton.setPreferredSize(new Dimension(200, 60));
        jFoursButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        jFoursTArea = new JTextArea("0");
        jFoursTArea.setPreferredSize(new Dimension(100, 60));
        jFoursTArea.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        jFoursTArea.setEditable(false);
        jFoursButton.addActionListener(this);

        jFivesButton = new JButton("Fives");
        jFivesButton.setPreferredSize(new Dimension(200, 60));
        jFivesButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        jFivesTArea = new JTextArea("0");
        jFivesTArea.setPreferredSize(new Dimension(100, 60));
        jFivesTArea.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        jFivesTArea.setEditable(false);
        jFivesButton.addActionListener(this);

        jSixesButton = new JButton("Sixes");
        jSixesButton.setPreferredSize(new Dimension(200, 60));
        jSixesButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        jSixesTArea = new JTextArea("0");
        jSixesTArea.setPreferredSize(new Dimension(100, 60));
        jSixesTArea.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        jSixesTArea.setEditable(false);
        jSixesButton.addActionListener(this);

        //Creating Right Panel and Assigning Buttons & TextFields
        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
        rightPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.getContentPane().add(rightPanel);

        j3OfAKindButton = new JButton("3 of a Kind");
        j3OfAKindButton.setPreferredSize(new Dimension(200, 60));
        j3OfAKindButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        j3OfAKindTArea = new JTextArea("0");
        j3OfAKindTArea.setPreferredSize(new Dimension(150, 60));
        j3OfAKindTArea.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        j3OfAKindTArea.setEditable(false);
        j3OfAKindButton.addActionListener(this);

        j4OfAKindButton = new JButton("4 of a Kind");
        j4OfAKindButton.setPreferredSize(new Dimension(200, 60));
        j4OfAKindButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        j4OfAKindTArea = new JTextArea("0");
        j4OfAKindTArea.setPreferredSize(new Dimension(100, 60));
        j4OfAKindTArea.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        j4OfAKindTArea.setEditable(false);
        j4OfAKindButton.addActionListener(this);

        jFullHouseButton = new JButton("Full House");
        jFullHouseButton.setPreferredSize(new Dimension(200, 60));
        jFullHouseButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        jFullHouseTArea = new JTextArea("0");
        jFullHouseTArea.setPreferredSize(new Dimension(100, 60));
        jFullHouseTArea.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        jFullHouseTArea.setEditable(false);
        jFullHouseButton.addActionListener(this);

        jSmallStrButton = new JButton("Small Straight");
        jSmallStrButton.setPreferredSize(new Dimension(200, 60));
        jSmallStrButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        jSmallStrTArea = new JTextArea("0");
        jSmallStrTArea.setPreferredSize(new Dimension(100, 60));
        jSmallStrTArea.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        jSmallStrTArea.setEditable(false);
        jSmallStrButton.addActionListener(this);

        jLargeStrButton = new JButton("Large Straight");
        jLargeStrButton.setPreferredSize(new Dimension(200, 60));
        jLargeStrButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        jLargeStrTArea = new JTextArea("0");
        jLargeStrTArea.setPreferredSize(new Dimension(100, 60));
        jLargeStrTArea.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        jLargeStrTArea.setEditable(false);
        jLargeStrButton.addActionListener(this);

        jYahtzeeButton = new JButton("Yahtzee!");
        jYahtzeeButton.setPreferredSize(new Dimension(200, 60));
        jYahtzeeButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        jYahtzeeTArea = new JTextArea("0");
        jYahtzeeTArea.setPreferredSize(new Dimension(100, 60));
        jYahtzeeTArea.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        jYahtzeeTArea.setEditable(false);
        jYahtzeeButton.addActionListener(this);

        jChanceButton = new JButton("Chance");
        jChanceButton.setPreferredSize(new Dimension(200, 60));
        jChanceButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        jChanceTArea = new JTextArea("0");
        jChanceTArea.setPreferredSize(new Dimension(100, 60));
        jChanceTArea.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        jChanceTArea.setEditable(false);
        jChanceButton.addActionListener(this);

        category_text_areas = new JTextArea[]{jOnesTArea, jTwosTArea, jThreesTArea, jFoursTArea, jFivesTArea, jSixesTArea, j3OfAKindTArea, j4OfAKindTArea, jFullHouseTArea, jSmallStrTArea, jLargeStrTArea, jYahtzeeTArea, jChanceTArea};

        //Creating Center Panel and its Labels & TextFields
        centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout());

        jGrandTotalLabel = new JLabel("Grand Total: ");
        jGrandTotalLabel.setPreferredSize(new Dimension(80, 60));
        jGrandTotalTField = new JTextArea("0");
        jGrandTotalTField.setPreferredSize(new Dimension(20, 20));
        jGrandTotalTField.setEditable(false);

        //Creating Bottom Panel and its Buttons & TextAreas
        bottomPanel = new JPanel();

        JDieLabel = new JLabel("Die: ");

        jFirstDieTButton = new JToggleButton();
        jFirstDieTButton.setPreferredSize(new Dimension(100, 60));
        jFirstDieTButton.addItemListener((ItemEvent e) -> {
            int i = 0;
            if (e.getStateChange() == ItemEvent.SELECTED) {
                current_die_chosen[i] = true;
                System.out.println("die " + i + " button pressed on");
            } else {
                current_die_chosen[i] = false;
                System.out.println("die " + i + " button pressed off");
            }
        });


        jSecondDieTButton = new JToggleButton();
        jSecondDieTButton.setPreferredSize(new Dimension(100, 60));
        jSecondDieTButton.addItemListener((ItemEvent e) -> {
            int i = 1;
            if (e.getStateChange() == ItemEvent.SELECTED) {
                current_die_chosen[i] = true;
                System.out.println("die " + i + " button pressed on");
            } else {
                current_die_chosen[i] = false;
                System.out.println("die " + i + " button pressed off");
            }
        });

        jThirdDieTButton = new JToggleButton();
        jThirdDieTButton.setPreferredSize(new Dimension(100, 60));
        jThirdDieTButton.addItemListener((ItemEvent e) -> {
            int i = 2;
            if (e.getStateChange() == ItemEvent.SELECTED) {
                current_die_chosen[i] = true;
                System.out.println("die " + i + " button pressed on");
            } else {
                current_die_chosen[i] = false;
                System.out.println("die " + i + " button pressed off");
            }
        });

        jFourthDieTButton = new JToggleButton();
        jFourthDieTButton.setPreferredSize(new Dimension(100, 60));
        jFourthDieTButton.addItemListener((ItemEvent e) -> {
            int i = 3;
            if (e.getStateChange() == ItemEvent.SELECTED) {
                current_die_chosen[i] = true;
                System.out.println("die " + i + " button pressed on");
            } else {
                current_die_chosen[i] = false;
                System.out.println("die " + i + " button pressed off");
            }
        });

        jFifthDieTButton = new JToggleButton();
        jFifthDieTButton.setPreferredSize(new Dimension(100, 60));
        jFifthDieTButton.addItemListener((ItemEvent e) -> {
            int i = 4;
            if (e.getStateChange() == ItemEvent.SELECTED) {
                current_die_chosen[i] = true;
                System.out.println("die " + i + " button pressed on");
            } else {
                current_die_chosen[i] = false;
                System.out.println("die " + i + " button pressed off");
            }
        });

        jRollButton = new JButton("Roll");
        jRollButton.addActionListener(this);

        //Creating List of Buttons, Toggle Buttons, and TextFields
        buttons = new JButton[]{jOnesButton, jTwosButton, jThreesButton, jFoursButton, jFivesButton, jSixesButton, j3OfAKindButton, j4OfAKindButton,
                jFullHouseButton, jSmallStrButton, jLargeStrButton, jYahtzeeButton, jChanceButton, jRollButton};

        tButtons = new JToggleButton[]{jFirstDieTButton, jSecondDieTButton, jThirdDieTButton, jFourthDieTButton, jFifthDieTButton};

        textAreas = new JTextArea[]{jOnesTArea, jTwosTArea, jThreesTArea, jFoursTArea, jFivesTArea, jSixesTArea,
                j3OfAKindTArea, j4OfAKindTArea, jFullHouseTArea, jSmallStrTArea, jLargeStrTArea, jYahtzeeTArea, jChanceTArea};

        //Adding Contents to Top Panel
        topPanel.add(jBonusLabel);
        topPanel.add(jBonusTArea);

        //Adding Contents to Left Panel
        leftPanel.add(jOnesButton);
        leftPanel.add(jOnesTArea);
        leftPanel.add(jTwosButton);
        leftPanel.add(jTwosTArea);
        leftPanel.add(jThreesButton);
        leftPanel.add(jThreesTArea);
        leftPanel.add(jFoursButton);
        leftPanel.add(jFoursTArea);
        leftPanel.add(jFivesButton);
        leftPanel.add(jFivesTArea);
        leftPanel.add(jSixesButton);
        leftPanel.add(jSixesTArea);

        //Adding Contents to Right Panel
        rightPanel.add(j3OfAKindButton);
        rightPanel.add(j3OfAKindTArea);
        rightPanel.add(j4OfAKindButton);
        rightPanel.add(j4OfAKindTArea);
        rightPanel.add(jFullHouseButton);
        rightPanel.add(jFullHouseTArea);
        rightPanel.add(jSmallStrButton);
        rightPanel.add(jSmallStrTArea);
        rightPanel.add(jLargeStrButton);
        rightPanel.add(jLargeStrTArea);
        rightPanel.add(jYahtzeeButton);
        rightPanel.add(jYahtzeeTArea);
        rightPanel.add(jChanceButton);
        rightPanel.add(jChanceTArea);

        //Adding Contents to Center Panel
        centerPanel.add(jGrandTotalLabel);
        centerPanel.add(jGrandTotalTField);

        //Adding Contents to Bottom Panel
        bottomPanel.add(JDieLabel);
        bottomPanel.add(jFirstDieTButton);
        bottomPanel.add(jSecondDieTButton);
        bottomPanel.add(jThirdDieTButton);
        bottomPanel.add(jFourthDieTButton);
        bottomPanel.add(jFifthDieTButton);
        bottomPanel.add(jRollButton);

        //Adding Panels to BorderLayout
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public Game() {
        setup();
        current_pick = "update layout";

        //load game data from start/reset
        listPlayers = new Player[]{new Player("Player 1", 0)};


        while (!checkGameOver()) {

            switch (current_pick) {
                case "roll": {
                    if (num_rolls == 0) {
                        jRollButton.setEnabled(true);
                        playTurn(listPlayers[0]);
                        setDieEnabled();
                        setCurrent_pick("empty");
                        num_rolls++; // first roll
                        setGameRelevantButtonsToFocusable();
                        this.jRollButton.setEnabled(true);
                    } else if (num_rolls == 1) {
                        playTurn(listPlayers[0]);
                        setCurrent_pick("empty");
                        num_rolls++; // second roll
                        setGameRelevantButtonsToFocusable();
                        this.jRollButton.setEnabled(true);
                    } else if (num_rolls == 2) {
                        playTurn(listPlayers[0]);
                        setCurrent_pick("empty");
                        num_rolls++; //third roll
                        setGameRelevantButtonsToFocusable();
                        setDieToNotSelectedNorEnabled();
                        this.jRollButton.setEnabled(false);
                    }
                    System.out.println(num_rolls);
                    break;
                }
                case "ones", "twos", "threes", "fours",
                        "fives", "sixes", "three of a kind",
                        "four of a kind", "full house",
                        "small straight", "large straight", "chance", "yahtzee": {
                    HashMap<String, Integer> tempHashMap = listPlayers[0].getPointsMap();
                    try {
                        setDieToNotSelectedNorEnabled();
                        setDieEnabled();
                        jRollButton.setEnabled(false);
//                        System.err.println(tempHashMap.get(current_pick));
                        if (tempHashMap.get(current_pick) == -1) {
                            listPlayers[0].updatePointsAtCategory(current_pick,
                                    CalculateScoring.calculatePlayerScore(current_pick, listPlayers[0]));
                            System.out.println(listPlayers[0].getPointsMap().get(current_pick));
                            listPlayers[0].setScore(listPlayers[0].getScore() + listPlayers[0].getPointsMap().get(current_pick));
                        } else {
                            JOptionPane.showMessageDialog(null, "Input is invalid. Please try again..");
                            throw new InvalidChoiceException("catch new invalid input exception error");
                        }
                    } catch (InvalidChoiceException e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Invalid input -- you already scored here!");
                        setCurrent_pick("empty");
                    }
                    num_rolls = 0;
                    setCurrent_pick("update layout");
                    break;
                }
                case "update layout": {
                    setPlayerData();
                    setGameRelevantButtonsToFocusable();
                    System.out.println(java.util.Arrays.toString(current_die_chosen));
                    setCurrent_pick("roll");
                    if (!bonus_check_checked) {
                         if (bonusCheck()) {
                             jBonusTArea.setText(Integer.toString(62));
                             listPlayers[0].setScore(listPlayers[0].getScore()+62);
                         }

                    }
                    jGrandTotalTField.setText(Integer.toString(listPlayers[0].getScore()));
                }


            }
        }
        gameEnd();
    }



    private boolean bonusCheck() {
        int sum = 0;
        for (int i = 0; i < 6; i++) {
            sum += listPlayers[0].getPointsMap().get(Constants.LIST_CATEGORIES[i]);
        }
        if (sum > 62) {
            return true;
        }
        return false;
    }

    private void setPlayerData() {
        int count = 0;

        for (JTextArea t : category_text_areas) {
            int number = Integer.valueOf(listPlayers[curr_player].getPointsMap().get(LIST_CATEGORIES[count]));
            if (number == -1) number = 0;
            System.out.println(LIST_CATEGORIES[count] + " count: " + number);
            t.setText(Integer.toString(number));
            count++;
        }


    }
    private void gameEnd() {
        jGrandTotalTField.setText(Integer.toString(listPlayers[0].getScore()));
        JOptionPane.showMessageDialog(null, "Game is over! You have a total of " + listPlayers[0].getScore() + " points.");
    }

    private boolean checkGameOver() {
        return this.listPlayers[0].checkIfPlayerHasFinished();
    }
    private void playTurn(Player p) {//sets buttons to unfocusable, rolls, sets them back to focusable, updates die
        setGameRelevantButtonsToUnfocusable();
        Die[] copyList = p.getDice();
        for (int i = 0; i < copyList.length; i++) {
            if (!current_die_chosen[i]) {
                copyList[i].roll();
            } else {
                copyList[i] = new Die(p.getDice()[i]);
            }
        }
        p.setDice(copyList);
        setGameRelevantButtonsToFocusable();
        setDieValuesInTextArea(p.getDice());
        this.setCurrent_pick("empty");
    }

    private void setDieValuesInTextArea(Die[] listDice) {
        jFirstDieTButton.setText(Integer.toString(listDice[0].getValue()));
        jSecondDieTButton.setText(Integer.toString(listDice[1].getValue()));
        jThirdDieTButton.setText(Integer.toString(listDice[2].getValue()));
        jFourthDieTButton.setText(Integer.toString(listDice[3].getValue()));
        jFifthDieTButton.setText(Integer.toString(listDice[4].getValue()));
    }

    private void setDieToNotSelectedNorEnabled(){
        jFirstDieTButton.setSelected(false);
        jFirstDieTButton.setEnabled(false);
        jSecondDieTButton.setSelected(false);
        jSecondDieTButton.setEnabled(false);
        jThirdDieTButton.setSelected(false);
        jThirdDieTButton.setEnabled(false);
        jFourthDieTButton.setSelected(false);
        jFourthDieTButton.setEnabled(false);
        jFifthDieTButton.setSelected(false);
        jFifthDieTButton.setEnabled(false);
    }

    private void setDieEnabled(){
        jFirstDieTButton.setEnabled(true);
        jSecondDieTButton.setEnabled(true);
        jThirdDieTButton.setEnabled(true);
        jFourthDieTButton.setEnabled(true);
        jFifthDieTButton.setEnabled(true);
    }

    private void setGameRelevantButtonsToUnfocusable() {
        for (int i = 0; i < this.buttons.length; i++) {
            buttons[i].setEnabled(false);
        }
    }

    private void setGameRelevantButtonsToFocusable() {
        for (int i = 0; i < this.buttons.length; i++) {
            buttons[i].setEnabled(true);
        }
    }

    private void focusableButtonsExceptRoll() {
        setGameRelevantButtonsToFocusable();
        jRollButton.setEnabled(false);
    }


    public void setCurrent_pick(String current_pick) {
        this.current_pick = current_pick;
    }

    public String getCurrent_pick() {
        return this.current_pick;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jRollButton) {
            this.current_pick = "roll";
        } else if (e.getSource() == jOnesButton) {
            this.current_pick = "ones";
        } else if (e.getSource() == jTwosButton) {
            this.current_pick = "twos";
        } else if (e.getSource() == jThreesButton) {
            this.current_pick = "threes";
        } else if (e.getSource() == jFoursButton) {
            this.current_pick = "fours";
        } else if (e.getSource() == jFivesButton) {
            this.current_pick = "fives";
        } else if (e.getSource() == jSixesButton) {
            this.current_pick = "sixes";
        } else if (e.getSource() == j3OfAKindButton) {
            this.current_pick = "three of a kind";
        } else if (e.getSource() == j4OfAKindButton) {
            this.current_pick = "four of a kind";
        } else if (e.getSource() == jFullHouseButton) {
            this.current_pick = "full house";
        } else if (e.getSource() == jSmallStrButton) {
            this.current_pick = "small straight";
        } else if (e.getSource() == jLargeStrButton) {
            this.current_pick = "large straight";
        } else if (e.getSource() == jYahtzeeButton) {
            this.current_pick = "yahtzee";
        } else if (e.getSource() == jChanceButton) {
            this.current_pick = "chance";
        } else {
            this.current_pick = "null value";
        }
        System.err.println(current_pick);
    }


}

class InvalidChoiceException extends Exception {
    public InvalidChoiceException(String m) {
        super(m);
    }

    public InvalidChoiceException(InvalidPropertiesFormatException m) {
        super(m.getMessage());
    }
}
