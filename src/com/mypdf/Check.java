package com.mypdf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
 
 
public class Check {
	
	public static final String RESULT = "resources/noname.pdf";
	public static final String OUT_INFO = "resources/noname_info.txt";
	
	public static void main(String[] args)
			  throws DocumentException, IOException {
//			  Document document = new Document();                       
//			  PdfWriter.getInstance(document, new FileOutputStream(RESULT)); 
//			  document.open();                                            
//			  document.add(new Paragraph("Hello World!"));   
//			  document.close();
			  			  
			  PrintWriter writer = new PrintWriter(new FileOutputStream(OUT_INFO));
		      inspect(writer, RESULT);
		      writer.close();
		      System.out.println("done");
			}
	
	public static void inspect(PrintWriter writer, String filename)
	        throws IOException {
	        PdfReader reader = new PdfReader(filename);
	        writer.println(filename);
	        writer.print("Number of pages: ");
	        writer.println(reader.getNumberOfPages());
	        Rectangle mediabox = reader.getPageSize(1);
	        writer.print("Size of page 1: [");
	        writer.print(mediabox.getLeft());
	        writer.print(',');
	        writer.print(mediabox.getBottom());
	        writer.print(',');
	        writer.print(mediabox.getRight());
	        writer.print(',');
	        writer.print(mediabox.getTop());
	        writer.println("]");
	        writer.print("Rotation of page 1: ");
	        writer.println(reader.getPageRotation(1));
	        writer.print("Page size with rotation of page 1: ");
	        writer.println(reader.getPageSizeWithRotation(1));
	        writer.print("Is rebuilt? ");
	        writer.println(reader.isRebuilt());
	        writer.print("Is encrypted? ");
	        writer.println(reader.isEncrypted());
	        writer.println();
	        writer.flush();
	        reader.close();
	    }
	
}