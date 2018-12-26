package KelimeBul;

import java.util.Scanner;

public class KelimeBul {
	public KelimeBul(String kelime) {
		int kelimeBoyutu, ilkBoyut=6, ilkHarfHakki=3, deger, countTahmin=0;
		float kalanBoyut;
		float b=3.0f, d=0;
		String [] Tahmin;
		Scanner scan = new Scanner(System.in);
		String [] kelimeBul;
		String tahminEt, sonuc="YANLIÞ",harfler;
		
		kelimeBoyutu = kelime.length();
		kelimeBul = new String[kelimeBoyutu];
		Tahmin = new String[kelimeBoyutu];
		
		for(int i=0;i<kelimeBoyutu;i++)
		{
			kelimeBul[i] = kelime.substring(i, i+1);
			Tahmin[i] = "X";
		}
		
		deger = ilkHarfHakki;
		
		if(kelimeBoyutu>ilkBoyut)
		{
			kalanBoyut = (float)(kelimeBoyutu - ilkBoyut);
			d = kalanBoyut/b;
			deger = ilkHarfHakki+(int)Math.ceil(d);
		}
				
		System.out.println("KELÝME BUL OYUNU");
		for(int i=0;i<kelimeBoyutu;i++)
		{
			System.out.print(Tahmin[i]);
		}
		System.out.println();

		System.out.println("kelime: "+kelime);

		System.out.println(kelimeBoyutu+" harfli kelime için "+deger+" harf seçin");
		
		for(int i=0;i<deger;i++)
		{
			harfler = scan.next();

			for(int j=0;j<kelimeBoyutu;j++)
			{
				if(harfler.contentEquals(kelimeBul[j]))
				{
					Tahmin[j] = harfler;
				}
			}
		}
		
		for(int i=0;i<kelimeBoyutu;i++)
		{
			System.out.print(Tahmin[i]);
		}
		System.out.println();

		System.out.println("3 tahmin hakkýnýz var.");
		
		while(countTahmin<3)
		{
			countTahmin++;
			tahminEt = scan.next();
			if(kelime.contentEquals(tahminEt))
			{
				sonuc = "DOÐRU";
				break;
			}
		}
		
		System.out.println("CEVABINIZ "+sonuc+"! KELÝME="+kelime);
		
		scan.close();
	}
}
