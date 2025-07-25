// A simple java program to guess a number between 1 and 100
import java.util.Random;
import java.util.Scanner;
public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final Random rand = new Random();
    private static int tries=0;
    private static int dur;
    private static char playagain='y';
    public static void score(int min_tries, int min_dur){
        int score=101-tries-dur;//the score is calculated based on the number of attempts and time taken to guess the number
        System.out.println("\n==================== 🎯 SCOREBOARD 🎯 ====================");
        System.out.println("⏱️  Time Taken                : " + dur + " seconds");
        System.out.println("🎯 Attempts                   : " + tries);
        System.out.println("🏆 Score                      : " + score);
        System.out.println("📉 Least Attempts Overall     : " + min_tries + " attempts");
        System.out.println("⏳ Fastest Time Overall       : " + min_dur + " seconds");
        System.out.println("==========================================================\n");
    }
    public static void welcome() {
        System.out.println("\n==========================================================");
        System.out.println("🎮         WELCOME TO THE NUMBER GUESSING GAME!          🎮");
        System.out.println("==========================================================");
        System.out.println("📌 Set a custom number range.");
        System.out.println("📌 Try to guess the number selected by the computer.");
        System.out.println("📌 You'll be scored based on:");
        System.out.println("   - Number of attempts");
        System.out.println("   - Time taken to guess correctly");
        System.out.println("📌 You can play as many rounds as you'd like.");
        System.out.println("==========================================================");
        System.out.println("🟢 Enter 'Y' to play again or 'N' to exit.");
        System.out.println("💡 Tip: Think smart and guess wisely!");
        System.out.println("==========================================================");
    }
    public static int getRange(String prompt){
        int range;
        System.out.print("\n"+ prompt);
        while(true){
            if(sc.hasNextInt()) {
                range = sc.nextInt();
                break;
            }
            else{
                System.out.println("❌ Invalid input! Please enter a valid integer.");
                sc.next();
            }
        }
        return range;
    }
    public static void askReplay(){
        System.out.print("\n🔁 Do you want to play again? (Y/N): ");
        playagain = sc.next().trim().toLowerCase().charAt(0);//storing new choice (y/n)
        if (playagain == 'y') {
            replay();//if the user wants to play again, call the replay method
        }
        else if(playagain == 'n'){
            System.out.println("👋 Thanks for playing!");
            sc.close();
            System.exit(0);//exit the program
        }
        else{
            System.out.println("❗ Invalid input. Assuming you don't want to play again.");
            System.out.println("👋 Thanks for playing! See you next time.");
            sc.close();
            System.exit(0);
        }
    }
    public static void replay(){
        while(playagain == 'y') {
            play();//call the play method to start the game
            askReplay();
        }
    }
    public static int getGuess(int start_range, int end_range){
        int guess;
        while(true){
            System.out.print("\n🔢 Enter your guess (" + start_range + " to " + end_range + "): ");
            if(sc.hasNextInt()) {//checking to make sure if entered input is of int value
                guess = sc.nextInt();
                break;
            }
            else{
                System.out.println("❌ Invalid input! Please enter a valid integer.");
                sc.next();//clear invalid input
            }
        }
        return guess;
    }
    public static void play(){
        int start_range = getRange("Enter the starting number of the range: ");
        int end_range = getRange("Enter the ending number of the range: ");
        int guess;
        int min_tries=Integer.MAX_VALUE;//declaring a variable with the max possible value to check for least no of attempts taken during the program's execution
        int min_dur=Integer.MAX_VALUE;//declaring a variable with the max possible value to check for least time taken during the program's execution
        int num=rand.nextInt(start_range, end_range+1);//generating random number from start_range to end_range(inclusive)
        long start = System.currentTimeMillis();
        while(true) {
            guess= getGuess(start_range, end_range);//get the guess from the user
            if (guess > end_range || guess < start_range) {//check to make sure the value is in between starting and ending range
                System.out.println("⚠️  Your guess must be within the range " + start_range + " to " + end_range + ".");
            } else {//core logic
                if (guess > num) {//too high guess block
                    System.out.println("Too high. Guess again!");
                    tries++;
                } else if (guess < num) {//too low guess block
                    System.out.println("Too low. Guess again!");
                    tries++;
                } else {//correct guess block
                    System.out.println("\n🎉 Congratulations! You've guessed the number " + num + " correctly!");
                    tries++;
                    if (tries < min_tries)//check if current no of attempts is lesser than the previous min_attempts
                        min_tries = tries;//if yes, set min_tries to current no of tries
                    long end = System.currentTimeMillis();//track time at the end of a round
                    dur = (int) ((end - start) / 1000);//get the time elapsed in ms since the start of the round
                    if (dur < min_dur)//check if current time taken (in s) is lesser than previous time taken
                        min_dur = dur;//if yes, set min_dur to current duration taken for the round
                    score(min_tries, min_dur);
                    break;
                }
            }
        }
    }
    public static void main(String[] args) {
        welcome();//call the welcome method to print the welcome message
        play();//call the play method to start the game
        askReplay();//call the replay method to ask if the user wants to play again
    }
}