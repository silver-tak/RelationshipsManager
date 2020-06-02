package com.silvertak.relationshipsmanager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.silvertak.relationshipsmanager.Consts;
import com.silvertak.relationshipsmanager.vo.CallLogVO;
import com.silvertak.relationshipsmanager.vo.PersonRelationshipInfo;
import com.silvertak.relationshipsmanager.databinding.CallLogItemBinding;

public class CallLogAdapter extends RecyclerView.Adapter<CallLogAdapter.CustomViewHolder> {

    private PersonRelationshipInfo personRelationshipInfo;

    public CallLogAdapter()
    {
    }

    public void setPersonRelationshipInfo(PersonRelationshipInfo info)
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
        anim.setDuration(Consts.FADE_ANIMATION_DURATION);
        view.startAnimation(anim);
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CallLogItemBinding callLogItemBinding = CallLogItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CustomViewHolder(callLogItemBinding);
    }

    @Override
    public int getItemCount() {
        return personRelationshipInfo.getCallLogInfos().size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder
    {
        private CallLogItemBinding mBinding;

        public CustomViewHolder(CallLogItemBinding binding)
        {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        void bind(CallLogVO info)
        {
            mBinding.setCallLogInfo(info);
        }
    }
}
