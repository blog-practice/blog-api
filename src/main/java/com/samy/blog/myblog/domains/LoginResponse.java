package com.samy.blog.myblog.domains;

import lombok.Data;

@Data
public class LoginResponse {
    protected String name;
    protected String token;
    protected String email;
    protected long id;

}
