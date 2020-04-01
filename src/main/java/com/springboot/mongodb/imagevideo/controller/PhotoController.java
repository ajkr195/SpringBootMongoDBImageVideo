package com.springboot.mongodb.imagevideo.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.mongodb.imagevideo.model.Photo;
import com.springboot.mongodb.imagevideo.service.PhotoService;

@Controller
public class PhotoController {

	@Autowired
	private PhotoService photoService;

	@ModelAttribute("metaTitle")
	public String globalTitle() {
		return "MongoDB";
	}

	@RequestMapping(value = "/photolist")
	public String indexphoto(Model model) {
		model.addAttribute("photos", photoService.listFilesByPage(0, 20));
		return "photolist";
	}

	@GetMapping("/photo/{id}")
	public String getPhoto(@PathVariable String id, Model model) {
		Photo photo = photoService.getPhoto(id);
		model.addAttribute("serverTime", new Date());
		model.addAttribute("photoid", photo.getId());
		model.addAttribute("title", photo.getTitle());
		model.addAttribute("photodate", photo.getPhotodate());
		model.addAttribute("photographer", photo.getPhotographer());
		model.addAttribute("photolocation", photo.getPhotolocation());
		model.addAttribute("image", Base64.getEncoder().encodeToString(photo.getImage().getData()));
		System.out.println("Retrieving image : " + photo.getId() + " : " + photo.getTitle());
		return "photo";
	}

	@GetMapping("/photo/upload")
	public String uploadPhoto(Model model) {
		model.addAttribute("message", "hello");
		return "photoupload";
	}

	@PostMapping("/photo/add")
	public String addPhoto(@RequestParam("title") String title, @RequestParam("photodate") String photodate,
			@RequestParam("photographer") String photographer, @RequestParam("photolocation") String photolocation,
			@RequestParam("image") MultipartFile image, Model model) throws IOException {
		String id = photoService.addPhoto(title,photodate,photographer,photolocation, image);
		return "redirect:/photo/" + id;
	}

}
