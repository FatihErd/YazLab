using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using UnityEngine;
using UnityEngine.UI;

public class Manager : MonoBehaviour
{
    public static string Oyuncu = "";
    public AudioClip ClickSes;
    public static int Level, Bolum;
    public static int SonLevel, SonBolum;
    public readonly static string[][] Kelimeler = new string[3][];

    public GameObject Menu;
    public GameObject Oyun;
    public Button Devam;
    public InputField isim;

    public static Manager Aktif;
    void Awake()
    {
        Aktif = this;
        Yukle();
        //Level 1
        Kelimeler[0] = new string[] {
            "Ana",
            "Nar",
            "Kan",
            "Bir",
            "Çit",
            "Göl"
        };
        //Level 2
        Kelimeler[1] = new string[] {
            "Ağaç",
            "Hain",
            "Kısa",
            "Masa",
            "Yuva",
            "Rüya"
        };
        //Level 3
        Kelimeler[2] = new string[] {
            "Arıza",
            "Balık",
            "Sekiz",
            "Resim",
            "Tatil",
            "Zirve" 
        };
    }

    internal static void Karistir()
    {
        System.Random random = new System.Random();
        Kelimeler[0] = Kelimeler[0].OrderBy(a => random.Next()).ToArray();
        Kelimeler[1] = Kelimeler[1].OrderBy(a => random.Next()).ToArray();
        Kelimeler[2] = Kelimeler[2].OrderBy(a => random.Next()).ToArray();
    }

    //TODO
    internal static bool Ilerle()
    {
        Aktif.Devam.interactable = true;

        if (Bolum == 5)
        {
            //Oyun bitti!
            if (Level == 2)
            {
                //Menuye gönder
                Aktif.Menu.SetActive(true);
                Aktif.Oyun.SetActive(false);
                Aktif.Kaydet();
                return true;
            }

            //Level bitti
            if (Level == SonLevel)
                SonBolum = 0;

            Bolum = 0;
            Level++;
            if (SonLevel != 2 && Level > SonLevel)
                SonLevel++;
        } else
        {
            Bolum++;
            if (Level == SonLevel)
                SonBolum = Bolum;
        }
        //Save data
        Aktif.Kaydet();
        return false;
    }

    public void Yukle()
    {
        if (PlayerPrefs.GetInt("kayit", 0) == 0)
        {
            Devam.interactable = false;
            return;
        }
        Devam.interactable = true;
        SonLevel = PlayerPrefs.GetInt("sonLevel", 0);
        SonBolum = PlayerPrefs.GetInt("sonBolum", 0);
        Oyuncu = PlayerPrefs.GetString("oyuncu", "");
        isim.text = Oyuncu;
    }

    public void Kaydet()
    {
        PlayerPrefs.SetInt("kayit", 1);
        PlayerPrefs.SetInt("sonLevel", SonLevel);
        PlayerPrefs.SetInt("sonBolum", SonBolum);
        PlayerPrefs.SetString("oyuncu", Oyuncu);
        PlayerPrefs.Save();
    }

    public void Resetle()
    {
        SonLevel = 0;
        SonBolum = 0;
        PlayerPrefs.SetInt("kayit", 0);
        PlayerPrefs.SetInt("sonLevel", 0);
        PlayerPrefs.SetInt("sonBolum", 0);
        PlayerPrefs.Save();
    }

    public void Skor(int skor)
    {
        if (skor >= PlayerPrefs.GetInt(Kelimeler[Level][Bolum] + "_ykskor", -1))
        {
            PlayerPrefs.SetString(Kelimeler[Level][Bolum] + "_ykoyuncu", Oyuncu);
            PlayerPrefs.SetInt(Kelimeler[Level][Bolum] + "_ykskor", skor);
        }
    }

    public string YuksekSkor()
    {
        string metin = "";

        if (PlayerPrefs.GetInt(Kelimeler[Level][Bolum] + "_ykskor", -1) != -1)
        {
            metin += PlayerPrefs.GetString(Kelimeler[Level][Bolum] + "_ykoyuncu", "") + ": ";
            metin += PlayerPrefs.GetInt(Kelimeler[Level][Bolum] + "_ykskor", -1);
        }
        return metin;
    }

    public void SkorResetle()
    {
        PlayerPrefs.SetInt(Kelimeler[Level][Bolum] + "_ykskor", -1);
    }
}
