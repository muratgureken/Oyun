package yilan;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Runner {

	public static void main(String[] args) throws InterruptedException, IOException{
		Scanner scan;
		String tus="3", oncekiTus;
		scan = new Scanner(System.in);
		boolean kosul;
		
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
		System.out.println("YILAN OYUNU");
		oyun.MatrisCiz("3");
		oyun.start();
		System.out.println("sol:1 sað:3 yukarý:5 aþaðý:2");
		
		while(oyun.oyunDevam)
		{
			oncekiTus = oyun.tus;
			tus = scan.nextLine();
			//String tus="3";
			boolean kosul1 = (!tus.equals("1"))&&(!tus.equals("2"))&&(!tus.equals("3"))&&(!tus.equals("5"));
			//ters yone hareket engelleniyor.
			boolean kosul2 = ((tus.equals("1"))&&(oncekiTus.equals("3")));
			boolean kosul3 = ((tus.equals("3"))&&(oncekiTus.equals("1")));
			boolean kosul4 = ((tus.equals("2"))&&(oncekiTus.equals("5")));
			boolean kosul5 = ((tus.equals("5"))&&(oncekiTus.equals("2")));
			//System.out.println("tus:"+tus+" onceki tus:"+oncekiTus);
			
			kosul = kosul1||kosul2||kosul3||kosul4||kosul5;
			
			if(kosul)
			{
				//System.out.println("hatali giris");
				continue;
			}
			else
			{
				oyun.tus = tus;
			}
		}
		System.out.println("Oyun bitti");				
		scan.close();
	}

}
