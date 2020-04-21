package logica;

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
    private ArrayList<Nodo> _nodos;
    private ArrayList<ArrayList<String>> _nombreDatos;
    private ArrayList<Nodo> nodos;
    private HashMap<String,ArrayList<String>> _mapaNombreDatos;
    public ID3(String[] c, ArrayList<String []> d){
        _colName = c;
        _datos = d;
        valores = new HashMap<>();
        meritos = new HashMap<>();
        _mapaNombreDatos = new HashMap<>();
        _nombreDatos = new ArrayList<ArrayList<String>>();
        nodos = new ArrayList<>();
        primeraVuelta();
    }
    private void primeraVuelta(){
        for(int i = 0; i < _colName.length-1;i++){
            valores = new HashMap<>();
            ArrayList<String> aux = new ArrayList<>();
            for(int j = 0; j < _datos.size();j++){
                String  nombre = _datos.get(j)[i];



                if(valores.containsKey(nombre)){
                    valores.get(nombre).setRep();
                }
                else{
                    aux.add(nombre);
                    valores.put(nombre,new Elementos(nombre));

                }
                String YorN = _datos.get(j)[_colName.length-1];
                if(YorN.equalsIgnoreCase("si")){
                    valores.get(nombre).setPos();
                }
                else{
                    valores.get(nombre).setNeg();
                    valores.put(nombre,valores.get(nombre));
                }

            }
            if(!_mapaNombreDatos.containsKey(_colName[i])){
                _mapaNombreDatos.put(_colName[i],aux);
            }
            Iterator<Map.Entry<String, Elementos>> it = valores.entrySet().iterator();
            Map.Entry<String,Elementos> entry = null;
            double merito = 0;
            while(it.hasNext()){
                entry = it.next();
                double a = valores.get(entry.getKey()).getRep();
                double b = _datos.size();
                double r = a/b;
                valores.get(entry.getKey()).setR(r);
                int p = entry.getValue().getP();
                a = p/(double)valores.get(entry.getKey()).getRep();
                int n = entry.getValue().getN();
                b = n /(double)valores.get(entry.getKey()).getRep();
                double y = infor( a,  b);


                merito = r*y;
                valores.get(entry.getKey()).setMerito(merito);
                if(meritos.containsKey(_colName[i])){
                    double w = merito + meritos.get(_colName[i]);
                    meritos.put(_colName[i],w);
                }
                else{
                    double w = merito;
                    meritos.put(_colName[i],w);
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
            System.out.println(_colName[i]);
            for(int z = 0; z < ss.size();z++){
                System.out.println(ss.get(z)+"\n"+ "Repeticiones: "+ x.get(z).getRep()+"\nPositivos: "+ x.get(z).getP()+"\nNegativos: "+x.get(z).getN()+"\nValor de R :"+ x.get(z).getR());
                System.out.println("Merito de "+ ss.get(z)+" : "+ x.get(z).getMerito());
            }
            System.out.println("Merito de la variable principal " + _colName[i] +": " +meritos.get(_colName[i]));
        }
        //aqui ya tendria que tener todos los datos
        double MeritoMin = Double.MAX_VALUE;
        Iterator<Map.Entry<String,Double>> it = meritos.entrySet().iterator();
        Map.Entry<String,Double> aux = null;
        String cabeza = null;
        while(it.hasNext()){
            aux = it.next();
            if(aux.getValue() < MeritoMin){
                MeritoMin = aux.getValue();
                cabeza = aux.getKey();
            }
        }
        System.out.println("La cabeza del arbol es: "+ cabeza +" con un merito de : " + MeritoMin);

        _nuevosColName = new String[_colName.length-2];
        int cont = 0;
        for(int i = 0; i < _colName.length-1;i++){
            if(!cabeza.equalsIgnoreCase(_colName[i])){
                _nuevosColName[cont] = _colName[i];
                cont++;
            }
        }
        ArrayList<String> da = _mapaNombreDatos.get(cabeza);
        for(int i = 0; i < da.size();i++){
            ArrayList<String[]> _datosNuevaTabla = new ArrayList<>();
            for(int j = 0; j < _datos.size();j++){
                String t = da.get(i);
                String tt = _datos.get(j)[0];
                if(t.equalsIgnoreCase(tt)){
                    _datosNuevaTabla.add(_datos.get(j));
                }
            }
            Nodo nuevo = new Nodo(_datosNuevaTabla,_nuevosColName,cabeza,da.get(i));
            nodos.add(nuevo);
        }

        System.out.println("ARBOL: "+ cabeza);
        for(int i = 0; i < nodos.size();i++){
            System.out.println("Ramificacion: "+i+": "+ nodos.get(i).get_nombre()+":"); System.out.println("-------- DATOS TABLA NUEVA RAMIFICACION " + i);
            for(int j = 0; j < nodos.get(i).get_datosTabla().size();j++){

                for(int z = 0 ; z <nodos.get(i).get_datosTabla().get(j).length; z++){
                    System.out.print(nodos.get(i).get_datosTabla().get(j)[z]+"  ");
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
