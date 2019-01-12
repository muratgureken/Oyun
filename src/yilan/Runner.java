package yilan;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Runner {

	public static void main(String[] args) throws InterruptedException, IOException{
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
		System.out.println("YILAN OYUNU");
		oyun.MatrisCiz("3");

		while(devam)
		{
			System.out.println("sol:1 sað:3 yukarý:5 aþaðý:2");
			String tus = scan.nextLine();
			//String tus="3";
			boolean kosul = (!tus.equals("1"))&&(!tus.equals("2"))&&(!tus.equals("3"))&&(!tus.equals("5"));
			if(kosul)
			{
				continue;
			}
			
			oyun.YilanBasiniHesapla(tus);
			if(!(oyun.YilaniYerlestir(tus)))
			{
				devam = false;
				System.out.println("Oyun bitti");				
			}
			/*TimeUnit.SECONDS.sleep(2);*/
		}
		scan.close();
	}

}
