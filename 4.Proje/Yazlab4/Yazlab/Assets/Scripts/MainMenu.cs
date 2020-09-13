using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MainMenu : MonoBehaviour
{
    public GameObject LevelSecim;

    public void Baslat()
    {
        transform.GetComponentInParent<AudioSource>().PlayOneShot(Manager.Aktif.ClickSes);
        LevelSecim.SetActive(true);
        gameObject.SetActive(false);
    }

    public void YeniOyun()
    {
        Manager.Karistir();
        Manager.Oyuncu = Manager.Aktif.isim.text;
        Manager.Aktif.Resetle();
        Baslat();
    }
}
