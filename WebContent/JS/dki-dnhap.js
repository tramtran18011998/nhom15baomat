
function validateFormDNhap()
{
	var vali= /[$\\\#%\^\&\*\(\)\[\]\+\{\}\`\~\=\|]/;
	
	
    var username = document.getElementById('tenDN').value;
    var password = document.getElementById('mk').value; 

    if(username.length==0 && password.length==0){
        alert('Vui lòng nhập thông tin');
    }

    
    else if (username.length==0){
        alert('Bạn chưa nhập tên đăng nhập');
    
    }
    else if (password == '')
    {
        alert('Bạn chưa nhập mật khẩu');
    }
    else if(username.match(vali) || password.match(vali)){
	    alert('Thông tin không được chứa kí tự [$ # % ^ & * () [] {} + = ` ~ |]');
	    $("#btnLogin").attr("disabled","disabled");
	    return false;
    }else {
    	$('#myModal').modal('hide');
	}
    return true;

}


function validateFormDKy()
{
    var hovaten=document.getElementById('hoTen').value;
    var sdt=document.getElementById('sDt').value;
    var dchi=document.getElementById('DChi').value;
    var mail=document.getElementById('eMail').value;
    var mknhaplai=document.getElementById('MKnhaplai').value;
    var username = document.getElementById('tenDn').value;
    var password = document.getElementById('MK').value;
    
    var min =3;
    var maxDN=20;
    var maxMK=15;

    if(mknhaplai.length > 0 && password.length>0 && mknhaplai != password)
    {
        alert('Xác nhận mật khẩu sai. Vui lòng nhập lại');
    }
    else if(hovaten.length==0 || sdt.length==0 ||dchi.length==0 || mail.length==0 || username.length==0 ){
        alert('Vui lòng nhập đầy đủ thông tin đăng ký');
    }
    else if(username.length>maxDN || username.length<min || password.length>maxMK || password.length<min)
    {
        alert('Bạn phải nhập Tên đăng nhập trong khoảng [3,20], Mật khẩu [3,15] kí tự');
    }
    else{
        swal({
            title: "Đăng ký thành công !",
            
            icon: "success",
          });
        $('#myModal1').modal('hide');
    }

}


function validateQuenMK(){
    var email=document.getElementById('email').value;
    var code=document.getElementById('code').value;
    var mk=document.getElementById('newpassword').value;
    var mk2=document.getElementById('newpassword2').value;

    if( mk.length>0 && mk2.length>0 && mk!=mk2 ){
        alert('Xác nhận sai mật khẩu. Vui lòng nhập lại');
    }
    else if(email.length==0 || code.length==0 || mk.length==0 || mk2.length==0){
        alert('Vui lòng nhập đầy đủ thông tin');
    }
    else{
        $('#myModalQuenMK').modal('hide');      
    }
    
}

function checkCharacter(){
	
	var hovaten=document.getElementById('hoTen').value;
    var sdt=document.getElementById('sDt').value;
    var dchi=document.getElementById('DChi').value;
    var mail=document.getElementById('eMail').value;
    var mknhaplai=document.getElementById('MKnhaplai').value;
    var username = document.getElementById('tenDn').value;
    var password = document.getElementById('MK').value;
	
    
    var vali= /[$\\\#%\^\&\*\(\)\[\]\+\{\}\`\~\=\|]/;
    
	
	if(hovaten.match(vali) || sdt.match(vali) || dchi.match(vali) || mail.match(vali) || mknhaplai.match(vali) || username.match(vali) || password.match(vali))
	{
	    alert('Thông tin không được chứa kí tự [$ # % ^ & * () [] {} + = ` ~ |]');
	    $("#btnDK").attr("disabled","disabled");
	    return false;
	}	
	return true;
}
