package com.example.card20.ui.deletecarddialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.card20.R;
import com.example.card20.data.Card;
import com.example.card20.ui.dashboard.ListCardViewModel;

public class DeleteCardDialogFragment extends Fragment {
    Button yes, no;
    ListCardViewModel listCardViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.delete_dialog2, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        yes = view.findViewById(R.id.btn_yes);
        no = view.findViewById(R.id.btn_no);

        ListCardViewModel listCardViewModel =
                new ViewModelProvider(this).get(ListCardViewModel.class);

        Bundle bundle = this.getArguments();
        Card card = bundle.getParcelable(Card.class.getSimpleName());
        yes.setOnClickListener(view1 -> {
            listCardViewModel.deleteCard(card);

            getActivity().getSupportFragmentManager().beginTransaction().remove(DeleteCardDialogFragment.this).commit();
            Toast.makeText(getContext(),"Карта удалена",Toast.LENGTH_SHORT).show();
        });

        no.setOnClickListener(view1 -> {
            getActivity().getSupportFragmentManager().beginTransaction().remove(DeleteCardDialogFragment.this).commit();
        });
    }
}
