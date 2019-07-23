package ru.mydesignstudio.spring.mvc.cache.control;

import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class CacheControlController {
    @GetMapping("")
    public ResponseEntity<String> getMessage() {
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.DAYS))
                .body("Done");
    }
}
