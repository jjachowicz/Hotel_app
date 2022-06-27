package com.example.demo.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;


@Controller
public class HomeController {

    // http://localhost:8088
    @GetMapping
    @ResponseBody
    public ResponseEntity<String> index(
    ) {
        String url = "http://localhost:8089/api/users";
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> response
                = template.getForEntity(url, String.class);
        return response;

    }
}
