package _2048_;

import java.util.Random;

public class _2048_ {
	private int boy, tabloBoy, bosElemanSayisi;
	private int[] tablo, kolonKontrol;
	private int [][] kolon;
	public String yon;
	private boolean hareketVar=false;

	public _2048_(int boy) {
		this.boy = boy;
		tabloBoy = boy * boy;
		tablo = new int[tabloBoy];
		kolon = new int[boy][boy];
		bosElemanSayisi = tabloBoy;
		kolonKontrol = new int[boy];

		for(int i=0; i<tabloBoy; i++)
		{
			tablo[i] = 0;
		}
		rastgeleSayiUret();
		matrisCiz();
	}

	public boolean oyna()
	{
		//int nereye, suAnkiYer;
		boolean ikiBinKirkSekizVar=true;

		/*if((yon.equals("3"))||(yon.equals("2")))
		{
			nereye = boy - 1;
			suAnkiYer = boy - 1;
		}
		else
		{
			nereye = 0;
			suAnkiYer = 0; 
		}*/

		kolonHesapla();
		hareketVar = false;
		for(int i=0; i<boy; i++)
		{
			kolonKopyala(i);
			//System.out.println("Kolon : "+(i+1));
			kaydirHesapla(i);
			yoneDogruHareketVarmi(i);
			/*kaydir(i, nereye, suAnkiYer);*/	

			/*for(int j=0;j<4;j++)
			{
				System.out.print(kolon[i][j]+" ");
			}
			System.out.println();*/
		}

		if(hareketVar)
		{
			tabloYerlestir();
			bosElemanSay();
			//ONEMLI: ilk kac hamle 2048 kontrolu yapmaya gerek yok bakalim.
			if(!IkiBinKirkSekizVar())
			{
				rastgeleSayiUret();
				ikiBinKirkSekizVar = false;
			}

			matrisCiz();
		}
		else
		{
			ikiBinKirkSekizVar = false;
		}
		return ikiBinKirkSekizVar;
	}

	private void kolonKopyala(int kolonInd)
	{
		for(int i=0; i<boy; i++)
		{
			kolonKontrol[i] = kolon[kolonInd][i];
		}
	}

	private void yoneDogruHareketVarmi(int kolonInd)
	{
		if(!hareketVar)
		{
			for(int i=0; i<boy; i++)
			{
				if(kolonKontrol[i]!=kolon[kolonInd][i])
				{
					hareketVar = true;
					break;
				}
			}
		}
	}

	private void bosElemanSay()
	{
		bosElemanSayisi = 0;

		for(int i=0; i<tabloBoy; i++)
		{
			if(tablo[i]==0)
			{
				bosElemanSayisi++;
			}
		}
	}

	private boolean IkiBinKirkSekizVar()
	{
		boolean durum=false;

		for(int i=0; i<tabloBoy; i++)
		{
			if(tablo[i]==2048)
			{
				durum = true;
				break;
			}
		}

		return durum;
	}

	private void tabloYerlestir()
	{
		int count1=-1, count2=-1;
		for(int i=0; i<tabloBoy; i++)
		{
			if((i%boy)==0)
			{
				count1++;
				count2 = -1;
			}
			count2++;

			if((yon.equals("1"))||(yon.equals("3")))
			{
				tablo[i] = kolon[count1][count2];
			}
			else
			{
				tablo[i] = kolon[count2][count1];
			}
		}
	}

	private void kolonHesapla()
	{
		int count1=-1, count2=-1;

		for(int i=0; i<tabloBoy; i++)
		{
			if(i%boy==0)
			{
				count1++;
				count2=-1;
			}
			count2++;
			if((yon.equals("3"))||(yon.equals("1")))
			{
				kolon[count1][count2] = tablo[i];
				//System.out.println("kolon["+count1+"]["+count2+"]="+tablo[i]);
			}
			else
			{
				kolon[count2][count1] = tablo[i];
				//System.out.println("kolon["+count2+"]["+count1+"]="+tablo[i]);
			}
		}
	}

