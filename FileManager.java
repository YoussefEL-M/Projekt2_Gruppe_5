import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static java.lang.System.exit;

public class FileManager {
    public static void main(String[] randyDandyO){ //For testing
    }
    public static ArrayList<Swimmer> getMembers(){
        ArrayList<Swimmer> list = new ArrayList<>();
        try {
            FileReader file = new FileReader("Members.txt");
            BufferedReader in = new BufferedReader(file);
            String line = in.readLine();

            while(line!=null){
                String[] bits = line.split(",");
                boolean active = Boolean.parseBoolean(bits[0]);
                String name = bits[1];
                LocalDate birthday = LocalDate.parse(bits[2]);
                short amount = Short.parseShort(bits[3]);
                float butterfly = Float.parseFloat(bits[4]);
                float breaststroke = Float.parseFloat(bits[5]);
                float freestyle = Float.parseFloat(bits[6]);
                boolean competition = Boolean.parseBoolean(bits[7]);
                Trainer trainer = new Trainer(bits[8]);

                list.add(new Swimmer(active,name,birthday,amount,butterfly,breaststroke,freestyle,competition, trainer));

                line = in.readLine();
            }

        }catch(IOException E){ //FileNotFoundException is a subset of IOException; this catches both.
            System.out.println("Error: unable to read members from file.");
            E.printStackTrace();
        }
        return list;
    }
    //public static ArrayList<Trainer> getTrainers(){}
}
