package com.example.thithitkgiaodinandroid;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

public class Fragment_Add extends Fragment {

    private Button btnThem;
    private SqliteData sqliteData;
    private EditText edtSKU,edtTacGia,edtSoTrang,edtGia,edtGioiThieu;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add,container,false);

        sqliteData = new SqliteData(getActivity());

        btnThem = view.findViewById(R.id.btnThemData);
        final TextView tvNgay = view.findViewById(R.id.tvNgay);
        edtSKU = view.findViewById(R.id.edtSKU);
        edtTacGia = view.findViewById(R.id.edtTacGia);
        edtSoTrang = view.findViewById(R.id.edtSoTrang);
        edtGia = view.findViewById(R.id.edtGia);
        edtGioiThieu = view.findViewById(R.id.edtGioiThieu);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data data = new Data();

                data.setSKU(edtSKU.getText().toString().trim());
                data.setTacGia(edtTacGia.getText().toString().trim());
                data.setNgayXuatBan(tvNgay.getText().toString());
                data.setSoTrang(edtSoTrang.getText().toString().trim());
                data.setGia(edtGia.getText().toString().trim());
                data.setGioiThieu(edtGioiThieu.toString().trim());

                long result =  sqliteData.insertData(data);

                if (result>0){
                    Toast.makeText(getActivity(),"Thành Công",Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(getActivity(),"thêm thất bại",Toast.LENGTH_SHORT).show();

                }
            }
        });

        //chon ngay

        Button btnChonNgay  = view.findViewById(R.id.btnChonNgay);
        btnChonNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar lich = Calendar.getInstance();

                DatePickerDialog pickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tvNgay.setText(dayOfMonth + "-" +(month+1) + "-" + year);
                    }
                },lich.get(Calendar.YEAR)
                        ,lich.get(Calendar.MONTH)
                        ,lich.get(Calendar.DAY_OF_MONTH));
                pickerDialog.show();
                    }
        });
        return view;
    }
}
