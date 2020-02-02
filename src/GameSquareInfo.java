
public class GameSquareInfo {

	private GameSquare[][] squares; // matrix
	
	public GameSquareInfo() {
		squares = new GameSquare[GridGame.currentSize][GridGame.currentSize]; // Creates new GameSquare object
	}
	
	/**
	   * This method is used to get the GameSquare at the certain row
	   * and column.
	   * @param row This is the first parameter to getSquare method
	   * @param col  This is the second parameter to getSquare method
	   * @return GameSquare This returns the GameSquare at the row and col.
	   */
	
	public GameSquare getSquare (int row, int col) { // Returns the square of that position
		if (validPosition (row, col))
			return squares[row][col];
		else
			return null;
	}
	
	/**
	   * This method is used to set a GameSquare at a certain position.
	   * @param sq This is the first parameter to setSquare method
	   */
	
	public void setSquare (GameSquare sq) // Creates a square at that square object
	{ 
		Position pos = sq.getPosition();
		
		if (validPosition (pos))
			squares[pos.r][pos.c]= sq;
	}
	
	/**
	   * This method is used to get a GameSquare at a certain position. 
	   * @param pos This is the first parameter to getSquare method
	   * @return GameSquare This returns a GameSquare at a certain position.
	   */
	
	public GameSquare getSquare (Position pos) {
		return getSquare (pos.r, pos.c);
	}
	
	/**
	   * This method is used to check if a certain row and column
	   * is a validPosition.
	   * @param row This is the first parameter to validPosition method
	   * @param col  This is the second parameter to validPosition method
	   * @return boolean This returns whether or not.
	   */
	
	protected boolean validPosition (int row, int col) 	// Checks if it's a valid position
	{
		if ( row < 0)
			return false;
		else if (row >= GridGame.currentSize)
			return false;
			
		if (col < 0)
			return false;
		else if (col >= GridGame.currentSize)
			return false;
		
		return true;
	}
	
	/**
	   * This method is used to check if a certain position
	   * is a validPosition.
	   * @param position This is the first parameter to validPosition method
	   * @return boolean This returns whether or not.
	   */
	
	protected boolean validPosition (Position pos) {
		return validPosition (pos.r, pos.c);
	}
}
