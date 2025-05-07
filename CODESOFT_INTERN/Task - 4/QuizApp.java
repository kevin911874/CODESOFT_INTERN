import java.util.*;

class Question {
    String question;
    String[] options;
    char correctAnswer;

    public Question(String question, String[] options, char correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

public class QuizApp {
    static Scanner scanner = new Scanner(System.in);
    static int score = 0;
    static List<String> summary = new ArrayList<>();

    public static void main(String[] args) {
        List<Question> questions = loadQuestions();

        System.out.println("Welcome to the Java Quiz!");
        System.out.println("You have 10 seconds to answer each question.\n");

        for (int i = 0; i < questions.size(); i++) {
            askQuestion(i + 1, questions.get(i));
        }

        // Final Score
        System.out.println("\n=== Quiz Finished ===");
        System.out.println("Your Final Score: " + score + "/" + questions.size());
        System.out.println("\nAnswer Summary:");
        for (String s : summary) {
            System.out.println(s);
        }
    }

    public static List<Question> loadQuestions() {
        List<Question> qList = new ArrayList<>();
        qList.add(new Question("What is the capital of France?",
                new String[]{"A. London", "B. Paris", "C. Rome", "D. Berlin"}, 'B'));
        qList.add(new Question("Who developed Java?",
                new String[]{"A. Dennis Ritchie", "B. Bjarne Stroustrup", "C. James Gosling", "D. Guido van Rossum"}, 'C'));
        qList.add(new Question("Which keyword is used to inherit a class in Java?",
                new String[]{"A. super", "B. extends", "C. implements", "D. inherits"}, 'B'));
        qList.add(new Question("Which method is the entry point of Java programs?",
                new String[]{"A. start()", "B. init()", "C. main()", "D. run()"}, 'C'));
        qList.add(new Question("Which data type is used to create a variable that should store text?",
                new String[]{"A. string", "B. String", "C. txt", "D. char"}, 'B'));
        return qList;
    }

    public static void askQuestion(int qNo, Question q) {
        System.out.println("Q" + qNo + ": " + q.question);
        for (String opt : q.options) {
            System.out.println(opt);
        }
    
        String[] userInput = {null};
    
        Thread inputThread = new Thread(() -> {
            System.out.print("Your answer (A/B/C/D): ");
            userInput[0] = scanner.nextLine().toUpperCase();
        });
    
        inputThread.start();
    
        try {
            inputThread.join(10000); // wait up to 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    
        if (userInput[0] == null) {
            System.out.println("\nTime's up!");
            summary.add("Q" + qNo + ": Time out! Correct answer: " + q.correctAnswer);
            inputThread.interrupt(); // clean exit
            return;
        }
    
        char inputChar = userInput[0].charAt(0);
        if (inputChar == q.correctAnswer) {
            System.out.println("Correct!\n");
            score++;
            summary.add("Q" + qNo + ": Correct");
        } else {
            System.out.println("Incorrect! The correct answer was " + q.correctAnswer + "\n");
            summary.add("Q" + qNo + ": Incorrect (Your answer: " + inputChar + ")");
        }
    }    
}
