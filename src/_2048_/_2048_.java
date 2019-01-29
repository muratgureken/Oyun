package _2048_;

import java.util.Random;

public class _2048_ {
	private int boy, tabloBoy, bosElemanSayisi;
	private int[] tablo;
	private int [][] kolon;
	public String yon;

	public _2048_(int boy) {
		this.boy = boy;
		tabloBoy = boy * boy;
		tablo = new int[tabloBoy];
		kolon = new int[boy][boy];
		bosElemanSayisi = tabloBoy;

		for(int i=0; i<tabloBoy; i++)
		{
			tablo[i] = 0;
		}
		rastgeleSayiUret();
		matrisCiz();
	}

	public void oyna()
	{
		int nereye, suAnkiYer;

		if((yon.equals("3"))||(yon.equals("2")))
		{
			nereye = boy - 1;
			suAnkiYer = boy - 1;
		}
		else
		{
			nereye = 0;
			suAnkiYer = 0; 
		}

		kolonHesapla();
		for(int i=0; i<boy; i++)
		{
			System.out.println("Kolon : "+(i+1));
			kaydir(i, nereye, suAnkiYer);	

			for(int j=0;j<4;j++)
			{
				System.out.print(kolon[i][j]+" ");
			}
			System.out.println();
		}
		tabloYerlestir();
		rastgeleSayiUret();
		matrisCiz();
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

	private void kaydir(int kolonInd, int nereye, int suAnkiYer)
	{
		int indis, indis2;
		System.out.println("kolon["+kolonInd+"]["+suAnkiYer+"] nereye:"+nereye);
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
		yer = rand.nextInt(bosElemanSayisi);
		// yuzde95 olasilikla 2, yuzde5 olasilikla 4 uretilecek.
		if(olasilik<95)
		{
			deger = 2;
		}

		System.out.println("rastgele: bossayi:"+bosElemanSayisi+" yer:"+yer+" deger:"+deger);
		
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
