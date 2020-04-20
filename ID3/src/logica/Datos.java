package logica;

import java.util.ArrayList;

public class Datos {
    private String _hijo;
    private String[] _titulos;
    private ArrayList<String[]> _datos;
    private Datos _padre;

    public Datos(ArrayList<String[]> d, String h){
        _datos = d;
        _hijo = h;
        _padre = null;
    }

    public ArrayList<String[]> get_datos() {
        return _datos;
    }

    public Datos get_padre() {
        return _padre;
    }

    public String get_hijo() {
        return _hijo;
    }

    public String[] get_titulos() {
        return _titulos;
    }

    public void set_datos(ArrayList<String[]> _datos) {
        this._datos = _datos;
    }

    public void set_hijo(String _hijo) {
        this._hijo = _hijo;
    }

    public void set_padre(Datos _padre) {
        this._padre = _padre;
    }

    public void set_titulos(String[] _titulos) {
        this._titulos = _titulos;
    }

}
