package com.andrewmcdonald27.spartan_grades;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;


public class SearchResults extends AppCompatActivity implements View.OnClickListener {

  ArrayList<String[]> global_results = new ArrayList<String[]>();
  public static final String key = "com.shayladd.spartan_grades";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search_results);
    setTitle("Spartan Grades");

    ArrayList<String[]> master = new ArrayList<String[]>();
    ArrayList<String[]> results = new ArrayList<String[]>();
    //TextView output = (TextView) findViewById(R.id.output);
    TableLayout table = (TableLayout) findViewById(R.id.table);
    TableLayout header = (TableLayout) findViewById(R.id.header);

    //get search type and search term from search page call
    Intent intent = getIntent();
    String[] messages = intent.getStringArrayExtra(MainActivity.key);
    String search_by = messages[0];
    String search_term = messages[1];
    //output.setText(search_by + ": "  + search_term);

    //import data from master CSV (located under app/res/raw/data.csv)

    master = ((ComparisonArray)SearchResults.this.getApplication()).getMasterArray();

    //decide which search method to call and store results in results ArrayList
    if (search_by.equals("Course Code")) {
      results = searchCode(search_term, master);
    } else if (search_by.equals("Course Title")) {
      results = searchTitle(search_term, master);
    } else if (search_by.equals("Professor")) {
      results = searchProf(search_term, master);
    } else {
      TextView output = new TextView(this);
      output.setText("Error.");
      TableRow table_row = new TableRow(this);
      TableRow.LayoutParams output_params = new TableRow.LayoutParams((int) ((1 / 8.0) * table.getWidth()), TableRow.LayoutParams.WRAP_CONTENT, 1);
      table.addView(output, output_params);
    }

    global_results = results;

    if (results.size() < 1) {
      TextView output = new TextView(this);
      output.setText("No matching classes found. Try revising your search term and re-searching.");
      TableRow table_row = new TableRow(this);
      TableRow.LayoutParams output_params = new TableRow.LayoutParams(table.getWidth(), TableRow.LayoutParams.WRAP_CONTENT, 1);
      table_row.addView(output);
      header.addView(table_row, output_params);
    } else {
      //add header entries
      String[] temp_header = master.get(0);
      ((ComparisonArray) SearchResults.this.getApplication()).insertComparisonArray(temp_header);

      TableRow table_row_header = new TableRow(this);

      VerticalTextView semester_header = new VerticalTextView(this);
      semester_header.setText(temp_header[0]);
      semester_header.setTypeface(null, Typeface.BOLD);
      semester_header.setTextColor(Color.BLACK);
      TableRow.LayoutParams semester_params = new TableRow.LayoutParams(table.getWidth(), TableRow.LayoutParams.WRAP_CONTENT, (float) 0.1);
      semester_params.rightMargin = 10;
      table_row_header.addView(semester_header, semester_params);

      VerticalTextView subject_code_header = new VerticalTextView(this);
      subject_code_header.setText(temp_header[2]);
      subject_code_header.setTypeface(null, Typeface.BOLD);
      subject_code_header.setTextColor(Color.BLACK);
      TableRow.LayoutParams subject_code_params = new TableRow.LayoutParams(table.getWidth(), TableRow.LayoutParams.WRAP_CONTENT, (float) 0.1);
      subject_code_params.rightMargin = 10;
      table_row_header.addView(subject_code_header, subject_code_params);

      VerticalTextView course_title_header = new VerticalTextView(this);
      course_title_header.setText(temp_header[3]);
      course_title_header.setTypeface(null, Typeface.BOLD);
      course_title_header.setTextColor(Color.BLACK);
      TableRow.LayoutParams course_title_params = new TableRow.LayoutParams(table.getWidth(), TableRow.LayoutParams.WRAP_CONTENT, (float) 0.2);
      course_title_params.rightMargin = 10;
      table_row_header.addView(course_title_header, course_title_params);

      VerticalTextView professor_header = new VerticalTextView(this);
      professor_header.setText(temp_header[4]);
      professor_header.setTypeface(null, Typeface.BOLD);
      professor_header.setTextColor(Color.BLACK);
      TableRow.LayoutParams professor_params = new TableRow.LayoutParams(table.getWidth(), TableRow.LayoutParams.WRAP_CONTENT, (float) 0.25);
      professor_params.rightMargin = 10;
      table_row_header.addView(professor_header, professor_params);

      VerticalTextView avg_gpa_header = new VerticalTextView(this);
      avg_gpa_header.setText(temp_header[5]);
      avg_gpa_header.setTypeface(null, Typeface.BOLD);
      avg_gpa_header.setTextColor(Color.BLACK);
      TableRow.LayoutParams avg_gpa_params = new TableRow.LayoutParams(table.getWidth(), TableRow.LayoutParams.WRAP_CONTENT, (float) 0.15);
      table_row_header.addView(avg_gpa_header, avg_gpa_params);

      VerticalTextView btn_header = new VerticalTextView(this);
      btn_header.setText("More Info...");
      btn_header.setTypeface(null, Typeface.BOLD);
      btn_header.setTextColor(Color.BLACK);
      TableRow.LayoutParams btn_params = new TableRow.LayoutParams(table.getWidth(), TableRow.LayoutParams.WRAP_CONTENT, (float) 0.15);
      table_row_header.addView(btn_header, btn_params);

      header.addView(table_row_header);

      //adjust params before adding class entries

      semester_params = new TableRow.LayoutParams(table.getWidth(), TableRow.LayoutParams.WRAP_CONTENT, (float) 0.12);
      subject_code_params = new TableRow.LayoutParams(table.getWidth(), TableRow.LayoutParams.WRAP_CONTENT, (float) 0.12);
      course_title_params = new TableRow.LayoutParams(table.getWidth(), TableRow.LayoutParams.WRAP_CONTENT, (float) 0.2);
      professor_params = new TableRow.LayoutParams(table.getWidth(), TableRow.LayoutParams.WRAP_CONTENT, (float) 0.25);
      avg_gpa_params = new TableRow.LayoutParams(table.getWidth(), TableRow.LayoutParams.WRAP_CONTENT, (float) 0.15);
      btn_params = new TableRow.LayoutParams(table.getWidth(), TableRow.LayoutParams.WRAP_CONTENT, (float) 0.08);

      //add class entries
      for (int i = 0; i < results.size(); i++) {
        String[] temp = results.get(i);

        TableRow table_row = new TableRow(this);

        TextView semester = new TextView(this);
        semester.setText(temp[0]);
        semester.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f);
        //TableRow.LayoutParams semester_params = new TableRow.LayoutParams((int) ((1 / 20) * table.getWidth()), TableRow.LayoutParams.WRAP_CONTENT, 1);
        semester_params.rightMargin = 10;
        table_row.addView(semester, semester_params);

        TextView subject_code = new TextView(this);
        subject_code.setText(temp[1] + " " + temp[2] + " ");
        subject_code.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f);
        //TableRow.LayoutParams subject_code_params = new TableRow.LayoutParams((int) ((1 / 20) * table.getWidth()), TableRow.LayoutParams.WRAP_CONTENT, 1);
        subject_code_params.rightMargin = 10;
        table_row.addView(subject_code, subject_code_params);

        TextView course_title = new TextView(this);
        course_title.setText(temp[3]);
        course_title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f);
        //TableRow.LayoutParams course_title_params = new TableRow.LayoutParams((int) ((3 / 8.0) * table.getWidth()), TableRow.LayoutParams.WRAP_CONTENT, 1);
        course_title_params.rightMargin = 10;
        table_row.addView(course_title, course_title_params);

        TextView professor = new TextView(this);
        professor.setText(temp[4]);
        professor.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f);
        //TableRow.LayoutParams professor_params = new TableRow.LayoutParams((int) ((2 / 8.0) * table.getWidth()), TableRow.LayoutParams.WRAP_CONTENT, 1);
        professor_params.rightMargin = 10;
        table_row.addView(professor, professor_params);

        TextView avg_gpa = new TextView(this);
        avg_gpa.setText(temp[5]);
        avg_gpa.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f);
        //TableRow.LayoutParams avg_gpa_params = new TableRow.LayoutParams((int) ((1 / 8.0) * table.getWidth()), TableRow.LayoutParams.WRAP_CONTENT, 1);
        table_row.addView(avg_gpa, avg_gpa_params);

        Button btn = new Button(this);
        btn.setId(i);
        btn.setOnClickListener(this);
        btn.setText("");
        //TableRow.LayoutParams btn_params = new TableRow.LayoutParams((int) ((1 / 8.0) * table.getWidth()), TableRow.LayoutParams.WRAP_CONTENT, 1);
        table_row.addView(btn, btn_params);

        table.addView(table_row);
      }
    }
  }

  private static ArrayList<String[]> searchCode(String search_term, ArrayList<String[]> master) {
    ArrayList<String[]> result = new ArrayList<String[]>();
    String subject = "";
    String number = "";
    boolean number_flag = false;
    for (int i = 0; i < search_term.length(); i++) {
      char c = search_term.charAt(i);
      if (Character.isLetter(c) && !number_flag) {
        //add to subject code
        subject += c;
      } else if (Character.isDigit(c) || number_flag) {
        //add to course number
        number += c;
        number_flag = true;
      }
    }
    subject = subject.toUpperCase();
    number = number.toUpperCase(); //in the case that the number contains an "H" honors denotation
    //
    for (String[] ele : master) {
      if (subject.equals(ele[1]) && number.equals(ele[2])) {
        result.add(ele);
      }
    }
    return result;
  }

  private static ArrayList<String[]> searchProf(String prof_name, ArrayList<String[]> master) {
    ArrayList<String[]> result = new ArrayList<String[]>();
    prof_name = prof_name.trim();
    prof_name = prof_name.toUpperCase();
    String[] tokenized_prof_name = prof_name.split(" ");
    for (String[] ele : master) {
      String names_of_profs = ele[4].toUpperCase();
      boolean flag = true;
      for (String t_name : tokenized_prof_name) {
        if (names_of_profs.indexOf(t_name.trim()) == -1) {
          flag = false;
        }
      }
      if (flag == true) {
        result.add(ele);
      }
    }
    return result;
  }

  private static ArrayList<String[]> searchTitle(String title, ArrayList<String[]> master) {
    ArrayList<String[]> result = new ArrayList<String[]>();
    title = title.trim();
    title = title.toUpperCase();
    String[] tokenized_class_title = title.split(" ");
    for (String[] ele : master) {
      String master_title = ele[3].toUpperCase();
      boolean flag = true;
      for (String t_title : tokenized_class_title) {
        if (master_title.indexOf(t_title.trim()) == -1) {
          flag = false;
        }
      }
      if (flag == true) {
        result.add(ele);
      }
    }
    return result;
  }

  @Override
  public void onClick(View v) {
    Button btn = (Button)v;
    int index = btn.getId();
    Intent intent = new Intent(SearchResults.this, ClassInfo.class);
    String[] class_info = global_results.get(index);
    intent.putExtra(key, class_info);
    startActivity(intent);
  }
}
