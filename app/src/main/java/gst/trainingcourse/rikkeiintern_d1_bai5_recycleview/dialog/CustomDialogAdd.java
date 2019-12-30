package gst.trainingcourse.rikkeiintern_d1_bai5_recycleview.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import gst.trainingcourse.rikkeiintern_d1_bai5_recycleview.DAO.ContactInfoDAO;
import gst.trainingcourse.rikkeiintern_d1_bai5_recycleview.DTO.ContactInfoDTO;
import gst.trainingcourse.rikkeiintern_d1_bai5_recycleview.ListenerDialogAdd;
import gst.trainingcourse.rikkeiintern_d1_bai5_recycleview.MainActivity;
import gst.trainingcourse.rikkeiintern_d1_bai5_recycleview.R;

public class CustomDialogAdd extends Dialog {
    private EditText mEtName;
    private EditText mEtPhone;
    private Button mBtnSaveAdd;
    private Button mBtnCancer;
    private ListenerDialogAdd mListenerDialogAdd;

    public CustomDialogAdd(@NonNull Context context,ListenerDialogAdd mListenerDialogAdd) {
        super(context);
        this.mListenerDialogAdd=mListenerDialogAdd;
    }

    private View.OnClickListener mBtnCancerClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            CustomDialogAdd.super.onBackPressed();
        }
    };
    private View.OnClickListener mBtnSaveAddClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            addContact();
            mListenerDialogAdd.isCheck(true);
            dismiss();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog_add);

        initView();
        initAction();
    }
    private void addContact(){
        ContactInfoDAO contactInfoDAO = new ContactInfoDAO(getContext());
        String sName=mEtName.getText().toString();
        String sPhone=mEtPhone.getText().toString();

        ContactInfoDTO contactInfoDTO=new ContactInfoDTO();
        contactInfoDTO.setmName(sName);
        contactInfoDTO.setmPhone(sPhone);

        boolean kiemtra = contactInfoDAO.addContact(contactInfoDTO);
        if(kiemtra){
            Log.d("testAdd", "done");
        }
        else Log.d("testAdd", "fail");
    }

    private void initAction() {
        mBtnCancer.setOnClickListener(mBtnCancerClick);
        mBtnSaveAdd.setOnClickListener(mBtnSaveAddClick);

    }

    private void initView() {
        mEtName = findViewById(R.id.etNameAdd);
        mEtPhone = findViewById(R.id.etPhoneAdd);
        mBtnSaveAdd = findViewById(R.id.btnSaveAdd);
        mBtnCancer = findViewById(R.id.btnCancer);
    }
}
