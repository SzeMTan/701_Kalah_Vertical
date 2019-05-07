package kalah;

public class Pit {

    protected int _seeds;

    public void setUp(int seeds) {
        _seeds = seeds;
    }

    public void addSeeds(int seeds){
        _seeds += seeds;
    }

    public int getSeeds(){
        return _seeds;
    }
}
