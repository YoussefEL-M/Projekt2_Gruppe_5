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
        this.list=list;
        ArrayList<Float> sortListA = new ArrayList<>();
        ArrayList<Float> sortListB = new ArrayList<>();
        ArrayList<Float> sortListC = new ArrayList<>();

        //Uddeler tiderne efter disciplin og sorterer dem.

        for(SwimMeet sm: list){
            switch(sm.discipline){
                case Butterfly -> sortListA.add(sm.time);
                case Backstroke -> sortListB.add(sm.time);
                case Freestyle -> sortListC.add(sm.time);
            }
        }
        sortListA.sort(null);
        sortListB.sort(null);
        sortListC.sort(null);

        butterflyRecord=sortListA.get(0);
        backstrokeRecord=sortListB.get(0);
        freestyleRecord=sortListC.get(0);

        ArrayList<Byte> sortListD = new ArrayList<>();
        ArrayList<Byte> sortListE = new ArrayList<>();
        ArrayList<Byte> sortListF = new ArrayList<>();

        for(SwimMeet sm: list){
            switch(sm.discipline){
                case Butterfly -> sortListD.add(sm.placement);
                case Backstroke -> sortListE.add(sm.placement);
                case Freestyle -> sortListF.add(sm.placement);
            }
        }

        sortListD.sort(null);
        sortListE.sort(null);
        sortListF.sort(null);

        butterflyPlacement=sortListD.get(0);
        backstrokePlacement=sortListE.get(0);
        freestylePlacement=sortListF.get(0);
    }
    void updateRecords(){}
}
enum Discipline{Butterfly,Backstroke,Freestyle}
class SwimMeet{
    String meetName;
    LocalDate date;
    Discipline discipline;
    float time;
    byte placement;
    SwimMeet(String meetName,LocalDate date,Discipline discipline,float time, byte placement){
        this.meetName=meetName;
        this.date=date;
        this.discipline=discipline;
        this.time=time;
        this.placement=placement;
    }
}