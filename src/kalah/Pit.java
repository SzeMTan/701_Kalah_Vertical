package kalah;

public class Pit {

    int _seeds;

    void setUp(int seeds) {
        _seeds = seeds;
    }
    void addSeeds(int seeds){
        _seeds += seeds;
    }

    int getSeeds(){
        return _seeds;
    }
}
