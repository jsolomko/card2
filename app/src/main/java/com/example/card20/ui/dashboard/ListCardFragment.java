package com.example.card20.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.card20.R;
import com.example.card20.adapters.CardAdapter;
import com.example.card20.data.Card;
import com.example.card20.databinding.FragmentListCardBinding;

import java.util.List;

public class ListCardFragment extends Fragment {

    private FragmentListCardBinding binding;
    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ListCardViewModel listCardViewModel =
                new ViewModelProvider(this).get(ListCardViewModel.class);

        binding = FragmentListCardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = root.findViewById(R.id.rv_dashboard);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        CardAdapter.OnClickCardListener onClickCardListener = new CardAdapter.OnClickCardListener() {
            @Override
            public void onClick(Card card, int position) {

            }
        };
        CardAdapter cardAdapter = new CardAdapter(new CardAdapter.CardDiff(), onClickCardListener);
        recyclerView.setAdapter(cardAdapter);
        listCardViewModel.getListRepo().observe(getViewLifecycleOwner(), new Observer<List<Card>>() {
            @Override
            public void onChanged(List<Card> cards) {
                cardAdapter.submitList(cards);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}