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

    //Bruges ikke længere.
    /*public static Trainer getTrainerByIndex(ArrayList<Trainer> trainerList, int index) {
        if (index >= 0 && index < trainerList.size()) {
            return trainerList.get(index);
        } else {
            return null;
        }
    }
*/
    //Tildel træner til svømmer
    public static void assignTrainers(ArrayList<Trainer> trainerList, ArrayList<Swimmer> swimmerList, Scanner scanner){

        try {
            Swimmer selectedSwimmer;
            System.out.println("Tilføj eller fjern træner til/fra medlem:");
            System.out.println();
            System.out.println("Søg efter medlem eller tast enter for at vise alle medlemmer.");
            String searchTerm = scanner.nextLine();
            System.out.println("Medlemsliste:");
            System.out.println();

            for(Swimmer s:swimmerList){
                if(s.getName().toLowerCase().contains(searchTerm.toLowerCase()))
                    System.out.println(s.indexNo+". "+s.getName());
            }


            System.out.println("Vælg hvilket medlem, du ønsker at tildele en træner til:");
            int swimmerIndex = scanner.nextInt();
            scanner.nextLine();

            if (swimmerIndex >= 0 && swimmerIndex < swimmerList.size()) {
                selectedSwimmer = swimmerList.get(swimmerIndex);
            }
            else{
                System.out.println("Intet medlem fundet med dette indekstal.");
                return;
            }
            System.out.println();
            System.out.println("Trænerliste:");
            for(Trainer t: trainerList){
                System.out.println(trainerList.indexOf(t)+". "+t.getName());
            }
            System.out.println("Vælg den træner, du ønsker at tildele medlemmet. Tast -1 for ingen træner.");
            int trainerIndex = scanner.nextInt();
            scanner.nextLine();

            if (trainerIndex == -1) {
                selectedSwimmer.setTrainer(null);
                selectedSwimmer.setTrainerIndex((short) trainerIndex);
                System.out.println("Træner fjernet fra medlem " + selectedSwimmer.getName());
            } else if (trainerIndex < trainerList.size()) {
                selectedSwimmer.setTrainer(trainerList.get(trainerIndex));
                selectedSwimmer.setTrainerIndex((short) trainerIndex);
                System.out.println("Træner " + trainerList.get(trainerIndex).getName() + " tilføjet til medlem " + selectedSwimmer.getName());
            } else {
                System.out.println("Ikke-eksisterende træner. Ingen ændringer ved " + selectedSwimmer.getName());
            }

        }catch(InputMismatchException E){
            System.out.println("Fejl: Ugyldigt input.");
        }catch(IndexOutOfBoundsException e){
            System.out.println("Ugyldigt indeks");
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
