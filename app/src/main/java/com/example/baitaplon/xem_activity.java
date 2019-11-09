package com.example.baitaplon;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static java.lang.Integer.max;
import static java.lang.Integer.parseInt;

public class xem_activity extends AppCompatActivity {
    Button btn_them1,btn_sua, btn_xoa, btn_trove, btn_xemtc, btn_suachonngay;
    GridView gridView;
    EditText edt_suaten, edt_suasotien, edt_suanoidung, edt_maxoa;
    TextView edt_suangay;
    DBhelper dBhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_activity);
        edt_suangay = (TextView) findViewById(R.id.edt_SuaNgay);
        btn_sua = (Button) findViewById(R.id.btn_Sua);
        btn_them1=(Button)findViewById(R.id.btn_Them1);
        btn_xoa = (Button) findViewById(R.id.btn_Xoa);
        edt_maxoa = (EditText) findViewById(R.id.edt_maxoa);
        edt_suaten = (EditText) findViewById(R.id.edt_SuaTen);
        edt_suasotien = (EditText) findViewById(R.id.edt_SuaSoTien);
        edt_suanoidung = (EditText) findViewById(R.id.edt_SuaNoiDung);
        btn_trove = (Button) findViewById(R.id.btn_Trove);
        btn_trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_xemtc = (Button) findViewById(R.id.btn_Xemtc);
        btn_suachonngay = (Button) findViewById(R.id.btn_SuachonNgay);
        dBhelper = new DBhelper(xem_activity.this);
        gridView = (GridView) findViewById(R.id.gridview);
        btn_xemtc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> lst = new ArrayList<>();
                ArrayList<ChiTieu> chiTieuArrayList = new ArrayList<>();
                chiTieuArrayList = dBhelper.getAllChiTieu();
                for (ChiTieu chiTieu : chiTieuArrayList) {
                    lst.add(chiTieu.getMa() + "");
                    lst.add(chiTieu.getTen() + "");
                    lst.add(chiTieu.getNgay() + "");
                    lst.add(chiTieu.getSotien() + "");
                    lst.add(chiTieu.getNoidung() + "");
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(xem_activity.this, android.R.layout.simple_list_item_1, lst);
                gridView.setAdapter(adapter);
            }
        });
        btn_suachonngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(xem_activity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edt_suangay.setText(year + "/" + (month + 1) + "/" + dayOfMonth);
                    }
                }, year, month, day);

                datePickerDialog.show();
            }
        });
        btn_them1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(xem_activity.this, them_activity.class);
                startActivity(intent);
            }
        });
        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edt_maxoa.getText().toString().trim().isEmpty()==false && edt_suaten.getText().toString().trim().isEmpty()== false && edt_suangay.getText().toString().trim().isEmpty()==false
                        && edt_suasotien.getText().toString().trim().isEmpty()== false && edt_suanoidung.getText().toString().trim().isEmpty()==false){
                    ChiTieu chiTieu = new ChiTieu();
                    chiTieu.setMa(parseInt(edt_maxoa.getText().toString()));
                    chiTieu.setTen(edt_suaten.getText().toString());
                    chiTieu.setNgay(new Date(edt_suangay.getText().toString()));
                    chiTieu.setSotien(edt_suasotien.getText().toString());
                    chiTieu.setNoidung(edt_suanoidung.getText().toString());
                    if (dBhelper.updateChiTieu(chiTieu)) {
                        Toast.makeText(getApplicationContext(), "Đã Sửa Thành Công", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Sửa không thành công", Toast.LENGTH_LONG).show();
                    }}else {
                    Toast.makeText(getApplicationContext(),"Nhập dữ liệu để sửa",Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt_maxoa.getText().toString().trim().isEmpty()==false){
                    dBhelper = new DBhelper(xem_activity.this);
                    if (dBhelper.deleteChiTieu(parseInt(edt_maxoa.getText().toString()))) {
                        Toast.makeText(getApplicationContext(), "Xóa Thành Công", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Xóa Không Thành Công", Toast.LENGTH_LONG).show();
                    }}else {
                    Toast.makeText(getApplicationContext(),"Nhập Mã Để Xóa",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
