## Dungeon and monsters game devloped using JAVA MVC

## About/Overview


This project is an implementation of a game consists of a dungeon, a network of tunnels and caves that are interconnected so that player can explore the entire world by traveling from cave to cave through the tunnels that connect them.


## Features

- A player to enter the dungeon at the start.
- The player moves from their current location.
- The player gets a chance to pick up treasure that is located in their same location.
- The player gets a chance to pick up arrow that is located in their same location.
- There are monsters present in some random caves.
- There is always a monster located at the end cave.
- The player gets a chance to shoot the monster.
- The player wins the game by killing the monster in the end cave.


## How To Run
An executable JAR file is present in the following path that can be run on Command Prompt without any need of additional user input for first round of battle.
Dungeon\res\dungeoncontroller.jar


# Output Description

- Initially, the player is asked to enter player details like name and the details of how the dungeon should look.
- The player needs to input the dungeon rows, columns, wrapping/non-wrapping dungeon, interconnectivity, percent of caves that contains treasure and arrows, number of monsters to be added in the game.
- The game then begins by asking the player for 3 options- Move, PickUp, Shoot.
- On selecting Move option, the player will be able to navigate to the specified direction. 
- PickUp option asks the player to pickUp if arrow/ treasure based on availability in the cave.
- Shoot option asks the number of caves and direction to try killing the monster.
- The player wins by successfully reaching the end cave by killing the monster present in the same cave.



## Assumptions
- The treasures are being assigned to the first 'n' random number of caves when traversed sequentially through the grid.
- A cave can hold any number of treasures.
- A player picks all the treasure in the cave.
- There is always atleast 1 monster in the dungeon.


## Limitations
- Player needs to input dungeon details before the start of the game.
- There can be only 1 Otyugh at a particular cave.


## Citations
Referred to syntax and concepts:
Java Tutorial - https://docs.oracle.com/javase/tutorial/
StackOverFLow - https://stackoverflow.com/
BaelDung - https://www.baeldung.com/
GeeksForGeeks - https://www.geeksforgeeks.org/
