/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidornegocio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Everson
 */

public class ArquivoLog {

    //Variáveis criadas para criação, leitura e escrita do arquivo.
    
    File arq;                                                                                                 
    FileReader fr;                                                                                            
    BufferedReader bf;
    FileWriter fw;
    BufferedWriter bw;
    
    public ArquivoLog(String dh, int no, String o, String c1, String c2, String valor) {                         
        //Construtor recebe como parametro o que vai ser escrito no arquivo Log.
         
        escreverLog(dh, no, o, c1, c2, valor);                                                                   //Construtor já chama o método para montagem do arquivo.
    }
    
    private void escreverLog(String dh, int no, String o, String c1, String c2, String valor){                    //Método que recebe como parâmetro o que vai ser escrito no arquivo e o responsável por montar o arquivo.
        try {
            
            arq = new File("C:\\Temp\\Logs_Negocio.txt");                                                           //Criação do arquivo txt na partição C na pasta Temp.
            fr = new FileReader(arq);                                                                             //Variável instânciada para leitura do arquivo.
            bf = new BufferedReader(fr);                                                                          //Variável instânciada para leitura das linhas do arquivo.
            
            List linha = new ArrayList();                                                                         //Array criado para guardar tudo que já estava escrito no arquivo.
            
            while(bf.ready()){                                                                                    //Estrutura de repetição para ler todas as linhas do arquivo.
                linha.add(bf.readLine());                                                                         //Lendo as linhas do arquuivo.
            }
            
            fw = new FileWriter(arq);                                                                             //Variável instânciada para escrita no arquivo.
            bw = new BufferedWriter(fw);                                                                          //Váriavel instânciada para escrita por linha no arquivo.
            
            for (int i = 0; i < linha.size(); i++) {                                                              //Estrutura de repetição para escrever nas linhas do arquivo.
                bw.write(linha.get(i).toString());                                                                //Reescrevendo o que foi guardado no Array, que já estava antes no arquivo.
                bw.newLine();                                                                                     //Pular linha.
            }
            
            //Escrevendo no arquivo o que foi recebido do parâmetro do método.
            
            bw.write("    Data e hora     | Num Oper  | Operação | Conta 1 | Conta 2 | Valor");
            bw.newLine();
            bw.write(dh + "      "); 
            bw.write(no + "         ");
            bw.write(o + "     "); 
            bw.write(c1 + "       "); 
            bw.write(c2 + "         ");
            bw.write(valor + "\n");

            
            bf.close();                                                                                            //Fechando a leitura por linha.
            bw.close();                                                                                            //Fechando a escrita por linha.     
            
            //Exeções sendo tratadas, sendo elas: 
            //- Se não encontrar o arquivo ele é criado.
            //- Se encontrar o arquivo e não puder ser criado, o programa é fechado.
            
        } catch (FileNotFoundException ex) {
            try {
                arq.createNewFile();
                escreverLog(dh, no, o, c1, c2, valor);
            } catch (IOException ex1) {
                System.exit(0);
            }
        } catch (IOException ex){
            System.exit(0);
        }
    }
    
}
