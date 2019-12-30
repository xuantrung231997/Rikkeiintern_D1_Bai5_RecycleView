package gst.trainingcourse.rikkeiintern_d1_bai5_recycleview.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import gst.trainingcourse.rikkeiintern_d1_bai5_recycleview.DTO.ContactInfoDTO;
import gst.trainingcourse.rikkeiintern_d1_bai5_recycleview.ListenerCustomAdapterClick;
import gst.trainingcourse.rikkeiintern_d1_bai5_recycleview.ListenerCustomAdapterLongClick;
import gst.trainingcourse.rikkeiintern_d1_bai5_recycleview.R;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private List<ContactInfoDTO> mListContact;
    private ListenerCustomAdapterClick mListenerCustomAdapterClick;
    private ListenerCustomAdapterLongClick mListenerCustomAdapterLongClick;

    public CustomAdapter(List<ContactInfoDTO> mListContact, ListenerCustomAdapterClick mListenerCustomAdapterClick,
                         ListenerCustomAdapterLongClick mListenerCustomAdapterLongClick) {
        this.mListContact = mListContact;
        this.mListenerCustomAdapterClick = mListenerCustomAdapterClick;
        this.mListenerCustomAdapterLongClick = mListenerCustomAdapterLongClick;
    }


    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_adapter_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        holder.mTxtName.setText(mListContact.get(position).getmName());
        holder.mTxtName.setTag(position);
        holder.mTxtPhone.setText(mListContact.get(position).getmPhone());
        holder.mTxtPhone.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mListContact.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mTxtName;
        private TextView mTxtPhone;
        private CheckBox mCheckBox;
        private boolean mCheckLongClick = false;

        private View.OnClickListener mItemViewClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListenerCustomAdapterClick.onClick(mListContact.get(getAdapterPosition()));
            }
        };
        private View.OnLongClickListener mItemViewLongClick = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mCheckLongClick=true;
                mListenerCustomAdapterLongClick.onClick(mListContact.get(getAdapterPosition()));
//                setVisiableCheckBox();
                mCheckBox.setVisibility(View.VISIBLE);
                return true;
            }
        };
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTxtName = itemView.findViewById(R.id.tvName);
            mTxtPhone = itemView.findViewById(R.id.tvPhone);
            mCheckBox=itemView.findViewById(R.id.checkBox);
            itemView.setOnClickListener(mItemViewClick);
            itemView.setOnLongClickListener(mItemViewLongClick);
//            if(mCheckLongClick) mCheckBox.setVisibility(View.VISIBLE);
        }
        public void setVisiableCheckBox(){
            if(mCheckLongClick) mCheckBox.setVisibility(View.VISIBLE);
        }
    }
}
