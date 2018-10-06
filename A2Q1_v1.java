/*
SYST44288
October 5, 2018
Students: Pavel K, Herit G
Professor: Christina R.
*/

package a1;
   
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.BufferedReader;

class A1 
{
    public static void main(String args[]) 
    {
        try
        {
            ServerSocket ss = new ServerSocket(6013);
            while (true) 
            {
                Socket s = ss.accept();
                InputStream is = s.getInputStream();
                DataInputStream dis = new DataInputStream(is);

                OutputStream os = s.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);

//                int data = dis.read();
//                while (data != -1)
//                {
//                    dos.writeBytes("\nServer: ");
//                    dos.writeByte(data);
//                    data = dis.read();
//                }

                String userInput;
                while (null != (userInput = dis.readLine())) 
                {
                    dos.writeBytes("Server: " + userInput + "\n");
                }
                
                s.close();
                ss.close();
            }
        }
        catch (IOException ioe)
        {
            System.err.println(ioe);
            System.out.println("Connection closed by user.");
        }
    }
}
