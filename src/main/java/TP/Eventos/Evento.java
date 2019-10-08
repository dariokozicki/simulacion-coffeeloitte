package TP.Eventos;

import java.util.ArrayList;

public abstract class Evento {
    public double instante;
    protected String nombre;

    public Evento(double instante) {
        this.instante = instante;
    }
    public double getInstante(){
        return instante;
    }
    public abstract void determinarEvento();
    public abstract int actualizarVariableEstado();
    public abstract ArrayList<Evento> eventosFuturosNoCondicionados();
    public abstract ArrayList<Evento> eventosFuturosCondicionados();
    @Override
    public String toString(){
        return nombre;
    }
    public long factorial(long n){
        return n==0 ? 1 : n*factorial(n-1);
    }
    public boolean esVE(){
        return false;
    }
}
