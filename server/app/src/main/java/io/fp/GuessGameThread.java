package io.fp;

import java.net.*;
import java.util.Random;
import java.io.*;

public class GuessGameThread extends Thread{
    
    private Socket clientSocket;

    public GuessGameThread(Socket s)
        throws IOException {
        clientSocket = s;
        start(); // Calls run()
    }


    @Override
    public void run(){
        try {
            while (true) {
                
                InputStream in = clientSocket.getInputStream();
                OutputStream out = clientSocket.getOutputStream();
                int guessingNumber = 0;
                boolean keepPlaying = true;
                boolean keepPlaying2 = true;
                int initGame;
                int guessedNumber;
                int counter;

                while(keepPlaying){
                initGame = in.read();
                if(initGame==0){
                    System.out.println("Conncetion Succesfull");
                    Random r = new Random();
                    guessingNumber = r.nextInt(10);
                    System.out.println("Guessing number is: "+guessingNumber);
                    keepPlaying2=true;
                    out.write(0);
                } else{keepPlaying2=false;
                    keepPlaying=false;}
                    counter = 0;

                        while(keepPlaying2){
                            guessedNumber = in.read();
                            if(guessedNumber==guessingNumber){
                                keepPlaying2=false;
                                System.out.println("1 Guessed number is:"+guessedNumber);

                                out.write(1);

                            } else if(counter==2){
                                keepPlaying2=false;
                                System.out.println("9 Guessed number is:"+guessedNumber);
                                out.write(9);
                            } else{
                                keepPlaying2= true;
                                System.out.println("2 Guessed number is:"+guessedNumber);
                                counter++;
                                out.write(2);
                            }
                        }   
                                                                  
            }                
        
        }}catch (Exception e) {
            System.out.println("Listen :" + e.getMessage());
        }
    }}
