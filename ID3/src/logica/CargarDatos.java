package logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CargarDatos {
    private String _ficheroAtributos ="AtributosJuego.txt";//"AtributosEx2019.txt";;AtributosJuego;;AtributosTO;
    private String _ficheroDatos  = "Juego.txt";//"Ex2019.txt";Juego;TO; EJIC;
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

                   new ID3(_colName,_datos);
                }
                else throw new IOException("Ficheros no encontrados");
            }
            else throw new IOException("Ficheros no encontrados");
        }
        catch (IOException i){
            System.out.println(i.getMessage());
        }
    }

    private void cargaFicheroDatos() throws IOException {
        String cadena;
        FileReader f = new FileReader(_ficheroDatos);
        BufferedReader b = new BufferedReader(f);
        while ((cadena = b.readLine()) != null) {
            String[] aux = cadena.split(",");
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
