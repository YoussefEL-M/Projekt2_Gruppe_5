import java.lang.reflect.Array;
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
                System.out.println("Indtast fødselsdato i format ÅÅÅÅ-MM-DD:");
                LocalDate birthdate = LocalDate.parse(scanner.nextLine());
                System.out.println("Er det et aktivt medlem? Indtast J/N");
                String valg = scanner.nextLine();
                short owedAmount = 0;
                boolean competitionSwimmer = false;

                if (valg.equalsIgnoreCase("j")) {
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
                System.out.println("Ugyldig dato format, skriv i formatet ÅÅÅÅ-MM-DD " + e.getMessage());
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
            System.out.println();

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("Medlemsliste:");

                    for (int i = 0; i < list.size(); i++) {
                        System.out.println("Indeks: " + i + " - Svømmer: " + list.get(i).getName());
                    }

                    System.out.println("Indtast indekstal på svømmer, du ønsker at ændre navn på:");
                    int swimmerIndex = scanner.nextInt();
                    scanner.nextLine();

                    if (swimmerIndex >= 0 && swimmerIndex < list.size()) {
                        Swimmer selectedSwimmer = list.get(swimmerIndex);
                        System.out.println("Svømmer stamdata:");
                        System.out.println(selectedSwimmer);
                        System.out.println();
                        System.out.println("Du vil ændre navn.");
                        System.out.println("Indtast nyt navn og tryk Enter");
                        String newName = scanner.nextLine();
                        selectedSwimmer.setName(newName);
                        System.out.println("Navneændring er foretaget.");
                    }
                }
                case 2 -> {
                    System.out.println("Medlemsliste:");

                    for (int i = 0; i < list.size(); i++) {
                        System.out.println("Indeks: " + i + " - Svømmer: " + list.get(i).getName()+" fødselsdag: "+list.get(i).getBirthday());
                    }

                    System.out.println("Indtast indekstal på svømmer, du ønsker at ændre fødselsdag på:");
                    int swimmerIndex = scanner.nextInt();
                    scanner.nextLine();

                    if (swimmerIndex >= 0 && swimmerIndex < list.size()) {
                        Swimmer selectedSwimmer = list.get(swimmerIndex);
                        System.out.println("Svømmer stamdata:");
                        System.out.println(selectedSwimmer);
                        System.out.println();
                        System.out.println("Du vil ændre fødselsdato.");
                        System.out.println("Indtast nyt datoformat (ÅÅÅÅ-MM-DD) og tryk Enter");
                        String newBirthdate = scanner.nextLine();
                        selectedSwimmer.setBirthdate(LocalDate.parse(newBirthdate));
                        System.out.println("Fødselsdagsændring er foretaget.");
                    }
                }
                case 3 -> {
                    System.out.println("Medlemsliste:");

                    for (int i = 0; i < list.size(); i++) {
                        System.out.println("Indeks: " + i + " - Svømmer: " + list.get(i).getName());
                    }

                    System.out.println("Indtast indekstal på svømmer, du ønsker at ændre aktivt/passivt medlemskab på:");
                    int swimmerIndex = scanner.nextInt();
                    scanner.nextLine();

                    if (swimmerIndex >= 0 && swimmerIndex < list.size()) {
                        Swimmer selectedSwimmer = list.get(swimmerIndex);
                        System.out.println("Svømmer stamdata:");
                        System.out.println(selectedSwimmer);
                        System.out.println();
                        System.out.println("Du vil ændre aktivt medlemskab");
                        System.out.println("Indtast J/N og tryk Enter");
                        System.out.println();
                        String newActiveStatus = scanner.nextLine();
                        if (!newActiveStatus.isEmpty()) {
                            if ("J".equalsIgnoreCase(newActiveStatus)) {
                                System.out.println("Du har valgt aktivt medlemskab.");
                                System.out.println("Ændring foretaget.");
                                selectedSwimmer.setActiveMember(true);
                            } else if ("N".equalsIgnoreCase(newActiveStatus)) {
                                System.out.println("Du har valgt inaktivt medlemskab.");
                                System.out.println("Ændring foretaget.");
                                selectedSwimmer.setActiveMember(false);
                            } else {
                                System.out.println("Ugyldigt input. Medlemsskab forbliver uændret.");
                            }
                        }
                    }
                }

                default -> System.out.println("Ugyldigt input.");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Ugyldig dato format, venligst skriv i formattet som beskrevet");
        }catch (IndexOutOfBoundsException e) {
            System.out.println("Der er ingen svømmere med det indeks tal");
        }catch (Exception e) {
            System.out.println("En fejl er opstået: " + e.getMessage());
            e.printStackTrace();
        }
    }
    static void updateCompetitionRecords(ArrayList<Swimmer> list, Scanner scanner) {
        try {

            System.out.println("Medlemsliste:");

            for (int i = 0; i < list.size(); i++) {
                System.out.println("Indeks: " + i + " - Svømmer: " + list.get(i).getName());
            }

            System.out.println("Indtast indekstal på svømmer, du ønsker at opdatere rekorder hos:");
            int swimmerIndex = scanner.nextInt();
            scanner.nextLine();

            if (swimmerIndex >= 0 && swimmerIndex < list.size()) {
                Swimmer selectedSwimmer = list.get(swimmerIndex);

                if (selectedSwimmer != null) {
                    System.out.println("Svømmer stamdata:");
                    System.out.println(selectedSwimmer);
                    System.out.println("Du vil opdatere rekorder.");
                    System.out.println();
                    selectedSwimmer.results.updateRecords(scanner);
                    System.out.println("Rekorder opdateret:");
                    System.out.println(selectedSwimmer);
                } else {
                    System.out.println("Fejl: Svømmer ikke fundet.");
                }
            }
            }catch(IndexOutOfBoundsException e){
            System.out.println("Der er ingen svømmere med det indeks tal");
            }catch(Exception e){
            System.out.println("En fejl er opstået: " + e.getMessage());
            e.printStackTrace();
        }
    }

    static void editTrainer(ArrayList<Trainer> trainers, ArrayList<Swimmer> swimmers, Scanner scanner){
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

    static void updatePayment(ArrayList<Swimmer> list, Scanner scanner) {
        try {
            System.out.println("Opdater kontingentbetaling\n");
            System.out.println("Medlemsliste:");

            for (int i = 0; i < list.size(); i++) {
                System.out.println("Indeks: " + i + " - Svømmer: " + list.get(i).getName());
            }

            System.out.println("Indtast indekstal på svømmer, du ønsker at tage imod betaling fra:");
            int swimmerIndex = scanner.nextInt();
            scanner.nextLine();

            if (swimmerIndex >= 0 && swimmerIndex < list.size()) {
                Swimmer selectedSwimmer = list.get(swimmerIndex);
                System.out.println("Medlem fundet med følgende info:");
                System.out.println(selectedSwimmer);
                System.out.println();
                System.out.println("Medlem skylder følgende: " + selectedSwimmer.getOwedAmount());
                System.out.println("Indtast beløb for betaling");
                float paymentAmount = scanner.nextFloat();
                scanner.nextLine();
                if (paymentAmount <= selectedSwimmer.getOwedAmount()) {
                    selectedSwimmer.registerPayment(paymentAmount);
                } else {
                    System.out.println("Beløb er for meget. Medlem skylder: " + selectedSwimmer.getOwedAmount());
                }
            } else {
                System.out.println("Fejl: Medlem ikke fundet.");
            }
        }catch (IndexOutOfBoundsException e){
            System.out.println("Der er ingen svømmere med det indeks tal");
        }
        catch (InputMismatchException e) {
            System.out.println("Forkert input: ");
        }
    }

    static void registerCompetitionSwimmer(ArrayList<Swimmer> list, Scanner scanner) {
        try {
            System.out.println("Tilmeld svømmer til konkurrence.");
            System.out.println("Medlemsliste:");

            for (int i = 0; i < list.size(); i++) {
                System.out.println("Indeks: " + i + " - Svømmer: " + list.get(i).getName());
            }

            System.out.println("Indtast indekstal på svømmer, du ønsker at registrere som konkurrencesvømmer:");
            int swimmerIndex = scanner.nextInt();
            scanner.nextLine();

            if (swimmerIndex >= 0 && swimmerIndex < list.size()) {
                Swimmer selectedSwimmer = list.get(swimmerIndex);

                System.out.println("Vil du indstille " + selectedSwimmer.getName() + " til konkurrence? skriv Ja eller tryk Enter for at bevare den nuværende status:");
                String competitionChoice = scanner.nextLine();
                if (!competitionChoice.isEmpty()) {
                    if ("Ja".equalsIgnoreCase(competitionChoice)) {
                        selectedSwimmer.setCompetitionSwimmer(true);
                    } else {
                        System.out.println("Ugyldigt input. Konkurrence status forbliver uændret.");
                    }
                    System.out.println("Svømmer opdateret: "+selectedSwimmer);

                } else {
                    System.out.println("Fejl: Svømmer ikke fundet.");
                }
            }
        } catch (Exception e) {
            System.out.println("En fejl er opstået: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static void registerCompetition(ArrayList<Swimmer> list, Scanner scanner) {
        try {
            System.out.println("Registrer konkurrence:");
            System.out.println("Medlemsliste:");

            for (int i = 0; i < list.size(); i++) {
                System.out.println("Indeks: " + i + " - Svømmer: " + list.get(i).getName());
            }

            System.out.println("Indtast indekstal på svømmer, du ønsker at tilmelde konkurrence:");
            int swimmerIndex = scanner.nextInt();
            scanner.nextLine();

            if (swimmerIndex >= 0 && swimmerIndex < list.size()) {
                Swimmer selectedSwimmer = list.get(swimmerIndex);

                System.out.println("Du har valgt at registrere konkurrence for: " + selectedSwimmer.getName());
                Results.addSwimMeet(scanner);
            } else {
                System.out.println("Ugyldigt indekstal. Prøv igen.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Forkert input: " + e.getMessage());
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
                System.out.println("Fejl, medlem blev ikke fundet. ");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Der er ingen svømmere med det indeks tal");
        }catch (Exception e) {
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
    static Trainer createTrainer(Scanner sc) {
        String name = null;
        try {
            System.out.println("Indtast venligst navn på ny træner:");
            name = sc.nextLine();
            System.out.println("Træner oprettet med navn: "+name);
        } catch (InputMismatchException E) {
            System.out.println("Fejl: ugyldigt input.");
        }
        return new Trainer(name);
    }
    static void removeTrainer(Scanner sc, ArrayList<Trainer> trainerList, ArrayList<Swimmer> swimmerList){
        try {
            System.out.println("Fjern træner");
            System.out.println();
            System.out.println("Søg efter træner eller tryk enter for at vise alle medlemmer.");
            String searchTerm = sc.nextLine();
            for (Trainer t : trainerList) {
                if(t.getName().toLowerCase().contains(searchTerm.toLowerCase()))
                    System.out.println("Indeks: "+trainerList.indexOf(t)+" Træner: "+t);
            }

            if (searchTerm==null) {
                System.out.println("Trænerliste:");
                for (int i = 0; i < trainerList.size(); i++) {
                    System.out.println("Indeks: " + i + " - Træner: " + trainerList.get(i).getName());
                }
            }
            System.out.println();
            System.out.println("Indtast indekstal på den træner, du ønsker at fjerne.");
            int indexToRemove = sc.nextInt();
            sc.nextLine();
            Trainer trainerToRemove = trainerList.get(indexToRemove);

            if (trainerToRemove !=null) {
                trainerList.remove(trainerToRemove);

                for(Swimmer s: swimmerList){
                    if(s.trainerIndex==indexToRemove)
                        s.trainerIndex=-1;
                    else if(s.trainerIndex>indexToRemove)
                        s.trainerIndex--;
                }

                System.out.println("Træner fjernet: " +trainerToRemove.getName());
            }else {
                System.out.println("Fejl, træner blev ikke fundet. ");
            }
        }catch (IndexOutOfBoundsException e){
            System.out.println("Der er ingen svømmere med det indeks tal");
        }
        catch (Exception e) {
            System.out.println("En fejl er opstået: " + e.getMessage());
            e.printStackTrace();
        }
    }
}