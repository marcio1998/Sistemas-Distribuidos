/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;

/**
 *
 * @author Gabriel
 */
public interface IDateTime extends Remote{
     //Propriedades de serviço
    public static final int PORTA = 1999;
    public static final String  SERVICE_NAME = "date_time";
    public static final String  SERVICE_HOST = "192.168.15.21";
    
    //Ssinatura dos metodos que fazem parte dos serviços.
    public abstract String getDate() throws RemoteException;
    public abstract String getTime() throws RemoteException;
    public abstract String getAge(LocalDate birthDate) throws RemoteException;
}
