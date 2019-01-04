package mayintarlasi;

import java.util.Scanner;

public class Runner {

	public static void main(String[] args) {
		Scanner scan;
		scan = new Scanner(System.in);
		Kutu a = new Kutu();
		int secilenIndis, mayinSayisi,hamleSayisi=0, satirSayisi, sutunSayisi;
		boolean Devam=true, ilkTahmin=true;
		String tahmin;
		
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
			System.out.println("T yanindaki degeri girerek bir kutu secin. Ardindan T/F secimi yapýn. (Tahmin/Flag) ÖRNEK:61F");
			//System.out.println("Hamle Sayisi:"+hamleSayisi+"  Mayýn Sayýsý:"+mayinSayisi);
			String girdi;
			girdi = scan.next();
			if(girdi.contains("E"))
			{
				Devam=false;
				System.out.println("Gule gule :)");
				continue;
			}
			
			secilenIndis = Integer.parseInt(girdi.substring(0, girdi.length()-1));
			secilenIndis = secilenIndis-1;
			
			tahmin = girdi.substring(girdi.length()-1,girdi.length());

			if((secilenIndis)>=matrisBoyutu)
			{
				System.out.println("Olmayan kutu seçtiniz :/");
				continue;
			}
			
			a.secilenIndis = secilenIndis;
			//System.out.println("secilen indis:"+a.secilenIndis);
			if(a.MayinMi()&&(tahmin.contains("F")))
			{
				a.Tarla[a.secilenIndis] = "FLAG";
				a.MatrisCiz(matrisBoyutu, sutunSayisi);
				a.MayinKontrol[a.secilenIndis] = 1;
				mayinSayisi--;
				
				int count2=0;
				for(int i=0;i<matrisBoyutu;i++)
				{
					if(!a.Tarla[i].contains("T"))
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
			if(!(a.MayinMi())&&(tahmin.contains("F")))
			{
				continue;
			}

			if(a.MayinMi())
			{
				mayinSayisi--;
				a.Tarla[a.secilenIndis] = "BOM";
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
				if(!a.Tarla[i].contains("T"))
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
