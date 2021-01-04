package projet_psi;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.ArrayList;

public class ThreadAction extends Thread implements Serializable{
private Socket so;
private PrintWriter pw;
private BufferedReader br;
private ObjectInputStream obj_in;
private ObjectOutputStream obj_out;
private int clientId;

	public boolean id_found(String id){
		for (int i=0;i<Server.Users.size() ;i++ ) {
		if(id.equals(Server.Users.get(i).getId()))
			return true;			
		}
			return false;
		}

public ThreadAction(Socket so){
	try{
	this.so=so;
	this.br=new BufferedReader(new InputStreamReader(so.getInputStream()));
	this.pw=new PrintWriter(new OutputStreamWriter(so.getOutputStream()));
	
	}catch(Exception e){
	System.out.println(e);
	e.printStackTrace();
	
	}
	}
public void run(){
		User us=new User();		
		String id="";
		
		User e = new User();
	     e.setId("0");
	     e.setIp(so.getInetAddress().toString().replace("/",""));
	     e.setPort("2001");
	     Server.Users.add(e);
	    
	 	User e1 = new User();
	    e1.setId("1");
	     e1.setIp(so.getInetAddress().toString().replace("/",""));
	     e1.setPort("42881");
	     Server.Users.add(e1);
	     
	 	User e2 = new User();
	     e2.setId("2");
	     e2.setIp(so.getInetAddress().toString().replace("/",""));
	     e2.setPort("2003");
	     Server.Users.add(e2);
	     
	 	User e3 = new User();
	     e3.setId("3");
	     e3.setIp(so.getInetAddress().toString().replace("/",""));
	     e3.setPort("2004");
	     Server.Users.add(e3);
	    
	     String rep = authenticateClient();
	     System.out.println(rep);
  
	     if (rep.equals("SIGNUP")) {
			    try {
				pw.write("veuillez saisir le nom d'utilisateur\n");
				pw.write("\r\n");
				pw.flush();
				
				id = br.readLine();
				 boolean b = false;
				 
				while(id_found(id)){	
					//System.out.println("while server");
					b=true;
					pw.write("0\n");
					pw.flush();
					id=br.readLine();
				}
				
				if(!b) {
				pw.print("1\n");
				pw.flush();
				
				}
				String port=br.readLine();
				us.setPort(port);
				us.setId(id);
				us.setIp(so.getInetAddress().toString().replace("/",""));
				Server.Users.add(us);
				} catch (IOException ex) {			
				}}
	     try {  
			
			ActionThread();
		} catch (IOException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
				
				}
private String authenticateClient(){
    // Variables
  //  int id=1;
	 String a="";
    try {
        // Send SignUp and LogIn menu
        pw.write("Hello there, What do you want to do ?\n");
        pw.write("1. Log in\n");
        pw.write("2. Sign up\n");
        pw.write("\r\n");
        pw.flush();
        
       a = br.readLine();

      
    } catch (Exception exception) {
        exception.printStackTrace();
    }
    return a ;
}

public boolean ActionThread() throws IOException
{  

	String [] commandes = {"GETADS","GETAD","ADDAD","UPDATEAD","DELETEAD","SEND_MSG","LOGOUT"};//,"GETCLIENTINFO","UPDATECLIENT","DELETECLIENT","LOGOUT"};
	for (int i =1; i <= 7 ; i++) {
	pw.write(i + ":" +  commandes[i-1] + '\n');
	
	}
	pw.write("\r\n");
	pw.flush();
	
	br =new BufferedReader(new InputStreamReader(so.getInputStream()));
	//String rep =br.readLine();
	//System.out.println(rep);
	String request =br.readLine();  
	String Request[] = request.split("\\|");
	System.out.println("requesrt" + request);
	// System.out.println(Request.length);
	switch (Request[0])
			{
			case "ADDAD":
			{
			//System.out.println("Hello");
			Server.annonces.add(new Annonces(so,Request[1],Request[2],Double.valueOf(Request[3]),Request[4]));
			pw.write("succesful!");
			pw.write("\r\n");
			pw.flush();
			break;
			}
			case "GETAD":{
			boolean b=false;
			for (int i = 0; i < Server.annonces.size(); i++) {
			    if (Request[1].equals(Server.annonces.get(i).GetTitre())) {//le titre que l'utilisateur a choisi
			        pw.write("Description : " + Server.annonces.get(i).GetDescription() + "\n");
			        pw.write("Categorie : " + Server.annonces.get(i).GetDomaine()+ "\n");
			        pw.write("Prix : " + String.valueOf(Server.annonces.get(i).GetPrix()));
			        pw.write("\r\n");
			        pw.flush();
			        b=true;
			        break;
			    }
			   }
			if (b==false) {
			pw.write("no annone was found with this title!");
			pw.write("\r\n");
			        pw.flush();
			}
			break;
			}
			case "DELETEAD":{
			for (int i = 0; i < Server.annonces.size(); i++) {
			    if (Request[1].equals(Server.annonces.get(i).GetTitre())) {
			    Server.annonces.remove(i);
			    pw.write( "delete successful");
			    pw.write("\r\n");
			       pw.flush();
			    }
			    break;
			    }
			}
			case "UPDATEAD":{
			if (Request.length>2)  //l'utilisateur a modife au moins un champ
			{ for (int i = 0; i < Server.annonces.size(); i++) {
			    if (Request[1].equals(Server.annonces.get(i).GetTitre())) {//le titre que l'utilisateur a choisi
			    for(int j=2;j<Request.length;j++) {
			   
			    if (Request[j].equals("t"))
			    Server.annonces.get(i).SetTitre(Request[j+1]) ;
			    if (Request[j].equals("d"))
			    Server.annonces.get(i).setDescription(Request[j+1]) ;
			    if (Request[j].equals("p"))
			    Server.annonces.get(i).setPrix(Double.valueOf(Request[j+1])) ;
			    if (Request[j].equals("c"))
			    Server.annonces.get(i).setDomaine(Request[j+1]) ;
			           j++; //les lettre correspondantes aux champs modifies se trouve aux positions j,j+2,j+4...
			    }
			    pw.write( "update successful");
			    pw.write("\r\n");
			       pw.flush();
			    break;
			    }    
			   }
			}
			break;
			}
			case "GETADS":{
			for (int i = 0; i < Server.annonces.size(); i++) {
			  pw.write("Titre : " + Server.annonces.get(i).GetTitre() + "\n");
			  pw.write("Description : " + Server.annonces.get(i).GetDescription() + "\n");
			      pw.write("Categorie : " + Server.annonces.get(i).GetDomaine()+ "\n");
			      pw.write("Prix : " + String.valueOf("Prix" + Server.annonces.get(i).GetPrix()));
			      pw.write("\r\n");
			      pw.flush();
			   
			}
			break;
			}
			
			case "SEND_MSG" : {
				//String rep1=br.readLine();
			//	String id[] = rep1.split("\\|");
				 // System.out.println("rep1" + Request[1]);
			System.out.println("send msg server");
				
				
				boolean exist=false;
				for (int i=0; i< Server.Users.size();i++ ) {
					if(Request[1].equals(Server.Users.get(i).getId())){
						exist=true;
						break;
					}
				}
				if(exist)
					pw.write("1\n");
				else
					pw.write("0\n");
			//	pw.write("\r\n");
				pw.flush();
				
				if(exist){
				for (int i=0;i<Server.Users.size() ;i++ ) {
					if((Request[1].equals(Server.Users.get(i).getId()))){
						pw.print(Server.Users.get(i).getIp().replace("/","")+"\n");System.out.println(Server.Users.get(i).getIp().replace("/",""));
						pw.flush();
						pw.print(Server.Users.get(i).getPort()+"\n");
						pw.flush();
						System.out.println("destinat trouvé");

					}
				}
				}
			}
			case "LOGOUT" : {
		    pw.write("vous vous etes deconnnecté! au revoir");
			pw.write("\r\n");
				
			}
			}
			       pw.flush();
			return true ;
			}
			
			}