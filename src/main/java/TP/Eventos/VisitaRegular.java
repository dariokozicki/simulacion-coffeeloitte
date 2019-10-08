package TP.Eventos;

import TP.CoffeLoitte;

import java.util.ArrayList;

public class VisitaRegular extends Evento {

    public VisitaRegular(double instante) {
        super(instante);
    }

    public void determinarEvento() {
        CoffeLoitte.costoTotal += 13.65*(CoffeLoitte.stockTotal-CoffeLoitte.stockActual);
    }

    public int actualizarVariableEstado() {
        return CoffeLoitte.stockTotal;
    }

    public ArrayList<Evento> eventosFuturosNoCondicionados() {
        ArrayList<Evento> eventos = new ArrayList<>();
        eventos.add(new VisitaRegular(CoffeLoitte.instanteActual + CoffeLoitte.frecuenciaVisitaRegular));
        return eventos;
    }

    public ArrayList<Evento> eventosFuturosCondicionados() {
        return new ArrayList<>();
    }
}
