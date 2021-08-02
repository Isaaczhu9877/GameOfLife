# Conway's Game of Life

## What Does My Application Do?
This application is based on the well known game of life created by mathematician John Conway. 
It is a cellular automaton that depicts the evolution of cells. The game is quite simple and only
requires input from the player before the simulation begins. The player can set the initial state
of the program by creating different patterns of cells before running the simulation to watch the
behaviour of the cells as the program progresses.

The Rules:
- A cell with less than two neighbours dies due to underpopulation
- A cell with two of three neighbours survives
- A cell with more than three neighbours dies due to overpopulation
- A dead cell with exactly three neighbours becomes alive due to reproduction

## Who Will Use My Program

This program can be used by anyone who is interested 
learning about the game of life, such as a high school student
who was introduced to the game in biology class. The game is easy to grasp 
to play so anyone can begin to test different initial states and create interesting 
patterns.
## Why This Interests Me
I was first introduced to this game by my high school
biology teacher and I found myself really attracted to the
game and spent the whole class trying to create cool shapes and 
moving groups of cells with friends. Later I found out that the 
game, as simple as it may seem, has a very complicated past with mathematics and even today there are still
new discoveries about the behaviour of the game. I thought it would be a cool project 
to recreate the game and include a few extra pieces of 
functionality that I think will improve the experience of
the game.

##User Stories

- As a user, I want to be able to add cells to the grid
- As a user, I want to be able to control how long the simulation runs
- As a user, I want to be able to reset the game
- As a user, I want to be able to load an initial game board from the file
- As a user, I want to be able to save a game inital board to file
    - As a user, I want to be able to save an in progress board to file
- As a user, I want to be reminded of the option to save my game when I exit the application
- As a user, I want to be given the option to load my game from file when I start the application