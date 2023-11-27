import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    public static void main(String[] args) throws IOException {
        ArrayList<Swimmer> swimmers = new ArrayList<>();
        ArrayList<Trainer> trainers = new ArrayList<>();
        Trainer sharedTrainer = new Trainer("Ingen træner");
        menu(swimmers,sharedTrainer);
    }

    public static void menu(ArrayList<Swimmer> swimmers, Trainer sharedTrainer) {
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
                            System.out.println("2 Opdater stamoplysninger for medlem");
                            System.out.println("3 Generer oversigt over nuværende medlemmer");
                            System.out.println("4 Gå tilbage");
                            System.out.println();

                            subchoice1 = scanner.nextInt();
                            scanner.nextLine();
                            switch (subchoice1) {
                                case 1 -> {
                                    Swimmer newSwimmer = createSwimmer(scanner,sharedTrainer);
                                    swimmers.add(newSwimmer);
                                }
                                case 2 -> {
                                    editSwimmer(swimmers, scanner);
                                }
                                case 3 -> {
                                    removeSwimmer(swimmers, scanner);
                                }
                                case 4 -> {
                                    showSwimmers(swimmers);
                                }
                                case 5 -> {
                                    System.out.println("Går tilbage til main menu.");
                                }
                                default -> System.out.println("Fejl: Forkert input. Prøv igen.");
                            }
                        }
                    }
                    case 2 -> {
                        while (subchoice2 != 5) {
                            System.out.println();
                            System.out.println("Trænermenu");
                            System.out.println("1 Tilføj svømmere til hold");
                            System.out.println("2 Registrer disciplin rekorder");
                            System.out.println("3 Registrer konkurrencedeltagere");
                            System.out.println("4 Generer top 5 oversigt");
                            System.out.println("5 Gå tilbage");
                            System.out.println();
                            subchoice2 = scanner.nextInt();
                            scanner.nextLine();

                            switch (subchoice2) {
                                case 1 -> {
                                    System.out.println("Tilføj svømmere til hold");
                                }
                                case 2 -> {
                                    registerDiscipline(swimmers,scanner);
                                }
                                case 3 -> {
                                    registerCompetitionSwimmer(swimmers,scanner);
                                }
                                case 4 -> {
                                // mangler liste.
                                }
                                case 5 -> {
                                    System.out.println("Går tilbage til main menu.");
                                }
                                default -> System.out.println("Fejl: Forkert input. Prøv igen.");
                            }
                        }
                    }
                    case 3 -> {
                        while (subchoice3 != 3) {
                            System.out.println();
                            System.out.println("Kasserermenu");
                            System.out.println("1 Vis medlemmer i restance");
                            System.out.println("2 Opdater kontigentbetaling");
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
                                case 2 -> {
                                    updatePayment(swimmers, scanner);
                                }
                                default -> System.out.println("Fejl: Forkert input. Prøv igen.");
                            }
                        }
                    }
                    case 4 -> {
                        FileManager.saveMembers(swimmers);
                        System.out.println("Medlemmer gemt i systemet.");
                    }
                }
            } catch (Exception e) {
                System.out.println("En fejl er opstået " + e.getMessage());
                scanner.nextLine();
            }
        } while (choice != 5);
        scanner.close();
    }
    private static Swimmer createSwimmer(Scanner scanner, Trainer sharedTrainer) {
        while (true) {
            try {
                System.out.println("Du har valgt Opret medlem");
                System.out.println("Indtast navn:");
                String name = scanner.nextLine();
                System.out.println("Indtast fødselsdato i format YYYY-MM-DD:");
                LocalDate birthdate = LocalDate.parse(scanner.nextLine());
                System.out.println("Er det et aktivt medlem? Indtast Y/N");
                String valg = scanner.nextLine();
                boolean activeMember;
                short owedAmount = 0;
                float butterflyRecord = 0, backstrokeRecord = 0, freestyleRecord = 0;
                boolean competitionSwimmer = false;

                if (valg.equalsIgnoreCase("y")) {
                    activeMember = true;
                    System.out.println("Ny svømmer oprettet.");
                    return new Swimmer(activeMember, name, birthdate, owedAmount, butterflyRecord, backstrokeRecord, freestyleRecord, competitionSwimmer, (short) -1, (byte) 0, (byte) 0, (byte) 0);
                } else if (valg.equalsIgnoreCase("n")) {
                    activeMember = false;
                    System.out.println("Ny svømmer oprettet.");
                    return new Swimmer(activeMember, name, birthdate, owedAmount, butterflyRecord, backstrokeRecord, freestyleRecord, competitionSwimmer, (short) -1, (byte) 0, (byte) 0, (byte) 0);
                } else {
                    System.out.println("Valg ugyldigt, prøv igen.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("En fejl er opstået! " + e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("Ugyldig dato format, skriv i formatet YYYY-MM-DD " + e.getMessage());
            }
        }
    }

    static void showSwimmers(ArrayList<Swimmer> list) {
        try {
            System.out.println("Medlemsliste:");
            for (Swimmer s : list) {
                System.out.println(s);
            }
        } catch (Exception e) {
            System.out.println("En fejl er opstået: " + e.getMessage());
            e.printStackTrace();
        }
    }

    static void editSwimmer(ArrayList<Swimmer> list, Scanner scanner) {
        try {
            System.out.println("Medlemsliste:");
            for (Swimmer s : list) {
                System.out.println(s);
            }
            System.out.println("Indtast navn på svømmer, du ønsker at redigere");
            String searchName = scanner.nextLine();
            Swimmer swimmerToEdit = null;


            for (Swimmer s : list) {
                if (s.getName().equalsIgnoreCase(searchName)) {
                    swimmerToEdit = s; // Assign the found swimmer to swimmerToEdit.
                    break;
                }
            }

            if (swimmerToEdit != null) {
                System.out.println("Svømmer stamdata:");
                System.out.println(swimmerToEdit);

                System.out.println("Vil du ændre navn? Indtast nyt navn eller tryk Enter for at bevare det nuværende:");
                String newName = scanner.nextLine();
                if (!newName.isEmpty()) {
                    swimmerToEdit.setName(newName);
                }

                System.out.println("Vil du ændre fødselsdato? Indtast nyt datoformat (YYYY-MM-DD) eller tryk Enter for at bevare det nuværende:");
                String newBirthdate = scanner.nextLine();
                if (!newBirthdate.isEmpty()) {
                    swimmerToEdit.setBirthdate(LocalDate.parse(newBirthdate));
                }

                System.out.println("Vil du ændre aktivt medlemskab? Indtast Y/N eller tryk Enter for at bevare det nuværende:");
                String newActiveStatus = scanner.nextLine();
                if (!newActiveStatus.isEmpty()) {
                    if ("Y".equalsIgnoreCase(newActiveStatus)) {
                        swimmerToEdit.setActiveMember(true);
                    } else if ("N".equalsIgnoreCase(newActiveStatus)) {
                        swimmerToEdit.setActiveMember(false);
                    } else {
                        System.out.println("Ugyldigt input. Medlemsskab forbliver uændret.");
                    }
                }
                System.out.println("Svømmer opdateret:");
                System.out.println(swimmerToEdit);
            } else {
                System.out.println("Fejl: Svømmer ikke fundet.");
            }
        } catch (Exception e) {
            System.out.println("En fejl er opstået: " + e.getMessage());
            e.printStackTrace();

        }
    }

    static void updatePayment(ArrayList<Swimmer> list, Scanner scanner) {
        try {
            System.out.println("Opdater kontingentbetaling\n");
            System.out.println("Indtast medlemmets navn:");

            String searchName = scanner.nextLine();
            Swimmer swimmerToEdit = null;

            for (Swimmer s : list) {
                if (s.getName().equalsIgnoreCase(searchName)) {
                    swimmerToEdit = s; // Assign the found swimmer to swimmerToEdit.
                    break;
                }
            }
            if (swimmerToEdit != null) {
                System.out.println("Medlem fundet med følgende info:");
                System.out.println(swimmerToEdit);
                System.out.println();
                System.out.println("Medlem skylder følgende: " + swimmerToEdit.getOwedAmount());
                System.out.println("Indtast beløb for betaling");
                float paymentAmount = scanner.nextFloat();
                scanner.nextLine();
                if (paymentAmount <= swimmerToEdit.getOwedAmount()) {
                    swimmerToEdit.registerPayment(paymentAmount);
                    System.out.println("Betaling registreret. Resterende beløb: " + swimmerToEdit.getOwedAmount());
                    System.out.println("Beløb: " + paymentAmount + " opdateret for medlem: " + swimmerToEdit.getName());
                    //beslut hvilken vi ønsker ovenstående!
                    //System.out.println(swimmerToEdit);
                } else {
                    System.out.println("Beløb er for meget. Medlem skylder: " + swimmerToEdit.getOwedAmount());
                }
            } else {
                System.out.println("Fejl: Medlem ikke fundet.");
            }
        } catch (Exception e) {
            System.out.println("En fejl er opstået: " + e.getMessage());
            e.printStackTrace();
        }
    }



    static void registerCompetitionSwimmer(ArrayList<Swimmer> list, Scanner scanner) {
        try {
            System.out.println("Tilmeld svømmer til konkurrence.");
            System.out.println("Medlemsliste:");
            for (Swimmer s : list) {
                System.out.println(list);
            }
            System.out.println("Indtast navn på svømmer, du ønsker at redigere:");
            String searchName = scanner.nextLine();
            Swimmer swimmerToEdit = null;

            for (Swimmer s : list) {
                if (s.getName().equalsIgnoreCase(searchName)) {
                    swimmerToEdit = s; // Assign the found swimmer to swimmerToEdit.
                    break;
                }
            }

            if (swimmerToEdit != null) {
                System.out.println("Svømmer stamdata:");
                System.out.println(swimmerToEdit);

                System.out.println("Vil du indstille " + swimmerToEdit.getName() + " til konkurrence? skriv Ja eller tryk Enter for at bevare den nuværende status:");
                String competitionChoice = scanner.nextLine();
                if (!competitionChoice.isEmpty()) {
                    if ("Ja".equalsIgnoreCase(competitionChoice)) {
                        swimmerToEdit.setCompetitionSwimmer(true);
                    } else {
                        System.out.println("Ugyldigt input. Konkurrence status forbliver uændret.");
                    }
                    System.out.println("Svømmer opdateret:");
                } else {
                    System.out.println("Fejl: Svømmer ikke fundet.");
                }
            }
        } catch (Exception e) {
            System.out.println("En fejl er opstået: " + e.getMessage());
            e.printStackTrace();
        }
    }
    static void registerDiscipline(ArrayList<Swimmer> list, Scanner scanner){
        try {
            System.out.println("Tilmeld svømmer til disciplin.");
            System.out.println("Medlemsliste:");
            System.out.println(list);
            System.out.println("Indtast navn på svømmer, du ønsker at tilmelde:");
            String searchName = scanner.nextLine();
            Swimmer swimmerToEdit = null;

            for (Swimmer s : list) {
                if (s.getName().equalsIgnoreCase(searchName)) {
                    swimmerToEdit = s; // Assign the found swimmer to swimmerToEdit.
                    break;
                }
            }
            if (swimmerToEdit != null) {
                LocalDate today = LocalDate.now();
                System.out.println("Svømmer stamdata:");
                System.out.println(swimmerToEdit);
                System.out.println();
                System.out.println("Vælg hvilken disciplin, du ønsker at opdatere " + swimmerToEdit.getName() + " for");
                System.out.println("Tast 1 for Butterfly Record");
                System.out.println("Tast 2 for Backstroke Record");
                System.out.println("Tast 3 for Freestyle Record");
                String valg = scanner.nextLine();
                if (valg.equalsIgnoreCase("1")) {
                    System.out.println("Indtast ny rekord:");
                    float rekord = scanner.nextFloat();
                    scanner.nextLine();
                    swimmerToEdit.setRecord(rekord,swimmerToEdit.getBackstrokeRecord(), swimmerToEdit.getFreestyleRecord());
                } else if (valg.equalsIgnoreCase("2")) {
                    float rekord = scanner.nextFloat();
                    scanner.nextLine();
                    swimmerToEdit.setRecord(swimmerToEdit.getButterflyRecord(),rekord,swimmerToEdit.getFreestyleRecord());
                } else if (valg.equalsIgnoreCase("3")) {
                    float rekord = scanner.nextFloat();
                    scanner.nextLine();
                    swimmerToEdit.setRecord(swimmerToEdit.getButterflyRecord(), swimmerToEdit.getBackstrokeRecord(), rekord);
                } else {
                    System.out.println("Valg ugyldigt, prøv igen.");
                }
            }

            } catch (Exception e) {
        System.out.println("En fejl er opstået: " + e.getMessage());
        e.printStackTrace();
        }
    }
    static void removeSwimmer(ArrayList<Swimmer> list, Scanner scanner) {
        try {
            System.out.println("Fjern medlem");
            System.out.println("Medlemsliste:");
            for (Swimmer s : list) {
                System.out.println(s.getName());
            }
            System.out.println("Indtast navn på svømmer");
            String searchName = scanner.nextLine();
            Swimmer swimmerToRemove = null;

            for(Swimmer s : list) {
                if (s.getName().equalsIgnoreCase(searchName)) {
                    swimmerToRemove = s;
                    s.resetIndexNos(list);
                    break;
                }
            }
            if (swimmerToRemove !=null) {
                list.remove(swimmerToRemove);
                System.out.println("Medlem fjernet: " +swimmerToRemove.getName());
            }else {
                System.out.println("Fejl, svømmer blev ikke fundet. ");
            }
        } catch (Exception e) {
            System.out.println("En fejl er opstået: " + e.getMessage());
            e.printStackTrace();
        }
    }
}