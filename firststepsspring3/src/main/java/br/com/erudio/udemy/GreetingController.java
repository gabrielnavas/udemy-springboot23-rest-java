package br.com.erudio.udemy;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private static final AtomicLong counter = new AtomicLong();

    private List<Greeting> greetings = new ArrayList<>();


    @GetMapping("/greeting/{id}")
    public ResponseEntity<Object> getById(
            @PathVariable("id") Long id
    ) {
        Greeting greeting = null;
        for (Greeting g : greetings) {
            if (g.getId() == id) {
                greeting = g;
                break;
            }
        }

        if (greeting == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<String, String>() {{
                put("details", "greating not found");
            }});
        }
        return ResponseEntity.status(HttpStatus.OK).body(greeting);
    }

    @GetMapping("/greeting")
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(greetings);
    }

    @PostMapping("/greeting")
    public ResponseEntity<Object> create(@RequestBody Greeting body) {
        Greeting greeting = new Greeting(counter.incrementAndGet(), body.getContent());
        greetings.add(greeting);
        return ResponseEntity.status(HttpStatus.OK).body(greeting);
    }

    @PutMapping("/greeting/{id}")
    public ResponseEntity<Object> putContent(
            @RequestBody Greeting body,
            @PathVariable("id") Long id
    ) {
        int greetingIndex = -1;
        Greeting greeting = null;
        for (int i = 0; i < greetings.size(); i++) {
            System.out.println(greetings.get(i).getContent());
            if (greetings.get(i).getId() == id) {
                greeting = greetings.get(i);
                greetingIndex = i;
                break;
            }
        }

        if (greeting == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<String, String>() {{
                put("details", "greating not found");
            }});
        }

        Greeting newGreeting = new Greeting(greeting.getId(), body.getContent());
        greetings.set(greetingIndex, newGreeting);

        return ResponseEntity.status(HttpStatus.OK).body(newGreeting);
    }

    @DeleteMapping("/greeting/{id}")
    public ResponseEntity<Object> delete(
            @PathVariable("id") Long id
    ) {
        Greeting greeting = null;
        for (int i = 0; i < greetings.size(); i++) {
            System.out.println(greetings.get(i).getContent());
            if (greetings.get(i).getId() == id) {
                greeting = greetings.get(i);
                break;
            }
        }

        if (greeting == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<String, String>() {{
                put("details", "greating not found");
            }});
        }

        greetings.remove(greeting);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
