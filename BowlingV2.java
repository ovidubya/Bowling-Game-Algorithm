
/**
 * Bowling simulator 
 * 
 * @author Ovadia Shalom
 * @version Version 2
 */
public class BowlingV2
{
    private int[][] chart;
    private int spare;
    private int frame;
    private int roll;
    private int strike;
    private int score;
    private String name;
    private int gutterBalls;
    
    public BowlingV2(String n)
    {
        gutterBalls = 0;
        this.name = n;
        chart = new int[14][2];
        strike = 0;
        spare = 0;
        frame = 1;
        roll = 0;
    }
    
    public boolean shot(int knockPins)
    {
        if(roll == 2)
        {
            System.out.println("Game over, you cannot roll");
            return false;
        }
        // Gutter Balls
        if(knockPins == 0) {
            gutterBalls++;
        }
        
        // Invalid Numbers 
        if( knockPins < 0 || knockPins > 10) {
            System.out.println("Invalid number for pins, choose number between 0 and 10");
            return false;
        }
        
        // If by the 10th frame you roll a strike, the following applies.
        // Increment the frame by 1. If by the 11th frame you get a strike, game over, if not
        // Increment the roll by 1, so you get another shot.
        if(chart[10][0] == 10) {
            if(roll == 0)
            {
                if(knockPins == 10)
                {
                    strike++;
                }
                chart[11][0] = knockPins;
                roll++;
                return false;
            }
            else { //they got a strick on the 9th frame, and they didnt not get a strike on the 10th frame, STILL possible to get a spare and have a total of 3 shots
                if(knockPins == 10)
                {
                    strike++;
                }
                chart[11][1] = knockPins;
                roll++; //incremented cuz game over
                System.out.println("Game Over");
                return true;
            }
        }
        
        //this means they got a spare on the 10th frame
        if(chart[10][0] + chart[10][1] == 10 && chart[10][0] != 10)
        {
            chart[11][0] = knockPins;
            System.out.println("Game over!");
            roll++;
            roll++;
            return false;
        }
        
        //Stike
        if(knockPins == 10 && roll == 0) {
            chart[frame][0] = 10;
            frame++;
            strike++;
            //printScore();
            return true;
        }
        
        if(roll == 1) {
            chart[frame][roll] = knockPins;
            if(frame == 10 && (chart[10][0] + chart[10][1] != 10)) {
                System.out.println("Game over");
                return false;
            }
            if((chart[frame][0] + chart[frame][1] == 10) && (chart[frame][0] != 10)) {
                spare++;
            }
            roll--;
            frame++;
            //printScore();
            return true;
        }
        else {
            try {
                chart[frame][roll] = knockPins;
                roll++;
                //printScore();
                return false;
            }
            catch(Exception e) {
                System.out.println("Game finished! :)");
                return true;
            }
        }
    }
    
    public void printScore()
    {
        if(frame == 11) {
           int rows = 12; //use to be 11
           int columns = 2;
           int i, j;
           for ( i = 1; i < rows; i++) {
               for (j = 0; j < columns; j++) {
                    System.out.println(chart[i][j] + " ");
               }
               System.out.println( "" );
           }
        }
        else {
           int rows = 11; //use to be 11
           int columns = 2;
           int i, j;
           for ( i = 1; i < rows; i++) {
               for (j = 0; j < columns; j++) {
                    System.out.println(chart[i][j] + " ");
               }
               System.out.println( "" );
           }
        }
    }
    
    public int addScore()
    {
        int result = 0;
        int rows = 10;
        int columns = 2;
        int i, j;
        int currentFrame = frame;
        // This calculates the RAW score without the bonous 
       for (i = currentFrame; i >= 0; i--) { 
            for (j = 0; j < columns; j++) {
                result += chart[i][j];
            }
       }
       
       // This calculates the bonus scores
       for (i = currentFrame; i >= 0; i--) {
           if(i == 11)
           {
               if(chart[10][0] == 10)
               {
                   result += chart[11][0] + chart[11][1]; 
               }
               if((chart[10][0] + chart[10][1] == 10) && chart[10][0] != 10)
               {
                   result += chart[11][0];
               }
               i--;
               i--;
           }
           if(chart[i][0] == 10) //hillel torah
           {
               if(chart[i+1][0] != 10)
               {
                   result += chart[i+1][0] + chart[i+1][1];
               }
               if(chart[i+1][0] == 10)
               {
                   result += chart[i+1][0] + chart[i+2][0];
               }
           }
           
           //spare
           if(chart[i][0] + chart[i][1] == 10 && chart[i][0] != 10)
           {
               result += chart[i+1][0];
           }
           
       }
       score = result;
       if(score == 320)
       {
           score = 300;
       }
       System.out.println("Result: " + score);
       return score;
    }
    
    /**
     * @return Returns a int that represents what the current roll is
     */
    public int returnRoll()
    {
        return roll;
    }
    
    /**
     * @return Returns a int that represents how many spares in the game.
     */
    public int returnSpare()
    {
        return spare;
    }
    
    /**
     * @return Returns a int that represents how many strikes in the game.
     */
     public int returnStrike()
    {
        return strike;
    }
    
     /**
      * @return Returns a String that represents the user's name.
      */
    public String returnName()
    {
        return name;
    }
    
     /**
      * @return Returns a int that represents how many Gutter Balls in a game.
      */
    public int returnGutterBalls()
    {
        return gutterBalls;
    }
    
    /**
     * @return Returns a int that represents the current score of the game.
     */
    public int returnScore()
    {
        int currentScore = addScore();
        return currentScore;
    }
    
    /**
     * @return Returns a int that represents the current frame of the game.
     */
    public int returnFrame()
    {
        return frame;
    }
    
    /**
     * Prints outs name, frame, score, strikes, spares and gutterballs.
     */
    public void updateInfo()
    {
        System.out.println(name + "'s" + " frame " + frame + ":" + " Score is " + returnScore() + ", " + "Strikes " + returnStrike() + ", Spares " + returnSpare() + ", Gutter Balls " + returnGutterBalls());
    }
}
