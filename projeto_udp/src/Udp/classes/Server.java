package Udp.classes;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server extends Thread {

    private int PORTA_RECEBER = 12345;
    private int PORTA_ENVIAR = 54321;
    private String URL = "localhost";

    public Server() {
        System.out.println("---------------------------");
        System.out.println("Servidor Iniciado na porta " + PORTA_RECEBER);
        System.out.println("---------------------------");
    }

    public void enviar(String enviar) {
        try {
            byte[] buffer = enviar.getBytes();
            DatagramPacket pacote = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(URL), PORTA_ENVIAR);
            new DatagramSocket().send(pacote);
        } catch (Exception e) {
            System.out.println("Erro: " +  e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            DatagramSocket srv = new DatagramSocket(PORTA_RECEBER);
            while (true) {
                byte[] buffer = new byte[256];
                DatagramPacket pacote = new DatagramPacket(buffer, buffer.length);
                srv.receive(pacote);
                String mensagem = new String(pacote.getData()).trim();

            }
        } catch (Exception e) {
            System.out.println("Erro: " +  e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        new Server().start();
    }

}
