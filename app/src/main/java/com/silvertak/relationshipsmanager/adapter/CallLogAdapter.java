package com.silvertak.relationshipsmanager.adapter;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.silvertak.relationshipsmanager.customInterface.OnContactInfoClick;
import com.silvertak.relationshipsmanager.data.PersonRelationshipInfo;
import com.silvertak.relationshipsmanager.databinding.MostContactRankingItemBinding;

public class CallLogAdapter extends RecyclerView.Adapter<CallLogAdapter.CustomViewHolder> {

    private PersonRelationshipInfo personRelationshipInfo;

    public CallLogAdapter()
    {
    }

    public void setContactInfos(PersonRelationshipInfo info)
    {
        this.personRelationshipInfo = info;
        this.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, final int position)
    {
        holder.bind(personRelationshipInfo.getCallLogInfos().get(position));
        setAnimation(holder.itemView);
    }

    private void setAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(300);
        view.startAnimation(anim);
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MostContactRankingItemBinding contactRankingItemBinding = MostContactRankingItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CustomViewHolder(contactRankingItemBinding);
    }

    @Override
    public int getItemCount() {
        return personRelationshipInfo.getCallLogInfos().size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder
    {
        private MostContactRankingItemBinding mBinding;

        public CustomViewHolder(MostContactRankingItemBinding binding)
        {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        void bind(PersonRelationshipInfo info)
        {
            mBinding.setPersonRelationshipInfo(info);
        }
    }
}
