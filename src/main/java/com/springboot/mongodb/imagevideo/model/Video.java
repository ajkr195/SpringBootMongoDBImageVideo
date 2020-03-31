package com.springboot.mongodb.imagevideo.model;

import java.io.InputStream;
import java.util.Date;

public class Video {
    private String title;
    private String releasedate;
    private String producer;
    private String director;
    private InputStream stream;
    
    public Video() {
        super();
    }

    public Video(String title) {
        super();
        this.title = title;
    }
    
    public Video(String title, String releasedate, String producer, String director, InputStream stream) {
		super();
		this.title = title;
		this.releasedate = releasedate;
		this.producer = producer;
		this.director = director;
		this.stream = stream;
	}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    
	public String getReleasedate() {
		return releasedate;
	}

	public void setReleasedate(String releasedate) {
		this.releasedate = releasedate;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public InputStream getStream() {
        return stream;
    }

    public void setStream(InputStream stream) {
        this.stream = stream;
    }

    @Override
	public String toString() {
		return "Video [title=" + title + ", releasedate=" + releasedate + ", producer=" + producer + ", director="
				+ director + "]";
	}
    
    

}
