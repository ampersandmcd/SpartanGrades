package com.andrewmcdonald27.spartan_grades;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.model.GradientColor;

import java.util.ArrayList;
import java.util.List;

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
    final String[] messages = intent.getStringArrayExtra(SearchResults.key);

    course_code.setText("Course Code: "+ messages[1] + " " + messages[2]);
    course_title.setText("Course Title: " + messages[3]);
    String professor_str = getProfessor(messages[4]);
    professor.setText("Professor(s): " + professor_str);
    String semester_str = getSemester(messages[0]);
    semester.setText("Semester: " + semester_str);
    averageGPA.setText("Average GPA: " + messages[5]); //Added GPA


    Button compare_button = (Button) findViewById(R.id.compare_button);
    compare_button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        ((ComparisonArray)ClassInfo.this.getApplication()).insertComparisonArray(messages);
      }
    });

    Button view_button = (Button) findViewById(R.id.view_button);
    view_button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(ClassInfo.this, ViewComparison.class);
        startActivity(intent);
      }
    });

    BarChart chart = (BarChart) findViewById(R.id.chart);

    //create dataset of gpa distribution to graph
    ArrayList<BarEntry> entries = new ArrayList<>();
    entries.add(new BarEntry(0f, Integer.parseInt(messages[6])));
    entries.add(new BarEntry(1f, Integer.parseInt(messages[7])));
    entries.add(new BarEntry(2f, Integer.parseInt(messages[8])));
    entries.add(new BarEntry(3f, Integer.parseInt(messages[9])));
    entries.add(new BarEntry(4f, Integer.parseInt(messages[10])));
    entries.add(new BarEntry(5f, Integer.parseInt(messages[11])));
    entries.add(new BarEntry(6f, Integer.parseInt(messages[12])));
    entries.add(new BarEntry(7f, Integer.parseInt(messages[13])));

    //create x axis labels
    final ArrayList<String> x_labels = new ArrayList<>();
    x_labels.add("4.0");
    x_labels.add("3.5");
    x_labels.add("3.0");
    x_labels.add("2.5");
    x_labels.add("2.0");
    x_labels.add("1.5");
    x_labels.add("1.0");
    x_labels.add("0");

    //set data of chart
    BarDataSet dataset = new BarDataSet(entries, "Grade Distribution");

    int color_4 = ContextCompat.getColor(this, android.R.color.holo_green_dark);
    int color_3_5 = ContextCompat.getColor(this, android.R.color.holo_green_light);
    int color_3 = ContextCompat.getColor(this, android.R.color.holo_orange_light);
    int color_2_5 = ContextCompat.getColor(this, android.R.color.holo_orange_dark);
    int color_2 = ContextCompat.getColor(this, android.R.color.holo_red_light);
    int color_1_5 = ContextCompat.getColor(this, android.R.color.holo_red_dark);
    int color_1 = ContextCompat.getColor(this, android.R.color.holo_purple);
    int color_0 = ContextCompat.getColor(this, android.R.color.black);


    List<GradientColor> gradient_colors = new ArrayList<>();
    gradient_colors.add(new GradientColor(color_4, color_4)); //4.0 color
    gradient_colors.add(new GradientColor(color_3_5, color_3_5)); //3.5 color
    gradient_colors.add(new GradientColor(color_3, color_3)); //3.0 color
    gradient_colors.add(new GradientColor(color_2_5, color_2_5)); //2.5 color
    gradient_colors.add(new GradientColor(color_2, color_2)); //2.0 color
    gradient_colors.add(new GradientColor(color_1_5, color_1_5)); //1.5 color
    gradient_colors.add(new GradientColor(color_1, color_1)); //1.0 color
    gradient_colors.add(new GradientColor(color_0, color_0)); //0.0 color
    dataset.setGradientColors(gradient_colors);

    BarData data = new BarData(dataset);

    Legend legend = chart.getLegend();
    legend.setEnabled(false);

    chart.setDrawGridBackground(false);

    XAxis x_axis = chart.getXAxis();
    x_axis.setPosition(XAxis.XAxisPosition.BOTTOM);
    x_axis.setDrawGridLines(false);
    x_axis.setDrawLabels(true);
    x_axis.setValueFormatter(new IAxisValueFormatter() {
      @Override
      public String getFormattedValue(float value, AxisBase axis) {
        return x_labels.get((int) value);
      }
    });

    YAxis right_axis = chart.getAxisRight();
    right_axis.setDrawGridLines(false);
    right_axis.setEnabled(false);

    YAxis y_axis = chart.getAxisRight();
    y_axis.setDrawGridLines(false);
    y_axis.setSpaceBottom(0.0f);

    int width = chart.getWidth();
    int height = chart.getHeight();

    Description description = chart.getDescription();
    description.setText("");
    description.setPosition(3f,3f);
    chart.setDescription(description);
    chart.setData(data);

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
