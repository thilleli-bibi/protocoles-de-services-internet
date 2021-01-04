package projet_psi;
import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList; 

public class Server{
	public  static  int ref=0;
	public static final  Object lock = new Object();
	public static ArrayList<Annonces> annonces = new ArrayList<Annonces>();
	public static ArrayList<User> Users=new ArrayList<User>();

	public static BufferedReader br;
	public static void main(String[] args){
	
	try{
		String mess;
		ServerSocket serv=new ServerSocket(1027);

		while(true)
		{	
			Socket so=serv.accept();
			System.out.println("serveur : ");
			ThreadAction th=new ThreadAction(so);
			th.start();
			//th.start();
			
			//br =new BufferedReader(new InputStreamReader(so.getInputStream()));
			//System.out.println(br.readLine());
			
			br =new BufferedReader(new InputStreamReader(so.getInputStream()));
			//System.out.println(br.readLine());
  
			
			
		}
		
	}
			catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
		
			}
		}
	
	}