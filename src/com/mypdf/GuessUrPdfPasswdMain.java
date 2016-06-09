package com.mypdf;

public class GuessUrPdfPasswdMain {
	
	public static final String SRC = "results/security/noname.pdf";
    public static final String DEST = "results/security/noname_cracked.pdf";
    
    public static volatile int guess = 100000;
    public static int maxGuess = 999999;
	public static void main(String[] args) {
		
		long start = System.currentTimeMillis();
		
		System.out.println("Cracking passwd in progress......");
		
		GuessUrPdfPasswd t1 = new GuessUrPdfPasswd("thread1");
		GuessUrPdfPasswd t2 = new GuessUrPdfPasswd("thread2");
		GuessUrPdfPasswd t3 = new GuessUrPdfPasswd("thread3");
		GuessUrPdfPasswd t4 = new GuessUrPdfPasswd("thread4");
		GuessUrPdfPasswd t5 = new GuessUrPdfPasswd("thread5");
		GuessUrPdfPasswd t6 = new GuessUrPdfPasswd("thread6");
		GuessUrPdfPasswd t7 = new GuessUrPdfPasswd("thread7");
		GuessUrPdfPasswd t8 = new GuessUrPdfPasswd("thread8");
		GuessUrPdfPasswd t9 = new GuessUrPdfPasswd("thread9");
		GuessUrPdfPasswd t10 = new GuessUrPdfPasswd("thread10");
		try {
			t1.t.join();
			t2.t.join();
			t3.t.join();
			t4.t.join();
			t5.t.join();
			t6.t.join();
			t7.t.join();
			t8.t.join();
			t9.t.join();
			t10.t.join();
		} catch (InterruptedException e) {
			System.out.println("Somewhere something went wrong run()");
			e.printStackTrace();
		}		
		long elapsed = System.currentTimeMillis() - start;
		System.out.println("Always in your service:"+ elapsed +" milisecs");
	}

}
