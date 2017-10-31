/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidornegocio;

import java.util.Date;
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
@Path("servidornegocio")
public class Negocio {

    @Context
    private UriInfo context;
    Date date = new Date();
    String dh;
    private static int contGetSaldo;
    private static int contSetSaldo;
    private static int contWithdraw;
    private static int contDeposit;
    private static int contTransfer;

    /**
     * Creates a new instance of Negocio
     */
    public Negocio() {
    }

    /**
     * Retrieves representation of an instance of servidornegocio.Negocio
     *
     * @param conta
     * @param idServ_negoc
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    @Path("saldo/{idConta}/{id_negoc}")
    public String getsaldo(@PathParam("idConta") String conta, @PathParam("id_negoc") String idServ_negoc) {
        //Conexão com o servidor de Dados.
        ServidorProxyDados spd = new ServidorProxyDados();
        
        //Pegando o saldo da conta.
        String saldo = spd.getSaldo(conta, idServ_negoc);
        
        System.out.println("Servidor de Negocio - operacao saldo - conta " + conta + " - Saldo: " + saldo);

        //Contador que serve para visualizar quantas vezes esse método foi utilizado.
        int contador = incrementaGetSaldo();
        
        //Pega a data e hora atual.
        dh = Data.date2string(date);
        
        //Escreve no arquivo de logs
        new ArquivoLog(dh, contador, "getsaldo", conta, "", saldo);

        return saldo;
    }

    @GET
    @Produces("text/plain")
    @Path("setsaldo/{idConta}/{valor}/{id_negoc}")
    public String setSaldo(@PathParam("idConta") String conta, @PathParam("valor") String valor, @PathParam("id_negoc") String idServ_negoc) {
        //Conexão com o servidor de Dados.
        ServidorProxyDados spd = new ServidorProxyDados();
        
        //Variável recebendo o valor do novo saldo.
        String novoSaldo = valor;
        
        //Mudando o saldo da conta.
        spd.setSaldo(conta, novoSaldo, idServ_negoc);
        System.out.println("Servidor de Negocio - operacao setSaldo - conta " + conta + " - valor: " + valor + " - Saldo: " + spd.getSaldo(conta, idServ_negoc));

        //Contador que serve para visualizar quantas vezes esse método foi utilizado.
        int contador = incrementaSetSaldo();

        //Pega a data e hora atual.
        dh = Data.date2string(date);
        
        //Escreve no arquivo de logs
        new ArquivoLog(dh, contador, "setsaldo", conta, "", novoSaldo);

        return spd.getSaldo(conta, idServ_negoc);
    }

    @GET
    @Produces("text/plain")
    @Path("deposit/{idConta}/{valor}/{id_negoc}")
    public void deposit(@PathParam("idConta") String conta, @PathParam("valor") String valor, @PathParam("id_negoc") String idServ_negoc) {
        //Conexão com o servidor de Dados.
        ServidorProxyDados spd = new ServidorProxyDados();
        
        //Pegando o saldo da conta.
        int saldo_da_conta = Integer.parseInt(spd.getSaldo(conta, idServ_negoc));
        
        //Novo saldo da conta com a adição do valor de deposito.
        int deposito = saldo_da_conta + Integer.parseInt(valor);
        
        //Mudando o saldo da conta.
        spd.setSaldo(conta, Integer.toString(deposito), idServ_negoc);
        System.out.println("Servidor de Negocio - operacao de deposito - conta " + conta + " - valor: " + valor + " - Saldo: " + spd.getSaldo(conta, idServ_negoc));

        //Contador que serve para visualizar quantas vezes esse método foi utilizado.
        int contador = incrementaDeposit();

        //Pegando data e hora atual.
        dh = Data.date2string(date);
        
        //Escreve no arquivo de logs
        new ArquivoLog(dh, contador, "deposit", conta, "", Integer.toString(deposito));

    }

    @GET
    @Produces("text/plain")
    @Path("withdraw/{idConta}/{valor}/{id_negoc}")
    public void withdraw(@PathParam("idConta") String conta, @PathParam("valor") String valor, @PathParam("id_negoc") String idServ_negoc) {
        //Conexão com o servidor de Dados.
        ServidorProxyDados spd = new ServidorProxyDados();
        
        //Pegando o saldo da conta.
        int saldo_da_conta = Integer.parseInt(spd.getSaldo(conta, idServ_negoc));
        
        //Novo saldo da conta depois da retirada do valor de saque.
        int saque = saldo_da_conta - Integer.parseInt(valor);
        
        //Mudando o saldo da conta.
        spd.setSaldo(conta, Integer.toString(saque), idServ_negoc);
        System.out.println("Servidor de Negocio - operacao de saque - conta " + conta + " - valor: " + valor + " - Saldo: " + spd.getSaldo(conta, idServ_negoc));

        //Contador que serve para visualizar quantas vezes esse método foi utilizado.
        int contador = incrementaWithdraw();

        //Pegando data e hora atual.
        dh = Data.date2string(date);
        
        //Escreve no arquivo de logs
        new ArquivoLog(dh, contador, "withdraw", conta, "", Integer.toString(saque));
    }

    @GET
    @Produces("text/plain")
    @Path("/transfer/{idConta1}/{idConta2}/{valor}/{id_negoc}")
    public void transfer(@PathParam("idConta1") String conta_orig, @PathParam("idConta2") String conta_dest, @PathParam("valor") String valor_, @PathParam("id_negoc") String idServ_negoc) {
        //Conexão com o servidor de Dados.
        ServidorProxyDados spd = new ServidorProxyDados();
        
        
        int valor = Integer.parseInt(valor_);
        int saldo_cont_orig = Integer.parseInt(spd.getSaldo(conta_orig, idServ_negoc));
        
        //Verificando se a conta possui valor para transferencia.
        if (valor <= saldo_cont_orig) {
           
            //Retirando valor da conta original.
            withdraw(conta_orig, valor_, idServ_negoc);
            
            //Passando valor para a conta destino.
            deposit(conta_dest, valor_, idServ_negoc);

            //Contador que serve para visualizar quantas vezes esse método foi utilizado.
            int contador = incrementaTransfer();

            //Pegando a data e hora atual.
            dh = Data.date2string(date);
            
            //Escreve no arquivo de logs
            new ArquivoLog(dh, contador, "transfer", conta_orig, conta_dest, valor_);

            System.out.println("\nTransfer succesful. Tansfered: $" + valor);
        } else {
            
            //Mensagem para mostrar que a conta original não possui valor de transferencia exigido.
            System.out.println("\n Erro na transferencia, quantia insuficiente!");
        }
    }

    /**
     * PUT method for updating or creating an instance of Negocio
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
    //Método para contar as operações de Saque
    private static int incrementaWithdraw(){
       return ++contWithdraw;
    }
    //Método para contar as operações de Deposito
    private static int incrementaDeposit() {
       return ++contDeposit;
    }
    //Método para contar as operações de Transferencia
      private static int incrementaTransfer() {
       return ++contTransfer;
    }
}
