package google_signin;

public class SingIn {

	public boolean signIn(String email,String password){
		if(!email.isEmpty()&&!password.isEmpty()) {
			if(email.indexOf("@gmail.com")!=-1) {
				System.out.println("Google ile giri� ba�ar�l�");
				return true;
			}
		}else {
			System.out.println("L�tfen kullan�c� ad� ve �ifrenizi giriniz");
			return false;
		}
		System.out.println("Ge�ersiz e-mail adresi");
		return false;
	}
}
