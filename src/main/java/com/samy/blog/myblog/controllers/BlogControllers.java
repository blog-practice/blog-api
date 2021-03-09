package com.samy.blog.myblog.controllers;

import com.samy.blog.myblog.domains.Blog;
import com.samy.blog.myblog.domains.Comment;
import com.samy.blog.myblog.domains.Tag;
import com.samy.blog.myblog.services.BlogServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/blogs")
public class BlogControllers {

    @Autowired
    private BlogServices blogServices;

    @PostMapping("")
    public Blog createBlog(@RequestBody() Blog blog) {
        return blogServices.createBlog(blog);
    }

    @PatchMapping("/{id}")
    public Blog updateBlog(@RequestBody Blog blog, @PathVariable("id") long id) throws Exception {
        return blogServices.updateBlog(id, blog);
    }

    @GetMapping("")
    public List<Blog> getAllBlogs() {
        return blogServices.getAllBlogs();
    }

    @GetMapping("/{id}")
    public Blog getBlogById(@PathVariable("id") Long id) {
        return blogServices.getBlogById(id);
    }

    @PostMapping("/{id}/comments")
    public Blog createComment(@RequestBody() Comment comments, @PathVariable("id") Long id) {
        return blogServices.createComment(id, comments);
    }

    @PatchMapping("/{id}/likes")
    public Blog likeBlog(@PathVariable("id") Long id) {
        return blogServices.likeBlog(id);
    }

    @GetMapping("/{id}/comments")
    public List<Comment> getAllComments(@PathVariable("id") Long id) {
        return blogServices.fetchAllComments(id);

    }

    @GetMapping("/{id}/tags")
    public List<Tag> getAllTags(@PathVariable("id") Long id) {
        return blogServices.fetchAllTags(id);
    }

    @PatchMapping("/{blogId}/{commentId}/likes")
    public Comment likeComment(@PathVariable("blogId") long blogId, @PathVariable("commentId") long commentId) {
        return blogServices.likeComment(blogId, commentId);
    }

    @GetMapping("/tags/{tag}")
    public List<Blog> getBlogsByTag(@PathVariable("tag") String tag) {
        return blogServices.getBlogsByTags(tag);
    }

    @GetMapping("/popular")
    public List<Blog> getPopularBlogs() {
        return blogServices.getPopularBlogs();
    }

    @GetMapping("/{blogId}/comments/popular")
    public List<Comment> getPopularComments(@PathVariable("blogId") long blogId) {
        return blogServices.getPopularComments(blogId);
    }

    @GetMapping("/search")
    public List<Blog> searchByTitle(@RequestParam("query") String query) {
        return blogServices.searchByTitle(query);

    }

}
