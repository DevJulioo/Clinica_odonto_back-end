package biovitta.com.clinics.controllers;

import biovitta.com.clinics.DTOs.RelatorioConsultaDTO;
import biovitta.com.clinics.services.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("biovitta/api/relatorio")
public class RelatorioController {

    @Autowired
    RelatorioService relatorioService;

    // Endpoint original que retorna JSON
    @GetMapping("/consultas-por-periodo-json")
    public ResponseEntity<List<RelatorioConsultaDTO>> getConsultasPorPeriodoJson(
            @RequestParam("dataInicio") LocalDateTime dataInicio,
            @RequestParam("dataFim") LocalDateTime dataFim) {
        List<RelatorioConsultaDTO> relatorio = relatorioService.gerarRelatorioConsultasPorPeriodoDTO(dataInicio, dataFim);
        return ResponseEntity.ok(relatorio);
    }

    // NOVO ENDPOINT: Para gerar o PDF
    @GetMapping("/consultas-por-periodo-pdf")
    public ResponseEntity<byte[]> getConsultasPorPeriodoPdf(
            @RequestParam("dataInicio") LocalDateTime dataInicio,
            @RequestParam("dataFim") LocalDateTime dataFim) throws IOException {
        
        byte[] pdfContent = relatorioService.gerarRelatorioConsultasPdf(dataInicio, dataFim);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "relatorio_consultas.pdf"); // Isso força o download
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfContent);
    }
}