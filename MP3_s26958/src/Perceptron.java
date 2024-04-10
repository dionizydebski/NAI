import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Perceptron {
    private double[] weight_vector;
    private double threshold;
    private final double learning_rate;
    private ArrayList<Observation> train_arr;
    private ArrayList<Observation> test_arr;

    public Perceptron(double learning_rate, String train_set, String test_set) {
        this.learning_rate = learning_rate;

        this.train_arr = Observation.makeArr(train_set);
        Collections.shuffle(train_arr);
        this.test_arr = Observation.makeArr(test_set);

        Random random = new Random();
        this.weight_vector = new double[train_arr.getFirst().getLength()];
        for(int i = 0; i < weight_vector.length; i++)
            weight_vector[i] = random.nextDouble(11) - 5;
        this.threshold = random.nextDouble(11)-5;


    }

    public void learn(){
        for(Observation o : train_arr){
            //net
            double net = 0;
            for(int i = 0; i < o.getAttributte().length; i++){
                net += o.getAttributte()[i] * weight_vector[i];
            }
            net -= threshold;

            //d
            int d;
            if(o.getDecision_attribute().equals(train_arr.getFirst().getDecision_attribute()))
                d = 1;
            else
                d = 0;

            //y - output
            int y;
            if(net < 0)
                y = 0;
            else
                y = 1;

            //w'
            for(int i = 0; i < o.getAttributte().length; i++){
                weight_vector[i] += (d-y)*learning_rate*o.getAttributte()[i];
            }

            //t'
            threshold -= (d-y)*learning_rate;
        }
    }

    public void testIt(){
        //acc
        double good_answers_first = 0;
        double all_answers_first = 0;
        double good_answers_second = 0;
        double all_answers_second = 0;
        for(Observation o : test_arr){
            //net
            double net = 0;
            for(int i = 0; i < o.getAttributte().length; i++){
                net += o.getAttributte()[i] * weight_vector[i];
            }
            net -= threshold;

            //d
            int d;
            if(o.getDecision_attribute().equals(train_arr.getFirst().getDecision_attribute()))
                d = 1;
            else
                d = 0;

            //y - output
            int y;
            if(net < 0)
                y = 0;
            else
                y = 1;

            //acc
            if(d == y) {
                if (d == 1) {
                    good_answers_first++;
                    all_answers_first++;
                }
                else if (d == 0) {
                    good_answers_second++;
                    all_answers_second++;
                }
            }
            else if (d == 1)
                all_answers_first++;
            else if (d == 0)
                all_answers_second++;
        }
        double whole_acc = (good_answers_first+good_answers_second)/(all_answers_first+all_answers_second);
        double first_acc = good_answers_first/all_answers_first;
        double second_acc = good_answers_second/all_answers_second;
//        System.out.println(good_answers_first + " " + good_answers_second);
//        System.out.println(all_answers_first + " " + all_answers_second);
        System.out.println("Whole acc: " + whole_acc);
        System.out.println("First class acc " + first_acc);
        System.out.println("Second class acc " + second_acc);
//        for(double i : weight_vector)
//            System.out.print(i + ";");
//        System.out.println();
//        System.out.println(threshold);
    }

    public void testItYourself(Observation o){
        //net
        double net = 0;
        for(int i = 0; i < o.getAttributte().length; i++){
            net += o.getAttributte()[i] * weight_vector[i];
        }
        net -= threshold;

        //y - output
        int y;
        if(net < 0)
            y = 0;
        else
            y = 1;

        System.out.println(y);
        System.out.println("Where 1: " + train_arr.getFirst().getDecision_attribute());
    }
}
