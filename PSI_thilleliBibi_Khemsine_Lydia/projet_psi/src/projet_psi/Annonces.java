package projet_psi;

import java.net.*;
import java.io.*;

public class Annonces implements Serializable{



	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	Socket so=null;
	String description;
	String titre;
	String categorie=null;
	double prix=0.0;
	int ref=0;
	String user=null;



	public Annonces(){};

	public Annonces(Socket s,String titre,String desc,double pr,String dom){
		so=s;
		this.titre =titre;
		description=desc;
		categorie=dom;
		prix=pr;
	}

	public void setPrix(double pr){
		this.prix=pr;
	}
	public void setSocket(Socket so){
		this.so=so;
	}

	public void setDescription(String desc){
		this.description=desc;

	}
	public void SetTitre(String t){
		this.titre=t;

	}
	public void setDomaine(String dom){
		this.categorie=dom;
	}
	public void setRef(int ref){
		this.ref=ref;
	}
	public String GetDomaine(){
		return this.categorie;
	}
	public String GetDescription(){
		return this.description;
	}
	public double GetPrix(){
		return this.prix;
	}
	public String GetTitre(){
		return this.titre;
	}

}
