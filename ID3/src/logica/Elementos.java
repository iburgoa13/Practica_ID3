package logica;

public class Elementos {
    private String _nombre;
    private int _rep;
    private int n,p;
    private double r,merito;
    public Elementos(String ne){
        _nombre = ne;
        _rep = 1;
        n = 0;
        p = 0;
        r = 0;
        merito = 0;
    }
    public void setMerito(double s){
        merito = s;
    }
    public double getMerito(){return merito;}
    public void setRep(){
        _rep++;
    }
    public int getRep(){return _rep;}

    public int getN() {
        return n;
    }
    public double getR(){
        return r;
    }
    public void setR(double _R){
        r = _R;
    }
    public int getP() {
        return p;
    }

    public void setNeg(){
        n++;
    }
    public void setPos(){
        p++;
    }
}
