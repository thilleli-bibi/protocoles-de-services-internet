package projet_psi;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Communication extends Thread {
Socket so;
PrintWriter pw;
BufferedReader br;
public Communication(Socket so) throws IOException {
this.so=so;
this.br=new BufferedReader(new InputStreamReader(so.getInputStream()));
this.pw=new PrintWriter(new OutputStreamWriter(so.getOutputStream()));
}
public  void run()
{
try {
String mess_chiffrer=br.readLine();System.out.println("com"+ mess_chiffrer);
br.close();
so.close();
//System.out.println(mess_chiffrer);
} catch (IOException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
}
}
