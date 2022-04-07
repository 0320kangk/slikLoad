package com.example.demo.controller;

import com.example.demo.dto.MemberRequestDto;
import com.example.demo.dto.MemberResponseDto;
import com.example.demo.dto.UserDataResponseDto;
import com.example.demo.exception.CustomException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.model.MemberService;
import com.example.demo.model.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/test")
    public String test() {
        throw new CustomException(ErrorCode.POSTS_NOT_FOUND);
    }

    @GetMapping("/b")
    public List<MemberResponseDto> findAll() {
        return memberService.findAll();
    }


    @PostMapping("/save")
    public void save(@RequestBody final MemberRequestDto params) {
        memberService.save(params);
    }

    @PostMapping("/login")
    public MemberResponseDto login(@RequestBody final MemberRequestDto params) {
        MemberResponseDto entity = memberService.findBy(params);
        return entity;
    }

//    @PostMapping("/logincheck")
//    public MemberResponseDto logincheck(@RequestBody final MemberRequestDto params) {
//        MemberResponseDto entity = memberService.Logincheck(params);
//        return entity;
//    }

}
