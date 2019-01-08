package yilan;

import java.util.Random;

public class Yilan implements AraIslemler{
	public int oyunBoyu;
	public int[] yilan;
	public int[] yilanMatrisi;
	public int[] bosSatirSayisi;
	public int yilanBoyu, ilkDeger, yilaninBasi;
	
	@Override
	public int YilanIlklendir(int ilkDeger, int yilanBoyu)
	{
		int yilaninBasi;
		
		for(int i=0;i<yilanBoyu;i++)
		{
			yilan[i] = ilkDeger+i;
		}
		
		yilaninBasi = yilan[yilanBoyu-1];

		for(int i=0;i<oyunBoyu*oyunBoyu;i++)
		{
			yilanMatrisi[i] = 0;
		}
		
		for(int i=0;i<yilanBoyu;i++)
		{
			yilanMatrisi[yilan[i]] = 1;
		}
		
		return yilaninBasi;
	}
	
	public int YilanBasiniHesapla(int yilanBasi, String tus)
	{
		int yilaninBasi;
		
		switch(tus)
		{
		case "1":
			if((yilanBasi%oyunBoyu)==0)
			{
				yilaninBasi = yilanBasi + (oyunBoyu-1);
			}
			else
			{
				yilaninBasi = yilanBasi-1;
			}
			break;
		case "2":
			if(yilanBasi>=(oyunBoyu*(oyunBoyu-1)))
			{
				yilaninBasi = yilanBasi%oyunBoyu;
			}
			else
			{
				yilaninBasi = yilanBasi+oyunBoyu;
			}
			break;
		case "3":
			if((yilanBasi%oyunBoyu)==(oyunBoyu-1))
			{
				yilaninBasi = yilanBasi-(oyunBoyu-1);
			}
			else
			{
				yilaninBasi = yilanBasi+1;
			}
			break;
		default:
			if(yilanBasi<oyunBoyu)
			{
				yilaninBasi = yilanBasi+oyunBoyu*(oyunBoyu-1);
			}
			else
			{
				yilaninBasi = yilanBasi-oyunBoyu;
			}
			break;	
		}
		
		return yilaninBasi;
	}

	@Override
	public void RastgeleSayiUret(int oyunBoyu, int yilanBoyu) {
		int rastGeleSayi=0,count=-1;
		
		Random rand = new Random();
		rastGeleSayi = rand.nextInt(oyunBoyu-yilanBoyu);
		//System.out.println("rastgele sayi:"+rastGeleSayi);
		for(int i=0;i<(oyunBoyu*oyunBoyu);i++)
		{
			if(yilanMatrisi[i]==0)
			{
				count++;
				if(count==rastGeleSayi)
				{
					yilanMatrisi[i] = 2;
					break;
				}
			}
		}
		yilanMatrisi[rastGeleSayi] = 2;
	}

	@Override
	public void MatrisCiz(String yon) {
		for(int i=0;i<oyunBoyu*oyunBoyu;i++)
		{
			if((i%oyunBoyu)==0)
			{
				System.out.println();
			}
			if(yilanMatrisi[i]==0)
			{
				System.out.print(" . ");
			}
			else if(yilanMatrisi[i]==1)
			{
				if(i==yilaninBasi)
				{
					switch(yon)
					{
						case "1":
							System.out.print(" < ");
							break;
						case "2":
							System.out.print(" V ");
							break;
						case "3":
							System.out.print(" > ");
							break;
						default:
							System.out.print(" A ");
							break;
					}
				}
				else
				{
					System.out.print(" @ ");
				}
			}
			else
			{
				System.out.println(" O ");
			}
		}
		System.out.println();
	}

	@Override
	public void YilaniYerlestir(String tus) {
  
	}
	
	public boolean OyunDevamKontrol() {
		boolean devam = false;
		
		return devam;
	}
}
