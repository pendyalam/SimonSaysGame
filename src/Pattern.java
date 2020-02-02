import java.util.ArrayList;
import java.util.Random;

public class Pattern {
	private int numPattern;
	
	public static ArrayList<Position> patternArray;
	
	public Pattern(){
		numPattern = 4;

		patternArray = new ArrayList<>();
	}
	
	/**
	   * This method is used to initialize the Pattern object.
	   * @param num This sets the number of squares in the pattern.
	   */
	
	public Pattern(int num){
		numPattern = num;

		patternArray = new ArrayList<>();
	}
	
	/**
	   * This method is used to create a pattern
	   */
	
	public void createPattern(){
		int rnd1; 
		int rnd2;
		
		Position curr = null;
		for (int i = 0; i < numPattern; i++){
			boolean good = false;
			
			while (!good){
				rnd1 = new Random().nextInt(numPattern);
				rnd2 = new Random().nextInt(numPattern);
				curr = new Position(rnd1,rnd2);
				good = checkPattern(curr);
			}
			patternArray.add(curr);
			good = false;
		}
	}
	
	/**
	   * This method is used to check if a pattern is not repeated.
	   * @param boolean This returns a boolean depending on if a pattern was repeated.
	   */
	
	public boolean checkPattern(Position curr){
		for (int i = 0; i < patternArray.size(); i++){
			if (patternArray.get(i).equals(curr)){
				return false;
			}
		}

		return true;
	}
	
	/**
	   * This method is used to display a pattern by setting the GameSquare's inPattern state as true.
	   */
	
	public void displayPattern(int counter){
		final GameSquare sq = GridGame.squares.getSquare(patternArray.get(counter).r, patternArray.get(counter).c);
		sq.setSquareInPattern();
	}
	
	/**
	   * This method is used to end a pattern by setting the GameSquare's inPattern state as false.
	   */
	
	public void endPattern(){
		for (int i = 0; i < numPattern; i++){
			final GameSquare sq = GridGame.squares.getSquare(patternArray.get(i).r, patternArray.get(i).c);
			sq.notInPattern();
		}

	}
	
}
