import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Swimmer implements Comparable<Swimmer>{
    public static void main(String[] args) {

        //TEST//
        //TEST//

        ArrayList<Swimmer> swimmers = new ArrayList<>();
        swimmers.add(new Swimmer(true, "Swimmer1", LocalDate.of(1990, 1, 1), (short) 0, 25.5f, 30.2f, 20.3f, false, (short)0));
        swimmers.add(new Swimmer(true, "Swimmer2", LocalDate.of(1995, 5, 5), (short) 0, 22.3f, 28.1f, 18.7f, true, (short)0));
        swimmers.add(new Swimmer(true, "Swimmer3", LocalDate.of(1955, 10, 10), (short) 0, 24.0f, 29.8f, 19.5f, false, (short)0));

        // Print den unsortet liste
        System.out.println("Ikke sortet Swimmers:");
        for (Swimmer swimmer : swimmers) {
            System.out.println(swimmer);
        }

        // Sorter listen
        Collections.sort(swimmers);

        // Print den sortet liste
        System.out.println("\nSorteret Swimmers:");
        for (Swimmer swimmer : swimmers) {
            System.out.println(swimmer);
        }
        // Regner hvor meget Swimmers skal betale
        for (Swimmer swimmer : swimmers) {
            swimmer.calculateYearlyCharge();
        }

        // Printer liste
        System.out.println("\nOpdateret Swimmers:");
        for (Swimmer swimmer : swimmers) {
            System.out.println(swimmer);
        }
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
    Trainer trainer = null;
    short trainerIndex;
    private boolean competitionSwimmer;

    // Konstruktor til at oprette en Swimmer-objekt
    Swimmer( boolean isActive, String name, LocalDate birthday, short owedAmount,float butterflyRecord, float backstrokeRecord, float freestyleRecord, boolean competitionSwimmer, short trainerIndex){
        indexNo=noOfSwimmers;
        noOfSwimmers++;
        this.name = name;
        this.birthday = birthday;
        this.age=calculateAge(birthday);
        this.senior=(this.age >= 18);
        this.isActive = isActive;
        this.owedAmount=owedAmount;
        this.butterflyRecord=butterflyRecord;
        this.backstrokeRecord=backstrokeRecord;
        this.freestyleRecord=freestyleRecord;
        this.trainerIndex=trainerIndex;
        this.competitionSwimmer=competitionSwimmer;
    }

    // Metode til at returnere leaselig tekst
    public String toString(){
        return name+", "+age+", "+birthday+", "+senior+", "+(isActive? "Active" : "Inactive")+"\nButterfly rekord: "+butterflyRecord+"\nBackStroke rekord: "+backstrokeRecord+"\nFreeStyle rekord: "+freestyleRecord;
    }
    public String fileOutput(){
        return isActive+","+name+","+birthday+","+owedAmount+","+butterflyRecord+","+backstrokeRecord+","+freestyleRecord+","+competitionSwimmer+","+trainerIndex;
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
            System.out.println("Betaling registreret. Resterende beløb: " + owedAmount);
        } else {
            System.out.println("Ugyldig beløb. Det skyldte beløb er: " + owedAmount);
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
        // Compare Butterfly rekorder
        int butterflyComparison = Float.compare(this.butterflyRecord, swimmer.butterflyRecord);
        if (butterflyComparison != 0) {
            return butterflyComparison;
        }

        // Compare Backstroke rekorder
        int backstrokeComparison = Float.compare(this.backstrokeRecord, swimmer.backstrokeRecord);
        if (backstrokeComparison != 0) {
            return backstrokeComparison;
        }

        // Compare Freestyle recorder
        return Float.compare(this.freestyleRecord, swimmer.freestyleRecord);
    }
    public void calculateYearlyCharge() {
        int baseCharge;

        if (age < 18) {
            baseCharge = 1000;
        } else if (age <= 60) {
            baseCharge = 1600;
        } else {
            // Hvis du er over 60
            baseCharge = (int) (0.75 * 1600);
        }

        // Juster pris hvis du ikke er aktiv
        int yearlyCharge = isActive ? baseCharge : 500;

        // Tilføj priserne til owedAmount
        addCharge((short) yearlyCharge);

        System.out.println("Årlig kontingent for " + name + ": " + yearlyCharge);
    }

    // Metode til at modtage navn på enkelt svømmer i liste.
    public String getName(){
        return name;
    }
    // Metode til at redigere navn på enkelt svømmer i liste.
    public void setName(String newName){
        this.name = newName;
    }
    // Metode til at redigere fødselsdato på enkelt svømmer i liste.
    public void setBirthdate(LocalDate newBirthdate){
        this.birthday = newBirthdate;
    }
    // Metode til at redigere aktivt medlemskab på enkelt svømmer i liste.
    public void setActiveMember(boolean activeMember){
        this.isActive=activeMember;
    }
    public void setCompetitionSwimmer(boolean competitionswimmer){
        this.competitionSwimmer=competitionswimmer;
    }
    public float getOwedAmount(){
        return this.owedAmount;
    }
    public float getButterflyRecord(){
        return this.butterflyRecord;
    }
    public float getBackstrokeRecord(){
        return this.backstrokeRecord;
    }
    public float getFreestyleRecord(){
        return this.freestyleRecord;
    }
    public void resetIndexNos(ArrayList<Swimmer> list){
        Swimmer.noOfSwimmers=0;
        for(Swimmer s:list){
            s.indexNo=noOfSwimmers;
            noOfSwimmers++;
        }
    }
}

