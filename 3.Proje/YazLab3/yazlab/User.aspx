<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="User.aspx.cs" Inherits="yazlab.User" %>

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
    <title>Kullanıcı Sayfası</title>
      </head>
  <body background="library53.jpg">
      <form id="form1" runat="server">
    <div class="card mx-auto my-5 w-50">
      <div class="card-header h4">Kullanıcı Sayfası</div>
        <div class="ml-4 mt-2">
            <asp:Label ID="Label3" runat="server" Text="Giriş Yapan Kullanıcı:"></asp:Label>
            <asp:Label ID="Label4" Class="ml-1 text-danger" runat="server" Text="Label" Font-Bold="True"></asp:Label>
        </div>
         <div class="card-body">
            <div id="accordion">


                <div class="card " style="left: 0px; top: 0px">
                  <div class="card-header" >
                    <a href="#collapseOne" class="card-link text-info h5" data-toggle="collapse">
                        <b>
                            Kitap Arama
                        </b>
                      </a>
                     
                  </div>
                  <div id="collapseOne" class="collapse" data-parent="#accordion">
                      <div class="card-body border border-dark">
                          <asp:Label ID="Label1" runat="server" Text="Kitap Adı:"></asp:Label>
                          <br />
                          <asp:TextBox ID="TextBox1"  runat="server"></asp:TextBox>
                          <br />
                          <asp:Button ID="Button2" runat="server" CssClass="btn text-light  btn-info mt-2 mb-4"  Text="İsime Göre Ara" OnClick="Button2_Click" />
                          <br />
                          <asp:Label ID="Label2" runat="server" Class="mt-2" Text="Kitap Isbn:"></asp:Label>
                          <br />
                          <asp:TextBox ID="TextBox2" runat="server" Class="mt-2" Height="27px" Width="188px"></asp:TextBox>
                          
                          <br />
                          <asp:Button ID="Button3" runat="server" CssClass="btn text-light mb-4 mt-2 btn-info" Text="Isbn Göre Ara" OnClick="Button3_Click" />
                         
                    
                          <br />
                           <asp:GridView ID="GridView1" runat="server" Class="mt-2 " CellPadding="4" ForeColor="#333333" GridLines="None" AutoGenerateColumns="False">
                            <AlternatingRowStyle BackColor="White" ForeColor="#284775" />
                            <Columns>
                                <asp:BoundField DataField="idkitap" HeaderText="Kitap_ID" />
                                <asp:BoundField DataField="kitapad" HeaderText="Kitap_ADI" />
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
                </div>
          
          
                 <div class="card mt-1">
                  <div class="card-header">
                    <a href="#collapseTwo" class="card-link text-info h5" data-toggle="collapse">
                      <b>
                          Kitap Alma
                      </b>
                      </a>
                  </div>
                    <div id="collapseTwo" class="collapse" data-parent="#accordion">
                      <div class="card-body p-5 border border-dark table-responsive">
                  <asp:GridView ID="GridView3" runat="server" CellPadding="4" ForeColor="#333333" GridLines="None" AutoGenerateColumns="False">

                            <AlternatingRowStyle BackColor="White" ForeColor="#284775" />
                            <Columns>
                                <asp:BoundField DataField="idkitap" HeaderText="Kitap_ID" />
                                <asp:BoundField DataField="kitapad" HeaderText="Kitap_ADI" />
                                <asp:BoundField DataField="kitapIsnb" HeaderText="Kitap_ISBN" />

                                 <asp:TemplateField>
                   <ItemTemplate>
                      
                       <asp:LinkButton Text="AL" ID="lnkSelect"   CommandArgument='<%# Eval("idkitap") %>' runat="server" OnClick="lnkSelect_OnClick">Kitap AL</asp:LinkButton>
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
                        

                          <asp:Label ID="Label6" runat="server" Text=".."></asp:Label>
                          <br />
                          <asp:Label ID="Label10" runat="server" Text=".."></asp:Label>
                  </div>
                    </div>
                </div>
          
                <div class="card mt-1">
                  <div class="card-header">
                    <a href="#collapseThree" class="card-link text-info h5" data-toggle="collapse">
                        <b>
                            Üzerinizdeki Kitabı Geri Verme
                        </b>
                      </a>
                     
                  </div>
                    <div id="collapseThree" class="collapse" data-parent="#accordion">
                     <div class="card-body p-5 border border-dark table-responsive">
                      
                                   <asp:GridView ID="GridView2" runat="server" CellPadding="4" GridLines="None" AutoGenerateColumns="False" ForeColor="#333333">
                            <AlternatingRowStyle BackColor="White" ForeColor="#284775" />
                            <Columns>
                                <asp:BoundField DataField="username" HeaderText="Kullanıcı_Adı" />
                                <asp:BoundField DataField="kitapad" HeaderText="Kitap_AD" />
                                <asp:BoundField DataField="kitapIsnb" HeaderText="Kitap_ISBN" />
                            </Columns>
                                       <EditRowStyle BackColor="#999999" />
                            <FooterStyle BackColor="#5D7B9D" ForeColor="White" Font-Bold="True" />
                            <HeaderStyle BackColor="#5D7B9D" Font-Bold="True" ForeColor="White" />
                            <PagerStyle BackColor="#284775" ForeColor="White" HorizontalAlign="Center" />
                            <RowStyle BackColor="#F7F6F3" ForeColor="#333333" />
                            <SelectedRowStyle BackColor="#E2DED6" Font-Bold="True" ForeColor="#333333" />
                            <SortedAscendingCellStyle BackColor="#E9E7E2" />
                            <SortedAscendingHeaderStyle BackColor="#506C8C" />
                            <SortedDescendingCellStyle BackColor="#FFFDF8" />
                            <SortedDescendingHeaderStyle BackColor="#6F8DAE" />
                        </asp:GridView>
                         <asp:FileUpload ID="FileUpload1" CssClass="w-75 btn text-light  btn-outline-info mt-2" runat="server"  />
                                   <br />
                                   <asp:Button ID="Button6" runat="server" Class="btn btn-info my-2" Text="Yükle" OnClick="Button6_Click" />
                         <br />
                         <asp:TextBox ID="TextBox3" CssClass="my-2"  runat="server"></asp:TextBox>
                         <br />
                         <asp:Button ID="Button5" runat="server" Class="btn btn-info" OnClick="Button5_Click" Text="Geri Ver" />
                         <br />
                         <asp:Label ID="Label9" runat="server" CssClass="text-danger" Text=".."></asp:Label>
                         <br />
          
                  </div>
                          
              </div>
              <div class="mt-1">
                  <asp:Button ID="Button1" class="btn btn-info btn-block text-light mt-2"  runat="server" OnClick="Button1_Click" Text="Çıkış" />
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