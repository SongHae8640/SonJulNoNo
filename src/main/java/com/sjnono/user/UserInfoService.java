package com.sjnono.user;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserInfoService {


    private final UserInfoRepository userInfoRepository;

    public UserInfoService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public List<UserInfo> findAll(){
        List<UserInfo> userInfoList = userInfoRepository.findAll();

        return userInfoList;
    }

    public UserInfo save(UserInfo userInfo) {
        UserInfo newUserInfo = this.userInfoRepository.save(userInfo);
        return newUserInfo;


    }

    public UserInfo findById(Long id) {
        Optional<UserInfo> optionalUserInfo = this.userInfoRepository.findById(id);
        return optionalUserInfo.get();
    }
}
