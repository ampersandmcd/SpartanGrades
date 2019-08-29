package com.andrewmcdonald27.spartan_grades;

import android.app.Application;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComparisonArray extends Application {

  private ArrayList<String[]> comparison_array = new ArrayList<String[]>();
  private ArrayList<String[]> master_array = new ArrayList<String[]>();
  private boolean master_ready = false;

  public ArrayList<String[]> getComparisonArray(){
    return comparison_array;
  }

  public ArrayList<String[]> getMasterArray() {
    return master_array;
  }

  public void setMasterArray() {
    if (!master_ready) {
      master_array = readData();
      master_ready = true;
    }
  }

  public void removeEntry(int index){
    comparison_array.remove(index);
  }

  public void insertComparisonArray(String[] temp_array){
    boolean flag = true;
    for (String[] ary : comparison_array){
      if (Arrays.equals(ary, temp_array)){
        flag = false;
      }
    }
    if (flag) { //we can add non-duplicate
      comparison_array.add(temp_array);
    }
  }

  private ArrayList<String[]> readData(){
    try {
      InputStream is = getResources().openRawResource(R.raw.data);
      CSVReader reader = new CSVReader(new InputStreamReader(is));
      List<String[]> result_arr = reader.readAll();
      ArrayList<String[]> result = (ArrayList<String[]>) (result_arr);
      return result;
    } catch (IOException e) {
      e.printStackTrace();
      return new ArrayList<String[]>();
    }
  }
}


