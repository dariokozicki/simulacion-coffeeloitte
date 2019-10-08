package TP.Eventos;

import TP.CoffeLoitte;

import java.util.ArrayList;

public class VisitaExcepcional extends Evento {
    public VisitaExcepcional(double instante) {
        super(instante);
        nombre = "Visita Excepcional";
    }

    public void determinarEvento() {
        CoffeLoitte.costoTotal += 13.65*CoffeLoitte.stockTotal + 500;
    }

    public int actualizarVariableEstado() {
        return CoffeLoitte.stockTotal;
    }

    public ArrayList<Evento> eventosFuturosNoCondicionados() {
        return new ArrayList<>();
    }

    public ArrayList<Evento> eventosFuturosCondicionados() {
        return new ArrayList<>();
    }

    @Override
    public boolean esVE(){
        return true;
    }
}
