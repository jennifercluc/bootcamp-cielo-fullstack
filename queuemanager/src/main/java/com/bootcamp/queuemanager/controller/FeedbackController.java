package com.bootcamp.queuemanager.controller;

import com.bootcamp.queuemanager.model.CustomerFeedbackRequest;
import com.bootcamp.queuemanager.service.FeedbackProducerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Feedback", description = "APIs de envio e verificação de Feedbacks")
@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackProducerService feedbackProducerService;

    /** Enviar Feedback **/
    @Operation(
            summary = "Envia um Feedback",
            description = "Envia um Feedback, que é uma mensagem de texto de 1 dos 3 tipos [SUGESTAO, ELOGIO, CRITICA].")
    @PostMapping("/envio")
    public ResponseEntity<String> enviarFeedback(@RequestBody CustomerFeedbackRequest feedback) {
        feedbackProducerService.sendFeedback(feedback);
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
    public ResponseEntity<List<CustomerFeedbackRequest>> obterInformacoesFeedbacks (@RequestParam String tipo) {
        List<CustomerFeedbackRequest> feedbacks = new ArrayList<>();
            //TODO chamada da service, utilizar MAPA cuja chave é a inicial do tipo da fila
        return ResponseEntity.ok(feedbacks);
    }
}
