### mysql单表约束和事务

#### DQL排序查询

+ DQL操作单表

  + 创建数据库，复制表

    + 创建数据库db2

      ```mysql
      CREATE DATABASE db2 CHARACTER SET utf8;
      ```

    + 将db1中的emp表复制到db2中

      + 别问，问就是sqlyog图形化操作。。。

  ```mysql
  /*
  	排序
  	使用 order by
  	语法结构： select 字段名 from 表名 （条件语句） order by 字段名 [ASC/DESC]
  	ASC 升序（默认）	DESC 降序
  */
  
  -- 单列排序 按照某一个字段进行排序
  -- 使用salary 字段对emp进行排序
  SELECT * FROM emp ORDER BY salary;
  SELECT * FROM emp ORDER BY salary DESC;
  
  -- 组合排序	同时对多个字段进行排序
  -- 在薪资的基础上对id进行排序
  SELECT * FROM emp ORDER BY salary DESC ,eid DESC;
  ```

  

#### DQL聚合函数

+ 之前的查询都是横向查询，聚合函数主要是做纵向查询将一列数据处理为一个数据并返回

+ 语法结构： select 聚合函数 from 表名

  ```mysql
  /*
  	聚合函数
  	    作用： 将一列数据作为一个整体进行纵向计算
  	    注意： 聚合函数在统计的时候会忽略null值
  	常用聚合函数
  	    count（字段）：统计记录数
  	    sum（字段）： 求和操作
  	    max（字段）：求最大值
  	    min（字段）：求最小值
  	    avg（字段）：求平均值
  	语法格式
  	    select 聚合函数（字段名） from 表名 [where 条件]
  */
  
  #1 查询员工的总数 
  SELECT COUNT(*) FROM emp;
  SELECT COUNT(1) FROM emp;
  SELECT COUNT(eid) FROM emp;
  -- 注意count在统计时会忽略空值
  
  #2 查看员工总薪水、最高薪水、最小薪水、薪水的平均值 
  SELECT SUM(salary) AS '总薪水',
  	MAX(salary) AS '最高薪水',
  	MIN(salary) AS '最小薪水',
  	AVG(salary) AS '平均薪水'
   FROM emp;
  #3 查询薪水大于4000员工的个数 
  SELECT COUNT(*) FROM emp WHERE salary > 4000;
  #4 查询部门为'教学部'的所有员工的个数 
  SELECT COUNT(*) FROM emp WHERE dept_name = '教学部';
  #5 查询部门为'市场部'所有员工的平均薪水
  SELECT AVG(salary) FROM emp WHERE dept_name = '市场部';
  ```

  

#### DQL分组查询

+ 分组的目的是为了做统计操作的一般分组会和聚合函数一起使用，另外查询的时候要查询分组字段

  ```mysql
  /*
  	分组查询 使用group by关键字
  	语法格式： select 分组字段/聚合函数 from 表名 group by 分组字段
  	
  	分组的过程 根据分组不同的分组字段进行分组，每一组返回一个数据
  	select * from emp group by sex;
  	将会返回一男一女的所有信息，并不会显示所有信息，当然mysql8.x已经不支持这种语法了，select后面必须接分组字段/聚合函数
  	分组字段用于展示不同的分组，聚合函数用于返回统计结果
  */
  
  -- 统计不同性别
  SELECT sex,AVG(salary) FROM emp GROUP BY sex;
  
  -- 查询所有部门信息
  SELECT dept_name AS '部门名称' FROM emp GROUP BY dept_name;
  
  -- 查询每个部门的平均薪资
  SELECT dept_name,AVG(salary) FROM emp GROUP BY dept_name;
  
  -- 查询每个部分的平均薪资，部门名称不能为null
  SELECT dept_name,AVG(salary) FROM emp WHERE dept_name IS NOT NULL GROUP BY dept_name;
  
  -- 查询平均薪资大于6000的部门
  	-- 首先求平均薪资
  	-- 求出平均薪资大于6000的部门
  	-- 在分组之后进行条件过滤需要使用having（判断条件）
  	SELECT dept_name,AVG(salary) FROM emp WHERE dept_name IS NOT NULL GROUP BY dept_name HAVING AVG(salary) > 6000; 
  	
  /*
  	where 和 having的区别
  	where 
  		1. 在分组前进行过滤
  		2. where后面不能跟聚合函数
  	having
  		1. 是在分组后进行条件过滤
  		2. having后面可以写聚合函数
  */
  ```

  

#### limit关键字

```mysql
/*
	limit 通过limit去指定要查询的数据的条数 行数
	语法格式
		select 字段 from 表名 limit offset , length;
		参数说明： 
			offset： 起始行数默认从0开始技术
			length： 返回行数要查询几条数据
*/

-- 查询emp表中的前五条数据
SELECT * FROM emp LIMIT 0, 5;
SELECT * FROM emp LIMIT 5;
-- 查询emp表中数据 从第四行开始查询六条
SELECT * FROM emp LIMIT 3 ,6;

-- limit分页，每页显示3条
SELECT * FROM emp LIMIT 0,3; -- 第一页
SELECT * FROM emp LIMIT 3,3; -- 第二页
SELECT * FROM emp LIMIT 6,3; -- 第三页

-- 分页公式 起始行数 = （当前页码 - 1） * 每页显示条数
```



