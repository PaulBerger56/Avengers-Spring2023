The entire map and descriptions are in a single line in the txt file.
The Values are delimited by a "~", and there can be several descriptions

MonsterFile.txt
This text file is the basis of important variables that is parse into the map for the monster class.
In total there are six variables that the monster class will contain, these are name, monsterDescription, hitPoints, strength, attackChance, and weakness.
The format is <name>~<description>~<hitPoints>~<strength>~<attackChance>~<weakness>~<roomNumber>

<name>
A string variable which will provide the name of a monster.

<monsterDescription>
A string that contains a description of the monster.

<hitPoints>
This int variable in the amount of health the monster.

<strength>
It's an int variable about the amount of damage the monster can put to the player when in combat.

<attackChance>
This is an int variable where the monsters have a percentage of success in hitting the player. When it's the monsters turn.

<weakness>
String value that represents the name of a combat Item that the monster is weak to.  If the player uses the corresponding item, the monster will be defeated immediately

<roomNumber>
It's an int variable to help place which room the monster will be in the map. It's being read from the MonsterFile then parsed to the room class.
------------------------------------------------------------------------------------------------------------------------------------------------------------------
RoomFile.txt

The room text file helps to detect the navigation of the game, and it's variables are a representation of that.
In total there are 7 variables that the room class had when being parsed for the game.
The format is <roomNumber>~<name>~<description>~<northExit>~<eastExit>~<southExit>~<westExit>

<roomNumber>
An int variable to represent what number it's placed from the map based on the client's request.

<name>
A String variable which gives the name of the room.

<description>
This variable is a string which contains the information of the room in its illustration.

<northExit>
A int variable where it detects in which ever room where the next room will be when the player moves north.
If the value of the exit is 99 it means that exit is a dead end and will keep the player in the same room.

<eastExit>
A int variable where it detects in which ever room where the next room will be when the player moves east.
If the value of the exit is 99 it means that exit is a dead end and will keep the player in the same room.

<southExit>
A int variable where it detects in which ever room where the next room will be when the player moves south.
If the value of the exit is 99 it means that exit is a dead end and will keep the player in the same room.

<westExit>
A int variable where it detects in which ever room where the next room will be when the player moves south.
If the value of the exit is 99 it means that exit is a dead end and will keep the player in the same room.

----------------------------------------------------------------------------------------------------------------------------------------------------------------------
ItemFile.txt
This text file keeps all information about the different items that are present in the game. There's four different types of items which are interactable, consumable, combatItem, and collectible.
Interactable, combatItem, and collectible have 4 variables to be parsed, while consumable has 5.

Interactable: An interactable item is one where the player can put input to have a specific command. There's only one item like this it's phone, and it is used to beat the game.
Interactable Format: <type>~<name>~<description>~<roomNumber>

CombatItem: These items are specifically used when the player is in a battle with a monster. If the monster currently in combat is weak to this item, it will be defeated immediately upon use. Combat
            Items can be used infinite times and will not be deleted upon use.
CombatItem Format: <type>~<name>~<description>~<roomNumber>

Collectible: There will be multiple instances of this item inside the game.  They will each contain one number which when combined create the pin that will need to be used on the phone to beat the game.
Collectible Format: <type>~<name>~<description>~<roomNumber>

Consumable: Consumable items can be used to add health points to the player's current health.  Will be destroyed after a single use.
<type>~<name>~<description>~<roomNumber>~<healthPoints>

<type>
A string that is used to let the filereader know which Item type to create.  The data will be passed to the appropriate constructor based on the type.

<name>
A string variable of the name of the item.

<description>
A string variable that contains the description of item only if in inventory and player hits "inspect" said item.

<roomNumber>
A int variable to help place which items will be placed in the room best suited for the enjoyment of the game.

<healthPoints>
A variable exclusive to only consumable as there are items that increase the player's health when used.

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
PuzzleFile.txt
Puzzles are read in from a .txt file. The puzzles are set in their place at the beginning of the game and cannot be moved
after initialization. Each puzzle has a certain number of attempts. If the user answers incorrectly for each number of
attempts, they will be returned to the room that the puzzle is in. If they leave the room and return, the puzzle tries
will be reset, and they can try again.  If the user gets the correct answer, the puzzle will be solved and become
inaccessible for the rest of the current game. The values will all be on one line and delimited by "~".

There's two types of puzzle in the game they're Switches and Keypad.
Switches: You will be presented with five switches that may be in either an up or down state. All switches will start in the down state.
You will be given a number to input using the switches. To do so, convert the number from decimal into a five-bit binary number.
Flip the five switches in such a way that the switch in the same position as each digit in the binary number is up if the digit is a 1 or down if the digit is a 0.
Submitting the correct configuration of switches will solve the puzzle. If an incorrect configuration of switches is submitted you will fail the puzzle.
The file format structure is <puzzleID>~<Problem>~<Answer>~<maxAttempts>~<roomID>.
Command:
•	“flip #”: Changes the up or down state of the switch in the position #. The command is registered as invalid if # is not a digit from 1-5.
•	“submit”: The program will check the current configuration of switches to see if it is correct.

<puzzleID>
An int variable to check which puzzle type it is.

<Problem>
This is an int variable which is the problem presented when the player is solving the puzzle.

<Answer>
The string variable which the answer it will be in binary notation.

<maxAttempts>
An int variable which will give the player the attempts needed to complete the puzzle. If they fail the player will be sent to the previous room that came from.

<roomID>
A int variable where the puzzle will be placed on the map in the room it's in.

