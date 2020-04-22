package logica;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ID3 {
    private String[] _colName;
    private ArrayList<String[]> _datos;
    private String[] _nuevosColName;
    //pruebas

    private HashMap<String,Elementos> valores;
    private HashMap<String,Double> meritos;
    private ArrayList<Nodo> _nodosRestantes;
    private ArrayList<ArrayList<String>> _nombreDatos;
    private ArrayList<Nodo> nodos;
    private HashMap<String,ArrayList<String>> _mapaNombreDatos;
    private ArrayList<Nodo> auxiliar ;


    private ArrayList<ArrayList<String>> _posiblesRespuestas;
    private int _numRecursion;
    public ID3(String[] c, ArrayList<String []> d){
        _numRecursion = 0;
        _colName = c;
        _datos = d;valores = new HashMap<>();
        _nodosRestantes = new ArrayList<>();
        meritos = new HashMap<>();
        _mapaNombreDatos = new HashMap<>();
        _nombreDatos = new ArrayList<ArrayList<String>>();
        nodos = new ArrayList<>();
        _posiblesRespuestas = new ArrayList<ArrayList<String>>();
        primeraVuelta(null,_datos,_colName);
       /* for(int i = 0; i < _posiblesRespuestas.size();i++){
            for(int j = 0 ; j < _posiblesRespuestas.get(i).size();j++){
                System.out.println("Posible RESPUESTAS: " + _posiblesRespuestas.get(i).get(j) +" ");
            }
        }*/
        while(recursion()){} //&& _numRecursion!=3){}
    }
    private void recursividad(Nodo n){
        if(n.getPadre()!=null){
            System.out.println(n.getPadre().get_nombre());
            recursividad(n.getPadre());
        }
    }
    private String  recuperarDato(Nodo n){
        if(n.get_datosTabla().get(0).length>1){
            return n.get_datosTabla().get(0)[n.get_datosTabla().get(0).length-1];
        }
        else
            if(n.get_datosTabla().get(0)[0]==null) return recuperarDato(n.getPadre());
            return n.get_datosTabla().get(0)[0];

    }
    private boolean recursion(){
        ArrayList<Nodo> auxNodo = new ArrayList<>();
        for(Nodo n: nodos){
            auxNodo.add(n);
        }
        nodos.clear();
        if(auxNodo.isEmpty())return false;
        else{

            _numRecursion++;
            for(Nodo n : auxNodo){
                String p;
                if(n.getPadre()==null){
                    p = "ES BROMA YO SOY EL PADRE";
                }
                else p = n.getPadre().get_nombre();
                System.out.println("SOY ITERACION " +_numRecursion +" con el Nodo de " + n.get_nombre() +" Y MI PADRE ES "+p);
                if(n.get_datosTabla().size()==1 && n.get_datosTabla().get(0).length==1){
                    if(n.get_datosTabla().get(0)[0]==null){
                        System.out.println("HAS LLEGADO AL FINAL Y EL RESULTADO ES " + recuperarDato(n.getPadre()));
                    }
                    else System.out.println("HAS LLEGADO AL FINAL Y EL RESULTADO ES "+ n.get_datosTabla().get(0)[0]);
                    System.out.println(n.get_nombre());
                    recursividad(n);
                }
                else primeraVuelta(n,n.get_datosTabla(),n.get_nombreTabla());
               /* for(int i = 0; i < _posiblesRespuestas.size();i++){
                    System.out.print("Posibles RESPUESTAS: ");
                    for(int j = 0 ; j < _posiblesRespuestas.get(i).size();j++){
                        System.out.print( _posiblesRespuestas.get(i).get(j) +" ");
                    }
                    System.out.println();
                }*/
            }
           // nodos.clear();
            //nodos = auxiliar;
            return true;
        }
    }
    private void primeraVuelta(Nodo nodo,  ArrayList<String[]> nDatos, String[] ncolName){

        _mapaNombreDatos = new HashMap<>();
        auxiliar = new ArrayList<>();
        meritos = new HashMap<>();
        for(int i = 0; i < ncolName.length-1;i++){
            valores = new HashMap<>();

            ArrayList<String> aux = new ArrayList<>();
            for(int j = 0; j < nDatos.size();j++){
                String  nombre = nDatos.get(j)[i];



                if(valores.containsKey(nombre)){
                    valores.get(nombre).setRep();
                }
                else{
                    aux.add(nombre);
                    valores.put(nombre,new Elementos(nombre));

                }
                String YorN = nDatos.get(j)[ncolName.length-1];
                if(YorN.equalsIgnoreCase("si")){
                    valores.get(nombre).setPos();
                }
                else{
                    valores.get(nombre).setNeg();
                    valores.put(nombre,valores.get(nombre));
                }

            }
            if(!_mapaNombreDatos.containsKey(ncolName[i])){
                _mapaNombreDatos.put(ncolName[i],aux);
            }
            Iterator<Map.Entry<String, Elementos>> it = valores.entrySet().iterator();
            Map.Entry<String,Elementos> entry = null;
            double merito = 0;
            while(it.hasNext()){
                entry = it.next();
                double a = valores.get(entry.getKey()).getRep();
                double b = nDatos.size();
                double r = a/b;
                valores.get(entry.getKey()).setR(r);
                int p = entry.getValue().getP();
                a = p/(double)valores.get(entry.getKey()).getRep();
                int n = entry.getValue().getN();
                b = n /(double)valores.get(entry.getKey()).getRep();
                double y = infor( a,  b);


                merito = r*y;
                valores.get(entry.getKey()).setMerito(merito);
                if(meritos.containsKey(ncolName[i])){
                    double w = merito + meritos.get(ncolName[i]);
                    meritos.put(ncolName[i],w);
                }
                else{
                    double w = merito;
                    meritos.put(ncolName[i],w);
                }
            }
            ArrayList<Elementos> x = new ArrayList<>();

            for(Elementos e : valores.values()){
                x.add(e);
            }
            ArrayList<String > ss = new ArrayList<>();
            for(String s : valores.keySet()){

                ss.add(s);
            }

            System.out.println(ncolName[i]);
            for(int z = 0; z < ss.size();z++){
                System.out.println(ss.get(z)+"\n"+ "Repeticiones: "+ x.get(z).getRep()+"\nPositivos: "+ x.get(z).getP()+"\nNegativos: "+x.get(z).getN()+"\nValor de R :"+ x.get(z).getR());
                System.out.println("Merito de "+ ss.get(z)+" : "+ x.get(z).getMerito());
            }
            System.out.println("Merito de la variable principal " + ncolName[i] +": " +meritos.get(ncolName[i]));
        }



        //aqui ya tendria que tener todos los datos

            double MeritoMin = Double.MAX_VALUE;
            Iterator<Map.Entry<String, Double>> it = meritos.entrySet().iterator();
            Map.Entry<String, Double> aux = null;
            String cabeza = null;
            while (it.hasNext()) {
                aux = it.next();
                if (aux.getValue() < MeritoMin) {
                    MeritoMin = aux.getValue();
                    cabeza = aux.getKey();
                }
            }
            System.out.println("La cabeza del arbol es: " + cabeza + " con un merito de : " + MeritoMin);

            _nuevosColName = new String[ncolName.length - 1];
            int cont = 0;
            for (int i = 0; i < ncolName.length; i++) {
                if (!cabeza.equalsIgnoreCase(ncolName[i])) {
                    _nuevosColName[cont] = ncolName[i];
                    cont++;
                }
            }
            ArrayList<String> da = _mapaNombreDatos.get(cabeza);
            for (int i = 0; i < da.size(); i++) {
                ArrayList<String[]> _datosNuevaTabla = new ArrayList<>();
                for (int j = 0; j < nDatos.size(); j++) {
                    String t = da.get(i);
                    //tengo que extraer la tabla de la cabeza
                    ArrayList<String> valor = _mapaNombreDatos.get(cabeza);

                    for (int z = 0; z < _nuevosColName.length; z++) {//for(int z = 0; z < valor.size();z++) {
                        String tt = nDatos.get(j)[z];
                        if (t.equalsIgnoreCase(tt)) {
                            String[] p = new String[nDatos.get(j).length - 1];
                            int c = 0;
                            for (int w = 0; w < nDatos.get(j).length; w++) {
                                if (!nDatos.get(j)[w].equalsIgnoreCase(tt)) {
                                    p[c] = nDatos.get(j)[w];
                                    c++;
                                }
                            }
                            _datosNuevaTabla.add(p);
                        }
                    }
                }
                ArrayList<String> _respuesta = new ArrayList<>();
                if (nodo != null) {
                    for (int t = 0; t < _posiblesRespuestas.size(); t++) {
                        for (int j = 0; j < _posiblesRespuestas.get(t).size(); j++) {
                            if (_posiblesRespuestas.get(t).get(j) == nodo.get_nombre()) {
                                _posiblesRespuestas.get(t).add(da.get(i));
                            }
                        }
                    }
                } else {
                    _respuesta.add(da.get(i));
                    _posiblesRespuestas.add(_respuesta);
                }
                Nodo nuevo = null;
                if (nodo == null) {
                    nuevo = new Nodo(_datosNuevaTabla, _nuevosColName, cabeza, da.get(i));
                } else {
                    nuevo = new Nodo(_datosNuevaTabla, _nuevosColName, cabeza, da.get(i), nodo);
                }
                nodos.add(nuevo);
                auxiliar.add(nuevo);
                _nodosRestantes.add(nuevo);
            }

            System.out.println("ARBOL: " + cabeza);
            for (int i = 0; i < auxiliar.size(); i++) {
                System.out.println("Ramificacion: " + i + ": " + auxiliar.get(i).get_nombre() + ":");
                System.out.println("-------- DATOS TABLA NUEVA RAMIFICACION " + i);
                for (int j = 0; j < auxiliar.get(i).get_datosTabla().size(); j++) {

                    for (int z = 0; z < auxiliar.get(i).get_datosTabla().get(j).length; z++) {
                        System.out.print(auxiliar.get(i).get_datosTabla().get(j)[z] + "  ");
                    }
                    System.out.println();
                }
            }

    }


    public static double log2(double n) {
        double x = Math.log(n)/Math.log(2);
        return x;
    }

    private double calculoInfo(double p){
        double x = p * log2(p);
        return x;
    }
    private double infor(double p, double n){
        if(p != 0 && n!=0){
            return -  calculoInfo(p) - calculoInfo(n);
        }
        return 0;
        /*if(p==0){
            return -  calculoInfo(n);
        }
          return -  calculoInfo(p);*/
    }
}
