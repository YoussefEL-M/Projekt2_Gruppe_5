import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;

public class Swimmer implements Comparable<Swimmer>{
    private static short noOfSwimmers = 0;
    private short indexNo;
    private boolean isActive;
    private String name;
    final private int age;
    private boolean senior;
    private short owedAmount;
    private float butterflyRecord;
    private float backstrokeRecord;
    private float freestyleRecord;
    private LocalDate birthday;
    Trainer trainer = null;
    short trainerIndex;
    private boolean competitionSwimmer;
    byte butterflyPlacement=0;
    byte backstrokePlacement = 0;
    byte freestylePlacement= 0;
    private LocalDate lastChargeDate;

    // Konstruktor til at oprette en Swimmer-objekt
    Swimmer( boolean isActive, String name, LocalDate birthday, short owedAmount,float butterflyRecord, float backstrokeRecord, float freestyleRecord, boolean competitionSwimmer, short trainerIndex, byte butterflyPlacement, byte backstrokePlacement, byte freestylePlacement){
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
        this.butterflyPlacement=butterflyPlacement;
        this.backstrokePlacement=backstrokePlacement;
        this.freestylePlacement=freestylePlacement;
    }

    // Metode til at returnere leaselig tekst
    public String toString(){
        return name+", "+age+", "+birthday+", "+(senior? "Senior" : "Junior")+", "+(isActive? "Active" : "Inactive")+", skylder: "+owedAmount+"\nButterfly rekord: "+butterflyRecord+" Placering: "+butterflyPlacement+"\nBackStroke rekord: "+backstrokeRecord+" Placering: "+backstrokePlacement+"\nFreeStyle rekord: "+freestyleRecord+" Placering: "+freestylePlacement;
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
            owedAmount -= (short) amount;
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
    void setPlacement(byte butterflyPlacement, byte backstrokePlacement, byte freestylePlacement){
        this.butterflyPlacement = butterflyPlacement;
        this.backstrokePlacement = backstrokePlacement;
        this.freestylePlacement = freestylePlacement;
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
    private boolean hasYearPassed(LocalDate lastChargeDate) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(lastChargeDate, currentDate).getYears() >= 1;
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

        // Juster pris i forhold til aktiv eller passiv
        int yearlyCharge = isActive ? baseCharge : 500;

        // Tjek om der er gået et år efter sidste betalin
        if (lastChargeDate == null || hasYearPassed(lastChargeDate)) {

            // Tilføj beløb til owed amount
            addCharge((short) yearlyCharge);

            // Opdater lastChargeDate til at være efter betaling
            lastChargeDate = LocalDate.now();

            System.out.println("Årlig kontingent tilføjet til " + name + ": " + yearlyCharge);
        } else {
            System.out.println( name+", har betalt indenfor et år. Sidst betalt: "+lastChargeDate);
        }
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
    public byte getButterflyPlacement(){
        return this.butterflyPlacement;
    }
    public byte getBackstrokePlacement() {
        return backstrokePlacement;
    }
    public byte getFreestylePlacement() {
        return freestylePlacement;
    }

    public void resetIndexNos(ArrayList<Swimmer> list){
        Swimmer.noOfSwimmers=0;
        for(Swimmer s:list){
            s.indexNo=noOfSwimmers;
            noOfSwimmers++;
        }
    }
}

class BackstrokeSort implements Comparator<Swimmer> {
    public int compare(Swimmer a, Swimmer b){
        Float fa = a.getBackstrokeRecord();
        Float fb = b.getBackstrokeRecord();
        return fa.compareTo(fb);
    }
}
class ButterflySort implements Comparator<Swimmer> {
    public int compare(Swimmer a, Swimmer b){
        Float fa = a.getButterflyRecord();
        Float fb = b.getButterflyRecord();
        return fa.compareTo(fb);
    }
}
class FreestyleSort implements Comparator<Swimmer> {
    public int compare(Swimmer a, Swimmer b){
        Float fa = a.getFreestyleRecord();
        Float fb = b.getFreestyleRecord();
        return fa.compareTo(fb);
    }
}