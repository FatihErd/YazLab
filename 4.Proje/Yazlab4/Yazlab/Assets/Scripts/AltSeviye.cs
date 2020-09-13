using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class AltSeviye : MonoBehaviour
{
    public GameObject Seviye;
    public Text Title;
    public GameObject Panel;
    public GameObject Oyun;

    void Start()
    {
        for (int i = 0; i < Panel.transform.childCount; i++)
        {
            var buton = Panel.transform.GetChild(i).GetComponent<Button>();
            if (Manager.Level == Manager.SonLevel && i > Manager.SonBolum)
                buton.interactable = false;
            buton.onClick.AddListener(() => BolumBaslat(int.Parse(buton.name)));
        }
    }

    private void OnEnable()
    {
        for (int i = 0; i < Panel.transform.childCount; i++)
        {
            var buton = Panel.transform.GetChild(i).GetComponent<Button>();
            buton.interactable = true;
            if (Manager.Level == Manager.SonLevel && i > Manager.SonBolum)
                buton.interactable = false;
        }
        Title.text = $"{Manager.Level +1}. Bölüm";
    }

    void BolumBaslat(int i)
    {
        Manager.Bolum = i - 1;

        gameObject.SetActive(false);
        Oyun.SetActive(true);
        transform.GetComponentInParent<AudioSource>().PlayOneShot(Manager.Aktif.ClickSes);
    }

    public void Geri()
    {
        Seviye.SetActive(true);
        gameObject.SetActive(false);
        transform.GetComponentInParent<AudioSource>().PlayOneShot(Manager.Aktif.ClickSes);
    }
}
