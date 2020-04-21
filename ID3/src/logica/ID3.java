package logica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ID3 {
    private String[] _colName;
    private ArrayList<String[]> _datos;
    //pruebas

    private HashMap<String,Elementos> valores;
    private HashMap<String,Double> meritos;

    public ID3(String[] c, ArrayList<String []> d){
        _colName = c;
        _datos = d;
        valores = new HashMap<>();
        meritos = new HashMap<>();
        primeraVuelta();
    }
    private void primeraVuelta(){
        for(int i = 0; i < _colName.length-1;i++){
            valores = new HashMap<>();
            for(int j = 0; j < _datos.size();j++){
                String  nombre = _datos.get(j)[i];
                if(valores.containsKey(nombre)){
                    valores.get(nombre).setRep();
                }
                else{
                    valores.put(nombre,new Elementos(nombre));
                }
                String YorN = _datos.get(j)[_colName.length-1];
                if(YorN.equalsIgnoreCase("si")){
                    valores.get(nombre).setPos();
                    //valores.put(nombre,valores.get(nombre));
                }
                else{
                    valores.get(nombre).setNeg();
                    valores.put(nombre,valores.get(nombre));
                }
                // double r = valores.get(nombre).getRep()/ _datos.size();

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
                double sss;


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
                //valores.put(valores.get(entry.g));
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
