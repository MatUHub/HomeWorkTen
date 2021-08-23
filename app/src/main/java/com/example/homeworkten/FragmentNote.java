package com.example.homeworkten;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentNote extends Fragment {
    public static FragmentNote newInstance() {
        return new FragmentNote();
    }

    private CardSource data;
    private AdapterNote adapterNote;
    private RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_note, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        data = new CardSourceImpl(getResources()).init();
        adapterNote = new AdapterNote(data, this);
        adapterNote.setOnMyOnClickListener(new MyOnClickListener() {
            @Override
            public void onMyClick(View view, int position) {
                Toast.makeText(getContext(), "button" + position, Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapterNote);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                data.addCardData(new CardData("New Запись " + (data.size() + 1), "new Дело " + (data.size() + 1)));
                adapterNote.notifyDataSetChanged();
                return true;
            case R.id.action_clear:
                data.clearCardData();
                adapterNote.notifyDataSetChanged();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        requireActivity().getMenuInflater().inflate(R.menu.card_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int position = adapterNote.getMenuContextOnClickPosition();
        switch (item.getItemId()) {
            case R.id.card_action_update:
                data.getCardData(position).setNote("Обновлено " + (position + 1));
                adapterNote.notifyItemChanged(position);
                return true;
            case R.id.card_action_clear:
                data.deleteCardData(position);
                adapterNote.notifyItemRemoved(position);
                return true;
        }
        return super.onContextItemSelected(item);
    }
}
