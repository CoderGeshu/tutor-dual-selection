function modify(aname, tel) {
    document.getElementById('aname').innerHTML = "<input type='text' value='" + aname + "'>";
    document.getElementById('sex').innerHTML = "<select><option>男</option><option>女</option></select>";
    document.getElementById('tel').innerHTML = "<input type='text' value='"+ tel +"'>";
    document.getElementById('modifyBtn').style.display = 'none';
    document.getElementById('confirmBtn').style.display = 'inline';
    document.getElementById('cancelBackBtn').style.display = 'inline';
}

function confirmModify(ano) {
    let newAname = document.getElementById('aname').children[0].value;
    let newSex = document.getElementById('sex').children[0].value;
    let newTel = document.getElementById('tel').children[0].value;
    if (newTel === "") {
        alert("修改的信息输入不能为空！");
        return false;
    }
    window.location.href = 'admin_personalInfoCtrl.jsp?ano='+ano+'&aname=' + newAname + '&sex=' + newSex + '&tel=' + newTel;
}

function goBack() {
    window.location.href = 'admin_personalInfo.jsp';
}