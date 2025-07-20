// A simple java program to guess a number between 1 and 100
import java.util.Random;
import java.util.Scanner;
public class Main {
    static int score=0;
    public static void main(String[] args) {
        //Main method to start and run the game with replay support.
        Scanner sc = new Scanner(System.in);// creating a scanner object to read user input
        Random rand = new Random();// to generate the random number
        int end_range,start_range;
        System.out.println("\nWelcome to the number guessing game");
        System.out.println("Guess a random number from a range that you want");
        while(true){
            System.out.print("\nEnter the starting number of the range: ");
            if(sc.hasNextInt()) {
                start_range = sc.nextInt();
                break;
            }
            else{
                System.out.println("Invalid input, must be an integer");
                sc.next();
            }
        }
        while(true){
            System.out.print("\nEnter the ending number of the range: ");
            if(sc.hasNextInt()) {
                end_range = sc.nextInt();
                break;
            }
            else{
                System.out.println("Invalid input, must be an integer");
                sc.next();
            }
        }
        //initializing variables for use
        int guess;
        int tries=0;//initializing a variable to keep track of a number of tries before the user guesses it
        int min_tries=Integer.MAX_VALUE;//declaring a variable with the max possible value to check for least no of attempts taken during the program's execution
        int num = rand.nextInt(start_range,end_range+1);//generating random number from start_range to end_range(inclusive)
        char playagain = 'y';//setting playagain to Y to allow the first round of game to occur
        boolean guessed=false;//initially setting guessed to false as a round has not been completed yet
        long start = System.currentTimeMillis();//keep a track of time in ms at the moment when the user would start guessing
        long min_dur=Long.MAX_VALUE;//declaring a variable with the max possible value to check for least time taken during the program's execution
        while (playagain =='y') {
            // core loop to allow replay of game till user wishes to play by entering Y when prompted to
            if(guessed){
                //if the previous round was complete, reset the state and generate new number
                num = rand.nextInt(start_range,end_range+1);
                tries=0;
                guessed=false;
                start=System.currentTimeMillis();
            }
            System.out.print("\nGuess a number between "+ start_range+" to "+ end_range +" : ");
            if(sc.hasNextInt())//checking to make sure if entered input is of int value
                guess = sc.nextInt();//read input and store it if it is a valid value
            else{
                System.out.println("Invalid input, must be an integer");
                sc.next();//clear invalid input
                continue;//skip this iteration
            }
            if (guess > end_range || guess < start_range) {//check to make sure the value is in between starting and ending range
                System.out.println("Invalid input, your guess must be within "+ start_range + "to "+ end_range);
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
                    int dur = (int)((end-start)/1000);//get the time elapsed in ms since the start of the round
                    if(dur<min_dur)//check if current time taken (in s) is lesser than previous time taken
                        min_dur=dur;//if yes, set min_dur to current duration taken for the round
                    if (tries == 1)
                        System.out.println("It took you only 1 attempt to guess it");
                    else
                        System.out.println("It took you " + tries + " attempts to guess it");
                    score=101-tries-dur;
                    System.out.println("Time Taken: "+dur+" seconds");
                    System.out.println("Your score is: "+score);
                    System.out.println("\nCurrent least attempts to guess the number: "+min_tries+" attempts");
                    System.out.println("Current least time taken to guess the number: "+min_dur+" seconds");
                    System.out.print("\nDo you want to play again? (Y/N): ");//if a user wants to replay
                    playagain =sc.next().trim().toLowerCase().charAt(0);//storing new choice (y/n)
                    guessed=true;//setting guessed as true as a round has been completed
                    //this allows the game to reset the state and generate a new number for the next round
                }
            }
        }
        System.out.println("Thanks for playing!");
        sc.close();
    }
}