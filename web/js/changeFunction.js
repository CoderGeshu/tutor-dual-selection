// 获得功能页面的框架
let iframe = document.getElementById('myIframe');

// 设置功能页面要显示内容的url
function changeFunction(url) {
    iframe.setAttribute('src', url);
}

// 学生查看我的导师，当没有导师时不能进入
function myTutor(tno, url) {
    if (tno === "" || tno == null) {
        alert('您还未分配导师，请先去填报导师志愿或等待志愿审核！');
        return false;
    } else {
        iframe.setAttribute('src', url);
    }
}

// 学生对志愿表的操作：填报志愿或查看志愿
function voluntaryOperation(tno, url) {
    if (tno !== "" && tno != null) {
        alert('您已经拥有导师，不可再次进入此页面！');
        return false;
    } else {
        iframe.setAttribute('src', url);
    }
}

// 修改密码
function modifyPassword(password) {
    let pwd = prompt("请输入您的密码，以确保为本人操作！");
    if (pwd !== "" && pwd != null && pwd === password) {
        iframe.setAttribute('src', 'modifyPassword.jsp');
    } else if (pwd !== "" && pwd != null && pwd !== password) {
        alert('密码输入有误！');
    } else {
        return false;
    }
}

// 退出系统
function exit() {
    let exit = confirm('确认退出本系统？');
    if (exit) {
        // sessionStorage.clear();   //清除所有session值
        window.location = "login.jsp";
    }
}