package TuglaKiran;

import java.util.Scanner;

public class Runner {

	public static void main(String[] args) throws InterruptedException {
		int oyunBoyu=10, hamleSayisi=6;
		boolean oyunDevam=true;
		String hamle;
		Scanner scan;
		scan = new Scanner(System.in);
		
		Tugla oyun = new Tugla(oyunBoyu, hamleSayisi);

		System.out.println("TUGLA KIRMA OYUNU");
		while(oyunDevam)
		{
			System.out.println("Hamleler--> a:ates 1:sol 3:sag, MAKS: "+ hamleSayisi +" hamle hakkiniz var. Ornek HAMLE: 11a2: iki sola git, ates et saga git");
			hamle = scan.nextLine();
			oyun.HamleyiOku(hamle);
			oyun.HamleYap();
			if(oyun.OyunBitti())
			{
				oyunDevam= false;
				System.out.println("Oyun bitti");
			}
		}
	}

}
