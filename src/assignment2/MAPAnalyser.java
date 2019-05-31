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
        //Initialize member variables
        nrecords = 5;
	data = new Record[nrecords];
        //load arrays with id, sbp, dbp values and sort data[] by id in ascending.
        loadFromTables();
        SortById(); 
    }   
    
    //Implemented using binary search
    public Record find(String id) {
        int low = 0, high = data.length - 1; 
        while (low <= high) { 
            int mid = low + (high - low) / 2; 
            int res = id.compareTo(data[mid].getId()); 
            // Check if id is present at mid 
            if (res == 0) 
                 return data[mid]; 
            // If id is greater, ignore left half 
            if (res > 0) 
                low = mid + 1; 
            // If x is smaller, ignore right half 
            else
                high = mid - 1; 
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
        int median = 0;
        //get the MAP values to an array.
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
    
    //implemented using selection sort
    public void SortById() {
        for (int i=0; i<data.length-1; i++) {
            for (int j=i+1; j<data.length; j++) {
                //If i+1 is smaller than i, perform swap
                if (data[i].getId().compareTo(data[j].getId()) > 0) { 
                    Record rec = data[j];
                    data[j] = data[i];
                    data[i] = rec;
                }
            }
        }
    }
    
    //load the initial values of id, sbp & dbp. Calculate MAP & initialize data[]by creating Record Objects.
    void loadFromTables(){
        String[] id = {"S03", "S05", "S01", "S00", "S02"};
        //  array for sbp
        int[] sbp= {80, 60, 110, 65, 85};
        // array for dbp
        int[] dbp = {70, 50, 100, 99, 111};
        for (int i = 0; i < nrecords; i++) {
            // calculate  the map data
            int map = MapValue(sbp[i], dbp[i]);
            //  initialization of data array
            data[i] = new Record(id[i], sbp[i], dbp[i], map, classify(map));
        }
    }
    
    //method to categorise MAP
    String classify(int map) {
        if (map > 100) {
            return "high";
        } else if (map < 70) {
            return "low";
        } else {
            return "medium";
        }
    }
        
    //method to calculate MAP
    int MapValue(int sbp, int dbp) {
            return (int)(((1.0/3.0) * sbp) + ((2.0/3.0) * dbp));
    }

    public Record[] getData() {
        return data;
    }
}
