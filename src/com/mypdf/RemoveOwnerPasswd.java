package com.mypdf;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
public class RemoveOwnerPasswd {
 
    class MyReader extends PdfReader {
        public MyReader(String filename) throws IOException {
            super(filename);
        }
        public void decryptOnPurpose() {
            encrypted = false;
        }
    }
 
    public static final String SRC = "results/security/outpdf_encrypted.pdf";
    public static final String DEST = "results/security/hello.pdf";
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new RemoveOwnerPasswd().manipulatePdf(SRC, DEST);
    }
 
    public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        MyReader.unethicalreading = true;
        MyReader reader = new MyReader(src);
        reader.decryptOnPurpose();
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        stamper.close();
        reader.close();
        System.out.println("Owner passwd removed");
    }
}