import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Results {
    private byte butterflyPlacement;
    private byte backstrokePlacement;
    private byte freestylePlacement;
    private float butterflyRecord;
    private float backstrokeRecord;
    private float freestyleRecord;
    private float backstrokePracticeRecord;
    private float butterflyPracticeRecord;
    private float freestylePracticeRecord;
    static ArrayList<SwimMeet> list;
    float getButterflyRecord(){
        return butterflyRecord;
    }
    float getBackstrokeRecord(){
        return backstrokeRecord;
    }
    float getFreestyleRecord(){
        return freestyleRecord;
    }

    float getBackstrokePracticeRecord() {
        return backstrokePracticeRecord;
    }

    float getButterflyPracticeRecord() {
        return butterflyPracticeRecord;
    }

    float getFreestylePracticeRecord() {
        return freestylePracticeRecord;
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
                System.out.println("Skriv tid for " + discipline + " (i sekunder):");
                float newTime = scanner.nextFloat();
                scanner.nextLine();

                System.out.println("Skriv placering for " + discipline + ":");
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
                        System.out.println("Ugyldig disciplin.");
                        continue;
                }

                System.out.println("Rekorder opdateret");
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
    void updatePracticeRecord(String discipline, Scanner scanner) {
        boolean isValidInput = false;
        while (!isValidInput) {
            try {
                System.out.println("Skriv tid for " + discipline + " (i sekunder):");
                float newTime = scanner.nextFloat();
                scanner.nextLine();

                switch (discipline.toLowerCase()) {
                    case "butterfly":
                        butterflyPracticeRecord = newTime;
                        break;
                    case "backstroke":
                        backstrokePracticeRecord = newTime;
                        break;
                    case "freestyle":
                        freestylePracticeRecord = newTime;
                        break;
                    default:
                        System.out.println("Ugyldig disciplin.");
                        continue;
                }

                System.out.println("Rekorder opdateret");
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
        System.out.println("Opdater rekorder for svømmere: ");
        System.out.println("1. Opdater konkurrence rekorder og placering");
        System.out.println("2. Opdater trænings rekorder ");
        System.out.println("Skriv et nummer på hvad du vil opdatere:");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("1. Butterfly");
                System.out.println("2. Backstroke");
                System.out.println("3. Freestyle");
                System.out.println("Skriv et nummer på hvad du vil opdatere:");

                scanner.nextLine();
                int subChoice = scanner.nextInt();
                switch (subChoice) {
                    case 1:
                        updateRecord("Butterfly", scanner);
                        break;
                    case 2:
                        updateRecord("Backstroke", scanner);
                        break;
                    case 3:
                        updateRecord("Freestyle", scanner);
                        break;
                    default:
                        System.out.println("Ugyldig valg. Vælg et tal mellem 1 og 3.");
                }
            case 2:
                System.out.println("1. Butterfly");
                System.out.println("2. Backstroke");
                System.out.println("3. Freestyle");
                System.out.println("Skriv et nummer på hvad du vil opdatere:");
                int subChoice2 = scanner.nextInt();
                switch (subChoice2) {
                    case 1:
                        updatePracticeRecord("Butterfly", scanner);
                        break;
                    case 2:
                        updatePracticeRecord("Backstroke", scanner);
                        break;
                    case 3:
                        updatePracticeRecord("Freestyle",scanner);
                        break;
                    default:
                        System.out.println("Ugyldig valg. Vælg et tal mellem 1 og 3.");
                }
        }
    }
    static void addSwimMeet(Scanner scanner) {
        try {
            System.out.println("Skriv navnet på konkurrencen:");
            String meetName = scanner.nextLine();

            System.out.println("Skriv dato for konkurrencen (format: ÅÅÅÅ-MM-DD):");
            LocalDate date = LocalDate.parse(scanner.nextLine());

            System.out.println("Vælg disciplin (1. Butterfly, 2. Backstroke, 3. Freestyle):");
            int disciplineChoice = scanner.nextInt();
            Discipline discipline;

            switch (disciplineChoice) {
                case 1:
                    discipline = Discipline.Butterfly;
                    break;
                case 2:
                    discipline = Discipline.Backstroke;
                    break;
                case 3:
                    discipline = Discipline.Freestyle;
                    break;
                default:
                    System.out.println("Ugyldig valg. Vælg et tal mellem 1-3.");
                    return;
            }

            System.out.println("Skriv tid for konkurrencen (i sekunder):");
            float time = scanner.nextFloat();

            System.out.println("Skriv placering for konkurrencen:");
            byte placement = scanner.nextByte();

            list.add(new SwimMeet(meetName, date, discipline, time, placement));

            System.out.println("Konkurrence tilføjet!");
        } catch (InputMismatchException e) {
            System.out.println("Ugyldig input.");
            scanner.nextLine();
        }catch (DateTimeParseException e){
            System.out.println("Ugyldig dato format, venligst skriv som beskrevet");
        }
    }
}

enum Discipline {Butterfly, Backstroke, Freestyle}

//Klasse for stævner
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