import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    public static void main(String[] args) {
        ArrayList<Swimmer> swimmers = FileManager.getMembers();
        ArrayList<Trainer> trainers = FileManager.getTrainers();
        FileManager.getBackups(swimmers,trainers);
        //Trainer.assignTrainers(trainers,swimmers);
        Club.calculateYearlyCharge(swimmers);
        menu(swimmers,trainers);
    }
    public static void menu(ArrayList<Swimmer> swimmers, ArrayList<Trainer> trainers) {
        Scanner scanner = new Scanner(System.in);

        int choice = 0;
        do {
            try {
                int subchoice1 = 0;
                int subchoice2 = 0;
                int subchoice3 = 0;
                System.out.println();
                System.out.println("**********************");
                System.out.println("Svømmeklubben Delfinen");
                System.out.println("**********************");
                System.out.println();
                System.out.println("1. Medlemsadministration");
                System.out.println("2. Trænermenu");
                System.out.println("3. Kasserermenu");
                System.out.println("4. Backup funktion");
                System.out.println("5. Afslut program\n");
                System.out.print("Indtast venligst et tal mellem 1-4 \n");
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> {
                        while (subchoice1 != 5) {
                            System.out.println();
                            System.out.println("Medlemsadministration");
                            System.out.println("1 Opret medlem");
                            System.out.println("2 Opdater stamoplysninger");
                            System.out.println("3 Fjern medlem");
                            System.out.println("4 Generer oversigt og kontingent over nuværende medlemmer");
                            System.out.println("5 Gå tilbage");
                            System.out.println();

                            subchoice1 = scanner.nextInt();
                            scanner.nextLine();
                            switch (subchoice1) {
                                case 1 -> {
                                    Swimmer newSwimmer = Club.createSwimmer(scanner);
                                    swimmers.add(newSwimmer);
                                }
                                case 2 ->
                                        Club.editSwimmer(swimmers, scanner);

                                case 3 ->
                                        Club.removeSwimmer(swimmers, scanner);

                                case 4 -> {
                                    Club.showSwimmers(swimmers);
                                    Club.calculateYearlyCharge(swimmers);
                                }
                                case 5 ->
                                        System.out.println("Går tilbage til main menu.");

                                default -> System.out.println("Fejl: Forkert input. Prøv igen.");
                            }
                        }
                    }
                    case 2 -> {
                        while (subchoice2 != 7) {
                            System.out.println();
                            System.out.println("Trænermenu");
                            System.out.println("1 Tilføj svømmere til stævne");
                            System.out.println("2 Tilføj eller fjern træner fra svømmer");
                            System.out.println("3 Registrer konkurrencedeltagere");
                            System.out.println("4 Registrer disciplin rekorder");
                            System.out.println("5 Opret og slet træner");
                            System.out.println("6 Generer top 5 oversigt");
                            System.out.println("7 Gå tilbage");
                            System.out.println();

                            subchoice2 = scanner.nextInt();
                            scanner.nextLine();
                            switch (subchoice2) {
                                case 1 -> Club.registerCompetition(swimmers, scanner);
                                case 2 -> Trainer.assignTrainers(trainers, swimmers, scanner);

                                case 3 -> Club.registerCompetitionSwimmer(swimmers, scanner);
                                case 4 -> Club.updateCompetetionRecords(swimmers, scanner);
                                case 5 -> {
                                    try {
                                        System.out.println();
                                        System.out.println("Vælg venligst hvad du ønsker at redigere:");
                                        System.out.println("1. Opret træner");
                                        System.out.println("2. Slet træner");
                                        System.out.println("3. Gå tilbage");
                                        System.out.println();

                                        int subchoice4 = scanner.nextInt();
                                        scanner.nextLine();
                                        switch (subchoice4) {
                                            case 1 -> trainers.add(Club.createTrainer(scanner));
                                            case 2 -> {Club.removeTrainer(scanner, trainers, swimmers);
                                                Trainer.assignTrainers(trainers,swimmers);
                                            }
                                            case 3 -> System.out.println("Går tilbage til main menu.");

                                            default -> System.out.println("Fejl: Forkert input. Prøv igen.");
                                        }

                                } catch(Exception e){
                                    System.out.println("En fejl er opstået: " + e.getMessage());
                                    e.printStackTrace();
                                }
                            }
                                case 6 ->
                                        Club.displayTopFive(swimmers,scanner);

                                case 7 ->
                                        System.out.println("Går tilbage til main menu.");

                                default -> System.out.println("Fejl: Forkert input. Prøv igen.");
                            }
                        }
                    }
                    case 3 -> {
                        while (subchoice3 != 3) {
                            System.out.println();
                            System.out.println("Kasserermenu");
                            System.out.println("1 Vis medlemmer i restance");
                            System.out.println("2 Modtag kontigentbetaling");
                            System.out.println("3 Gå tilbage");
                            System.out.println();

                            subchoice3 = scanner.nextInt();
                            scanner.nextLine();
                            switch (subchoice3) {
                                case 1 -> {
                                    for(Swimmer s: swimmers){
                                        if(s.getOwedAmount()>0)
                                            System.out.println(s);
                                    }
                                }
                                case 2 ->
                                        Club.updatePayment(swimmers, scanner);
                                case 3 ->
                                        System.out.println("Går tilbage til main menu.");
                                default -> System.out.println("Fejl: Forkert input. Prøv igen.");
                            }
                        }
                    }
                    case 4 -> {
                        FileManager.saveMembers(swimmers);
                        FileManager.saveTrainers(trainers);
                        System.out.println("Medlemmer og trænere er gemt i systemet.");
                    }
                }
            } catch (Exception e) {
                System.out.println("En fejl er opstået " + e.getMessage());
                scanner.nextLine();
            }
        } while (choice != 5);
        scanner.close();
        FileManager.saveTrainers(trainers);
        FileManager.saveMembers(swimmers);
        FileManager.clearBackups();
    }
}