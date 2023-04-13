package com.bank.bank.Controller;

import java.util.ArrayList;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.bank.Models.Post;
import com.bank.bank.Services.LoggingService;

@RestController
@RequestMapping("/posts")
public class PostController {
    ArrayList<Post> posts = new ArrayList<>();
    
    private LoggingService loggingService;
    @Autowired
    public PostController(LoggingService loggingService){
        this.loggingService = loggingService;
        this.loggingService.log("Posts have been fetched");
    }

    @GetMapping("")
    public ResponseEntity GetPost(){
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("{postId}")
    public ResponseEntity GetPost(@PathVariable("postId") Long postId){
        for(Post post:posts){
            if(post.getId().equals(postId)){
                return new ResponseEntity<>(post, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);

    }

    @PostMapping("")
    public ResponseEntity createPost(@RequestBody Map<String,String> body){
        Long id = (long) (posts.size());
        Post res = new Post(id, body.get("caption"));
        posts.add(res);
        return new ResponseEntity(res, HttpStatus.CREATED);
    }

    @PutMapping("{postId}")
    public ResponseEntity updatePost(@PathVariable("postId") Long postId, @RequestBody Map<String,String> body){
        for(int i = 0; i< posts.size(); i++){
            if(posts.get(i).getId().equals(postId)){
                Post res = new Post(postId, body.get("caption"));
                posts.set(i, res);
                return new ResponseEntity(res, HttpStatus.OK);
            }
        }
        return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{postId}")
    public ResponseEntity deletePost(@PathVariable("postId") Long postId){
        for(int i = 0; i< posts.size(); i++){
            if(posts.get(i).getId().equals(postId)){
                posts.remove(i);
                return new ResponseEntity("Deleted", HttpStatus.OK);
            }
        }
        return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);
    }
}
