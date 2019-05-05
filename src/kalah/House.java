package kalah;

public class House extends Pit {

    public void setupHouse (int seeds){
        _seeds = seeds;
    }
    void updateHouse(){
        _seeds += 1;
    }
}
