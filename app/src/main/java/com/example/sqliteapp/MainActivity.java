package com.example.sqliteapp;

import android.app.AlertDialog;
import android.database.Cursor;
//import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    Button btnSearch;
    EditText searchText;
    EditText editName, editSurname, editTextId;
    Button btnAddData, btnviewAll, btnDelete, btnviewUpdate;
    EditText editid, editpux, editpapbl, editgrafkom;
    Button btnAddData_2, btnviewAll_2, btnDelete_2, btnviewUpdate_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        btnSearch = (Button) findViewById(R.id.button_search);
//        btnSearch_2 = (Button) findViewById(R.id.button_search_2);
        searchText = (EditText) findViewById(R.id.editText_search);

        editName = (EditText) findViewById(R.id.editText_name);
        editSurname = (EditText) findViewById(R.id.editText_surname);
        editTextId = (EditText) findViewById(R.id.editText_id);
        btnAddData = (Button) findViewById(R.id.button_add);
        btnviewAll = (Button) findViewById(R.id.button_viewAll);
        btnviewUpdate = (Button) findViewById(R.id.button_update);
        btnDelete = (Button) findViewById(R.id.button_delete);

        editid = (EditText) findViewById(R.id.editText_id_2);
        editpux = (EditText) findViewById(R.id.editText_pux_2);
        editpapbl = (EditText) findViewById(R.id.editText_papbl_2);
        editgrafkom = (EditText) findViewById(R.id.editText_grafkom_2);
        btnAddData_2 = (Button) findViewById(R.id.button_add_2);
        btnviewAll_2 = (Button) findViewById(R.id.button_viewAll_2);
        btnviewUpdate_2 = (Button) findViewById(R.id.button_update_2);
        btnDelete_2 = (Button) findViewById(R.id.button_delete_2);

        search();
//        search_2();

        AddData();
        viewAll();
        UpdateData();
        DeleteData();

        AddData_2();
        viewAll_2();
        UpdateData_2();
        DeleteData_2();
    }

    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(editTextId.getText().toString());
                        if (deletedRows > 0)
                            Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data not Deleted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void DeleteData_2() {
        btnDelete_2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData_2(editid.getText().toString());
                        if (deletedRows > 0)
                            Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data not Deleted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void UpdateData() {
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(editTextId.getText().toString(),
                                editName.getText().toString(),
                                editSurname.getText().toString());
                        if (isUpdate == true)
                            Toast.makeText(MainActivity.this, "Data Update", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data not Updated", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void UpdateData_2() {
        btnviewUpdate_2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData_2(editid.getText().toString(),
                                editpapbl.getText().toString(),
                                editpux.getText().toString(), editgrafkom.getText().toString());
                        if (isUpdate == true)
                            Toast.makeText(MainActivity.this, "Data Update", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data not Updated", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(editName.getText().toString(),
                                editSurname.getText().toString());
                        if (isInserted == true)
                            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void AddData_2() {
        btnAddData_2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData_2(editid.getText().toString(),
                                editpapbl.getText().toString(),
                                editpux.getText().toString(),
                                editgrafkom.getText().toString());
                        if (isInserted == true)
                            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if (res.getCount() == 0) {
                            // show message
                            showMessage("Error", "Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :" + res.getString(0) + "\n");
                            buffer.append("Nama Lengkap :" + res.getString(1) + "\n");
                            buffer.append("NIM :" + res.getString(2) + "\n\n");
                        }

                        // Show all data
                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }

    public void viewAll_2() {
        btnviewAll_2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData_2();
                        if (res.getCount() == 0) {
                            // show message
                            showMessage("Error", "Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :" + res.getString(0) + "\n");
                            buffer.append("Nama Barang :" + res.getString(2) + "\n");
                            buffer.append("Harga :" + res.getString(1) + "\n");
                            buffer.append("Jumlah :" + res.getString(3) + "\n\n");
                        }

                        // Show all data
                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }

    public void search() {
        btnSearch.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String textSearch = searchText.getText().toString();
                        if (searchText.getText().toString() != null) {
                            Cursor res = myDb.searchData(textSearch);
                            if (res.getCount() == 0) {
                                // show message
                                showMessage("Error", "Nothing found");
                                return;
                            }

                            StringBuffer buffer = new StringBuffer();
                            while (res.moveToNext()) {
                                buffer.append("Id :" + res.getString(0) + "\n");
                                buffer.append("Nama Lengkap :" + res.getString(1) + "\n");
                                buffer.append("NIM :" + res.getString(2) + "\n\n");
                                Cursor res2 = myDb.searchData_2(res.getString(0));
                                if (res2.getCount() == 0) {
                                    buffer.append(res.getString(1) + " tidak memiliki barang\n");
                                } else {
                                    while (res2.moveToNext()) {
                                        buffer.append("Nama Barang : " + res2.getString(2) + "\n");
                                        buffer.append("Harga : " + res2.getString(1) + "\n");
                                        buffer.append("Jumlah : " + res2.getString(3) + "\n\n");
                                    }
                                }
                                buffer.append("=====\n");
                            }

                            // Show all data
                            showMessage("Data", buffer.toString());
                        } else
                            Toast.makeText(MainActivity.this, "Can't search", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }


//    public void search_2() {
//        btnSearch_2.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        String textSearch = searchText.getText().toString();
//                        if (searchText.getText().toString() != null) {
//                            Cursor res = myDb.searchData_2(textSearch);
//                            if (res.getCount() == 0) {
//                                // show message
//                                showMessage("Error", "Nothing found");
//                                return;
//                            }
//
//                            StringBuffer buffer = new StringBuffer();
//                            while (res.moveToNext()) {
//                                buffer.append("Id :" + res.getString(0) + "\n");
//                                buffer.append("Nama Barang :" + res.getString(1) + "\n");
//                                buffer.append("Harga :" + res.getString(2) + "\n");
//                                buffer.append("Jumlah :" + res.getString(3) + "\n\n");
//                            }
//
//                            // Show all data
//                            showMessage("Data", buffer.toString());
//                        } else
//                            Toast.makeText(MainActivity.this, "Can't search", Toast.LENGTH_LONG).show();
//                    }
//                }
//        );
//    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
