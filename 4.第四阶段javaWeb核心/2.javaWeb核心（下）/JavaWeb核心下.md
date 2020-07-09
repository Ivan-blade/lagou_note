# JavaWeb核心下

## EL和JSTL核心技术

### EL表达式

- EL表达式的概念和主要功能

  - 概念
    - EL（Expression Language）表达式提供了在jsp中简化表达式的方法，可以方便的访问各种数据并输出
  - 主要功能
    - 依次访问pageContext（当前页面有效），request（当前请求有效），session（当前会话有效），application（当前浏览器有效）作用域对象存储的数据
    - 获取请求参数值
    - 访问Bean对象中的属性值
    - 访问集合中的数据
    - 输出简单的运算结果

- EL表达式访问内置对象的数据

  - 访问方式

    ```jsp
    <%=request.getAtrribute("varName")%>
    <-- 用EL实现${varName} -->
    ```

    

- EL表达式访问请求参数的数据

- EL表达式访问BEAN对象的属性值

- EL表达式访问集合中的数据

- EL表达式常用的内置对象

- EL表达式常用运算的使用

### JSTL

- JSTL标签库的概念和使用
- JSTL标签库的set标签使用
- JSTL标签库中remove标签的使用
- JSTL标签库中if标签的使用
- JSTL标签库中choose标签的使用
- JSTL标签库中forEach标签的使用
- JSTL函数标签的使用
- JSTL标签库中格式化标签的使用
- JSTL标签库中自定义标签的使用

## Filter+Listener核心技术

### Filter

- Filter过滤器的基本概念和工作方式
- 登陆页面和servlet通信实现
- 登陆功能的缺陷
- 登陆功能的优化实现
- Filter接口的方法使用
- FilterConfig方法的接口使用
- 多过滤器的使用和优点

### Listener

- 监听器的概念和分类
- ServletRequestListener监听器的使用
- ServletRequestAttributeListener监听器的使用
- HttpSessionListener监听器的使用
- HttpSessionAttributeListener监听器的使用
- ServletContextListener监听器的使用
- ServletContextAttributeListener监听器的使用
- HttpSessionBindingListener监听器的使用
- HttpSessionActivationListener监听器的使用
- 监听器实现在线用户数量的统计

*XMind - Trial Version*