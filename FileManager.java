import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    public static void main(String[] randyDandyO){ //For testing
        ArrayList<Trainer> list = getTrainers();
        ArrayList<Swimmer> alist = getMembers();

        //alist.add(Club.createSwimmer(new Scanner(System.in)));

        System.out.println(alist.get(0));

        saveMembers(alist);
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
                boolean competition = Boolean.parseBoolean(bits[4]);
                short trainerIndex = Short.parseShort(bits[5]);
                LocalDate lastChargeDate = LocalDate.parse(bits[6]);
                float butterflyPracticeRecord = Float.parseFloat(bits[7]);
                float backstrokePracticeRecord = Float.parseFloat(bits[8]);
                float freestylePracticeRecord = Float.parseFloat(bits[9]);

                ArrayList<SwimMeet> meetList = new ArrayList<>();
                line = in.readLine();

                while(!line.equals(";")){
                    String[] bits2 = line.split(",");
                    String meetName = bits2[0];
                    LocalDate date = LocalDate.parse(bits2[1]);
                    Discipline discipline = Discipline.valueOf(bits2[2]);
                    float time = Float.parseFloat(bits2[3]);
                    byte placement = Byte.parseByte(bits2[4]);

                    meetList.add(new SwimMeet(meetName,date,discipline,time,placement));
                    line=in.readLine();
                }

                list.add(new Swimmer(active,name,birthday,amount,competition,trainerIndex,new Results(meetList,butterflyPracticeRecord,backstrokePracticeRecord,freestylePracticeRecord),lastChargeDate));

                line = in.readLine();
            }
            file.close();

        }catch(IOException E){ //FileNotFoundException is a subset of IOException; this catches both.
            System.out.println("Fejl under indlæsning af medlemmer.");
            E.printStackTrace();
        }
        return list;
    } //getMembers
    public static void saveMembers(ArrayList<Swimmer> list){
        try {
            FileWriter file = new FileWriter("Members.txt");
            PrintWriter out = new PrintWriter(file);

            for (Swimmer s : list) {
                out.println(s.fileOutput());
                for(SwimMeet sm: s.results.list) {
                    out.println(sm.meetName+","+sm.date+","+sm.discipline+","+sm.time+","+sm.placement);
                }
                out.println(";");
            }

            out.close();
            file.close();
        }catch(IOException E){
            System.out.println("Fejl: data kunne ikke gemmes.");
            E.printStackTrace();
        }
    }
    public static ArrayList<Trainer> getTrainers(){
        ArrayList<Trainer> list = new ArrayList<>();

        try{
            FileReader file = new FileReader("Trainers.txt");
            BufferedReader in = new BufferedReader(file);
            String line = in.readLine();

            while(line!=null){
                String[] bits = line.split(",");
                String name = bits[0];

                list.add(new Trainer(name));
                line = in.readLine();
            }
            in.close();
            file.close();
        }catch(IOException E){
            System.out.println("Error: unable to read trainer data.");
            E.printStackTrace();
        }
        return list;
    }
    public static void saveTrainers(ArrayList<Trainer> list){
        try {
            FileWriter file = new FileWriter("Trainers.txt");
            PrintWriter out = new PrintWriter(file);

            for (Trainer t : list) {
                out.println(t.getName());
            }

            out.close();
            file.close();
        }catch(IOException E){
            System.out.println("Error: unable to save trainer data.");
            E.printStackTrace();
        }
    }
    public static void saveBackup(Swimmer s){
        try {
            FileWriter file = new FileWriter("Backup.txt",true);
            BufferedWriter out = new BufferedWriter(file);

            out.write("s,"+s.fileOutput());
            out.newLine();

            for(SwimMeet sm: s.results.list) {
                out.write(sm.meetName+","+sm.date+","+sm.discipline+","+sm.time+","+sm.placement);
                out.newLine();
            }

            out.write(";");
            out.newLine();

            out.close();
            file.close();
        }catch(IOException E){
            E.printStackTrace();
        }
    }
    public static void saveBackup (Trainer t){
        try {
            FileWriter file = new FileWriter("Backup.txt",true);
            BufferedWriter out = new BufferedWriter(file);

            out.write("t,"+t.getName());
            out.newLine();

            out.close();
            file.close();
        }catch(IOException E){
            E.printStackTrace();
        }
    }
    public static void getBackups (ArrayList<Swimmer> swimmerList,ArrayList<Trainer> trainerList){
        try {
            FileReader file = new FileReader("Backup.txt");
            BufferedReader in = new BufferedReader(file);
            String line = in.readLine();

            while(line!=null){
                String[] bits = line.split(",");
                if(bits[0].equals("s")){
                    boolean active = Boolean.parseBoolean(bits[1]);
                    String name = bits[2];
                    LocalDate birthday = LocalDate.parse(bits[3]);
                    short amount = Short.parseShort(bits[4]);
                    boolean competition = Boolean.parseBoolean(bits[5]);
                    short trainerIndex = Short.parseShort(bits[6]);
                    LocalDate lastChargeDate = LocalDate.parse(bits[7]);
                    float butterflyPracticeRecord = Float.parseFloat(bits[8]);
                    float backstrokePracticeRecord = Float.parseFloat(bits[9]);
                    float freestylePracticeRecord = Float.parseFloat(bits[10]);

                    ArrayList<SwimMeet> meetList = new ArrayList<>();
                    line = in.readLine();

                    while(!line.equals(";")){
                        String[] bits2 = line.split(",");
                        String meetName = bits2[0];
                        LocalDate date = LocalDate.parse(bits2[1]);
                        Discipline discipline = Discipline.valueOf(bits2[2]);
                        float time = Float.parseFloat(bits2[3]);
                        byte placement = Byte.parseByte(bits2[4]);

                        meetList.add(new SwimMeet(meetName,date,discipline,time,placement));
                        line=in.readLine();
                    }

                    swimmerList.add(new Swimmer(active,name,birthday,amount,competition,trainerIndex,new Results(meetList,butterflyPracticeRecord,backstrokePracticeRecord,freestylePracticeRecord),lastChargeDate));

                    line = in.readLine();
                }
                else if(bits[0].equals("t")){
                    trainerList.add(new Trainer(bits[1]));
                    line=in.readLine();
                }
                else{
                    System.out.println("Fejl under indlæsning fra backupfil.");
                    line=in.readLine();
                }
            }

        }catch(IOException E){
            E.printStackTrace();
        }
    }
    public static void clearBackups(){
        try {
            FileWriter file = new FileWriter("Backup.txt");
            PrintWriter out = new PrintWriter(file);
            out.println();
            out.close();
            file.close();
        } catch(IOException E){
            E.printStackTrace();
        }
    }
}
