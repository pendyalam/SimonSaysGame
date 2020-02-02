
public class Scores implements Comparable<Scores>{
	protected String username;
	protected String score;
	
	/**
	   * This method is used to initialize the Scores object.
	   */
	
	public Scores(String name, String score){
		this.username = name;
		this.score = score;
	}
	
	/**
	   * This method is used to get the username in the Scores object. 
	   * @return String This is the username in the Scores object.
	   */
	
	public String getUsername(){
		return username;
	}
	
	/**
	   * This method is used to get the score in the Scores object. 
	   * @return String This is the score in the Scores object.
	   */
	
	public String getScore(){
		return score;
	}
	
	
	/**
	   * This method is used to sort Scores objects according to score.
	   * This uses the Comparable interface.
	   * @return int This is integer that reflects whether a score is lower than the other.
	   */
	
	public int compareTo (Scores o) {
		if (Integer.parseInt(this.score) > Integer.parseInt(o.getScore())){
			return -1;
		}
		else if (Integer.parseInt(this.score) < Integer.parseInt(o.getScore())){
			return 1;
		}
		return 0;
	}
	
}
