// 验证用户登录时输入的信息是否填写完整
function submitForm() {
    // 获得账号
    let account = document.getElementsByName("account")[0].value;
    // 获得密码
    let password = document.getElementsByName("password")[0].value;
    // 获得用户类型
    let typeArray = document.getElementsByName("type");
    // 获得选中的用户类型
    let typeValue = null;
    for (let i = 0; i < typeArray.length; i++) {
        if (typeArray[i].checked) {
            typeValue = typeArray[i].value;
        }
    }
    // 判断是否有空输入
    if (account != null && account !== "" && password != null && password !== "" && typeValue != null) {
        let form = document.getElementById('loginForm');
        form.submit();
    } else {
        alert("输入均不能为空！")
    }
}