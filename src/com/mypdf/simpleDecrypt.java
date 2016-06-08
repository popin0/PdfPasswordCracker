package com.mypdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
public class simpleDecrypt {
 
	public static final String SRC = "results/security/outpdf_encrypted.pdf";
    public static final String DEST = "results/security/hello.pdf";
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        manipulatePdf(SRC, DEST);
    }
 
    public static void manipulatePdf(String src, String dest) {
    	boolean workOn = true;
    	int tryPasswd = 100000;
    	PdfReader.unethicalreading = true;
    	while(workOn){
    		PdfReader reader;
			try {
//				System.out.println("trying with: "+tryPasswd);
				reader = new PdfReader(src, String.valueOf(tryPasswd++).getBytes());
				inspect(reader);
	            createCopy(reader);
	            reader.close();
	            workOn = false;
	            System.out.println("The password for your file was: "+ (tryPasswd - 1));
			} catch (IOException | DocumentException e) {
				
			}   		
            
    	}        
        
        System.out.println("Always in your service: Password cracked successfully");
    }
    
    private static void createCopy(PdfReader reader) throws IOException, DocumentException {
            int n = reader.getNumberOfPages();
            Document document = new Document();
            PdfCopy copy = new PdfCopy(document, new FileOutputStream(DEST));
            document.open();
            for (int i = 0; i < n;) {
                copy.addPage(copy.getImportedPage(reader, ++i));
            }
            document.close();
        }
    
    private static void inspect(PdfReader reader){
    	System.out.print("Number of pages: ");
        System.out.println(reader.getNumberOfPages());
        Rectangle mediabox = reader.getPageSize(1);
        System.out.print("Size of page 1: [");
        System.out.print(mediabox.getLeft());
        System.out.print(',');
        System.out.print(mediabox.getBottom());
        System.out.print(',');
        System.out.print(mediabox.getRight());
        System.out.print(',');
        System.out.print(mediabox.getTop());
        System.out.println("]");
        System.out.print("Rotation of page 1: ");
        System.out.println(reader.getPageRotation(1));
        System.out.print("Page size with rotation of page 1: ");
        System.out.println(reader.getPageSizeWithRotation(1));
        System.out.print("Is rebuilt? ");
        System.out.println(reader.isRebuilt());
        System.out.print("Is encrypted? ");
        System.out.println(reader.isEncrypted());
        System.out.println();
    }
}