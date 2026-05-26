import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class EMHASEMERGENCYACCESS {

    public static void ACCESSEMERGENCY(List<String> logs, String patientId) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            // Load fonts
            PDType0Font arial = PDType0Font.load(document, new File("C:/Windows/Fonts/arial.ttf"));
            PDType0Font arialBold = PDType0Font.load(document, new File("C:/Windows/Fonts/arialbd.ttf"));

            PDPageContentStream content = new PDPageContentStream(document, page);

            float margin = 50;
            float yStart = 750;
            float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
            float rowHeight = 30;
            int cols = 2;
            float colWidth = tableWidth / cols;

            // ✅ Add MediRush logo (upper left)
            PDImageXObject mediRushLogo = PDImageXObject.createFromFile("MEDIRUSHLOGO.jpg", document);
            content.drawImage(mediRushLogo, margin, page.getMediaBox().getHeight() - 100, 80, 80);

            // ✅ Add DOH logo (upper right)
            PDImageXObject dohLogo = PDImageXObject.createFromFile("DOHLOGO.png", document);
            content.drawImage(dohLogo, page.getMediaBox().getWidth() - margin - 80, page.getMediaBox().getHeight() - 100, 80, 80);

            // Title (shifted down so it doesn’t overlap logos)
            content.setFont(arialBold, 18);
            content.beginText();
            content.newLineAtOffset(margin, yStart - 50);
            content.showText("         Critical Emergency Data for Patient ID: " + patientId);
            content.endText();

            yStart -= 70;

            // Draw table
            yStart = drawTable(content, arial, arialBold, margin, yStart, colWidth, rowHeight, logs, "");

            content.close();

            // Save into PATIENTDETAILS folder
            String folderPath = "PATIENTDETAILS";
            File folder = new File(folderPath);
            if (!folder.exists()) folder.mkdirs();

            String fileName = folderPath + "/INFO_" + patientId + ".pdf";
            document.save(new File(fileName));
            System.out.println("PDF generated: " + fileName);

            // Auto-open PDF
            File pdfFile = new File(fileName);
            if (pdfFile.exists()) {
                java.awt.Desktop.getDesktop().open(pdfFile);
            }

        } catch (IOException e) {
            System.out.println("Error generating PDF: " + e.getMessage());
        }
    }

    // Helper method to draw a table section
    private static float drawTable(PDPageContentStream content, PDType0Font arial, PDType0Font arialBold,
                                   float margin, float yStart, float colWidth, float rowHeight,
                                   List<String> data, String sectionTitle) throws IOException {

        if (sectionTitle != null && !sectionTitle.isEmpty()) {
            content.setFont(arialBold, 14);
            content.beginText();
            content.newLineAtOffset(margin, yStart);
            content.showText(sectionTitle);
            content.endText();
            yStart -= 30;
        }

        // Headers
        String[] headers = {"Field", "Value"};
        content.setFont(arialBold, 16);
        for (int i = 0; i < headers.length; i++) {
            String header = headers[i];
            float textWidth = arialBold.getStringWidth(header) / 1000 * 12;
            float xOffset = margin + i * colWidth + (colWidth - textWidth) / 2;
            float yOffset = yStart - 20;

            content.beginText();
            content.newLineAtOffset(xOffset, yOffset);
            content.showText(header);
            content.endText();
        }

        // Draw grid
        int totalRows = data.size() + 1;
        for (int i = 0; i <= totalRows; i++) {
            float y = yStart - i * rowHeight;
            content.moveTo(margin, y);
            content.lineTo(margin + colWidth * 2, y);
        }
        for (int i = 0; i <= 2; i++) {
            float x = margin + i * colWidth;
            content.moveTo(x, yStart);
            content.lineTo(x, yStart - totalRows * rowHeight);
        }
        content.stroke();

        // Fill rows
        int rowIndex = 1;
        for (String entry : data) {
            String[] parts = entry.split(":", 2);
            for (int i = 0; i < parts.length; i++) {
                content.beginText();
                if (i == 0) {
                    content.setFont(arialBold, 11);
                } else {
                    content.setFont(arial, 11);
                }
                content.newLineAtOffset(margin + i * colWidth + 10, yStart - rowIndex * rowHeight - 20);
                content.showText(parts[i].trim());
                content.endText();
            }
            rowIndex++;
        }

        return yStart - totalRows * rowHeight;
    }
}
