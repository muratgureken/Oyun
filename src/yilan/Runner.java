package yilan;

import java.util.LinkedList;
import java.util.Scanner;

public class Runner {

	public static void main(String[] args){
		boolean devam=true;
		Scanner scan;
		scan = new Scanner(System.in);
		
		/*String tus = scan.nextLine();
		System.out.println(tus);*/
		
		Yilan oyun = new Yilan();
		oyun.oyunBoyu = 10;
		oyun.ilkDeger=1*oyun.oyunBoyu+1;
		oyun.yilan = new LinkedList<Integer>();
		oyun.yilanMatrisi = new int[oyun.oyunBoyu*oyun.oyunBoyu];

		oyun.YilanIlklendir(oyun.ilkDeger, 4);
		//rastgele yerde yem uretimi
		oyun.RastgeleSayiUret();
		System.out.println("YILAN  @@@@@@@@>");
		oyun.MatrisCiz("3");
		
		while(devam)
		{
			System.out.println("sol:1 sað:3 yukarý:5 aþaðý:2");
			String tus = scan.nextLine();
			
			oyun.YilanBasiniHesapla(tus);
			if(!(oyun.YilaniYerlestir(tus)))
			{
				devam = false;
				System.out.println("Oyun bitti");				
			}
		}
		
		scan.close();
	}

}
