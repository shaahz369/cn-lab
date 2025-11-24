import java.io.*;
import java.net.*;
import java.util.*;

public class  UDP2Client 
 {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        Scanner scan = null;

        try {
            scan = new Scanner(System.in);
            socket = new DatagramSocket(); // no fixed port — OS picks one

            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 5656;

            byte[] sendBuffer;
            byte[] receiveBuffer = new byte[1024];

            while (true) {
                // Send message to server
                System.out.print("Write your message: ");
                String str = scan.nextLine();
                sendBuffer = str.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, serverPort);
                socket.send(sendPacket);

                if (str.equalsIgnoreCase("bye")) {
                    System.out.println("Disconnected from server.");
                    break;
                }

                // Receive server's reply
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(receivePacket);
                String reply = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Server ---> " + reply);
            }

        } catch (IOException e) {
            System.out.println("Client Error: " + e);
        } finally {
            if (scan != null) scan.close();
            if (socket != null) socket.close();
        }
    }
}