// A simple java program to guess a number between 1 and 100
import java.util.Random;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        //Main method to start and run the game with replay support.
        Scanner sc = new Scanner(System.in);// creating a scanner object to read user input
        Random rand = new Random();// to generate the random number
        System.out.println("Welcome to the number guessing game");
        //initializing variables for use
        int guess;
        int tries=0;//initializing a variable to keep track of a number of tries before the user guesses it
        int min_tries=Integer.MAX_VALUE;//declaring a variable with the max possible value to check for least no of attempts taken during the program's execution
        int num = rand.nextInt(100) + 1;//generating random number from 1-100(inclusive)
        char playagain ='Y';//setting playagain to Y to allow the first round of game to occur
        boolean guessed=false;//initially setting guessed to false as a round has not been completed yet
        long start = System.currentTimeMillis();//keep a track of time in ms at the moment when the user would start guessing
        long min_dur=Long.MAX_VALUE;//declaring a variable with the max possible value to check for least time taken during the program's execution
        while (playagain =='Y'|| playagain =='y') {
            // core loop to allow replay of game till user wishes to play by entering Y when prompted to
            if(guessed){
                //if the previous round was complete, reset the state and generate new number
                num = rand.nextInt(100) + 1;
                tries=0;
                guessed=false;
                start=System.currentTimeMillis();
            }
            System.out.print("\nGuess a number between 1 to 100: ");
            if(sc.hasNextInt())//checking to make sure if entered input is of int value
                guess = sc.nextInt();//read input and store it if it is a valid value
            else{
                System.out.println("Invalid input, must be an integer");
                sc.next();//clear invalid input
                continue;//skip this iteration
            }
            if (guess > 100 || guess < 1) {//check to make sure the value is in between 1 and 100
                System.out.println("Invalid input, your guess must be within 1 to 100");
            }
            else {//core logic
                if (guess > num) {//too high guess block
                    System.out.println("Too high. Guess again!");
                    tries++;
                }
                else if (guess < num) {//too low guess block
                    System.out.println("Too low. Guess again!");
                    tries++;
                }
                else{//correct guess block
                    System.out.println("Congrats! You have guessed the number " + num + " correctly.");
                    tries++;
                    if(tries<min_tries)//check if current no of attempts is lesser than the previous min_attempts
                        min_tries=tries;//if yes, set min_tries to current no of tries
                    long end = System.currentTimeMillis();//track time at the end of a round
                    long dur = (end-start)/1000;//get the time elapsed in ms since the start of the round
                    if(dur<min_dur)//check if current time taken (in s) is lesser than previous time taken
                        min_dur=dur;//if yes, set min_dur to current duration taken for the round
                    if (tries == 1)
                        System.out.println("It took you only 1 attempt to guess it");
                    else
                        System.out.println("It took you " + tries + " attempts to guess it");
                    System.out.println("Time Taken: "+dur+" seconds");
                    System.out.println("\nCurrent least attempts to guess the number: "+min_tries+" attempts");
                    System.out.println("Current least time taken to guess the number: "+min_dur+" seconds");
                    System.out.print("\nDo you want to play again? (Y/N): ");//if a user wants to replay
                    playagain =sc.next().trim().charAt(0);//storing new choice (y/n)
                    guessed=true;//setting guessed as true as a round has been completed
                    //this allows the game to reset the state and generate a new number for the next round
                }
            }
        }
        System.out.println("Thanks for playing!");
        sc.close();
    }
}