package com.sjnono.bbs;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data @AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "board")
public class Bbs {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long articleId;
    private String userId;
    private String articleTitle;
    private String articleDetail;
    private String articleHits;
    private String articleRecommend;

    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
