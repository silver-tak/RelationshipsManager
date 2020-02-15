package com.silvertak.relationshipsmanager.adapter;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.silvertak.relationshipsmanager.customInterface.OnContactInfoClick;
import com.silvertak.relationshipsmanager.data.ContactInfo;
import com.silvertak.relationshipsmanager.databinding.ContactRankingItemBinding;

public class ContactRankingAdapter extends RecyclerView.Adapter<ContactRankingAdapter.CustomViewHolder> implements RecyclerView.OnItemTouchListener{

    private ObservableArrayList<ContactInfo> contactInfos;
    private OnContactInfoClick contactInfoClickListener;

    public ContactRankingAdapter(ObservableArrayList<ContactInfo> queue)
    {
        this.contactInfos = queue;
    }

    public ContactRankingAdapter()
    {
    }

    public void setContactInfos(ObservableArrayList<ContactInfo> infos)
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
        holder.mBinding.callIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactInfoClickListener.onContactInfoClick(contactInfos.get(position));
            }
        });
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_ranking_item, parent, false);
        //CustomViewHolder customViewHolder = new CustomViewHolder(view);
        ContactRankingItemBinding contactRankingItemBinding = ContactRankingItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
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
        private ContactRankingItemBinding mBinding;

        public CustomViewHolder(ContactRankingItemBinding binding)
        {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        void bind(ContactInfo info)
        {
            mBinding.setInfo(info);
        }
    }
}
