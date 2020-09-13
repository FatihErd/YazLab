using UnityEngine;
using UnityEngine.UI;

public class Seviye : MonoBehaviour
{
    public GameObject Menu;
    public GameObject SeviyePanel;
    public GameObject AltSeviye;
    private void OnEnable()
    {
        int childCount = SeviyePanel.transform.childCount;
        for (int i = 0; i < childCount; i++)
        {
            SeviyePanel.transform.GetChild(i).GetComponent<Button>().interactable = (i <= Manager.SonLevel);
        }
    }

    public void Seviye1()
    {
        Manager.Level = 0;
        gameObject.SetActive(false);
        AltSeviye.SetActive(true);
        transform.GetComponentInParent<AudioSource>().PlayOneShot(Manager.Aktif.ClickSes);
    }

    public void Seviye2()
    {
        Manager.Level = 1;
        gameObject.SetActive(false);
        AltSeviye.SetActive(true);
        transform.GetComponentInParent<AudioSource>().PlayOneShot(Manager.Aktif.ClickSes);
    }

    public void Seviye3()
    {
        Manager.Level = 2;
        gameObject.SetActive(false);
        AltSeviye.SetActive(true);
        transform.GetComponentInParent<AudioSource>().PlayOneShot(Manager.Aktif.ClickSes);
    }

    public void Geri()
    {
        Menu.SetActive(true);
        gameObject.SetActive(false);
        transform.GetComponentInParent<AudioSource>().PlayOneShot(Manager.Aktif.ClickSes);
    }
}
