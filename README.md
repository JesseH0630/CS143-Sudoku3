This repo contains solution(s) to a 3-part Sudoku Program built in CS 143: Computer Science II (Java). 

# About Sudoku
Sudoku puzzles [play online](https://sudoku.game/) begin with a 9x9 board where each "cell" is a number (1-9) or an empty space. The goal of the puzzle is to make it so that

* every row contains the values 1-9 only once per row,
* every column contains the values 1-9 only once per column, and
* each of the inside 3x3 squares only contains the values 1-9 once per small square.

The puzzle is solved once all the cells are filled with a number following the rules of the puzzle.

# Program #1: Sudoku #1 (Board Setup)
In Part 1, the code imports an incomplete sudoku puzzle from a file and prints the board to the screen in a particular way. The rules of Sudoku are not checked. The puzzle does not have the ability to self-solve. 

Expected functionality:
* a single field: a 2D array that will store the contents of the Sudoku board
* a constructor: takes one String parameter representing a file path to a .sdk board representation
* a toString(): prints the board to the screen

# Program #2: Sudoku #2 (isValid, isSolved)
In Part 2, checking for board validity and a solved state are added.

## isValid
Method added to board class that will return true if all of the following constraints are followed according to the board's current data; false otherwise:

* there is no data in the board that is not a 1-9 or a space (or your representation of a space - could be a '.' or a 0, etc)
* no row contains any duplicate values of 1-9
* no column contains any duplicate values of 1-9
* no mini-square contains any duplicate values of 1-9

For this assignment, must demonstrate use of Set.

## isSolved
Method added to board class that will return true if there are nine occurrences of every number 1-9 in the grid AND all the constraints of isValid are followed.

For this assignment, must demonstrate use of Map.

# Program #3: Sudoku #3 (solve)
In Part 3, add a new method to our board class called solve() which will allow us to use recursive backtracking to solve a Sudoku puzzle.