Keypad: You will be presented with a string of digits. When these digits are entered into a phone keypad, a word will be formed.
Enter this word into the panel to complete the puzzle. If an incorrect word is entered you will fail the puzzle. The file is formatted from <puzzleID>~<Numerical Answer>~<Alphabetical Answer>~<maxAttempts>~<roomID>.

Commands:
•	“enter word”: Submits the word “word” to the panel.
Reference: To input a particular letter into a phone keypad, you must press the number key with that letter on its label a number of times equal to the position of that letter on the label.
For example, entering 999 will enter a Y. The 1 key, however, will not enter a digit.
Instead, entering a 1 will separate two instances of letters that must be entered with the same key.
 For example, entering 66166 will enter NN. The labels of the buttons are listed below.
           2: ABC    3: DEF    4: GHI
           5: JKL    6: MNO    7: PQRS
           8: TUV    9: WXYZ

 <puzzleID>
 A int variable to detect if the puzzle is either switch or keypad.

 <Numerical Answer>
 This string variable is the answer of the word in it will be typed in a real keypad.

 <Alphabetical answer>
 The answer of the problem as received in text.

 <maxAttempt>
 A int variable about the amount of attempts the player has when solving the puzzle.

 <roomID>
 The int variable that places the puzzle in the specific room it will be.
 --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
PlayerFile.txt
There standard player stats once the user starts the game is we parsed that data into the project.
Which will make for a more concise playing experience. There's 8 variables that are important to the function of the player.
The file format structure is <maxHP>~<currentHP>~<attackPower>~<currentRoom>~<previousRoom>~<attackChance>~<defeated>~<inventory>.

<maxHP>
This int variable contain the maximum amount of health the player will have in the game. The player starts with the max HP to help the user getting use to the game.

<currentHP>
This int variable is to update the player's health, in the beginning to it will the same of the maxHP. Once the user starts battling monsters and gets hit then the player's health will change.

<attackPower>
A int variable where it provides the attack damage to the player hit towards monster's.

<currentRoom>
An int variable to help keep the player in track on what room there in from the map.

<previousRoom>
This int variable is to help keep track in where was the last room the player will in. This is for the user when they want to escape from escape or failed to solve a puzzle.

<attackChance>
Attack chance is the int variable of the probability of the player's attack hitting the monster.

<defeated>
A boolean variable used if the player is defeated which will transfer the user to the main menu. In its default state the defeated statement will be false.

<inventory>
An ArrayList from the item class that keep store of any items that player picks up throughout the game.
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





0 - Beach- Giant Fish Net - Roger the Ravenous Sea Lion- No Puzzle
1 - Abandoned Shelter- Knife and Coconut - No Monster - No Puzzle
2 - Deep Jungle - No Item - Sly the Anaconda - No Puzzle
3 - Waterfall - Potential phone number - No Monster - Keypad
4 - Cave - No item - No Monster - No Puzzle
5 - Underground Chamber - Flame Thrower and Potential Phone number-Spiders-Switches
6 - Raging River - Potential phone number - no Monster - Keypad
7 - Mangrove Swamp - Alligator Meat - Angry Alligator - No Puzzle
8 - Old Temple Ruins - Machete - Zamrah the Minotaur - No Puzzle
9 - Crystal Cave - First Aid Kit and Potential Phone Number - No Monster - Switches
10 - Sand Dunes - Beach Ball - Shifty the Coconut Crab - No Puzzle
11 - Lighthouse - Maracas - No Monster - Switches
12 - Volcano - No Items - Dasami the Komodo Dragon - No Puzzle
13 - Mountain Lookout - Potential phone number - No Monster - Keypad
14 - Sky Garden - No Items - No Monster - No Puzzle - No Puzzle
15 - Shipwreck - No Items - No Monster - No Puzzle
16 - Ship's Main Foyer - Potential Phone Number - No Monster - Keypad
17 - Sickbay - Phone - No Monster - No Puzzle
18 - Cargo Room - Potential Phone Number - No Monster - Switches
19 - Captain's Quarters = No Item - No Monster - No Puzzle
_____________________________________________________________________________________________________________________________________________________________________________-
User Manual:
Upon Starting the game you will be greeted by the main menu which has 4 options:

"n" New Game - This starts a new game which uses the default map and all visited flags are set to false.

"l" Load Game - This will allow you to load a save game file and start from where you last save in the game.

"q" Quit the Game - This will exit out of the program completely.


During the Game you have 10 options, all of which are case-insensitive:

"n" - will move you to the room North of your current position if there is one available.

"s" - will move you to the room South of your current position if there is one available.

"e" - will move you to the room East of your current position if there is one available.

"w" - will move you to the room West of your current position if there is one available.

"explore" - prints a list of all the Items contained in the current room.

"pickup <item-name>" - removes the item from the current room's inventory and adds it to the player's inventory.

"drop <item-name>" - removes the item from the player's inventory and adds it to the current room's inventory.

"inspect <item-name>" - if the specific item is inside the player's inventory, that item's description will be printed.

"inventory" - prints the names of all the items in the player's inventory.

"Attack" - Will start combat with the monster in the room.
"use <item-name>" - During combat the player can use an item to impact the monster's health or the combat itself.

"examine" - It will trigger the puzzle feature and will start the puzzle for the player to solve.

"help" - Prints out instruction to playing the game.

"health" - Prints the player's current health.

"s" Continue a Saved Game (in progress) - This will allow you to load a save file which has visited room
            flags set to true.



If the player enters a room with a puzzle, that puzzle will be started immediately.  A question will be asked, and the
user needs to type in their answer and press enter.  The answers are case-insensitive, so capitalization is not needed.
