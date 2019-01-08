package yilan;

import java.util.Scanner;

public class Runner {

	public static void main(String[] args){
		boolean devam=true;
		Scanner scan;
		scan = new Scanner(System.in);
		
		/*String tus = scan.nextLine();
		System.out.println(tus);*/
		
		Yilan oyun = new Yilan();
		oyun.yilanBoyu = 4;
		oyun.oyunBoyu = 10;
		oyun.ilkDeger=1*oyun.oyunBoyu+1;
		oyun.yilan = new int[oyun.oyunBoyu*oyun.oyunBoyu];
		oyun.yilanMatrisi = new int[oyun.oyunBoyu*oyun.oyunBoyu];

		oyun.yilaninBasi = oyun.YilanIlklendir(oyun.ilkDeger, oyun.yilanBoyu);
		/*System.out.println("yilan basi:"+oyun.yilaninBasi);*/
		oyun.MatrisCiz("3");
		/*System.out.println(oyun.YilanBasiniHesapla(99, "1"));*/
		//rastgele yerde yem uretimi
		oyun.RastgeleSayiUret(oyun.oyunBoyu, oyun.yilanBoyu);
		
		while(devam)
		{
			String tus = scan.nextLine();
			
			/*oyun bitirme kriteri*/
			if(!oyun.OyunDevamKontrol())
			{
				devam = false;
				System.out.println("Oyun bitti");
			}
		}
		
		scan.close();
	}

}
