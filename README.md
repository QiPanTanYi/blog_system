### blog_system
#####《博客系统》......其他信息待补充
  \1.源码文件中的application.yml，第二行的port端口，必须得是不被占用的；

  2.application-jdbc.properties中的MySQL账号和密码需要和自己本地一致；

  3.config包下的RedisConfig类,我添加在@Bean下@SuppressWarnings("all")，
是用来避免RedisConnectionFactory报错的；

  4.初次引入项目需要修改Settings中的Build,Execution,Deployment下的Build Tools下的
Maven中的settings.xml和repository文件夹与本地路径一致；

  5.blog_system.sql直接拖进Navicat就行，会生成对应的数据库，确保数据库存在再运行。
