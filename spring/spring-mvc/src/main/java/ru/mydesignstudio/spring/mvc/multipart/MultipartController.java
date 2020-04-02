package ru.mydesignstudio.spring.mvc.multipart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("")
public class MultipartController {
    @PostMapping
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            return "uploadFailure";
        }
        return "uploadSuccess";
    }
}
