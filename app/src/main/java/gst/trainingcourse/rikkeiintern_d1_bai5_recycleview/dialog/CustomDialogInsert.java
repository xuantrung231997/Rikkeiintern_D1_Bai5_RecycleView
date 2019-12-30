package gst.trainingcourse.rikkeiintern_d1_bai5_recycleview.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import gst.trainingcourse.rikkeiintern_d1_bai5_recycleview.DAO.ContactInfoDAO;
import gst.trainingcourse.rikkeiintern_d1_bai5_recycleview.DTO.ContactInfoDTO;
import gst.trainingcourse.rikkeiintern_d1_bai5_recycleview.ListenerDialogInsert;
import gst.trainingcourse.rikkeiintern_d1_bai5_recycleview.R;

public class CustomDialogInsert extends Dialog {
    private EditText mEtName;
    private EditText mEtPhone;
    private Button mBtnSaveInsert;
    private Button mBtnDelete;
    private ContactInfoDTO mContactInfoDTO;
    private ListenerDialogInsert mListenerDialogInsert;

    private View.OnClickListener mBtnSaveInsertClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ContactInfoDTO contactInfoDTO1New = new ContactInfoDTO();
            String sName = mEtName.getText().toString();
            String sPhone = mEtPhone.getText().toString();
            contactInfoDTO1New.setmName(sName);
            contactInfoDTO1New.setmPhone(sPhone);

            ContactInfoDAO contactInfoDAO = new ContactInfoDAO(getContext());
            boolean kiemtra = contactInfoDAO.insert(mContactInfoDTO, contactInfoDTO1New);
            Log.d("check", "onClick: " + kiemtra);
            mListenerDialogInsert.isCheck(true);
            dismiss();
        }
    };
    private View.OnClickListener mBtnDeleteClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ContactInfoDAO contactInfoDAO = new ContactInfoDAO(getContext());
            contactInfoDAO.delete(mContactInfoDTO);
            mListenerDialogInsert.isCheck(true);
            dismiss();
        }
    };


    public CustomDialogInsert(@NonNull Context context, ListenerDialogInsert mListenerDialogInsert) {
        super(context);
        this.mListenerDialogInsert = mListenerDialogInsert;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog_insert);

        initView();
    }

    public void getViewCurrent(ContactInfoDTO contactInfoDTO) {
        mEtName.setText(contactInfoDTO.getmName());
        mEtPhone.setText(contactInfoDTO.getmPhone());
    }

    public void insert(ContactInfoDTO contactInfoDTO) {
        mContactInfoDTO = contactInfoDTO;
        mBtnSaveInsert.setOnClickListener(mBtnSaveInsertClick);

    }

    public void delete(ContactInfoDTO contactInfoDTO) {
        mContactInfoDTO = contactInfoDTO;
        mBtnDelete.setOnClickListener(mBtnDeleteClick);
    }

    private void initView() {
        mEtName = findViewById(R.id.etNameInsert);
        mEtPhone = findViewById(R.id.etPhoneInsert);
        mBtnSaveInsert = findViewById(R.id.btnSaveInsert);
        mBtnDelete = findViewById(R.id.btnDelete);
    }
}
