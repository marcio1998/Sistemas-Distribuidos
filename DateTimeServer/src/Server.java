
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import service.IDateTime;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gabriel
 */

//retorna os metodos da classe acissíveis remotamente.
public class Server extends UnicastRemoteObject implements IDateTime{
    public Server() throws RemoteException{
        super();
    }

    @Override
    public String getDate() throws RemoteException {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return f.format(LocalDate.now());
    }

    @Override
    public String getTime() throws RemoteException {
         DateTimeFormatter f = DateTimeFormatter.ofPattern("hh:mm:ss");
         return f.format(LocalDate.now());
    }

    @Override
    public String getAge(LocalDate birthDate) throws RemoteException {
        Period p  = Period.between(birthDate, LocalDate.now());
        return String.format("%anos, %meses e %dias", p.getYears(), p.getMonths(), p.getDays());
    }
    
    
    //executar o servidor
    public static void main(String[] args) {
        try {
            IDateTime srv = new Server();
            
            //Registrar a porta de comunicação
            Registry rg = LocateRegistry.createRegistry(IDateTime.PORTA);
            
            //Disponibilizar o Serviço.
            rg.bind(IDateTime.SERVICE_NAME, srv);
            System.out.println("Servidor em Execução");
        } catch (AlreadyBoundException | RemoteException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
