/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HocSinhUDP;

/**
 *
 * @author er1nzz
 */
public class HocSinh {
    private String ID;
    private String name;
    private String studyClass;
    private float score;

    public HocSinh(String ID, String name, String studyClass, float score) {
        this.ID = ID;
        this.name = name;
        this.studyClass = studyClass;
        this.score = score;
    }

    public HocSinh() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudyClass() {
        return studyClass;
    }

    public void setStudyClass(String studyClass) {
        this.studyClass = studyClass;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "HocSinh{" + "ID=" + ID + ", name=" + name + ", studyClass=" + studyClass + ", score=" + score + '}';
    }
    
    
}
