package com.company;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageConverter {

    public static final String userPassword = "vmsusr";
    public static final String ownerPassword = "password";

    public static void convert(File file) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4, 0, 0, 0, 0);
        Image image = Image.getInstance(file.getAbsolutePath());
        document.setPageSize(image);

        System.out.println("file.getParent(): " + file.getParent());
        System.out.println("file.getAbsolutePath(): " + file.getAbsolutePath());
        System.out.println("file.getPath(): " + file.getPath());

        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(file.getParent() + "/output.pdf"));

        encrptPDF(pdfWriter);

        document.open();
        document.add(image);
        document.close();
    }

    public static void encrptPDF(PdfWriter pdfWriter) throws DocumentException {
        pdfWriter.setEncryption(userPassword.getBytes(),
                        ownerPassword.getBytes(),
                        PdfWriter.ALLOW_PRINTING,
                        PdfWriter.ENCRYPTION_AES_128);
    }
}

