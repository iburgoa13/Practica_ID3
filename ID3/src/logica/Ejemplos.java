package logica;

import java.util.ArrayList;

public class Ejemplos {

    private ArrayList<ArrayList<String>> _ejemplo;

    public Ejemplos(){
        _ejemplo = new ArrayList<ArrayList<String>>();
    }

    public void addEjemplo(String[] d){
        ArrayList<String> aux = new ArrayList<>();
        for(String s : d){
            aux.add(s);
        }
        _ejemplo.add(aux);
    }

    public void delete(int index){
        _ejemplo.remove(index);
    }

    public ArrayList<ArrayList<String>> get_ejemplo(){
        return _ejemplo;
    }
    public void addEjemplo(ArrayList<String >d){
        _ejemplo.add(d);
    }
}
