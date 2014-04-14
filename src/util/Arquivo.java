/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author charles
 */
public class Arquivo {

    public void escreverArquivo(String nome, String texto) {
        File arquivo = new File(nome);

        try {

//            if (!arquivo.exists()) {
//                //cria um arquivo (vazio)
//                arquivo.createNewFile();
//            }

            //caso seja um diretório, é possível listar seus arquivos e diretórios
            //File[] arquivos = arquivo.listFiles();
            try ( //escreve no arquivo
                    FileWriter fw = new FileWriter(arquivo, false); BufferedWriter bw = new BufferedWriter(fw)) {

                bw.write(texto);

                bw.newLine();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void LerArquivo(String nome) {
        File arquivo = new File(nome);
        //faz a leitura do arquivo
        try {
            try (FileReader fr = new FileReader(arquivo); BufferedReader br = new BufferedReader(fr)) {

                //equanto houver mais linhas
                while (br.ready()) {
                    //lê a proxima linha
                    String linha = br.readLine();

                    //faz algo com a linha
                    System.out.println(linha);
                }

            }

        } catch (IOException ex) {

        }
    }

}
