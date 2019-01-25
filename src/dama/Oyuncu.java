package dama;

public class Oyuncu extends dama{

	public int oyuncuTipi, tasSayisi;
	private int digerOyuncuTipi, gecerliHamleIndis;
	
	public Oyuncu() {
		oyuncuTipiSecimi();
		oyuncuTipi = oyuncuTipiSecimi;
	}

	public boolean hamleleriHesapla()
	{
		//HAMLE FORMATI: SATIR x 10 + SUTUN

		boolean durum=false;
		int satir, sutun, araDeger;
		satir = secilenTasKonum/10;
		sutun = secilenTasKonum%10;
				
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
			//komsulara bak�larak hamle hesaplanir. maksimum 4 komsusu olabilir.

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
						zorunluHamleVar = 0;
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
						zorunluHamleVar = 0;
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
						zorunluHamleVar = 0;
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
						zorunluHamleVar = 0;
					}				
				}
			}
		}
		//DAMA
		//komsu sayisi maksimum 14 olabilir, 7 satir 7 sutun hamlesi
		else if(damaMasasi[satir][sutun]<10)
		{
			//UYARI: mutlaka guncelle. Elenen tasi da hesapla.
                        //UYARI: zorunluhamlevar alanini doldurmak gerekiyor.
			enYakinKendiTasiniBul();
		}

                hamleleriIsle();
		return durum;
	}

	private int enYakinKendiTasiniBul()
	{
		// satir veya sutunda sag veya solda kendine en yakin tasini bul.
		//once sol en yakini bul ,sonra sag en yakini bul sagi 10la carp, ikisini topla.
		int sonuc=-1, solEnYakin=-1, sagEnYakin=-1,satir,sutun,count;
		satir = secilenTasKonum/10;
		sutun = secilenTasKonum%10;

			//sola bak
			solEnYakin = -1;
                        sagEnYakin = satir;
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
                                            sagEnYakin = i;
                                            count++;
					}
					//2 kez karsi oyuncu tasina rastlarsa 2.den onceki tasi sec
					if(count==2)
					{
						solEnYakin = i;
						break;
					}
				}
			}

			for(int i=solEnYakin+1;i<sagEnYakin;i++)
			{
				//kendi uzerine hamle yapamaz
				if(i!=satir)
				{
					hamleler[hamleSayisi] = i*10+sutun;
					hamleSayisi++;
				}
			}
                        
			//saga bak
			sagEnYakin = 8;
                        solEnYakin = satir;
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
                                            solEnYakin = i;
                                            count++;
					}
					//2 kez karsi oyuncu tasina rastlarsa 2.den onceki tasi sec
					if(count==2)
					{
						sagEnYakin = i;
						break;
					}
				}
			}
                        
                        for(int i=solEnYakin+1;i<sagEnYakin;i++)
			{
				//kendi uzerine hamle yapamaz
				if(i!=satir)
				{
					hamleler[hamleSayisi] = i*10+sutun;
					hamleSayisi++;
				}
			}
		

			//sola bak
			solEnYakin = -1;
                        sagEnYakin = sutun;
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
                                            sagEnYakin = i;
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
                        
                        for(int i=solEnYakin+1;i<sagEnYakin;i++)
			{
				//kendi uzerine hamle yapamaz
				if(i!=sutun)
				{
					hamleler[hamleSayisi] = satir*10+i;
					hamleSayisi++;
				}
			}

			//saga bak
			sagEnYakin = 8;
                        solEnYakin = sutun;
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
						solEnYakin = i;
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
                        
                        for(int i=solEnYakin+1;i<sagEnYakin;i++)
			{
				//kendi uzerine hamle yapamaz
				if(i!=sutun)
				{
					hamleler[hamleSayisi] = satir*10+i;
					hamleSayisi++;
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
		satir2 = secilenTasKonum/10;
		sutun2 = secilenTasKonum%10;;
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
                       
                        //tasin yeni konumu guncellenir.
                        secilenTasKonum = konum;  
                        //tas dama olacak sarti saglamis ve hala dama degilse, dama yapilir.
                        if((damaMasasi[satir][sutun]<10)&&tasDamaOlduMu())
                        {
                            damaMasasi[satir][sutun] = damaMasasi[satir][sutun]+10;
                        }
                        durum = true;
		}	
		return durum;
	}
        
        private boolean tasDamaOlduMu()
        {
            boolean durum=false;
            
            if(oyuncuTipi==1)
            {
                if((secilenTasKonum/10)==7)
                {
                    durum=true;
                }
            }
            else if(oyuncuTipi==2)
            {
                if((secilenTasKonum/10)==0)
                {
                    durum = true;
                }
            }
            
            return durum;
        }
        
        public int OyuncuTasiSay()
        {
            int tasSayisi=0;
            
            for(int i=0;i<8;i++)
            {
                for(int j=0; j<8; j++)
                {
                    if(damaMasasi[i][j]==oyuncuTipi)
                    {
                        tasSayisi++;
                    }
                }
            }
            
            return tasSayisi;
        }
}
