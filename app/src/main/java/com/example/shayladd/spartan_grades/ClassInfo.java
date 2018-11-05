package com.example.shayladd.spartan_grades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ClassInfo extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_class_info);
    setTitle("Spartan Grades");
    TextView course_code = (TextView)findViewById(R.id.course_code);
    TextView course_title = (TextView)findViewById(R.id.course_title);
    TextView professor = (TextView)findViewById(R.id.professor);
    TextView semester = (TextView)findViewById(R.id.semester);
    TextView averageGPA = (TextView)findViewById(R.id.avgGPA);

    Intent intent = getIntent();
    String[] messages = intent.getStringArrayExtra(SearchResults.key);

    course_code.setText("Course Code: "+ messages[1] + " " + messages[2]);
    course_title.setText("Course Title: " + messages[3]);
    String professor_str = getProfessor(messages[4]);
    professor.setText("Professor(s): " + professor_str);
    String semester_str = getSemester(messages[0]);
    semester.setText("Semester: " + semester_str);
    averageGPA.setText("GPA: " + messages[5]); //Added GPA
  }

  private String getSemester(String str) {
    String result = "";
    String prefix = str.substring(0,2);

    if (prefix.equals("FS")) {
      result += "Fall ";
    } else if (prefix.equals("SS")) {
      result += "Spring ";
    } else if (prefix.equals("US")) {
      result += "Summer ";
    } else {
      result += "Undefined ";
    }

    result += "20" + str.substring(2);
    return result;
  }

  private String getProfessor(String str) {
    String result = "";
    String last_ch = " ";

    for (int i = 0; i < str.length(); i++){
      String ch = str.substring(i, i+1);

      if (ch.equals("|")) {
        result += "&";
      } else if (last_ch.equals(" ")) {
        result += ch.toUpperCase();
      } else {
        result += ch.toLowerCase();
      }

      last_ch = ch;
    }

    return result;
  }
}