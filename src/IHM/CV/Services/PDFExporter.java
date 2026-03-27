package IHM.CV.Services;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.font.FontProvider;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class PDFExporter {

    public void exportCVToPDF(String htmlContent, String outputPath) throws IOException {

        PdfWriter writer = new PdfWriter(new FileOutputStream(outputPath));

        PdfDocument pdfDoc = new PdfDocument(writer);
        pdfDoc.setDefaultPageSize(PageSize.A4);

        ConverterProperties properties = new ConverterProperties();

        FontProvider fontProvider = new FontProvider();
        fontProvider.addStandardPdfFonts();  // Helvetica, Times, Courier
        fontProvider.addSystemFonts();       // Polices installées sur le système
        properties.setFontProvider(fontProvider);

        HtmlConverter.convertToPdf(
                new ByteArrayInputStream(htmlContent.getBytes(StandardCharsets.UTF_8)),
                pdfDoc,
                properties   // <-- ConverterProperties, PAS FontProvider directement
        );

        pdfDoc.close();
    }
}