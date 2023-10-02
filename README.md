# Singleplayer Yahtzee Simulation


## **Why this was done**
This project was done as a final project for my Programming II course. I did this with two other classmates.

## **What we did for this project**
This project uses object oriented programming (the focus of our Programming II course) as well as the Java Swing/AWT libraries to pop out a menu that allows the user to generate 5 pseudorandom numbers (representative of 5 dice) and roll, keep, or discard them as needed to try and get a high score.

In an attempt to simulate the "model view controller" coding pattern (MVC) we split up our classes to have algorithms that calculate scores behind the scenes, a sort of frontend that the player can interact with, and links between them that the player cannot see. The code was based on a state machine which we 

## **What we could have done better/included**
### *Features*
Due to emergency situations a groupmate had to deal with in his home country, we had to postpone our project and were restricted to doing it in 24 hours. We had to scrap a lot of ideas like same-system multiplayer and using the File class to upload images of dice that we could use instead of buttons showing the numbers. However, in the span of such a short time constraint, getting this program working was a highlight of that semester, especially because of how large the scale of the project originally was and how much of that scale we were able to retain.

### *Information*
We originally intended to write documentation for our program as well. However, I was the only one who was able to document what one of those "model"/backend classes did because of those aforementioned time constraints. Here is a copy-paste of my documentation for the YahtzeeScoring class:

"
Class YahtzeeScoring:

Public methods:

calculateHand(Die[]) returns String combines the following methods in order:
Private method forceFunctionExitIfError which checks if the list size is somehow invalid (bugged out) and forcefully ends the program.
Public method getDieInHand.
Returns String of return value of calculateHandType.


calculateHandType(int[], HashMap<Integer,Integer>)
returns String output that checks what hand type is. Also includes some extra hand types not in the game score rules just in case we need to be specific about allowing or disallowing certain things for points (e.g.: checking if its a “one pair” and not a “full house”).
Can return:
 “yahtzee”, “full house”, “large straight”, “small straight”, “four of a kind”, “three of a kind”, “no special type” (only returns “no special type” if the type in the hand is one or two pair), and “-1”. “-1” is returned if there is some sort of error.

getDieInHand(HashMap<Integer, Integer>) returns int[] of all unique values of each die in a list (e.g: if you have 1, 2, 3, 4, 4 value dice in your hand, function returns [1, 2, 3, 4]. If you have yahtzee with die value X, list returns [X].)

getDieValuesAndAmount(Die[]) returns hashmap containing all values of die in the hand and how many there are. E.g., die list [1,1,1,1,1] returns hashmap with one key, 1, and one value matching it, 5.  Die list [1,2,3,4,5] returns hashmap {1:1, 2:2, 3:3, 4:4, 5:5}.

Private methods:

forceFunctionExitIfError checks for list inaccuracy. That function uses another functioncalled checkListError to check if the list is the right amount of units long. Of course, we shouldn’t be listing out what private methods usually do, but this is more so because groupmates need to understand the logic.
"

## **Will we continue to finish this project?**
If I were to finish this project, it would most likely be in making it a full game fleshed out with actual art and UI/UX development instead of being limited to Java Swing/AWT and our previously restrictive knowledge on programming. So while I may not finish this exact project right now, I might complete an actual Yahtzee project in the future based off of this (possibly in another coding language as well, such as JS).
