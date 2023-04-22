There are 5 different .txt files that will need to be read in for this game.
All files except for the Player file will be read in and distributed in the Map class.  Player will be read in inside of the Player class.
1. A .txt file with the Monster Values
2. A .txt file with the Room Values
3. A .txt file with the Item Values
4. A .txt file with the Puzzle Values
5. A .txt file with the Player Values

The Values on each line are delimited by a "~"

MonsterFile.txt
.txt file that contains all the monster values.
In total there are six variables that the monster class will contain, these are name, monsterDescription, hitPoints, strength, attackChance, and weakness.
The format is <name>~<description>~<hitPoints>~<strength>~<attackChance>~<weakness>~<roomNumber>

<name>
A string which will provide the name of a monster.

<monsterDescription>
A string that contains a description of the monster.

<hitPoints>
An int that represents the max health points of the monster.

<strength>
An int that represents the amount that the monster hits for when attacking.

<attackChance>
An int that represents the chance that a monster has to land a hit when attacking.

<weakness>
A String value that represents the name of a combat Item that the monster is weak to.  If the player uses the corresponding item, the monster will be defeated immediately.

<roomNumber>
An int that represents which room the monster should be placed in.

------------------------------------------------------------------------------------------------------------------------------------------------------------------

RoomFile.txt

The Room text file holds all the values needed to build the room objects.

The format is <roomNumber>~<name>~<description>~<northExit>~<eastExit>~<southExit>~<westExit>

<roomNumber>
An int used as an ID for the room.

<name>
A String that represents the name of the room.

<description>
A String that describes the room.

<northExit>
An int that represents the room ID of the room that is connected to the North Exit of the current room.  If there is no room
in that direction, it will be represented with the number "99";

<eastExit>
An int that represents the room ID of the room that is connected to the East Exit of the current room.  If there is no room
in that direction, it will be represented with the number "99";

<southExit>
An int that represents the room ID of the room that is connected to the South Exit of the current room.  If there is no room
in that direction, it will be represented with the number "99";

<westExit>
An int that represents the room ID of the room that is connected to the West Exit of the current room.  If there is no room
in that direction, it will be represented with the number "99";

----------------------------------------------------------------------------------------------------------------------------------------------------------------------

ItemFile.txt

The Room text file holds all the values needed to build the room objects. There are four different types of items which are interactable, consumable, combatItem, and collectible.
Interactable, combatItem, and collectible have 4 variables to be parsed, while consumable has 5.

Interactable: An item that presents a usable menu when the player uses it. This menu will take inputs from the player and is used as the final task to beat the game.
Interactable Format: <type>~<name>~<description>~<roomNumber>

CombatItem: These items are specifically used when the player is in a battle with a monster. If the monster currently in combat is weak to this item, it will be defeated immediately upon use. Combat
            Items can be used infinite times and will not be deleted upon use.
CombatItem Format: <type>~<name>~<description>~<roomNumber>

Collectible: There will be multiple instances of this item inside the game.  They will each contain one number which represent one value of a 4 digit pin number.  This pin number will need to be used
             in the interactable to beat the game.
Collectible Format: <type>~<name>~<description>~<roomNumber>

Consumable: Consumable items can be used to add health points to the player's current health.  Will be destroyed after a single use.
<type>~<name>~<description>~<roomNumber>~<healthPoints>

<type>
A string that is used to let the filereader know which Item type to create.  The data will be passed to the appropriate constructor based on the type.

<name>
A string variable of the name of the item.

<description>
A string variable that contains the description of item.

<roomNumber>
A int that represents which room the current item should be placed in.

<healthPoints>
An int that represents how many health points it will restore to the player. Exclusive to consumable.

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

PuzzleFile.txt

Puzzles are read in from a .txt file. The puzzles are set in their place at the beginning of the game and cannot be moved
after initialization. Each puzzle has a certain number of attempts. If the user answers incorrectly for each number of
attempts, they will be returned to the room that the puzzle is in. The puzzle tries will be reset immediately and the player
can access the puzzle again immediately. If the user gets the correct answer, the puzzle will be solved and become
inaccessible for the rest of the current game. The values will all be on one line and delimited by "~".

