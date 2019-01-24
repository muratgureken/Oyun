package dama;

public class Oyuncu extends dama{

	public int oyuncuTipi;
	private int digerOyuncuTipi, gecerliHamleIndis;
	
	public Oyuncu() {
		oyuncuTipiSecimi();
		oyuncuTipi = oyuncuTipiSecimi;
	}

	public boolean hamleleriHesapla(String tasYeri)
	{
		//HAMLE FORMATI: SATIR x 10 + SUTUN

		boolean durum=false;
		int satir, sutun, konum, araDeger, damaAraligi, bas,son;
		konum = tasYeri2MasaKonumu(tasYeri);
		satir = konum/10;
		sutun = konum%10;
		
		secilenTasKonum = konum;
		
		//diger oyuncunun tipi belirlenir (1 veya 2 olacak)
		digerOyuncuTipi = oyuncuTipi++;
		if(digerOyuncuTipi>2)
		{
			digerOyuncuTipi = 1;
		}
		//UYARI: gecersiz konum kontrol ekle.

		hamleSayisi = 0;
		zorunluHamleVar = 0;

		//NORMAL TAS 
		if(damaMasasi[satir][sutun]<10)
		{
			//damataslari 11-12 degerlerini aliyor. normal taslar 1-2 oluyor.
			//komsulara bakýlarak hamle hesaplanir. maksimum 4 komsusu olabilir.

			//ZORUNLU HAMLELER:
			if((satir-1)>=0)
			{
				araDeger = damaMasasi[satir-1][sutun];
				if(araDeger==digerOyuncuTipi)
				{
					if((satir-2)>=0)
					{
						araDeger = damaMasasi[satir-2][sutun];
						//bir yaninda diger oyuncu, onun arkasinda da bosluk var ise normal tas hamle yapabilir.
						if(araDeger==0)
						{
							hamleler[hamleSayisi] = (satir-2)*10+sutun;
							elenenTas[hamleSayisi] = (satir-1)*10+sutun;
							hamleSayisi++;
							zorunluHamleVar = 1;
						}
					}
				}
			}
			if((satir+1)<=7)
			{
				araDeger = damaMasasi[satir+1][sutun];
				if(araDeger==digerOyuncuTipi)
				{
					if((satir+2)<=7)
					{
						araDeger = damaMasasi[satir+2][sutun];
						//bir yaninda diger oyuncu, onun arkasinda da bosluk var ise normal tas hamle yapabilir.
						if(araDeger==0)
						{
							hamleler[hamleSayisi] = (satir+2)*10+sutun;
							elenenTas[hamleSayisi] = (satir+1)*10+sutun;
							hamleSayisi++;
							zorunluHamleVar = 1;
						}
					}
				}				
			}
			if((sutun-1)>=0)
			{
				araDeger = damaMasasi[satir][sutun-1];
				if(araDeger==digerOyuncuTipi)
				{
					if((sutun-2)>=0)
					{
						araDeger = damaMasasi[satir][sutun-2];
						//bir yaninda diger oyuncu, onun arkasinda da bosluk var ise normal tas hamle yapabilir.
						if(araDeger==0)
						{
							hamleler[hamleSayisi] = satir*10+(sutun-2);
							elenenTas[hamleSayisi] = satir*10+(sutun-1);
							hamleSayisi++;
							zorunluHamleVar = 1;
						}
					}
				}
			}
			if((sutun+1)<=7)
			{
				araDeger = damaMasasi[satir][sutun+1];
				if(araDeger==digerOyuncuTipi)
				{
					if((sutun+2)<=7)
					{
						araDeger = damaMasasi[satir][sutun+2];
						//bir yaninda diger oyuncu, onun arkasinda da bosluk var ise normal tas hamle yapabilir.
						if(araDeger==0)
						{
							hamleler[hamleSayisi] = satir*10+(sutun+2);
							elenenTas[hamleSayisi] = satir*10+(sutun+1);
							hamleSayisi++;
							zorunluHamleVar = 1;
						}
					}
				}				
			}

			//ZORUNLU HAMLE YOKSA, DIGER HAMLELER HESAPLANIR
			if(zorunluHamleVar==0)
			{
				if((satir-1)>=0)
				{
					araDeger = damaMasasi[satir-1][sutun];
					if(araDeger==0)
					{
						hamleler[hamleSayisi] = (satir-1)*10+sutun;
						elenenTas[hamleSayisi] = -1;
						hamleSayisi++;
						zorunluHamleVar = 1;
					}
				}

				if((satir+1)<=7)
				{
					araDeger = damaMasasi[satir+1][sutun];
					if(araDeger==0)
					{
						hamleler[hamleSayisi] = (satir+1)*10+sutun;
						elenenTas[hamleSayisi] = -1;
						hamleSayisi++;
						zorunluHamleVar = 1;
					}				
				}
				if((sutun-1)>=0)
				{
					araDeger = damaMasasi[satir][sutun-1];
					if(araDeger==0)
					{
						hamleler[hamleSayisi] = satir*10+(sutun-1);
						elenenTas[hamleSayisi] = -1;
						hamleSayisi++;
						zorunluHamleVar = 1;
					}	
				}
				if((sutun+1)<=7)
				{
					araDeger = damaMasasi[satir][sutun+1];
					if(araDeger==0)
					{
						hamleler[hamleSayisi] = satir*10+(sutun+1);
						elenenTas[hamleSayisi] = -1;
						hamleSayisi++;
						zorunluHamleVar = 1;
					}				
				}
			}
		}
		//DAMA
		//komsu sayisi maksimum 14 olabilir, 7 satir 7 sutun hamlesi
		else if(damaMasasi[satir][sutun]<10)
		{
			//UYARI: mutlaka guncelle. Elenen tasi da hesapla.
			//satirdaki hamleler hesaplanir.
			damaAraligi = enYakinKendiTasiniBul(konum, "satir");
			bas = damaAraligi/10;
			son = damaAraligi%10;
			//bas -1 olmasi durumunda son eksi olmamasi icin onlem alinir.
			if(son<0)
			{
				son = -1*son;
			}
			for(int i=bas+1;i<son;i++)
			{
				//kendi uzerine hamle yapamaz
				if(i!=satir)
				{
					hamleler[hamleSayisi] = i*10+sutun;
					hamleSayisi++;
				}
			}

			damaAraligi = enYakinKendiTasiniBul(konum, "sutun");
			bas = damaAraligi/10;
			son = damaAraligi%10;
			//bas -1 olmasi durumunda son eksi olmamasi icin onlem alinir.
			if(son<0)
			{
				son = -1*son;
			}
			for(int i=bas+1;i<son;i++)
			{
				//kendi uzerine hamle yapamaz
				if(i!=sutun)
				{
					hamleler[hamleSayisi] = satir*10+i;
					hamleSayisi++;
				}
			}
		}


		return durum;
	}

