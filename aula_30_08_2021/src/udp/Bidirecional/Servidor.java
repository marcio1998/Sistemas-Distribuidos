
package udp.Bidirecional;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.swing.JTextArea;


public class Servidor extends Thread{
    //porta de comunicão
    private final int PORTA_RECEBER = 12345;
    private final int PORTA_ENVIAR = 54321;
    private final String URL = "127.0.0.1";
    private JTextArea txt;
    
    public Servidor(JTextArea txt){
        this.txt = txt;
        System.out.println("---------------------");
        System.out.println("Servidor Iniciado na Porta " + PORTA_RECEBER);
        System.out.println("---------------------");
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
                    PORTA_ENVIAR); //porta
            
            //enviar pacote
            new DatagramSocket().send(pacote);
        }catch(Exception e){
            System.out.println("ERRO: " + e.getMessage());
        }
    }
    
    @Override
    public void run(){
        try{
            //Criar um socket do tipo UDP
            DatagramSocket srv = new DatagramSocket(PORTA_RECEBER);
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
                //Exibir a mensagem no textArea.
                txt.append("DE: " + pacote.getAddress() + "\n");
                txt.append("MSG: " + msg + "\n\n");
                
            }
        }catch(Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    /*public static void main(String[] args) {
        new Servidor().start();
    }*/
}