#### 约束的介绍

```mysql
/*
	约束
		约束是指对数据进行一定的限制，来保证数据的完整性 有效性 正确性
		
	常见约束
		主键约束 primary key
		唯一约束 unique
		非空约束 not null
		外键约束 foreign key
*/
```



#### 主键约束

```mysql
/*
	主键约束   不可重复 唯一 非空
		作用	表示数据库中的每一条记录
	语法格式
		字段名 	字段类型 primary key
*/

-- 方式一 创建一个带有主键的表
CREATE TABLE emp2(
	eid INT PRIMARY KEY,
	ename VARCHAR(20),
	sex CHAR(1)
);

DROP TABLE emp2;

-- 方式二
CREATE TABLE emp2(
	eid INT,
	ename VARCHAR(20),
	sex CHAR(1),
	PRIMARY KEY(eid) 	-- 指定eid作为主键
);

-- 方式三 
-- 通过DDL添加主键
CREATE TABLE emp2(
	eid INT,
	ename VARCHAR(20),
	sex CHAR(1)
);
ALTER TABLE emp2 ADD PRIMARY KEY(eid);
-- 通过DDL删除主键
ALTER TABLE emp2 DROP PRIMARY KEY;
```



#### 主键自增

```mysql
/*
	主键自增	关键字 auto_increment 主键自动增长（字段类型必须死int类型）
	
*/
-- 创建主键自增的表
CREATE TABLE emp3(
	eid INT PRIMARY KEY AUTO_INCREMENT,
	ename VARCHAR(20),
	sex CHAR(1)
);


INSERT INTO emp2(ename,sex) VALUES('张三','男'); 
INSERT INTO emp2(ename,sex) VALUES('李四','男'); 
INSERT INTO emp2 VALUES(NULL,'翠花','女'); 
INSERT INTO emp2 VALUES(NULL,'艳秋','女');

-- 创建自增主键表，自定义自增起始值
CREATE TABLE emp4(
	eid INT PRIMARY KEY AUTO_INCREMENT,
	ename VARCHAR(20),
	sex CHAR(1)
)AUTO_INCREMENT=100;
```



#### delete和truncate对自增的影响

```mysql
/*
	delete和truncate删除对自增的影响
	
	delete逐条删除数据	使用delete之后，自增id会延续删除前的id
	truncate删除数据表重建数据表	使用truncate之后自增id会重置
*/
```



#### 非空约束

```mysql
/*
	非空约束
		特点某一列不为空
	语法格式
		字段名 字段类型 not null
*/

CREATE TABLE emp4(
	eid INT AUTO_INCREMENT PRIMARY KEY,
	ename VARCHAR(20) NOT NULL,
	sex CHAR(1)
	
);
```



#### 唯一约束

```mysql
/*
	唯一约束
	特点： 表中的某一列不能够重复（对null值 不做判断）
	语法格式
	字段名 字段类型 unique
*/

CREATE TABLE emp4(
	eid INT PRIMARY KEY;
	ename VARCHAR(20) UNIQUE;
	sex CHAR(1)
);

/*
	主键约束和唯一约束的区别
	1.主键约束唯一不能为空
	2.唯一约束唯一但是可以为空
	3.一个表中只能有一个主键但是可以有多个唯一约束
*/
```



#### 默认值

```mysql
/*
	默认值
		特点 指定某一列的默认值
	语法格式
		字段名 字段类型 default 默认值
*/

CREATE TABLE emp4(
	eid INT PRIMARY KEY,
	ename VARCHAR(20),
	sex CHAR(1) DEFAULT '男'
);
```



#### 事务的基本概念和转账操作演示

+ 事务是由一条或者多条sql组成的一个整体，事务的操作，要么全部成功，要么全部失败

  ```mysql
  -- 创建账户表 
  CREATE TABLE account(   
  	-- 主键    
  	id INT PRIMARY KEY AUTO_INCREMENT,    
  	-- 姓名    
  	NAME VARCHAR(10),    
  	-- 余额    
  	money DOUBLE 
  );
   
  -- 添加两个用户 
  INSERT INTO account (NAME, money) VALUES ('tom', 1000), ('jack', 1000);
  
  -- tom账户 -500元 
  UPDATE account SET money = money - 500 WHERE NAME = 'tom';
   
   error happens!
  -- jack账户 + 500元 
  UPDATE account SET money = money + 500 WHERE NAME = 'jack';
  -- 同时指向上面的代码会报错，因为mysql无法处理error happens这样a账户扣钱b账户却没有增加金额
  ```

  

#### mysql手动提交事务

