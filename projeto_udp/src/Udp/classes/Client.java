
package Udp.classes;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class Client extends Thread{
    private final int PORTA_RECEBER = 54321;
    private final int PORTA_ENVIAR = 12345;
    private final String URL = "localhost";
    
    
    public void enviar(String enviar){
        try {
            byte[] buffer = enviar.getBytes();
            DatagramPacket pacote = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(URL), PORTA_ENVIAR);
            new DatagramSocket().send(pacote);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    @Override
    public void run(){
        
        try {
            DatagramSocket client = new DatagramSocket(PORTA_RECEBER);
            while(true){
                byte[] buffer = new byte[256];
                DatagramPacket pacote = new DatagramPacket(buffer,buffer.length);
                client.receive(pacote);
                String mensagem = new String(pacote.getData()).trim();
            }
            
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    
    
}
