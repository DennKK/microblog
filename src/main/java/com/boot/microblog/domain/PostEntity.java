package com.boot.microblog.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String text;
    private String tag;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private UserEntity author;

    public PostEntity(String text, String tag, UserEntity author) {
        this.text = text;
        this.tag = tag;
        this.author = author;
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }
}