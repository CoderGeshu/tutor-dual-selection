# tutor-dual-selection 项目

**★★★特别注意：本项目版权所有 [@微信CoderGeshu](https://mp.weixin.qq.com/s/IziWp01QgxlSUUuICP6_FQ)**，可供大家研究学习，如有其它用途，请联系作者。

【课设项目】**基于 JSP 的研究生导师双选系统。**

系统开发及部署报告见 **系统文件** 目录下文件内容。

### 系统功能

每个用户利用自己的角色及相应的账号密码登录系统。

研究生需要选择自己的导师，导师也需要选择学生；管理员需要管理学生、导师的信息数据，并最终确定学生导师关系。每个研究生选导师有三个志愿，而且导师所带学生上限由管理员设定。

本系统的用户及其功能详细要求：

1. 研究生院管理员（超级管理员）端功能

   - 登录系统；
   - 学生基本信息批量导入（单个录入），维护学生信息；
   - 导师基本信息批量导入（单个录入），维护导师信息；
   - 管理（增加，维护）各学院级管理员信息；
   - 查看学生导师关系最终选择情况；
   - 导出学生导师选择志愿表。
   - 维护个人信息；

2. 学院级管理员（权限只限于本学院）端功能

   - 登录系统；
   - 维护学生基本信息（不具备信息导入功能）；
   - 维护导师基本信息（不具备信息导入功能）； 
   - 设定导师所带学生人数的上限；
   - 查看各专业导师、学生信息；
   - 查看学生导师选择志愿信息，最终确定学生导师选择关系；
   - 导出学生导师选择志愿表。
   - 维护个人信息；

3. 学生端功能

   - 登录系统；
   - 查看本专业导师信息（包含导师基本信息，导师现在所带学生人数，导师所带人数剩余名额）；
   - 填写导师志愿表（三个志愿必填），不可选择所带人数已满的导师，当提交志愿表后不可修改；
   - 查看导师志愿表的审核状态（审核中、审核通过、审核未通过）；
   - 导师志愿表审核通过后可以查看最终确定的导师信息；
   - 导师志愿表审核不通过时系统允许重新填写导师志愿表；
   - 维护个人信息（账号不可修改）；

   注：导师所带人数是学院级管理员最终确定关系后的人数，并不等同于选择此导师的学生人数。

4. 导师端功能

   - 登录系统；
   - 查看选择自己的学生信息；
   - 选择所带学生（在自己的数量上限范围内），等待院级管理员的确认；
   - 查看所带学生的信息；
   - 维护个人信息（账号不可修改）。

### 部分功能及页面展示

#### 登录页面

<img src="https://gitee.com/CoderGeshu/pic-go-images/raw/master/img/image-20210216140520237.png" alt="登录页面" style="zoom:80%;" />

#### 个人信息管理

![个人信息管理](https://gitee.com/CoderGeshu/pic-go-images/raw/master/img/image-20210216140833976.png)

#### 设定志愿开放时间

![设定志愿开放时间](https://gitee.com/CoderGeshu/pic-go-images/raw/master/img/image-20210216141400221.png)

#### 学生填报志愿与志愿结果查看

![学生填报志愿](https://gitee.com/CoderGeshu/pic-go-images/raw/master/img/image-20210216141625911.png)

![查看志愿结果](https://gitee.com/CoderGeshu/pic-go-images/raw/master/img/image-20210216141758587.png)

#### 系管理员为学生分配导师

![分配导师](https://gitee.com/CoderGeshu/pic-go-images/raw/master/img/image-20210216142011590.png)

#### 导师处理学生志愿

![导师处理学生志愿](https://gitee.com/CoderGeshu/pic-go-images/raw/master/img/image-20210216142128834.png)

#### 管理师生关系

![管理师生关系](https://gitee.com/CoderGeshu/pic-go-images/raw/master/img/image-20210216142228063.png)

#### 单个导入用户

![单个导入学生信息](https://gitee.com/CoderGeshu/pic-go-images/raw/master/img/image-20210216142325051.png)

### 批量导入用户信息

![批量导入学生信息](https://gitee.com/CoderGeshu/pic-go-images/raw/master/img/image-20210216142415634.png)



**……**

更多功能请阅读开发手册，位于 **系统文件** 目录下。
