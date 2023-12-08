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

                    return new Swimmer(true, name, birthdate, owedAmount, competitionSwimmer, (short) -1, new Results(new ArrayList<>(List.of(s1)),0,0,0),null);
                } else if (valg.equalsIgnoreCase("n")) {

                    //Laver et default swimmeet objekt for svømmer
                    SwimMeet s1 = new SwimMeet("Default Meet", LocalDate.now(), Discipline.Butterfly, 0.0f, (byte) 0);

                    System.out.println("Ny svømmer oprettet.");
                    return new Swimmer(false, name, birthdate, owedAmount, competitionSwimmer, (short) -1, new Results(new ArrayList<>(List.of(s1)),0,0,0),null);
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
                System.out.println(s.getName());
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
        System.out.println("Kontingent opdateret for alle medlemmer.");
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
                    System.out.println("Søg efter medlem eller tast enter for at vise alle medlemmer.");
                    String searchTerm = scanner.nextLine();
                    System.out.println("Medlemsliste:");
                    System.out.println();

                    for(Swimmer s:list){
                        if(s.getName().toLowerCase().contains(searchTerm.toLowerCase()))
                            System.out.println(s.indexNo+". "+s.getName());
                    }

                    System.out.println("Vælg det medlem, du ønsker at ændre navn på:");
                    int swimmerIndex = scanner.nextInt();
                    scanner.nextLine();

                    if (swimmerIndex >= 0 && swimmerIndex < list.size()) {
                        Swimmer selectedSwimmer = list.get(swimmerIndex);
                        System.out.println("Stamdata:");
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
                    System.out.println("Søg efter medlem eller tast enter for at vise alle medlemmer.");
                    String searchTerm = scanner.nextLine();
                    System.out.println("Medlemsliste:");
                    System.out.println();

                    for(Swimmer s:list){
                        if(s.getName().toLowerCase().contains(searchTerm.toLowerCase()))
                            System.out.println(s.indexNo+". "+s.getName());
                    }

                    System.out.println("Vælg det medlem, du ønsker at ændre fødselsdag på:");
                    int swimmerIndex = scanner.nextInt();
                    scanner.nextLine();

                    if (swimmerIndex >= 0 && swimmerIndex < list.size()) {
                        Swimmer selectedSwimmer = list.get(swimmerIndex);
                        System.out.println("Stamdata:");
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
                    System.out.println("Søg efter medlem eller tast enter for at vise alle medlemmer.");
                    String searchTerm = scanner.nextLine();
                    System.out.println("Medlemsliste:");
                    System.out.println();

                    for(Swimmer s:list){
                        if(s.getName().toLowerCase().contains(searchTerm.toLowerCase()))
                            System.out.println(s.indexNo+". "+s.getName());
                    }

                    System.out.println("Vælg det medlem, du ønsker at ændre aktivt/passivt medlemskab på:");
                    int swimmerIndex = scanner.nextInt();
                    scanner.nextLine();

                    if (swimmerIndex >= 0 && swimmerIndex < list.size()) {
                        Swimmer selectedSwimmer = list.get(swimmerIndex);
                        System.out.println("Stamdata:");
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
            System.out.println("Der er ingen medlemmer med det indekstal");
        }catch (Exception e) {
            System.out.println("En fejl er opstået: " + e.getMessage());
            e.printStackTrace();
        }
    }
    static void updateCompetitionRecords(ArrayList<Swimmer> list, Scanner scanner) {
        try {
            System.out.println("Søg efter medlem eller tast enter for at vise alle medlemmer.");
            String searchTerm = scanner.nextLine();
            System.out.println("Medlemsliste:");
            System.out.println();

            for(Swimmer s:list){
                if(s.getName().toLowerCase().contains(searchTerm.toLowerCase()))
                    System.out.println(s.indexNo+". "+s.getName());
            }

            System.out.println("Vælg det medlem, du ønsker at opdatere rekorder for:");
            int swimmerIndex = scanner.nextInt();
            scanner.nextLine();

            if (swimmerIndex >= 0 && swimmerIndex < list.size()) {
                Swimmer selectedSwimmer = list.get(swimmerIndex);

                if (selectedSwimmer != null) {
                    System.out.println("Stamdata:");
                    System.out.println(selectedSwimmer);
                    System.out.println("Du vil opdatere rekorder.");
                    System.out.println();
                    selectedSwimmer.results.updateRecords(scanner);
                    System.out.println("Rekorder opdateret:");
                    System.out.println(selectedSwimmer);
                } else {
                    System.out.println("Fejl: medlem ikke fundet.");
                }
            }
            }catch(IndexOutOfBoundsException e){
            System.out.println("Der er ingen medlemmer med det indekstal");
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
                case 1 -> {Trainer newTrainer = Club.createTrainer(scanner);
                    trainers.add(newTrainer);
                    FileManager.saveBackup(newTrainer);
                }
                case 2 -> {Club.removeTrainer(scanner, trainers, swimmers);
                    Trainer.assignTrainers(trainers,swimmers);
                }
                case 3 -> System.out.println("Går tilbage til main menu.");

                default -> System.out.println("Fejl: ugyldigt input. Prøv igen.");
            }

        } catch(Exception e){
            System.out.println("En fejl er opstået: " + e.getMessage());
            e.printStackTrace();
        }
    }

    static void updatePayment(ArrayList<Swimmer> list, Scanner scanner) {
        try {
            System.out.println("Opdater kontingentbetaling\n");
            System.out.println("Søg efter medlem eller tast enter for at vise alle medlemmer.");
            String searchTerm = scanner.nextLine();
            System.out.println("Medlemsliste:");
            System.out.println();

            for(Swimmer s:list){
                if(s.getName().toLowerCase().contains(searchTerm.toLowerCase()))
                    System.out.println(s.indexNo+". "+s.getName());
            }

            System.out.println("Vælg det medlem, du ønsker at tage imod betaling fra:");
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
            System.out.println("Der er ingen medlemmer med det indekstal");
        }
        catch (InputMismatchException e) {
            System.out.println("Ugyldigt input: ");
        }
    }

    static void registerCompetitionSwimmer(ArrayList<Swimmer> list, Scanner scanner) {
        try {
            System.out.println("Tilmeld medlem som konkurrencesvømmer.");
            System.out.println();
            System.out.println("Søg efter medlem eller tast enter for at vise alle medlemmer.");
            String searchTerm = scanner.nextLine();
            System.out.println("Medlemsliste:");
            System.out.println();

            for(Swimmer s:list){
                if(s.getName().toLowerCase().contains(searchTerm.toLowerCase()))
                    System.out.println(s.indexNo+". "+s.getName());
            }

            System.out.println("Vælg det medlem, du ønsker at registrere som konkurrencesvømmer:");
            int swimmerIndex = scanner.nextInt();
            scanner.nextLine();

            if (swimmerIndex >= 0 && swimmerIndex < list.size()) {
                Swimmer selectedSwimmer = list.get(swimmerIndex);

                System.out.println("Vil du indstille " + selectedSwimmer.getName() + " til konkurrence? Tast J/N, eller tast Enter for at bevare den nuværende status:");
                String competitionChoice = scanner.nextLine();
                if (!competitionChoice.isEmpty()) {
                    if ("J".equalsIgnoreCase(competitionChoice)) {
                        selectedSwimmer.setCompetitionSwimmer(true);
                    } else if("N".equalsIgnoreCase(competitionChoice)){
                        selectedSwimmer.setCompetitionSwimmer(false);
                    } else {
                        System.out.println("Ugyldigt input. Konkurrence status forbliver uændret.");
                    }
                    System.out.println("Medlem opdateret: "+selectedSwimmer);

                } else {
                    System.out.println("Fejl: medlem ikke fundet.");
                }
            }
        } catch (Exception e) {
            System.out.println("En fejl er opstået: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static void registerCompetition(ArrayList<Swimmer> list, Scanner scanner) {
        try {
            System.out.println("Registrer konkurrenceresultater:");
            System.out.println();
            System.out.println("Søg efter medlem eller tast enter for at vise alle medlemmer.");
            String searchTerm = scanner.nextLine();
            System.out.println("Medlemsliste:");
            System.out.println();

            for(Swimmer s:list){
                if(s.getName().toLowerCase().contains(searchTerm.toLowerCase()))
                    System.out.println(s.indexNo+". "+s.getName());
            }

            System.out.println("Vælg det medlem, du ønsker at registrere konkurrenceresultater for:");
            int swimmerIndex = scanner.nextInt();
            scanner.nextLine();

            if (swimmerIndex >= 0 && swimmerIndex < list.size()) {
                Swimmer selectedSwimmer = list.get(swimmerIndex);

                System.out.println("Du har valgt at registrere konkurrenceresultater for: " + selectedSwimmer.getName());
                selectedSwimmer.results.addSwimMeet(scanner);
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
            System.out.println("Søg efter medlem eller tast enter for at vise alle medlemmer.");
            String searchTerm = scanner.nextLine();
            System.out.println("Medlemsliste:");
            System.out.println();

            for(Swimmer s:list){
                if(s.getName().toLowerCase().contains(searchTerm.toLowerCase()))
                    System.out.println(s.indexNo+". "+s.getName());
            }

            System.out.println("Vælg det medlem, du ønsker at fjerne:");
            int swimmerIndex = scanner.nextInt();
            scanner.nextLine();

            if (swimmerIndex >= 0 && swimmerIndex < list.size()) {
                Swimmer selectedSwimmer = list.get(swimmerIndex);
                list.remove(selectedSwimmer);
                Swimmer.resetIndexNos(list);
                System.out.println("Medlem fjernet: " +selectedSwimmer.getName());
            }else {
                System.out.println("Fejl, medlem blev ikke fundet. ");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Der er ingen medlemmer med det indekstal");
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
            System.out.println("1. Butterfly");
            System.out.println("2. Backstroke");
            System.out.println("3. Freestyle");

            int choice = sc.nextInt();
            sc.nextLine();

            System.out.println("Ønskes konkurrencerekorder eller træningsrekorder?");
            System.out.println("1. Konkurrencerekorder");
            System.out.println("2. Træningsrekorder");

            int choice2 = sc.nextInt();
            sc.nextLine();

            if(choice2==1||choice2==2) {
                switch (choice) {
                    case 1 -> {
                        if(choice2==1) {
                            for (Swimmer s : list) {
                                if (s.getButterflyRecord() != 0)
                                    sortList.add(s);
                            }
                            sortList.sort(new ButterflySort());
                            for (int i = 0; i <= 4; i++) {
                                System.out.println(sortList.get(i));
                            }
                        }
                        else{
                            for (Swimmer s : list) {
                                if (s.results.getButterflyPracticeRecord() != 0)
                                    sortList.add(s);
                            }
                            sortList.sort(new ButterflyPracticeSort());
                            for (int i = 0; i <= 4; i++) {
                                System.out.println(sortList.get(i));
                            }
                        }
                    }
                    case 2 -> {
                        if(choice2==1) {
                            for (Swimmer s : list) {
                                if (s.getBackstrokeRecord() != 0)
                                    sortList.add(s);
                            }
                            sortList.sort(new BackstrokeSort());
                            for (int i = 0; i <= 4; i++) {
                                System.out.println(sortList.get(i));
                            }
                        }
                        else{
                            for (Swimmer s : list) {
                                if (s.results.getBackstrokePracticeRecord() != 0)
                                    sortList.add(s);
                            }
                            sortList.sort(new BackstrokePracticeSort());
                            for (int i = 0; i <= 4; i++) {
                                System.out.println(sortList.get(i));
                            }
                        }
                    }
                    case 3 -> {
                        if(choice2==1) {
                            for (Swimmer s : list) {
                                if (s.getFreestyleRecord() != 0)
                                    sortList.add(s);
                            }
                            sortList.sort(new FreestyleSort());
                            for (int i = 0; i <= 4; i++) {
                                System.out.println(sortList.get(i));
                            }
                        }
                        else{
                            for (Swimmer s : list) {
                                if (s.results.getFreestylePracticeRecord() != 0)
                                    sortList.add(s);
                            }
                            sortList.sort(new FreestylePracticeSort());
                            for (int i = 0; i <= 4; i++) {
                                System.out.println(sortList.get(i));
                            }
                        }
                    }
                    default -> System.out.println("Fejl: ugyldigt valg af ønsket disciplin.");
                }
            }
            else{
                System.out.println("Fejl: ugyldigt valg af ønsket rekordtype.");
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
                    System.out.println(trainerList.indexOf(t)+". "+t);
            }

            System.out.println();
            System.out.println("Vælg den træner, du ønsker at fjerne.");
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
            System.out.println("Der er ingen træner med det indekstal");
        }
        catch (Exception e) {
            System.out.println("En fejl er opstået: " + e.getMessage());
            e.printStackTrace();
        }
    }
}