package kalah;

import java.util.ArrayList;

public class Player {
    ArrayList<House> _houses;
    Store _store;
    int _score;

    public void setHouses(ArrayList houses) {
        _houses = houses;
    }

    public void setStore(Store store) {
        _store = store;
    }

    private void calculateFinalScore(){
        for (House h: _houses){
            _score += h.getSeeds();
        }
        _score += _store.getSeeds();
    }

    int getFinalScore(){
        _score = 0;
        calculateFinalScore();
        return _score;
    }
}
