package mayintarlasi;

public abstract class MayinTarlasi {
	public abstract void MayinlariYerlestir(int sayi);
	public abstract void NoktaDegeri(int [] MayinTarlasi);
	public abstract void TarlayiDoldur(Kutu a, String [] Tarla, int[] MayinTarlasi, int[] MayinKontrol, int satirSayisi, int sutunSayisi, int iterasyon);
	public abstract void  MayinTarlasiOlustur(int matrisBoyutu);

	public void MayinlariGoster(int []MayinTarlasi, int matrisBoyutu, int sutunSayisi)
	{
		int deger1=0,deger2=0;


		for(int i=0;i<matrisBoyutu;i++)
		{
			deger1=i/sutunSayisi;
			if(deger1!=deger2)
			{
				System.out.println();
				deger2 = deger1;
			}
			if(MayinTarlasi[i]==1)
			{
				System.out.print("M"+" ");
			}
			else
			{
				System.out.print("o"+" ");
			}
		}
		System.out.println();
	}
}
