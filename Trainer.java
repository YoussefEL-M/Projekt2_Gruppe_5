import java.util.ArrayList;
import java.util.Scanner;

public class Trainer {
    String name;
    Trainer(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }

    public static void assignTrainers(ArrayList<Trainer> trainerList, ArrayList<Swimmer> swimmerList, Scanner scanner){

        System.out.println("Tilføj træner til svømmer:");

        for(Swimmer s: swimmerList){
            System.out.println("Tilføj træner til svømmer: "+ s.getName());
            System.out.println("Available trainers");

            for(int i=0; i < trainerList.size(); i++) {
                System.out.println((i+1) + "." + trainerList.get(i).getName());
            }

            System.out.println("Vælg træner index for " + s.getName() + " (-1 for ingen træner): ");
            int trainerIndex = scanner.nextInt();
            scanner.nextLine();


            if(s.trainerIndex>=-1 && trainerIndex < trainerList.size()){
                if (trainerIndex ==1 ) {
                    s.setTrainer(null);

                } else {
                    s.setTrainer(trainerList.get(s.trainerIndex));
                }
                s.setTrainerIndex((short) trainerIndex);
                System.out.println("Træner er tilføjet!");
            } else {
                System.out.println("Ikke-eksisterende træner index. Ingen ændringer ved "+ s.getName());
            }
        }
    }
}
