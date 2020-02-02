public class Position
{
	protected int r;
	protected int c;

	public Position (Position p) {
		this (p.r, p.c); // Sets rows and columns
	}

	/**
	   * This method is used to initialize the Position object.
	   */
	
	public Position (int r, int c) {
		this.r = r;
		this.c = c;
	}
	
	/**
	   * This method is used to set the row and column.
	   * @param r This sets the row of the Position.
	   * @param c This sets the column of the Position.
	   */
	
	public void setPos(int r, int c){
		this.r = r;
		this.c = r;
	}
	
	/**
	   * This method is used to check if two Positions are equal.
	   * @param pos This is the other Position for comparison.
	   */
	
	public boolean equals (Position pos) // Creates an operator that compares two positions
	{
		if ((r == pos.r) && (c == pos.c))
			return true;
		else
			return false;
	}
}