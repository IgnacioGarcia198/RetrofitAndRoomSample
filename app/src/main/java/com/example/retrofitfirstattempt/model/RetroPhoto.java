package com.example.retrofitfirstattempt.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

@Entity(tableName = "photoTable")
public class RetroPhoto implements Parcelable {

    @SerializedName("albumId")
    @Expose
    private int albumId;

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("thumbnailUrl")
    @Expose
    private String thumbnailUrl;

    private Date lastRefresh;


    /**
     *
     * @param id
     * @param title
     * @param albumId
     * @param thumbnailUrl
     * @param url
     */
    public RetroPhoto(int albumId, int id, String title, String url, String thumbnailUrl, Date lastRefresh) {
        this.albumId = albumId;
        this.id = id;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
        this.lastRefresh = lastRefresh;
    }

    //==============================================
    //getters

    public int getAlbumId() {
        return albumId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public Date getLastRefresh() {
        return lastRefresh;
    }

    //=================================================
    //setters

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public void setLastRefresh(Date lastRefresh) {
        this.lastRefresh = lastRefresh;
    }

    //===============================================
    //parcelable

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.url);
        dest.writeString(this.thumbnailUrl);
        dest.writeInt(this.albumId);
    }

    protected RetroPhoto(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.url = in.readString();
        this.thumbnailUrl = in.readString();
        this.albumId = in.readInt();
    }

    public static final Parcelable.Creator<RetroPhoto> CREATOR = new Parcelable.Creator<RetroPhoto>() {
        @Override
        public RetroPhoto createFromParcel(Parcel source) {
            return new RetroPhoto(source);
        }

        @Override
        public RetroPhoto[] newArray(int size) {
            return new RetroPhoto[size];
        }
    };
}
