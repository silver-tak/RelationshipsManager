package com.silvertak.relationshipsmanager.adapter;

import android.os.Handler;
import android.util.Log;
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
import com.silvertak.relationshipsmanager.databinding.SelectablePersonItemBinding;

public class SelectablePersonListAdapter extends RecyclerView.Adapter<SelectablePersonListAdapter.CustomViewHolder> implements RecyclerView.OnItemTouchListener{

    private ObservableArrayList<PersonRelationshipInfo> contactInfos = new ObservableArrayList<>();
    private ObservableArrayList<PersonRelationshipInfo> selectInfos = new ObservableArrayList<>();
    private OnContactInfoClick contactInfoClickListener;

    public SelectablePersonListAdapter(){
    }

    public void setContactInfos(ObservableArrayList<PersonRelationshipInfo> infos)
    {
        this.contactInfos = infos;
        //this.notifyDataSetChanged();
    }

    public void setSelectInfos(ObservableArrayList<PersonRelationshipInfo> infos)
    {
        this.selectInfos = infos;
        //this.notifyDataSetChanged();
    }

    public void setContactInfoClickListener(OnContactInfoClick contactInfoClick)
    {
        contactInfoClickListener = contactInfoClick;
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder holder, final int position)
    {
        holder.bind(contactInfos.get(position));
        holder.mBinding.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if(contactInfoClickListener != null) contactInfoClickListener.onContactInfoClick(contactInfos.get(position));
                PersonRelationshipInfo info = contactInfos.get(position);
                if(contactInfos.get(position).getSelected().getValue())
                {
                    info.setSelected(false);
                    holder.setSelectValue(false);
                    if(selectInfos.contains(info)) selectInfos.remove(info);
                }
                else
                {
                    info.setSelected(true);
                    holder.setSelectValue(true);
                    selectInfos.add(info);
                }

                //holder.itemView.requestLayout();
            }
        });
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
        SelectablePersonItemBinding selectablePersonItemBinding = SelectablePersonItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CustomViewHolder(selectablePersonItemBinding);
    }

    @Override
    public int getItemCount() {
        return contactInfos.size();
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
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
        private SelectablePersonItemBinding mBinding;

        public CustomViewHolder(SelectablePersonItemBinding binding)
        {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        void bind(PersonRelationshipInfo info)
        {
            mBinding.setPersonRelationshipInfo(info);
        }

        void setSelectValue(boolean bValue)
        {
            PersonRelationshipInfo info = mBinding.getPersonRelationshipInfo();
            info.setSelected(bValue);
            mBinding.setPersonRelationshipInfo(info);
        }
    }
}
