
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import service.IDateTime;


public class ClienteTeste {
    public static void main(String[] args) {
        try {
            // Registrar serviço que será acesado.
            Registry srv = LocateRegistry.getRegistry(IDateTime.SERVICE_HOST, IDateTime.PORTA);
            //Descobrir quais operações que estão disponíveis.
            IDateTime op = (IDateTime)srv.lookup(IDateTime.SERVICE_NAME);
            System.out.println("Data = " + op.getDate());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
