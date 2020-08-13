package com.example.films.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Film implements Parcelable {

    @SerializedName("id")
    private Integer id;
    @SerializedName("localized_name")
    private String localizedName;
    @SerializedName("name")
    private String name;
    @SerializedName("year")
    private Integer year;
    @SerializedName("rating")
    private Double rating;
    @SerializedName("image_url")
    private String imageUrl;
    @SerializedName("description")
    private String description;
    @SerializedName("genres")
    private List<String> genres = null;

    protected Film(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        localizedName = in.readString();
        name = in.readString();
        if (in.readByte() == 0) {
            year = null;
        } else {
            year = in.readInt();
        }
        if (in.readByte() == 0) {
            rating = null;
        } else {
            rating = in.readDouble();
        }
        imageUrl = in.readString();
        description = in.readString();
        genres = in.createStringArrayList();
    }

    public static final Creator<Film> CREATOR = new Creator<Film>() {
        @Override
        public Film createFromParcel(Parcel in) {
            return new Film(in);
        }

        @Override
        public Film[] newArray(int size) {
            return new Film[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        parcel.writeString(localizedName);
        parcel.writeString(name);
        if (year == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(year);
        }
        if (rating == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(rating);
        }
        parcel.writeString(imageUrl);
        parcel.writeString(description);
        parcel.writeStringList(genres);
    }

    public Integer getId() {
        return id;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public String getName() {
        return name;
    }

    public Integer getYear() {
        return year;
    }

    public Double getRating() {
        return rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getGenres() {
        return genres;
    }

}
