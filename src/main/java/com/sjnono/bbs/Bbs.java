package com.sjnono.bbs;

import com.sjnono.user.UserInfo;
import lombok.*;

import javax.persistence.*;

@Entity(name = "BOARD")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Bbs {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_INFO_ID")
    private UserInfo userInfo;

    private String title;
    private String content;
    private long hits;

}
