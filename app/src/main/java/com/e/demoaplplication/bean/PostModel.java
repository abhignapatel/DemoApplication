package com.e.demoaplplication.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class PostModel implements Serializable {

    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;
    @SerializedName("name")
    @Expose
    private String name;
    private  String email;
    private String company;
    @SerializedName("follower_Url")
    @Expose
    private String followerUrl;
    private boolean isFavorite;


    public PostModel(String login,String avatarUrl,String name, boolean isFavorite,String followerUrl, String company,String email) {
        this.avatarUrl=avatarUrl;
        this.name=name;
        this.login=login;
        this.isFavorite=isFavorite;
        this.email=email;
        this.company=company;
        this.followerUrl=followerUrl;
    }

    public PostModel() {

    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFavorite() { return isFavorite; }

    public void setFavorite(boolean favorite) { isFavorite = favorite; }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setFollowerUrl(String followerUrl) {
        this.followerUrl = followerUrl;
    }

    public String getEmail() {
        return email;
    }

    public String getFollowerUrl() {
        return followerUrl;
    }

    public String getCompany() {
        return company;
    }
}
