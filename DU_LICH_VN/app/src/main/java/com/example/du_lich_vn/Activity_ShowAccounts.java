package com.example.du_lich_vn;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity_ShowAccounts extends AppCompatActivity {

    final int CODE_UPDATE = 444;

    SQLiteDatabase database;
    Cursor curAccount;
    ListView lShow_Accounts;
    ArrayList<Class_Account> classAccounts = new ArrayList<Class_Account>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_accounts);
        try {
            database = openOrCreateDatabase("Database.db", MODE_PRIVATE, null);
            curAccount = database.query("ACCOUNTS", null, null, null, null, null, null);
            curAccount.moveToFirst();
            do {
                classAccounts.add(new Class_Account(curAccount.getString(0),curAccount.getString(1),curAccount.getString(2),curAccount.getString(3)));
            } while (curAccount.moveToNext());

        } catch (Exception ignored) {}

        Adapter_AccountShow adapterAccountShow = new Adapter_AccountShow(Activity_ShowAccounts.this, R.layout.place_item_of_show_custom, classAccounts);

        lShow_Accounts = findViewById(R.id.lShow_Accounts);
        lShow_Accounts.setAdapter(adapterAccountShow);

        lShow_Accounts.setOnItemClickListener((adapterView, view, i, l) -> {
                Intent update_place = new Intent(Activity_ShowAccounts.this, Activity_ActionsAccount.class);
                Bundle bundle = new Bundle();
                bundle.putInt("CODE_UPDATE", CODE_UPDATE);
                bundle.putSerializable("classAccount", classAccounts.get(i));
                update_place.putExtra("bundle", bundle);
                startActivity(update_place);
        });

        lShow_Accounts.setLongClickable(true);
        lShow_Accounts.setOnItemLongClickListener((adapterView, view, i, l) -> {
            if (i > 1) {
                String nametmp = classAccounts.get(i).get_name();
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(), AlertDialog.BUTTON_NEGATIVE);
                builder.setMessage("Muốn xóa "+ nametmp + " thiệt hả?");
                builder.setTitle("Cái gì cơ");

                builder.setPositiveButton("Dẹt sơ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        database.delete("ACCOUNTS", "_ID=?", new String[]{ classAccounts.get(i).get_id() });
                        classAccounts.remove(i);
                        adapterAccountShow.notifyDataSetChanged();
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
            }
            return true;
        });

        findViewById(R.id.bBackToAdminTools_ShowAccounts).setOnClickListener(view -> {
            finish();
        });

    }
}