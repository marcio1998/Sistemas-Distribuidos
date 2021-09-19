package Udp.classes;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import javax.swing.JTextArea;

public class Server extends Thread {

    private int PORTA_RECEBER = 12345;
    private int PORTA_ENVIAR = 54321;
    private String URL = "127.0.0.1";
    private JTextArea textArea;
    private DateAndTime funcao = new DateAndTime();
    HoraCadaSegundo horas = new HoraCadaSegundo();

    public Server(JTextArea textArea) {
        this.textArea = textArea;
        textArea.append("Servidor Iniciado na porta " + PORTA_RECEBER + "\n");
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

    @Override
    public void run() {
        try {
            DatagramSocket srv = new DatagramSocket(PORTA_RECEBER);
            while (true) {
                byte[] buffer = new byte[256];
                DatagramPacket pacote = new DatagramPacket(buffer, buffer.length);
                srv.receive(pacote);
                String mensagem = new String(pacote.getData()).trim();
                textArea.append("DE: " + pacote.getAddress() + "\n");
                switch (mensagem) {
                    case "1":
                        Date data = funcao.retornarDataEHora();
                        textArea.append(mensagem + ": " + "Código para retorno de data " + "\n");
                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        String dataFormatada = dateFormat.format(data);
                        this.enviar(dataFormatada);
                        break;
                    case "2":
                        String dataPorExtenso = funcao.dataPorExtenso();
                        textArea.append(mensagem + ": " + "Código para retorno de data por Extenso " + "\n");
                        this.enviar(dataPorExtenso);
                        break;
                    case "3":
                        String hora = funcao.hora();
                        textArea.append(mensagem + ": " + "Código para retorno de hora " + "\n");
                        this.enviar(hora);
                        break;
                    case "4":
                        textArea.append(mensagem + ": " + "Código para retorno de hora por segundo" + "\n");
                        horas.disparar();
                        break;
                    case "5":
                        textArea.append(mensagem + ": " + "Código para cancelar hora por segundo " + "\n");
                        horas.cancelar();
                        break;
                    default:
                        this.enviar("Entrada inválida Valores de: 1 - 4");
                        textArea.append("Texto Inválido \n");
                        break;
                }

            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

}
