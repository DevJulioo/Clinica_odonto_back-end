package biovitta.com.clinics.services;

import biovitta.com.clinics.DTOs.RelatorioConsultaDTO;
import biovitta.com.clinics.entities.Consulta;
import biovitta.com.clinics.repositories.ConsultaRepositorio;

import com.lowagie.text.Document; // Importa Document do OpenPDF
import com.lowagie.text.DocumentException; // Importa DocumentException do OpenPDF
import com.lowagie.text.Element; // Importa Element do OpenPDF
import com.lowagie.text.Font; // Importa Font do OpenPDF
import com.lowagie.text.FontFactory; // Importa FontFactory do OpenPDF
import com.lowagie.text.PageSize; // Importa PageSize do OpenPDF
import com.lowagie.text.Paragraph; // Importa Paragraph do OpenPDF
import com.lowagie.text.Phrase; // Importa Phrase do OpenPDF
import com.lowagie.text.pdf.PdfPTable; // Importa PdfPTable do OpenPDF
import com.lowagie.text.pdf.PdfWriter; // Importa PdfWriter do OpenPDF

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RelatorioService {

    @Autowired
    ConsultaRepositorio consultaRepositorio;

    // Método original, que agora pode ser chamado internamente ou para outros fins
    public List<RelatorioConsultaDTO> gerarRelatorioConsultasPorPeriodoDTO(LocalDateTime dataInicio, LocalDateTime dataFim) {
        List<Consulta> consultas = consultaRepositorio.findByDataConsultaBetween(dataInicio, dataFim);
        return consultas.stream()
                .map(consulta -> new RelatorioConsultaDTO(
                        consulta.getConsultaId(),
                        consulta.getDataConsulta(),
                        consulta.getPaciente().getNome(),
                        consulta.getMedico().getNome(),
                        consulta.getEspecialidade() != null ? consulta.getEspecialidade().getNome() : "N/A"
                ))
                .collect(Collectors.toList());
    }

    // NOVO MÉTODO: Para gerar o PDF
    public byte[] gerarRelatorioConsultasPdf(LocalDateTime dataInicio, LocalDateTime dataFim) throws IOException {
        List<RelatorioConsultaDTO> consultas = gerarRelatorioConsultasPorPeriodoDTO(dataInicio, dataFim);

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Document document = new Document(PageSize.A4); // Usando a classe Document correta
            PdfWriter.getInstance(document, baos);

            document.open(); // Chamada correta do método open()

            // Adicionar título
            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD); // Usando a classe Font correta
            fontTitle.setSize(18);
            Paragraph title = new Paragraph("Relatório de Consultas", fontTitle);
            title.setAlignment(Element.ALIGN_CENTER); // Usando a classe Element correta para alinhamento
            document.add(title); // Chamada correta do método add()
            document.add(new Paragraph("\n")); // Espaço

            // Adicionar informações do período
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            Paragraph periodInfo = new Paragraph(
                    "Período: " + dataInicio.format(formatter) + " a " + dataFim.format(formatter)
            );
            document.add(periodInfo);
            document.add(new Paragraph("\n")); // Espaço

            // Adicionar a tabela de consultas
            PdfPTable table = new PdfPTable(5); // 5 colunas
            table.setWidthPercentage(100); // Largura da tabela em porcentagem
            // Definir larguras relativas das colunas (ex: ID pequena, Paciente maior)
            float[] columnWidths = {0.8f, 2f, 2.5f, 2.5f, 2f}; // Ajuste conforme necessário
            table.setWidths(columnWidths);
            table.setSpacingBefore(10f); // Espaço antes da tabela
            table.setSpacingAfter(10f); // Espaço depois da tabela

            // Cabeçalhos da tabela
            Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD); // Usando a classe Font correta
            table.addCell(new Phrase("ID", fontHeader));
            table.addCell(new Phrase("Data", fontHeader));
            table.addCell(new Phrase("Paciente", fontHeader));
            table.addCell(new Phrase("Médico", fontHeader));
            table.addCell(new Phrase("Especialidade", fontHeader));

            // Dados das consultas
            Font fontBody = FontFactory.getFont(FontFactory.HELVETICA); // Usando a classe Font correta
            for (RelatorioConsultaDTO consulta : consultas) {
                table.addCell(new Phrase(String.valueOf(consulta.getConsultaId()), fontBody));
                table.addCell(new Phrase(consulta.getDataConsulta().format(formatter), fontBody));
                table.addCell(new Phrase(consulta.getPacienteNome(), fontBody));
                table.addCell(new Phrase(consulta.getMedicoNome(), fontBody));
                table.addCell(new Phrase(consulta.getEspecialidadeNome(), fontBody));
            }
            document.add(table);

            document.close();
            return baos.toByteArray();

        } catch (DocumentException e) {
            throw new RuntimeException("Erro ao gerar o PDF do relatório.", e);
        }
    }
}