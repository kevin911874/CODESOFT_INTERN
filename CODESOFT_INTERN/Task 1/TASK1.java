
import java.util.*;

public class TASK1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        boolean playAgain = true;
        int roundsWon = 0;

        while (playAgain) {
            int numberToGuess = random.nextInt(100) + 1; // 1 to 100
            int attemptsLeft = 7;
            boolean guessedCorrectly = false;

            System.out.println("\nNew Round! Guess the number between 1 and 100.");
            System.out.println("You have " + attemptsLeft + " attempts.");

            while (attemptsLeft > 0) {
                System.out.print("Enter your guess: ");
                int guess = sc.nextInt();

                if (guess == numberToGuess) {
                    System.out.println("🎉 Correct! You've guessed the number!");
                    guessedCorrectly = true;
                    roundsWon++;
                    break;
                } else if (guess < numberToGuess) {
                    System.out.println("Too low!");
                } else {
                    System.out.println("Too high!");
                }

                attemptsLeft--;
                System.out.println("Attempts left: " + attemptsLeft);
            }

            if (!guessedCorrectly) {
                System.out.println(" You've run out of attempts! The correct number was: " + numberToGuess);
            }

            System.out.print("Do you want to play another round? (yes/no): ");
            sc.nextLine(); // Consume newline
            String response = sc.nextLine();
            playAgain = response.equalsIgnoreCase("yes");
        }

        System.out.println("\nThanks for playing! 🏆 You won " + roundsWon + " round(s).");
        sc.close();
    }
}
