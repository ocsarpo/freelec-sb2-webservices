package org.jojo.book.springboot.config.auth.dto;

import lombok.Getter;
import org.jojo.book.springboot.domain.user.User;

import java.io.Serializable;

// 인증된 사용자 정보
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
