package com.example.homeworkten;

import android.content.Context;
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

    private Navigation navigation;
    private Publisher publisher;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        MainActivity activity = (MainActivity) context;
        navigation = activity.getNavigation();
        publisher = activity.getPublisher();
    }

    @Override
    public void onDetach() {
        navigation = null;
        publisher = null;
        super.onDetach();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_note, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);


        adapterNote = new AdapterNote(this);
        adapterNote.setOnMyOnClickListener(new MyOnClickListener() {
            @Override
            public void onMyClick(View view, int position) {
                Toast.makeText(getContext(), "button" + position, Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapterNote);
       /* data = new CardSourceImpl(getResources()).init(new CardSourceResponse() {
            @Override
            public void initialized(CardSource cardSource) {
                adapterNote.notifyDataSetChanged();
            }
        });*/
        data = new CardsSourceFirebaseImpl().init(new CardSourceResponse() {
            @Override
            public void initialized(CardSource cardSource) {
                adapterNote.notifyDataSetChanged();
            }
        });

        adapterNote.setDataSource(data);
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
                navigation.addFragment(CardUpdateFragment.newInstance(), true);
                publisher.subscribe(new Observer() {
                    @Override
                    public void updateState(CardData cardData) {
                        data.addCardData(cardData);
                        adapterNote.notifyItemInserted(data.size() - 1);
                    }
                });
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
                navigation.addFragment(CardUpdateFragment.newInstance(data.getCardData(position)), true);
                publisher.subscribe(new Observer() {
                    @Override
                    public void updateState(CardData cardData) {
                        data.updateCardData(position, cardData);
                        adapterNote.notifyItemChanged(position);
                    }
                });
                return true;
            case R.id.card_action_clear:
                data.deleteCardData(position);
                adapterNote.notifyItemRemoved(position);
                return true;
        }
        return super.onContextItemSelected(item);
    }
}