	private void kaydirHesapla(int kolonInd)
	{
		int count, indis, fark;
		boolean devam=true;

		if((yon.equals("2"))||(yon.equals("3")))
		{
			count = boy-1;
		}
		else
		{
			count = 0;
		}

		while(devam)
		{
			indis = ilkSayiyiBul(count, kolonInd);
			//System.out.println("ilk sayi: "+indis);
			if(indis>=0)
			{
				fark = count - indis;
				fark = (fark<0)?(-1*fark):fark;
				oteleme(fark, count, kolonInd);
				if((yon.equals("2"))||(yon.equals("3")))
				{
					count--;
				}
				else
				{
					count++;
				}
			}
			else
			{
				devam= false;
			}
		}

		devam=true;
		if((yon.equals("2"))||(yon.equals("3")))
		{
			count = boy-1;
		}
		else
		{
			count = 0;
		}

		while(devam)
		{
			if((yon.equals("2"))||(yon.equals("3")))
			{
				indis = count - 1;
			}
			else
			{
				indis = count + 1;
			}

			if((count!=0 &&(yon.equals("2")||yon.equals(("3"))))||(count!=(boy-1) &&(yon.equals("1")||yon.equals(("5")))))
			{
				if(kolon[kolonInd][count]==kolon[kolonInd][indis])
				{
					kolon[kolonInd][count] = kolon[kolonInd][count] + kolon[kolonInd][indis];
					if((yon.equals("2"))||(yon.equals("3")))
					{
						count--;
					}
					else
					{
						count++;
					}			
					oteleme(1, indis, kolonInd);
				}
				else
				{
					if((yon.equals("2"))||(yon.equals("3")))
					{
						count--;
					}
					else
					{
						count++;
					}					
				}
			}
			else
			{
				devam = false;
			}
		}
	}

	private int ilkSayiyiBul(int count, int kolonInd)
	{
		int sonuc=-1;

		if((yon.equals("2"))||(yon.equals("3")))
		{
			for(int i=count; i>=0; i--)
			{
				if(kolon[kolonInd][i]>0)
				{
					sonuc = i;
					break;
				}
			}
		}
		else
		{
			for(int i=count; i<boy; i++)
			{
				if(kolon[kolonInd][i]>0)
				{
					sonuc = i;
					break;
				}
			}
		}

		return sonuc;
	}

	private void oteleme(int fark, int nereye, int kolonInd)
	{
		if((yon.equals("2"))||(yon.equals("3")))
		{
			for(int i=nereye; i>=fark; i--)
			{
				kolon[kolonInd][i] = kolon[kolonInd][i-fark];
			}
			for(int i=0; i<fark; i++)
			{
				kolon[kolonInd][i] = 0;
			}
		}
		else
		{
			for(int i=nereye; i<boy; i++)
			{
				if((i+fark)>=boy)
				{
					break;
				}
				kolon[kolonInd][i] = kolon[kolonInd][i+fark];
			}
			for(int i=(boy-1); i>(boy-1-fark); i--)
			{
				kolon[kolonInd][i] = 0;
			}			
		}
	}

	private void kaydir(int kolonInd, int nereye, int suAnkiYer)
	{
		int indis, indis2;
		//System.out.println("kolon["+kolonInd+"]["+suAnkiYer+"] nereye:"+nereye);
		if((yon.equals("3"))||(yon.equals("2")))
		{
			indis = suAnkiYer - 1;
			indis2 = nereye - 1;
		}
		else
		{
			indis = suAnkiYer + 1;
			indis2 = nereye + 1;
		}

		if(kolon[kolonInd][suAnkiYer]==0)
		{
			if((indis>=1)&&(indis<=(boy-2)))
			{
				//System.out.println("1");
				kaydir(kolonInd, nereye, indis);
			}
			else
			{
				kolon[kolonInd][nereye] = kolon[kolonInd][suAnkiYer];
				kolon[kolonInd][suAnkiYer] = 0;
			}
		}
		else
		{
			if(kolon[kolonInd][suAnkiYer]==kolon[kolonInd][indis])
			{
				kolon[kolonInd][nereye] = kolon[kolonInd][suAnkiYer] + kolon[kolonInd][indis];
				if(nereye!=suAnkiYer)
				{
					kolon[kolonInd][suAnkiYer] = 0;
				}
				kolon[kolonInd][indis] = 0;
				bosElemanSayisi = bosElemanSayisi + 1;
				if((indis>=1)&&(indis<=(boy-2)))
				{
					//System.out.println("2");
					kaydir(kolonInd, suAnkiYer, indis);
				}
			}

			if(kolon[kolonInd][suAnkiYer]!=kolon[kolonInd][indis])
			{
				kolon[kolonInd][nereye] = kolon[kolonInd][suAnkiYer];
				if(nereye!=suAnkiYer)
				{
					kolon[kolonInd][suAnkiYer] = 0;
				}
				if(((indis>=1)&&(indis2>=1))&&((indis<=(boy-2))&&(indis2<=(boy-2))))
				{
					//System.out.println("3");
					kaydir(kolonInd, indis2, indis);
				}
			}
		}
	}

