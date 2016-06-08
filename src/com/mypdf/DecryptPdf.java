/*
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/27867868/how-can-i-decrypt-a-pdf-document-with-the-owner-password
 */

package com.mypdf;
 
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.exceptions.BadPasswordException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
public class DecryptPdf implements Runnable{
 
    public static final String SRC = "results/security/outpdf_encrypted.pdf";
    public static final String DEST = "results/security/outpdf_encrypted_cracked.pdf";
    public static volatile int passwd = 306922;
    public static File file;
    public static volatile boolean workOn = true;
    Thread t;
    
    
    DecryptPdf(String t_name){
    	t = new Thread(this, t_name);
    	System.out.println("created: "+t);
    	t.start();
    }
 
 
    public static  boolean manipulatePdf(String src, String dest, int passwd) throws IOException, DocumentException, BadPasswordException {
        PdfReader reader = new PdfReader(src, String.valueOf(passwd).getBytes());
        System.out.println(new String(reader.computeUserPassword()));
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        stamper.close();
        reader.close();
        System.out.println("Always in your service: \nCreacking is done for you.");
        return false;
    }
    

	@Override
	public void run() {
		System.out.println("starting: "+t);
		if(file == null){
			file = new File(DEST);
		}		
        file.getParentFile().mkdirs();
        
		while(workOn){
			try{        	
				System.out.println(passwd);
				workOn = manipulatePdf(SRC, DEST, passwd++);
	        }catch(IOException | DocumentException e){
	        	System.out.println("Aeeeeee BAD Password");
	        }
			if(passwd > 306940){
				workOn = false;
			}
		}
		
	}
}