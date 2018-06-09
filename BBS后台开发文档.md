# BBS论坛后台开发文档

## 登陆 & 注册 & 个人资料修改

### 1. 登陆

> 前台输入登陆的用户名和密码，然后在后台编写一个控制器Controller类，类中随意编写一个方法，注意在该类的上面确定好@PostMapping或者@GetMapping的value值的内容（url路径），这个是当登陆表单提交时那个form标签的action属性值要和上面的value值保持一致，这样就代表着当登陆表单提交时，来到这个控制器中的你编写的这个方法进行处理，提供用户名和登陆密码两个参数

| 请求方式 |  请求路径   |                     实现功能                     |
| :------: | :---------: | :----------------------------------------------: |
|   POST   | /user/login | 验证用户的用户名和密码是否正确（需要调用数据库） |

### 2. 注册

> 和前面的登陆类似，我们获取到注册的一系列参数信息，比如昵称、密码、确认密码、邮箱、验证码等，我们要做的比如昵称有没有被注册（需要调用数据库）,密码和确认密码是否相同，验证码是否相同，如果要做的更详细，我们可以指定昵称和密码的格式，正则表达，通过注解或者自己编写后台代码的

| 请求方式 |    请求路径    |                          实现功能                          |
| :------: | :------------: | :--------------------------------------------------------: |
|   POST   | /user/register | 验证用户名是否被注册或者密码和确认密码相同或验证码是否相同 |


用户表sql语句


```
CREATE TABLE `user` (
	`user_id` BIGINT ( 20 ) NOT NULL AUTO_INCREMENT,
	`username` VARCHAR ( 20 ) NOT NULL,
	`password` VARCHAR ( 20 ) NOT NULL,
	`gender` VARCHAR ( 2 ) DEFAULT NULL,
	`email` VARCHAR ( 50 ) DEFAULT NULL,
	`phone` BIGINT ( 11 ) DEFAULT NULL,
	`address` VARCHAR ( 50 ) DEFAULT NULL,
	`work` VARCHAR ( 15 ) DEFAULT NULL,
	`introduce` VARCHAR ( 100 ) DEFAULT NULL,
	`interests` VARCHAR ( 30 ) DEFAULT NULL,
PRIMARY KEY ( `user_id` ) USING BTREE 
) ENGINE = INNODB AUTO_INCREMENT = 2 DEFAULT CHARSET = utf8;
```
## 查看帖子分类（Table name="category"）

字段名          |  数据类型       |  长度  |  主键   |      外键            |   可空    | 说明 
---------------|----------------|-------|--------|----------------------|----------|----------------------
category_id    | int unsigned   |  20   | yes    |                      | not null | 类别唯一标识 
category_name  | varchar        |  50   |        |                      | not null | 类名

### 查看所有分类

请求方式    |   请求路径    |  实现功能
------------|---------------|--------------------
get         |/category      |查看所有分类

sql语句
```
create table category (
    category_id int(20) unsigned not null auto_increment primary key,
    category_name varchar(50) not null comment '类名'
) comment '分类表';
```
## 查看所有帖子 & 查看某用户发布的所有帖子 & 发帖 & 帖子详情 & 回帖及评论

### 1. 所有发布的帖子

请求方式    |   请求路径    |  实现功能
------------|---------------|--------------------
get         |/post_article  |查看所有已发布的帖子


### 2. 某用户发布的所有帖子

请求方式    |            请求路径       |  实现功能
------------|---------------------------|--------------------------
get         |/user/{user_id}/post_article |用户查看自己发布的所有帖子


### 3. 发帖（Table name="post_article"）

字段名          |  数据类型       |  长度  |  主键   |      外键            |   可空    | 说明 
---------------|----------------|-------|--------|----------------------|----------|----------------------
article_id     | int unsigned   |  20   | yes    |                      | not null | 帖子唯一标识;实现自增。 
author_id      | int            |       |        | user(user_id)        | not null | 发帖者用户名 
title          | text           |       |        |                      | not null | 帖子标题
create_time    | datetime       |       |        |                      | not null | 发布时间
article_contnet| longtext       |       |        |                      | not null | 帖子内容
category_id    | int            |       |        | category(category_id)| not null | 类别id 

请求方式    |            请求路径     |  实现功能
------------|-------------------------|-------
post        |/post_article |发帖



data:{  
    authorId:  
    title:  
    articleContent:  
    categoryId:  
}


Ps:不使用外键，为了便于理解此处我写了外键  
sql建表语句


```
create table post_article (
    article_id int(20) unsigned not null auto_increment primary key,
    author_id int not null comment '发帖者id',
    title  text not null comment '帖子标题',
	create_time datetime not null default current_timestamp comment '发布时间',
	article_content longtext not null comment '帖子内容',
	category_id int not null comment '类别id'
) comment '发帖表';
```
### 4. 帖子详情

