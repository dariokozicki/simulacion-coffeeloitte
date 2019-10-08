package TP.Eventos;

import TP.*;
import java.util.ArrayList;
import java.util.Random;

public class TomarCafe extends Evento {
    public static Random random = new Random();
    public int cantidadTazas;

    public TomarCafe(double instante){
        super(instante);
        inicializarTazas();
    }

    public void inicializarTazas(){
        double y;
        double x;
        double Maximo = 0.26686;
        do{
            x = random.nextInt(7);
            y = Math.random()*Maximo;
        }while(y>CTC(x));
        cantidadTazas = (int) x;
    }
    public double CTC(double x){
        return  (factorial(14)*Math.pow(0.18558,x)*Math.pow(1-0.18558,14-x))/(factorial((int) x)*factorial((int) (14-x)));
    }
    public double IA(){
        return Math.random()*(1-0.5) + 0.5;
    }
    public void determinarEvento() {
        if(CoffeLoitte.stockActual==0){
            CoffeLoitte.cantidadDeEmpleadosSinCafe++;
        }
    }

    public int actualizarVariableEstado() {
        return -cantidadTazas;
    }

    public ArrayList<Evento> eventosFuturosNoCondicionados() {
        ArrayList<Evento> eventos = new ArrayList<>();
        eventos.add(new TomarCafe(CoffeLoitte.instanteActual + IA()));
        return eventos;
    }

    public ArrayList<Evento> eventosFuturosCondicionados() {
        ArrayList<Evento> eventos = new ArrayList<>();
        if (CoffeLoitte.stockActual==0 && !CoffeLoitte.eventosFuturos.stream().anyMatch(e->e.esVE())) {
            CoffeLoitte.cantidadDeEmpleadosSinCafe++;
            eventos.add(new VisitaExcepcional(CoffeLoitte.instanteActual + 2));
        }
        return eventos;
    }
}
