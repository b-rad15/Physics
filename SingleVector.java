import java.util.Scanner;

public class SingleVector {
	public static void main(String Args[]){
		do{
			Scanner input = new Scanner(System.in);
			System.out.println("Enter the magnitude");
			String Ams = input.nextLine();
			float Am = Float.parseFloat(Ams);
			System.out.println("Enter the direction in degrees");
			String Ads = input.nextLine();
			//if(Ams != "" || Ads != ""){
			//	System.out.println("You didnt enter one of the variables");
			//	break;
			//}
			float Ad = Float.parseFloat(Ads);
			float Ar = (float) Math.toRadians(Ad);
			float Ax = (float) (Am * Math.cos(Ar));
			float Ay = (float) (Am * Math.sin(Ar));
			System.out.println("i-hat of the vector is: " + Ax);
			System.out.println("j-hat of the vector is: " + Ay);
			
			} while(true);
		}
}