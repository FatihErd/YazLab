<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Login.aspx.cs" Inherits="yazlab.Login" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous"
    />
    <link rel="stylesheet" >
    <title>Giriş Sayfası</title>
    <style>
        .btn-color {
    background-color: cadetblue;
}

.link-type {
    color: cadetblue;
    font-size: large;
}

.giris-box {
    width: 25%;
    border: 1px solid cadetblue;
}

@media screen and (max-width: 992px) {
    .giris-box {
        width: 50%;
    }
}

@media screen and (max-width: 768px) {
    .giris-box {
        width: 75%;
    }
}

@media screen and (max-width: 576px) {
    .giris-box {
        width: 100%;
    }
}

    </style>
  </head >
  <body background="library53.jpg">
    <div class="card w-25 mx-auto my-5 giris-box" style="left: 10px; top: 134px; margin-bottom: 0px">
      <div class="card-header h5">Kullanıcı Girişi</div>
      <div class="card-body">
          <form id="form1" runat="server">
          <div class="form-group">
            <label for="name">Kullanıcı Adı:</label>
              <br />
              <asp:TextBox ID="TextBox1" runat="server" class="w-100"></asp:TextBox>
<label for="password" class="mt-2">Şifre:</label>
              <br />
              <asp:TextBox ID="TextBox2" runat="server" class="w-100" TextMode="Password" ></asp:TextBox>
              <br />
              <asp:Label  ID="Label1" runat="server" Class="text-danger"  Text="Lütfen kullanıcı bilgilerinizi giriniz." Font-Size="Small"></asp:Label>
              </div>
        
              <asp:Button ID="Button1" runat="server"  OnClick="Button1_Click1" Text="Giriş"  CssClass="btn text-light  btn-info"  />
          </form>
      </div>
    </div>
      
    <script
      src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
      integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
      integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
      integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
      crossorigin="anonymous"
    ></script>
  </body>
</html>