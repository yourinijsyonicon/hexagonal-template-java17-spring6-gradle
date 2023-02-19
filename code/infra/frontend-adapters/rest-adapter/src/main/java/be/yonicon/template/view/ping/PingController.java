package be.yonicon.template.view.ping;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "PingRestApi", description = "Ping API Operations")
public class PingController {
    @GetMapping(value = "/ping", produces = "text/plain")
    public String ping() {
        return "i am alive";
    }
}
