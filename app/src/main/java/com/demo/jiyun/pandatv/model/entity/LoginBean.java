package com.demo.jiyun.pandatv.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by dell on 2017/7/20.
 */

public class LoginBean implements Parcelable {


    /**
     * timestamp : 2017-07-20 16:58:28
     * ticket : 1f58cbf3-8281-420d-a009-974cf7dfa651
     * errType : 0
     * errMsg : 成功
     * sso : []
     * user_seq_id : 55065932
     * usrid : 550659321500539373563
     */

    private String timestamp;
    private String ticket;
    private String errType;
    private String errMsg;
    private String user_seq_id;
    private String usrid;
    private List<?> sso;

    protected LoginBean(Parcel in) {
        timestamp = in.readString();
        ticket = in.readString();
        errType = in.readString();
        errMsg = in.readString();
        user_seq_id = in.readString();
        usrid = in.readString();
    }

    public static final Creator<LoginBean> CREATOR = new Creator<LoginBean>() {
        @Override
        public LoginBean createFromParcel(Parcel in) {
            return new LoginBean(in);
        }

        @Override
        public LoginBean[] newArray(int size) {
            return new LoginBean[size];
        }
    };

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getErrType() {
        return errType;
    }

    public void setErrType(String errType) {
        this.errType = errType;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getUser_seq_id() {
        return user_seq_id;
    }

    public void setUser_seq_id(String user_seq_id) {
        this.user_seq_id = user_seq_id;
    }

    public String getUsrid() {
        return usrid;
    }

    public void setUsrid(String usrid) {
        this.usrid = usrid;
    }

    public List<?> getSso() {
        return sso;
    }

    public void setSso(List<?> sso) {
        this.sso = sso;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(timestamp);
        dest.writeString(ticket);
        dest.writeString(errType);
        dest.writeString(errMsg);
        dest.writeString(user_seq_id);
        dest.writeString(usrid);
    }
}
