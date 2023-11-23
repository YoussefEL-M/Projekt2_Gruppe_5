import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    public static void main(String[] args) throws IOException {
        menu();
    }

    public static void menu() {
        ArrayList<Swimmer> swimmers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        int choice = 0;
        do {
            try {
                int subchoice1=0;
                int subchoice2=0;
                int subchoice3=0;
                System.out.println();
                System.out.println("**********************");
                System.out.println("Svømmeklubben Delfinen");
                System.out.println("**********************");
                System.out.println();
                System.out.println("1. Medlemsadministration");
                System.out.println("1.1 Opret medlem");
                System.out.println("1.2 Opdater stamoplysninger for medlem");
                System.out.println("1.3 Generer oversigt over nuværende medlemmer");
                System.out.println();
                System.out.println("2. Trænermenu");
                System.out.println("2.1 Tilføj svømmere til hold");
                System.out.println("2.2 Registrer discipliner");
                System.out.println("2.3 Registrer konkurrencedeltagere");
                System.out.println("2.4 Generer top 5 oversigt");
                System.out.println();
                System.out.println("3. Kasserermenu");
                System.out.println("3.1 Vis medlemmer i restance");
                System.out.println("3.2 Opdater kontigentbetaling");
                System.out.println();
                System.out.println("4. Afslut program\n");
                System.out.print("Indtast venligst et tal mellem 1-4 \n");
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> {
                        while (subchoice1 != 4) {
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
                                    Swimmer newSwimmer = getSwimmerDetails(scanner);
                                    swimmers.add(newSwimmer);
                                }
                                case 2 -> {
                                    editSwimmer(swimmers, scanner);
                                }
                                case 3 -> {
                                    showSwimmers(swimmers);
                                }
                                case 4 -> {
                                    System.out.println("Går tilbage til main menu.");
                                }
                                default -> System.out.println("Fejl: Forkert input. Prøv igen.");
                            }
                        }
                    }
                    case 2 -> {
                        while (subchoice2!=5) {
                            System.out.println();
                            System.out.println("Trænermenu");
                            System.out.println("1 Tilføj svømmere til hold");
                            System.out.println("2 Registrer discipliner");
                            System.out.println("3 Registrer konkurrencedeltagere");
                            System.out.println("4 Generer top 5 oversigt");
                            System.out.println("5 Gå tilbage");
                            System.out.println();
                            subchoice2 = scanner.nextInt();
                            scanner.nextLine();

                            switch(subchoice2){
                                case 1 -> {
                                    System.out.println("Tilføj svømmere til hold");
                                }
                                case 2 -> {

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
                                    // print liste af medlemmer i restance.
                                }
                                case 2 -> {
                                    updatePayment(swimmers, scanner);

                                }
                                default -> System.out.println("Fejl: Forkert input. Prøv igen.");
                            }
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("An error has occurred " + e.getMessage());
                scanner.nextLine();
            }
        } while (choice != 4);
        scanner.close();
    }

    private static Swimmer getSwimmerDetails(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Du har valgt Opret medlem");
                System.out.println("Indtast navn:");
                String name = scanner.nextLine();
                System.out.println("Indtast fødselsdato:");
                LocalDate birthdate = LocalDate.parse(scanner.nextLine());
                System.out.println("Er det et aktivt medlem? Indtast Y/N");
                String valg = scanner.nextLine();
                boolean activeMember = false;
                short owedAmount=0;
                float butterflyRecord=0,backstrokeRecord=0,freestyleRecord=0;
                boolean competitionSwimmer=false;
                Trainer trainer=null;

                if (valg.equalsIgnoreCase("y")) {
                    activeMember = true;
                } else if (valg.equalsIgnoreCase("n")) {
                    activeMember = false;
                } else {
                    System.out.println("Valg ugyldigt, prøv igen.");
                }
                System.out.println("Ny svømmer oprettet med følgende stamdata:");
                System.out.println("Navn: " + name);
                System.out.println("Fødselsdato: " + birthdate);
                System.out.println("Aktivt medlem: " + valg);
                return new Swimmer(activeMember,name,birthdate,owedAmount,butterflyRecord,backstrokeRecord,freestyleRecord,competitionSwimmer,trainer);
            } catch (IllegalArgumentException e) {
                System.out.println("An error has occured! " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An error has occured! " + e.getMessage());
            }
        }
    }

    static void showSwimmers(ArrayList<Swimmer> list) {
        try {
            System.out.println("Medlemsliste:");
            for (Swimmer s : list) {
                System.out.println(list);
            }

        } catch (Exception e) {
            System.out.println("An error has occurred: " + e.getMessage());
            e.printStackTrace();

        }
    }
    static void editSwimmer(ArrayList<Swimmer> list, Scanner scanner){
        try {
            System.out.println("Medlemsliste:");
            for (Swimmer s : list) {
                System.out.println(list);
            }
            System.out.println("Indtast navn på svømmer, du ønsker at redigere");
            String searchName = scanner.nextLine();
            Swimmer swimmerToEdit = null;


            for (Swimmer s : list) {
                if (s.getName().equals(searchName)) {
                    swimmerToEdit = s; // Assign the found swimmer to swimmerToEdit.
                    break;
                }
            }

            if (swimmerToEdit != null){
                System.out.println("Svømmer stamdata:");
                System.out.println(swimmerToEdit);

                System.out.println("Vil du ændre navn? Indtast nyt navn eller tryk Enter for at bevare det nuværende:");
                String newName = scanner.nextLine();
                if (!newName.isEmpty()){
                    swimmerToEdit.setName(newName);
                }

                System.out.println("Vil du ændre fødselsdato? Indtast nyt datoformat (YYYY-MM-DD) eller tryk Enter for at bevare det nuværende:");
                String newBirthdate = scanner.nextLine();
                if(!newBirthdate.isEmpty()){
                    swimmerToEdit.setBirthdate(LocalDate.parse(newBirthdate));
                }

                System.out.println("Vil du ændre aktivt medlemskab? Indtast Y/N eller tryk Enter for at bevare det nuværende:");
                String newActiveStatus = scanner.nextLine();
                if (!newActiveStatus.isEmpty()) {
                    if ("Y".equalsIgnoreCase(newActiveStatus)) {
                        swimmerToEdit.setActiveMember(true);
                    } else if ("N".equalsIgnoreCase(newActiveStatus)){
                        swimmerToEdit.setActiveMember(false);
                    } else {
                        System.out.println("Ugyldigt input. Medlemsskab forbliver uændret.");
                    }
                }

                System.out.println("Svømmer opdateret:");
                System.out.println(swimmerToEdit);
            }
            else {
                System.out.println("Fejl: Svømmer ikke fundet.");
            }


        } catch (Exception e) {
            System.out.println("An error has occurred: " + e.getMessage());
            e.printStackTrace();

        }
    }
    static void updatePayment(ArrayList<Swimmer> list, Scanner scanner){
        try {
            System.out.println("Opdater kontingentbetaling\n");
            System.out.println("Indtast medlemmets navn:");

            String searchName = scanner.nextLine();
            Swimmer swimmerToEdit = null;

            for (Swimmer s : list) {
                if (s.getName().equals(searchName)) {
                    swimmerToEdit = s; // Assign the found swimmer to swimmerToEdit.
                    break;
                }
            }
            if (swimmerToEdit!=null){
                System.out.println("Medlem fundet med følgende info:");
                System.out.println(swimmerToEdit);
                System.out.println();
                System.out.println("Medlem skylder følgende: "+swimmerToEdit.getOwedAmount());
                System.out.println("Indtast beløb for betaling");
                float paymentAmount = scanner.nextFloat();
                scanner.nextLine();
                if (paymentAmount <= swimmerToEdit.getOwedAmount()) {
                    swimmerToEdit.registerPayment(paymentAmount);
                    System.out.println("Payment registered. Remaining amount: " + swimmerToEdit.getOwedAmount());
                    System.out.println("Beløb: " + paymentAmount + " opdateret for medlem: " + swimmerToEdit.getName());
                    //beslut hvilken vi ønsker ovenstående!
                    //System.out.println(swimmerToEdit);
                } else{
                    System.out.println("Beløb er for meget. Medlem skylder: " + swimmerToEdit.getOwedAmount());
                    }
            } else {
            System.out.println("Fejl: Medlem ikke fundet.");
            }
        } catch (Exception e) {
        System.out.println("An error has occurred: " + e.getMessage());
        e.printStackTrace();
    }
    }
}
