using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using IronOcr;

namespace yazlab
{
    public partial class Admin : System.Web.UI.Page
    {
        public static MySqlConnection mysqlbaglan = new MySqlConnection("server=localhost;user id=root;database=yazlab2; Pwd = '1234'");

        public static string RecursiveHtmlDecode(string str)
        {
            //https://stackoverflow.com/questions/2720684/c-function-to-replace-all-html-special-characters-with-normal-text-characters/53052856#53052856  Alıntı//

            if (string.IsNullOrWhiteSpace(str)) return str;
            var tmp = HttpUtility.HtmlDecode(str);
            while (tmp != str)
            {
                str = tmp;
                tmp = HttpUtility.HtmlDecode(str);
            }
            return str; 
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["Kullanici"] != null)
            {
             //   Label1.Text = "Hoşgeldin " + Session["kullanici"];
            }
            else
            {
                Response.Redirect("Login.aspx");
            }

            kayitListele();
            kitapaAlanlar();
            TextBox2.Enabled = false;
        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            Session.Abandon(); //sessionu siliyoruz.
            Response.Redirect("Login.aspx");
        }





        protected void kayitListele() // kullanııcı listeleme
        {
            MySqlCommand listele = new MySqlCommand("SELECT yazlab2.user.username  ,  yazlab2.kitap.kitapad,yazlab2.kitap.kitapIsnb FROM yazlab2.kitapalan JOIN yazlab2.kitap ON yazlab2.kitapalan.idkitap = yazlab2.kitap.idkitap RIGHT JOIN yazlab2.user ON yazlab2.kitapalan.iduser = yazlab2.user.iduser", mysqlbaglan);
            try
            {
                mysqlbaglan.Open();
                MySqlDataReader oku = listele.ExecuteReader();
                GridView1.DataSource = oku;
                GridView1.DataBind();
                oku.Close();
            }
            catch (Exception hata)
            {
                Response.Write("hata:" + hata.Message);
            }
            finally
            {
                mysqlbaglan.Close();
            }
        }
        protected void kitapaAlanlar() // kitap alanlar tablosu
        {
            MySqlCommand listele = new MySqlCommand(" SELECT yazlab2.user.username,yazlab2.kitap.kitapad,yazlab2.kitap.kitapIsnb,date_format( yazlab2.kitapalan.alinanTarih,'%d-%m-%Y'),date_format(yazlab2.kitapalan.verilecekTarih,'%d-%m-%Y') FROM yazlab2.kitapalan JOIN yazlab2.kitap ON yazlab2.kitapalan.idkitap = yazlab2.kitap.idkitap JOIN yazlab2.user ON yazlab2.kitapalan.iduser = yazlab2.user.iduser", mysqlbaglan);
            try
            {
                mysqlbaglan.Open();
                MySqlDataReader oku = listele.ExecuteReader();
                GridView2.DataSource = oku;
                GridView2.DataBind();
                oku.Close();
            }
            catch (Exception hata)
            {
                Response.Write("hata:" + hata.Message);
            }
            finally
            {
                mysqlbaglan.Close();
            }
        }

        protected void Button2_Click(object sender, EventArgs e)
        {
            try
            {
                
                mysqlbaglan.Open();
                
                MySqlCommand ekle = new MySqlCommand("insert into yazlab2.kitap(kitapad,kitapIsnb) VALUES ('" + TextBox1.Text + "','" + TextBox2.Text + "')", mysqlbaglan);
                
                object sonuc = null;
                sonuc = ekle.ExecuteNonQuery(); // sorgu çalıştı ve dönen değer objec türünden değişkene geçti eğer değişken boş değilse eklendi boşşsa eklenmedi.
                if (sonuc != null)
                {
                    Label4.Text = ("Sisteme başarıyla eklendi");
                    TextBox1.Text = "";
                    TextBox2.Text = "";
                }

                else
                    Label3.Text = ("Sisteme başarıyla eklenmedi");
                // bağlantıyı kapatalım
                mysqlbaglan.Close();
            }
            catch (Exception HataYakala)
            {
                Response.Write("hata:" + HataYakala.Message);
            }
        }




