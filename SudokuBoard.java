import java.util.*;
import java.io.*;

public class SudokuBoard {
	private int[][] board;
	private BoardGUI boardGUI;

	// Constructs a new board.
	public SudokuBoard() {
		board = new int[9][9];
		boardGUI = new BoardGUI(9);
	}

	// Construct the board with imported data from file.
	public SudokuBoard(String fileName) {
		this();
		try {
			Scanner console = new Scanner(new File(fileName));
			for(int r = 0; r < board.length; r++) {
				if(console.hasNext()) {
					String line = console.next();
					for(int c = 0; c < board[0].length; c++) {
						char value = line.charAt(c);
						if(value == '.') {
							board[r][c] = 0;
						}
						else {
							board[r][c] = value - '0';
						}
					}
				}
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("Error: File not found.");
		}
	}

	// Check if the board is valid.
	public boolean isValid() {
		if(!checkNumbers())
			return false;
		if(!checkRow())
			return false;
		if(!checkCol())
			return false;
		if(!checkBox())
			return false;

		return true;
	}

	// Checks to ensure that everything on the board is a number between 1 and 9.
	private boolean checkNumbers() {
		for(int r = 0; r < board.length; r++) {
			for(int c = 0; c < board[0].length; c++) {
				if(board[r][c] < 0 || board[r][c] > 9) {
					return false;
				}
			}
		}

		return true;
	}

	// Checks that each row is valid.
	private boolean checkRow() {
		for(int r = 0; r < board.length; r++) {
			Set<Integer> set = new HashSet<>();
			for(int c = 0; c < board[0].length; c++) {
				int val = board[r][c];
				if(val != 0) {
					if(set.contains(val))
						return false;
					set.add(val);
				}
			}
		}


		return true;
	}

	// Checks that each column is valid.
	private boolean checkCol() {
		for(int r = 0; r < board.length; r++) {
			Set<Integer> set = new HashSet<>();
			for(int c = 0; c < board[0].length; c++) {
				int val = board[c][r];
				if(val != 0) {
					if(set.contains(val))
						return false;
					set.add(val);
				}
			}
		}

		return true;
	}

	// Checks that each small box is valid.
	private boolean checkBox() {
		for(int boxRow = 0; boxRow < 3; boxRow++) {
			for(int boxCol = 0; boxCol <
					3; boxCol++) { // checks the first, second, and third box
				Set<Integer> set = new HashSet<>();
				for(int r = 0; r < 3; r++) {
					for(int c = 0; c <
							3; c++) { // checks the first row, then first column of the box
						int val = board[boxRow * 3 + r][boxCol * 3 +
								c]; // adds the value of the board at the location of [(if in the case box row 0 and row 1 of the box then (0*3+1))]
						if(val != 0) {
							if(set.contains(val))
								return false;
							set.add(val);
						}
					}
				}
			}
		}

		return true;
	}

	// Check to see if the board is solved.
	public boolean isSolved() {
		if(!isValid())
			return false;

		Map<Integer,Integer> map = new HashMap<>();

		for(int r = 0; r < board.length; r++) {
			for(int c = 0; c < board[0].length; c++) {
				int val = board[r][c];

				if(val != 0) {
					if(map.containsKey(val)) {
						map.put(val, map.get(val) + 1);
					}
					else {
						map.put(val, 1);
					}
				}
			}
		}

		for(int num = 1; num <= 9; num++) {
			if(!map.containsKey(num) || map.get(num) !=
					9) { // this loop goes from 1-9 and checks if the map contains the number and the occurrences the number came up
				return false;
			}
		}

		return true;
	}

	// Solve the board using recursive backtracking.
	public boolean solve() {
		// Update the GUI (this line may cause the board to take a long time to solve).
		boardGUI.update(board);

		if(isSolved()) {
			return true;
		}
		for(int r = 0; r < board.length; r++) {
			for(int c = 0; c < board[r].length; c++) {
				if(board[r][c] == 0) {
					for(int i = 1; i <= 9; i++) {
						board[r][c] = i;
						if(isValid() && solve()) {
							return true;
						}
					}
					if(!isValid()) {
						board[r][c] = 0;
					}
				}
			}
		}
		return false;
	}

	// Print the board in a nice way.
	public String toString() {
		String result = "";
		String line = "+-------+-------+-------+\n";

		for(int r = 0; r < board.length; r++) {
			if(r % 3 == 0) {
				result = result + line;
			}
			for(int c = 0; c < board[0].length; c++) {
				if(c % 3 == 0) {
					result = result + "| ";
				}
				if(board[r][c] == 0) {
					result = result + "- ";
				}
				else {
					result = result + board[r][c] + " ";
				}
			}
			result = result + "|\n";
		}
		result = result + line;
		return result;
	}
}