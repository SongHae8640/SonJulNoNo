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
@SequenceGenerator(name = "BBS_SEQ_GEN", sequenceName = "BBS_SEQ_GEN", initialValue = 3)
public class Bbs {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "BBS_SEQ_GEN")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_INFO_ID")
    private UserInfo userInfo;
    private String title;
    private String content;
    private long hits;

}
