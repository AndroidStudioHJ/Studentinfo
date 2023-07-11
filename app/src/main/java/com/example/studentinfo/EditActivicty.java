package com.example.studentinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import org.w3c.dom.Text;

public class EditActivicty extends AppCompatActivity implements View.OnClickListener {

    TextView nameValueTextView, birthValueTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activicty);

        nameValueTextView = findViewById(R.id.nameValueTextView);
        birthValueTextView = findViewById(R.id.birthValueTextView);

        findViewById(R.id.birthDateLayer).setOnClickListener(this); // 날짜 레이어 클릭
        findViewById(R.id.saveButton).setOnClickListener(this); // 저장 버튼

        readData();
    }

    private void readData() {
        SharedPreferences sp = getSharedPreferences(getString(R.string.sp), MODE_PRIVATE);
        nameValueTextView.setText(sp.getString("이름", "무명"));
        birthValueTextView.setText(sp.getString("생일", "모름"));
        //TODO 혈액형, 기타정보를 읽어서 화면에 표시
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.birthDateLayer:
                // 데이트 피커를 생성
                DatePickerDialog.OnDateSetListener myDatePicker = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                        ((TextView)findViewById(R.id.birthValueTextView)).setText(y + "/" + (m + 1) + "/" + d);
                    }
                };

                DatePickerDialog dialog = new DatePickerDialog(this, myDatePicker, 2023, 6, 11);
                dialog.show();
                break;
            case R.id.saveButton:
                // 데이터 저장
                saveData();
                break;
        }
    }

    private void saveData() {
        // 이름과 날짜 읽어서 sp에 저장한다.
        String name = nameValueTextView.getText().toString();
        String birth = birthValueTextView.getText().toString();
        // S_P의 editor 를 연다.
        SharedPreferences.Editor editor = getSharedPreferences(getString(R.string.sp), MODE_PRIVATE).edit();
        editor.putString("이름", name);
        editor.putString("생일", birth);
        editor.apply();

        finish();
    }
}





















