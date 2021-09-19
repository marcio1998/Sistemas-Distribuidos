/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Udp.classes;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JTextArea;

/**
 *
 * @author Gabriel
 */
public class HoraCadaSegundo {

    private int PORTA_ENVIAR = 54321;
    private String URL = "127.0.0.1";
    private final int delay = 0;
    private int interval = 1000;
    DateTimeFormatter dt = DateTimeFormatter.ofPattern("HH:mm:ss");
    Timer timer = new Timer();

    public void enviar(String enviar) {
        try {
            byte[] buffer = enviar.getBytes();
            DatagramPacket pacote = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(URL), PORTA_ENVIAR);
            new DatagramSocket().send(pacote);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void disparar() {
        this.timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                String hora = dt.format(LocalDateTime.now());
                enviar(hora);
            }
        }, this.delay, this.interval);
    }

    public void cancelar() {
        enviar("Hora Por Segundo Cancelada \n");
        this.timer.cancel();
    }

;

}
