package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Cliente {
    //parâmetros de comunicação.
    private final String URL = "127.0.0.1";
    private final int PORTA = 12345;
    
    public Cliente(){
        
    }
    
    public void enviar(String msg){
        try{
            //converter a mensagem em bytes
            byte[] buffer = msg.getBytes();
            
            //criar o pacote que será transmitido
            DatagramPacket pacote = new DatagramPacket(
                    buffer, //conteudo
                    buffer.length, //tamanho
                    InetAddress.getByName(URL), //endereço
                    PORTA); //porta
            
            //enviar pacote
            new DatagramSocket().send(pacote);
        }catch(Exception e){
            System.out.println("ERRO: " + e.getMessage());
        }
    }
    
}
