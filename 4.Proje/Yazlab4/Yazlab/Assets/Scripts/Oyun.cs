using System.Collections;
using System.Collections.Generic;
using System.Linq;
using UnityEngine;
using UnityEngine.UI;

public class Oyun : MonoBehaviour
{
    public GameObject Yanlis;
    public Text Sure;
    public Text Seviye,Bolum;
    public Text SkorText;
    public GameObject KelimePanel, SkorPanel;
    public GameObject HarflerPanel;
    public GameObject SeviyeSecim;
    public GameObject Bildin;

    public static float Baslangic;

    void OnEnable()
    {
        //Temizle
        basilmisButonlar = new Stack<Button>();
        karakter = 0;
        Baslangic = Time.time;
        Skor = 100;
        Bildin.SetActive(false);
        SkorPanel.SetActive(false);
        Time.timeScale = 1;

        //Metinleri ayarla
        Sure.text = "Süre: 0sn";
        Seviye.text = (Manager.Level+1) + ". Seviye";
        Bolum.text = (Manager.Bolum+1) + ". Bölüm";
        
        //Kelime
        int childCount = KelimePanel.transform.childCount;
        for (int i=0;i< childCount;i++)
        {
            GameObject harf = KelimePanel.transform.GetChild(i).gameObject;
            harf.SetActive(i < Manager.Level + 3);
            harf.GetComponentInChildren<Text>().text = "";
        }
        //Harfleri olustur
        var random = new System.Random();
        List<string> harfler = new List<string>();
        seciliKelime = Manager.Kelimeler[Manager.Level][Manager.Bolum];
        foreach (char harf in seciliKelime)
            harfler.Add(harf.ToString());

        //Harfleri karıştır
        harfler = harfler.OrderBy(a => random.Next()).ToList();

        //Butonları dinamik olarak harfe göre ayarla
        for (int i = 0; i < HarflerPanel.transform.childCount; i++)
        {
            Button buton = HarflerPanel.transform.GetChild(i).GetComponent<Button>();
            if (i >= seciliKelime.Length)
            {
                buton.gameObject.SetActive(false);
                continue;
            }
            else
            {
                buton.gameObject.SetActive(true);
            }
            buton.GetComponentInChildren<Text>().text = harfler[i];
            buton.onClick.RemoveAllListeners();
            buton.onClick.AddListener(() => HarfEkle(buton));
            buton.interactable = true;
        }
    }

    int zaman;
    void Update()
    {

        int simdi = Mathf.FloorToInt(Time.time - Baslangic);
        if (Time.time != Baslangic)
        {
            if (zaman != simdi)
                Skor--;
            if (Skor < 0)
                Skor = 0;
        }
        zaman = simdi;

        Sure.text = "Süre: " + simdi + "sn";
    }

    public void Sonraki()
    {
        if (!Manager.Ilerle())
            OnEnable();
    }

    public void Geri()
    {
        SeviyeSecim.SetActive(true);
        gameObject.SetActive(false);
        transform.GetComponentInParent<AudioSource>().PlayOneShot(Manager.Aktif.ClickSes);
    }

    Stack<Button> basilmisButonlar;
    int karakter;
    public void Sil()
    {
        if (karakter > 0)
        {
            basilmisButonlar.Pop().interactable = true;
            KelimePanel.transform.GetChild(--karakter).GetComponentInChildren<Text>().text = "";
        }
    }

    string seciliKelime;
    int Skor = 100;
    public void HarfEkle(Button buton)
    {
        if (karakter >= Manager.Level+3)
            return;
        Yanlis.SetActive(false);

        string harf = buton.GetComponentInChildren<Text>().text;
        buton.interactable = false;
        basilmisButonlar.Push(buton);
        KelimePanel.transform.GetChild(karakter++).GetComponentInChildren<Text>().text = harf;

        //Bitti
        if (karakter == Manager.Level + 3)
        {
            string metin = string.Empty;
            foreach (Button btn in basilmisButonlar.Reverse())
                metin += btn.GetComponentInChildren<Text>().text;
            if (seciliKelime.ToLowerInvariant() == metin.ToLowerInvariant())
            {
                Bildin.SetActive(true);
                Bildin.GetComponentInChildren<Text>().text = $"Seviye {Manager.Level + 1}\r\nBölüm {Manager.Bolum + 1}\r\n\r\nSkor: ~{Skor}~";
                Manager.Aktif.Skor(Skor);
            }
            else
            {
                Skor -= 10;
                Yanlis.SetActive(true);
                for (int i = 0; i < seciliKelime.Length; i++)
                {
                    Sil();
                }
            }
        }
    }

    bool skorAcik = false;
    public void SkorGosterGizle()
    {
        if (skorAcik)
        {
            Time.timeScale = 1;
            SkorPanel.SetActive(false);
        }
        else
        {
            //Skor göster
            Time.timeScale = 0;
            SkorPanel.SetActive(true);
            SkorText.text = "-Yüksek Skor-\r\n" + Manager.Aktif.YuksekSkor();
        }
        skorAcik = !skorAcik;
    }

    public void SkorResetle()
    {
        Manager.Aktif.SkorResetle();
    }
}
