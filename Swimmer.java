import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;

public class Swimmer{
    private static short noOfSwimmers = 0;
    private short indexNo;
    private boolean isActive;
    private String name;
    final private int age;
    private final boolean senior;
    private short owedAmount;
    private LocalDate birthday;
    private Trainer trainer;
    short trainerIndex;
    private boolean competitionSwimmer;
    private LocalDate lastChargeDate;
    Results results;


    // Konstruktor til at oprette en Swimmer-objekt
    Swimmer(boolean isActive, String name, LocalDate birthday, short owedAmount, boolean competitionSwimmer, short trainerIndex,Results results, LocalDate lastChargeDate){
        indexNo=noOfSwimmers;
        noOfSwimmers++;
        this.name = name;
        this.birthday = birthday;
        this.age=calculateAge(birthday);
        this.senior=(this.age >= 18);
        this.isActive = isActive;
        this.owedAmount=owedAmount;
        this.trainerIndex=trainerIndex;
        this.competitionSwimmer=competitionSwimmer;
        this.results=results;
        this.lastChargeDate=lastChargeDate;
    }

    // Metode til at returnere leaselig tekst
    public String toString(){
        String trainerInfo = (trainerIndex == -1) ? ", Ingen Træner" : ", Træner: " + Trainer.getTrainerByIndex(FileManager.getTrainers(), trainerIndex).getName();
        return indexNo+". "+name+", "+age+", "+birthday+", "+(senior? "Senior" : "Junior")+", "+(isActive? "Aktiv" : "Ikke Aktiv")+", Skylder: "+owedAmount+" kr. "+(lastChargeDate != null ? "- Sidste betalingsdato: "+lastChargeDate.toString() : "" )+trainerInfo+"\nRekorderder for øvelse: "+"\nButterfly rekord:"+results.getButterflyPracticeRecord()+"\nBackstroke rekord: "+results.getBackstrokePracticeRecord()+"\nFreestyle rekorder "+results.getFreestylePracticeRecord()+"\nRekorder og placering for konkurrencer"+"\nButterfly rekord: "+results.getButterflyRecord()+" Placering: "+results.getButterflyPlacement()+"\nBackStroke rekord: "+results.getBackstrokeRecord()+" Placering: "+results.getBackstrokePlacement()+"\nFreeStyle rekord: "+results.getFreestyleRecord()+" Placering: "+results.getFreestylePlacement();
    }
    public String fileOutput(){
        return isActive+","+name+","+birthday+","+owedAmount+","+competitionSwimmer+","+trainerIndex+","+lastChargeDate+","+results.getButterflyPracticeRecord()+","+results.getBackstrokePracticeRecord()+","+results.getFreestylePracticeRecord();
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
    // Metode til at modtage fødselsdag på enkelt svømmer i liste.
    public LocalDate getBirthday() {
        return birthday;
    }
    // Metode til at redigere aktivt medlemskab på enkelt svømmer i liste.
    public void setActiveMember(boolean activeMember){
        this.isActive=activeMember;
    }
    // Metode til at redigere konkurrencesvømmer medlemskab på enkelt svømmer i liste.
    public void setCompetitionSwimmer(boolean competitionswimmer){
        this.competitionSwimmer=competitionswimmer;
    }
    // Metode til at modtage skyldige beløv på enkelt svømmer i liste.
    public float getOwedAmount(){
        return this.owedAmount;
    }
    // Metode til at modtage butterfly rekord på enkelt svømmer i liste.
    public float getButterflyRecord(){
        return this.results.getButterflyRecord();
    }
    // Metode til at modtage backstroke rekord på enkelt svømmer i liste.
    public float getBackstrokeRecord(){
        return this.results.getBackstrokeRecord();
    }
    // Metode til at modtage freestyle rekord på enkelt svømmer i liste.
    public float getFreestyleRecord(){
        return this.results.getFreestyleRecord();
    }
    // Metode til at resette indeks numre i liste. Bruges ved fjernelse af svømmer mfl.
    public static void resetIndexNos(ArrayList<Swimmer> list){
        Swimmer.noOfSwimmers=0;
        for(Swimmer s:list){
            s.indexNo=noOfSwimmers;
            noOfSwimmers++;
        }
    }
    // Metode til at tildele træner til enkelt svømmer i liste.
    public void setTrainer(Trainer trainer) {
        this.trainer= trainer;
    }
    // Metode til at tildele træner indeks.
    public void setTrainerIndex(short trainerIndex) {
        this.trainerIndex = trainerIndex;
    }
}
// Klasse der bruges til at sammenligne rekorder - bruges i DisplayTopFive.
class BackstrokeSort implements Comparator<Swimmer> {
    public int compare(Swimmer a, Swimmer b){
        Float fa = a.getBackstrokeRecord();
        Float fb = b.getBackstrokeRecord();
        return fa.compareTo(fb);
    }
}
// Klasse der bruges til at sammenligne rekorder - bruges i DisplayTopFive.
class BackstrokePracticeSort implements Comparator<Swimmer> {
    public int compare(Swimmer a, Swimmer b){
        Float fa = a.results.getBackstrokePracticeRecord();
        Float fb = b.results.getBackstrokePracticeRecord();
        return fa.compareTo(fb);
    }
}
// Klasse der bruges til at sammenligne rekorder - bruges i DisplayTopFive.
class ButterflySort implements Comparator<Swimmer> {
    public int compare(Swimmer a, Swimmer b){
        Float fa = a.results.getButterflyRecord();
        Float fb = b.results.getButterflyRecord();
        return fa.compareTo(fb);
    }
}
// Klasse der bruges til at sammenligne rekorder - bruges i DisplayTopFive.
class ButterflyPracticeSort implements Comparator<Swimmer> {
    public int compare(Swimmer a, Swimmer b){
        Float fa = a.results.getButterflyPracticeRecord();
        Float fb = b.results.getButterflyPracticeRecord();
        return fa.compareTo(fb);
    }
}
// Klasse der bruges til at sammenligne rekorder - bruges i DisplayTopFive.
class FreestyleSort implements Comparator<Swimmer> {
    public int compare(Swimmer a, Swimmer b){
        Float fa = a.getFreestyleRecord();
        Float fb = b.getFreestyleRecord();
        return fa.compareTo(fb);
    }
}
// Klasse der bruges til at sammenligne rekorder - bruges i DisplayTopFive.
class FreestylePracticeSort implements Comparator<Swimmer> {
    public int compare(Swimmer a, Swimmer b){
        Float fa = a.results.getFreestylePracticeRecord();
        Float fb = b.results.getFreestylePracticeRecord();
        return fa.compareTo(fb);
    }
}