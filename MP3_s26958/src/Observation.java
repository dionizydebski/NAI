import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Observation {
    private String decision_attribute;
    private double[] attributte;

    public Observation(String line) {

        String[] temp = line.split(";");

        this.decision_attribute = temp[temp.length-1];

        this.attributte = new double[temp.length - 1];
        for(int i = 0; i < attributte.length; i++){
            attributte[i] = Double.parseDouble(temp[i]);
        }
    }

    public String getDecision_attribute() {
        return decision_attribute;
    }
    public double[] getAttributte() {
        return attributte;
    }
    public static ArrayList<Observation> makeArr(String set_name){

        ArrayList<Observation> observations_arr = new ArrayList<>();

        try {
            File file = new File(set_name);

            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                observations_arr.add(new Observation(line));
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku: " + e.getMessage());
        }
        return observations_arr;
    }
    public int getLength(){
        return attributte.length;
    }
}