package com.myapplication.advancedlibrary;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {
    public Context context;
    public List<Books> booksList;
    Animation search_anim;

    public  BookAdapter(Context context, List<Books> books){
        this.context = context;
        booksList  = books;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Books book = booksList.get(position);
        holder.book_text.setText(book.getTitle());
        holder.author_text.setText(book.getAuthor());
        holder.page_count.setText(book.getPage_count());
        Glide.with(context).load(book.getImage_link()).into(holder.book_image);
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl(book.getMore());

            }
        });
    }

    private void gotoUrl(String more) {
        Uri uri = Uri.parse(more);
        context.startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }


    @Override
    public int getItemCount() {

        return booksList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView book_image;
        TextView book_text, author_text, page_count;
        ConstraintLayout constraintLayout;
        LinearLayout anim;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            book_text = itemView.findViewById(R.id.book_text);
            author_text = itemView.findViewById(R.id.author_text);
            page_count = itemView.findViewById(R.id.page_count);
            book_image = itemView.findViewById(R.id.book_image);
            constraintLayout = itemView.findViewById(R.id.mainLayout);
            anim = itemView.findViewById(R.id.anim);
            search_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            anim.setAnimation(search_anim);

        }
    }
}
