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

        System.out.println("Søg efter medlem eller tryk enter for at vise alle medlemmer.");
        String searchTerm = scanner.nextLine();

        System.out.println("Medlemsliste:");
        for (Swimmer s : swimmerList) {
            if(s.getName().toLowerCase().contains(searchTerm.toLowerCase()))
                System.out.println(s);
        }
        System.out.println();
        System.out.println("Indtast indekstal på det medlem, du ønsker at tilføje træner for.");
        Swimmer swimmerToAssign = swimmerList.get(scanner.nextInt());
        scanner.nextLine();

        if (swimmerToAssign !=null) {

            for(Swimmer s: swimmerList) {
                System.out.println("Tilføj træner til svømmer: " + s.getName());
                System.out.println("Available trainers");

                for (int i = 0; i < trainerList.size(); i++) {
                    System.out.println((i + 1) + "." + trainerList.get(i).getName());
                }

                System.out.println("Vælg træner index for " + s.getName() + " (-1 for ingen træner): ");
                int trainerIndex = scanner.nextInt();
                scanner.nextLine();


                if (s.trainerIndex >= -1 && trainerIndex < trainerList.size()) {
                    if (trainerIndex == 1) {
                        s.setTrainer(null);

                    } else {
                        s.setTrainer(trainerList.get(s.trainerIndex));
                    }
                    s.setTrainerIndex((short) trainerIndex);
                    System.out.println("Træner med index "+trainerIndex+" tilføjet til medlem: " + swimmerToAssign.getName());

                } else {
                    System.out.println("Ikke-eksisterende træner index. Ingen ændringer ved " + s.getName());
                }
            }
        }else {
            System.out.println("Fejl, medlem blev ikke fundet. ");
        }

    }
    public static void assignTrainers(ArrayList<Trainer> trainerList, ArrayList<Swimmer> swimmerList){
        for(Swimmer s: swimmerList){
            if(s.trainerIndex>-1){
                s.setTrainer(trainerList.get(s.trainerIndex));
            }
        }
    }
}
