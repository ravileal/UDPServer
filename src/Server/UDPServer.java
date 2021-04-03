package Server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UDPServer extends Thread {

	private DatagramSocket socket;
 
    public UDPServer() throws SocketException {
        socket = new DatagramSocket(17);
    }
 
    public static void main(String[] args) {
        try {
        	UDPServer server = new UDPServer();
            server.service();
        } catch (SocketException ex) {
            System.out.println("Socket error: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
 
    private void service() throws IOException {
        while (true) {
        	// Recebe a requisição
            DatagramPacket request = new DatagramPacket(new byte[1], 1);
            socket.receive(request);
 
            // Configura mensagem
            String mensagem = "Yare Yare Daze";
            byte[] buffer = mensagem.getBytes();
 
            // Configura porta e endereço ip
            InetAddress clientAddress = request.getAddress();
            int clientPort = request.getPort();
 
            // Envia a resposta
            DatagramPacket response = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
            socket.send(response);
        }
    }
 
}