	public int enYakinKendiTasiniBul(int konum, String taramaBoyutu)
	{
		// satir veya sutunda sag veya solda kendine en yakin tasini bul.
		//once sol en yakini bul ,sonra sag en yakini bul sagi 10la carp, ikisini topla.
		int sonuc=-1, solEnYakin=-1, sagEnYakin=-1,satir,sutun,count;
		satir = konum/10;
		sutun = konum%10;
		if(taramaBoyutu.equals("satir"))
		{
			//sola bak
			solEnYakin = -1;
			count=0;
			if(satir!=0)
			{
				for(int i=satir-1;i>=0;i--)
				{
					//en yakin oldugu yer kendi normal veya dama tasi olabilir, o sebeple mod10 alindi
					if(damaMasasi[i][sutun]==(oyuncuTipi%10))
					{
						solEnYakin = i;
						break;
					}
					//en yakin oldugu ilk tas karsi oyuncu ise bir sonraki de karsi oyuncu olur mu kontrolu yap
					if(damaMasasi[i][sutun]==(digerOyuncuTipi%10))
					{
						count++;
					}
					//2 kez karsi oyuncu tasina rastlarsa 2.den onceki tasi sec
					if(count==2)
					{
						solEnYakin = i+1;
						break;
					}
				}
			}

			//saga bak
			sagEnYakin = 8;
			count=0;
			if(satir!=7)
			{
				for(int i=satir+1;i<=7;i++)
				{
					//en yakin oldugu yer kendi normal veya dama tasiolabilir, o sebeple mod10 alindi
					if(damaMasasi[i][sutun]==(oyuncuTipi%10))
					{
						sagEnYakin = i;
						break;
					}
					//en yakin oldugu ilk tas karsi oyuncu ise bir sonraki de karsi oyuncu olur mu kontrolu yap
					if(damaMasasi[i][sutun]==(digerOyuncuTipi%10))
					{
						count++;
					}
					//2 kez karsi oyuncu tasina rastlarsa 2.den onceki tasi sec
					if(count==2)
					{
						sagEnYakin = i-1;
						break;
					}
				}
			}
		}
		if(taramaBoyutu.equals("sutun"))
		{
			//sola bak
			solEnYakin = -1;
			count=0;
			if(sutun!=0)
			{
				for(int i=sutun-1;i>=0;i--)
				{
					//en yakin oldugu yer kendi normal veya dama tasiolabilir, o sebeple mod10 alindi
					if(damaMasasi[satir][i]==(oyuncuTipi%10))
					{
						solEnYakin = i;
						break;
					}
					//en yakin oldugu ilk tas karsi oyuncu ise bir sonraki de karsi oyuncu olur mu kontrolu yap
					if(damaMasasi[satir][i]==(digerOyuncuTipi%10))
					{
						count++;
					}
					//2 kez karsi oyuncu tasina rastlarsa 2.den onceki tasi sec
					if(count==2)
					{
						solEnYakin = i+1;
						break;
					}
				}
			}

			//saga bak
			sagEnYakin = 8;
			count=0;
			if(sutun!=7)
			{
				for(int i=sutun+1;i<=7;i++)
				{
					//en yakin oldugu yer kendi normal veya dama tasiolabilir, o sebeple mod10 alindi
					if(damaMasasi[satir][i]==(oyuncuTipi%10))
					{
						sagEnYakin = i;
						break;
					}
					//en yakin oldugu ilk tas karsi oyuncu ise bir sonraki de karsi oyuncu olur mu kontrolu yap
					if(damaMasasi[satir][i]==(digerOyuncuTipi%10))
					{
						count++;
					}
					//2 kez karsi oyuncu tasina rastlarsa 2.den onceki tasi sec
					if(count==2)
					{
						solEnYakin = i-1;
						break;
					}
				}
			}		
		}

		sonuc = solEnYakin*10+sagEnYakin;
		return sonuc;
	}

