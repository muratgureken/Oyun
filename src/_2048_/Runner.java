package _2048_;

import java.util.Scanner;

public class Runner {

	public static void main(String[] args) {
		int boy=1;
		boolean durum=true, oyunDevam=true;
		String devamSecimi;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("   2048 OYUNU   ");
		while(durum)
		{
			System.out.println("Oyun Boyutunu secin: 1 (4x4) veya 2 (8x8)");
			boy = scan.nextInt();
			if(boy==1)
			{
				boy = 4;
				durum = false;
			}
			else if(boy==2)
			{
				boy = 8;
				durum = false;
			}
			else
			{
				System.out.println("Gecersiz bir boyut sectiniz !...");
			}
		}
		
		System.out.println("   2048 OYUNU   ");
		_2048_ yeniOyun = new _2048_(boy);
		
		while(oyunDevam)
		{
			durum = true;
			
			System.out.println("Bir yon secin (1:sol, 2:asagi, 3:sag, 5 yukari");;
			while(durum)
			{
				yeniOyun.yon = scan.next();
				if((yeniOyun.yon.equals("1"))||(yeniOyun.yon.equals("2"))||(yeniOyun.yon.equals("3"))||(yeniOyun.yon.equals("5")))
				{
					durum = false;
				}
				else
				{
					System.out.println("Gecersiz bir yon bilgisi girdiniz !...");
				}
			}
				
			if(yeniOyun.hamleVarmi())
			{
				System.out.println("   2048 OYUNU   ");
				if(yeniOyun.oyna())
				{
					System.out.println("Tebrikler, kazandiniz !...");
					durum = true;
					while(durum)
					{
						System.out.println("Tamam ? Devam ? (T/D");
						devamSecimi = scan.next();
						if(devamSecimi.equals("T"))
						{
							durum = true;
							oyunDevam = false;
						}
						else if(devamSecimi.equals("D"))
						{
							durum = false;
						}
						else
						{
							System.out.println("Yanlis tercih yaptiniz !...");
							durum = true;
						}
					}
				}
			}
			else
			{
				System.out.println("Oyun bitti !...");
				oyunDevam = false;
			}
		}
		scan.close();
	}

}
