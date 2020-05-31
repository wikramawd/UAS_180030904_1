package com.bh183.withadharma;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class GitarAdapter extends RecyclerView.Adapter<GitarAdapter.GitarViewHolder> {

    private Context context;
    private ArrayList<Gitar> dataGitar;
    private SimpleDateFormat sdFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.getDefault());

    public GitarAdapter(Context context, ArrayList<Gitar> dataGitar) {
        this.context = context;
        this.dataGitar = dataGitar;
    }

    @NonNull
    @Override
    public GitarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_gitar, parent, false);
        return new GitarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GitarViewHolder holder, int position) {
        Gitar tempGitar = dataGitar.get(position);
        holder.idGitar = tempGitar.getIdGitar();
        holder.tvNama.setText(tempGitar.getNama());
        holder.tvHeadline.setText(tempGitar.getKeterangan());
        holder.tanggal = sdFormat.format(tempGitar.getTanggal());
        holder.gambar = tempGitar.getGambar();
        holder.caption = tempGitar.getCaption();
        holder.link = tempGitar.getLink();

        try{
            File file = new File(holder.gambar);
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            holder.imgGitar.setImageBitmap(bitmap);
            holder.imgGitar.setContentDescription(holder.gambar);
        }catch (FileNotFoundException er){
            er.printStackTrace();
            Toast.makeText(context, "Gagal mengambil gambar dari media penyimpanan", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {

        return dataGitar.size();
    }

    public class GitarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        private ImageView imgGitar;
        private TextView tvNama, tvHeadline;
        private int idGitar;
        private String tanggal, gambar, caption, link;

        public GitarViewHolder(@NonNull View itemView) {
            super(itemView);

            imgGitar = itemView.findViewById(R.id.iv_gitar);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvHeadline = itemView.findViewById(R.id.tv_headline);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Intent bukaGitar = new Intent(context, TampilActivity.class);
            bukaGitar.putExtra("ID", idGitar);
            bukaGitar.putExtra("NAMA", tvNama.getText().toString());
            bukaGitar.putExtra("TANGGAL", tanggal);
            bukaGitar.putExtra("GAMBAR", gambar);
            bukaGitar.putExtra("CAPTION", caption);
            bukaGitar.putExtra("KETERANGAN", tvHeadline.getText().toString());
            bukaGitar.putExtra("LINK", link);
            context.startActivity(bukaGitar);

        }

        @Override
        public boolean onLongClick(View v) {

            Intent bukaInput = new Intent(context, InputActivity.class);
            bukaInput.putExtra("OPERASI", "update");
            bukaInput.putExtra("ID", idGitar);
            bukaInput.putExtra("NAMA", tvNama.getText().toString());
            bukaInput.putExtra("TANGGAL", tanggal);
            bukaInput.putExtra("GAMBAR", gambar);
            bukaInput.putExtra("CAPTION", caption);
            bukaInput.putExtra("KETERANGAN", tvHeadline.getText().toString());
            bukaInput.putExtra("LINK", link);
            context.startActivity(bukaInput);
            return true;
        }
    }
}
