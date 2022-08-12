package com.be1.plant4you.auth.controller;

import com.be1.plant4you.auth.dto.response.UserNameResponse;
import com.be1.plant4you.auth.service.UserService;
import com.be1.plant4you.common.auth.CurrentUser;
import com.be1.plant4you.common.auth.UserPrincipal;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "유저 API")
@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserController {

    private final UserService userService;

    @Operation(summary = "현재 로그인한 유저 이름 조회", description = "로그인 후 메인화면에 '~님 안녕하세요'에 사용")
    @GetMapping("/name")
    public UserNameResponse getLoggedInUserName(@CurrentUser UserPrincipal userPrincipal) {
        return userService.getUserName(userPrincipal.getUserId());
    }

    @Operation(summary = "유저 탈퇴", description = "일단 유저 관련 정보 모두 삭제하도록 구현")
    @DeleteMapping
    public String withdraw(@CurrentUser UserPrincipal userPrincipal) {
        userService.withdraw(userPrincipal.getUserId());
        return "탈퇴가 완료되었습니다.";
    }
}