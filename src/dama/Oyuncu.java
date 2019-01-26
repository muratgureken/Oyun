package dama;

public class Oyuncu extends dama{

	public int oyuncuTipi, tasSayisi;
	private int digerOyuncuTipi, gecerliHamleIndis;
	private int yeniDamaOldu=0;
	
	public Oyuncu() {
		oyuncuTipiSecimi();
		oyuncuTipi = oyuncuTipiSecimi;
	}

	public boolean hamleleriHesapla()
	{
		//HAMLE FORMATI: SATIR x 10 + SUTUN

		boolean durum=true;
		int satir, sutun, araDeger;
		satir = secilenTasKonum/10;
		sutun = secilenTasKonum%10;

		if((damaMasasi[satir][sutun]%10)!=oyuncuTipi)
		{
			durum = false;
		}
		else
		{
			//diger oyuncunun tipi belirlenir (1 veya 2 olacak)
			digerOyuncuTipi = oyuncuTipi+1;
			if(digerOyuncuTipi>2)
			{
				digerOyuncuTipi = 1;
			}
			//UYARI: gecersiz konum kontrol ekle.
			/*System.out.println("oyuncu tipleri: sen:"+oyuncuTipi+" rakip:"+digerOyuncuTipi+" tas tipi:"+damaMasasi[satir][sutun]+
					" secilen konum:"+secilenTasKonum);*/
			hamleSayisi = 0;
			zorunluHamleVar = 0;

			//NORMAL TAS 
			if(damaMasasi[satir][sutun]<10)
			{
				//damataslari 11-12 degerlerini aliyor. normal taslar 1-2 oluyor.
				//komsulara bakï¿½larak hamle hesaplanir. maksimum 4 komsusu olabilir.

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
					//System.out.println("satir:"+satir+" sutun:"+sutun);
					if((satir-1)>=0)
					{
						araDeger = damaMasasi[satir-1][sutun];
						//System.out.println("satir:"+(satir-1)+" sutun:"+sutun+" aradeger:"+araDeger);
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
						//System.out.println("satir:"+(satir+1)+" sutun:"+sutun+" aradeger:"+araDeger);
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
						//System.out.println("satir:"+(satir)+" sutun:"+(sutun-1)+" aradeger:"+araDeger);
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
						//System.out.println("satir:"+(satir)+" sutun:"+(sutun+1)+" aradeger:"+araDeger);
						if(araDeger==0)
						{
							hamleler[hamleSayisi] = satir*10+(sutun+1);
							elenenTas[hamleSayisi] = -1;
							hamleSayisi++;
							zorunluHamleVar = 0;
						}				
					}
				}
				//System.out.println("yeni mi dama oldu? "+yeniDamaOldu+" hamle sayisi:"+hamleSayisi+" zorunlu hamle var mý? "+zorunluHamleVar);
				if(yeniDamaOldu==1)
				{
					zorunluHamleVar = 0;
				}
			}
			//DAMA
			//komsu sayisi maksimum 14 olabilir, 7 satir 7 sutun hamlesi
			else if(damaMasasi[satir][sutun]>10)
			{
				//UYARI: mutlaka guncelle. Elenen tasi da hesapla.
				//UYARI: zorunluhamlevar alanini doldurmak gerekiyor.
				enYakinKendiTasiniBul();
			}

			hamleleriIsle();
		}
		return durum;
	}

	private int enYakinKendiTasiniBul()
	{
		// satir veya sutunda sag veya solda kendine en yakin tasini bul.
		//once sol en yakini bul ,sonra sag en yakini bul sagi 10la carp, ikisini topla.
		int sonuc=-1, solEnYakin=-1, sagEnYakin=-1,satir,sutun,count, digerOyuncuKonum;
		int[] zorunlu=new int[4];
		int[] damakonumlar=new int[8];
		int[] kendikonumu=new int[4];
		satir = secilenTasKonum/10;
		sutun = secilenTasKonum%10;

		zorunluHamleVar = 0;
		
		//sola bak
		solEnYakin = -1;
		sagEnYakin = satir;
		count=0;
		digerOyuncuKonum = -1;
		zorunlu[0]=-1;
		if(satir!=0)
		{
			for(int i=satir-1;i>=0;i--)
			{
				//System.out.println("dama yerlestir1: "+(damaMasasi[i][sutun]%10)+" "+oyuncuTipi+" "+digerOyuncuTipi);
				//en yakin oldugu yer kendi normal veya dama tasi olabilir, o sebeple mod10 alindi
				if((damaMasasi[i][sutun]%10)==oyuncuTipi)
				{
					solEnYakin = i;
					break;
				}
				//en yakin oldugu ilk tas karsi oyuncu ise bir sonraki de karsi oyuncu olur mu kontrolu yap
				if((damaMasasi[i][sutun]%10)==digerOyuncuTipi)
				{
					digerOyuncuKonum = i*10+sutun;
					zorunluHamleVar = 1;
					zorunlu[0]=1;
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
		
		damakonumlar[0] = solEnYakin+1;
		damakonumlar[1] = sagEnYakin;
		kendikonumu[0] = satir;
		//System.out.println("aralik:"+solEnYakin+" "+sagEnYakin);
		
		/*for(int i=solEnYakin+1;i<sagEnYakin;i++)
		{
			//kendi uzerine hamle yapamaz
			if(i!=satir)
			{
				hamleler[hamleSayisi] = i*10+sutun;
				hamleSayisi++;
			}
		}

		elenenTas[0] = digerOyuncuKonum;	*/		

		//saga bak
		sagEnYakin = 8;
		solEnYakin = satir;
		count=0;
		digerOyuncuKonum = -1;
		zorunlu[1] = -1;
		if(satir!=7)
		{
			for(int i=satir+1;i<=7;i++)
			{
				//System.out.println("dama yerlestir2: "+(damaMasasi[i][sutun]%10)+" "+oyuncuTipi+" "+digerOyuncuTipi);

				//en yakin oldugu yer kendi normal veya dama tasiolabilir, o sebeple mod10 alindi
				if((damaMasasi[i][sutun]%10)==oyuncuTipi)
				{
					sagEnYakin = i;
					break;
				}
				//en yakin oldugu ilk tas karsi oyuncu ise bir sonraki de karsi oyuncu olur mu kontrolu yap
				if((damaMasasi[i][sutun]%10)==digerOyuncuTipi)
				{
					digerOyuncuKonum = i*10+sutun;
					zorunluHamleVar = 1;
					zorunlu[1] = 1;
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

		//System.out.println("aralik:"+solEnYakin+" "+sagEnYakin);
		damakonumlar[0] = solEnYakin+1;
		damakonumlar[1] = sagEnYakin;
		kendikonumu[1] = satir;

		/*for(int i=solEnYakin+1;i<sagEnYakin;i++)
		{
			//kendi uzerine hamle yapamaz
			if(i!=satir)
			{
				hamleler[hamleSayisi] = i*10+sutun;
				hamleSayisi++;
			}
		}

		elenenTas[0] = digerOyuncuKonum;	*/		
		
		//sola bak
		solEnYakin = -1;
		sagEnYakin = sutun;
		count=0;
		digerOyuncuKonum = -1;
		zorunlu[2] = -1;
		if(sutun!=0)
		{
			for(int i=sutun-1;i>=0;i--)
			{
				//System.out.println("dama yerlestir3: "+(damaMasasi[satir][i]%10)+" "+oyuncuTipi+" "+digerOyuncuTipi);

				//en yakin oldugu yer kendi normal veya dama tasiolabilir, o sebeple mod10 alindi
				if((damaMasasi[satir][i]%10)==oyuncuTipi)
				{
					solEnYakin = i;
					break;
				}
				//en yakin oldugu ilk tas karsi oyuncu ise bir sonraki de karsi oyuncu olur mu kontrolu yap
				if((damaMasasi[satir][i]%10)==digerOyuncuTipi)
				{
					digerOyuncuKonum = satir*10+i;
					zorunluHamleVar = 1;
					zorunlu[2] = 1;
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

		//System.out.println("aralik:"+solEnYakin+" "+sagEnYakin);
		damakonumlar[0] = solEnYakin+1;
		damakonumlar[1] = sagEnYakin;
		kendikonumu[2] = satir;

		/*for(int i=solEnYakin+1;i<sagEnYakin;i++)
		{
			//kendi uzerine hamle yapamaz
			if(i!=sutun)
			{
				hamleler[hamleSayisi] = satir*10+i;
				hamleSayisi++;
			}
		}

		elenenTas[0] = digerOyuncuKonum;	*/		

		//saga bak
		sagEnYakin = 8;
		solEnYakin = sutun;
		count=0;
		digerOyuncuKonum = -1;
		zorunlu[3] = -1;
		if(sutun!=7)
		{
			for(int i=sutun+1;i<=7;i++)
			{
				//System.out.println("dama yerlestir4: "+(damaMasasi[satir][i]%10)+" "+oyuncuTipi+" "+digerOyuncuTipi);

				//en yakin oldugu yer kendi normal veya dama tasiolabilir, o sebeple mod10 alindi
				if((damaMasasi[satir][i]%10)==oyuncuTipi)
				{
					sagEnYakin = i;
					break;
				}
				//en yakin oldugu ilk tas karsi oyuncu ise bir sonraki de karsi oyuncu olur mu kontrolu yap
				if((damaMasasi[satir][i]%10)==digerOyuncuTipi)
				{
					digerOyuncuKonum = satir*10+i;
					zorunluHamleVar = 1;
					zorunlu[3] = 1;
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
		
		//System.out.println("aralik:"+solEnYakin+" "+sagEnYakin);
		damakonumlar[0] = solEnYakin+1;
		damakonumlar[1] = sagEnYakin;
		kendikonumu[3] = satir;

		/*for(int i=solEnYakin+1;i<sagEnYakin;i++)
		{
			//kendi uzerine hamle yapamaz
			if(i!=sutun)
			{
				hamleler[hamleSayisi] = satir*10+i;
				hamleSayisi++;
			}
		}

		elenenTas[0] = digerOyuncuKonum;	*/		
		int necizilecek=0;
		if(zorunluHamleVar==1)
		{
			necizilecek = 1;
		}
		
		hamleSayisi = 0;
		for(int i=0;i<4;i++)
		{
			if(zorunlu[i]==necizilecek)
			{
				for(int j=damakonumlar[2*i]; j<damakonumlar[2*i+1];j++)
				{
					if(j!=kendikonumu[i])
					{
						hamleler[hamleSayisi] = satir*10+i;
						hamleSayisi++;
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
		//System.out.println("hamle sayisi:"+hamleSayisi);
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
		int silinecekIndis=0;
		konum = tasYeri2MasaKonumu(hamleYeri);
		satir = konum/10;
		sutun = konum%10;
		satir2 = secilenTasKonum/10;
		sutun2 = secilenTasKonum%10;;
		//hamle, hamleler listesinde mi kontrol et
		
		//System.out.println("secilen konum:"+konum+" "+" suan:"+secilenTasKonum);
		
		//hamle gecerli ise
		if(hamleGecerliMi(konum))
		{
			//hamle yapilir
			damaMasasi[satir][sutun] = damaMasasi[satir2][sutun2];
			damaMasasi[satir2][sutun2] = 0;
			//hamle yapilirken yenen tas silinir
			//normal tas veya dama durumuna gore elenecek tas indisi farkli secilir.
			if(damaMasasi[satir2][sutun2]<10)
			{
				silinecekIndis = gecerliHamleIndis;
			}
			System.out.println("silinecek indis"+silinecekIndis+" "+elenenTas[silinecekIndis]);
			
			if(elenenTas[silinecekIndis]!=-1)
			{
				satir2 = elenenTas[silinecekIndis]/10;
				sutun2 = elenenTas[silinecekIndis]%10;
				//System.out.println("silinecek indis: "+satir2+" "+sutun2);
				damaMasasi[satir2][sutun2] = 0;
			}
			//tasin yeni konumu guncellenir.
			secilenTasKonum = konum;  
			//System.out.println("hamle sonrasi tas konum:"+secilenTasKonum+" deger:"+damaMasasi[satir][sutun]);
			//tas dama olacak sarti saglamis ve hala dama degilse, dama yapilir.
			yeniDamaOldu = 0;
			if((damaMasasi[satir][sutun]<10)&&tasDamaOlduMu())
			{
				damaMasasi[satir][sutun] = damaMasasi[satir][sutun]+10;
				yeniDamaOldu = 1;
			}
			durum = true;
		}	
		return durum;
	}

	private boolean tasDamaOlduMu()
	{
		boolean durum=false;

		//System.out.println("tas dama oldu mu? oyuncutipi:"+oyuncuTipi+" secilenkonum:"+secilenTasKonum+ " satir:"+secilenTasKonum/10);
		
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

	public int DigerOyuncuSonTasDamaYap()
	{
		int tasSayisi=0,satir=0,sutun=0;
		int oyuncuTipi=1;

		if(this.oyuncuTipi==1)
		{
			oyuncuTipi = 2;
		}

		for(int i=0;i<8;i++)
		{
			for(int j=0; j<8; j++)
			{
				if(damaMasasi[i][j]==oyuncuTipi)
				{
					tasSayisi++;
					satir = i;
					sutun = j;
				}
			}
		}

		//eger diger oyuncunun sadece 1 tasi kaldiysa ve bu tas dama degilse, son tasi dama yapilir.
		if((tasSayisi==1)&&(damaMasasi[satir][sutun]<10))
		{
			damaMasasi[satir][sutun] = damaMasasi[satir][sutun]+10; 
		}

		return tasSayisi;
	}
}
