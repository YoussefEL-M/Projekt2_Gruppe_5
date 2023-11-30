import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Results {
    private byte butterflyPlacement;
    private byte backstrokePlacement;
    private byte freestylePlacement;
    private float butterflyRecord;
    private float backstrokeRecord;
    private float freestyleRecord;
    ArrayList<SwimMeet> list;
    float getButterflyRecord(){
        return butterflyRecord;
    }
    float getBackstrokeRecord(){
        return backstrokeRecord;
    }
    float getFreestyleRecord(){
        return freestyleRecord;
    }
    byte getButterflyPlacement(){
        return butterflyPlacement;
    }
    byte getBackstrokePlacement(){
        return backstrokePlacement;
    }
    byte getFreestylePlacement(){
        return freestylePlacement;
    }
    Results(ArrayList<SwimMeet> list){
        this.list=list;
        ArrayList<Float> sortListA = new ArrayList<>();
        ArrayList<Float> sortListB = new ArrayList<>();
        ArrayList<Float> sortListC = new ArrayList<>();

        //Uddeler tiderne efter disciplin og sorterer dem.

        for(SwimMeet sm : list) {
            switch (sm.discipline) {
                case Butterfly -> sortListA.add(sm.time);
                case Backstroke -> sortListB.add(sm.time);
                case Freestyle -> sortListC.add(sm.time);
            }
        }
        sortListA.sort(null);
        sortListB.sort(null);
        sortListC.sort(null);

        if (!sortListA.isEmpty()) butterflyRecord = sortListA.get(0);
        if (!sortListB.isEmpty()) backstrokeRecord = sortListB.get(0);
        if (!sortListC.isEmpty()) freestyleRecord = sortListC.get(0);

        ArrayList<Byte> sortListD = new ArrayList<>();
        ArrayList<Byte> sortListE = new ArrayList<>();
        ArrayList<Byte> sortListF = new ArrayList<>();

        for (SwimMeet sm : list) {
            switch (sm.discipline) {
                case Butterfly -> sortListD.add(sm.placement);
                case Backstroke -> sortListE.add(sm.placement);
                case Freestyle -> sortListF.add(sm.placement);
            }
        }

        sortListD.sort(null);
        sortListE.sort(null);
        sortListF.sort(null);

        if (!sortListD.isEmpty()) butterflyPlacement = sortListD.get(0);
        if (!sortListE.isEmpty()) backstrokePlacement = sortListE.get(0);
        if (!sortListF.isEmpty()) freestylePlacement = sortListF.get(0);
    }

    void updateRecord(String discipline, Scanner scanner) {
        boolean isValidInput = false;
        while (!isValidInput) {
            try {
                System.out.println("Enter new time for " + discipline + " (in seconds):");
                float newTime = scanner.nextFloat();
                scanner.nextLine();

                System.out.println("Enter new placement for " + discipline + ":");
                byte newPlacement = scanner.nextByte();
                scanner.nextLine();

                switch (discipline.toLowerCase()) {
                    case "butterfly":
                        butterflyRecord = newTime;
                        butterflyPlacement = newPlacement;
                        break;
                    case "backstroke":
                        backstrokeRecord = newTime;
                        backstrokePlacement = newPlacement;
                        break;
                    case "freestyle":
                        freestyleRecord = newTime;
                        freestylePlacement = newPlacement;
                        break;
                    default:
                        System.out.println("Invalid discipline.");
                        continue;
                }

                System.out.println("Records updated successfully.");
                isValidInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Ugyldig input. Skriv tid i sekunder");
                scanner.nextLine();

            } catch (NumberFormatException e) {
                System.out.println("Ugyldig tal format.");
                scanner.nextLine();
            }
        }

    }
    void updateRecords(Scanner scanner) {
        System.out.println("Update Records for Swimmer: ");
        System.out.println("1. Butterfly");
        System.out.println("2. Backstroke");
        System.out.println("3. Freestyle");
        System.out.println("Enter the number corresponding to the discipline to update:");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                updateRecord("Butterfly", scanner);
                break;
            case "2":
                updateRecord("Backstroke", scanner);
                break;
            case "3":
                updateRecord("Freestyle", scanner);
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 3.");
        }
    }
}


enum Discipline {Butterfly, Backstroke, Freestyle}

class SwimMeet {
    String meetName;
    LocalDate date;
    Discipline discipline;
    float time;
    byte placement;

    SwimMeet(String meetName, LocalDate date, Discipline discipline, float time, byte placement) {
        this.meetName = meetName;
        this.date = date;
        this.discipline = discipline;
        this.time = time;
        this.placement = placement;
    }
}