package projet_psi;

public class User {

		private String id;
		private String ip;
		private String port;


		public User(){}	

	public String getId(){
		return this.id;
	}

	public String getIp(){
		return this.ip;
	}

	public String getPort(){
	return this.port;
	}


	public void setIp(String ip){
		this.ip=ip;
	}
	public void setId(String id){
		this.id=id;
	}

	public void setPort(String port){
		this.port=port;
	}

	
	}

