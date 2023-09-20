package com.bootcamp.queuemanager.controller;

import com.bootcamp.queuemanager.model.CustomerFeedback;
import com.bootcamp.queuemanager.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    /** Enviar Feedback **/
    @PostMapping("/envio")
    public ResponseEntity<String> enviarFeedback(@RequestBody CustomerFeedback feedback) {
        feedbackService.enviaFeedback(feedback);
        return ResponseEntity.ok("Feedback enviado!");
    }

    /** Obter tamanho atual da fila de feedbacks para cada tipo **/
    @GetMapping("/tamanho")
    public ResponseEntity<String> obterTamanhoDaFila(@RequestParam String tipo) {
        int tamanho = 0;
        //TODO chamada da service
        return ResponseEntity.ok("A fila do tipo " + tipo + " possui tamanho " + tamanho);
    }

    /** Obter informações sobre todos os feedbacks na fila de cada tipo **/
    @GetMapping("/info")
    public ResponseEntity<List<CustomerFeedback>> obterInformacoesFeedbacks (@RequestParam String tipo) {
        List<CustomerFeedback> feedbacks = new ArrayList<>();
        //TODO chamada da service, definir estrutura de dados
        return ResponseEntity.ok(feedbacks);
    }

}
