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
import com.silvertak.relationshipsmanager.data.RelationshipGroupInfo;
import com.silvertak.relationshipsmanager.databinding.ManageGroupInfoItemBinding;
import com.silvertak.relationshipsmanager.databinding.MostContactRankingItemBinding;

public class GroupListAdapter extends RecyclerView.Adapter<GroupListAdapter.CustomViewHolder> implements RecyclerView.OnItemTouchListener{

    private ObservableArrayList<RelationshipGroupInfo> groupInfos = new ObservableArrayList<>();

    public GroupListAdapter()
    {
    }

    public void setGroupInfos(ObservableArrayList<RelationshipGroupInfo> infos)
    {
        this.groupInfos = infos;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, final int position)
    {
        holder.bind(groupInfos.get(position));
        holder.mBinding.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        ManageGroupInfoItemBinding manageGroupInfoItemBinding = ManageGroupInfoItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CustomViewHolder(manageGroupInfoItemBinding);
    }

    @Override
    public int getItemCount() {
        return groupInfos.size();
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        if(e.getAction() == MotionEvent.ACTION_UP)
        {
            View childView = rv.findChildViewUnder(e.getX(), e.getY());
            if(childView != null)
            {
                int currentPosition = rv.getChildAdapterPosition(childView);
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
        private ManageGroupInfoItemBinding mBinding;

        public CustomViewHolder(ManageGroupInfoItemBinding binding)
        {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        void bind(RelationshipGroupInfo info)
        {
            mBinding.setRelationshipGroupInfo(info);
        }
    }
}
