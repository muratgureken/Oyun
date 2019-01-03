package mayintarlasi;
import java.io.*;
import java.util.Random;
public class Kutu extends MayinTarlasi implements YardimciIslemler{

	public int[] Mayinlar = new int[100];
	public int[] MayinTarlasi = new int[100];
	public int[] Komsular = new int[8]; 
	public String [] Tarla = new String[100];
	public int secilenIndis, komsuSayisi, mayinKomsular;
	public boolean kutuDurumu=false;
	
	public Kutu() {
		//initial islemlerin yapildigi bir constructor yaz. asagidaki baslangic metotlarini calistirsin.
	}
	
	public boolean OncedenVarmi(int randomSayi, int count)
	{
		boolean sonuc = false;
		for(int i=0;i<count;i++)
		{
			if(Mayinlar[i]==randomSayi)
			{
				sonuc = true;
				break;
			}
		}
		
		return sonuc;
	}
	
	public void MayinMi()
	{		
		kutuDurumu = false;
		if(MayinTarlasi[secilenIndis]==1)
		{
			kutuDurumu = true;
		}
	}
	
	public void KomsulariBul()
	{
		int satir,sutun;
		satir = secilenIndis%10;
		sutun = secilenIndis-10*satir;
		int count=0;
		if(((satir-1)>0)&&((sutun-1)>0))
		{
			Komsular[count] = satir*10+sutun;
			count++;
		}
		if((satir-1)>0)
		{
			Komsular[count] = (satir-1)*10+sutun;
			count++;
			if((sutun-1)>0)
			{
				Komsular[count] = (satir-1)*10+(sutun-1);
				count++;				
			}
			if((sutun+1)<10)
			{
				Komsular[count] = (satir-1)*10+(sutun+1);
				count++;				
			}
		}
		if((satir+1)>0)
		{
			Komsular[count] = (satir+1)*10+sutun;
			count++;
			if((sutun-1)>0)
			{
				Komsular[count] = (satir+1)*10+(sutun-1);
				count++;				
			}
			if((sutun+1)<10)
			{
				Komsular[count] = (satir+1)*10+(sutun+1);
				count++;				
			}
		}
		if((sutun-1)>0)
		{
			Komsular[count] = (satir)*10+(sutun-1);
			count++;
		}
		if((sutun+1)<10)
		{
			Komsular[count] = (satir)*10+(sutun+1);
			count++;
		}
		komsuSayisi = count;
	}
	
	@Override
	public void RastgeleSayiUret(int sayi) {
		int rastgeleSayi, count=1;
		boolean Devam=true;
		Random rand = new Random();

		while(Devam)
		{
			rastgeleSayi = rand.nextInt(99);
			if(OncedenVarmi(rastgeleSayi, count))
			{
				continue;
			}
			count++;
			Mayinlar[count] = rastgeleSayi;
			if(count==sayi)
			{
				Devam=false;
			}	
		}
	}

	@Override
	public void MatrisIlklendir(int boyut) {
		for(int i=0;i<boyut;i++)
		{
			MayinTarlasi[i] = 0;
		}
	}

	@Override
	public void MatrisCiz() {
		int deger1=0,deger2=0;
		
		for(int i=0;i<100;i++)
		{
			deger1=i%10;
			if(deger1!=deger2)
			{
				System.out.println();
			}
			System.out.print(Tarla[i]);
		}
	}

	@Override
	public void MayinlariYerlestir(int sayi) {
		for(int i=0;i<sayi;i++)
		{
			MayinTarlasi[Mayinlar[i]] = 1;
		}
	}

	@Override
	public void NoktaDegeri() {
		mayinKomsular=0;
		for(int i=0;i<komsuSayisi;i++)
		{
			if(Komsular[i]==1)
			{
				mayinKomsular++;
			}
		}
	}

	@Override
	public void MayinTarlasiOlustur() {
		for(int i=0;i<100;i++)
		{
			Tarla[i] = "X";
		}
	}

	@Override
	public void TarlayiDoldur(Kutu[] KomsuKutular) {
		Tarla[secilenIndis] = Integer.toString(mayinKomsular);
		//eger mayinkomsu sayisi 0 ise, komsularin mayinKomsusu=0 olanlarini bul. 
		if(mayinKomsular==0)
		{
			for(int i=0;i<komsuSayisi;i++)
			{
				KomsuKutular[i].secilenIndis = Komsular[i];
				//komsu kutunun degerini bulacak fonksiyonlari cagir (mayin olan komsularinin sayisi)
				//komsularin hicbiri mayin degil
				
				Tarla[Komsular[i]] = "0";//en son bu icerik doldurulacak.
			}
		}		
	}

}
