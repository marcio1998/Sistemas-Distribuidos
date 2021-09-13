package tcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JTextArea;


public class Servidor extends Thread{
    //Definir a porta de comunicação.
    private final int PORTA = 5000;
    
    //Definir os fluxos (stream) de comunicação.
    private ObjectInputStream fluxo_entrada;
    private ObjectOutputStream fluxo_saida;
    
    private final JTextArea txt;
    
    public Servidor(JTextArea txt){
        this.txt = txt;
    }
    
    public void enviar(Mensagem msg){
        try{
           fluxo_saida.writeObject(msg);
           fluxo_saida.flush();
        }catch(IOException e){
            System.err.println("Erro: " + e.getMessage());
        }
    }
    
    private void show(String msg){
        txt.append(msg + "\n");
    }
    
    @Override
    public void run(){
        try {
            //Iniciar o servidor Stream Socket.
            ServerSocket srv = new ServerSocket(PORTA);
            show("Servidor iniciado na PORTA: " + PORTA);
            while(true){
                show("Aguardando conexão...");
                Socket con = srv.accept();
                show("Conexão de: " + con.getInetAddress().getHostName());
                
                //Instanciar os fluxos de comunicação.
                fluxo_saida = new ObjectOutputStream(con.getOutputStream());
                fluxo_entrada = new ObjectInputStream(con.getInputStream());
                
                enviar(new Mensagem(1, "Conexão enviada com Sucesso"));
                
                //Processar as mensagens
                Mensagem msg;
                do{
                   msg = (Mensagem)fluxo_entrada.readObject();
                   show("\n Mensagem");
                   show("Tipo: " + msg.getTipo());
                   show("Body: " + msg.getBody());
                }while(msg.getTipo() != -1);// Tipo -1 = Fim da Comunicação.
                fluxo_entrada.close();
                fluxo_saida.close();
                con.close();
            }
            
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
    
}
