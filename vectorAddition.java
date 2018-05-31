import java.util.Scanner;

public class vectorAddition {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		System.out.println("Enter Am");
		String Ams = input.nextLine();
		float Am = Float.parseFloat(Ams);
		System.out.println("Enter Ad");
		String Ads = input.nextLine();
		float Ad = Float.parseFloat(Ads);
		System.out.println("Enter Bm");
		String Bms = input.nextLine();
		float Bm = Float.parseFloat(Bms);
		System.out.println("Enter Bd");
		String Bds = input.nextLine();
		float Bd = Float.parseFloat(Bds);
		/*System.out.println("Enter Ax");
		String Axs = input.nextLine();
		float Ax = Float.parseFloat(Axs); 
		System.out.println("Enter Ay");
		String Ays = input.nextLine();
		float Ay = Float.parseFloat(Ays); 
		System.out.println("Enter Bx");
		String Bxs = input.nextLine();
		float Bx = Float.parseFloat(Bxs); 
		System.out.println("Enter By");
		String Bys = input.nextLine();
		float By = Float.parseFloat(Bys);
		*/
		float Ar = (float) Math.toRadians(Ad);
		float Br = (float) Math.toRadians(Bd);
		float Ax = (float) (Am * Math.cos(Ar));
		float Ay = (float) (Am * Math.sin(Ar));
		float Bx = (float) (Bm * Math.cos(Br));
		float By = (float) (Bm * Math.sin(Br));
		float Rx = Ax + Bx;
		float Ry = Ay + By;
		float Rm = (float) Math.sqrt(Math.pow(Rx, 2) + Math.pow(Ry, 2));
		//double Ryr = Math.toRadians(Ry);
		//double Rxr = Math.toRadians(Rx);
		double Rr = Math.atan(Ry/Rx);
		float Rd = (float) Math.toDegrees(Rr);
		if (Rx < 0){
			Rd = Rd + 180;
		}
		System.out.println("Rx is: "+Rx);
		System.out.println("Ry is: "+Ry);
		System.out.println("Magnitude of R is :" + Rm);
		System.out.println("Direction of R is :" + Rd + "°");
		
		
		
		
	}

}
