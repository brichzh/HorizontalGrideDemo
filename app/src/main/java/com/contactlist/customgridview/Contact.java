package com.contactlist.customgridview;

import android.graphics.Bitmap;

/**
 * 
 * @author manish.s
 *
 */

public class Contact {
    String username;
	String imageUrl;
	String nickname;

	
	public Contact(String username, String imageUrl, String nickname) {
        this.username = username;
		this.imageUrl = imageUrl;
		this.nickname = nickname;
	}
    public String getUsername() {
        return username;
    }
	public String getImage() {
		return imageUrl;
	}
	public void setImage(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String title) {
		this.nickname = title;
	}
}
