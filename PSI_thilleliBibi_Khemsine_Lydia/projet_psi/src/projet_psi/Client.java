package projet_psi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client extends Thread implements Serializable{
ArrayList<String> user = new ArrayList<String>();
private Socket so;
private PrintWriter pw;
private BufferedReader br;
//private int ID=0;
//String port;

public Client(){
//this.port=port;
};


private int clientId;
public void creat_Users() {
     user.add("veronique@gmail.com");
    
	 user.add("lydia@outlook.fr");
	
     user.add("lili@gmail.com");
 
     user.add("ab@outlook.fr");

   
     /*for(int i=0;i<Server.Users.size();i++) {
     System.out.println(Server.Users.get(i).getId());
     System.out.println(Server.Users.get(i).getIp());}

    */ 
   }
public void run(){
		try {
		Socket so = new Socket("localhost",1027);
		this.br =new BufferedReader(new InputStreamReader(so.getInputStream()));
		this.pw = new PrintWriter(new OutputStreamWriter(so.getOutputStream()));
		
		
		creat_Users();
		
		
		if (authenticate()) {
		Action();
	
		}}
		catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		}
public boolean Action() throws IOException{
	this.readMessages();
	System.out.print("enter choice: ");
	Scanner reader = new Scanner(System.in);
	String choice;
	
	do {
	choice = reader.nextLine();
	} while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4")
	&& !choice.equals("5") && !choice.equals("6")&& !choice.equals("7"));
	int i = Integer.parseInt(choice);
	//this.pw.write(choice + "\n");
	//pw.flush();
	switch (i)
	{  
	case 1:{
	this.GETADS();
	break;
	}
	case 3:{
	this.AddAd();//verifier la valeur de retour
	break;
	}
	case 2:{
	this.GETAD();
	break;
	}
	case 4:{
	this.UpdateAD();
	break;
	}
	case 5: {
	this.DeleteAd();
	break;
	}
	case 6: {
	this.SEND_MSG();
	break;
	}
	case 7:{
	this.logout();
	break;
	}
	
	default:  {
	System.out.println("command not found");
	break;
	}
	}
	return true ;
	}

public boolean authenticate() throws IOException{
      String choice;

      this.readMessages();
   
 Scanner reader = new Scanner(System.in);      
  do {
  choice = reader.nextLine();
  } while (!choice.equals("1") && !choice.equals("2"));
      // LogIn or SignUp
      if(choice.equals("1")){
    	  pw.write("LOGIN");
    	  pw.write("\r\n");
    	  pw.flush();
          if(!logIn() ) {
              System.out.println("LogIn failed");
              return false;
          }
      }
      else {
    	  pw.write("SIGNUP");
    	  pw.write("\r\n");
    	  pw.flush();
          if(!this.signUp()) {
              System.out.println("SignUp failed");
              return false;
          }
          else {
              System.out.println("SignUp succeeded");
          }
      }

      return true;
  }
public void readMessages(){  
       String message;

       // Print messages
       try{
           while((message = br.readLine()) != null){            
               if(message.length() == 0)
                   break;
               System.out.println(message);
           }
       } catch (IOException exception){
           exception.printStackTrace();
       }
   }
