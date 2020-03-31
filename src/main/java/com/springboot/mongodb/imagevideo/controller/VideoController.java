package com.springboot.mongodb.imagevideo.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.mongodb.imagevideo.model.Video;
import com.springboot.mongodb.imagevideo.service.VideoService;

@Controller
public class VideoController {

    @Autowired
    private VideoService videoService;

    @ModelAttribute("metaTitle")
	public String globalTitle() {
		return "MongoDB";
	}
    
    @RequestMapping(value = "/videolist")
   	public String index(Model model) {
   		model.addAttribute("videos", videoService.getMetaData());
   		return "indexvideo";
   	}
    
    
    @GetMapping("/videos/{id}")
    public String getVideo(@PathVariable String id, Model model) throws IllegalStateException, IOException {
        Video video = videoService.getVideo(id);
        model.addAttribute("title", id);
        model.addAttribute("releasedate", video.getReleasedate());
        model.addAttribute("producer", video.getProducer());
        model.addAttribute("director", video.getDirector());
        model.addAttribute("url", "/videos/stream/" + id);
        
        System.out.println("+=+=+=+=+=    " + id);
        System.out.println("+=+=+=+=+=    " + video.getTitle());
        System.out.println("+=+=+=+=+=    " + video.getReleasedate());
        System.out.println("+=+=+=+=+=    " + video.getProducer());
        System.out.println("+=+=+=+=+=    " + video.getDirector());
        
        
        return "videos";
    }

    @GetMapping("/videos/stream/{id}")
    public void streamVideo(@PathVariable String id, HttpServletResponse response) throws IllegalStateException, IOException {
        Video video = videoService.getVideo(id);
        FileCopyUtils.copy(video.getStream(), response.getOutputStream());
    }

    @GetMapping("/videos/upload")
    public String uploadVideo(Model model) {
        model.addAttribute("message", "hello");
        return "uploadVideo";
    }

    @PostMapping("/videos/add")
    public String addVideo(@RequestParam("title") String title, @RequestParam("releasedate") String releasedate,@RequestParam("producer") String producer,@RequestParam("director") String director,@RequestParam("file") MultipartFile file, Model model) throws IOException {
        String id = videoService.addVideo(title, releasedate, producer, director, file);
        
        return "redirect:/videos/" + title;
//        return "redirect:/videos/" + id;
    }
    
}