	private boolean hamleGecerliMi(int konum)
	{
		boolean durum=false;
		gecerliHamleIndis = -1;
		for(int i=0;i<hamleSayisi;i++)
		{
			if(hamleler[i]==konum)
			{
				gecerliHamleIndis = i;
				durum = true;
			}
		}
		
		return durum;
	}
	
	public boolean hamleYap(String hamleYeri)
	{
		boolean durum=false;
		int konum,satir,sutun,satir2=0,sutun2=0;
		
		konum = tasYeri2MasaKonumu(hamleYeri);
		satir = konum/10;
		sutun = konum%10;
		satir = secilenTasKonum/10;
		sutun = secilenTasKonum%10;;
		//hamle, hamleler listesinde mi kontrol et
		
		//hamle gecerli ise
		if(hamleGecerliMi(konum))
		{
			//hamle yapilir
			damaMasasi[satir][sutun] = damaMasasi[satir2][sutun2];
			damaMasasi[satir2][sutun2] = 0;
			//hamle yapilirken yenen tas silinir
			satir = elenenTas[gecerliHamleIndis]/10;
			sutun = elenenTas[gecerliHamleIndis]%10;
			damaMasasi[satir][sutun] = 0;
		}

		//tasin yeni konumu guncellenir.
		secilenTasKonum = konum;
		
		return durum;
	}
}
