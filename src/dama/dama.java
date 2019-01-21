package dama;

public abstract class dama {
	public static int[][] damaMasasi;
	public String tasTipi;
	public static int oyuncuTipiSecimi=0;
	public static String secilenTas;
	
	static {
		damaMasasi = new int[8][8];
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
		System.out.println("arac static blok calisti");
	}
	
	public void oyuncuTipiSecimi()
	{
		oyuncuTipiSecimi++;
		if(oyuncuTipiSecimi>2)
		{
			oyuncuTipiSecimi = 1;
		}
	}
	
	public int tasSay(int oyuncuTipi)
	{
		int count=0;
		
		for(int i=0; i<8; i++)
		{
			for(int j=0; j<8; j++)
			{
				if((damaMasasi[i][j])==oyuncuTipi)
				{
					count++;
				}
			}
		}
		
		return count;
	}
	
	public void damaMasasiCiz()
	{
		
	}
	
	public void damlaMasasiGuncelle()
	{
		
	}
}
