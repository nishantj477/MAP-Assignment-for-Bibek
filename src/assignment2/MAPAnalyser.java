/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;
import java.util.*;

/**
 *
 * @author Nishant
 */
public class MAPAnalyser {
    private Record[] data;
    private int nrecords;
    
    public MAPAnalyser() {
        nrecords = 3;
	data = new Record[nrecords];
        loadFromTables();
        SortById(); 
    }   
    
    //Implemented using binary search
    public Record find(String id) {
        int l = 0, r = data.length - 1; 
        while (l <= r) { 
            int m = l + (r - l) / 2; 
            int res = id.compareTo(data[m].getId()); 
            // Check if x is present at mid 
            if (res == 0) 
                 return data[m]; 
            // If x greater, ignore left half 
            if (res > 0) 
                l = m + 1; 
            // If x is smaller, ignore right half 
            else
                r = m - 1; 
        } 
        return null; 
    }
    
    public Record[] find(int map1, int map2) {
        ArrayList<Record> myRec = new ArrayList<Record>(Arrays.asList());
        for (int i = 0; i < nrecords; i++) {
            int map = data[i].getMap();
            if (map >= map1 && map <= map2) {
                myRec.add(data[i]);
            }            
        }
        return myRec.toArray(new Record[myRec.size()]);
    }
        
    public int lowest() {
        int min = data[0].getMap();
        for (int i = 1; i < nrecords; i++) {
            if(data[i].getMap() < min) {
                min = data[i].getMap();
            }
        }
        return min;
    }
    
    public int highest () {
        int max = data[0].getMap();
        for (int i = 1; i < nrecords; i++) {
            if(data[i].getMap() > max) {
                max = data[i].getMap();
            }
        }
        return max;
    }
    
    public int median() {
        //get the MAP values to an array.
        int median = 0;
        int[] arr = new int[nrecords];
        for(int i = 0; i < nrecords; i++) {
            arr[i] = data[i].getMap();
        }
        for (int i = 0; i < arr.length - 1; i++){  
            int index = i;  
            for (int j = i + 1; j < arr.length; j++){  
                if (arr[j] < arr[index]){  
                    index = j;//searching for lowest index  
                }  
            }  
            int smallerNumber = arr[index];   
            arr[index] = arr[i];  
            arr[i] = smallerNumber;  
        }  
        //If n is odd
         if(arr.length % 2 != 0) {
             median = arr[(arr.length)/2];
            } else {
               median = (arr[(arr.length)/2] + arr[((arr.length)/2) - 1])/2;
            }   
        return median;  
    }
    
    public void SortById() {
        for (int i=0; i<data.length-1; i++) {
            for (int j=i+1; j<data.length; j++) {
                if (data[i].getId().compareTo(data[j].getId()) > 0) {
                    Record rec = data[j];
                    data[j] = data[i];
                    data[i] = rec;
                }
            }
        }
    }
    
    void loadFromTables(){
        String[] id = {"S03", "S02", "S01"};
        //  array for sbp
        int[] sbp= {80, 60, 110};
        // array for dbp
        int[] dbp = {70, 50, 100};
        for (int i = 0; i < nrecords; i++) {
            // calculate  the map data
            int map = MapValue(sbp[i], dbp[i]);
            //  initialization of data array
            data[i] = new Record(id[i], sbp[i], dbp[i], map, classify(map));
        }
    }
    
    String classify(int map) {
        if (map > 100) {
            return "high";
        } else if (map < 70) {
            return "low";
        } else {
            return "medium";
        }
    }
        
    int MapValue(int sbp, int dbp) {
            return (int)(((1.0/3.0) * sbp) + ((2.0/3.0) * dbp));
    }

    public Record[] getData() {
        return data;
    }
}
