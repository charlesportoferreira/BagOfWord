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

    
    private int[] valoresAtributos;
    private String classe;

    Texto(int valoresAtributos[], String nomeClasse) {
        this.valoresAtributos = valoresAtributos;
        this.classe = nomeClasse;
    }

    public int[] getValoresAtributos() {
        return valoresAtributos;
    }

    public void setValoresAtributos(int[] valoresAtributos) {
        this.valoresAtributos = valoresAtributos;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

}
