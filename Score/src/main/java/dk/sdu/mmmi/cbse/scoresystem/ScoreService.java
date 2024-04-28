package dk.sdu.mmmi.cbse.scoresystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RequestMapping("/score")
@RestController
public class ScoreService {
    private Long currentScore = 0L;

    public static void main(String[] args) {
        SpringApplication.run(ScoreService.class, args);
    }

    @GetMapping
    public Long getScore() {
        return currentScore;
    }

    @PutMapping("/update/{score}")
    public Long updateScore(@PathVariable(value = "score") Long score) {
        currentScore += score;
        return currentScore;
    }
}
