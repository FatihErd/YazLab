<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Admin.aspx.cs" Inherits="yazlab.Admin" %>

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
    <link rel="stylesheet" href="library.css">
    <title>Admin Sayfası</title>
  </head>
  <body background="library53.jpg">
      <form id="form1" runat="server">
    <div class="card mx-auto my-5 w-50">
      <div class="card-header h4">Admin Sayfası</div>
        <div class="card-body">
            <div id="accordion">


                <div class="card mt-1">
                  <div class="card-header">
                    <a href="#collapseOne" class="card-link text-info h5" data-toggle="collapse">
                        <b>
                            Kitap Ekle
                        </b>
                      </a>
                     
                  </div>
                  <div id="collapseOne" class="collapse" data-parent="#accordion">
                      <div class="card-body border border-dark">
                          <asp:Label ID="Label1" runat="server" Text="Kitap Adı:"></asp:Label>
                          <br />
                          <asp:TextBox ID="TextBox1" Class="mb-2" runat="server"></asp:TextBox>
                          <br />
                          <asp:Label ID="Label2" runat="server" Class="mt-2" Text="Kitap Isbn:"></asp:Label>
                          <br />
                          <asp:TextBox ID="TextBox2" runat="server"></asp:TextBox>
                          <br />
                          <br />
                          <asp:FileUpload ID="FileUpload1" CssClass="w-75 btn text-light  btn-outline-info" runat="server"  />
                          <br />
                          <asp:Label ID="Label3" runat="server"  Class="text-danger" Text="."></asp:Label>
                          <br />
                          <asp:Button ID="Button3" runat="server" CssClass="btn text-light  btn-info" Text="Yükle" OnClick="Button3_Click" />
                          <br />
                          <asp:Label ID="Label4" runat="server"  Class="text-danger" Text="."></asp:Label>
                    
                          <br />
                          <asp:Button ID="Button2" runat="server" Text="Kaydet" CssClass="btn text-light  btn-info" OnClick="Button2_Click" />
                    
                      </div>
          
                  </div>
                </div>
          
          
                <div class="card mt-1">
                  <div class="card-header">
                    <a href="#collapseTwo" class="card-link text-info h5" data-toggle="collapse">
                      <b>
                          Zaman Atla
                      </b>
                      </a>
                  </div>
                    <div id="collapseTwo" class="collapse" data-parent="#accordion">
                      <div class="card-body p-5 border border-dark table-responsive">
                  
                        <asp:GridView ID="GridView2" class="mb-2" runat="server" CellPadding="4" ForeColor="#333333" GridLines="None" AutoGenerateColumns="False">
                            <AlternatingRowStyle BackColor="White" ForeColor="#284775" />
                            <Columns>
                                <asp:BoundField DataField="username" HeaderText="Kullanıcı_Adı" />
                                <asp:BoundField DataField="kitapad" HeaderText="Kitap_Adı" />
                                <asp:BoundField DataField="kitapIsnb" HeaderText="Kitap_ISBN" />
                                <asp:BoundField DataField="date_format( yazlab2.kitapalan.alinanTarih,'%d-%m-%Y')" HeaderText="Alınan_Tarih" />
                                <asp:BoundField DataField="date_format(yazlab2.kitapalan.verilecekTarih,'%d-%m-%Y')" HeaderText="Verilen_Tarih" />
                         <asp:TemplateField>
                         <ItemTemplate>                      
                       <asp:LinkButton  ID="lnkSelect"   CommandArgument='<%# Eval("username") %>' runat="server" OnClick="lnkSelect_OnClick">Uzat</asp:LinkButton>
                        </ItemTemplate>
                </asp:TemplateField>
                            </Columns>
                            <EditRowStyle BackColor="#999999" />
                            <FooterStyle BackColor="#5D7B9D" Font-Bold="True" ForeColor="White" />
                            <HeaderStyle BackColor="#5D7B9D" Font-Bold="True" ForeColor="White" />
                            <PagerStyle BackColor="#284775" ForeColor="White" HorizontalAlign="Center" />
                            <RowStyle BackColor="#F7F6F3" ForeColor="#333333" />
                            <SelectedRowStyle BackColor="#E2DED6" Font-Bold="True" ForeColor="#333333" />
                            <SortedAscendingCellStyle BackColor="#E9E7E2" />
                            <SortedAscendingHeaderStyle BackColor="#506C8C" />
                            <SortedDescendingCellStyle BackColor="#FFFDF8" />
                            <SortedDescendingHeaderStyle BackColor="#6F8DAE" />
                        </asp:GridView>

                    <asp:Label ID="Label9" class="mb-5 text-danger" runat="server" Text="."></asp:Label>
                          <br />
                          <asp:Label ID="Label10" runat="server" class="mb-5 text-danger" Text=".."></asp:Label>
                  </div>
                    </div>
                </div>
          
                <div class="card mt-1">
                  <div class="card-header">
                    <a href="#collapseThree" class="card-link text-info h5" data-toggle="collapse">
                      <b>
                          Kullanıcıları Listele
                      </b>
                      </a>
                  </div>
                    <div id="collapseThree" class="collapse" data-parent="#accordion">
                     <div class="card-body p-5 border border-dark table-responsive">
               <asp:GridView ID="GridView1" class="mx-auto" runat="server" CellPadding="4" ForeColor="#333333" GridLines="None" AutoGenerateColumns="False">
                            <AlternatingRowStyle BackColor="White" ForeColor="#284775" />
                            <Columns>
                                <asp:BoundField DataField="username" HeaderText="Kullanıcı_Adı" />
                                <asp:BoundField DataField="kitapad" HeaderText="Kitap_Adı" />
                                <asp:BoundField DataField="kitapIsnb" HeaderText="Kitap_ISBN" />
                            </Columns>
                            <EditRowStyle BackColor="#999999" />
                            <FooterStyle BackColor="#5D7B9D" Font-Bold="True" ForeColor="White" />
                            <HeaderStyle BackColor="#5D7B9D" Font-Bold="True" ForeColor="White" />
                            <PagerStyle BackColor="#284775" ForeColor="White" HorizontalAlign="Center" />
                            <RowStyle BackColor="#F7F6F3" ForeColor="#333333" />
                            <SelectedRowStyle BackColor="#E2DED6" Font-Bold="True" ForeColor="#333333" />
                            <SortedAscendingCellStyle BackColor="#E9E7E2" />
                            <SortedAscendingHeaderStyle BackColor="#506C8C" />
                            <SortedDescendingCellStyle BackColor="#FFFDF8" />
                            <SortedDescendingHeaderStyle BackColor="#6F8DAE" />
                        </asp:GridView>
          
                  </div>
                          
              </div>
              <div class="mt-1">
                  <asp:Button ID="Button1" class="btn btn-info btn-block text-light"  runat="server" OnClick="Button1_Click" Text="Çıkış" />
            </div>
            
        </div>
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
      </form>
  </body>
</html>