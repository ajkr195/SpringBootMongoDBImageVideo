package com.springboot.mongodb.imagevideo.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.springboot.mongodb.imagevideo.model.Video;

@Service
public class VideoService {

	@Autowired
	private GridFsTemplate gridFsTemplate;

	@Autowired
	private GridFsOperations operations;

	public Video getVideo(String title) throws IllegalStateException, IOException {
		GridFSFile file = gridFsTemplate.findOne(new Query(Criteria.where("metadata.title").is(title)));
//		public Video getVideo(String id) throws IllegalStateException, IOException {
//			GridFSFile file = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
		Video video = new Video();
		video.setTitle(file.getMetadata().get("title").toString());
		video.setReleasedate(file.getMetadata().get("releasedate").toString());
		video.setDirector(file.getMetadata().get("director").toString());
		video.setProducer(file.getMetadata().get("producer").toString());
		video.setStream(operations.getResource(file).getInputStream());
		return video;
	}

	public String addVideo(String title,String releasedate,String producer,String director,MultipartFile file) throws IOException {
		DBObject metaData = new BasicDBObject();
		metaData.put("type", "video");
		metaData.put("title", title);
		metaData.put("releasedate", releasedate);
		metaData.put("producer", producer);
		metaData.put("director", director);
		
		ObjectId id = gridFsTemplate.store(file.getInputStream(), file.getName(), file.getContentType(), metaData);
		return id.toString();
	}

	public List<GridFSFile> getvideoList() {
		List<GridFSFile> fileList = new ArrayList<GridFSFile>();
//    	gridFsTemplate.find(new Query()).into(fileList);
//    	gridFsTemplate.find(new Query(Criteria.where("metadata.user").is("alex"))).into(fileList);
		return gridFsTemplate.find(new Query()).into(fileList);

	}

	public List<String> getVideoIdList() {
		List<GridFSFile> fileList = new ArrayList<GridFSFile>();
		List<String> idStrList = new ArrayList<String>();
   	 for (GridFSFile gfs : gridFsTemplate.find(new Query()).into(fileList)) {
//        	System.out.println(" ID FOR VIDEO IS :: " + gfs.getId());
        	idStrList.add((gfs.getId().toString()).replaceAll("BsonObjectId\\{value=", "").replaceAll("\\}", ""));
//        	.replaceAll("BsonObjectId{value=", "").replaceAll("}", "")
        	System.out.println(" METADATA FOR VIDEO IS :: " + gfs.getMetadata().toString());
//        	System.out.println(" TITLE FOR VIDEO IS :: " + gfs.getMetadata().getString("title"));
        }
   	 
   	ObjectId fileId = null; //ObjectId of a file uploaded to GridFS
   	GridFSBucket gridFSBucket = null;
   	gridFSBucket.rename(fileId, "mongodbTutorial");
	return idStrList;

	}
	
	public List<Document> getMetaData() {
		List<GridFSFile> fileList = new ArrayList<GridFSFile>();
		List<Document> idStrList = new ArrayList<Document>();
   	 for (GridFSFile gfs : gridFsTemplate.find(new Query()).into(fileList)) {
        	idStrList.add(gfs.getMetadata());
        }
   	 
//   	for (Document d: idStrList) {
//   		System.out.println(">>>>>>>>>>>>>>>                "+ d.getString("title"));
//   		System.out.println(">>>>>>>>>>>>>>>                "+ d.getString("releasedate"));
//   		System.out.println(">>>>>>>>>>>>>>>                "+ d.getString("producer"));
//   		System.out.println(">>>>>>>>>>>>>>>                "+ d.getString("director"));
//   	}
//   	BasicDBObject query = new BasicDBObject("metadata.target_field", "abcdefg");
	return idStrList;

	}

}
