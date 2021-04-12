package com.company;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PDFConverter {

    public final static String root = "/Users/andyyeung/desktop";
    public final static String outFilename = "output.pdf";
    public final static String OUT_FILE = Paths.get(root, outFilename).toString();
    public final static String OUT_FILE2 = Paths.get(root, "2" + outFilename).toString();

    public static byte[] base64ToPdf(String base64Str, int[] selectedPages) throws IOException, DocumentException {
        if (selectedPages.length == 0)
            base64ToPdf(base64Str);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] decoded = java.util.Base64.getDecoder().decode(base64Str);

        PdfReader reader = new PdfReader(decoded);
        Document document = new Document();
        PdfCopy writer = new PdfCopy(document, outputStream);

        document.open();
        PdfImportedPage page = null;
        for (int i=0; i < selectedPages.length; i++) {
            page = writer.getImportedPage(reader, selectedPages[i]);
            writer.addPage(page);
        }
        document.close();
        writer.close();

        return outputStream.toByteArray();
    }

    public static byte[] base64ToPdf(String base64Str) throws IOException, DocumentException {
        byte[] decoded = java.util.Base64.getDecoder().decode(base64Str);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PdfReader reader = new PdfReader(decoded);
        Document document = new Document();
        PdfCopy writer = new PdfCopy(document, outputStream);

        document.open();
        PdfImportedPage page = null;
        for (int i=0; i < reader.getNumberOfPages(); i++) {
            page = writer.getImportedPage(reader, i+1);
            writer.addPage(page);
        }
        document.close();
        writer.close();

        return outputStream.toByteArray();
    }

    public static void createPDFfromByteArr(byte[] inputStream, String filePath) throws IOException {
        FileOutputStream fos = new FileOutputStream(filePath);
        fos.write(inputStream);
        fos.flush();
        fos.close();
    }

    public static void Main(String[] args) {
        try {
            byte[] inFileBytes = Files.readAllBytes(Paths.get(OUT_FILE));
            String base64Str = java.util.Base64.getEncoder().encodeToString(inFileBytes);

            byte[] outputDocument = PDFConverter.base64ToPdf(base64Str, new int[] {2,3});
            PDFConverter.createPDFfromByteArr(outputDocument, OUT_FILE2);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
