package com.kjone.useroauth.domain.oauth.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kjone.useroauth.domain.board.entity.Board;
import com.kjone.useroauth.domain.friend.entity.Friend;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Setter
@Getter
@Table(name = "user_entity")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // key 아이디 & 식별번호
    private String email; // 이메일
    private String password; // 비번
    private String username; // 이름
    private String phone; // 전화번호
    private int age; // 나이
    private String sex; //성별
    private String image;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Friend> friends = new HashSet<>();

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<Board> boards = new ArrayList<>();

    // 권한이라는 것에 외래키를 추가 함으로써
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "authority", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<Authority> roles = new HashSet<>();

    public UserEntity(String email, String password, String username, String phone, int age, String sex, String image, Set<Authority> roles) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.phone = phone;
        this.age = age;
        this.sex = sex;
        this.image = image;
        this.roles = roles;
    }

}
