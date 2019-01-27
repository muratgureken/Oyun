package dama;

public abstract class dama {
	public static int[][] damaMasasi;
	public String tasTipi;
	public static int oyuncuTipiSecimi=0, hamleSayisi, zorunluHamleVar, secilmesiZorunluTasSayisi;
	public static int secilenTasKonum, elenecekTasSayisi, toplamHamleSayisi;
	public static int[] hamleler;
	public static int[] elenenTas, elenenIndis, secilenTaslar, tasHamleSayisi, tasZorunluMu;

	static {
		hamleler = new int[14];
		elenenTas = new int[4];
		elenenIndis = new int[4];
		damaMasasi = new int[8][8];
		secilenTaslar = new int[16];
		tasZorunluMu = new int[16];
		tasHamleSayisi =new int[16];
		hamleSayisi = 0;
		secilmesiZorunluTasSayisi = 0;
		for(int i=0;i<8;i++)
		{
			for(int j=0; j<8; j++)
			{
				if((i==1)||(i==2))
				{
					damaMasasi[i][j] = 1;
					continue;
				}
				if((i==5)||(i==6))
				{
					damaMasasi[i][j] = 2;
					continue;
				}			
				damaMasasi[i][j] = 0;
			}
		}
	}

	public static int tasYeri2MasaKonumu(String tasYeri)
	{
		int masaKonumu=0,satir=0,sutun=0;

		switch(tasYeri.substring(0, 1))
		{
		case "A":
			satir = 0;
			break;
		case "B":
			satir = 1;
			break;
		case "C":
			satir = 2;
			break;
		case "D":
			satir = 3;
			break;
		case "E":
			satir = 4;
			break;
		case "F":
			satir = 5;
			break;
		case "G":
			satir = 6;
			break;
		case "H":
			satir = 7;
			break;
		default:
			break;
		}

		sutun = Integer.parseInt(tasYeri.substring(1, 2));
		sutun = sutun-1;
		//yanlis deger girilmesine onlem
		if(sutun<0)
		{
			sutun = 0;
		}
		if(sutun>7)
		{
			sutun = 0;
		}

		masaKonumu = satir*10+sutun;

		return masaKonumu;
	}

	public void oyuncuTipiSecimi()
	{
		oyuncuTipiSecimi++;
		if(oyuncuTipiSecimi>2)
		{
			oyuncuTipiSecimi = 1;
		}
	}

	public void hamleleriIsle(int k)
	{
		int satir, sutun;
		//System.out.println("hamle islede hamle sayisi:"+hamleSayisi);
		if((k==0)||(k==1))
		{
			for(int i=0;i<hamleSayisi;i++)
			{
				//System.out.println("HAMLELER:"+hamleler[i]);
				satir = hamleler[i]/10;
				sutun = hamleler[i]%10;
				damaMasasi[satir][sutun] = 3;
			}
		}
	}

	public static void hamleleriSil()
	{
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				if(damaMasasi[i][j]==3)
				{
					damaMasasi[i][j] = 0;	
				}
			}
		}		
	}
	
	public static void damaMasasiCiz()
	{
		int satir=-1,sutun=-1;
		String satirIsim="ABCDEFGH";
		
		if(secilenTasKonum>-1)
		{
			satir = secilenTasKonum/10;
			sutun = secilenTasKonum%10;
		}

		System.out.println("    1  2  3  4  5  6  7  8");
		
		for(int i=0;i<8;i++)
		{
			
			System.out.print(" "+satirIsim.substring(i,i+1)+" ");
			for(int j=0; j<8; j++)
			{
				//secilen tasi belirtmek icin bas ve sonuna [] konulur.
				if((i==satir)&&(j==sutun))
				{
					System.out.print("[");
				}
				else
				{
					System.out.print(" ");
				}

				// ilk oyuncu normal tas
				if(damaMasasi[i][j]==1)
				{
					System.out.print("q");
				}
				//ikinci oyuncu normal tas
				else if(damaMasasi[i][j]==2)
				{
					System.out.print("t");
				}
				//ilk oyuncu dama tasi
				else if(damaMasasi[i][j]==11)
				{
					System.out.print("Q");
				}
				//ikinci oyuncu dama tasi
				else if(damaMasasi[i][j]==12)
				{
					System.out.print("T");
				}
				//hamleler
				else if(damaMasasi[i][j]==3)
				{
					System.out.print("O");
					//hamle ile isaretlenen satirlari yeniden 0a cekelim.
					damaMasasi[i][j] = 0;
				}
				//bos 
				else
				{
					System.out.print(".");
				}	

				//secilen tasi belirtmek icin bas ve sonuna [] konulur.
				if((i==satir)&&(j==sutun))
				{
					System.out.print("]");
				}
				else
				{
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
}
