package gst.trainingcourse.rikkeiintern_d1_bai5_recycleview.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;

import gst.trainingcourse.rikkeiintern_d1_bai5_recycleview.DTO.ContactInfoDTO;
import gst.trainingcourse.rikkeiintern_d1_bai5_recycleview.database.CreateDatabase;
import gst.trainingcourse.rikkeiintern_d1_bai5_recycleview.dialog.CustomDialogAdd;

public class ContactInfoDAO {
    SQLiteDatabase database;

    public ContactInfoDAO(Context context) {
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }

    public boolean addContact(ContactInfoDTO contactInfoDTO) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_CONTACT_NAME, contactInfoDTO.getmName());
        contentValues.put(CreateDatabase.TB_CONTACT_PHONE, contactInfoDTO.getmPhone());

        long kiemtra = database.insert(CreateDatabase.TB_CONTACT, null, contentValues);
        if (kiemtra != 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<ContactInfoDTO> getListContact() {
        List<ContactInfoDTO> listContact = new ArrayList<>();
        String sql = " select * from " + CreateDatabase.TB_CONTACT;
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ContactInfoDTO contactInfoDTO = new ContactInfoDTO();
            int idContact = cursor.getInt(0);
            String nameContact = cursor.getString(1);
            String phoneContact = cursor.getString(2);
            contactInfoDTO.setmID(idContact);
            contactInfoDTO.setmName(nameContact);
            contactInfoDTO.setmPhone(phoneContact);
            listContact.add(contactInfoDTO);
            cursor.moveToNext();
        }
        return listContact;
    }

    public boolean delete(ContactInfoDTO contactInfoDTO) {
        int kiemtra = database.delete(CreateDatabase.TB_CONTACT, CreateDatabase.TB_CONTACT_ID + " = " + contactInfoDTO.getmID(), null);
        if (kiemtra != 0) return true;
        else return false;
    }

    public boolean insert(ContactInfoDTO contactInfoDTOOld, ContactInfoDTO contactInfoDTONew) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_CONTACT_NAME, contactInfoDTONew.getmName());
        contentValues.put(CreateDatabase.TB_CONTACT_PHONE, contactInfoDTONew.getmPhone());

        int kiemtra = database.update(CreateDatabase.TB_CONTACT, contentValues,
                CreateDatabase.TB_CONTACT_ID + " = " + contactInfoDTOOld.getmID(), null);
        if (kiemtra != 0) return true;
        else return false;
    }

}
