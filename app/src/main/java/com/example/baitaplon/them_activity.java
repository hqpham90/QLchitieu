package com.example.baitaplon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class them_activity extends AppCompatActivity {
    EditText  edt_sotien, edt_noidung, edt_ten;
    TextView edt_ngay;
    Button btn_luu, btn_nhapmoi, btn_trove,btn_chongngay,btn_xem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them);
        final DBhelper dbHelper = new DBhelper(them_activity.this);
        edt_ngay= (TextView) findViewById(R.id.edt_Ngay);
        edt_sotien=(EditText)findViewById(R.id.edt_sotien);
        edt_noidung=(EditText)findViewById(R.id.edt_Noidung);
        edt_ten=(EditText)findViewById(R.id.edt_Ten);
        btn_trove=(Button)findViewById(R.id.btn_TroVe1);
        btn_chongngay=(Button)findViewById(R.id.btn_Chonngay);
        btn_luu=(Button)findViewById(R.id.btn_luu);
        btn_xem=(Button)findViewById(R.id.btn_Xemtc1);
        btn_trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_nhapmoi = (Button)findViewById(R.id.btn_NhapMoi);
        btn_nhapmoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_ten.setText("");
                edt_ngay.setText("");
                edt_noidung.setText("");
                edt_sotien.setText("");
            }
        });
        btn_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edt_ten.getText().toString().trim().isEmpty()==false && edt_ngay.getText().toString().trim().isEmpty()==false &&
                edt_sotien.getText().toString().trim().isEmpty()==false && edt_noidung.getText().toString().trim().isEmpty()==false){
                ChiTieu chiTieu= new ChiTieu();
                chiTieu.setTen(edt_ten.getText().toString());
                chiTieu.setNgay(new Date(edt_ngay.getText().toString()));
                chiTieu.setSotien(edt_sotien.getText().toString());
                chiTieu.setNoidung(edt_noidung.getText().toString());
                if(dbHelper.insertChitieu(chiTieu))
                {
                    Toast.makeText(getApplicationContext(),"Đã Lưu Thành Công",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Lưu không thành công",Toast.LENGTH_LONG).show();
                }
            }else {
                Toast.makeText(getApplicationContext(),"Nhập đủ dữ liệu để lưu",Toast.LENGTH_LONG).show();}
            }
        });
        btn_chongngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(them_activity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edt_ngay.setText(year+"/"+(month+1)+"/"+dayOfMonth);
                    }
                },year,month,day);

                datePickerDialog.show();
            }
        });
        btn_xem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(them_activity.this,xem_activity.class);
                startActivity(intent);
            }
        });
    }
}