	public boolean hamleVarmi()
	{
		boolean durum=false;

		for(int i=0; i<tabloBoy; i++)
		{
			if(tablo[i]==0)
			{
				durum = true;
				break;
			}
			if(i==0)
			{
				if((tablo[i]==tablo[i+1])||(tablo[i]==tablo[i+boy]))
				{
					durum = true;
					break;					
				}
			}
			else if(i==boy)
			{
				if((tablo[i]==tablo[i-1])||(tablo[i]==tablo[i+boy]))
				{
					durum = true;
					break;					
				}
			}
			else if(i==(boy*(boy-1)))
			{
				if((tablo[i]==tablo[i+1])||(tablo[i]==tablo[i-boy]))
				{
					durum = true;
					break;					
				}
			}
			else if(i==(tabloBoy-1))
			{
				if((tablo[i]==tablo[i-1])||(tablo[i]==tablo[i-boy]))
				{
					durum = true;
					break;					
				}
			}
			else if(i<boy)
			{
				if((tablo[i]==tablo[i+boy])||(tablo[i]==tablo[i-1])||(tablo[i]==tablo[i+1]))
				{
					durum = true;
					break;						
				}
			}
			else if((i%boy)==0)
			{
				if((tablo[i]==tablo[i+boy])||(tablo[i]==tablo[i+1])||(tablo[i]==tablo[i-boy]))
				{
					durum = true;
					break;						
				}
			}
			else if((i%boy)==(boy-1))
			{
				if((tablo[i]==tablo[i+boy])||(tablo[i]==tablo[i-1])||(tablo[i]==tablo[i-boy]))
				{
					durum = true;
					break;						
				}
			}
			else if(i>=(boy*(boy-1)))
			{
				if((tablo[i]==tablo[i-boy])||(tablo[i]==tablo[i-1])||(tablo[i]==tablo[i+1]))
				{
					durum = true;
					break;						
				}
			}
			else
			{
				if((tablo[i]==tablo[i-boy])||(tablo[i]==tablo[i-1])||(tablo[i]==tablo[i+1])||(tablo[i]==tablo[i+boy]))
				{
					durum = true;
					break;						
				}				
			}
		}
		return durum;
	}

	private void matrisCiz()
	{
		for(int i=0; i<tabloBoy; i++)
		{
			if((i%boy)==0)
			{
				System.out.println();
			}
			if(tablo[i]==0)
			{
				System.out.print("      .");
			}
			else
			{
				System.out.printf("%7S",tablo[i]);				
			}
		}
		System.out.println();
		System.out.println();
	}

	private void rastgeleSayiUret()
	{
		int olasilik, deger=4, yer, count;

		bosElemanSayisi = bosElemanSayisi - 1;
		Random rand = new Random();
		olasilik = rand.nextInt(100);
		//System.out.println("rastgele: bossayiiii:"+bosElemanSayisi);
		yer = rand.nextInt(bosElemanSayisi);
		// yuzde95 olasilikla 2, yuzde5 olasilikla 4 uretilecek.
		if(olasilik<95)
		{
			deger = 2;
		}

		//System.out.println("rastgele: bossayi:"+bosElemanSayisi+" yer:"+yer+" deger:"+deger);

		count = -1;
		for(int i=0;i<tabloBoy; i++)
		{
			if(tablo[i]==0)
			{
				count++;
				if(count==yer)
				{
					tablo[i] = deger;
					break;
				}
			}
		}
	}
}
