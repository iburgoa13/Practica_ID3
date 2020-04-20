package logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CargarDatos {
    private String _ficheroAtributos;
    private String _ficheroDatos;
    private String[] _colName;
    private ArrayList<String[]> _datos;

    public CargarDatos(){
        _colName = null;
        _datos = new ArrayList<>();
        try{
            cargaFicheroAtributos();
            if(_colName!=null){
                cargaFicheroDatos();
                if(!_datos.isEmpty()){

                }
                else throw new IOException("ii");
            }
            else throw new IOException("ii");
        }
        catch (IOException i){

        }
    }
    private void cargaFicheroDatos() throws IOException {
        String cadena;
        FileReader f = new FileReader(_ficheroDatos);
        BufferedReader b = new BufferedReader(f);
        while ((cadena = b.readLine()) != null) {
            String[] aux = cadena.split(",");
            // Para que no coja lineas en blanco
            if(!aux[0].equalsIgnoreCase(""))
                _datos.add(aux);
        }
        b.close();

    }
    private void cargaFicheroAtributos() throws IOException {
        String cadena;
        FileReader f = new FileReader(_ficheroAtributos);
        BufferedReader b = new BufferedReader(f);
        while ((cadena = b.readLine()) != null) {
            _colName = cadena.split(",");
        }
        b.close();

    }
}