package gst.trainingcourse.rikkeiintern_d1_bai5_recycleview;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import gst.trainingcourse.rikkeiintern_d1_bai5_recycleview.DAO.ContactInfoDAO;
import gst.trainingcourse.rikkeiintern_d1_bai5_recycleview.DTO.ContactInfoDTO;
import gst.trainingcourse.rikkeiintern_d1_bai5_recycleview.adapter.CustomAdapter;
import gst.trainingcourse.rikkeiintern_d1_bai5_recycleview.dialog.CustomDialogAdd;
import gst.trainingcourse.rikkeiintern_d1_bai5_recycleview.dialog.CustomDialogInsert;

import static gst.trainingcourse.rikkeiintern_d1_bai5_recycleview.R.layout.*;

public class MainActivity extends AppCompatActivity {
    private List<ContactInfoDTO> mListContact;
    private CustomAdapter mCustomAdapter;
    private RecyclerView mRecyclerViewContact;
    private Switch mSwitchView;
    private ImageButton mImgBtnAdd;
    private CustomDialogAdd mCustomDialogAdd;
    private CustomDialogInsert mCustomDialogInsert;
    private Button mBtnDeleteMain;
    private Button mBtnCancelMain;

    private CompoundButton.OnCheckedChangeListener mSwitchViewClick = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            if (!isChecked) {
                initAdapter();
            } else {
                GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 6, RecyclerView.HORIZONTAL, false);
                mRecyclerViewContact.setLayoutManager(layoutManager);
                mRecyclerViewContact.setAdapter(mCustomAdapter);
            }
        }
    };
    private View.OnClickListener mImgBtnAddClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mCustomDialogAdd = new CustomDialogAdd(MainActivity.this, mListenerDialogAdd);
            mCustomDialogAdd.show();
        }
    };
    private ListenerDialogAdd mListenerDialogAdd = new ListenerDialogAdd() {
        @Override
        public void isCheck(boolean isCheck) {
            getListContact();
            sortListContact(mListContact);
            initAdapter();
        }
    };
    private ListenerCustomAdapterClick mListenerCustomAdapterClick = new ListenerCustomAdapterClick() {
        @Override
        public void onClick(ContactInfoDTO contactInfoDTO) {
            mCustomDialogInsert = new CustomDialogInsert(MainActivity.this, mListenerDialogInsert);
            mCustomDialogInsert.show();
            mCustomDialogInsert.getViewCurrent(contactInfoDTO);
            mCustomDialogInsert.delete(contactInfoDTO);
            mCustomDialogInsert.insert(contactInfoDTO);

        }
    };
    private ListenerCustomAdapterLongClick mListenerCustomAdapterLongClick = new ListenerCustomAdapterLongClick() {
        @Override
        public void onClick(ContactInfoDTO contactInfoDTO) {
            mBtnDeleteMain.setVisibility(View.VISIBLE);
            mBtnCancelMain.setVisibility(View.VISIBLE);
//            initAdapter();
        }
    };
    private View.OnClickListener mBtnDeleteMainClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };
    private View.OnClickListener mBtnCancelMainClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mBtnCancelMain.setVisibility(View.GONE);
            mBtnDeleteMain.setVisibility(View.GONE);
            initAdapter();
        }
    };
    private ListenerDialogInsert mListenerDialogInsert = new ListenerDialogInsert() {
        @Override
        public void isCheck(boolean check) {
            getListContact();
            sortListContact(mListContact);
            initAdapter();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);

        initView();
        getListContact();
        sortListContact(mListContact);
        initAdapter();
        initAction();
    }

    private void sortListContact(List<ContactInfoDTO> listContact) {
        Collections.sort(listContact, new Comparator<ContactInfoDTO>() {
            @Override
            public int compare(ContactInfoDTO t1, ContactInfoDTO t2) {
                return Integer.valueOf(t2.getmID()).compareTo(Integer.valueOf(t1.getmID()));
            }
        });
    }


    private void getListContact() {
        ContactInfoDAO contactInfoDAO = new ContactInfoDAO(this);
        mListContact = contactInfoDAO.getListContact();
    }

    private void initAction() {
        mSwitchView.setOnCheckedChangeListener(mSwitchViewClick);
        mImgBtnAdd.setOnClickListener(mImgBtnAddClick);
        mBtnDeleteMain.setOnClickListener(mBtnDeleteMainClick);
        mBtnCancelMain.setOnClickListener(mBtnCancelMainClick);
    }


    private void initAdapter() {
        mCustomAdapter = new CustomAdapter(mListContact, mListenerCustomAdapterClick, mListenerCustomAdapterLongClick);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerViewContact.setLayoutManager(layoutManager);
        mRecyclerViewContact.setAdapter(mCustomAdapter);
    }

    private void initView() {
        mRecyclerViewContact = findViewById(R.id.rvContact);
        mSwitchView = findViewById(R.id.switchView);
        mImgBtnAdd = findViewById(R.id.imgBtnAdd);
        mBtnDeleteMain = findViewById(R.id.btnDeleteMain);
        mBtnCancelMain = findViewById(R.id.btnCancelMain);
    }
}
