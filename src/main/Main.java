/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import bagofword.BagOfWord;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author charles
 */
public class Main {

    public static List<String> fileNames = new ArrayList<>();

    public static void main(String args[]) {

        ArrayList<String> atributosVerificacao = new ArrayList<>();
        atributosVerificacao.addAll(Arrays.asList("|adverbios.xml",
                "|verbos.xml",
                "|ingl.xml",
                // "|adverbiosingl.xml",
                // "|adverbiosverbos.xml",
                // "|adverbiosverbosingl.xml",
                // "|verbosingl",
                "|1gram",
                "|2gram",
                "|3gram",
                "|4gram",
                "|1-2gram",
                "|1-3gram",
                "|1-4gram",
                "|2-3gram",
                "|2-4gram",
                "|3-4gram",
                "|1-2-3gram",
                "|1-2-4gram",
                "|1-3-4gram",
                "|2-3-4gram",
                "1.2desvio",
                "1.3desvio",
                "1.4desvio",
                "1.5desvio",
                "1.6desvio",
                "acertos"));

        BagOfWord bow = new BagOfWord(atributosVerificacao.size());
        bow.addAtributo("st_adv", "NUMERIC");
        bow.addAtributo("st_ver", "NUMERIC");
        bow.addAtributo("st_ing", "NUMERIC");
        //bow.addAtributo("st_adv_ing", "NUMERIC");
        //bow.addAtributo("st_adv_ver", "NUMERIC");
        //bow.addAtributo("st_adv_ver_ing", "NUMERIC");
        //bow.addAtributo("st_ver_ing", "NUMERIC");
        bow.addAtributo("1g", "NUMERIC");
        bow.addAtributo("2g", "NUMERIC");
        bow.addAtributo("3g", "NUMERIC");
        bow.addAtributo("4g", "NUMERIC");
        bow.addAtributo("1g_2g", "NUMERIC");
        bow.addAtributo("1g_3g", "NUMERIC");
        bow.addAtributo("1g_4g", "NUMERIC");
        bow.addAtributo("2g_3g", "NUMERIC");
        bow.addAtributo("2g_4g", "NUMERIC");
        bow.addAtributo("3g_4g", "NUMERIC");
        bow.addAtributo("1g_2g_3g", "NUMERIC");
        bow.addAtributo("1g_2g_4g", "NUMERIC");
        bow.addAtributo("1g_3g_4g", "NUMERIC");
        bow.addAtributo("2g_3g_4g", "NUMERIC");
        bow.addAtributo("dv_1_2", "NUMERIC");
        bow.addAtributo("dv_1_3", "NUMERIC");
        bow.addAtributo("dv_1_4", "NUMERIC");
        bow.addAtributo("dv_1_5", "NUMERIC");
        bow.addAtributo("dv_1_6", "NUMERIC");
        bow.addAtributo("acerto", "NUMERIC");

        bow.addListClasses(Arrays.asList(new String[]{"20_29", "30_39", "40_49", "50_59", "60_69", "70_79", "80_89"}));
        double valoresAtributos[] = new double[atributosVerificacao.size()];

        File arquivo = new File("analiseResultados.csv");
        try {
            try (FileReader fr = new FileReader(arquivo); BufferedReader br = new BufferedReader(fr)) {
                while (br.ready()) {
                    String linha = br.readLine();

                    if ( possuiPalavraExcluida(linha) || linha.length() < 10) {
                        continue;
                    }
                    for (int i = 0; i < atributosVerificacao.size(); i++) {
                        valoresAtributos[i] = possuiAtributo(atributosVerificacao.get(i), linha);
                    }
                    bow.addValoresDosAtributos(valoresAtributos, getNomeClasse(linha));
                }
                bow.createArffFile("SMO");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

//        for (int i : valoresAtributos) {
//
//        }
    }

    private static BagOfWord createBagOFWordTeste() {
        BagOfWord bow = new BagOfWord(3);
        bow.addAtributo("a1", "NUMERIC");
        bow.addAtributo("a2", "NUMERIC");
        bow.addAtributo("ta3", "NUMERIC");
        bow.addListClasses(Arrays.asList(new String[]{"C1", "C2", "C3"}));
        bow.addValoresDosAtributos(new double[]{0, 0, 0}, "C1");
        bow.addValoresDosAtributos(new double[]{0, 0, 1}, "C2");
        bow.addValoresDosAtributos(new double[]{0, 1, 0}, "C1");
        bow.addValoresDosAtributos(new double[]{0, 1, 1}, "C2");
        bow.addValoresDosAtributos(new double[]{1, 0, 0}, "C1");
        bow.addValoresDosAtributos(new double[]{1, 1, 0}, "C2");
        bow.createArffFile("Teste");
        return bow;
    }

    public static double possuiAtributo(String atributo, String texto) {
        if (atributo.equals("acertos")) {
            Pattern r = Pattern.compile("[0-9][0-9][\\.][0-9]+");
            Matcher m = r.matcher(texto);
            if (m.find()) {
                return Double.parseDouble(texto.substring(m.start(), m.end()));
            }
        }
        return texto.contains(atributo) ? 1 : 0;
    }

    public static String getNomeClasse(String linha) {
        Pattern r = Pattern.compile("[0-9][0-9][\\.][0-9]+");
        Matcher m = r.matcher(linha);
        if (m.find()) {
            double resultado = Double.parseDouble(linha.substring(m.start(), m.end()));
            if (resultado >= 20.00 && resultado <= 29.99) {
                return "20_29";
            }
            if (resultado >= 30.00 && resultado <= 39.99) {
                return "30_39";
            }
            if (resultado >= 40.00 && resultado <= 49.99) {
                return "40_49";
            }
            if (resultado >= 50.00 && resultado <= 59.99) {
                return "50_59";
            }
            if (resultado >= 60.00 && resultado <= 69.99) {
                return "60_69";
            }
            if (resultado >= 70.00 && resultado <= 79.99) {
                return "70_79";
            }
            if (resultado >= 80.00 && resultado <= 89.99) {
                return "80_89";
            }

            return " !!!!! " + resultado;
        }

        return " ???? ";
    }

    private static boolean possuiPalavraExcluida(String linha) {
        boolean possui = false;
        ArrayList<String> palavrasExcluidas = new ArrayList<>();
        palavrasExcluidas.add("misc");
        palavrasExcluidas.add("NaN");
        palavrasExcluidas.add("Classificador");
        palavrasExcluidas.add("port");
        palavrasExcluidas.add("|adverbiosingl.xml");
        palavrasExcluidas.add("|adverbiosverbos.xml");
        palavrasExcluidas.add("|adverbiosverbosingl.xml");
        palavrasExcluidas.add("|verbosingl");
        for (String palavra : palavrasExcluidas) {
            if (linha.contains(palavra)) {
                possui = true;
            }
        }
       
        return possui;
    }
}
