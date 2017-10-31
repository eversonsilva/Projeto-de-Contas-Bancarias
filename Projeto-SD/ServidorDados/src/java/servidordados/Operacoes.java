/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidordados;

import java.util.Date;
import javax.ejb.Singleton;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author 114751
 */
@Singleton
@Path("servidordados")
public class Operacoes extends Thread{

    @Context
    private UriInfo context;
    Date date = new Date();
    String dh;
    private static int contGetSaldo;
    private static int contSetSaldo;
    int[] saldos = {1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000}; //Lista de contas com saldos 1000
    /**
     * Creates a new instance of Operacoes
     */
    public Operacoes(){
    }

    /**
     * Retrieves representation of an instance of servidordados.Operacoes
     *
     * @param conta_
     * @param Serv_negoc
     * @return an instance of java.lang.String
     */
    //Método SetSaldo do servidor de dados 
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/saldo/{idConta}/{id_negoc}")
    public synchronized String getSaldo(@PathParam("idConta") String conta_, @PathParam("id_negoc") String Serv_negoc) {
        int conta = Integer.parseInt(conta_);
        System.out.println("Enviado saldo da conta " + conta + " com o valor de " + saldos[conta]);
        
        //Contador que serve para visualizar quantas vezes esse método foi utilizado.
        int contador = incrementaGetSaldo();                                                                
        
        //Pega a data e hora atual.
        dh = Data.date2string(date);
        
        //Escreve no arquivo de logs
        new ArquivoLog(dh, contador, Serv_negoc,"getSaldo", conta_, Integer.toString(saldos[conta]));
        
        return Integer.toString(saldos[conta]);
    }

    /**
     * @param conta_
     * @param valor_
     * @param idServ_negoc
     */
   
    //Método SetSaldo do servidor de dados 
    @GET
    @Produces("text/plain")
    @Path("/setsaldo/{idConta}/{valor}/{id_negoc}")
    public synchronized void setSaldo(@PathParam("idConta") String conta_, @PathParam("valor") String valor_,@PathParam("id_negoc") String idServ_negoc) {
        System.out.println("Servidor de Dados - entrou no setSaldo");
        int conta = Integer.parseInt(conta_);
        int valor = Integer.parseInt(valor_);

        System.out.println("Saldo conta " + conta + ": " + saldos[conta]);
        
        //Mudança de saldo para a conta que foi passada.
        saldos[conta] = valor;
        
        //Contador que serve para visualizar quantas vezes esse método foi utilizado.
        int contador = incrementaSetSaldo();                                                                

        dh = Data.date2string(date);
        //Escreve no arquivo de logs
        new ArquivoLog(dh, contador, idServ_negoc,"setSaldo", conta_, valor_);
    }

    @GET
    @Produces("text/plain")
    @Path("/unLock/{ServidorNegocio}/{conta}")
    public synchronized int unLock(@PathParam("ServidorNegocio") String servidorNegocio, @PathParam("conta") String conta_) {
      return -1;
    }

    @GET
    @Produces("text/plain")
    @Path("/onLock/{idServidorNegocio}/{conta}")
    public synchronized int Lock(@PathParam("idServidorNegocio") String idServidorNegocio, @PathParam("conta") String conta) {
        return -1;
    }

    /**
     * PUT method for updating or creating an instance of Operacoes
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public void putText(String content) {
    }
    
    //Método para contar as operações de getSaldo
   private static int incrementaGetSaldo() {
       return ++contGetSaldo;
    }
    
   //Método para contar as operações de SetSaldo
    private static int incrementaSetSaldo() {
       return ++contSetSaldo;
    }
}
