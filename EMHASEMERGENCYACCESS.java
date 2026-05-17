import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class EMHASEMERGENCYACCESS {

    public static void ACCESSEMERGENCY(List<String> logs, String patientId) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            // Load Arial font (adjust path if needed)
            PDType0Font arial = PDType0Font.load(document, new File("C:/Windows/Fonts/arial.ttf"));

            PDPageContentStream content = new PDPageContentStream(document, page);

            float margin = 50;
            float yStart = 750;

            // Title
            content.setFont(arial, 14);
            content.beginText();
            content.newLineAtOffset(margin, yStart);
            content.showText("Emergency Access Logs for Patient ID: " + patientId);
            content.endText();

            yStart -= 30;

            // Log entries (just line by line, no table)
            content.setFont(arial, 12);
            for (String log : logs) {
                content.beginText();
                content.newLineAtOffset(margin, yStart);
                content.showText(log);
                content.endText();
                yStart -= 20; // move down for next line
            }

            content.close();

            // Save into PATIENTDETAILS folder inside your project
            String folderPath = "PATIENTDETAILS"; 
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs(); // create folder if missing
            }

            String fileName = folderPath + "/logs_" + patientId + ".pdf";
            document.save(new File(fileName));
            System.out.println();
            System.out.println("PDF generated and saved to: " + fileName);

            // Auto-open PDF
            File pdfFile = new File(fileName);
            if (pdfFile.exists()) {
                java.awt.Desktop.getDesktop().open(pdfFile);
            }

        } catch (IOException e) {
            System.out.println();
            System.out.println("Error generating PDF: " + e.getMessage());
        }
    }
}
