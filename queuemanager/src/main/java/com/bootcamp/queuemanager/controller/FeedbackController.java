package com.bootcamp.queuemanager.controller;

import com.bootcamp.queuemanager.dto.CustomerFeedbackDTO;
import com.bootcamp.queuemanager.dto.CustomerFeedbackRequestDTO;
import com.bootcamp.queuemanager.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    /** Enviar Feedback **/
    @PostMapping("/envio")
    public ResponseEntity<String> enviarFeedback(@RequestBody CustomerFeedbackRequestDTO feedback) {
        feedbackService.sendFeedback(feedback);
        return ResponseEntity.ok("Feedback enviado!");
    }

    /** Obter tamanho atual da fila de feedbacks para cada tipo **/
    @GetMapping("/tamanho")
    public ResponseEntity<String> obterTamanhoDaFila(@RequestParam String type) {
        int size = feedbackService.getQueueSize(type);
        return ResponseEntity.ok("A fila do tipo " + type + " possui tamanho " + size);
    }

    /** Obter informações sobre todos os feedbacks de uma fila SQS **/
    @GetMapping("/info")
    public ResponseEntity<LinkedList<CustomerFeedbackDTO>> obterInformacoesFila (@RequestParam String type) {
        LinkedList<CustomerFeedbackDTO> feedbacks = feedbackService.getQueueInformation(type);
        return ResponseEntity.ok(feedbacks);
    }

    /** Obter informações sobre todos os feedbacks na fila de cada tipo **/
    @GetMapping("/info/all")
    public ResponseEntity<LinkedList<CustomerFeedbackDTO>> obterInformacoesFeedbacks (@RequestParam String type) {
        LinkedList<CustomerFeedbackDTO> feedbacks = feedbackService.getQueueInformation(type);
        return ResponseEntity.ok(feedbacks);
    }

    /** Obter informações sobre todos os feedbacks na fila de cada tipo **/
    @GetMapping("/job")
    public ResponseEntity<String> dispararArmazenamento (@RequestParam String type) {
        feedbackService.armazenar(type);
        return ResponseEntity.ok("Armazenamento em memória da fila "+ type +" concluído!");
    }
}
