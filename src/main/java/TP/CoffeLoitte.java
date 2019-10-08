package TP;

import TP.Eventos.Evento;
import TP.Eventos.TomarCafe;
import TP.Eventos.VisitaRegular;

import java.util.ArrayList;

public class CoffeLoitte {

    public static double frecuenciaVisitaRegular;
    public static final int stockTotal = 450;
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
            eventosFuturos.remove(eventoActual);
            System.out.println("Estoy en el evento: " + eventoActual);
            System.out.println("La variable de estado es: " + stockActual);
            System.out.println("La hora actual es: " + instanteActual);
            avanzarTiempoHasta(eventoActual.instante);
            eventoActual.determinarEvento();
            eventosFuturos.addAll(eventoActual.eventosFuturosNoCondicionados());
            stockActual = Math.min(Math.max(stockActual + eventoActual.actualizarVariableEstado(), 0),stockTotal);
            eventosFuturos.addAll(eventoActual.eventosFuturosCondicionados());
        }
        calcularResultados();
        imprimirResultados();
    }


    public static void fijarCondicionesIniciales(){
        eventosFuturos = new ArrayList<>();
        eventosFuturos.add(new TomarCafe(0));
        frecuenciaVisitaRegular = 40; //una semana
        eventosFuturos.add(new VisitaRegular(frecuenciaVisitaRegular));
        instanteFinal = 19200; //diez años laborales
        stockActual = stockTotal;
    }
    public static Evento getProximoEvento(){
        return eventosFuturos.stream().reduce((anterior,actual)->elMenor(anterior,actual)).get();
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
        System.out.println("StockTotal = " + stockTotal + " , Frecuencia de restock = " + (long) frecuenciaVisitaRegular);
        System.out.println("El costo total a lo largo de " + instanteFinal + " horas laborales fue " + (long) costoTotal);
        System.out.println("En este periodo, los empleados se quedaron sin cafe " + (long)cantidadDeEmpleadosSinCafe + " veces.");
        System.out.println("El costo mensual es de: "+(long)costoMensual);
        System.out.println("En este periodo, los empleados se quedaron sin cafe " + (long) cantidadDeEmpleadosMensual + " veces.");
    }
}
