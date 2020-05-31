package com.bh183.withadharma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;

public class TampilActivity extends AppCompatActivity {

    private ImageView imgGitar;
    private TextView tvNama, tvTanggal, tvCaption, tvKeterangan;
    private String link;
    private SimpleDateFormat sdFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil);

        imgGitar = findViewById(R.id.iv_gitar);
        tvNama = findViewById(R.id.tv_nama);
        tvTanggal = findViewById(R.id.tv_tanggal);
        tvCaption = findViewById(R.id.tv_caption);
        tvKeterangan = findViewById(R.id.tv_keterangan);

        Intent terimaData = getIntent();
        tvNama.setText(terimaData.getStringExtra("NAMA"));
        tvTanggal.setText(terimaData.getStringExtra("TANGGAL"));
        tvCaption.setText(terimaData.getStringExtra("CAPTION"));
        tvKeterangan.setText(terimaData.getStringExtra("KETERANGAN"));
        String imgLocation = terimaData.getStringExtra("GAMBAR");

        try{
            File file = new File(imgLocation);
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            imgGitar.setImageBitmap(bitmap);
            imgGitar.setContentDescription(imgLocation);
        }catch (FileNotFoundException er){
            er.printStackTrace();
            Toast.makeText(this, "Gagal mengambil gambar dari media penyimpanan", Toast.LENGTH_SHORT).show();
        }

        link = terimaData.getStringExtra("LINK");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tampil_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.item_bagikan)
        {
            Intent bagikanGitar = new Intent(Intent.ACTION_SEND);
            bagikanGitar.putExtra(Intent.EXTRA_SUBJECT, tvNama.getText().toString());
            bagikanGitar.putExtra(Intent.EXTRA_TEXT, link);
            bagikanGitar.setType("text/plain");
            startActivity(Intent.createChooser(bagikanGitar, "Bagikan"));
        }

        return super.onOptionsItemSelected(item);
    }
}
