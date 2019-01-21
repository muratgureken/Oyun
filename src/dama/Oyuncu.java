package dama;

public class Oyuncu extends dama{
	
	public int oyuncuTipi;
	
	public Oyuncu() {
		oyuncuTipiSecimi();
		oyuncuTipi = oyuncuTipiSecimi;
	}
	
	public boolean hamleYap(String hamleYeri)
	{
		boolean durum=false;
		
		//hamle yaptirir
		if(tasTipi.equals("dama"))
		{
			
		}
		
		return durum;
	}
	
	public void OncelikliHamleleriBul()
	{
		//secilen tasin zorunlu yapmasi gereken hamleleri secer
		//yiyebilecegi bir tas varken bos hamle yapilamaz
	}
	
	public void komsulariKontrolEt()
	{
		//secilen tasin 8 komsuluguna bakilir.
	}
	
	public boolean hamleYapilabilirMi()
	{
		boolean durum=false;
		
		//dama mi normal tas mi?
		//dama ise guzergahta kenditasi var mi?
		//normal ise komsusu mu? komsusunun yani bos mu?
		//normal ise geri hamle yapiyor mu?
		
		return durum;
	}
	
	public boolean tasYe()
	{
		boolean durum=false;
		
		return durum;
	}	
	
	public boolean tasDamaMi()
	{
		//tas dama oldu mu kontrol et
		//tassay ve son karede mi?
		boolean durum=false;
		
		return durum;
	}
	
	public void tasSec(String secilenTas)
	{
		this.secilenTas = secilenTas;
	}
}