请求方式     |            请求路径                 |  实现功能
------------|------------------------------------|-------
get         |/post_article/articleId/{articleId} |查看帖子详细内容


###  5.删除帖子

请求方式    |            请求路径                  |  实现功能
------------|--------------------------------------|-------
delete        |/post_article?articleId= |删除帖子及相关评论

###  6.根据分类返回帖子列表

请求方式   |             请求路径                  |  实现功能
----------|--------------------------------------|-------
get       |/post_article/categoryId/{categoryId} |根据分类查看帖子


### 7. 回帖及评论（Table name="reply_article"）

字段名          |  数据类型       |  长度  |  主键   |            外键          |   可空     | 说明 
---------------|----------------|-------|--------|--------------------------|-----------|----------------------
id             | int unsigned   |   20  |  yes   |                          | not null  |主键，自定生成,自增
article_id     | int            |       |        |  post_article(article_id)| not null  | 评论所在文章的id
user_id        | int            |       |        |  user(user_id)           | not null  | 评论的用户的id
pid            | int            |       |        |  user(user_id)           |     null  | 评论的父id
reply_user_id  | int            |       |        |  user(user_id)           |     null  | 被回复人的id
create_time    | datetime       |       |        |                          | not null  | 创建时间，自动生成
article_comment| longtext       |       |        |                          | not null  | 评论内容，限制500个字符



请求方式    |           请求路径     |  实现功能
-----------|-----------------------|-------
post       |        /comments      |回帖或评论


data:{  
    articleId:  
    userId:  
    pid:可为空，默认为0,为一级评论  
    replyUserId:可为空，默认为null，也是一级评论的特点  
    articleComment:  
}

### 8. 查看当前文章所有回帖及评论


请求方式    |            请求路径     |  实现功能
------------|-------------------------|-------
get         |  /comments?articleId=   |查看当前文章所有回帖及评论


### 9. 删除回帖及相关评论


请求方式    |            请求路径                    |  实现功能
------------|----------------------------------------|-------
delete      |/comments/user/{user_id}/pid/{pid}?id=  |删除回帖及相关评论

ps:如果是二级评论，则只删除二级评论  
如果是一级评论，则一级评论和其相关的二级评论都会被删除


```
create table reply_article (
	id int(20) unsigned not null auto_increment primary key,
	article_id int not null comment '评论所在文章的id',
    user_id int not null comment '评论的用户的id',
    pid int comment '评论的父id',
    reply_user_id int comment '被回复人的id', 
	create_time datetime not null default current_timestamp comment '发布时间',
	article_comment longtext not null comment '评论内容，限制500个字符'
) comment '回帖表';
```

## 需求帖（Table name="request_article"）
> 发帖功能类似，不再多做描述
把所有的post_article换成request_article即可
sql语句
```
create table requst_article (
    request_id int(20) unsigned not null auto_increment primary key,
    author_id int not null comment '发需求帖者id',
    title  text not null comment '需求帖标题',
	create_time datetime not null default current_timestamp comment '发布时间',
	article_content longtext not null comment '需求帖内容',
	category_id int not null comment '类别id'
) comment '需求表';
```
## 回复需求帖（Table name="reply_request"）
> 与发帖的回复功能相似，不再多做描述
把所有的comments换成request_comments


## 精华帖（Table name="good_article"）

字段名          |  数据类型     |  长度  |  主键  |   可空    | 说明 
---------------|--------------|-------|-------|-----------|----------------------
good_id        | int unsigned |  20   | yes   |  not null | 精华帖唯一标识;实现自增。 
article_id     | int          |       |       |  not null | 文章id 
user_id        | int          |       |       |  not null | 添加精华帖的用户id，判断是普通用户还是管理员

```
create table good_article (
    good_id int(20) unsigned not null auto_increment primary key,
    article_id int not null comment '文章id',
    user_id  int not null comment '添加精华帖的用户id'
) comment '精华帖表';
```
### 1. 添加精华帖/我的收藏

请求方式     |    请求路径    |  实现功能
------------|---------------|------------
post        |/good_article  |添加精华帖/我的收藏

data:{  
    articleId:  
    userId:  
}

### 2. 查看精华帖/我的收藏

请求方式  |          请求路径            |  实现功能
---------|-----------------------------|------------
get      |/user/{user_id}/good_article  |查看精华帖/我的收藏

### 3. 删除精华帖/我的收藏

请求方式  |          请求路径        |  实现功能
---------|-------------------------|------------
delete   |/good_article/{good_id}  |删除精华帖/我的收藏











