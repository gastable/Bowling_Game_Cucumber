/*
   Filename: BowlingGame.java
     Author: Minyen Huang
       Date: Mar 30, 2025 11:41:25 AM
Description: This program plays a bowling game with users
 */
package game;


import java.util.Scanner;

public class BowlingGame
{
	private static final int FRAMES = 10;  //Number of frames to play
  private static final int MAX_PINS = 10;  //Number of pins per frame
	private static int[][] scoresArray = new int[FRAMES][3]; //2-D array to store scores in each frame
	private static final Scanner scanner = new Scanner(System.in);  //To prompt user to roll balls
	
	public void playGame()
	{
		System.out.println("*********Welcome to Bowling Game!!!***********");
		System.out.println("You will be playing "+FRAMES+" frames. Each frame you roll 2 times to knock down "+MAX_PINS+" pins.");

		// Play Frame 1 to Frame 9
		playRegularFrames();
		
		// Play Frame 10
		playLastFrame();
		
		//Calculate total score
		getTotalScore(scoresArray);
		scanner.close();
	}

	public static void playRegularFrames()
	{
		int firstScore ;
		int secondScore ;
		for (int frame = 1; frame <= FRAMES - 1; frame++)
		{
			System.out.println("*** Frame " + frame +" ***");
			System.out.print("Press Enter to roll the next ball.");
			scanner.nextLine();
			//Throw first ball
			firstScore = rollBall(frame, 0, MAX_PINS, "First");
			//Skip second roll if the first one is a strike
			secondScore = firstScore == MAX_PINS ? 0 : rollBall(frame, 1, MAX_PINS-firstScore, "Second");
		}
	}
	
	public static void playLastFrame()
	{
		int firstScore ;
		int secondScore ;
		int thirdScore ;
		
		System.out.println("*** Frame " + FRAMES +" ***");
		firstScore = rollBall(FRAMES, 0, MAX_PINS, "First");

		//If first roll is strike
		if (firstScore == MAX_PINS)
		{
			System.out.println("First roll is strike");
			secondScore = rollBall(FRAMES, 1, MAX_PINS, "Second");
			
			//If second roll is strike
			thirdScore = secondScore == MAX_PINS ? rollBall(FRAMES, 2, MAX_PINS, "Third") : rollBall(FRAMES, 2, MAX_PINS - secondScore, "Third");
		} 
		else
		{
			secondScore = rollBall(FRAMES, 1, MAX_PINS - firstScore, "Second");;
		}
		
		// If this frame is spare
		if ((firstScore != MAX_PINS) && (firstScore + secondScore == MAX_PINS))
		{
			System.out.println("Second roll is spare.");
			
			thirdScore = rollBall(FRAMES, 2, MAX_PINS, "Third");
		}
	}
	
	public static int rollBall(int frameIndex, int rollIndex, int leftPins, String rollString)
	{
		int score = (int) (Math.random() * (leftPins + 1));
		scoresArray[frameIndex-1][rollIndex] = score;
		
		System.out.println(rollString + " roll is: " + score + " pin(s).");
		
		return score;
	}

	public static int getTotalScore(int[][] scoresArray)
	{
		int total = 0;
		int frameScore =0;
		//Set Strike and Spare bonuses before calculating total score
		//setStrikeAndSpareBonus(scoresArray);
		//Calculate total score
		for (int frameIndex = 0; frameIndex < FRAMES; frameIndex++)
		{
			//Only frame 1 to 9 has bonus
			if(frameIndex < 9)
			{
				setSpareBonus(scoresArray, frameIndex);
				setStrikeBonus(scoresArray, frameIndex);
			}
			frameScore = scoresArray[frameIndex][0] + scoresArray[frameIndex][1] + scoresArray[frameIndex][2];
			//System.out.println("Frame " + (frame + 1) + " score is " + frameScore);
			total += frameScore;
		}
		System.out.println("Your total score is " + total );
		return total;
	}
	
	public static void setSpareBonus(int[][] scoresArray, int frameIndex)
	{
				if (scoresArray[frameIndex][0] + scoresArray[frameIndex][1] == MAX_PINS)
				{
					scoresArray[frameIndex][2] += scoresArray[frameIndex + 1][0];
				}	
	}
	
	public static void setStrikeBonus(int[][] scoresArray, int frameIndex)
	{
				if (scoresArray[frameIndex][0] == MAX_PINS)
				{
					//For frame 1 to 8:
					if(frameIndex < 8)
					{
						//If second roll is also a strike
						if(scoresArray[frameIndex+1][0] == MAX_PINS)
						{
							scoresArray[frameIndex][2] = MAX_PINS + scoresArray[frameIndex + 2][0];
						}
						else
						{
							scoresArray[frameIndex][2] = scoresArray[frameIndex + 1][0] + scoresArray[frameIndex + 1][1];
						}
					}
					else
					{
						//For frame 9
						//If second roll is also a strike
						scoresArray[frameIndex][2] = scoresArray[frameIndex + 1][0] + scoresArray[frameIndex + 1][1];
					}
				}
	}
	
}
