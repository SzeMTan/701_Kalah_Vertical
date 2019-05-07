package kalah;

import java.util.ArrayList;

public class Player {
    private ArrayList<House> _houses;
    private Store _store;
    private int _score;
    private int _id;

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

    public ArrayList<House> getHouses(){
        return _houses;
    }

    public Store getStore() {
        return _store;
    }

    public int getId() {
        return _id;
    }

    void setId(int id){
        _id = id;
    }

    boolean checkIfHousesAreEmpty(){
        boolean isEmpty = false;
        int allSeeds = 0;
        for (House h: this.getHouses()){
            allSeeds += h.getSeeds();
        }
        if (allSeeds == 0){
            isEmpty = true;
        }
        return isEmpty;
    }
}
