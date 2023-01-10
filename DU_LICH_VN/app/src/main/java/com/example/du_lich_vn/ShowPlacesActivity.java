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

public class ShowPlacesActivity extends AppCompatActivity {

    SQLiteDatabase database;
    Cursor curPlace;
    ListView lShow_Places;
    ArrayList<Place> places = new ArrayList<Place>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_places);
        try {
            database = openOrCreateDatabase("Database.db", MODE_PRIVATE, null);

            curPlace = database.query("PLACES", null, null, null, null, null, null);
            curPlace.moveToFirst();
            do {
                places.add(new Place(Integer.parseInt(curPlace.getString(6)), curPlace.getString(1), curPlace.getString(2), curPlace.getString(3), curPlace.getString(4), curPlace.getString(5), curPlace.getString(7), curPlace.getString(0)));
            } while (curPlace.moveToNext());

        } catch (Exception ignored) {}

        PlaceShowAdapter placeShowAdapter = new PlaceShowAdapter(ShowPlacesActivity.this, R.layout.place_item_of_show_custom, places);

        lShow_Places = findViewById(R.id.lShow_Places);
        lShow_Places.setAdapter(placeShowAdapter);

        lShow_Places.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent update_place = new Intent(ShowPlacesActivity.this, ActionsPlaceActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("CODE_UPDATE", 444);
                bundle.putSerializable("place", places.get(i));
                update_place.putExtra("bundle", bundle);
                startActivity(update_place);
            }
        });

        lShow_Places.setLongClickable(true);
        lShow_Places.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String nametmp = places.get(i).getName();
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(), AlertDialog.BUTTON_NEGATIVE);
                builder.setMessage("Muốn xóa "+ nametmp + " thiệt hả?");
                builder.setTitle("Cái gì cơ");

                builder.setPositiveButton("Dẹt sơ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String code = places.get(i).getCode();
                        database.delete("PLACES", "PLACE_CODE=?", new String[]{ places.get(i).getCode() });
                        places.remove(i);
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




        findViewById(R.id.bBackToAdminTools_Show).setOnClickListener(view -> {
            finish();
        });

    }
}