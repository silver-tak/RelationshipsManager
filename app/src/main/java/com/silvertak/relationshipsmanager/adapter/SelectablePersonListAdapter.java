package com.silvertak.relationshipsmanager.adapter;

import android.content.Context;
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

import com.silvertak.relationshipsmanager.R;
import com.silvertak.relationshipsmanager.customInterface.OnContactInfoClick;
import com.silvertak.relationshipsmanager.data.PersonRelationshipInfo;
import com.silvertak.relationshipsmanager.databinding.SelectablePersonItemBinding;
import com.silvertak.relationshipsmanager.library.StringLib;

public class SelectablePersonListAdapter extends RecyclerView.Adapter<SelectablePersonListAdapter.CustomViewHolder> implements RecyclerView.OnItemTouchListener{

    private ObservableArrayList<PersonRelationshipInfo> contactInfos = new ObservableArrayList<>();
    private ObservableArrayList<PersonRelationshipInfo> selectInfos = new ObservableArrayList<>();
    private OnContactInfoClick contactInfoClickListener;
    private String strSearchText = "";
    private Context mContext;

    public SelectablePersonListAdapter(Context context){
        mContext = context;
    }

    public void setContactInfos(ObservableArrayList<PersonRelationshipInfo> infos)
    {
        this.contactInfos = infos;
    }

    public void setSelectInfos(ObservableArrayList<PersonRelationshipInfo> infos)
    {
        this.selectInfos = infos;
    }

    public void executeSearch(String strSearchText)
    {
        this.strSearchText = strSearchText;
        notifyDataSetChanged();
    }

    private boolean isSearchTextContain(PersonRelationshipInfo info)
    {
        if(info.getContactInfo() == null
                || StringLib.isEmpty(info.getContactInfo().getName())
                || StringLib.isEmpty(info.getContactInfo().getPhoneNumber()))
            return false;

        if(info.getContactInfo().getName().contains(this.strSearchText)
                || info.getContactInfo().getPhoneNumber().contains(this.strSearchText))
            return true;
        else return false;
    }

    private boolean isSearchMode()
    {
        if(StringLib.isEmpty(this.strSearchText)) return false;
        else return true;
    }

    private int getSearchCount()
    {
        int nResult = 0;
        for(PersonRelationshipInfo info : contactInfos)
            if(isSearchTextContain(info)) nResult++;
        return nResult;
    }

    private PersonRelationshipInfo getNextData(int position) {
        int nSearchCount = 0;
        PersonRelationshipInfo resultInfo = null;
        for (int i = 0; i < contactInfos.size(); i++)
        {
            PersonRelationshipInfo info = contactInfos.get(i);
            if(isSearchTextContain(info))
            {
                if(position == nSearchCount) {resultInfo = info; break;}
                nSearchCount++;
            }
        }
        return resultInfo;
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder holder, final int position)
    {
        PersonRelationshipInfo info = (isSearchMode()) ? getNextData(position) : contactInfos.get(position);
        holder.bind(info, mContext);
        holder.mBinding.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
            }
        });
        //if(!isSearchMode())
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
        if(isSearchMode())
            return getSearchCount();
        else
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

        void bind(PersonRelationshipInfo info, Context context)
        {
            mBinding.setPersonRelationshipInfo(info);
            initBackgroundColor(info, context);
        }

        void initBackgroundColor(PersonRelationshipInfo info, Context context)
        {
            if(info.getSelected().getValue())
                mBinding.backgroundLayout.setBackgroundColor(context.getResources().getColor(R.color.hashtag));
            else
                mBinding.backgroundLayout.setBackgroundColor(context.getResources().getColor(R.color.fontColor));
        }

        void setSelectValue(boolean bValue)
        {
            PersonRelationshipInfo info = mBinding.getPersonRelationshipInfo();
            info.setSelected(bValue);
            mBinding.setPersonRelationshipInfo(info);
        }
    }
}
