package Whale_dec;

import java.util.Hashtable;


public class TestDB {
		public static Hashtable<String,String> table = new Hashtable<String,String>();
		public String addEmailRecord(String email, String password) {	
			String pwd = table.get(email);//Retrieve a record using id as key
											//see if the record exists in the hash table
			if(pwd==null){					//the record does not exists in hash table
				table.put(email, password);//add the record to the hash table
				return "1";					//tell the web page added record sucessfully
			}else{
				return "0";					//tell the web page the record already exists
			}

		}
		
		public String verifyLogIn(String email, String password){
			String pwd = table.get(email);
			if((pwd!=null) && (pwd.equals(password))){
				return "1";
			}else{
				return "0";
			}
		}
}
