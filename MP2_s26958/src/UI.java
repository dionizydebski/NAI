import java.util.Scanner;

public class UI {
    public static void start(double learning_rate, String train_set, String test_set){
        Perceptron perceptron = new Perceptron(learning_rate,train_set,test_set);
        perceptron.learn();
        perceptron.testIt();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Wanna learn it again? (y/n)");
        if(scanner.nextLine().equals("y"))
            perceptron.learn();

        System.out.println("Wanna test it again? (y/n)");
        if(scanner.nextLine().equals("y"))
            perceptron.testIt();

        System.out.println("Wanna test it yourself? (y/n)");
        while(scanner.nextLine().equals("y")){
            perceptron.testItYourself(new Observation(scanner.nextLine() + ";" + " "));
            System.out.println("Wanna test it yourself? (y/n)");
        }
    }
}
