import TP.Eventos.TomarCafe;
import org.junit.jupiter.api.Test;

public class TestSimulacion {

    @Test
    public void laconchadetumadre(){
        TomarCafe tc = new TomarCafe(1);
        System.out.println(tc.factorial(14));
    }
}
