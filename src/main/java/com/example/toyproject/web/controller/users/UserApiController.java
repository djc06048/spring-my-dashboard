package com.example.toyproject.web.controller.users;

import com.example.toyproject.service.users.UserService;
import com.example.toyproject.web.dto.users.request.userJoinRequestDto;
import com.example.toyproject.web.dto.users.request.userLoginRequestDto;
import com.example.toyproject.web.dto.users.response.LoginResponseDto;
import com.example.toyproject.web.dto.users.response.UserResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@RestController
public class UserApiController {
    private final UserService userService;


    @PostMapping("/join")
    public LoginResponseDto join(@RequestBody userJoinRequestDto req){
      try{
          LoginResponseDto res=userService.join(req.getEmail(),req.getName(),req.getPassword());
          return res;
      }catch(Exception e){
          log.error(e.getMessage());
          return new LoginResponseDto(false,e.getMessage(),null);

        }

    }
    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody userLoginRequestDto req){
        LoginResponseDto res=userService.login(req.getEmail(),req.getPassword());
        return res;
    }
    @GetMapping("/{userId}")
    public UserResponseDto findUser(@PathVariable Long userId){
        try{
            UserResponseDto res=userService.findUser(userId);
            return res;
        }catch(Exception e){
            log.error(e.getMessage());
            return new UserResponseDto(false,e.getMessage(),null,null);
        }

    }
    @DeleteMapping("/{userId}")
    public LoginResponseDto DeleteUser(@PathVariable Long userId){
        LoginResponseDto res=userService.deleteUser(userId);
        return res;

    }



}
