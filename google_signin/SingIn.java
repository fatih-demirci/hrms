package google_signin;

public class SingIn {

	public boolean signIn(String email,String password){
		if(!email.isEmpty()&&!password.isEmpty()) {
			if(email.indexOf("@gmail.com")!=-1) {
				System.out.println("Google ile giriþ baþarýlý");
				return true;
			}
		}else {
			System.out.println("Lütfen kullanýcý adý ve þifrenizi giriniz");
			return false;
		}
		System.out.println("Geçersiz e-mail adresi");
		return false;
	}
}
