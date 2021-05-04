package com.sjnono.user;

import lombok.*;

import javax.persistence.*;

@Entity(name = "USER_INFO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@SequenceGenerator(name = "USER_SEQ_GEN", sequenceName = "USER_SEQ_GEN", initialValue = 3)
public class UserInfo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "USER_SEQ_GEN")
    private Long id;
    private String name;
    private String password;
    private String email;
}
