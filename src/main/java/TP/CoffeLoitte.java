package TP;

import TP.Eventos.Evento;
import TP.Eventos.TomarCafe;
import TP.Eventos.VisitaRegular;

import java.util.ArrayList;

public class CoffeLoitte {

    public static double frecuenciaVisitaRegular;
    public static int stockTotal;
    public static int stockActual;
    public static double instanteFinal;
    public static Evento eventoActual;
    public static double instanteActual;
    public static ArrayList<Evento> eventosFuturos;
    public static double costoTotal=0;
    public static double cantidadDeEmpleadosSinCafe=0;
    public static double cantidadDeEmpleadosMensual;
    public static double costoMensual;

    public static void main (String []args ){
        fijarCondicionesIniciales();
        while(!isOver()) {
            eventoActual = getProximoEvento();
            avanzarTiempoHasta(eventoActual.instante);
            eventoActual.determinarEvento();
            eventosFuturos.addAll(eventoActual.eventosFuturosNoCondicionados());
            actualizarVariableEstado();
            eventosFuturos.addAll(eventoActual.eventosFuturosCondicionados());
            imprimirInformacionActual();
        }
        calcularResultados();
        imprimirResultados();
    }

    public static void imprimirInformacionActual(){
        System.out.println("Estoy en el evento: " + eventoActual);
        System.out.println("La variable de estado es: " + stockActual);
        System.out.println("La hora actual es: " + instanteActual);
    }
    public static void actualizarVariableEstado(){
        stockActual = Math.min(Math.max(stockActual + eventoActual.actualizarVariableEstado(), 0),stockTotal);
    }
    public static void fijarCondicionesIniciales(){
        eventosFuturos = new ArrayList<>();
        eventosFuturos.add(new TomarCafe(0));
        frecuenciaVisitaRegular = 40;
        stockTotal = 190;
        eventosFuturos.add(new VisitaRegular(frecuenciaVisitaRegular));
        instanteFinal = 19200; //diez años laborales
        stockActual = stockTotal;
    }
    public static Evento getProximoEvento(){
        Evento evento = eventosFuturos.stream().reduce((anterior,actual)->elMenor(anterior,actual)).get();
        eventosFuturos.remove(evento);
        return evento;
    }
    public static void avanzarTiempoHasta(double instante){
        instanteActual = instante;
    }
    public static Evento elMenor(Evento anterior, Evento actual){
        return anterior.getInstante() >= actual.getInstante() ? actual:anterior;
    }
    public static boolean isOver(){
        return instanteActual>=instanteFinal;
    }
    public static void calcularResultados(){
        costoMensual = 160*costoTotal/instanteFinal;
        cantidadDeEmpleadosMensual = 160*cantidadDeEmpleadosSinCafe/instanteFinal;
    }
    public static void imprimirResultados(){
        System.out.println("Simulacion terminada.");
        System.out.println("StockTotal en tazas = " + stockTotal + "  \nFrecuencia de restock en horas laborales= " + (long) frecuenciaVisitaRegular);
        System.out.println("El costo total en pesos a lo largo de " + instanteFinal + " horas laborales fue " + (long) costoTotal);
        System.out.println("En este periodo, los empleados se quedaron sin cafe " + (long)cantidadDeEmpleadosSinCafe + " veces.");
        System.out.println("El costo mensual en pesos es de: "+(long)costoMensual);
        System.out.println("En este periodo, los empleados se quedaron sin cafe " + (long) cantidadDeEmpleadosMensual + " veces.");
    }
}
