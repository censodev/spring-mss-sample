package io.github.censodev.serviceorder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/order")
public class OrderController {
    @GetMapping("")
    public String welcome() {
        return "Welcome to Order Service";
    }
}
