import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class GridGame extends JPanel{
	
	public static int currentSize = 4;
	private int maxSize = 15; 
	private int currentSpeed = 1000;
	private int maxSpeed = 500;
	
	public static GameSquareInfo squares;
	private Pattern pattern;

	int counter = 0;						// counter is for pattern
	int index = 0;							// index is for color of the game squares
	int numClicks = 0;						// numClicks is for the number of user clicks
	
	private Timer t;
	
	private int scoreAdder = 100;
	private int score; 
		
	private boolean wonGame = false;		// Keeps the state of the game
	private boolean patternDone = false;	// Keeps the user from clicking until pattern is done displaying
	
	private Color[] listColors1 = {new Color(18,69,81), new Color(11,57,88), new Color(30,84,92), new Color(112,128,144), new Color(51,61,105)};
	private Color[] listColors2 = {new Color(61,126,142), new Color(50,117,162), new Color(66,128,138), new Color(176,196,222), new Color(93,104,164)};
	
	private HighScores highScores = new HighScores();
	
	/**
	   * This method is used to initialize the game. 
	   */
	
	public GridGame(){
		super();
		mainScreen();

	}
	
	/**
	   * This method is used to start the game. It
	   * creates a new pattern and displays it using a timer
	   */
	
	public void startGame(){
		patternDone = false;
		pattern = new Pattern(currentSize);
		pattern.createPattern();

		t = new Timer(currentSpeed, new ActionListener(){
			public void actionPerformed(ActionEvent event){
				if (counter < currentSize){
					pattern.displayPattern(counter);
					repaint();
					revalidate();
					counter++;
				}
				else if (counter == currentSize){
					counter = 0;
					pattern.endPattern();
					repaint(); 
					revalidate();
					t.stop();
					patternDone = true;
				}
			}
			
		});
		t.start();
		repaint(); 
		revalidate();
	}
	
	/**
	   * This method is used to reset the game board to the
	   * desired size and speed. It will create the game board and
	   * add mouse listeners.
	   * @param size This is the first parameter to resetGame method
	   * @param speed  This is the second parameter to resetGame method
	   */
	
	public void resetGame(int size, int speed){
		removeAll();
		currentSize = size;
		currentSpeed = speed;
		wonGame = false;
		counter = 0;
		setLayout(new GridLayout (currentSize, currentSize, 0, 0));
		if (index == 4){
			index = 0;
		}
		else{
			index++;
		}
		squares = new GameSquareInfo(); // Instantiates a new GameSquares object
		for (int r = 0; r < currentSize; r++) // For each row
			for (int c = 0; c < currentSize;  c++) // For each column
			{
				squares.setSquare (new GameSquare (r, c, listColors1[index], listColors2[index]));
				final GameSquare sq = squares.getSquare(r, c);
				sq.addMouseListener (new GameSquareMouseListener (sq));
				add (squares.getSquare(r, c)); // Adds square to frame
			}
		repaint();
		revalidate();
		startGame();
	}
	
	/**
	   * This method is used to check if the user's click is
	   * correct.
	   * @param sq This is the first parameter to checkClick method
	   * @param numClicks  This is the second parameter to checkClick method
	   * @return boolean This returns whether the user's click is in the pattern or not.
	   */
	
	public boolean checkClick(GameSquare sq, int numClicks){
		Position pos = sq.getPosition();
		for (int i = 0; i < currentSize; i++){
			if (numClicks == i){
				if(pos.r == Pattern.patternArray.get(i).r && pos.c == Pattern.patternArray.get(i).c){
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	   * This class is used to listen to the user's mouse click.
	   */
	
	public class GameSquareMouseListener extends MouseAdapter{
		GameSquare sq;
		public GameSquareMouseListener (GameSquare sq) {
			this.sq = sq;
		}
		
		/**
		   * This method is used to implement the logic of the game
		   * once the user clicks. It keeps track of the score and the status of the game.
		   * @param event This is the first parameter to mousePressed method
		   */
		
		public void mousePressed (MouseEvent event) // When the mouse is pressed
		{
				Point mousePoint = event.getPoint();
			
				if(patternDone && sq.contains(mousePoint) && currentSize != maxSize){						//CHANGE CURRENT SIZE
					sq.setSquareSelected();
					repaint();
					revalidate();
					if (checkClick(sq, numClicks)){
						score = score + 100;
						sq.setSquareSelected();
						numClicks++;
					}
					else{
						wonGame = false;
						scoreScreen(wonGame);
						numClicks = 0;
					}
				}

				if(numClicks == currentSize && currentSize != maxSize){										//CHANGE CURRENT SIZE
					numClicks = 0;
					if (currentSize <= maxSize){
						currentSize++;
						scoreAdder = (int) (scoreAdder * 0.25);
					}
					if (currentSpeed >= maxSpeed){
						currentSpeed = currentSpeed - 100;
						
					}
					if (currentSize == maxSize)																//CHANGE CURRENT SIZE
					{
						wonGame = true;
						scoreScreen(wonGame);
					}
					else{
						resetGame(currentSize, currentSpeed);
					}
				}
			}
		}
	
	/**
	   * This method is used to display the main screen. It
	   * will have buttons to start the game, see the high score board,
	   * and to see the directions to the game.
	   */
	
	public void mainScreen(){
		removeAll();
		score = 0;
		setLayout(new BorderLayout());
		setBackground(new Color(25,33,65));
		
		ImageIcon playIcon = new ImageIcon("/Users/meghanpendyala/Desktop/CSCI 2300/FinalProject/bin/Play_Button.png");
	    JButton playButton = new JButton(playIcon);
		playButton.setOpaque(false);
		playButton.setBorderPainted(false);
		playButton.setContentAreaFilled(false);
	    
	    ImageIcon trophy = new ImageIcon("/Users/meghanpendyala/Desktop/CSCI 2300/FinalProject/bin/trophy.png");
	    JButton highScoreButton = new JButton(trophy);
		highScoreButton.setOpaque(false);
		highScoreButton.setBorderPainted(false);
		highScoreButton.setContentAreaFilled(false);
	    
		ImageIcon info = new ImageIcon("/Users/meghanpendyala/Desktop/CSCI 2300/FinalProject/bin/Info_Button.png");
	    JButton infoButton = new JButton(info);
		infoButton.setOpaque(false);
		infoButton.setBorderPainted(false);
		infoButton.setContentAreaFilled(false);
		
		
	    add(playButton, BorderLayout.NORTH);
	    add(highScoreButton, BorderLayout.CENTER);
	    add(infoButton, BorderLayout.SOUTH);

	    
	    repaint();
	    revalidate();
	    
	    playButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	      	  resetGame(4, 1000);
	      	  wonGame = false;
	    	}
	    }); 
	    
	    infoButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent w){
	        	infoScreen();
	        }
	    });
	    
	    highScoreButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent r){
	        	highScoreScreen();
	        }
	    });

	}
	
	/**
	   * This method is used to display the score screen after the game
	   * is over. This screen will have buttons to go back to the main
	   * screen or start the game again. It will also have the option
	   * to enter a username to save to the high score board.
	   * @param wonGame This is the first parameter to scoreScreen method
	   */
	
	public void scoreScreen(boolean wonGame){
		removeAll();
		setLayout(new BorderLayout());
		setBackground(new Color(62,71,108));
	
		ImageIcon icon = new ImageIcon("/Users/meghanpendyala/Desktop/CSCI 2300/FinalProject/bin/small_trophy.png");
		JLabel label1 = new JLabel(Integer.toString(score), icon, JLabel.CENTER);
		label1.setVerticalTextPosition(JLabel.BOTTOM);
		label1.setHorizontalTextPosition(JLabel.CENTER);
		label1.setBackground(Color.WHITE);
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		label1.setBorder(BorderFactory.createCompoundBorder(raisedbevel, loweredbevel));

		label1.setFont (label1.getFont().deriveFont (64.0f));
		
		
		ImageIcon playIcon = new ImageIcon("/Users/meghanpendyala/Desktop/CSCI 2300/FinalProject/bin/Med_Play_Button.png");
	    JButton playButton = new JButton(playIcon);
		playButton.setOpaque(false);
		playButton.setBorderPainted(false);
		playButton.setContentAreaFilled(false);
	    
	    ImageIcon home = new ImageIcon("/Users/meghanpendyala/Desktop/CSCI 2300/FinalProject/bin/Med_Home_Button.png");
	    JButton homeButton = new JButton(home);
	    homeButton.setOpaque(false);
	    homeButton.setBorderPainted(false);
	    homeButton.setContentAreaFilled(false);
	    
	   
	    ImageIcon gameOver = new ImageIcon("/Users/meghanpendyala/Desktop/CSCI 2300/FinalProject/bin/GameOver.gif");			//326, 206
	    JLabel gameOverLabel = new JLabel(gameOver, JLabel.CENTER);
	    
	    ImageIcon won = new ImageIcon("/Users/meghanpendyala/Desktop/CSCI 2300/FinalProject/bin/WonGame.gif");
	    JLabel wonGameLabel = new JLabel(won, JLabel.CENTER);
	    
	    JTextField username = new JTextField("Enter your initials to save score!", 2);	 
	    username.setBackground(new Color(119,126,155));
	    
	    add(label1, BorderLayout.NORTH);
	    add(playButton,BorderLayout.EAST);
	    add(homeButton, BorderLayout.WEST);
	    add(username, BorderLayout.CENTER);
	    
	    if (!wonGame){
	    	add(gameOverLabel, BorderLayout.SOUTH);
	    }
	    else{
	    	add(wonGameLabel, BorderLayout.SOUTH);
	    }
	    
	    playButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	resetGame(4, 1000);
	        	score = 0;
	    	}
	    });
	    
	    homeButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	mainScreen();
	        	score = 0;
	    	}
	    });
	    
	    username.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	highScores.newScore(username.getText(), score);	    
	        	score = 0;
	        	mainScreen();
	        }
	    });
	    
	}
	
	/**
	   * This method is used to display the info screen. This 
	   * simply displays the directions to the game and also
	   * has a button to return to the main screen.
	   */
	
	public void infoScreen(){
		removeAll();
		setLayout(new BorderLayout());
		setBackground(new Color(152,158,179));
		
		JTextArea title = new JTextArea("DIRECTIONS: \n");
		title.setFont(new Font("Dialog", Font.BOLD, 60));
		title.setLineWrap(true);
		title.setWrapStyleWord(true);
		title.setBackground(new Color(152,158,179));
		
		String directions = "Click the play button, see the pattern and then repeat the pattern by clicking the right squares. Each level has more squares and gets faster!";

		JTextArea textArea = new JTextArea(directions);
		textArea.setFont(new Font("Dialog", Font.BOLD, 50));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBackground(new Color(152,158,179));
		
		ImageIcon home = new ImageIcon("/Users/meghanpendyala/Desktop/CSCI 2300/FinalProject/bin/Home_Button.png");
	    JButton homeButton = new JButton(home);
	    homeButton.setOpaque(false);
	    homeButton.setBorderPainted(false);
	    homeButton.setContentAreaFilled(false);
		
		add(title, BorderLayout.NORTH);
	    add(textArea, BorderLayout.CENTER);
	    add(homeButton, BorderLayout.SOUTH);

	    
	    homeButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	mainScreen();
	    	}
	    });
	    
	    repaint();
	    revalidate();
	    

	}
	
	/**
	   * This method is used to display the high score board. This 
	   * displays the top five scores of all time using the HighScores class.
	   * It also has a button to return to the main screen.
	   */
	
	public void highScoreScreen(){
		removeAll();
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBackground(new Color(49,52,63));

		Color[] scoreColors = {new Color(245,255,250), new Color(176,196,222), new Color(245,255,250), new Color(176,196,222), new Color(245,255,250)};
		ArrayList<Scores> scoreList = new ArrayList<>();
		
		scoreList = highScores.readScores();
		ImageIcon icon = new ImageIcon("/Users/meghanpendyala/Desktop/CSCI 2300/FinalProject/bin/iconTrophy.png");

		JLabel title = new JLabel("HIGH SCORES", JLabel.CENTER);
		title.setFont(new Font("Dialog", Font.BOLD, 50));
		title.setBackground(new Color(176,196,222));
		title.setOpaque(true);
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		title.setBorder(BorderFactory.createCompoundBorder(raisedbevel, loweredbevel));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(title);
		
		for(int i = 0; i < 5; i++){
			JLabel score = new JLabel(scoreList.get(i).getUsername() + ": " + scoreList.get(i).getScore() + " ", icon, JLabel.CENTER);
			score.setFont(new Font("Dialog", Font.BOLD, 38));
			score.setBackground(scoreColors[i]);
			score.setOpaque(true);
			score.setBorder(BorderFactory.createCompoundBorder(raisedbevel, loweredbevel));
			score.setAlignmentX(Component.CENTER_ALIGNMENT);
			add(score);
			repaint();
			revalidate();
		}
		
		ImageIcon home = new ImageIcon("/Users/meghanpendyala/Desktop/CSCI 2300/FinalProject/bin/Home_Button.png");
	    JButton homeButton = new JButton(home);
	    homeButton.setOpaque(false);
	    homeButton.setBorderPainted(false);
	    homeButton.setContentAreaFilled(false);
		homeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

	    
	    add(homeButton);
	    
	    homeButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	mainScreen();
	    	}
	    });
	    
	    repaint();
		revalidate();	    
	}

}
