package TuglaKiran;

import java.util.Scanner;

public class Runner {

	public static void main(String[] args) throws InterruptedException {
		int oyunBoyu=10, hamleSayisi=6, count=0;
		boolean oyunDevam=true;
		String hamle;
		Scanner scan;
		scan = new Scanner(System.in);
		
		Tugla oyun = new Tugla(oyunBoyu);

		System.out.println("TUGLA KIRMA OYUNU");
		while(oyunDevam)
		{
			System.out.println("HAMLELER --> a:ates 1:sol 3:sag, "+ (hamleSayisi-count) +" HAMLE sonra tuglalar bir asagi inecektir.");
			hamle = scan.nextLine();
                        hamle = hamle.substring(0,1);
			oyun.HamleyiOku(hamle);
			oyun.HamleYap();
                        count++;
                        
                        
                        if(count==hamleSayisi)
                        {
                            count = 0;
                            oyun.TuglalariAsagiIndir();
                            if(oyun.OyunBitti())
                            {
				oyunDevam= false;
				System.out.println("OYUN BITTI :(");
                            }   
                        }
		}
	}

}
