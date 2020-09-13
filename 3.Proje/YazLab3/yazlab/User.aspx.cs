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
    public partial class User : System.Web.UI.Page
    {
       public static MySqlConnection mysqlbaglan = new MySqlConnection("server=localhost;user id=root;database=yazlab2; Pwd = '1234'");

        protected void kitapVerme()
        {
            MySqlCommand listele = new MySqlCommand("SELECT yazlab2.user.username,yazlab2.kitap.kitapad,yazlab2.kitap.kitapIsnb FROM yazlab2.kitapalan  JOIN yazlab2.kitap ON yazlab2.kitapalan.idkitap = yazlab2.kitap.idkitap JOIN yazlab2.user ON yazlab2.kitapalan.iduser = yazlab2.user.iduser where yazlab2.user.username ='" + Session["kullanici"] + "' ", mysqlbaglan);
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

        protected void kitapAlma()
        {

            MySqlCommand listele = new MySqlCommand("select yazlab2.kitap.idkitap,yazlab2.kitap.kitapad,yazlab2.kitap.kitapIsnb from yazlab2.kitap  left JOIN yazlab2.kitapalan ON yazlab2.kitap.idkitap = yazlab2.kitapalan.idkitap  WHERE yazlab2.kitapalan.idkitap IS NULL ", mysqlbaglan);
            try
            {
                mysqlbaglan.Open();
                MySqlDataReader oku = listele.ExecuteReader();
                GridView3.DataSource = oku;
                GridView3.DataBind();
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


        protected void kitapArama()
        {

            MySqlCommand listele = new MySqlCommand("SELECT*FROM yazlab2.kitap ", mysqlbaglan);
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



        string userid;
        protected void useridbul()
        {
            MySqlCommand listele2 = new MySqlCommand("select yazlab2.user.iduser from yazlab2.user where yazlab2.user.username = '" + Session["kullanici"] + "' ", mysqlbaglan);
            try
            {

                mysqlbaglan.Open();

               userid = listele2.ExecuteScalar().ToString();
             //   Label10.Text = userid;

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


        string isnbno;
        protected void kitapidbul()
        {
           
            MySqlCommand listele = new MySqlCommand("select yazlab2.kitap.idkitap from yazlab2.kitap where yazlab2.kitap.kitapIsnb = '" + TextBox3.Text + "' ", mysqlbaglan);
            try
            {

                mysqlbaglan.Open();

                isnbno = listele.ExecuteScalar().ToString();
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
  
      
        string count;
        protected void kitapid_count()
        {
            useridbul();
            MySqlCommand usercount = new MySqlCommand("SELECT COUNT(yazlab2.kitapalan.iduser)FROM yazlab2.kitapalan where yazlab2.kitapalan.iduser='"+userid+"'", mysqlbaglan);
            try
            {
                mysqlbaglan.Open();
                count = usercount.ExecuteScalar().ToString();
            //   Label10.Text = count;
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
       
        string ktarih;
        protected void kişiTarih_Kontrol ()
        {
            useridbul();
            MySqlCommand tarihsayi = new MySqlCommand("select count(yazlab2.kitapalan.verilecekTarih)" +"from yazlab2.kitapalan where yazlab2.kitapalan.iduser = '"+userid+ "' and datediff(yazlab2.kitapalan.verilecekTarih, CURDATE()) < 0 ", mysqlbaglan);
            try
            {
                mysqlbaglan.Open();
                ktarih = tarihsayi.ExecuteScalar().ToString();

             //   Label10.Text = ktarih;
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

        string var;
        protected void kisikitapsahipmi()
        {
            //useridbul();
            kitapidbul();

            MySqlCommand tarihsayi = new MySqlCommand("select count(yazlab2.kitapalan.idkitap) from yazlab2.kitapalan WHERE yazlab2.kitapalan.idkitap='" + isnbno + "' and yazlab2.kitapalan.iduser='" + userid + "' ", mysqlbaglan);
            try
            {
                mysqlbaglan.Open();
                var = tarihsayi.ExecuteScalar().ToString();

                //   Label10.Text = ktarih;
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







        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["Kullanici"] != null)
            {
                Label4.Text = " "+Session["kullanici"];
            }
            else
            {
                Response.Redirect("Login.aspx");
            }
            useridbul();
            kitapArama();
            kitapAlma();
            kitapVerme();
            TextBox3.Enabled = false;
        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            Session.Abandon(); //sessionu siliyoruz.
            Response.Redirect("Login.aspx");
        }



        protected void Button2_Click(object sender, EventArgs e) //kitap adına göre arama
        {
            MySqlCommand listele = new MySqlCommand("select* from yazlab2.kitap where  kitapad  LIKE '%" + TextBox1.Text + "%' ", mysqlbaglan);
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

        protected void Button3_Click(object sender, EventArgs e) //kitap nosuna göre arama
        {
            MySqlCommand listele = new MySqlCommand("select* from yazlab2.kitap where  kitapIsnb  LIKE '%" + TextBox2.Text + "%' ", mysqlbaglan);
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

        protected void Button5_Click(object sender, EventArgs e)//kitap silme
        {
           // useridbul();
        //    kitapidbul();
            kisikitapsahipmi();

            if (var != "0")
            {

            
            MySqlCommand listele = new MySqlCommand("DELETE FROM yazlab2.kitapalan WHERE yazlab2.kitapalan.idkitap='" + isnbno + "' and yazlab2.kitapalan.iduser='" + userid + "' ", mysqlbaglan);
            try
            {
                mysqlbaglan.Open();
                MySqlDataReader oku = listele.ExecuteReader();
                GridView1.DataSource = oku;
                GridView1.DataBind();
                oku.Close();
                Label9.Text = "Başarılı";
                TextBox3.Text = "";
            }
            catch (Exception hata)
            {
                Response.Write("hata:" + hata.Message);
              
            }
            finally
            {
                mysqlbaglan.Close();
            }
                kitapVerme();
                kitapArama();
                kitapAlma();

            }
            else
            {
                Label9.Text = "Üzerinizde böyle bir kitap bulunmamakta";
            }

        }

        protected void lnkSelect_OnClick(object sender, EventArgs e) //kitap alma
        {
            useridbul();
            kitapid_count();
            kişiTarih_Kontrol();
            // Label6.Text = count;

            var ata = ((GridViewRow)(sender as LinkButton).NamingContainer).RowIndex;
            int linkkitapid=Int32.Parse(GridView3.Rows[ata].Cells[0].Text);
            //Label6.Text = linkkitapid.ToString();
            
              if (Int32.Parse( ktarih )<= 0)
              {
               // Label10.Text = ktarih;
                if (Int32.Parse(count) < 3)
                    {
                  //  Label10.Text = count;
                     
                      MySqlCommand listele = new MySqlCommand("INSERT INTO yazlab2.kitapalan VALUES ('"+userid+"','"+linkkitapid+"',CURDATE(),adddate(curdate(),interval 7 day))", mysqlbaglan);
                      try
                      {
                          mysqlbaglan.Open();
                          MySqlDataReader oku = listele.ExecuteReader();
                          GridView1.DataSource = oku;
                          GridView1.DataBind();
                          oku.Close();
                          Label6.Text = "Başarılı ile eklendi";
                      }
                      catch (Exception hata)
                      {
                          Response.Write("hata:" + hata.Message);
                      }
                      finally
                      {
                          mysqlbaglan.Close();
                      }
                      kitapVerme();
                      kitapArama();
                      kitapAlma();
                    

                  }
                  else
                  {
                      Label6.Text = "3 Kitaptan Fazla alamazsınız!";
                  }
              }
              else
              {
                  Label6.Text =ktarih +" adet veriliş tarihi Geçmiş kitaplar bulunmakta ";
              }
              



        }

        protected void Button6_Click(object sender, EventArgs e)
        {
            if (FileUpload1.HasFile)
            {
                string ext = System.IO.Path.GetExtension(FileUpload1.FileName);//uzantı
                if (ext != ".jpg" && ext != ".png" && ext != ".jpeg")
                {
                    Label9.Text = "Geçersiz dosya";
                    return;

                }

                string path = Server.MapPath("/image/");
                FileUpload1.SaveAs(path + FileUpload1.FileName);


                /* https://ironsoftware.com/csharp/ocr/  ocr için */

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
                Label9.Text = Result.Text;

                String[] kelime = Result.Text.Split(' ', '\n', '\r', '"');
                int a = 0;
                for (int i = 0; i < kelime.Length; i++)
                {
                    if (kelime[i] == "ISBN" || kelime[i] == "ısbn" || kelime[i] == "|SBN")
                    {
                        Label9.Text = kelime[i + 1];
                        TextBox3.Text = kelime[i + 1];
                        a = 1;
                    }

                }
                if (a == 0)
                {
                    Label9.Text = "ISBN bulunamadı";
                }
            }
            else
            {
                Label9.Text = "Dosya seçimi yapmadınız";
                return;
            }
        }
    }
}