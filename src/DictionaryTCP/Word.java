/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DictionaryTCP;

import java.io.Serializable;

/**
 *
 * @author er1nzz
 */
public class Word implements Serializable{
    private String en;
    private String vi;

    public Word() {
    }

    public Word(String en, String vi) {
        this.en = en;
        this.vi = vi;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getVi() {
        return vi;
    }

    public void setVi(String vi) {
        this.vi = vi;
    }

    @Override
    public String toString() {
        return "en=" + en + ", vi=" + vi;
    }
    
    
    
}
