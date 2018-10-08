/*
SYST44288
October 5, 2018
Students: Pavel K, Herit G
Professor: Christina R.
*/
   
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;

public class EchoServer 
{
    public static void main(String args[]) 
    {
        try
        {
            ServerSocket ss = new ServerSocket(7777);
            while (true) 
            {
                try (Socket s = ss.accept()) 
                {
                    BufferedReader dis = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    PrintWriter dos = new PrintWriter(s.getOutputStream(),true);
                    
                    String userInput;
                    while (null != (userInput = dis.readLine()))
                    {
                        dos.println("Server: " + userInput);
                    }
                }
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
