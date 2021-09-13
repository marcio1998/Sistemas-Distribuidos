
package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class Servidor extends Thread{
    //porta de comunicão
    private final int PORTA = 12345;
    
    public Servidor(){
        System.out.println("---------------------");
        System.out.println("Servidor Iniciado na Porta " + PORTA);
        System.out.println("---------------------");
    }
    
    @Override
    public void run(){
        try{
            //Criar um socket do tipo UDP
            DatagramSocket srv = new DatagramSocket(PORTA);
            while(true){
                //definir o buffer para recebimento da mensagem.
                byte[] buffer = new byte[256];
                //definir o pacote que será recebido.
                DatagramPacket pacote = new DatagramPacket(buffer, buffer.length);
                //esperar recebimento do pacote
                srv.receive(pacote);
                //transformar o pacote no tipo de dados esperado.
                String msg = new String(pacote.getData()).trim();
                //Exibir mensagem
                System.out.println("DE: " + pacote.getAddress());
                System.out.println("MSG: " + msg);
            }
        }catch(Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        new Servidor().start();
    }
}
