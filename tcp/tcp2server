import java.net.*;
import java.io.*;
import java.util.*;

class TCP2Server {
    public static void main(String[] args) {
        ServerSocket ss = null;
        Socket s = null;
        DataInputStream dis = null;
        DataOutputStream dout = null;
        Scanner scan = null;

        try {
            ss = new ServerSocket(5656);
            System.out.println("Server started. Waiting for client...");
            s = ss.accept();
            System.out.println("Client connected: " + s.getInetAddress());

            dis = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            scan = new Scanner(System.in);

            while (true) {
                String str = dis.readUTF();  // Wait for client message
                System.out.println("Client ---> " + str);

                if (str.equalsIgnoreCase("bye")) {
                    System.out.println("Client messaged BYE... Exiting");
                    break;
                }

                System.out.print("Enter your message: ");
                String reply = scan.nextLine();
                dout.writeUTF(reply);
                dout.flush();
            }

        } catch (IOException e) {
            System.out.println("Connection error: " + e);
        } finally {
            try {
                if (scan != null) scan.close();
                if (dout != null) dout.close();
                if (dis != null) dis.close();
                if (s != null) s.close();
                if (ss != null) ss.close();
            } catch (IOException e) {
                System.out.println("Error closing resources: " + e);
            }
        }
    }
}
