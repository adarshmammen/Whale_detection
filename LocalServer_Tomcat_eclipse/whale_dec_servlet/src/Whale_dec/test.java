package Whale_dec;

public class test {
	public static void main(String arg[]){
		TestDB db = new TestDB();
		String a = db.addEmailRecord("username", "password");
		System.out.printf(a);
		System.out.println(a.equals(new String("1")));
		String b = db.addEmailRecord("username", "password");
		System.out.printf(b);
		System.out.println(db.addEmailRecord("username", "password").equals("1"));
	}
}
