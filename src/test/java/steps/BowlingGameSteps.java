package steps;

import game.BowlingGame;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;

/*
   Filename: BowlingGameSteps.java
     Author: Minyen Huang
       Date: Apr 5, 2025 7:02:41 PM
Description: This program provides Cucumber test cases for Bowling Game
 */
public class BowlingGameSteps
{
	BowlingGame bowling;
	private ByteArrayOutputStream outputStreamCaptor;
	int rolledPins, actualResult, frame, frameIndex;
	int[][] scoresArray = new int[10][3];

	// Scenario: Telling how many pins after rolling a ball
	@Given("I am playing the game")
	public void i_am_playing_the_game()
	{
		bowling = new BowlingGame();
		outputStreamCaptor = new ByteArrayOutputStream();
	}

	@When("I roll a ball")
	public void i_roll_a_ball()
	{
		rolledPins = bowling.rollBall(1, 0, 10, "First");
	}

	@Then("The game should tell me how many pins I got")
	public void the_game_should_tell_me_how_many_pins_i_got()
	{
		assertTrue(rolledPins >= 0 && rolledPins <= 10);
	}

	// Scenario: Calculating total score for a regular game
	@Given("I roll the following ten frames:")
	public void i_roll_the_following_ten_frames(DataTable frames) {
		bowling = new BowlingGame();
		outputStreamCaptor = new ByteArrayOutputStream();
		List<Map<String, String>> frameList = frames.asMaps(String.class, String.class);
    for (int i = 0; i< frameList.size(); i++) {
    	Map<String, String> frame = frameList.get(i);
        int firstRoll = Integer.parseInt(frame.get("firstRoll"));
        int secondRoll = Integer.parseInt(frame.get("secondRoll"));
        String thirdRoll = frame.get("thirdRoll"); // Could be empty
        scoresArray[i][0] = firstRoll;
        scoresArray[i][1] = secondRoll;
        scoresArray[i][2] = thirdRoll==null ? 0 : Integer.parseInt(thirdRoll);
    }
	}
	
	@When("The game calculates the totoal score")
	public void the_game_calculates_the_totoal_score() {
		actualResult = bowling.getTotalScore(scoresArray);
	}
	
	//Scenario: Calculating total score for a perfect game
	//Scenario: Calculating total score for a no spare and no strike game
	@When("The game calculate the totoal score")
	public void the_game_calculate_the_totoal_score() {
		actualResult = bowling.getTotalScore(scoresArray);
	}
	
	@Then("The total score should be {int}")
	public void the_total_score_should_be(int expectedScore) {
	    assertEquals(expectedScore, actualResult);
	}
	
	//Scenario Outline: Calculating spare bonus
	@Given("I got a spare with {int} and {int} in frame {int}")
	public void I_got_a_spare(int firstRoll, int secondRoll, int frame) {
	    frameIndex = frame -1;
	    scoresArray[frameIndex][0] = firstRoll;
			scoresArray[frameIndex][1] = secondRoll;
	}
	
	@When("I knock down {int} for the next roll")
	public void i_knock_down_for_the_next_roll(int pins) {
		scoresArray[frameIndex + 1][0] = pins;
	}
	
//Scenario Outline: Calculating two strikes bonus for frame 1~8
	@Given("I got two strkes starting from frame {int}")
	public void i_got_two_strkes(int frame) {
	   frameIndex = frame -1;
	   scoresArray[frameIndex][0] = 10;
	   scoresArray[frameIndex+1][0] = 10;
	}
	
	@When("I knock down {int} for the third frame")
	public void i_knock_down_for_the_third_frame(int pins) {
		scoresArray[frameIndex + 2][0] = pins;
	}
	//Calculating two strikes bonus for frame 9
	@When("I knock down {int} for the second roll in tenth frame")
	public void i_knock_down_for_the_second_roll_in_tenth_frame(int pins) {
	    // Write code here that turns the phrase above into concrete actions
		scoresArray[frameIndex + 1][1] = pins;
	}
	
	@When("The game calculate the spare bonus")
	public void the_game_calculate_spare_bonus() {
		bowling.setSpareBonus(scoresArray, frameIndex);
	}

	@When("The game calculate the strike bonus")
	public void the_game_calculate_strike_bonus() {
		bowling.setStrikeBonus(scoresArray, frameIndex);
	}
	
	@Then("The bonus of this frame should be {int}")
	public void the_bonus_of_this_frame(int expectedBonus) {
	    actualResult = scoresArray[frameIndex][2];
	    assertEquals(expectedBonus, actualResult);
	}


	//Rolling a strike in the tenth frame
	@Given("I am playing for frame {int}")
	public void i_am_playing_for_frame(int tenthFrame) {
		bowling = new BowlingGame();  
		frameIndex = tenthFrame -1;
	}

	//Rolling a strike in the tenth frame
	@When("I roll a strke and knock down pins in the {int}")
	public void i_roll_a_strke_and_knock_down_pins_in_the(int secondRoll) {
		scoresArray[frameIndex][0] = 10;
		scoresArray[frameIndex][1] = secondRoll;
		
	}
	@Then("I should roll the third ball and the left pins should be {int}")
	public void i_should_roll_the_third_ball_and_the_left_pins_should_be(int thirdPins) {
		rolledPins = bowling.rollBall(frameIndex, 2, thirdPins, "Third");
		assertTrue(rolledPins >= 0 && rolledPins <= thirdPins);
	}

	//Rolling a spare in the tenth frame
	@When("I roll a spare with the {int} and {int}")
	public void i_roll_a_spare_with_the_and(int first, int second) {
		scoresArray[frameIndex][0] = first;
		scoresArray[frameIndex][1] = second;
	}
	@Then("The third roll should have {int} pins to be knocked down")
	public void the_third_roll_should_have_pins_to_be_knocked_down(int thirdPins) {
		rolledPins = bowling.rollBall(frameIndex, 2, thirdPins, "Third");
		assertTrue(rolledPins >= 0 && rolledPins <= thirdPins);
	}

	//Calculating scores for the tenth frame
	@When("I knock down pins in the {int}, {int} and {int}")
	public void i_knock_down_pins_in_the_and(int first, int second, int third) {
		scoresArray[frameIndex][0] = first;
		scoresArray[frameIndex][1] = second;
		scoresArray[frameIndex][2] = third;
	}
	
	@When("The game calculate the frame score")
	public void the_game_calculate_the_frame_score() {
		actualResult = bowling.getTotalScore(scoresArray);
	}
	
	@Then("The score of the tenth frame should be {int}")
	public void the_score_of_the_tenth_frame_should_be(int expectedScore) {
		assertEquals(expectedScore, actualResult);
	}
	

}
