package com.example.homeworkten;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Date;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CardUpdateFragment extends Fragment {

    private static final String ARG_CARD_DATA = "Param_CardData";

    private TextInputEditText note;
    private TextInputEditText descripton;
    private DatePicker datePicker;

    private CardData cardData;
    private Publisher publisher;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        publisher = ((MainActivity) context).getPublisher();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        publisher = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_card, container, false);
        initView(view);

        if (cardData != null) {
            populateView();
        }
        return view;
    }

    private void populateView() {
        note.setText(cardData.getNote());
        descripton.setText(cardData.getDescription());
        initDatePicker(cardData.getDate());

    }

    private void initDatePicker(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        this.datePicker.init(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                null);
    }

    private Date getDateFromDatePicker() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, this.datePicker.getYear());
        calendar.set(Calendar.MONTH, this.datePicker.getMonth());
        calendar.set(Calendar.DAY_OF_MONTH, this.datePicker.getDayOfMonth());
        return calendar.getTime();
    }


    private void initView(View view) {
        note = view.findViewById(R.id.inputTitle);
        descripton = view.findViewById(R.id.inputDescription);
        datePicker = view.findViewById(R.id.inputDate);
    }

    public static CardUpdateFragment newInstance(CardData cardData) {
        CardUpdateFragment fragment = new CardUpdateFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_CARD_DATA, cardData);
        fragment.setArguments(args);
        return fragment;
    }

    public static CardUpdateFragment newInstance() {
        CardUpdateFragment fragment = new CardUpdateFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cardData = getArguments().getParcelable(ARG_CARD_DATA);
        }
    }

    private CardData collectCardData() {
        String note = this.note.getText().toString();
        String description = this.descripton.getText().toString();
        Date date = getDateFromDatePicker();
        return new CardData(note, description, date);
    }

    @Override
    public void onStop() {
        super.onStop();
        cardData = collectCardData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        publisher.notifyTask(cardData);

    }
}
