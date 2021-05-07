package com.sjnono.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.Objects;
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

    public UserInfo joinUser(UserInfo userInfo, Errors errors) {
        UserInfo existUserInfo = this.userInfoRepository.findByEmail(userInfo.getEmail());
        if (!Objects.isNull(existUserInfo)){
            errors.reject("Registered member");
            return userInfo;
        }

        return this.userInfoRepository.save(userInfo);

    }

    public UserInfo loginUser(UserInfo userInfo, Errors errors) {
        UserInfo existUserInfo = this.userInfoRepository.findByNameAndPassword(userInfo.getName(), userInfo.getPassword());
        if (Objects.isNull(existUserInfo)){
            errors.reject("Not Registered member");
            return userInfo;
        }

        return existUserInfo;

    }
}
