package com.sampunity.launcher.model;

import a.qk1;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class LinksModel implements Parcelable {
    public static final Parcelable.Creator<LinksModel> CREATOR = new Parcelable.Creator<LinksModel>() { // from class: com.sampunity.launcher.model.LinksModel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LinksModel createFromParcel(Parcel parcel) {
            return new LinksModel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LinksModel[] newArray(int i) {
            return new LinksModel[i];
        }
    };

    @qk1("banner")
    private String banner;

    @qk1("discord")
    private String discord;

    @qk1("instagram")
    private String instagram;

    @qk1("isbanner")
    private boolean isbanner;

    @qk1("loadscreen")
    private String loadscreen;

    @qk1("shop")
    private String shop;

    @qk1("tiktok")
    private String tiktok;

    @qk1("url_banner")
    private String url_banner;

    @qk1("youtube")
    private String youtube;

    public LinksModel() {
    }

    public LinksModel(Parcel parcel) {
        boolean readBoolean;
        this.discord = parcel.readString();
        this.instagram = parcel.readString();
        this.youtube = parcel.readString();
        this.tiktok = parcel.readString();
        this.shop = parcel.readString();
        if (Build.VERSION.SDK_INT >= 29) {
            readBoolean = parcel.readBoolean();
            this.isbanner = readBoolean;
        } else {
            this.isbanner = parcel.readInt() == 1;
        }
        this.banner = parcel.readString();
        this.url_banner = parcel.readString();
        this.loadscreen = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getBanner() {
        return this.banner;
    }

    public String getBannerUrl() {
        return this.url_banner;
    }

    public String getDiscord() {
        return this.discord;
    }

    public String getInstagram() {
        return this.instagram;
    }

    public boolean getIsBanner() {
        return this.isbanner;
    }

    public String getLoadScreen() {
        return this.loadscreen;
    }

    public String getShop() {
        return this.shop;
    }

    public String getTiktok() {
        return this.tiktok;
    }

    public String getYoutube() {
        return this.youtube;
    }

    public void setBanner(String str) {
        this.banner = str;
    }

    public void setBannerRedirect(String str) {
        this.url_banner = str;
    }

    public void setDiscord(String str) {
        this.discord = str;
    }

    public void setInstagram(String str) {
        this.instagram = str;
    }

    public void setIsBanner(boolean z) {
        this.isbanner = z;
    }

    public void setLoadScreen(String str) {
        this.loadscreen = str;
    }

    public void setShop(String str) {
        this.shop = str;
    }

    public void setTiktok(String str) {
        this.tiktok = str;
    }

    public void setYoutube(String str) {
        this.youtube = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.discord);
        parcel.writeString(this.instagram);
        parcel.writeString(this.youtube);
        parcel.writeString(this.tiktok);
        parcel.writeString(this.shop);
        if (Build.VERSION.SDK_INT >= 29) {
            parcel.writeBoolean(this.isbanner);
        } else {
            parcel.writeInt(this.isbanner ? 1 : 0);
        }
        parcel.writeString(this.banner);
        parcel.writeString(this.url_banner);
        parcel.writeString(this.loadscreen);
    }
}
