# 关于项目

该项目是基于SSM实现的客户关系管理系统，在动力节点[crm项目](https://www.bilibili.com/video/BV1tZ4y1d7kg)基础上优化改进<br/>使用thymeleaf代替jsp，并且加入了vue，适合初学SSM的同学学习。

## 1️⃣技术栈

Spring+SpringMVC+MyBatis+Thymeleaf+Vue+JS+Jquery+Axios+Json

服务器： Tomcat_9.0.60

数据库： MySQL_8.0.28

开发工具：IDEA_2021.3.3

页面：Bootstrap_3.3.0

## 2️⃣已实现的功能

 **:star: 账号登录**
 
 **:star: 市场活动**

## 3️⃣日志

### 2022/4/2 - 实现基本的登录功能:

    1. 使用@RequestBody接收Axios传来的Json对象时，可以用一个Map对象接收，也可以用一个POJO类对象接收

    2. 使用@RequestBody接收Axios传来的Json对象时，必须设置请求报文中的Content-Type属性为application/json，否则报HTTP：415

    3. 导入js静态文件(vue.js,jquery.js等)时，不要直接使用相对路径，使用thymeleaf或者使用绝对路径

### 2022/4/3 - 记住密码，退出登录，验证登录状态:

    1. 使用@RequestBody接收Axios传来的Json对象时，接收到的checked属性为一个Boolean类型，而不是一个String

    2. 在用户不需要记住密码时，调用setMaxAge方法将cookie的生命周期设置为0，即可销毁cookie

    3. 由于使用thymeleaf暂时没找到操作cookie的方法，故此导入vue-cookie包，使用vue操作cookie
    
    4. 在vue中，不能用$cookies.get('loginAct') && $cookies.get('loginPwd')为checked赋值，此时值为$cookies.get('loginPwd')。应该用!!($cookies.get('loginAct') && $cookies.get('loginPwd'))，此时值为一个布尔类型。

### 2022/4/4 - 工作台主页显示，市场活动主页面显示，创建市场活动:

    1. 输入日期的input标签中应该加上autocomplete="off"，否则历史记录的下拉列表会覆盖日历

    2. vue无法在实现了bootstrap日历功能的input标签上使用v-model进行双向绑定，会出现值消失的情况。建议ref选择标签用value直接获取日期值

    3. bootstrap日历插件汉化包不能正常加载，出现乱码，解决办法：将汉化包中的代码复制到Script标签下
    
### 2022/4/7 - 市场活动的分页查询与展示，条件查询:

    1. 项目视频上有错误，查询市场活动的sql语句中的limit后面应该用${beginNo},${pageSize},而不是#{}.此外limit的起始索引应该为0，而不是1

    2. vue中调用钩子函数beforeMount()，在页面渲染之前向服务器发送axios异步请求，注意：此时页面还没有渲染，所以无法获取页面中的标签

    3. vue中使用v-for，根据列表动态的显示html标签
    
### 2022/4/8 - 翻页功能，全选功能，删除市场活动:

    1. 进行翻页操作的时候，注意要把选中的条目和全选重置

    2. 从axios返回给Java的json对象中的数组是一个List对象而不是一个数组，在controller中强转一下可以直接传入service

    3. Mybatis，foreach标签中的collection属性应该填list，表示的是List类型
    
### 2022/4/9 - 修改市场活动，导出全部市场活动:

### 2022/4/10 - 导入市场活动，市场活动备注的增删改查:

    1. 文件上传用到MultipartFile类，需要引入相关插件并在MVC配置文件中配置multipartResolver解析器bean

    2. 将thymeleaf中的变量传入vue方法，语法为：th:@click="|getName(${person.name})|"
    
## 4️⃣遗留的问题

### 1. 页面问题（下拉列表超出页面）：
![页面问题](https://user-images.githubusercontent.com/46675231/161415327-47f2f84e-1408-4338-b170-d39c5b987195.png)
