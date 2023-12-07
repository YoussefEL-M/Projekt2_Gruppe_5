import java.util.ArrayList;
import java.util.InputMismatchException;
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

        try {

            System.out.println("Tilføj træner til svømmer:");

            System.out.println("Søg efter medlem eller tryk enter for at vise alle medlemmer.");
            String searchTerm = scanner.nextLine();

            System.out.println("Medlemsliste:");
            for (Swimmer s : swimmerList) {
                if (s.getName().toLowerCase().contains(searchTerm.toLowerCase()))
                    System.out.println(s);
            }
            System.out.println();
            System.out.println("Vælg det medlem, du ønsker at tilføje træner for.");
            Swimmer swimmerToAssign = swimmerList.get(scanner.nextInt());
            scanner.nextLine();

            if (swimmerToAssign != null) {

                System.out.println("Tilføj træner til medlem: " + swimmerToAssign.getName());
                System.out.println("Tilgængelige trænere:");

                for (int i = 0; i < trainerList.size(); i++) {
                    System.out.println((i) + ". " + trainerList.get(i).getName());
                }

                System.out.println("Vælg træner for " + swimmerToAssign.getName() + " (-1 for ingen træner): ");
                int trainerIndex = scanner.nextInt();
                scanner.nextLine();


                if (trainerIndex == -1) {
                    swimmerToAssign.setTrainer(null);
                    swimmerToAssign.setTrainerIndex((short) trainerIndex);
                    System.out.println("Træner fjernet fra medlem: " + swimmerToAssign.getName());
                } else if (trainerIndex + 1 <= trainerList.size()) {
                    swimmerToAssign.setTrainer(trainerList.get(trainerIndex));
                    swimmerToAssign.setTrainerIndex((short) trainerIndex);
                    System.out.println("Træner " + trainerList.get(trainerIndex).getName() + " tilføjet til medlem: " + swimmerToAssign.getName());
                } else {
                    System.out.println("Ikke-eksisterende træner. Ingen ændringer ved " + swimmerToAssign.getName());
                }

            } else {
                System.out.println("Fejl: medlem blev ikke fundet. ");
            }
        }catch(InputMismatchException E){
            System.out.println("Fejl: Ugyldigt input.");
        }

    }
    public static void assignTrainers(ArrayList<Trainer> trainerList, ArrayList<Swimmer> swimmerList){
        for(Swimmer s: swimmerList){
            if(s.trainerIndex>-1){
                s.setTrainer(trainerList.get(s.trainerIndex));
            }
        }
    }
    public String toString() {
        return name;
    }
}
