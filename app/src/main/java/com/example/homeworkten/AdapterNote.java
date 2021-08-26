package com.example.homeworkten;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterNote extends RecyclerView.Adapter<AdapterNote.MyViewHolder> {

    public CardSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(CardSource dataSource) {
        this.dataSource = dataSource;
        notifyDataSetChanged();
    }

    private com.example.homeworkten.CardSource dataSource;

    private Fragment fragment;

    private int menuContextOnClickPosition;

    public int getMenuContextOnClickPosition() {
        return menuContextOnClickPosition;
    }

    public AdapterNote( Fragment fragment) {
        this.fragment = fragment;
    }

    private com.example.homeworkten.MyOnClickListener listener;

    public void setOnMyOnClickListener(com.example.homeworkten.MyOnClickListener listener) {
        this.listener = listener;
    }

    public void setData(com.example.homeworkten.CardSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textNote.setText(dataSource.getCardData(position).getNote());
        holder.textDescription.setText(dataSource.getCardData(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textNote;
        private TextView textDescription;


        public MyViewHolder(View itemView) {
            super(itemView);
            textNote = itemView.findViewById(R.id.textNote);
            textDescription = itemView.findViewById(R.id.textDescription);

            fragment.registerForContextMenu(textNote);
            textNote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onMyClick(view, getAdapterPosition());
                }
            });

            textNote.setOnLongClickListener(new View.OnLongClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public boolean onLongClick(View view) {
                    menuContextOnClickPosition = getAdapterPosition();
                    textNote.showContextMenu(100, 100);
                    return false;
                }
            });
        }


    }
}
