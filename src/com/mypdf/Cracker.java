package com.mypdf;

import java.io.IOException;

import com.itextpdf.text.DocumentException;

public class Cracker {

	public static void main(String[] args) {
//		try {
//			Check.main(args);
//		} catch (DocumentException e) {
//			System.out.println("You can not inspect without passwd");
//			e.printStackTrace();
//		} catch (IOException e) {
//			System.out.println("You can not inspect without passwd2");
//			e.printStackTrace();
//		}
		
		DecryptPdf t1 = new DecryptPdf("thread1");
//		DecryptPdf t2 = new DecryptPdf("thread2");
//		DecryptPdf t3 = new DecryptPdf("thread3");
//		DecryptPdf t4 = new DecryptPdf("thread4");
//		DecryptPdf t5 = new DecryptPdf("thread5");
//		DecryptPdf t6 = new DecryptPdf("thread6");
//		DecryptPdf t7 = new DecryptPdf("thread7");
//		DecryptPdf t8 = new DecryptPdf("thread8");
//		DecryptPdf t9 = new DecryptPdf("thread9");
		
		try {
			t1.t.join();
//			t2.t.join();
//			t3.t.join();
//			t4.t.join();
//			t5.t.join();
//			t6.t.join();
//			t7.t.join();
//			t8.t.join();
//			t9.t.join();
		} catch (InterruptedException e) {
			System.out.println("Well something happen in your run()");
			e.printStackTrace();
		}
		
		System.out.println("Exiting main");

	}

}
