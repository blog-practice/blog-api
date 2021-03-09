package com.samy.blog.myblog.services;

import com.samy.blog.myblog.domains.Blog;
import com.samy.blog.myblog.domains.Comment;
import com.samy.blog.myblog.domains.Tag;
import com.samy.blog.myblog.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BlogServices {

    @Autowired
    private BlogRepository blogRepository;

    public Blog createBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public Blog getBlogById(Long id) {
        return blogRepository.findById(id).get();
    }

    public Blog updateBlog(long id, Blog blog) throws Exception {
        Blog blog1 = getBlogById(id);
        if (blog1 == null) {
            throw new Exception("Blog not found!");
        }
        blog1.setTitle(blog.getTitle());
        blog1.setContent(blog.getContent());
        return blogRepository.save(blog1);
    }

    public Blog createComment(Long id, Comment comment) {
        Blog blog = getBlogById(id);
        blog.getComments().add(comment);
        return blogRepository.save(blog);
    }

    public Blog likeBlog(Long id) {
        Blog blog = getBlogById(id);
        int likes = blog.getLikes();
        likes++;
        blog.setLikes(likes);
        return blogRepository.save(blog);
    }

    public List<Comment> fetchAllComments(Long id) {
        Blog blog = getBlogById(id);
        return blog.getComments();
    }

    public List<Tag> fetchAllTags(long id) {
        Blog blog = getBlogById(id);
        return blog.getTags();
    }

    public Comment likeComment(long blogId, long commentId) {
        Blog blog = getBlogById(blogId);
        List<Comment> comments = blog.getComments();
        for (Comment comment : comments) {
            if (comment.getId() == commentId) {
                int likes = comment.getLikes();
                likes++;
                comment.setLikes(likes);
                blogRepository.save(blog);
                return comment;
            }
        }
        return null;
    }

    public List<Blog> getBlogsByTags(String tag) {
        String[] tags = tag.split(",");
        Set<String> tagSet = new HashSet<>();
        for(String tags1 : tags){
            tagSet.add(tags1);
        }
        List<Blog> blogList = getAllBlogs();
        List<Blog> blogList1 = new ArrayList<>();
        for (Blog blog : blogList) {
            // List<Tag> tags = fetchAllTags(blog.getId());
            for (Tag tag1 : blog.getTags()) {
                if(tagSet.contains(tag1.getName())){
                    blogList1.add(blog);
                    break;
                }
            }
        }
        return blogList1;

    }

    public List<Blog> getPopularBlogs() {
      List<Blog> blogs = blogRepository.findTop10ByOrderByLikesDescCommentsDesc();
      return blogs;
    }

    public List<Comment> getPopularComments(){
        List<Comment> comments = blogRepository.findTop10OrderByCommentsDesc();
        return comments;
    }


}
