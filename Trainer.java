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

    public static Trainer getTrainerByIndex(ArrayList<Trainer> trainerList, int index) {
        if (index >= 0 && index < trainerList.size()) {
            return trainerList.get(index);
        } else {
            return null;
        }
    }

    public static void assignTrainers(ArrayList<Trainer> trainerList, ArrayList<Swimmer> swimmerList, Scanner scanner){

        try {
            System.out.println("Tilføj træner til svømmer:");

            boolean memberFound = false;

            System.out.println("Medlemsliste:");

            for (int i = 0; i < trainerList.size(); i++) {
                System.out.println("Indeks: " + i + " - Træner: " + trainerList.get(i).getName());
            }

            System.out.println("Indtast indekstal på træner, du ønsker tildele svømmer til:");
            int swimmerIndex = scanner.nextInt();
            scanner.nextLine();

            if (swimmerIndex >= 0 && swimmerIndex < trainerList.size()) {
                Swimmer selectedSwimmer = swimmerList.get(swimmerIndex);{
                    System.out.println("Medlemsliste:");
                    System.out.println(selectedSwimmer);
                    memberFound = true;
                }
            }
            if (!memberFound) {
                System.out.println("Intet medlem fundet under søgningen");
                return;
            }
            System.out.println();
            System.out.println("Vælg det medlem, du ønsker at tilføje træner for. Indtast index-tallet for medlemmet.");
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
        }catch(IndexOutOfBoundsException e){
            System.out.println("Ugyldigt index");
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
