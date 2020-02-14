package com.silvertak.relationshipsmanager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.silvertak.relationshipsmanager.R;
import com.silvertak.relationshipsmanager.data.ContactPersonInfo;

import java.util.ArrayList;
import java.util.Queue;

public class ContactRankingAdapter extends RecyclerView.Adapter<ContactRankingAdapter.CustomViewHolder> {

    private ArrayList<ContactPersonInfo> contactPersonInfos;

    public ContactRankingAdapter(ArrayList<ContactPersonInfo> queue)
    {
        this.contactPersonInfos = queue;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position)
    {
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_ranking_item, parent, false);
        CustomViewHolder customViewHolder = new CustomViewHolder(view);
        return customViewHolder;
    }

    @Override
    public int getItemCount() {
        return contactPersonInfos.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView profileIv;
        private TextView nameTv;
        private TextView lastContactDateTv;

        public CustomViewHolder(View view)
        {
            super(view);

            profileIv = view.findViewById(R.id.profileIv);
            nameTv = view.findViewById(R.id.nameTv);
            lastContactDateTv = view.findViewById(R.id.lastContactDateTv);
        }
    }
}
