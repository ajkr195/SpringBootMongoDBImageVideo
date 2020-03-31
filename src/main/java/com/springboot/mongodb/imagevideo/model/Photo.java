package com.springboot.mongodb.imagevideo.model;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "photos")
public class Photo {
    
    @Id
    private String id;
    
    private String title;
    
    private String photodate;
    
    private String photographer;
    
    private String photolocation;
        
    private Binary image;

    public Photo(String title) {
        super();
        this.title = title;
    }

    public Photo() {
		super();
	}

	public Photo(String id, String title, String photodate, String photographer, String photolocation, Binary image) {
		super();
		this.id = id;
		this.title = title;
		this.photodate = photodate;
		this.photographer = photographer;
		this.photolocation = photolocation;
		this.image = image;
	}
	
	public Photo(String title, String photodate, String photographer, String photolocation) {
		super();
		this.title = title;
		this.photodate = photodate;
		this.photographer = photographer;
		this.photolocation = photolocation;
	}
	
	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

	public void setTitle(String title) {
        this.title = title;
    }

	
    public String getPhotodate() {
		return photodate;
	}

	public void setPhotodate(String photodate) {
		this.photodate = photodate;
	}

	public String getPhotographer() {
		return photographer;
	}

	public void setPhotographer(String photographer) {
		this.photographer = photographer;
	}

	public String getPhotolocation() {
		return photolocation;
	}

	public void setPhotolocation(String photolocation) {
		this.photolocation = photolocation;
	}

	public Binary getImage() {
        return image;
    }

    public void setImage(Binary image) {
        this.image = image;
    }

    
    
    @Override
	public String toString() {
		return "Photo [id=" + id + ", title=" + title + ", photodate=" + photodate + ", photographer=" + photographer
				+ ", photolocation=" + photolocation + ", image=" + image + "]";
	}
    
}
