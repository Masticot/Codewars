# Conway's Game of Life (Java Implementation)

This project provides a Java implementation of the Codewars exercise "Conway's Game of Life," which can be found at [this link](https://www.codewars.com/kata/52423db9add6f6fc39000354). The implementation simulates the evolution of a 2D grid based on Conway's rules of cellular automata.

## Exercise Description

The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970. The game is a zero-player game, meaning that its evolution is determined by its initial state, requiring no further input. You interact with the Game of Life by creating an initial configuration and observing how it evolves.

### Rules of the Game

1. **Underpopulation:** A live cell with fewer than two live neighbors dies.
2. **Overcrowding:** A live cell with more than three live neighbors dies.
3. **Survival:** A live cell with two or three live neighbors lives on to the next generation.
4. **Reproduction:** A dead cell with exactly three live neighbors becomes a live cell.

The universe of the Game of Life is infinite, but the simulation will handle a finite grid that is cropped around the living cells.

## Features

- **Simulation of Conway's Game of Life:** Computes multiple generations based on initial configuration.
- **Dynamic Grid Management:** Automatically adjusts the grid size to fit all living cells.
- **Testing Framework:** Includes unit tests that replicate some of the tests found on the Codewars kata, ensuring the parser meets the specified criteria.

### Prerequisites

- Java JDK 17
- Apache Maven (latest version recommended)
