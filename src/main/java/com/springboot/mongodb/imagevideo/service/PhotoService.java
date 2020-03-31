package com.springboot.mongodb.imagevideo.service;

import java.io.IOException;
import java.util.List;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.mongodb.imagevideo.dao.PhotoRepository;
import com.springboot.mongodb.imagevideo.model.Photo;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepo;

    public Photo getPhoto(String id) {
        return photoRepo.findById(id).get();
    }

    public String addPhoto(String title, String photodate,String photographer,String photolocation,MultipartFile file) throws IOException {
        Photo photo = new Photo(title,photodate,photographer,photolocation);
        photo.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        photo = photoRepo.insert(photo);
        return photo.getId();
    }
    
    public List<Photo> listFilesByPage(int pageIndex, int pageSize) {
		Page<Photo> page = null;
		List<Photo> list = null;
		
		Sort sort = Sort.by(Direction.DESC,"uploadDate"); 
		Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);
		
		page = photoRepo.findAll(pageable);
		list = page.getContent();
		return list;
	}
}
