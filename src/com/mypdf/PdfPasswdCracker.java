package com.mypdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.exceptions.BadPasswordException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class PdfPasswdCracker {
	
	public static final String SRC = "results/security/outpdf_encrypted.pdf";
    public static final String DEST = "results/security/outpdf_encrypted_cracked.pdf";
    public static boolean workOn = true;
    static int guestPasswd = 306922;

	public static void main(String[] args) {
		
		File file = new File(DEST);
        file.getParentFile().mkdirs();
        while(workOn){
        	try {
				manipulatePdf(SRC, DEST, guestPasswd++);
			} catch (BadPasswordException e) {
				System.out.println(guestPasswd-1+" : BAD Passwd");
//				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("don't know");
				e.printStackTrace();
			} catch (DocumentException e) {
				System.out.println("don't know 1");
				e.printStackTrace();
			}
        	if(guestPasswd > 306940){
        		workOn = false;
        	}
        }
		

	}
	
	public static  boolean manipulatePdf(String src, String dest, int passwd) throws IOException, DocumentException, BadPasswordException {
		System.out.println("trying with: "+passwd);
        PdfReader reader = new PdfReader(src, String.valueOf(passwd).getBytes());
        System.out.println(new String(reader.computeUserPassword()));
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        stamper.close();
        reader.close();
        System.out.println("Always in your service: \nCreacking is done for you.");
        return false;
    }

}
