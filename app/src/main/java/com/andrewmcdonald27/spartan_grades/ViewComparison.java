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

import static com.andrewmcdonald27.spartan_grades.SearchResults.key;

public class ViewComparison extends AppCompatActivity {

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_comparison);
    setTitle("Spartan Grades");

    ArrayList<String[]> comparison_table = ((ComparisonArray)ViewComparison.this.getApplication()).getComparisonArray();

    TableLayout table = (TableLayout) findViewById(R.id.table);
    TableLayout header = (TableLayout) findViewById(R.id.header);

    if (comparison_table.size() < 1) {
      TextView output = new TextView(this);
      output.setText("No matching classes found.");
      TableRow table_row = new TableRow(this);
      TableRow.LayoutParams output_params = new TableRow.LayoutParams((int) ((1 / 8.0) * table.getWidth()), TableRow.LayoutParams.WRAP_CONTENT, 1);
      header.addView(output, output_params);
    } else {
      //add header entries
      String[] temp_header = comparison_table.get(0);

      TableRow table_row_header = new TableRow(this);

      VerticalTextView semester_header = new VerticalTextView(this);
      semester_header.setText(temp_header[0]);
      semester_header.setTypeface(null, Typeface.BOLD);
      semester_header.setTextColor(Color.BLACK);
      TableRow.LayoutParams semester_params = new TableRow.LayoutParams(table.getWidth(), TableRow.LayoutParams.WRAP_CONTENT, (float)0.1);
      semester_params.rightMargin = 10;
      table_row_header.addView(semester_header, semester_params);

      VerticalTextView subject_code_header = new VerticalTextView(this);
      subject_code_header.setText(temp_header[2]);
      subject_code_header.setTypeface(null, Typeface.BOLD);
      subject_code_header.setTextColor(Color.BLACK);
      TableRow.LayoutParams subject_code_params = new TableRow.LayoutParams(table.getWidth(), TableRow.LayoutParams.WRAP_CONTENT, (float)0.1);
      subject_code_params.rightMargin = 10;
      table_row_header.addView(subject_code_header, subject_code_params);

      VerticalTextView course_title_header = new VerticalTextView(this);
      course_title_header.setText(temp_header[3]);
      course_title_header.setTypeface(null, Typeface.BOLD);
      course_title_header.setTextColor(Color.BLACK);
      TableRow.LayoutParams course_title_params = new TableRow.LayoutParams(table.getWidth(), TableRow.LayoutParams.WRAP_CONTENT, (float)0.15);
      course_title_params.rightMargin = 10;
      table_row_header.addView(course_title_header, course_title_params);

      VerticalTextView professor_header = new VerticalTextView(this);
      professor_header.setText(temp_header[4]);
      professor_header.setTypeface(null, Typeface.BOLD);
      professor_header.setTextColor(Color.BLACK);
      TableRow.LayoutParams professor_params = new TableRow.LayoutParams(table.getWidth(), TableRow.LayoutParams.WRAP_CONTENT, (float)0.25);
      professor_params.rightMargin = 10;
      table_row_header.addView(professor_header, professor_params);

      VerticalTextView avg_gpa_header = new VerticalTextView(this);
      avg_gpa_header.setText(temp_header[5]);
      avg_gpa_header.setTypeface(null, Typeface.BOLD);
      avg_gpa_header.setTextColor(Color.BLACK);
      TableRow.LayoutParams avg_gpa_params = new TableRow.LayoutParams(table.getWidth(), TableRow.LayoutParams.WRAP_CONTENT, (float)0.15);
      table_row_header.addView(avg_gpa_header, avg_gpa_params);

      VerticalTextView more_info_header = new VerticalTextView(this);
      more_info_header.setText("More Info...");
      more_info_header.setTypeface(null, Typeface.BOLD);
      more_info_header.setTextColor(Color.BLACK);
      TableRow.LayoutParams btn_params = new TableRow.LayoutParams(table.getWidth(), TableRow.LayoutParams.WRAP_CONTENT, (float)0.1);
      table_row_header.addView(more_info_header, btn_params);

      VerticalTextView btn_header = new VerticalTextView(this);
      btn_header.setText("Delete");
      btn_header.setTypeface(null, Typeface.BOLD);
      btn_header.setTextColor(Color.BLACK);
      table_row_header.addView(btn_header, btn_params);

      header.addView(table_row_header);

      //adjust params before adding class entries

      semester_params = new TableRow.LayoutParams(table.getWidth(), TableRow.LayoutParams.WRAP_CONTENT, (float)0.12);
      subject_code_params = new TableRow.LayoutParams(table.getWidth(), TableRow.LayoutParams.WRAP_CONTENT, (float)0.12);
      course_title_params = new TableRow.LayoutParams(table.getWidth(), TableRow.LayoutParams.WRAP_CONTENT, (float)0.2);
      professor_params = new TableRow.LayoutParams(table.getWidth(), TableRow.LayoutParams.WRAP_CONTENT, (float)0.25);
      avg_gpa_params = new TableRow.LayoutParams(table.getWidth(), TableRow.LayoutParams.WRAP_CONTENT, (float)0.15);
      btn_params = new TableRow.LayoutParams(table.getWidth(), TableRow.LayoutParams.WRAP_CONTENT, (float)0.08);
      /*
      TableRow table_row_header = new TableRow(this);

      TextView semester_header = new TextView(this);
      semester_header.setText(temp_header[0]);
      semester_header.setWidth(0);
      final TableRow.LayoutParams semester_params = new TableRow.LayoutParams(table.getWidth(), TableRow.LayoutParams.WRAP_CONTENT, (float) 0.05);
      semester_params.rightMargin = 10;
      table_row_header.addView(semester_header, semester_params);

      TextView subject_code_header = new TextView(this);
      subject_code_header.setText(temp_header[2]);
      subject_code_header.setWidth(0);
      final TableRow.LayoutParams subject_code_params = new TableRow.LayoutParams(table.getWidth(), TableRow.LayoutParams.WRAP_CONTENT, (float) 0.1);
      subject_code_params.rightMargin = 10;
      table_row_header.addView(subject_code_header, subject_code_params);

      TextView course_title_header = new TextView(this);
      course_title_header.setText(temp_header[3]);
      final TableRow.LayoutParams course_title_params = new TableRow.LayoutParams(table.getWidth(), TableRow.LayoutParams.WRAP_CONTENT, (float) 0.3);
      course_title_params.rightMargin = 10;
      table_row_header.addView(course_title_header, course_title_params);

      TextView professor_header = new TextView(this);
      professor_header.setText(temp_header[4]);
      final TableRow.LayoutParams professor_params = new TableRow.LayoutParams(table.getWidth(), TableRow.LayoutParams.WRAP_CONTENT, (float) 0.3);
      professor_params.rightMargin = 10;
      table_row_header.addView(professor_header, professor_params);

      TextView avg_gpa_header = new TextView(this);
      avg_gpa_header.setText(temp_header[5]);
      final TableRow.LayoutParams avg_gpa_params = new TableRow.LayoutParams(table.getWidth(), TableRow.LayoutParams.WRAP_CONTENT, (float) 0.15);
      table_row_header.addView(avg_gpa_header, avg_gpa_params);

      TextView btn_header = new TextView(this);
      btn_header.setText("Remove");
      final TableRow.LayoutParams btn_params = new TableRow.LayoutParams(table.getWidth(), TableRow.LayoutParams.WRAP_CONTENT, (float) 0.1);
      table_row_header.addView(btn_header, btn_params);

      //table.addView(table_row_header);
      //add class entries
      */

      addClassEntries(comparison_table, table_row_header, semester_params, subject_code_params, course_title_params, professor_params, avg_gpa_params, btn_params);
    }
  }

  private void addClassEntries(final ArrayList<String[]> comparison_table, final TableRow table_row_header, final TableRow.LayoutParams semester_params, final TableRow.LayoutParams subject_code_params, final TableRow.LayoutParams course_title_params, final TableRow.LayoutParams professor_params, final TableRow.LayoutParams avg_gpa_params, final TableRow.LayoutParams btn_params ) {
    TableLayout table = (TableLayout) findViewById(R.id.table);
    table.removeAllViews();

    for (int i = 1; i < comparison_table.size(); i++) {
      String[] temp = comparison_table.get(i);

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

      Button more_info = new Button(this);
      more_info.setId(i);
      more_info.setOnClickListener(new Button.OnClickListener(){
        public void onClick(View v) {
          Button btn = (Button)v;
          int index = btn.getId();
          Intent intent = new Intent(ViewComparison.this, ClassInfo.class);
          String[] class_info = comparison_table.get(index);
          intent.putExtra(key, class_info);
          startActivity(intent);
        }
      });
      table_row.addView(more_info, btn_params);

      Button btn = new Button(this);
      btn.setId(i);
      btn.setOnClickListener(new Button.OnClickListener(){
        public void onClick(View v) {
          Button btn = (Button)v;
          ((ComparisonArray)ViewComparison.this.getApplication()).removeEntry(btn.getId());
          ArrayList<String[]> new_comparison_table = ((ComparisonArray)ViewComparison.this.getApplication()).getComparisonArray();
          addClassEntries(new_comparison_table, table_row_header, semester_params, subject_code_params, course_title_params, professor_params, avg_gpa_params, btn_params);
        }
      });
      table_row.addView(btn, btn_params);

      table.addView(table_row);

    }
  }
}
