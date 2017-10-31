/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

/**
 *
 * @author 41414421
 */
public class ATM {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException  {
        // TODO code application logic here
    ServidorProxyNegocio spn = new ServidorProxyNegocio();                                        //Variável instânciada para criar uma comunicação com o servidor de negócio.
    String end = ServidorProxyNegocio.getBASE_URI();                                              //Variável utilizada para pegar o caminho da URI do servidor de negócio.
    String servidor[] = new String[5];                                                            //Vetor criado para alocar em suas posições partes da URI que serão destacadas.
    
    servidor = end.split("/");                                                                    //Dividindo a URI em partes e as guardando no vetor.
    String posicao = servidor[3];                                                                 //Passando para uma variável apenas a parte que nos interessa da URI, que seria qual servidor esta sendo utilizado.
        System.out.println("O saldo da conta 0 é de "+spn.getsaldo("0", posicao));
    
        
        System.out.println("==================================================");
        System.out.println("=====================DEPOSITO=====================");
        System.out.println("Realizei um Deposito na conta 0 no valor de R$1000"+spn.deposit("0", "1000", posicao));
        System.out.println("O saldo Atualizado é de "+spn.getsaldo("0", posicao));
        
        
        System.out.println("==================================================");
        System.out.println("======================SAQUE=======================");
        System.out.println("Operação de saque no valor R$500: "+spn.withdraw("0", "500", posicao));
        System.out.println("O saldo Atualizado é de "+spn.getsaldo("0", posicao));
        
        
        System.out.println("==================================================");
        System.out.println("======================TRANSFERENCIA===============");
        System.out.println("O saldo da conta 0 "+spn.getsaldo("0", posicao));
        System.out.println("O saldo da conta 9 "+spn.getsaldo("9", posicao));
        System.out.println("Operação de transferencia da conta 0 para conta 9 no valor de R$1000: "+spn.transfer("0", "9", "1000", posicao));
        System.out.println("O saldo Atualizado da conta 0 é de "+spn.getsaldo("0", posicao));
        System.out.println("O saldo Atualizado da conta 9 é de "+spn.getsaldo("9", posicao));
    }
    
}

