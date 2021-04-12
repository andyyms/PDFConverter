package com.company;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.log.DefaultCounter;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.tools.javac.util.Assert;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import javax.print.Doc;
import javax.print.attribute.standard.DocumentName;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public final static String root = "/Users/andyyeung/desktop";
    public final static String outFilename = "output.pdf";
    public final static String OUT_FILE = Paths.get(root, outFilename).toString();
    public final static String OUT_FILE2 = Paths.get(root, "2" + outFilename).toString();

    public static void main(String[] args) {
        try {
            File sourcefile = new File("/Users/andyyeung/desktop/Combined_Document.tiff");
            File destFile = new File("/Users/andyyeung/desktop/Combined_Document_watermark.tiff");

            Watermark.addTextWatermark("    BOCHK   ",
                            sourcefile,
                            destFile);

            System.out.println("Start Converting...");
            ImageConverter.convert(destFile);
            System.out.println("Finished");
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
