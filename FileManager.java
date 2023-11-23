import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
