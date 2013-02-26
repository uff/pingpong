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
        //Socket clientSocket = new Socket(ip, porta);
        clientSocket = new Socket("localhost", 6700);
        outToServer = new DataOutputStream(clientSocket.getOutputStream());
        inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

    }
    
    public void enviarDados() throws Exception {
    
        //String sentence = inFromUser.readLine();
    	String sentence = "Testando Socket";
    	outToServer.writeBytes(sentence + '\n');
        modifiedSentence = inFromServer.readLine();
    }
}
