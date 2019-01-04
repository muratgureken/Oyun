package mayintarlasi;

import java.util.Scanner;

public class Runner {

	public static void main(String[] args) {
		Scanner scan;
		scan = new Scanner(System.in);
		Kutu a = new Kutu();
		int satir,sutun,tahmin,mayinSayisi,hamleSayisi=0, satirSayisi, sutunSayisi;
		boolean Devam=true, ilkTahmin=true;

		System.out.println("Zorluk seviyesini seçin: 1:kolay 2:orta 3:zor");
		int girdi2 = scan.nextInt();
		switch(girdi2)
		{
		case 1:
			satirSayisi = 9;
			sutunSayisi = 9;
			mayinSayisi = 10;
			break;
		case 2:
			satirSayisi = 16;
			sutunSayisi = 16;
			mayinSayisi = 40;
			break;

		default:
			satirSayisi = 16;
			sutunSayisi = 30;
			mayinSayisi = 99;
			break;
		}

		int matrisBoyutu = satirSayisi*sutunSayisi;

		a.KutuIlklendir(mayinSayisi,satirSayisi,sutunSayisi);

		System.out.println();
		System.out.println();
		/*a.MayinlariGoster(a.MayinTarlasi, matrisBoyutu, sutunSayisi);
	System.out.println();
	System.out.println();*/

		while(Devam)
		{
			hamleSayisi++;
			System.out.println("Oyundan cikmak icin E'ye basin");
			System.out.println("satir sutun secim/tahmin(0/1)");
			//System.out.println("Hamle Sayisi:"+hamleSayisi+"  Mayýn Sayýsý:"+mayinSayisi);
			String girdi;
			girdi = scan.next();
			if(girdi.contains("E"))
			{
				Devam=false;
				System.out.println("Gule gule :)");
				continue;
			}
			satir = Integer.parseInt(girdi);
			girdi = scan.next();
			if(girdi.contains("E"))
			{
				Devam=false;
				System.out.println("Gule gule :)");
				continue;
			}
			sutun = Integer.parseInt(girdi);
			
			girdi = scan.next();
			if(girdi.contains("E"))
			{
				Devam=false;
				System.out.println("Gule gule :)");
				continue;
			}
			
			if((satir*sutun)>=matrisBoyutu)
			{
				System.out.println("Olmayan kutu seçtiniz :/");
				continue;
			}
			
			tahmin = Integer.parseInt(girdi);		

			a.secilenIndis = satir*sutunSayisi+sutun;
			//System.out.println("secilen indis:"+a.secilenIndis);
			if(a.MayinMi()&&(tahmin==1))
			{
				a.Tarla[a.secilenIndis] = "F";
				a.MatrisCiz(matrisBoyutu, sutunSayisi);
				a.MayinKontrol[a.secilenIndis] = 1;
				mayinSayisi--;
				
				int count2=0;
				for(int i=0;i<matrisBoyutu;i++)
				{
					if(!a.Tarla[i].contains("x"))
					{
						count2++;
					}
				}

				System.out.println("Açýlan Kutu:"+count2+"  Toplam:"+matrisBoyutu+
						"  Hamle Sayýsý:"+hamleSayisi+"  Mayýn Sayýsý:"+mayinSayisi);
				if(count2==matrisBoyutu)
				{
					System.out.println("Tebrikler, oyunu tamamladiniz :)");
					Devam = false;			
				}
				
				continue;
			}
			if(!(a.MayinMi())&&(tahmin==1))
			{
				continue;
			}

			if(a.MayinMi())
			{
				mayinSayisi--;
				a.Tarla[a.secilenIndis] = "M";
				a.MatrisCiz(matrisBoyutu, sutunSayisi);
				a.MayinKontrol[a.secilenIndis] = 1;
				if(!ilkTahmin)
				{
					Devam = false;
					System.out.println("Mayin sectiniz, oyun bitti :(");
					continue;
				}
			}
			else
			{
				a.KomsulariBul(satirSayisi, sutunSayisi);
				/*System.out.println("Komsular");
				for(int i=0;i<a.komsuSayisi;i++)
				{
					System.out.print(a.Komsular[i]+" ");
				}*/
				System.out.println();
				a.NoktaDegeri(a.MayinTarlasi);
				a.TarlayiDoldur(a, a.Tarla, a.MayinTarlasi, a.MayinKontrol, satirSayisi, sutunSayisi, 0);
				a.MatrisCiz(matrisBoyutu, sutunSayisi);
			}
			/*System.out.println();
		System.out.println();
		a.MayinlariGoster(a.MayinTarlasi, matrisBoyutu, sutunSayisi);*/
			int count2=0;
			for(int i=0;i<matrisBoyutu;i++)
			{
				if(!a.Tarla[i].contains("x"))
				{
					count2++;
				}
			}

			System.out.println("Açýlan Kutu:"+count2+"  Toplam:"+matrisBoyutu+
					"  Hamle Sayýsý:"+hamleSayisi+"  Mayýn Sayýsý:"+mayinSayisi);
			/*if((count2>=(matrisBoyutu-mayinSayisi))&&(mayinSayisi!=0))
			{
				System.out.println("Tebrikler, mayinsiz tum kutulari actiniz :)");
				Devam = false;
			}
			if(mayinSayisi==0)
			{
				System.out.println("Tebrikler, tüm mayýnlarý buldunuz :)");
				Devam = false;
			}*/
			if(count2==matrisBoyutu)
			{
				System.out.println("Tebrikler, oyunu tamamladiniz :)");
				Devam = false;			
			}
			ilkTahmin = false;
		}
	}

}
