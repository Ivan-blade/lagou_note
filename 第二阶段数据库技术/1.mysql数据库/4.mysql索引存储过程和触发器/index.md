### mysql索引存储过程和触发器

#### 索引的介绍

+ 什么是索引
  + 在数据表中，对字段建立索引可以大大提高查询速度，通过善用这些索引，可以令mysql的查询和运行更加高效
  + 如果合理的设计且使用索引的mysql是一辆兰博基尼，那么没有设计和使用索引的mysql就是一个人力三轮车
+ 常见索引分类
  + 主键索引（primary key） 
    + 主键是一个唯一性的索引，每个表中只能有一个主键
  + 唯一索引（unique）
    + 索引列的所有数据只能出现一次，必须是唯一
  + 普通索引（index）
    + 最常见的索引 作用就是提高对数据的访问速度
+ 表对应的索引被保存在一个文件中，如果对数据进行增删改操作，那么mysql就需要对索引进行更新
+ 因为索引配置需要占用一定空间已经需要维护所以越多越影响效率，不能将所有字段都加上索引
+ 注意在数据库设计初期就要想好哪些字段需要添加索引，当数据量比较庞大的时候就索引添加很慢，也可能添加失败

#### 索引的创建和删除

```mysql
CREATE DATABASE db4 CHARACTER SET utf8;

CREATE TABLE demo01(
	did INT,
	dname VARCHAR(20),
	hobby VARCHAR(30)
);

/*
	主键索引的创建
	1. 创建表的时候 直接添加主键
	2. 创建表之后 添加索引 使用DDL
*/

-- 为demo01表添加主键索引
ALTER TABLE demo01 ADD PRIMARY KEY (did);

/*
	唯一索引的创建
		特点： 索引列的所有值只能出现一次，必须唯一
		唯一索引可以保证数据记录的唯一性，事实上在许多场合，人们创建唯一索引的目的往往不是为了提高访问速度，而是为了避免重复数据
		语法格式： 
			1. 在创建表时添加唯一索引 
				UNIQUE [索引名] （列名）
			2. 使用create语句创建：在已有的表上创建索引
				create unique index 索引名 on 表名(列名(长度))
			3. 修改表结构添加索引
				alter table 表名 add unique(列名)
*/

-- 为demo01表中的hobby字段添加索引
CREATE UNIQUE INDEX ind_hobby ON demo01(hobby);
-- unique索引不仅保证数据唯一性，而且能提高性能

/*
	普通索引
	普通索引（由关键字KEY 或INDEX定义的索引）的唯一任务是加快数据的访问速度
	因此，应该只为那些最经常出现在查询条件（where column=）或排序条件（order by） 中的数据列创建索引
	语法格式：
		1.create语句
		create index 索引名 on 表名(列名[长度])
		2.修改表结构添加索引
		alter table 表名 add index 索引名 （列名）
*/
ALTER TABLE demo01 ADD INDEX ind_dname(dname);

/*
	删除索引
		alter table 表名 drop index 索引名;
*/
ALTER TABLE demo01 DROP INDEX ind_dname;
```



#### 索引性能测试

+ ？？？不用侧了效率大概差个10^n倍

#### 索引的优缺点

+ 总结
  + 创建索引的原则
    + 优先选择为 经常出现在 查询条件或者排序分组 后面的字段创建索引
  + 索引优点
    + 可以大大提高查询速度
    + 减少查询中分组和排序的时间
    + 通过创建唯一索引保证数据的唯一性
  + 索引缺点
    + 创建和维护索引需要时间，数据量越大时间越长
    + 表中的数据进行增删改操作的时候，索引也需要维护，这样降低了维护的速度
    + 索引文件需要占据磁盘空间

#### 视图的介绍与创建

+ 什么是视图
  + 视图是一种虚拟表
  + 视图建立在已有表上，视图赖以建立的这些表称为基表
  + 向视图提供数据内容的语句为select语句，可以将视图理解为存储起来的select语句
  + 视图向用户提供基表数据的另一种表现形式
+ 视图作用
  + 权限控制时可以使用
    + 比如某几列可以运行数据查询，其他列不允许，可以开启视图，查询特点的列，起到权限控制的作用
  + 简化复杂的多表查询
    + 视图本身就是一条查询sql，我们可以将一次复杂的查询构建成了一张视图，用户只要查询视图就可以获取想要得到的信息（不需要再编写复杂的sql）
      + 视图主要是为了简化多表查询
  + 使用场景
    + 如果某个查询的结果出现的十分频繁，并且查询语法比较发展，那么这个时候就可以根据这条查询语句构建一张视图 方便查询

