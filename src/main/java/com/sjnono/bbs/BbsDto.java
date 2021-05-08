package com.sjnono.bbs;

import com.sjnono.user.UserInfo;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BbsDto {

    private Long id;
    private UserInfo userInfo;
    private String title;
    private String content;
    private long hits;
    private String link;

}
