package com.example.testeacelerometro;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.Bundle;

public class Cliente {
	BufferedReader inFromUser;
	Socket clientSocket;
	DataOutputStream outToServer;
	BufferedReader inFromServer;
	
	String modifiedSentence;
    
	
    public Cliente(String ip, int port) throws Exception {
        inFromUser = new BufferedReader(new InputStreamReader(System.in));
        clientSocket = new Socket(ip, port);
        //clientSocket = new Socket("localhost", 6700);
        outToServer = new DataOutputStream(clientSocket.getOutputStream());
        inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        outToServer.writeBytes("olá pc!" + '\n');
        modifiedSentence = inFromServer.readLine();
        
    }
    
    public void enviarDados(double z) throws Exception {
    
        //String sentence = inFromUser.readLine();
    	//String sentence = "Testando Socket";
    	outToServer.writeBytes("" + z + '\n');
        modifiedSentence = inFromServer.readLine();
        //comentario teste
    }
}
