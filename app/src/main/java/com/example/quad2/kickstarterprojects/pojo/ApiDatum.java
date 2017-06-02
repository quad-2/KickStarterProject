
package com.example.quad2.kickstarterprojects.pojo;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiDatum implements Parcelable {

    @SerializedName("s.no")
    @Expose
    private long sNo;
    @SerializedName("amt.pledged")
    @Expose
    private long amtPledged;
    @SerializedName("blurb")
    @Expose
    private String blurb;
    @SerializedName("by")
    @Expose
    private String by;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("end.time")
    @Expose
    private String endTime;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("percentage.funded")
    @Expose
    private long percentageFunded;
    @SerializedName("num.backers")
    @Expose
    private String numBackers;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("url")
    @Expose
    private String url;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ApiDatum() {
    }

    /**
     * 
     * @param sNo
     * @param location
     * @param numBackers
     * @param state
     * @param by
     * @param blurb
     * @param type
     * @param endTime
     * @param url
     * @param country
     * @param currency
     * @param title
     * @param amtPledged
     * @param percentageFunded
     */
    public ApiDatum(long sNo, long amtPledged, String blurb, String by, String country, String currency, String endTime, String location, long percentageFunded, String numBackers, String state, String title, String type, String url) {
        super();
        this.sNo = sNo;
        this.amtPledged = amtPledged;
        this.blurb = blurb;
        this.by = by;
        this.country = country;
        this.currency = currency;
        this.endTime = endTime;
        this.location = location;
        this.percentageFunded = percentageFunded;
        this.numBackers = numBackers;
        this.state = state;
        this.title = title;
        this.type = type;
        this.url = url;
    }

    public long getSNo() {
        return sNo;
    }

    public void setSNo(long sNo) {
        this.sNo = sNo;
    }

    public long getAmtPledged() {
        return amtPledged;
    }

    public void setAmtPledged(long amtPledged) {
        this.amtPledged = amtPledged;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getPercentageFunded() {
        return percentageFunded;
    }

    public void setPercentageFunded(long percentageFunded) {
        this.percentageFunded = percentageFunded;
    }

    public String getNumBackers() {
        return numBackers;
    }

    public void setNumBackers(String numBackers) {
        this.numBackers = numBackers;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ApiDatum{" +
                "sNo=" + sNo +
                ", amtPledged=" + amtPledged +
                ", blurb='" + blurb + '\'' +
                ", by='" + by + '\'' +
                ", country='" + country + '\'' +
                ", currency='" + currency + '\'' +
                ", endTime='" + endTime + '\'' +
                ", location='" + location + '\'' +
                ", percentageFunded=" + percentageFunded +
                ", numBackers='" + numBackers + '\'' +
                ", state='" + state + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.sNo);
        dest.writeLong(this.amtPledged);
        dest.writeString(this.blurb);
        dest.writeString(this.by);
        dest.writeString(this.country);
        dest.writeString(this.currency);
        dest.writeString(this.endTime);
        dest.writeString(this.location);
        dest.writeLong(this.percentageFunded);
        dest.writeString(this.numBackers);
        dest.writeString(this.state);
        dest.writeString(this.title);
        dest.writeString(this.type);
        dest.writeString(this.url);
    }

    protected ApiDatum(Parcel in) {
        this.sNo = in.readLong();
        this.amtPledged = in.readLong();
        this.blurb = in.readString();
        this.by = in.readString();
        this.country = in.readString();
        this.currency = in.readString();
        this.endTime = in.readString();
        this.location = in.readString();
        this.percentageFunded = in.readLong();
        this.numBackers = in.readString();
        this.state = in.readString();
        this.title = in.readString();
        this.type = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<ApiDatum> CREATOR = new Parcelable.Creator<ApiDatum>() {
        @Override
        public ApiDatum createFromParcel(Parcel source) {
            return new ApiDatum(source);
        }

        @Override
        public ApiDatum[] newArray(int size) {
            return new ApiDatum[size];
        }
    };
}
