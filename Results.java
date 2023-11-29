import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

public class Results {
    private byte butterflyPlacement;
    private byte backstrokePlacement;
    private byte freestylePlacement;
    private float butterflyRecord;
    private float backstrokeRecord;
    private float freestyleRecord;
    ArrayList<SwimMeet> list;
    float getButterflyRecord(){
        return butterflyRecord;
    }
    float getBackstrokeRecord(){
        return backstrokeRecord;
    }
    float getFreestyleRecord(){
        return freestyleRecord;
    }
    byte getButterflyPlacement(){
        return butterflyPlacement;
    }
    byte getBackstrokePlacement(){
        return backstrokePlacement;
    }
    byte getFreestylePlacement(){
        return freestylePlacement;
    }
    Results(ArrayList<SwimMeet> list){

    }
}
enum Discipline{Butterfly,Backstroke,Freestyle}
class SwimMeet implements Comparable<SwimMeet>{
    String meetName;
    LocalDate date;
    Discipline discipline;
    float time;
    byte placement;
    public int compareTo(SwimMeet s){
        Float a = this.time;
        Float b = s.time;
        return a.compareTo(b);
    }
}

class placementComparison implements Comparator<SwimMeet>{
    public int compare(SwimMeet a, SwimMeet b){
        Byte ba = a.placement;
        Byte bb = b.placement;
        return ba.compareTo(bb);
    }
}