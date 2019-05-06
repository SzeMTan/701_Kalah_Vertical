package kalah;

public class Store extends Pit {
    int _seeds;
    Player _player;

    void setupStore(int seeds){
        _seeds = seeds;
    }

    void setPlayer(Player player){
        _player = player;
    }

}
