package logica;

import java.util.ArrayList;

public class Nodo {
    private String hijo;
    private String[] _nombreTabla;
    private ArrayList<String[]> _datosTabla;
    private Nodo padre;
    private String _nombre;
    public Nodo(ArrayList<String[]> d, String [] tabla, String h, String nombre){
        _datosTabla = d;
        _nombreTabla = tabla;
        hijo = h;
        padre = null;
        _nombre = nombre;
    }

    public String get_nombre() {
        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public ArrayList<String[]> get_datosTabla() {
        return _datosTabla;
    }

    public Nodo getPadre() {
        return padre;
    }

    public String getHijo() {
        return hijo;
    }

    public String[] get_nombreTabla() {
        return _nombreTabla;
    }

    public void set_datosTabla(ArrayList<String[]> _datosTabla) {
        this._datosTabla = _datosTabla;
    }

    public void set_nombreTabla(String[] _nombreTabla) {
        this._nombreTabla = _nombreTabla;
    }

    public void setHijo(String hijo) {
        this.hijo = hijo;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

}
