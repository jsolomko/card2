package com.example.card20.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.card20.R;
import com.example.card20.adapters.CardAdapter;
import com.example.card20.data.Card;
import com.example.card20.databinding.FragmentListCardBinding;
import com.example.card20.ui.CustomDialogFragment;

import java.util.List;

public class ListCardFragment extends Fragment {

    private FragmentListCardBinding binding;
    RecyclerView recyclerView;
    Button btn_edit;

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
                Bundle bundle = new Bundle();
                bundle.putParcelable(Card.class.getSimpleName(), card);


                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                CustomDialogFragment newFragment = new CustomDialogFragment();

                newFragment.setArguments(bundle);

                FragmentTransaction transaction = fragmentManager.beginTransaction();
                // For a little polish, specify a transition animation
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                // To make it fullscreen, use the 'content' root view as the container
                // for the fragment, which is always the root view for the activity
                transaction.add(android.R.id.content, newFragment)
                        .addToBackStack(null).commit();
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

    public void showDialog() {
        Bundle bundle = new Bundle();


        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        CustomDialogFragment newFragment = new CustomDialogFragment();

        newFragment.setArguments(bundle);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // For a little polish, specify a transition animation
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        // To make it fullscreen, use the 'content' root view as the container
        // for the fragment, which is always the root view for the activity
        transaction.add(android.R.id.content, newFragment)
                .addToBackStack(null).commit();


    }

}