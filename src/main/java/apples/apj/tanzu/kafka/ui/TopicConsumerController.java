package apples.apj.tanzu.kafka.ui;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
@Slf4j
public class TopicConsumerController {

    private static ArrayList<String> topicMessages = new ArrayList<String>();

    @GetMapping("/")
    public String indexPage (Model model){
        model.addAttribute("topicMessages", topicMessages);
        model.addAttribute("topicMessagesCount", topicMessages.size());

        return "home";
    }

    @KafkaListener(topics = "apples-topic")
    public void listen(String message) {
        log.info("Received Message: " + message);
        topicMessages.add(message);
    }
}
