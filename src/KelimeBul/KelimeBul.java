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
		String tahminEt, sonuc="YANLI�",harfler;
		
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
				
		System.out.println("KEL�ME BUL OYUNU");
		for(int i=0;i<kelimeBoyutu;i++)
		{
			System.out.print(Tahmin[i]);
		}
		System.out.println();

		System.out.println("kelime: "+kelime);

		System.out.println(kelimeBoyutu+" harfli kelime i�in "+deger+" harf se�in");
		
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

		System.out.println("3 tahmin hakk�n�z var.");
		
		while(countTahmin<3)
		{
			countTahmin++;
			tahminEt = scan.next();
			if(kelime.contentEquals(tahminEt))
			{
				sonuc = "DO�RU";
				break;
			}
		}
		
		System.out.println("CEVABINIZ "+sonuc+"! KEL�ME="+kelime);
		
		scan.close();
	}
}
