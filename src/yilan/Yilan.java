package yilan;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Yilan extends Thread implements AraIslemler{
	public int oyunBoyu;
	LinkedList<Integer> yilan;
	public int[] yilanMatrisi;
	public int[] bosSatirSayisi;
	public int ilkDeger;
	public String tus="3";
	public boolean oyunDevam=true;
	
	@Override
	public void run() {
		while(oyunDevam)
		{
			YilanBasiniHesapla();
			YilaniYerlestir();
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void YilanIlklendir(int ilkDeger, int baslangicBoy)
	{		
		for(int i=0;i<baslangicBoy;i++)
		{
			yilan.add(ilkDeger+i);
		}
		
		for(int i=0;i<oyunBoyu*oyunBoyu;i++)
		{
			yilanMatrisi[i] = 0;
		}
		
		for(int i=0;i<yilan.size();i++)
		{
			yilanMatrisi[yilan.get(i)] = 1;
		}	
	}
	
	public void YilanBasiniHesapla()
	{		
		int yilanBasi = yilan.getLast();
		
		switch(tus)
		{
		case "1":
			if((yilanBasi%oyunBoyu)==0)
			{
				yilan.add(yilanBasi + (oyunBoyu-1));
			}
			else
			{
				yilan.add(yilanBasi-1);
			}
			break;
		case "2":
			if(yilanBasi>=(oyunBoyu*(oyunBoyu-1)))
			{
				yilan.add(yilanBasi%oyunBoyu);
			}
			else
			{
				yilan.add(yilanBasi+oyunBoyu);
			}
			break;
		case "3":
			if((yilanBasi%oyunBoyu)==(oyunBoyu-1))
			{
				yilan.add(yilanBasi-(oyunBoyu-1));
			}
			else
			{
				yilan.add(yilanBasi+1);
			}
			break;
		case "5":
			if(yilanBasi<oyunBoyu)
			{
				yilan.add(yilanBasi+oyunBoyu*(oyunBoyu-1));
			}
			else
			{
				yilan.add(yilanBasi-oyunBoyu);
			}
			break;	
		default:
			break;
		}
	}

	@Override
	public void RastgeleSayiUret() {
		int rastGeleSayi=0,count=-1;
		
		Random rand = new Random();
		rastGeleSayi = rand.nextInt(oyunBoyu*oyunBoyu-yilan.size());
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
	}

	@Override
	public void MatrisCiz(String yon) {
		System.out.print(" ");
		for(int i=0;i<oyunBoyu;i++)
		{
			System.out.print("---");
		}		
		for(int i=0;i<oyunBoyu*oyunBoyu;i++)
		{
			if((i%oyunBoyu)==0)
			{
				System.out.println();
				System.out.print("|");
			}
			if(yilanMatrisi[i]==0)
			{
				System.out.print(" . ");
			}
			else if(yilanMatrisi[i]==1)
			{
				if(i==yilan.getLast())
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
						case "5":
							System.out.print(" ^ ");
							break;
						default:
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
				System.out.print(" O ");
			}
			
			if((i%oyunBoyu)==(oyunBoyu-1))
			{
				System.out.print("|");
			}
		}
		System.out.println();
		System.out.print(" ");
		for(int i=0;i<oyunBoyu;i++)
		{
			System.out.print("---");
		}
		System.out.println();
	}

	@Override
	public boolean YilaniYerlestir() {		
		//eger yilan bir onceki yilanbasi konumuna geri donmeye calisirsa oyun bitmesin, yilani hareket ettirme,
		//bir onceki yilan basi konumuna geri dondur.
		if(yilan.getLast()==yilan.get(yilan.size()-3))
		{
			yilan.removeLast();
			return oyunDevam;
		}
		
		if(yilanMatrisi[yilan.getLast()]==0)
		{
			yilanMatrisi[yilan.getLast()] = 1;
			yilanMatrisi[yilan.getFirst()] = 0;
			yilan.removeFirst();

		}
		else if(yilanMatrisi[yilan.getLast()]==2)
		{
			yilanMatrisi[yilan.getLast()] = 1;
			//yem yendi ise yeniden yem uret
			RastgeleSayiUret();
		}
		else
		{
			oyunDevam= false;
			yilan.removeLast();
		}
		
		if(yilan.size()==(oyunBoyu*oyunBoyu))
		{
			oyunDevam = false;
		}
		
		MatrisCiz(tus);
		return oyunDevam;
	}
}
