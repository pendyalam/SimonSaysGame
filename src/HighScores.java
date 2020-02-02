import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.io.IOException;

public class HighScores{
	protected ArrayList<Scores> scores = new ArrayList<Scores>(); 					//Only store top ten scores
		
	private String filename;
	
	/**
	   * This method is used to initialize the HighScores object.
	   */
	
	public HighScores(){
		filename = "/Users/meghanpendyala/Desktop/CSCI 2300/FinalProject/bin/highscores.txt";
	}
	
	/**
	   * This method is used to add a newScore the HighScores object by writing
	   * to a text file that contains all high scores.
	   * @param username This is the username associated with the score.
	   * @param score This is the total score after the game ends.
	   */
	
	public void newScore(String username, int score){
		try{
			FileWriter writer = new FileWriter(filename, true);
			writer.write(username + " " + Integer.toString(score) + System.getProperty("line.separator"));
			writer.close();
		}
		catch (IOException e){
			System.out.println("No High Scores Available Currently.");
		}
	}
	
	/**
	   * This method is used to read the text file that contains all of the Scores.
	   * @return ArrayList<Scores> This returns an ArrayList of sorted Scores.
	   */
	
	public ArrayList<Scores> readScores(){
		String[] currentLine; 
		scores = new ArrayList<>();
		try{
			FileReader reader = new FileReader(filename);
			BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            Scores newScore;
            while ((line = bufferedReader.readLine()) != null) {
                currentLine = new String[2];
                currentLine = line.split("\\s");
                newScore = new Scores(currentLine[0], currentLine[1]);
                scores.add(newScore);
            }
            reader.close();
		}
		catch(IOException e){
			System.out.println("No High Scores Available Currently");
		}
		
		Collections.sort(scores);
		return scores;
	}
	
}
