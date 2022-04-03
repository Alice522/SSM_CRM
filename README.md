# 关于项目

该项目是基于SSM实现的客户关系管理系统，在动力节点[crm项目](https://www.bilibili.com/video/BV1tZ4y1d7kg)基础上优化改进<br/>使用thymeleaf代替jsp，并且加入了vue，适合初学SSM的同学学习。

## 技术栈

Spring+SpringMVC+MyBatis+Thymeleaf+Vue+JS+Jquery+Axios+Json

服务器： Tomcat_9.0.60

数据库： MySQL_8.0.28

开发工具：IDEA_2021.3.3

页面：Bootstrap_3.3.0

## 已实现的功能

 **:star: 账号登录**

## 日志

### 2022/4/2 - 实现基本的登录功能:

    1. 使用@RequestBody接收Axios传来的Json对象时，可以用一个Map对象接收，也可以用一个POJO类对象接收

    2. 使用@RequestBody接收Axios传来的Json对象时，必须设置请求报文中的Content-Type属性为application/json，否则报HTTP：415

    3. 导入js静态文件(vue.js,jquery.js等)时，不要直接使用相对路径，使用thymeleaf或者使用绝对路径

### 2022/4/3 - 记住密码，退出登录，验证登录状态:

    1. 使用@RequestBody接收Axios传来的Json对象时，接收到的checked属性为一个Boolean类型，而不是一个String

    2. 在用户不需要记住密码时，调用setMaxAge方法将cookie的生命周期设置为0，即可销毁cookie

    3. 由于使用thymeleaf暂时没找到操作cookie的方法，故此导入vue-cookie包，使用vue操作cookie
    
    4. 在vue中，不能用$cookies.get('loginAct') && $cookies.get('loginPwd')为checked赋值，此时值为$cookies.get('loginPwd')。应该用!!($cookies.get('loginAct') && $cookies.get('loginPwd'))，此时值为一个布尔类型。
