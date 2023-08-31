package br.com.erudio.udemy;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private static final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public Greeting greeting(
            @RequestParam(value="name", defaultValue = "World") String name,
            @RequestParam(value="foo", defaultValue = "") String foo
    ) {
        if(foo.length() == 0) {
            return new Greeting(counter.incrementAndGet(), "bar");
        }
        return new Greeting(counter.incrementAndGet(), name);
    }
}
