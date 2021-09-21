// 修改信息，接收出生日期，电话和邮箱的参数
function modify(birth, tel, email) {
    document.getElementById('birth').innerHTML = "<input type='date' value='" + birth + "'>";
    document.getElementById('tel').innerHTML = "<input type='text' value='" + tel + "'>";
    document.getElementById('email').innerHTML = "<input type='email' value='" + email + "'>";
    document.getElementById('description').readOnly = false;
    document.getElementById('modifyBtn').style.display = 'none';
    document.getElementById('confirmBtn').style.display = 'inline';
    document.getElementById('cancelBackBtn').style.display = 'inline';
}

// 确定修改信息，接收修改者类型：学生或导师，并接收学生学号或导师编号
function confirmModify(type, account) {
    let newBirth = document.getElementById('birth').children[0].value;
    let newTel = document.getElementById('tel').children[0].value;
    let newEmail = document.getElementById('email').children[0].value;
    let newDescription = document.getElementById('description').value;
    if (newBirth === "" || newTel === "" || newEmail === "" || newDescription === "") {
        alert("修改的信息输入不能为空！");
        return false;
    }
    window.location.href = 'userModifyPersonalInfo.do?type=' + type +
        '&account=' + account + '&birth=' + newBirth + '&tel=' + newTel +
        '&email=' + newEmail + '&description=' + newDescription;
}

// 返回操作，返回指定的url页面
function goBack(url) {
    window.location.href = url;
}