```mysql
/*
	视图的语法
		create view 视图名[字段列表] ad select 查询语句;
		字段列表一般和查询语句相同所以一般省略
*/

#1. 先编写查询语句 
#查询所有商品 和 商品的对应分类信息 
SELECT * FROM products p LEFT JOIN category c ON p.`category_id` = c.`cid`;
 
#2.基于上面的查询语句,创建一张视图 
CREATE VIEW products_category_view AS 
SELECT * FROM products p LEFT JOIN category c ON p.`category_id` = c.`cid`;

-- 操作视图就是操作一只只读表
SELECT * FROM products_category_view;
```

#### 使用视图进行查询操作

```mysql

# 查询各个分类下的商品平均价格
# 通过 多表查询 
SELECT cname AS '分类名称', AVG(p.`price`) AS '平均价格'
FROM products p LEFT JOIN category c ON p.`category_id` = c.`cid` GROUP BY c.`cname`;
 
# 通过视图查询 可以省略连表的操作 
SELECT     cname AS '分类名称',    AVG(price) AS '平均价格' 
FROM products_category_view GROUP BY cname

# 查询鞋服分类下最贵的商品的全部信息
#1.先求出鞋服分类下的最高商品价格 
SELECT MAX(price) AS maxPrice 
FROM products p LEFT JOIN category c ON p.`category_id` = c.`cid` 
WHERE c.`cname` = '鞋服'

#2.将上面的查询 作为条件使用 
SELECT * FROM products p LEFT JOIN category c ON p.`category_id` = c.`cid` 
WHERE c.`cname` = '鞋服' AND p.`price` = 
(SELECT 
	MAX(price) AS maxPrice
FROM 
products p LEFT JOIN category c ON p.`category_id` = c.`cid` 
WHERE c.`cname` = '鞋服');

#通过视图查询 
SELECT * FROM products_category_view pcv 
WHERE  pcv.`cname` = '鞋服' AND pcv.`price` = 
(SELECT 
MAX(price) FROM products_category_view 
WHERE cname = '鞋服');
```



#### 视图和表的区别

+ 视图是建立在表的基础上，表存储数据库中的数据，而视图只是做一个数据展示
+ 通过视图不能改变表中数据（一般情况下，视图都是根据表中的列经过计算得到的结果不允许更新）
+ 删除视图，表不受影响，而删除表，视图不再起作用

#### 存储过程的介绍

+ 什么是存储过程
  + mysql5.0版本开始支持存储过程
  + 存储过程（store procedure）是一种在数据库中存储复杂程序，以便外部程序调用的一种数据库对象，存储过程是为了完成特定功能的sql语句集，精编译创建并保存在数据库中，用户可指定存储过程的名字并给定参数（需要时）来调用执行
  + 简单理解：存储过程其实就是一堆sql语句的合并，中间加入一些逻辑控制
+ 存储过程的优缺点
  + 优点
    + 存储过程一旦调试完成就可以稳定运行（前提是业务需求要相对稳定，没有变化）
    + 存储过程减少业务系统和数据库的交互，降低耦合，数据库交互更加快捷（应用服务器，与数据库服务器不在同一个地区）
  + 缺点
    + 在互联网行业中，大量使用mysql，mysql的存储过程与oracle的相比较弱，所以较少使用，并且互联网行业需求变化较快也是原因之一
    + 尽量在简单的逻辑中使用，存储过程移植十分困难，数据库集群环境，保证各个库之间存储过程变更一致也十分困难
    + 阿里的代码规范里也提出禁止是存储过程，存储过程维护起来的确麻烦

#### 存储过程创建方式

```mysql
# 商品表 
CREATE TABLE goods(  
	gid INT,  
	NAME VARCHAR(20),  
	num INT  -- 库存 
);
 
#订单表 
CREATE TABLE orders(  
	oid INT,  
	gid INT,  
	price INT -- 订单价格 
);
 
# 向商品表中添加3条数据 
INSERT INTO goods VALUES(1,'奶茶',20); 
INSERT INTO goods VALUES(2,'绿茶',100); 
INSERT INTO goods VALUES(3,'花茶',25);

/*
	创建存储过程
	语法格式
		delimiter $$   -- $$表示声明语句的结束符号自定义
		create procedure 存储过程名称()	   -- 声明存储过程
		begin 	-- 开始编写存储过程
		  -- 要执行的sql
		end $$ -- 存储过程结束
		
*/
DELIMITER $$
CREATE PROCEDURE goods_proc()
BEGIN 
SELECT * FROM goods;	-- (这里可以看出自定义结束符的意义，如果不自定义整个过程到；就结束了)
END $$
```



#### 触发器的介绍

#### 触发器的创建和使用

#### DCL创建用户

#### DCL用户授权

#### DCL查看用户权限

#### DCL查询用户删除用户

#### 数据库备份SQLYOG方式

#### 数据库备份命令行方式

