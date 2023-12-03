import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Club {

    static Swimmer createSwimmer(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Du har valgt Opret medlem");
                System.out.println("Indtast navn:");
                String name = scanner.nextLine();
                System.out.println("Indtast fødselsdato i format YYYY-MM-DD:");
                LocalDate birthdate = LocalDate.parse(scanner.nextLine());
                System.out.println("Er det et aktivt medlem? Indtast Y/N");
                String valg = scanner.nextLine();
                short owedAmount = 0;
                boolean competitionSwimmer = false;

                if (valg.equalsIgnoreCase("y")) {
                    System.out.println("Ny svømmer oprettet.");

                    SwimMeet s1 = new SwimMeet("Default Meet", LocalDate.now(), Discipline.Butterfly, 0.0f, (byte) 0);

                    return new Swimmer(true, name, birthdate, owedAmount, competitionSwimmer, (short) -1, new Results(new ArrayList<>(List.of(s1))),null);
                } else if (valg.equalsIgnoreCase("n")) {

                    //Laver et default swimmeet objekt for svømmer
                    SwimMeet s1 = new SwimMeet("Default Meet", LocalDate.now(), Discipline.Butterfly, 0.0f, (byte) 0);

                    System.out.println("Ny svømmer oprettet.");
                    return new Swimmer(false, name, birthdate, owedAmount, competitionSwimmer, (short) -1, new Results(new ArrayList<>(List.of(s1))),null);
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

    static void calculateYearlyCharge(ArrayList<Swimmer> swimmers) {
        for (Swimmer swimmer : swimmers) {
            swimmer.calculateYearlyCharge();
        }
    }

    static void editSwimmer(ArrayList<Swimmer> list, Scanner scanner) {
        try {

            System.out.println();
            System.out.println("Vælg venligst hvad du ønsker at redigere:");
            System.out.println("1. Navn");
            System.out.println("2. Fødselsdato");
            System.out.println("3. Medlemsskabstatus");
            System.out.println("4. Opdater rekorder");
            System.out.println();

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println();
                    System.out.println("Søg efter medlem eller tryk enter for at vise alle medlemmer.");
                    String searchTerm = scanner.nextLine();

                    System.out.println("Medlemsliste:");
                    for (Swimmer s : list) {
                        if (s.getName().toLowerCase().contains(searchTerm.toLowerCase()))
                            System.out.println(s);
                    }
                    System.out.println("Indtast indekstal på svømmer, du ønsker at redigere");

                    Swimmer swimmerToEdit = list.get(scanner.nextInt());
                    scanner.nextLine();

                    if (swimmerToEdit != null) {
                        System.out.println("Svømmer stamdata:");
                        System.out.println(swimmerToEdit);
                        System.out.println();
                        System.out.println("Du vil ændre navn.");
                        System.out.println("Indtast nyt navn og tryk Enter");
                        String newName = scanner.nextLine();
                        swimmerToEdit.setName(newName);
                        System.out.println("Navneændring er foretaget.");
                    }
                }
                case 2 -> {
                    System.out.println();
                    System.out.println("Søg efter medlem eller tryk enter for at vise alle medlemmer.");
                    String searchTerm = scanner.nextLine();

                    System.out.println("Medlemsliste:");
                    for (Swimmer s : list) {
                        if (s.getName().toLowerCase().contains(searchTerm.toLowerCase()))
                            System.out.println(s);
                    }
                    System.out.println("Indtast indekstal på svømmer, du ønsker at redigere");

                    Swimmer swimmerToEdit = list.get(scanner.nextInt());
                    scanner.nextLine();

                    if (swimmerToEdit != null) {
                        System.out.println("Svømmer stamdata:");
                        System.out.println(swimmerToEdit);
                        System.out.println();
                        System.out.println("Du vil ændre fødselsdato.");
                        System.out.println("Indtast nyt datoformat (YYYY-MM-DD) og tryk Enter");
                        String newBirthdate = scanner.nextLine();
                        swimmerToEdit.setBirthdate(LocalDate.parse(newBirthdate));
                        System.out.println("Fødselsdagsændring er foretaget.");
                    }
                }
                case 3 -> {
                    System.out.println();
                    System.out.println("Søg efter medlem eller tryk enter for at vise alle medlemmer.");
                    String searchTerm = scanner.nextLine();

                    System.out.println("Medlemsliste:");
                    for (Swimmer s : list) {
                        if (s.getName().toLowerCase().contains(searchTerm.toLowerCase()))
                            System.out.println(s);
                    }
                    System.out.println("Indtast indekstal på svømmer, du ønsker at redigere");

                    Swimmer swimmerToEdit = list.get(scanner.nextInt());
                    scanner.nextLine();

                    if (swimmerToEdit != null) {
                        System.out.println("Svømmer stamdata:");
                        System.out.println(swimmerToEdit);
                        System.out.println();
                        System.out.println("Du vil ændre aktivt medlemskab");
                        System.out.println("Indtast Y/N og tryk Enter");
                        System.out.println();
                        String newActiveStatus = scanner.nextLine();
                        if (!newActiveStatus.isEmpty()) {
                            if ("Y".equalsIgnoreCase(newActiveStatus)) {
                                System.out.println("Du har valgt aktivt medlemskab.");
                                System.out.println("Ændring foretaget.");
                                swimmerToEdit.setActiveMember(true);
                            } else if ("N".equalsIgnoreCase(newActiveStatus)) {
                                System.out.println("Du har valgt inaktivt medlemskab.");
                                System.out.println("Ændring foretaget.");
                                swimmerToEdit.setActiveMember(false);
                            } else {
                                System.out.println("Ugyldigt input. Medlemsskab forbliver uændret.");
                            }
                        }
                    }
                }
                case 4 -> {
                    try {
                        System.out.println();
                        System.out.println("Søg efter medlem eller tryk enter for at vise alle medlemmer.");
                        String searchTerm = scanner.nextLine();

                        System.out.println("Medlemsliste:");
                        for (Swimmer s : list) {
                            if (s.getName().toLowerCase().contains(searchTerm.toLowerCase()))
                                System.out.println(s);
                        }
                        System.out.println("Indtast indekstal på svømmer, du ønsker at redigere");

                        Swimmer swimmerToEdit = list.get(scanner.nextInt());
                        scanner.nextLine();

                        if (swimmerToEdit != null) {
                            System.out.println("Svømmer stamdata:");
                            System.out.println(swimmerToEdit);
                            System.out.println("Du vil opdatere rekorder.");
                            System.out.println();
                            swimmerToEdit.results.updateRecords(scanner);
                            System.out.println("Rekorder opdateret:");
                            System.out.println(swimmerToEdit);
                        } else {
                            System.out.println("Fejl: Svømmer ikke fundet.");
                        }
                    } catch (Exception e) {
                        System.out.println("En fejl er opstået: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
                default -> System.out.println("Ugyldigt input.");
            }
        } catch (Exception e) {
            System.out.println("En fejl er opstået: " + e.getMessage());
            e.printStackTrace();
        }
    }

    static void updatePayment(ArrayList<Swimmer> list, Scanner scanner) {
        try {
            System.out.println("Opdater kontingentbetaling\n");
            System.out.println("Søg efter medlem eller tryk enter for at vise alle medlemmer.");
            String searchTerm = scanner.nextLine();

            System.out.println("Medlemsliste:");
            for (Swimmer s : list) {
                if(s.getName().toLowerCase().contains(searchTerm.toLowerCase()))
                    System.out.println(s);
            }
            System.out.println();
            System.out.println("Indtast indekstal på det medlem, du ønsker at opdatere info for.");
            Swimmer swimmerToEdit = list.get(scanner.nextInt());
            scanner.nextLine();


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
                System.out.println(s);
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
    static void removeSwimmer(ArrayList<Swimmer> list, Scanner scanner) {
        try {
            System.out.println("Fjern medlem");
            System.out.println();
            System.out.println("Søg efter medlem eller tryk enter for at vise alle medlemmer.");
            String searchTerm = scanner.nextLine();

            System.out.println("Medlemsliste:");
            for (Swimmer s : list) {
                if(s.getName().toLowerCase().contains(searchTerm.toLowerCase()))
                    System.out.println(s);
            }
            System.out.println();
            System.out.println("Indtast indekstal på det medlem, du ønsker at fjerne.");
            Swimmer swimmerToRemove = list.get(scanner.nextInt());
            scanner.nextLine();

            if (swimmerToRemove !=null) {
                list.remove(swimmerToRemove);
                Swimmer.resetIndexNos(list);
                System.out.println("Medlem fjernet: " +swimmerToRemove.getName());
            }else {
                System.out.println("Fejl, svømmer blev ikke fundet. ");
            }
        } catch (Exception e) {
            System.out.println("En fejl er opstået: " + e.getMessage());
            e.printStackTrace();
        }
    }
    static void displayTopFive(ArrayList<Swimmer> list, Scanner sc){
        try{
            ArrayList<Swimmer> sortList = new ArrayList<>();
            System.out.println();
            System.out.println("Vælg venligst disciplin:");
            System.out.println("1. Rygcrawl");
            System.out.println("2. Butterfly");
            System.out.println("3. Crawl");

            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1 -> {
                    for(Swimmer s: list){
                        if(s.getBackstrokeRecord()!=0)
                            sortList.add(s);
                    }
                    sortList.sort(new BackstrokeSort());
                    for(int i = 0;i<=4;i++){
                        System.out.println(sortList.get(i));
                    }
                }
                case 2 -> {
                    for(Swimmer s: list){
                        if(s.getButterflyRecord()!=0)
                            sortList.add(s);
                    }
                    sortList.sort(new ButterflySort());
                    for(int i = 0;i<=4;i++){
                        System.out.println(sortList.get(i));
                    }
                }
                case 3 -> {
                    for(Swimmer s: list){
                        if(s.getFreestyleRecord()!=0)
                            sortList.add(s);
                    }
                    sortList.sort(new FreestyleSort());
                    for(int i = 0;i<=4;i++){
                        System.out.println(sortList.get(i));
                    }
                }
                default -> System.out.println("Ugyldigt input.");
            }
        }catch(InputMismatchException E){
            System.out.println("Inputfejl: prøv igen.");
        }
    }
}