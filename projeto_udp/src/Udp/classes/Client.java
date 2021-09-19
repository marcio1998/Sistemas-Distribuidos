package Udp.classes;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.swing.JTextArea;

public class Client extends Thread {

    private final int PORTA_RECEBER = 54321;
    private final int PORTA_ENVIAR = 12345;
    private final String URL = "127.0.0.1";
    private JTextArea textArea;

    public Client(JTextArea textArea) {
        this.textArea = textArea;
        textArea.append("Servidor Iniciado na Porta " + PORTA_RECEBER + "\n");
    }

    public void enviar(String enviar) {
        try {
            byte[] buffer = enviar.getBytes();
            DatagramPacket pacote = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(URL), PORTA_ENVIAR);
            new DatagramSocket().send(pacote);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void cancelar() {
        enviar("5");
    }

    @Override
    public void run() {

        try {
            DatagramSocket client = new DatagramSocket(PORTA_RECEBER);
            while (true) {
                byte[] buffer = new byte[256];
                DatagramPacket pacote = new DatagramPacket(buffer, buffer.length);
                client.receive(pacote);
                String mensagem = new String(pacote.getData()).trim();
                textArea.append("Resposta: " + mensagem + "\n");
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

}
