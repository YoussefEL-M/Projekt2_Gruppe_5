import java.util.ArrayList;

public class Trainer {
    String name;
    Trainer(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }

    static void assignTrainers(ArrayList<Trainer> trainerList, ArrayList<Swimmer> swimmerList){
        for(Swimmer s: swimmerList){
            if(s.trainerIndex>-1){
                s.trainer=trainerList.get(s.trainerIndex);
            }
        }
    }
}
