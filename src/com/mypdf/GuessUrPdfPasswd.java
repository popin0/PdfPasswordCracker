package com.mypdf;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;

public class GuessUrPdfPasswd implements Runnable{
	
	private static volatile boolean workOn = true;
//	private static volatile int guess;
	
	Thread t;

	GuessUrPdfPasswd(String t_name){
		t = new Thread(this, t_name);
		t.setPriority(Thread.MAX_PRIORITY);
		System.out.println("Thread created: "+t);
		t.start();
	}
	
	public void run(){
		guessAndTry(GuessUrPdfPasswdMain.SRC, GuessUrPdfPasswdMain.DEST);
	}
	
    private static void guessAndTry(String src, String dest) { 
    	int myGuess;
    	PdfReader.unethicalreading = true;
    	while(workOn){
    		PdfReader reader;
			try {
				myGuess = GuessUrPdfPasswdMain.guess++;
//				System.out.println("trying with: "+myGuess);
				reader = new PdfReader(src, String.valueOf(myGuess).getBytes());
				workOn = false;
				inspect(reader);
	            createCopy(reader, dest);
	            reader.close();
	            System.out.println("The password for your file was: "+ myGuess);
			} catch (IOException | DocumentException e) {
				
			}   		
            
    	}         
    }
    
    private static void createCopy(PdfReader reader, String dest) throws IOException, DocumentException {
            int n = reader.getNumberOfPages();
            Document document = new Document();
            PdfCopy copy = new PdfCopy(document, new FileOutputStream(dest));
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
