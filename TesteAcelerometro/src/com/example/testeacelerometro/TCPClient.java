/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.testeacelerometro;
import java.io.*;
import java.net.*;

class TCPClient {
   public static void main(String argv[]) throws Exception
   {
      String sentence;
      String modifiedSentence;
      BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
      //Socket clientSocket = new Socket(argv[0], 6789);
      Socket clientSocket = new Socket("localhost", 6700);
      DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
      BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      sentence = inFromUser.readLine();
      long time1 = System.nanoTime();
      outToServer.writeBytes(sentence + '\n');
      modifiedSentence = inFromServer.readLine();
      long time2 = System.nanoTime();
      long tempoTotal = (time2 - time1);
      
      long tamanho = sentence.length() * 2 * 8;
      System.out.println("Tamanho: " + tamanho + " bits");
      
      System.out.println("FROM SERVER: " + modifiedSentence + "\ntempo: " + tempoTotal + " ns.");
      
      System.out.println("Banda: " + tamanho/(tempoTotal/1000000) + " bits/ms");
      clientSocket.close();
   }
}
