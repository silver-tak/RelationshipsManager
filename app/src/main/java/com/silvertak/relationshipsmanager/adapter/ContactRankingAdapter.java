package com.silvertak.relationshipsmanager.adapter;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.silvertak.relationshipsmanager.Consts;
import com.silvertak.relationshipsmanager.customInterface.OnContactInfoClick;
import com.silvertak.relationshipsmanager.vo.PersonRelationshipInfo;
import com.silvertak.relationshipsmanager.databinding.MostContactRankingItemBinding;

public class ContactRankingAdapter extends RecyclerView.Adapter<ContactRankingAdapter.CustomViewHolder> implements RecyclerView.OnItemTouchListener{

    private ObservableArrayList<PersonRelationshipInfo> contactInfos = new ObservableArrayList<>();
    private OnContactInfoClick contactInfoClickListener;

    public ContactRankingAdapter()
    {
    }

    public void setContactInfos(ObservableArrayList<PersonRelationshipInfo> infos)
    {
        this.contactInfos = infos;
        this.notifyDataSetChanged();
    }

    public void setContactInfoClickListener(OnContactInfoClick contactInfoClick)
    {
        this.contactInfoClickListener = contactInfoClick;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, final int position)
    {
        holder.bind(contactInfos.get(position));
        holder.mBinding.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactInfoClickListener.onContactInfoClick(contactInfos.get(position));
            }
        });
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
        MostContactRankingItemBinding contactRankingItemBinding = MostContactRankingItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CustomViewHolder(contactRankingItemBinding);
    }

    @Override
    public int getItemCount() {
        return contactInfos.size();
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        if(e.getAction() == MotionEvent.ACTION_UP)
        {
            View childView = rv.findChildViewUnder(e.getX(), e.getY());
            if(childView != null)
            {
                int currentPosition = rv.getChildAdapterPosition(childView);
                if(contactInfos.size() > currentPosition)
                    contactInfoClickListener.onContactInfoClick(contactInfos.get(currentPosition));
            }
        }

        return true;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
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
