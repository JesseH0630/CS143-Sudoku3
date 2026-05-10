// This is a variation of the 8 queens board class that provides an animation
// of the recursive backtracking.
// This is modified from a version created by Stuart Reges at UW-Seattle

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.*;

public class BoardGUI {
	private JLabel[][] myTextLabel;
	private int myDelay;
	private JFrame f;
	private int[][] oldBoard;

	public BoardGUI(int size) {
		// Initialize an array to store the information for the board on the last update
		// Used for the border color changes.
		// -1 is an arbitrary value used for initialization (can't use 0 because it represents empty grid).
		oldBoard = new int[size][size];
		for(int r = 0; r < oldBoard.length; r++) {
			for(int c = 0; c < oldBoard[r].length; c++) {
				oldBoard[r][c] = -1;
			}
		}

		f = new JFrame();
		f.setTitle("Fully Automatic Sudoku Solver");

		// Set the size of the window such that each individual grid is a square.
		final int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		f.setSize(screenHeight / 2 - 30, screenHeight / 2);

		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		Container contentPane = f.getContentPane();

		// Initialize delay and add slider to control it at bottom
		final int maxPause = 1000; // milliseconds
		final JSlider slider = new JSlider(0, maxPause - 20);
		myDelay = slider.getValue();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				myDelay = maxPause - slider.getValue();
			}
		});

		JPanel p = new JPanel();
		p.add(new JLabel("slow"));
		p.add(slider);
		p.add(new JLabel("fast"));
		contentPane.add(p, "South");

		// Add text boxes in the middle for the grids
		p = new JPanel(new GridLayout(size, size));
		contentPane.add(p, "Center");
		p.setBackground(Color.white);
		myTextLabel = new JLabel[size][size];
		Font f24 = new Font("Serif", Font.BOLD, 24);
		for(int i = 0; i < size; i++)
			for(int j = 0; j < size; j++) {
				JLabel t = new JLabel();
				t.setFont(f24);
				t.setFocusable(false);
				t.setBackground(Color.WHITE);
				t.setHorizontalAlignment(JLabel.CENTER);
				t.setVerticalAlignment(JLabel.CENTER);
				t.setBorder(new LineBorder(Color.black, 2));
				p.add(t);
				myTextLabel[i][j] = t;
			}

		// bring it on...
		f.setVisible(true);
		f.toFront();
	}

	public void update(int[][] board) {
		for(int r = 0; r < board.length; r++) {
			for(int c = 0; c < board[r].length; c++) {
				if(board[r][c] == 0) {
					myTextLabel[r][c].setText("");
					// Change border color
					// If a grid turns to empty, show red border to indicate backtrack.
					if(oldBoard[r][c] != 0 && oldBoard[r][c] != -1) {
						myTextLabel[r][c].setBorder(new LineBorder(Color.red, 2));
					}
					else {
						myTextLabel[r][c].setBorder(new LineBorder(Color.black, 2));
					}
				}
				else {
					myTextLabel[r][c].setText("" + board[r][c]);
					// Change border color
					// If a grid gets filled, show green border to indicate progress.
					if(board[r][c] != oldBoard[r][c] && oldBoard[r][c] != -1) {
						myTextLabel[r][c].setBorder(new LineBorder(Color.green, 2));
					}
					else {
						myTextLabel[r][c].setBorder(new LineBorder(Color.black, 2));
					}
				}
				// Update old board
				oldBoard[r][c] = board[r][c];
			}
		}
		pause();
	}

	// pause using slider setting
	private void pause() {
		pause(myDelay);
	}

	private void pause(int delay) {
		try {
			Thread.sleep(delay);
		}
		catch(Exception e) {
			throw new InternalError();
		}
	}
}
