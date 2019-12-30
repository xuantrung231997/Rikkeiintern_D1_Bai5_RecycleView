package gst.trainingcourse.rikkeiintern_d1_bai5_recycleview.DTO;

public class ContactInfoDTO {
    private String mName;
    private String mPhone;
    private int mID;

    public ContactInfoDTO() {

    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public ContactInfoDTO(String mName, String mPhone) {
        this.mName = mName;
        this.mPhone = mPhone;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }
}
