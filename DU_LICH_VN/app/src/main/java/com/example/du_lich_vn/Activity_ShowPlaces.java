package com.example.du_lich_vn;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity_ShowPlaces extends AppCompatActivity {

    SQLiteDatabase database;
    Cursor curPlace;
    ListView lShow_Places;
    ArrayList<Class_Place> classPlaces = new ArrayList<Class_Place>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_places);
        try {
            database = openOrCreateDatabase("Database.db", MODE_PRIVATE, null);

            curPlace = database.query("PLACES", null, null, null, null, null, null);
            curPlace.moveToFirst();
            do {
                classPlaces.add(new Class_Place(Integer.parseInt(curPlace.getString(6)), curPlace.getString(1), curPlace.getString(2), curPlace.getString(3), curPlace.getString(4), curPlace.getString(5), curPlace.getString(7), curPlace.getString(0)));
            } while (curPlace.moveToNext());

        } catch (Exception ignored) {}

        Adapter_PlaceShow placeShowAdapter = new Adapter_PlaceShow(Activity_ShowPlaces.this, R.layout.place_item_of_show_custom, classPlaces);

        lShow_Places = findViewById(R.id.lShow_Places);
        lShow_Places.setAdapter(placeShowAdapter);

        lShow_Places.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent update_place = new Intent(Activity_ShowPlaces.this, Activity_ActionsPlace.class);
                Bundle bundle = new Bundle();
                bundle.putInt("CODE_UPDATE", 444);
                bundle.putSerializable("classPlace", classPlaces.get(i));
                update_place.putExtra("bundle", bundle);
                startActivity(update_place);
            }
        });

        lShow_Places.setLongClickable(true);
        lShow_Places.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String nametmp = classPlaces.get(i).getName();
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(), AlertDialog.BUTTON_NEGATIVE);
                builder.setMessage("Muốn xóa "+ nametmp + " thiệt hả?");
                builder.setTitle("Cái gì cơ");

                builder.setPositiveButton("Dẹt sơ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String code = classPlaces.get(i).getCode();
                        database.delete("PLACES", "PLACE_CODE=?", new String[]{ classPlaces.get(i).getCode() });
                        classPlaces.remove(i);
                        placeShowAdapter.notifyDataSetChanged();
                        Toast.makeText(view.getContext(), "Xóa " + nametmp +" thành công", Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("Nờ ô nô", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create().show();

                return true;
            }
        });




        findViewById(R.id.bBackToAdminTools_ShowPlaces).setOnClickListener(view -> {
            finish();
        });

    }
}