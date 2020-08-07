package com.example.icloud_work.Controller;


import com.example.icloud_work.VO.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @GetMapping("/msg")
    public ResponseVO getMsg(){
        return ResponseVO.buildSuccess("hello");
    }
}
