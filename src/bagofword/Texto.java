/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bagofword;

/**
 *
 * @author charleshenriqueportoferreira
 */
public class Texto {

    
    private double[] valoresAtributos;
    private String classe;

    Texto(double valoresAtributos[], String nomeClasse) {
        this.valoresAtributos = valoresAtributos;
        this.classe = nomeClasse;
    }

    public double[] getValoresAtributos() {
        return valoresAtributos;
    }

    public void setValoresAtributos(double[] valoresAtributos) {
        this.valoresAtributos = valoresAtributos;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

}
