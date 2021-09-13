
package Udp.classes;

import java.util.Calendar;
import java.util.Date;

public class DateAndTime {
    public Date retornarDataEHora(){
        Calendar c = Calendar.getInstance();
        return c.getTime();
        
    }
}
