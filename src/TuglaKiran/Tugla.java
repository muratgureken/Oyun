package TuglaKiran;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Tugla implements AraIslemler{
	public int oyunBoyu, skor, seviye;
	public int[] tuglalar;
	private int oyuncununYeri, beklemeSuresi=500, hamle;

	public Tugla(int oyunBoyu) {
		this.oyunBoyu = oyunBoyu;
		skor = 0;
		seviye = 1;
		TuglaIlklendir();
		MatrisIlklendir();
		MatrisCiz();
	}

	public void TuglaIlklendir()
	{
		tuglalar = new int[oyunBoyu*oyunBoyu];
	}

	@Override
	public void MatrisCiz() {
		//tuglalar: 0: bos, 1: tugla, 2:top olan oyuncu 3:top olmayan oyuncu 4:top
		System.out.print("SEVIYE:"+seviye);		
		System.out.println("  SKOR:"+skor);
		for(int i=0;i<(oyunBoyu*oyunBoyu);i++)
		{
			if(i%oyunBoyu==0)
			{
				System.out.println();				
				System.out.print("|");				
			}

			if(tuglalar[i]==0)
			{
				System.out.print(" . ");
			}
			else if(tuglalar[i]==1)
			{
				System.out.print("[@]");
			}
			else if(tuglalar[i]==2)
			{
				System.out.print("[O]");
			}
			else if(tuglalar[i]==3)
			{
				System.out.print("[ ]");
			}
			else
			{
				System.out.print(" O ");
			}

			if(i%oyunBoyu==(oyunBoyu-1))
			{
				System.out.print("|");				
			}
		}	
		System.out.println();				
		System.out.println();				
	}

	@Override
	public void RastgeleTuglaUret() {
		int tuglaSayisi=0, tuglaYeri, count=0;
		boolean devam=true, devam2=true;

		Random rand = new Random();	
		//en ust satirda uretilecek tugla sayisi min 1 olabilir.
		while(devam2)
		{
			tuglaSayisi = rand.nextInt(oyunBoyu/2*seviye-1);	//bir sirada uretilecek tugla sayisi
			if(tuglaSayisi>0)
			{
				devam2 = false;
			}
		}
		//System.out.println("en ust satir tugla sayisi:"+tuglaSayisi);
		//tugla yerlerini sec
		while(devam)
		{
			tuglaYeri = rand.nextInt(oyunBoyu-1);
			if(OncedenVarmi(tuglaYeri))
			{
				continue;
			}
			tuglalar[tuglaYeri] = 1;
			count++;

			if(count==tuglaSayisi)
			{
				devam = false;
			}
		}
	}

	public boolean OncedenVarmi(int randomSayi)
	{
		boolean durum=false;

		if(tuglalar[randomSayi]==1)
		{
			durum = true;
		}

		return durum;
	}

	@Override
	public void MatrisIlklendir() {
		for(int i=0;i<(oyunBoyu*oyunBoyu);i++)
		{
			tuglalar[i] = 0;
		}

		RastgeleTuglaUret();
		//tugla en alt satira oyuncuyu yerlestir
		tuglalar[oyunBoyu*(oyunBoyu-1)+oyunBoyu/2] = 2;
		oyuncununYeri = (oyunBoyu*(oyunBoyu-1)+oyunBoyu/2);
	}

	public void HamleYap(String hamle) throws InterruptedException {
		int enYakinTugla;
		//System.out.println("hamle:"+hamle);
		if(hamle.contains("1")) //oyuncu sola gidecek
		{
			//oyuncu en solda mi kontrol et, degilse sola kaydir. yoksa bisey yapma
			if((oyuncununYeri%oyunBoyu)!=0)
			{
				tuglalar[oyuncununYeri] = 0;
				oyuncununYeri--;
				tuglalar[oyuncununYeri] = 2;
			}
			MatrisCiz();
			TimeUnit.MILLISECONDS.sleep(beklemeSuresi);
		}
		else if(hamle.contains("3")) // oyuncu saga gidecek
		{
			//oyuncu en sagda mi kontrol et, degilse saga kaydir. yoksa bisey yapma
			if((oyuncununYeri%oyunBoyu)!=(oyunBoyu-1))
			{
				tuglalar[oyuncununYeri] = 0;
				oyuncununYeri++;
				tuglalar[oyuncununYeri] = 2;
			}			
			MatrisCiz();
			TimeUnit.MILLISECONDS.sleep(beklemeSuresi);
		}
		else if(hamle.contains("a"))//oyuncu ates edecek
		{
			//oncelikle oyuncunun bulundugu kolondaki en yakin kolonu bul (varsa)
			enYakinTugla = EnYakinTuglayiBul();
			//System.out.println("en yakin tugla: "+enYakinTugla);
			tuglalar[oyuncununYeri] = 3;
			//topu oyuncudan tuglaya firlat
			if(enYakinTugla!=-1)
			{
				tuglalar[enYakinTugla] = 4;
				skor++;
			}
			MatrisCiz();
			TimeUnit.MILLISECONDS.sleep(beklemeSuresi);
			//topu kullaniciya geri yerlestir, tugla yokolduysa orayi temizle
			tuglalar[oyuncununYeri] = 2;
			if(enYakinTugla!=-1)
			{
				tuglalar[enYakinTugla] = 0;
			}
			MatrisCiz();
			TimeUnit.MILLISECONDS.sleep(beklemeSuresi);
		}
	}



	private int EnYakinTuglayiBul() {
		int enYakinTugla=-1;

		//oyuncunun ustundeki kolona bak. oyuncuya en yakin satirdan basla
		for(int i=0; i<oyunBoyu; i++)
		{
			if(tuglalar[oyuncununYeri-oyunBoyu*i]==1)
			{
				enYakinTugla = oyuncununYeri-oyunBoyu*i;
				break;
			}
		}
		return enYakinTugla;
	}

	@Override
	public boolean OyunBitti() {
		boolean durum=false;
		int sonSatirBaslangic=oyunBoyu*(oyunBoyu-1);
		//en alt satirda tugla var mi kontrol edilir.
		for(int i=0;i<oyunBoyu;i++)
		{
			//System.out.println("Oyun bitti kontrol: indis: "+(sonSatirBaslangic+i)+" nedir:"+tuglalar[sonSatirBaslangic+i]);
			if(tuglalar[sonSatirBaslangic+i]==1)
			{
				durum = true;
				break;
			}
		}
		return durum;
	}

	public void TuglalariAsagiIndir()
	{
		//kaydirma islemi sondan baslamali, aksi takdirde matris karisir
		//son satira bakmaya gerek yok. orayavarildiginda zaten oyun bitiyor.
		for(int i=(oyunBoyu*(oyunBoyu-1)-1);i>=0;i--)
		{
			if(tuglalar[i]==1)
			{
				tuglalar[i] = 0;
				tuglalar[i+oyunBoyu] = 1;
			}
		}
		RastgeleTuglaUret();
		MatrisCiz();			
	}
}
