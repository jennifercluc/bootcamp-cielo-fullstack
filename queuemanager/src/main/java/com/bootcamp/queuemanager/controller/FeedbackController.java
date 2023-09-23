package com.bootcamp.queuemanager.controller;

import com.bootcamp.queuemanager.dto.CustomerFeedbackDTO;
import com.bootcamp.queuemanager.dto.CustomerFeedbackRequestDTO;
import com.bootcamp.queuemanager.service.FeedbackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;

@Tag(name = "Feedback", description = "APIs de envio e verificação de Feedbacks")
@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    /** Enviar Feedback **/
    @Operation(
            summary = "Envia um Feedback",
            description = "Envia um Feedback, que é uma mensagem de texto de 1 dos 3 tipos [SUGESTAO, ELOGIO, CRITICA].")
    @PostMapping("/envio")
    public ResponseEntity<String> enviarFeedback(@RequestBody CustomerFeedbackRequestDTO feedback) {
        feedbackService.sendFeedback(feedback);
        return ResponseEntity.ok("Feedback enviado!");
    }

    /** Obter tamanho atual da fila de feedbacks para cada tipo **/
    @Operation(
            summary = "Obtem o tamanho da fila")
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
    @Operation(
            summary = "Obtem informaçes sobre Feedbacks")
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