private boolean signUp() throws IOException{
       String commande = "SIGNUP";
       int response = 0;
       
       //Client.clearScreen();
       Scanner scanner = new Scanner(System.in);
       System.out.print("Please enter your first name: ");
       commande += "|" + scanner.nextLine();
       System.out.print("Please enter your last name: ");
       commande += "|" + scanner.nextLine();
       System.out.print("Please enter your birthday (yyyy-MM-dd): ");
       commande += "|" + scanner.nextLine();
       System.out.print("Please enter your email: ");
       commande += "|" + scanner.nextLine();
       System.out.print("Please enter your password: ");
       commande += "|" + scanner.nextLine();
       System.out.print("Please enter your address: ");
       commande += "|" + scanner.nextLine();
       System.out.print("Please enter your postal code: ");
       commande += "|" + scanner.nextLine();
       System.out.print("Please enter your city: ");
       commande += "|" + scanner.nextLine();
       System.out.print("Please enter your phone number (+xxx xxx xxx xxxx): ");
       commande += "|" + scanner.nextLine();
       ////////////////////////////
       String email[] = commande.split("\\|");
      // System.out.println(email[4]);
        Boolean existe = false;
       
       for (int i=0;i<user.size();i++) {
           // System.out.println(user.get(i));
           if (user.get(i).equals(email[4])) 
                  existe=true;
       }
          if(existe==true) {
          System.out.println("vous êtes déjà inscrit");
          this.logIn();
          }
       
        if (existe==false) {
        //pw.write(commande + "\n");
        //pw.write("\r\n");
        //pw.flush();
     
        String email1[] = commande.split("\\|");
        user.add(email1[4]);
     /*  for (int i=0;i<5;i++) {
       System.out.println(user.get(i));
     }*/
         
        }
       
       this.readMessages();
       // String d=br.readLine();

		String id =scanner.nextLine();
		//id_user=mess;
		pw.write(id+"\n");
		pw.flush();
		
		 String d=br.readLine();
		while(d.equals("0")){
			System.out.println("saisissez un autre identifiant !");
			id=scanner.nextLine();
			pw.write(id+"\n");
			pw.flush();
			d=br.readLine();
		}
		     System.out.println("entrez le numéro de port");
		     String port = scanner.nextLine();
		     pw.write(port+"\n");
				pw.flush();
		     
		         if ((commande.split("\\|")).length==10) {
		        return true;
		       }
		
		       return false;
		}

private boolean logIn() throws IOException{
       String commande = "LOGIN";
       //int response = 0;

      // Client.clearScreen();
       Scanner scanner = new Scanner(System.in);

       System.out.print("Please enter your email: ");
       commande += "|" + scanner.nextLine();
       System.out.print("Please enter your password: ");
       commande += "|" + scanner.nextLine();
     //  System.out.println(commande);
       String email2[] = commande.split("\\|");
       //System.out.println(email2[1]);
        Boolean existe = false;
       for (int i=0;i<user.size();i++) {
        //System.out.println(user.get(i));
         if (user.get(i).equals(email2[1])) {
             existe=true;
         /*if(existe==true) {
         pw.write(commande + "\n");
         System.out.println(user.get(i));
         pw.flush();  
         }*/
         
        }  
       }
       if (existe==false) {
         System.out.println("vous devez s'inscrire");
         this.signUp();
         
         }
       
       
         
         if (commande.split("\\|").length==3) {
          return true;
         }
         return false; 
       
   }