```mysql
/*
	mysql事务操作
	手动提交事务
		1.开启事务 start transaction; 或者begin;
		2.提交事务 commit;
		3.回滚事务 rollback;
*/
-- 假设完成扣款操作 在终端中
start transection;
UPDATE account SET money = money - 500 WHERE NAME = 'tom';
UPDATE account SET money = money + 500 WHERE NAME = 'jack';
-- 此时虽然差距更新成功但是数据库中的数据并没有改变，需要提交事务才行
commit;
-- commit之后数据库内容更新，如果commit之前数据库崩溃，该次事务中的所有操作无效

```



#### mysql自动提交事务

```mysql
/*
	mysql事务操作
	手动提交事务
		1.开启事务 start transaction; 或者begin;
		2.提交事务 commit;
		3.回滚事务 rollback;
	自动提交事务
		mysql默认的提交方式 自动提交事务
		每执行一条dml语句都是一个单独的事务
	取消自动提交
		1.登录mysql查看autocommit状态
		show variables like 'autocommit';
		2.设置提交方式为手动提交
		set @@autocommit=off;
		3.更改为手动提交之后commit才会触发数据库更新，开启事务省略
*/
```



#### mysql四大特性

```mysql
 /*
	事务的四大特性
		原子性
			每个事务都是一个整体，不可以再拆分，事务中的所有sql
			要么都执行成功 要么都执行失败
		一致性
			事务在执行之前 数据库的状态，与事务执行之后的状态要保持一致
		隔离性
			事务与事务之间不应该相互影响，执行时要保证隔离状态
		持久性
			一旦事务执行成功，对数据的修改是持久的
 */
```

#### mysql事务隔离级别

```mysql
/*
	MYSQL隔离级别
		各个事务之间的隔离，相互独立，但是如果多个事务对数据库中的同一批数据
		进行并发访问的时候，就会引发一些问题，可以通过设置隔离级别来解决问题
	并发访问的问题
		脏读 一个事务读取到了另一个事务没有提交的数据
		不可重复读 一个事务中 两次读取的数据不一致
		幻读	一个事务中一次查询的结果无法支撑后续的业务操作
	设置隔离级别

		read uncommitted: 读未提交
			可以防止问题： 无
			
		read committed: 读已提交（oracle默认）
			可以防止问题： 脏读

		repeatable read: 可重复读（mysql默认）
			可以防止问题： 脏读，可重复读

		serializable：串行化
			可以防止问题： 脏读，不可重复读，幻读

		注意：隔离级别从小到大安全性越来越高，效率越来越低
*/
```



#### 隔离级别相关命令

```mysql
/*
	隔离级别相关命令
	1.查看隔离级别
		select @@tx_isolation;	高版本可能不支持
		select @@transaction_isolation;		高版本支持
	2.设置隔离级别
		set global transaction isolation level 级别名称；
		read uncommitted;	读未提交
		read committed;		读已提交
		repeatable read;	可重复读
		serializable;		串行化
*/

-- 设置
SET GLOBAL TRANSACTION ISOLATION LEVEL READ COMMITTED;

-- 注意更改后重新连接才能生效
```



#### 脏读演示及解决

+ 脏读是指一个事务读到了另一个事务没有提交的数据

  ```mysql
  -- tom，jack初始1000
  -- tom账户 -500元 
  UPDATE account SET money = money - 500 WHERE NAME = 'tom';
  -- jack账户 + 500元 
  UPDATE account SET money = money + 500 WHERE NAME = 'jack';
  
  -- 客户端A进行以上操作但是还没有commit，此时如果隔离权限较低，客户端b使用select语句可以在commit之前查询到修改后的数据，如果上述事务在commit前取消，b获取的就是错误的数据
  ```

+ 解决方式开启read uncommitted模式

#### 不可重复读演示及解决

+ 不可重复读： 同一个事务中进行查询操作但是每次读取的数据内容不一样

  ```mysql
  -- a窗口
  start transaction;
  select * from account where name = 'tom';  // 1000
  
  -- b窗口
  start transaction;
  -- tom账户 -500元 
  UPDATE account SET money = money - 500 WHERE NAME = 'tom';
  commit;
  
  -- a窗口
  select * from account where name = 'tom';  // 1500
  -- 在a窗口中一次事务中查询到的结果不一致，影响还是比较大的，假如在一次事务中进行了金额查询和金额缴纳，但是结果不一致，后果可想而知
  ```

+ 解决策略： 更改隔离权限为repeatable read;

#### 幻读演示及解决

+ 幻读：select 某记录是否存在，不存在，准备插入此记录，但执行insert时发现此记录已经存在

  ```mysql
  -- a窗口
  start transaction;
  select * from account where id = 3; # 查询是否存在id等于3的记录，不存在则插入
  -- b窗口
  start transaction;
  insert into account values(3,'lucy',1000);  # 在a查询完但是没有插入的同时b窗口发生了一次插入id为3
  
  -- a窗口
  insert into account values(3,'luna',1000); # a窗口进行插入操作，但是操作失败，id为3的记录已经存在
  ```

+ 解决策略：更改隔离权限为serializable，注意更改后，a窗口事务开启时，b窗口开启事务执行语句会进入等待状态知道a川南关口commit，所以serializable效率极低一般不用

