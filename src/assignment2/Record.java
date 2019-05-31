/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;

/**
 *
 * @author Nishant
 */
public class Record {
    private String id;
    private int sbp;
    private int dbp;
    private int map;
    private String category;
   
    
    public Record(String id, int sbp, int dbp, int map, String category) {
        //Initialize member variables
        this.id = id;
        this.sbp = sbp;
        this.dbp = dbp;
        this.map = map;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public int getSbp() {
        return sbp;
    }

    public int getDbp() {
        return dbp;
    }

    public int getMap() {
        return map;
    }

    public String getCategory() {
        return category;
    }
    
    @Override
    public String toString() {
        return "   " + getId() + "           " + getSbp() +
                "         " + getDbp() + "         " + getMap() + "           " + getCategory() + "\n";
    } 
}
