package mayintarlasi;

import java.util.Scanner;

public class Runner {

	public static void main(String[] args) {
		Scanner scan;
		scan = new Scanner(System.in);
		Kutu a = new Kutu();
		int secilenIndis, mayinSayisi,hamleSayisi, satirSayisi, sutunSayisi, flagSayisi,ind2;
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
		hamleSayisi = matrisBoyutu - mayinSayisi;
		flagSayisi = mayinSayisi;
		
		a.KutuIlklendir(mayinSayisi,satirSayisi,sutunSayisi);

		System.out.println();
		System.out.println();
		/*a.MayinlariGoster(a.MayinTarlasi, matrisBoyutu, sutunSayisi);
	System.out.println();
	System.out.println();*/

		while(Devam)
		{
			System.out.println("Oyundan cikmak icin E'ye basin");
			System.out.println("T/F/K secimi yapýn. (Tahmin/Flag/flag Kaldir). Ardindan T veya F yanindaki degeri girerek bir kutu secin.  ÖRNEK:F62");
			//System.out.println("Hamle Sayisi:"+hamleSayisi+"  Mayýn Sayýsý:"+mayinSayisi);
			String girdi;
			girdi = scan.next();
			if(girdi.contains("E"))
			{
				Devam=false;
				System.out.println("Gule gule :)");
				continue;
			}
			
			secilenIndis = Integer.parseInt(girdi.substring(1, girdi.length()));
			ind2 = secilenIndis;
			secilenIndis = secilenIndis-1;
			
			tahmin = girdi.substring(0,1);

			if((secilenIndis)>=matrisBoyutu)
			{
				System.out.println("Olmayan kutu seçtiniz :/");
				continue;
			}
			
			a.secilenIndis = secilenIndis;
			//System.out.println("secilen indis:"+a.secilenIndis);
			if(tahmin.contains("F")&&(flagSayisi>0))
			{
				a.Tarla[a.secilenIndis] = "F"+ind2;
				a.MatrisCiz(matrisBoyutu, sutunSayisi);
				a.MayinKontrol[a.secilenIndis] = 1;
				flagSayisi--;
				Devam = OyunDevam(a, matrisBoyutu, hamleSayisi, flagSayisi);
				continue;
			}

			if(tahmin.contains("K"))
			{
				if(a.Tarla[a.secilenIndis].contains("F"))
				{
					a.Tarla[a.secilenIndis] = "T"+ind2;
					a.MatrisCiz(matrisBoyutu, sutunSayisi);
					a.MayinKontrol[a.secilenIndis] = 0;
					flagSayisi++;
				}
				Devam = OyunDevam(a, matrisBoyutu, hamleSayisi, flagSayisi);
				continue;
			}
			
			if(a.MayinMi())
			{
				if(ilkTahmin)
				{
					flagSayisi--;
					a.Tarla[a.secilenIndis] = "F"+ind2;
					a.MayinKontrol[a.secilenIndis] = 1;
					a.MatrisCiz(matrisBoyutu, sutunSayisi);
				}
				else
				{
					a.MayinlariGoster(a.MayinTarlasi, matrisBoyutu, sutunSayisi);
					a.Tarla[a.secilenIndis] = "*BOM*";
					a.MatrisCiz(matrisBoyutu, sutunSayisi);
					a.MayinKontrol[a.secilenIndis] = 1;
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
			Devam = OyunDevam(a, matrisBoyutu, hamleSayisi, flagSayisi);
			ilkTahmin = false;
		}
		scan.close();
	}
	public static boolean OyunDevam(Kutu a, int matrisBoyutu, int hamleSayisi, int flagSayisi)
	{
		boolean oyunDevam=true;
		int count2=0;
		for(int i=0;i<matrisBoyutu;i++)
		{
			if((!a.Tarla[i].contains("T"))&&(!a.Tarla[i].contains("F")))
			{
				count2++;
			}
		}
		
		System.out.println("Flag Sayisi: "+flagSayisi+" Acilan Kutu: "+count2+" Toplam Hamle: "+hamleSayisi);

		if(count2==hamleSayisi)
		{
			System.out.println("Tebrikler, oyunu tamamladiniz :)");
			oyunDevam = false;			
		}
		
		return oyunDevam;
	}

}
