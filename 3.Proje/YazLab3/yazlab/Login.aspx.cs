using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace yazlab
{
    public partial class Login : System.Web.UI.Page
    {



        protected void Page_Load(object sender, EventArgs e)
        {
        }


        public static MySqlConnection mysqlbaglan = new MySqlConnection("server=localhost;user id=root;database=yazlab2; Pwd = '1234'");


        public static MySqlCommand sorgu;
        public static MySqlDataReader dr;



        protected void Button1_Click(object sender, EventArgs e)
        {
        }

        protected void Button1_Click1(object sender, EventArgs e)
        {
            mysqlbaglan.Close();
            string kullanici = TextBox1.Text;
            string sifre = TextBox2.Text;
            MySqlCommand sorgula = new MySqlCommand("SELECT * FROM user WHERE username=@username AND usersifre=@usersifre ", mysqlbaglan);
            sorgula.Parameters.AddWithValue("@username", kullanici);
            sorgula.Parameters.AddWithValue("@usersifre", sifre);
            //     sorgula.Parameters.AddWithValue("@userkontrol", sayi);
            mysqlbaglan.Open();
            MySqlDataReader oku = sorgula.ExecuteReader();

            if (oku.Read())
            {
                //Session["Kullanici"] = oku["username"].ToString();

                switch (oku["userkontrol"].ToString())
                {
                    case "1":
                        Session["Kullanici"] = oku["username"].ToString();
                        Response.Redirect("Admin.aspx");

                        break;
                    default:
                        Session["Kullanici"] = oku["username"].ToString();
                        Response.Redirect("User.aspx");
                        break;
                }
            }
            else
            {
                Label1.Text = "*Kullanıcı adı yada şifre hatalı!";
            }
            oku.Close();
            mysqlbaglan.Close();
            mysqlbaglan.Dispose();
        }


    }
}
