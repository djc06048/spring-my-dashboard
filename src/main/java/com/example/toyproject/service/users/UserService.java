package com.example.toyproject.service.users;

import com.example.toyproject.domain.users.Users;
import com.example.toyproject.domain.users.UsersRepository;
import com.example.toyproject.web.dto.users.response.LoginResponseDto;
import com.example.toyproject.web.dto.users.response.UserResponseDto;
import com.example.toyproject.web.utils.WrongUserExceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UsersRepository usersRepository;


    @Transactional
    public LoginResponseDto join(String email, String name, String password) {
        checkDupllicatedUser(email);
        Users newUser=new Users(email,name,password);
        usersRepository.save(newUser);
        return new LoginResponseDto(true,"가입성공",newUser.getUserId());
    }

    private void checkDupllicatedUser(String email) {
        if(usersRepository.findByEmail(email).isPresent()){
            throw new WrongUserExceptions("유효하지않은 이메일입니다.");
        }
    }

    @Transactional
    public LoginResponseDto login(String email, String password) {
        Users user=usersRepository.findByEmail(email).orElse(null);
        LoginResponseDto res;
        if(user==null)
            return new LoginResponseDto(false,"이메일이 존재하지 않음",null);
        if(user.getPassword().equals(password))
            return new LoginResponseDto(true,"로그인 성공",user.getUserId());
        else
            return new LoginResponseDto(false,"비밀번호가 올바르지 않음",null);
    }
    @Transactional(readOnly = true)
    public UserResponseDto findUser(Long userId) {
        Users user=usersRepository.findByUserId(userId).orElseThrow(()->new WrongUserExceptions("해당하는 유저가 존재하지 않습니다"));
        return new UserResponseDto(true,"유저를 가져오는데 성공했습니다",user.getUserId(),user.getEmail());
    }
    @Transactional
    public LoginResponseDto deleteUser(Long userId) {
        Users user=usersRepository.findByUserId(userId).orElse(null);
        if(user==null){
            return new LoginResponseDto(false,"해당하는 유저가 없습니다",null);
        }
        else {
            usersRepository.deleteById(userId);
            return new LoginResponseDto(true,"탈퇴 완료",userId);
        }
    }
}
