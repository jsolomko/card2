package com.example.card20.adapters;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.card20.R;
import com.example.card20.data.Card;

import java.util.List;

public class CardAdapter extends ListAdapter<Card, CardAdapter.CardVH> {
    LiveData<List<Card>> listCards;

    //Слушатель нажатий на элемент RV
    public interface OnClickCardListener {
        void onClick(Card card, int position);
    }

    private OnClickCardListener onClickCardListener;

    //Слушатель нажатий на кнопку
    public interface OnClickBtnListener {
        void onClick(Card card, int position);
    }

    private OnClickBtnListener onClickBtnListener;


    public CardAdapter(@NonNull DiffUtil.ItemCallback<Card> diffCallback, OnClickCardListener onClickCardListener,OnClickBtnListener onClickBtnListener) {
        super(diffCallback);
        this.onClickCardListener = onClickCardListener;
        this.onClickBtnListener = onClickBtnListener;
    }

    static class CardVH extends RecyclerView.ViewHolder {
        TextView cardTitle;
        ImageView cardFrontImage;
        Button button;

        public CardVH(@NonNull View itemView) {
            super(itemView);
            cardTitle = itemView.findViewById(R.id.tv_card_title);
            cardFrontImage = itemView.findViewById(R.id.card_img);
            button = itemView.findViewById(R.id.btn_edit);
        }
    }

    @NonNull
    @Override
    public CardVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_2, parent, false);
        return new CardVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CardVH holder, int position) {
        Card card = getItem(position);
        holder.cardTitle.setText(card.getCardTitle());
        holder.cardFrontImage.setImageURI(Uri.parse(card.getCard_front()));
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickBtnListener.onClick(card,holder.getAdapterPosition());
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickCardListener.onClick(card, holder.getAdapterPosition());
            }
        });

    }


    static public class CardDiff extends DiffUtil.ItemCallback<Card> {
        @Override
        public boolean areItemsTheSame(@NonNull Card oldItem, @NonNull Card newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Card oldItem, @NonNull Card newItem) {
            return oldItem.getCardTitle().equals(newItem.getCardTitle());
        }
    }

}
