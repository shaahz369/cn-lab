import java.net.*;
import java.io.*;
import java.util.*;

public class TCP2Client {
    public static void main(String[] args) {
        Socket s = null;
        DataOutputStream dout = null;
        DataInputStream dis = null;
        Scanner scan = null;

        try {
            scan = new Scanner(System.in);
            s = new Socket("localhost", 5656);
            System.out.println("Connected to server.");

            dout = new DataOutputStream(s.getOutputStream());
            dis = new DataInputStream(s.getInputStream());

            while (true) {
                System.out.print("Write your message: ");
                String str = scan.nextLine();

                dout.writeUTF(str);
                dout.flush();

                if (str.equalsIgnoreCase("bye")) {
                    System.out.println("Disconnected from server.");
                    break;
                }

                // Receive server's reply
                String reply = dis.readUTF();
                System.out.println("Server ---> " + reply);
            }

        } catch (IOException e) {
            System.out.println("Client Error: " + e);
        } finally {
            try {
                if (scan != null) scan.close();
                if (dout != null) dout.close();
                if (dis != null) dis.close();
                if (s != null) s.close();
            } catch (IOException e) {
                System.out.println("Error closing resources: " + e);
            }
        }
    }
}