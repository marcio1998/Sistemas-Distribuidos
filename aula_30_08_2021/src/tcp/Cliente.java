package tcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JTextArea;

public class Cliente extends Thread {

    //Definir a porta de comunicação.
    private final int PORTA = 5000;
    private final String URL = "127.0.0.1";

    //Definir os fluxos (stream) de comunicação.
    private ObjectInputStream fluxo_entrada; //Recebendo uma Mensagem.
    private ObjectOutputStream fluxo_saida; //Enviando uma Mensagem.

    private final JTextArea txt;

    public Cliente(JTextArea txt) {
        this.txt = txt;
    }

    public void enviar(Mensagem msg) {
        try {
            fluxo_saida.writeObject(msg);
            fluxo_saida.flush();
        } catch (IOException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    private void show(String msg) {
        txt.append(msg + "\n");
    }

    @Override
    public void run() {
        try {
            Socket con = new Socket(URL, PORTA);
            show("Conectado no Servidor na PORTA: " + PORTA);

            //Instanciar os fluxos de comunicação.
            fluxo_saida = new ObjectOutputStream(con.getOutputStream());
            fluxo_entrada = new ObjectInputStream(con.getInputStream());


            //Processar as mensagens
            Mensagem msg;
            do {
                msg = (Mensagem) fluxo_entrada.readObject();
                show("\n Mensagem");
                show("Tipo: " + msg.getTipo());
                show("Body: " + msg.getBody());
            } while (msg.getTipo() != -1);// Tipo -1 = Fim da Comunicação.
            fluxo_entrada.close();
            fluxo_saida.close();
            con.close();

        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

}
