package TP.Eventos;

import java.util.ArrayList;

public abstract class Evento {
    public double instante;

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

    public long factorial(long n){
        return n==0 ? 1 : n*factorial(n-1);
    }
    public boolean esVE(){
        return false;
    }
}
