/*
SYST44288
October 5, 2018
Students: Pavel K, Herit G
Professor: Christina R.
*/
   
import java.net.Socket;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.util.Scanner;

public class EchoClient 
{
    public static void main(String args[]) throws IOException 
    {
        try (Socket s = new Socket("127.0.0.1",7777)) 
        {
            //Declare variables
            String line;
            Scanner scanner;
            String scan;
            
            //Open streams
            BufferedReader dis = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter dos = new PrintWriter(s.getOutputStream(), true);

            //Scan of user input and send it to the server. Print server response
            scanner = new Scanner(System.in);
            while(!".".equals(scan = scanner.nextLine()))
            {
                dos.println(scan);
                line = dis.readLine();
                System.out.println(line);
            }
        }
    }
}