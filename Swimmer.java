import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;

public class Swimmer implements Comparable<Swimmer>{
    public static void main(String[] args) { //TEST//
        // Laver en ArrayListe og Swimmer objekter
        ArrayList<Swimmer> swimmers = new ArrayList<>();

        // Tilfoj Swimmer objekter til listen
        swimmers.add(new Swimmer("Greg Gregory", LocalDate.of(1995, 7, 31), true));
        swimmers.add(new Swimmer("Billy Bob", LocalDate.of(2010, 1, 10), false));

        // Set rekorder for hver svømmer for hver disciplin
        for (Swimmer swimmer : swimmers) {
            swimmer.setRecord(18.7f, 69.1f, 30.4f);
        }

        // Print den usorteret liste af swimmers
        System.out.println("Unsorted list of swimmers:");
        for (Swimmer swimmer : swimmers) {
            System.out.println(swimmer);
        }

        // Sorter listen af swimmers
        Collections.sort(swimmers);

        // Print den sorteret liste af swimmers
        System.out.println("\nSorted list of swimmers based on records:");
        for (Swimmer swimmer : swimmers) {
            System.out.println(swimmer);
        }
        // Test addCharge og registerPayment metoder
        Swimmer testSwimmer = new Swimmer("Greggg Gregoryyy", LocalDate.of(1995, 7, 3), true);
        System.out.println("\nTesting addCharge and registerPayment methods:");
        System.out.println("Initial owed amount: " + testSwimmer.owedAmount);
        testSwimmer.addCharge((short) 50);
        System.out.println("New owed amount: " + testSwimmer.owedAmount);
        testSwimmer.registerPayment(30.0f);
        System.out.println("Remaining amount: " + testSwimmer.owedAmount);

        // Test updateMemberType metode
        System.out.println("\nTesting updateMemberType method:");
        System.out.println("Member type before update: " + testSwimmer.memberType);
        testSwimmer.updateMemberType();
        System.out.println("Member type after update: " + testSwimmer.memberType);

        // Test setRecord og getRecord metoder
        System.out.println("\nTesting setRecord and getRecord methods:");
        testSwimmer.setRecord(22.0f, 28.5f, 18.7f);
        System.out.println("Butterfly record: " + testSwimmer.getRecord("butterfly"));
        System.out.println("Backstroke record: " + testSwimmer.getRecord("backstroke"));
        System.out.println("Freestyle record: " + testSwimmer.getRecord("freestyle"));
    }
    private static short noOfSwimmers = 0;
    private short indexNo;
    private boolean isActive;
    private String name;
    private int age;
    private boolean senior;
    private short owedAmount;
    private float butterflyRecord;
    private float backstrokeRecord;
    private float freestyleRecord;
    private LocalDate birthday;
    Trainer trainer;
    private boolean competitionSwimmer;

    // Konstruktor til at oprette en Swimmer-objekt
    Swimmer( boolean isActive, String name, LocalDate birthday, short owedAmount,float butterflyRecord, float backstrokeRecord, float freestyleRecord, Trainer trainer, boolean competitionSwimmer){
        indexNo=noOfSwimmers;
        noOfSwimmers++;
        this.name = name;
        this.birthday = birthday;
        this.age=calculateAge(birthday);
        this.memberType="Junior";
        this.isActive = isActive;
        this.owedAmount=owedAmount;
        this.butterflyRecord=butterflyRecord;
        this.backstrokeRecord=backstrokeRecord;
        this.freestyleRecord=freestyleRecord;
        this.trainer=trainer;
        this.competitionSwimmer=competitionSwimmer;
    }

    // Metode til at returnere leaselig tekst
    public String toString(){
        return name+", "+age+", "+birthday+", "+senior+", "+isActive+"\nButterfly rekord: "+butterflyRecord+"\nBackStroke rekord: "+backstrokeRecord+"\nFreeStyle rekord: "+freestyleRecord;
    }
    private int calculateAge(LocalDate birthday) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthday, currentDate).getYears();
    }

    // Metode til at tilfoje en afgift til det skyldige belob
    void addCharge(short amount){
        owedAmount+=amount;
    }

    // Metode til at registrere en betaling og opdatere det skyldige belob
    void registerPayment(float amount){
        if (amount <= owedAmount) {
            owedAmount -= amount;
            System.out.println("Payment registered. Remaining amount: " + owedAmount);
        } else {
            System.out.println("Invalid payment amount. The owed amount is: " + owedAmount);
        }
    }

    // Metode til at opdatere medlemstypen baseret paa alder
    void updateMemberType(){
        if (age >= 18) {
            senior = true;
            memberType = "Senior";
        } else {
            senior = false;
            memberType = "Junior";
        }
    }

    // Metode til at angive rekorder for de forskellige svomme discipliner
    void setRecord(float butterflyRecord, float backstrokeRecord, float freestlyeRecord){
        this.butterflyRecord = butterflyRecord;
        this.backstrokeRecord = backstrokeRecord;
        this.freestyleRecord = freestlyeRecord;
    }

    // Metode til at hente en rekord for en given svomme disciplin
    float getRecord(String discipline){
        return switch (discipline.toLowerCase()) {
            case "butterfly" -> butterflyRecord;
            case "backstroke" -> backstrokeRecord;
            case "freestyle" -> freestyleRecord;
            default ->
                    throw new IllegalArgumentException("Ugyldig disciplin: " + discipline + ". Gyldige discipliner: butterfly, backstroke, freestyle");
        };
    }

    // Metode til at sammenligne svommeres rekorder
    public int compareTo(Swimmer swimmer){
        // Regn total rekorden ud for hver svommer
        float totalRecordsSwimmer = this.butterflyRecord + this.backstrokeRecord + this.freestyleRecord;
        float totalRecordsSwimmer2 = swimmer.butterflyRecord + swimmer.backstrokeRecord + swimmer.freestyleRecord;

        // Sammenlign deres rekorder
        return  Float.compare(totalRecordsSwimmer, totalRecordsSwimmer2);
    }
}
