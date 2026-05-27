import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class EMHASEMERGENCYACCESS {

    public static void ACCESSEMERGENCY(String patientId,
                                       String patientName,
                                       String bloodType,
                                       String emergencyContact,
                                       List<String> allergies,
                                       List<String> conditions,
                                       List<String> medications,
                                       List<String> familyHistory,
                                       List<String> immunizations) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            // Load fonts
            PDType0Font arial = PDType0Font.load(document, new File("C:/Windows/Fonts/arial.ttf"));
            PDType0Font arialBold = PDType0Font.load(document, new File("C:/Windows/Fonts/arialbd.ttf"));

            PDPageContentStream content = new PDPageContentStream(document, page);

            float margin = 50;
            float yStart = 750;
            float rowHeight = 25;

            // ✅ Add MediRush logo (upper left)
            PDImageXObject mediRushLogo = PDImageXObject.createFromFile("MEDIRUSHLOGO.jpg", document);
            content.drawImage(mediRushLogo, margin, page.getMediaBox().getHeight() - 100, 80, 80);

            // ✅ Add DOH logo (upper right)
            PDImageXObject dohLogo = PDImageXObject.createFromFile("DOHLOGO.png", document);
            content.drawImage(dohLogo, page.getMediaBox().getWidth() - margin - 80, page.getMediaBox().getHeight() - 100, 80, 80);

            // Title
            content.setFont(arialBold, 18);
            content.beginText();
            content.newLineAtOffset(margin, yStart - 50);
            content.showText("            Critical Emergency Data for Patient ID: " + patientId);
            content.endText();

            yStart -= 100;

            // Patient core info
            content.setFont(arialBold, 14);
            content.beginText();
            content.newLineAtOffset(margin, yStart);
            content.showText("Patient Name: " + patientName);
            content.endText();

            yStart -= 20;
            content.beginText();
            content.newLineAtOffset(margin, yStart);
            content.showText("Blood Type: " + bloodType);
            content.endText();

            yStart -= 20;
            content.beginText();
            content.newLineAtOffset(margin, yStart);
            content.showText("Emergency Contact: " + emergencyContact);
            content.endText();

            yStart -= 10;

            // Draw medical info table (✅ fixed call includes page)
            yStart = drawMedicalTable(content, page, arial, arialBold, margin, yStart, rowHeight,
                                      allergies, conditions, medications, familyHistory, immunizations);

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

    // Helper method to draw the 5-column medical info table
    private static float drawMedicalTable(PDPageContentStream content, PDPage page,
                                          PDType0Font arial, PDType0Font arialBold,
                                          float margin, float yStart, float rowHeight,
                                          List<String> allergies, List<String> conditions,
                                          List<String> medications, List<String> familyHistory,
                                          List<String> immunizations) throws IOException {

        String[] headers = {"ALLERGIES", "CONDITIONS", "MEDICATIONS", "PEDIGREES", "VACCINES"};
        int cols = headers.length;
        float tableWidth = page.getMediaBox().getWidth() - 2 * margin;  // ✅ fixed
        float colWidth = tableWidth / cols;

        // Draw headers
        content.setFont(arialBold, 12);
        for (int i = 0; i < headers.length; i++) {
            float textWidth = arialBold.getStringWidth(headers[i]) / 1000 * 12;
            float xOffset = margin + i * colWidth + (colWidth - textWidth) / 2;
            float yOffset = yStart - 20;
            content.beginText();
            content.newLineAtOffset(xOffset, yOffset);
            content.showText(headers[i]);
            content.endText();
        }

        // Determine max rows among all lists
        int maxRows = Math.max(allergies.size(),
                       Math.max(conditions.size(),
                       Math.max(medications.size(),
                       Math.max(familyHistory.size(), immunizations.size()))));

        // Draw grid
        for (int i = 0; i <= maxRows + 1; i++) {
            float y = yStart - i * rowHeight;
            content.moveTo(margin, y);
            content.lineTo(margin + tableWidth, y);
        }
        for (int i = 0; i <= cols; i++) {
            float x = margin + i * colWidth;
            content.moveTo(x, yStart);
            content.lineTo(x, yStart - (maxRows + 1) * rowHeight);
        }
        content.stroke();

        // Fill rows
        content.setFont(arial, 11);
        for (int row = 0; row < maxRows; row++) {
            if (row < allergies.size()) {
                content.beginText();
                content.newLineAtOffset(margin + 10, yStart - (row + 1) * rowHeight - 20);
                content.showText(allergies.get(row));
                content.endText();
            }
            if (row < conditions.size()) {
                content.beginText();
                content.newLineAtOffset(margin + colWidth + 10, yStart - (row + 1) * rowHeight - 20);
                content.showText(conditions.get(row));
                content.endText();
            }
            if (row < medications.size()) {
                content.beginText();
                content.newLineAtOffset(margin + 2 * colWidth + 10, yStart - (row + 1) * rowHeight - 20);
                content.showText(medications.get(row));
                content.endText();
            }
            if (row < familyHistory.size()) {
                content.beginText();
                content.newLineAtOffset(margin + 3 * colWidth + 10, yStart - (row + 1) * rowHeight - 20);
                content.showText(familyHistory.get(row));
                content.endText();
            }
            if (row < immunizations.size()) {
                content.beginText();
                content.newLineAtOffset(margin + 4 * colWidth + 10, yStart - (row + 1) * rowHeight - 20);
                content.showText(immunizations.get(row));
                content.endText();
            }
        }

        return yStart - (maxRows + 1) * rowHeight;
    }
}
