package logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CargarDatos {
    private String _ficheroAtributos ="AtributosEx2019.txt";//"AtributosEx2019.txt";;AtributosJuego
    private String _ficheroDatos  = "Ex2019.txt";//"Ex2019.txt";Juego
    private String[] _colName;
    private ArrayList<String[]> _datos;
    //pruebas


    public CargarDatos(){
        _colName = null;
        _datos = new ArrayList<>();
        try{
            cargaFicheroAtributos();
            if(_colName!=null){
                cargaFicheroDatos();
                if(!_datos.isEmpty()){
                   new ID3(_colName,_datos);
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