        protected void Button3_Click(object sender, EventArgs e)
        {
            Label3.Text = " ";
            if (FileUpload1.HasFile)
            {
                string ext = System.IO.Path.GetExtension(FileUpload1.FileName);//uzantı
                if (ext != ".jpg" && ext != ".png" && ext != ".jpeg")
                {
                    Label3.Text = "Geçersiz dosya";
                    return;

                }

                string path = Server.MapPath("/image/");
                FileUpload1.SaveAs(path + FileUpload1.FileName);

                //https://ironsoftware.com/csharp/ocr/ Alıntı

                var Ocr = new AdvancedOcr()
                {

                    CleanBackgroundNoise = true,
                    EnhanceContrast = true,
                    EnhanceResolution = true,
                    Language = IronOcr.Languages.Turkish.OcrLanguagePack,
                    Strategy = IronOcr.AdvancedOcr.OcrStrategy.Advanced,
                    ColorSpace = AdvancedOcr.OcrColorSpace.Color,
                    DetectWhiteTextOnDarkBackgrounds = true,
                    InputImageType = AdvancedOcr.InputTypes.Document,
                    RotateAndStraighten = true,
                    ReadBarCodes = true,
                    ColorDepth = 4
                };
                var Result = Ocr.Read(path + FileUpload1.FileName);
                Label3.Text = Result.Text;

                String[] kelime = Result.Text.Split(' ', '\n', '\r', '"');
                int a = 0;
                for (int i = 0; i < kelime.Length; i++)
                {
                    if (kelime[i] == "ISBN" || kelime[i] == "ısbn" || kelime[i] == "|SBN")
                    {
                        Label3.Text = kelime[i + 1];
                        TextBox2.Text = kelime[i + 1];
                        a = 1;
                    }

                }
                if (a == 0)
                {
                    Label3.Text = "ISBN bulunamadı";
                }
            }
            else
            {
                Label3.Text = "Dosya seçimi yapmadınız";
                return;
            }



        }


        string k_id;
        protected void lnkSelect_OnClick(object sender, EventArgs e)
        {

          
            var ata = ((GridViewRow)(sender as LinkButton).NamingContainer).RowIndex;

            string k_ad = RecursiveHtmlDecode( GridView2.Rows[ata].Cells[1].Text);
            Label10.Text = k_ad;
            MySqlCommand usercount = new MySqlCommand("select idkitap from yazlab2.kitap where yazlab2.kitap.kitapad='" + k_ad + "'", mysqlbaglan);
                try
                {
                    mysqlbaglan.Open();
                k_id = usercount.ExecuteScalar().ToString();
                      
                }
                catch (Exception hata)
                {
                    Response.Write("hata:" + hata.Message);
                }
                finally
                {
                    mysqlbaglan.Close();
                }

            try
                {
                
               
                mysqlbaglan.Open();
               
                MySqlCommand ekle = new MySqlCommand(" update yazlab2.kitapalan set yazlab2.kitapalan.verilecekTarih = adddate(yazlab2.kitapalan.verilecekTarih,INTERVAL 20 DAY)  WHERE  yazlab2.kitapalan.idkitap ='" +k_id + "'", mysqlbaglan);


                object sonuc = null;
                sonuc = ekle.ExecuteNonQuery(); // sorgu çalıştı ve dönen değer objec türünden değişkene geçti eğer değişken boş değilse eklendi boşşsa eklenmedi.
                if (sonuc != null)
                {
                    Label9.Text = ("Sisteme başarıyla eklendi");

                }

                else
                    Label9.Text = ("Sisteme başarıyla EKLENEMEDİ");
                // bağlantıyı kapatalım
                mysqlbaglan.Close();
            }
            catch (Exception HataYakala)
            {
                Response.Write("hata:" + HataYakala.Message);
            }
            
            kitapaAlanlar();        

          }

        


}
}