package com.rainyheaven.modooreport.api.presentation.common;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "안녕 (Hello)")
@RestController
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public ResponseEntity<String> hello() {
        return ResponseEntity.status(HttpStatus.OK.value()).body("hello");
    }

}