There are two types of puzzles in the game, Switches and Keypad.

Switches: You will be presented with five switches that may be in either an up or down state. All switches will start in the down state.
You will be given a number to input using the switches. To do so, convert the number from decimal into a five-bit binary number.
Flip the five switches in such a way that the switch in the same position as each digit in the binary number is up if the digit is a 1 or down if the digit is a 0.
Submitting the correct configuration of switches will solve the puzzle. If an incorrect configuration of switches is submitted you will fail the puzzle.

The file format is: <puzzleID>~<Problem>~<Answer>~<maxAttempts>~<roomID>.

Commands:
•	(F)lip [#]: Changes the up or down state of the switch in the position #. The command is registered as invalid if # is not a digit from 1-5.
              The user will need to enter the command flip with a space and the number of the switch they would like to flip. "flip <switch number>".
•	(S)ubmit: The program will check the current configuration of switches to see if it is correct.  If the switch configuration is correct, the
              puzzle will be set as completed and if it contains a collectible that collectible will be put in the player inventory.

<puzzleID>
An int that represents which puzzle type it is. Switches will be represented with a 0.

<Problem>
An int variable that will represent the problem that the player will have to solve.  It will be 1 integer that can have multiple numbers that the user will have
to translate into switch flips.

<Answer>
An int that represents the switch flips through 1s and 0s.  1 will mean up, and 0 will mean down.

<maxAttempts>
An int that represents the number of attempts the player has per puzzle.

<roomID>
An int that represents the i.d. of the room that the puzzle should be placed in.

Keypad: You will be presented with a string of digits. When these digits are entered into a phone keypad, a word will be formed.
Enter this word into the panel to complete the puzzle. If an incorrect word is entered you will fail the puzzle.

The file is format is <puzzleID>~<Problem>~<Answer>~<maxAttempts>~<roomID>.

Commands:
Attempt the puzzle by entering a word.

Reference: To input a particular letter into a phone keypad, you must press the number key with that letter on its label a number of times equal to the position of that letter on the label.
For example, entering 999 will enter a Y. The 1 key, however, will not enter a digit.
Instead, entering a 1 will separate two instances of letters that must be entered with the same key.
 For example, entering 66166 will enter NN. The labels of the buttons are listed below.
           2: ABC    3: DEF    4: GHI
           5: JKL    6: MNO    7: PQRS
           8: TUV    9: WXYZ

 <puzzleID>
 An int that represents which puzzle type it is. Keypad will be represented with a 1.

 <Problem>
 An int that is made up of multiple numbers.  Will represent number keys on a keypad.  The numbers will correlate with
 button presses on a keypad that represent the answer.

 <Answer>
 A String that represents the answer to the puzzle.

 <maxAttempt>
 An int that represents the number of attempts the player has per puzzle.

 <roomID>
 An int that represents the i.d. of the room that the puzzle should be placed in.

 --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

PlayerFile.txt

The Player text file holds the basic player stats.  These are Max Health, Attack Power, and Hit Chance.


The file format is: <MaxHP>~<Attack Power>~<Hit Chance>

<maxHP>
An int that contain the maximum amount of health the player will have in the game. The player starts with the max HP.

<attackPower>
A int that provides the amount of damage the player deals in combat.

<attackChance>
An int that represents the chance that the player has to hit an enemy in combat.


---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Map layout                                                             ______________________
                                                                       |   8     |   9      |
                                                                       |_________|__________|
                         ____________          _____________     ______|         |
                         |          |          |           |    |  7   |         |
                         |     18   |          |    1      |    |______|  6      |
                 ________|__________|__________|___________|___________|_________|________________________________________________________
                |        |    16    |          |           |                                |               |             |              |
                |    19  |          |      15  |     0     |      2                         |       3       |    4        |     5        |
                |________|__________|__________|___________|________________________________|_______________|_____________|______________|
                         |          |          |           |           ________________
                         |    17    |          |     10    |           |     14       |
                         |__________|          |___________|___________|______________|
                                               |           |           |             |
                                               |     11    |     12    |    13       |
                                               |___________|___________|_____________|





0 - Beach- Giant Fish Net - No Monster- No Puzzle
1 - Abandoned Shelter- Coconut - Roger the Ravenous Sea Lion - No Puzzle
2 - Deep Jungle - No Item - Sly the Anaconda - No Puzzle
3 - Waterfall - Potential phone number - No Monster - Keypad
4 - Cave - Flame Thrower - No Monster - No Puzzle
5 - Underground Chamber - Potential Phone number - Spiders - Switches
6 - Raging River - Machete and Potential phone number - no Monster - Keypad
7 - Mangrove Swamp - Alligator Meat - Angry Alligator - No Puzzle
8 - Old Temple Ruins - no item - Zamrah the Minotaur - No Puzzle
9 - Crystal Cave - First Aid Kit and Potential Phone Number - No Monster - Switches
10 - Sand Dunes - Beach Ball - Shifty the Coconut Crab - No Puzzle
11 - Lighthouse - Maracas - No Monster - Switches
12 - Volcano - No Items - Dasami the Komodo Dragon - No Puzzle
13 - Mountain Lookout - Potential phone number - No Monster - Keypad
14 - Sky Garden - No Items - No Monster - No Puzzle - No Puzzle
15 - Shipwreck - Beach Ball - No Monster - No Puzzle
16 - Ship's Main Foyer - Potential Phone Number - No Monster - Keypad
17 - Sickbay - Phone - No Monster - No Puzzle
18 - Cargo Room - Potential Phone Number - No Monster - Switches
19 - Captain's Quarters = No Item - No Monster - No Puzzle

_____________________________________________________________________________________________________________________________________________________________________________-

User Manual:
Commands maybe entered with the full command or the shortcut shown in ().

Upon Starting the game you will be greeted by the main menu which has 3 options:

(N)ew Game - This starts a new game which uses the default map and all visited flags are set to false.

(L)oad Game- This will allow you to load a save game file and start from where you last saved in the game.

(Q)uit Game- This will exit out of the program completely.


During the Game you have 10 options, all of which are case-insensitive:

(H)elp - prints a menu of all the possible commands.

(N) - will move you to the room North of your current position if there is one available.

(S) - will move you to the room South of your current position if there is one available.

(E) - will move you to the room East of your current position if there is one available.

(W) - will move you to the room West of your current position if there is one available.

(Exp)lore - prints a list of all the Items contained in the current room.

(P)ickup <item-name> - removes the item from the current room's inventory and adds it to the player's inventory.

(D)rop <item-name> - removes the item from the player's inventory and adds it to the current room's inventory.

(I)nspect <item-name> - if the specific item is inside the player's inventory, that item's description will be printed.

(Inv)entory - prints the names of all the items in the player's inventory.

(A)ttack - Only available during combat.  Attempts a chance at the player hitting the monster they are in combat with.

(U)se <item-name> - If using a consumable inside or outside of combat the players health will be increased by the amount
                    that the consumable contains.  If that amount is more than max health, the player's current health
                    is set to max health.

                    If using an interactible outside of combat, the interactable menu will print and allow the user to
                    enter commands.

                    If using a CombatItem during combat, a check is initiated to see if the current monster that the
                    player is in combat with is weak to it. If the monster is weak to it, the monster dies immediately.
                    If the monster is not weak, nothing happens and combat resumes.

                    If using a collectible outside of combat, the number contained in the collectible is printed.

(Ex)amine - If there is a puzzle in the room that the player is currently in, the puzzle will be started. If there is no
            puzzle in the room a message will be printed and nothing will happen.

(He)alth - Prints the player's current health.

(Sa)ve - Will allow the player to create a save file of the current playthrough.  Only one save file can exist at a time.
         The player will then be asked if the want to quit or not?  If they answer yes, the game will end.  If the answer
         no, the game will continue.


