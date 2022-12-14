package com.example.card20.ui.customdialog;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.card20.R;
import com.example.card20.data.Card;

public class CustomDialogFragment extends Fragment {
    ImageView imageView;

    /**
     * The system calls this to get the DialogFragment's layout, regardless
     * of whether it's being displayed as a dialog or an embedded fragment.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout to use as dialog or embedded fragment
        return inflater.inflate(R.layout.custom_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Bundle bundle = this.getArguments();
        if (bundle != null) {

            Card card = bundle.getParcelable(Card.class.getSimpleName());

            imageView = view.findViewById(R.id.imageView2);

            imageView.setImageURI(Uri.parse(card.getCard_front()));

            Handler handler = new Handler();


            imageView.setOnClickListener(view1 -> {
                getActivity().getSupportFragmentManager().beginTransaction().remove(CustomDialogFragment.this).commit();
            });
        }


    }
}