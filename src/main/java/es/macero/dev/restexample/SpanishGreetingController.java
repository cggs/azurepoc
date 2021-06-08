package es.macero.dev.restexample;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/spanish-greetings")
public class SpanishGreetingController {

    private List<SpanishGreeting> spanishGreetings;

    public SpanishGreetingController() {
        spanishGreetings = new ArrayList<>();
        spanishGreetings.add(new SpanishGreeting("Hola!"));
        spanishGreetings.add(new SpanishGreeting("Qué tal?!"));
        spanishGreetings.add(new SpanishGreeting("Buenas!"));
    }

    @GetMapping("/{id}")
    public SpanishGreeting getSpanishGreetingById(@PathVariable("id") final int id) {
        return spanishGreetings.get(id - 1); // list index starts with 0 but we prefer to start on 1
    }

    @GetMapping("/random")
    public SpanishGreeting getRandom() {
        return spanishGreetings.get(new Random().nextInt(spanishGreetings.size()));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createSpanishGreeting(@RequestBody SpanishGreeting spanishGreeting) {
        spanishGreetings.add(spanishGreeting);
    }

    @PostMapping("/token")
    @ResponseStatus(HttpStatus.OK)
    public PartnerToken CreateToken(@RequestBody PartnerToken ptoken) {
        String newtoken =ptoken.generateJWTToken(ptoken.getPartnerId());
        ptoken.setToken(newtoken);
        return ptoken;
    }

    @PostMapping("/token/validate")
    @ResponseStatus(HttpStatus.OK) 
    public String VerifyToken(@RequestBody PartnerToken ptoken) {
        String verifytoken =ptoken.verifyJWTToken(ptoken.getToken(),ptoken.getPartnerId());
        return verifytoken;
    }


}
