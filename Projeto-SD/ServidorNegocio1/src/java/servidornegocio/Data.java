/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidornegocio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Everson
 */

public class Data {

    private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");                    //Formatar data e hora juntos.
    private static SimpleDateFormat formatterD = new SimpleDateFormat("dd/MM/yyyy");                            //Formatar apenas a data.
    private static SimpleDateFormat formatterH = new SimpleDateFormat("HH:mm:ss");                              //Formatar apenas a hora.

    public static String date2string(Date date) {                                                               //Método que transforma um Date em String.
        return formatter.format(date);
    }

    public static String time2string(Date date) {                                                                //Método que transforma a hora em String. 
        return formatterH.format(date);
    }

    public static Date string2date(String str) {                                                                 //Método que transforma a String em Date.
        Date date = null;

        try {
            date = formatter.parse(str);
            System.out.println(str);
            System.out.println(date);
        } catch (ParseException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }

        return date;
    }
    
    public static Date string2time(String str) {                                                                    //Método que transforma a String em hora.
        Date date = null;

        try {
            date = formatterH.parse(str);
        } catch (ParseException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }

        return date;
    }
}

