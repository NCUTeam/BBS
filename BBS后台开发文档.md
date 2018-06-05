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

## 查看所有帖子 & 查看某用户发布的所有帖子 & 发帖 & 帖子详情 & 回帖及评论

### 1. 所有发布的帖子

请求方式    |   请求路径    |  实现功能
------------|---------------|--------------------
get         |/post_article  |查看所有已发布的帖子


### 2. 某用户发布的所有帖子

请求方式    |            请求路径       |  实现功能
------------|---------------------------|--------------------------
get         |u/{user_id}/post_article/  |用户查看自己发布的所有帖子


### 3. 发帖（Table name="post_article"）

字段名         |  数据类型      |  长度 |  主键  |  外键           |  可空    | 说明 
---------------|----------------|-------|--------|-----------------|----------|----------------------
article_id     | bigint unsigned|       | yes    |                 | not null | 帖子唯一标识;实现自增。 
author_name    | varchar        | 20    |        | user(user_name) | not null | 发帖者用户名 
title          | text           |       |        |                 | not null | 帖子标题
create_time    | datetime       |       |        |                 | not null | 发布时间
article_contnet| longtext       |       |        |                 | not null | 帖子内容
comment_size   | integer        |       |        |                 |          | 评论数


请求方式    |            请求路径     |  实现功能
------------|-------------------------|-------
post        |u/{user_id}/post_article |发帖

sql建表语句


```
create table post_article (
	article_id bigint unsigned not null auto_increment primary key,
    author_id varchar(20) not null comment '发帖者id',
    title  text not null comment '帖子标题',
	create_time datetime notu null default current_timestamp comment '发布时间',
	article_content longtext not null comment '帖子内容',
	comment_size integer defalt null comment '评论数';
    foreign key(author_id) references user(user_id)
) comment '发帖表';
```
### 4. 帖子详情

请求方式    |            请求路径                  |  实现功能
------------|--------------------------------------|-------
get         |u/{user_id}/post_article/{article_id} |查看帖子详细内容

### 5. 回帖及评论（Table name="reply_article"）

字段名         |  数据类型      |  长度 |  主键  |            外键          |   可空    | 说明 
---------------|----------------|-------|--------|--------------------------|-----------|----------------------
id             | bigint unsigned|       |  yes   |                          | not null  |主键，自定生成,自增
article_id     | bigint unsigned|       |        |  post_article(article_id)| not null  | 评论所在文章的id
user_id        | bigint unsigned|       |        |  user(user_id)           | not null  | 评论的用户的id
pid            | bigint unsigned|       |        |                          | not null  | 评论的父id
reply_user_id  | bigint unsigned|       |        |  user(user_id)           |     null  | 被回复人的id
create_time    | datetime       |       |        |                          | not null  | 创建时间，自动生成
article_comment| longtext       |       |        |                          | not null  | 评论内容，限制500个字符