public boolean AddAd() {
//System.out.println("ADDAD");
String Request="ADDAD";
Scanner scanner = new Scanner(System.in);
   
System.out.print("Please the title of the Ad:");
       Request += "|" + scanner.nextLine();
       System.out.print("Please enter the description:");
       Request += "|" + scanner.nextLine();
       System.out.print("Please enter the price:");
       Request += "|" + scanner.nextLine();
       System.out.print("Please enter the id of the categorie:");
       Request += "|" + scanner.nextLine();
       
       this.pw.write(Request + "\n");
       pw.write("\r\n");
       pw.flush();
       scanner.close();
       
       this.readMessages();
       if ((Request.split("\\|")).length==5) {
        return true;
       }
     
       return false;

}
public void GETAD() throws IOException {
//rien a afficher ici on revoie juste la commande a threadaction
String Request="GETAD";
         Scanner scanner = new Scanner(System.in);
   
         //la soit on travaille avec les reference soit avec les titres
         //j'ai choisi de mettre titre pour l'instant et on verra apres
         //le titre de l'annonce que l'utilisateur veut voir
System.out.print("Please enter the title of the Ad do you want to get :");
    Request += "|" + scanner.nextLine();
       
this.pw.write(Request + "\n");
pw.write("\r\n");
    pw.flush();
       
    this.readMessages();
}
public void DeleteAd()
{
String Request="DELETEAD";
Scanner scanner = new Scanner(System.in);
System.out.print("Please enter the title of the Ad do you want to delete :");
    Request += "|" + scanner.nextLine();
this.pw.write(Request + "\n");
pw.write("\r\n");
    pw.flush();
   
   this.readMessages();
}
public void UpdateAD() {
String Request="UPDATEAD";
Scanner scanner = new Scanner(System.in);
String rep;
String reponse="";
System.out.print("Please enter the title of the Ad do you want to update :");
    String titre = scanner.nextLine();
    Request += "|" + titre;
   
    System.out.println("do you want to modify the title ? please enter \"y\" for yes or \"n\" for no ");
    rep = scanner.nextLine();
   
    if (rep.equals("y") ||rep.equals("Y"))
    {
    System.out.println("please enter the new tiltle ");
    rep = scanner.nextLine();
    Request=  Request + "|"+"t" + "|" + rep;  //t pour titre
    }
   
    System.out.println("do you want to modify the description? please enter \"y\" for yes or \"n\" for no ");
    rep = scanner.nextLine();
   
    if (rep.equals("y") ||rep.equals("Y"))
    {
    System.out.println("please enter the new description ");
    rep = scanner.nextLine();
    Request=  Request + "|" + "d" +"|"+ rep;  //d pour description
    }
   
    System.out.println("do you want to modify the price? please enter \"y\" for yes or \"n\" for no ");
    rep = scanner.nextLine();
   
    if (rep.equals("y") ||rep.equals("Y"))
    {
    System.out.println("please enter the new price ");
    rep = scanner.nextLine();
    Request=  Request + "|" + "p" + "|"+rep;  //d pour description
    }
   
    System.out.println("do you want to modify the category? please enter \"y\" for yes or \"n\" for no ");
    rep = scanner.nextLine();
   
    if (rep.equals("y") ||rep.equals("Y"))
    {
    System.out.println("please enter the new category ");
    rep = scanner.nextLine();
    Request=  Request + "|" + "c" + "|" + rep;  //d pour description
    }
   
this.pw.write(Request + "\n");
pw.write("\r\n");
    pw.flush();
   
    this.readMessages();
   
}
public void GETADS() {
String Request="GETADS";
Scanner scanner = new Scanner(System.in);
this.pw.write(Request + "\n");
pw.write("\r\n");
    pw.flush();
      this.readMessages();
}
public void SEND_MSG() throws IOException {
	String port;
	String ip;
	
		String Request="SEND_MSG";
		Scanner scanner = new Scanner(System.in);
		System.out.print("entrez l'identifiant de l'utilisateur (id)");
		Request += "|" + scanner.nextLine();
		pw.write(Request);
		pw.write("\r\n");
		pw.flush();
		//System.out.println(Request);
		//String a = br.readLine();
		//System.out.println(a);
		
		if(br.readLine().equals("1")){
			//System.out.println("dans if 1");
				 ip=br.readLine();
				 port=br.readLine();  
				 System.out.println(ip);
				 System.out.println(port);
				System.out.println("entrez votre message !");
				String mess=scanner.nextLine();
				System.out.println(mess);
				Socket Socket_client=new Socket(ip,Integer.parseInt(port));
				PrintWriter pwc=new PrintWriter(new OutputStreamWriter(Socket_client.getOutputStream()));

				pwc.print(mess+"\n");
				pwc.flush();
				pwc.close();
				Socket_client.close();
			 
			}
			
		else{
				System.out.println("cet utilisateur n'existe pas ");
			}
		}
public void logout() {
String Request="LOGOUT";
pw.write(Request);
pw.write("\r\n");
pw.flush();
this.readMessages();
//this.authenticate();
System.exit(0);
}


public static void main(String[] args) throws IOException{
ServerSocket servs=new ServerSocket(0);
//String port=""+servs.getLocalPort();System.out.println("local port"+port);

Client c= new Client() ; //(port);
c.start();
while (true) {
Socket so=servs.accept();
Communication com=new Communication(so);
com.start();
servs.close();}

}
}