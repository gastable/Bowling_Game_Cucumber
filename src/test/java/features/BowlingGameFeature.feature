Feature: Bowling Game
 
  Scenario: Telling how many pins after rolling a ball
  	Given I am playing the game
  	When I roll a ball
  	Then The game should tell me how many pins I got
  	
  Scenario: Calculating total score for a regular game
  	Given I roll the following ten frames:
  	| frame | firstRoll | secondRoll | thirdRoll |
  	| 1     | 1         | 4          |           |
  	| 2     | 4         | 5          |           |
  	| 3     | 6         | 4          |           |
  	| 4     | 5         | 5          |           |
  	| 5     | 10        | 0          |           |
  	| 6     | 0         | 1          |           |
  	| 7     | 7         | 3          |           |
  	| 8     | 6         | 4          |           |
  	| 9     | 10        | 0          |           |
  	| 10    | 2         | 8          | 6         | 
  	When The game calculates the totoal score
  	Then The total score should be 133
  	
  Scenario: Calculating total score for a perfect game
  	Given I roll the following ten frames:
  	| frame | firstRoll | secondRoll | thirdRoll |
  	| 1     | 10        | 0          |           |
  	| 2     | 10        | 0          |           |
  	| 3     | 10        | 0          |           |
  	| 4     | 10        | 0          |           |
  	| 5     | 10        | 0          |           |
  	| 6     | 10        | 0          |           |
  	| 7     | 10        | 0          |           |
  	| 8     | 10        | 0          |           |
  	| 9     | 10        | 0          |           |
  	| 10    | 10        | 10         | 10        | 
  	When The game calculate the totoal score
  	Then The total score should be 300
  	
  Scenario: Calculating total score for a no spare and no strike game
  	Given I roll the following ten frames:
  	| frame | firstRoll | secondRoll | thirdRoll |
  	| 1     | 5         | 3          |           |
  	| 2     | 8         | 1          |           |
  	| 3     | 6         | 2          |           |
  	| 4     | 9         | 0          |           |
  	| 5     | 0         | 9          |           |
  	| 6     | 0         | 0          |           |
  	| 7     | 4         | 4          |           |
  	| 8     | 3         | 5          |           |
  	| 9     | 1         | 8          |           |
  	| 10    | 0         | 6          | 0         | 
  	When The game calculate the totoal score
  	Then The total score should be 74
  	
  Scenario Outline: Calculating spare bonus
  	Given I got a spare with <firstRoll> and <secondRoll> in frame <frame>
  	When I knock down <pins> for the next roll
  	And The game calculate the spare bonus
  	Then The bonus of this frame should be <bonus>
  	
  	Examples: 
  		| frame | firstRoll | secondRoll | pins | bonus |
  	  | 1     | 0         | 10         | 0    | 0     |
  	  | 2     | 1         | 9          | 5    | 5     |
  	  | 9     | 5         | 5          | 10   | 10    |
  
  Scenario Outline: Calculating two strikes bonus for frame 1~8
  	Given I got two strkes starting from frame <frame>
  	When I knock down <pins> for the third frame
  	And The game calculate the strike bonus
  	Then The bonus of this frame should be <bonus>
  	
  	Examples: 
  		| frame | pins | bonus |
  		| 1     | 0    | 10    |
  		| 2     | 1    | 11    |  
  		| 8     | 10   | 20    | 
  		  
  Scenario Outline: Calculating two strikes bonus for frame 9
  	Given I got two strkes starting from frame 9
  	When I knock down <pins> for the second roll in tenth frame
  	And The game calculate the strike bonus
  	Then The bonus of this frame should be <bonus>
  	
  	Examples: 
  		| pins | bonus |
  		| 0    | 10    |
  		| 1    | 11    |  
  		| 10   | 20    | 

 
   Scenario Outline: Rolling a strike in the tenth frame
  	Given I am playing for frame 10
  	When I roll a strke and knock down pins in the <secondRoll>
  	Then I should roll the third ball and the left pins should be <leftPins>
  	
  	Examples: 
  		| secondRoll | leftPins |
  		| 0          | 10       |
  		| 1          | 9        |
  		| 9          | 1        |
  		| 10         | 10       |
  		
  Scenario: Rolling a spare in the tenth frame
  	Given I am playing for frame 10
  	When I roll a spare with the <firstRoll> and <secondRoll>
  	Then The third roll should have 10 pins to be knocked down
  	
  	Examples: 
  		| firstRoll | secondRoll |
  		| 0         | 10         |
  		| 1         | 9          |
  		| 9         | 1          |


  Scenario Outline: Calculating scores for the tenth frame
  	Given I am playing for frame 10
  	When I knock down pins in the <firstRoll>, <secondRoll> and <thirdRoll>
  	And The game calculate the frame score 
  	Then The score of the tenth frame should be <score>
  	
  	Examples: 
  		| firstRoll | secondRoll | thirdRoll | score |
  		| 0         | 0          | 0         | 0     |
  		| 3         | 6          | 0         | 9     |
  		| 10        | 3          | 7         | 20    |
  		| 4         | 6          | 5         | 15    |
    	| 10        | 10         | 0         | 20    |
    	| 0         | 10         | 10        | 20    |
    	| 10        | 10         | 10        | 30    |

    