package Udp.classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateAndTime {

    public Date retornarDataEHora() {
        Calendar c = Calendar.getInstance();
        return c.getTime();

    }

    public String dataPorExtenso() {
        Calendar c = Calendar.getInstance();
        int dia = c.get(Calendar.DAY_OF_MONTH);
        int mes = c.get(Calendar.MONTH);
        String mesPorExtenso = mes(mes);
        int ano = c.get(Calendar.YEAR);
        return "Dia: " + Integer.toString(dia) + " de " + mesPorExtenso + " de " + Integer.toString(ano);
    }

    public String hora() {
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("HH:mm:ss");
        return dt.format(LocalDateTime.now());
    }
  

    private String mes(int mes) {
        String[] meses = new String[]{"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "junho", "julho", "agosto", "setembro", "outubro",
            "novemro", "dezembro"}; //inline initialization  
        return meses[mes];
    }
    
    
}
