# spring



## 一. 简介



spring是jave EE开发的必备技能



降低开发、框架耦合



spring版本到达5.0,spring framework是最基础的配置



### 1.1 IOC



使用对象时，由主动new来交给spring进行处理

对象的创建和控制权交给外部，叫做控制反转



spring提供容器，成为IOC容器



在容器中的对象统称为bean



### 1.2 DI 依赖注入



在容器中建立bean与bean之间依赖关系的整个过程称为依赖注入



### 1.3 IOC入门



1. 创建maven工程



2. pom文件导入相关依赖



```java
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>5.3.23</version>
</dependency>
    
<!--测试工具包-->
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.13.2</version>
    <scope>test</scope>
</dependency>
```



3. 在resources目录下创建applicationContext.xml文件



```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

</beans>
```



4. 创建主要的类



``` java
public interface BookMapper {
    public void save();
}
```



``` java
public class BookMapperImpl implements BookMapper {
    @Override
    public void save() {
        System.out.println("bookMapper");
    }
}
```



``` java
public class BookServiceImpl implements BookService {

    private BookMapper bookMapper = new BookMapperImpl();

    @Override
    public void save() {
        System.out.println("bookService1");
        bookMapper.save();
    }
}

```



``` java
public interface BookService {
    public void save();
}
```



5. 配置bean

``` xml
<bean id="bookMapper" class="com.ymx.mapper.impl.BookMapperImpl"/>
<bean id="bookService" class="com.ymx.service.impl.BookServiceImpl"/>
```



5. 测试



``` java
ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
// 获取资源
BookService bookService = (BookService) context.getBean("bookService");
bookService.save();
```



6. 测试结果



### 1.4 DI入门



1. 创建主要的类



``` java
public class BookMapperImpl implements BookMapper {
    @Override
    public void save() {
        System.out.println("bookMapper");
    }
}
```



``` java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookServiceImpl implements BookService {

    private BookMapper bookMapper;
    @Override
    public void save() {
        System.out.println("bookService");
        bookMapper.save();

    }
}
```



2. 配置bean



``` xml
<bean id="bookMapper2" class="com.ymx.mapper.impl.BookMapperImpl2"/>
<bean id="bookService2" class="com.ymx.service.impl.BookServiceImpl2">
    <property name="bookMapper" ref="bookMapper2" />
</bean>
```



3. 测试



``` java
ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
// 获取资源
BookService bookService = (BookService) context.getBean("bookService2");
bookService.save();
```



通过set注入



### 1.5 分析测试代码



获取ioc容器



``` java
ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
```



获取bean

``` java
BookMapper mapper = (BookMapper) context.getBean("mapper");
```



执行



``` java
mapper.save();
```





## 二. bean



### 2.1 配置



``` xml
<!--
    bean标签标识配置bean
    id属性表示为名字
    class属性表示定义类型-->
<bean id="bookMapper" class="com.ymx.mapper.impl.BookMapperImpl"/>
```



#### 2.1.1 别名

```xml

<bean id="bookMapper" class="com.ymx.mapper.impl.BookMapperImpl" name="mapper mapper2 mapper3"/>
```



通过name属性进行赋值，可以赋值多个，用, ; 和空格都可以分割



1. 创建主要的类



```java
public class BookMapperImpl implements BookMapper {

    @Override
    public void save() {
        System.out.println("bookMapper");
    }
}
```



```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookServiceImpl implements BookService {

    private BookMapper bookMapper;
    @Override
    public void save() {
        System.out.println("bookService");
        bookMapper.save();
    }
}
```



2. xml文件配置



``` xml
<!--配置别名,单例模式
name来设置别名,通过空格 逗号和分号进行分割
scope来设置是否为单例模式,默认为singleton来进行设置-->
<bean id="bookMapper" class="com.ymx.mapper.impl.BookMapperImpl" scope="prototype"/>
<bean id="bookService" class="com.ymx.service.impl.BookServiceImpl" name="impl impl,impl33;i34" scope="singleton">
    <property name="bookMapper" ref="bookMapper" />
</bean>
```



3. 测试

``` java
// 获取IOC容器
ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
// 获取资源
BookService bookService = (BookService) context.getBean("imp3");
bookService.save();
```



4. 结果



![别名配置](C:\Users\panda\Pictures\Screenshots\别名配置.png)



#### 2.1.2 作用范围配置



spring创建的bean默认为单例模式，简单来说就是创建的对象是否为同一个



上面的别名配置中已经将scope设置完成



1. 测试



``` java
@Test
public void test03() {
    // 获取IOC容器
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    // 获取资源
    BookService bookService = (BookService) context.getBean("impl3");
    BookService bookService1 = (BookService) context.getBean("bookService3");
    BookMapper bookMapper = (BookMapper) context.getBean("bookMapper3");
    BookMapper bookMapper1 = (BookMapper) context.getBean("bookMapper3");
    bookService.save();
    // 单例模式
    System.out.println(bookMapper);
    System.out.println(bookMapper1);
    System.out.println(bookService);
    System.out.println(bookService1);
}
```



2. 结果



![单例和非单例](C:\Users\panda\Pictures\Screenshots\单例和非单例.png)



spring适合创建需要重复使用的对象，可以看到mapper使用非单例模式，两个不同，表现层对象，业务层对象，数据层对象和工具对象适合单例



### 2.2 实例化



spring构造默认使用的方法



添加一段无参的构造方法



```  java
public class BookMapperImpl implements BookMapper {
    private BookMapperImpl(){
        System.out.println("无参构造");
    }
    @Override
    public void save() {
        System.out.println("bookMapper");
    }
}

```



添加bean



```xml
<bean id="bookMapper" class="com.ymx.mapper.impl.BookMapperImpl"/>
<bean id="bookService" class="com.ymx.service.impl.BookServiceImpl">
    <property name="bookMapper" ref="bookMapper"/>
</bean>
```



然后测试输出



```java
ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
BookMapper mapper = (BookMapper) context.getBean("bookMapper");
mapper.save();
```



发现会输出构造函数



无需修改xml文件



### 2.3 生命周期



bean从创建到消亡的完整过程



控制bean的生命周期



#### 2.3.1 第一种方法



1. 添加功能



``` java
public void init(){
    System.out.println("init");
}
public void destory(){
    System.out.println("destory");
}
```



2. xml配置



``` xml
<bean id="bookMapper" class="com.ymx.mapper.impl.BookMapperImpl" init-method="init" destroy-method="destroy"/>
<bean id="bookService" class="com.ymx.service.impl.BookServiceImpl">
    <property name="bookMapper" ref="bookMapper"/>
</bean>
```



3. 测试



``` java
ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
BookMapper mapper = (BookMapper) context.getBean("bookMapper");
mapper.save();
context.registerShutdownHook();

```



registerShutdownHook()方法虚拟机关闭前需要将容器销毁

close将容器暴力关闭

close必须将它放在最后一排，另一个相对自由



#### 2.3.2 第二种方法



``` java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookServiceImpl implements BookService , InitializingBean, DisposableBean {

    private BookMapper bookMapper;
    @Override
    public void save() {
        System.out.println("bservice running");
        bookMapper.save();
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("service destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("service init");
    }
}
```



通过这种方式不运行该方法也可以执行，和构造方法类似，因为构造方法在创建时就已经运行起来了



## 三. 依赖注入



### 3.1 setter注入



1. 定义类



```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookServiceImpl implements BookService {

    private BookMapper bookMapper;
    private UserMapper userMapper;
    @Override
    public void save() {
        System.out.println("bservice running");
        bookMapper.save();
        userMapper.save();
    }
}
```



``` java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMapperImpl implements com.ymx.mapper.UserMapper {
    private int id;
    private String name;

    @Override
    public void save() {
        System.out.println("userMapper" + id + name);
    }
}
```



``` java
public class BookMapperImpl implements BookMapper {

    @Override
    public void save() {
        System.out.println("bookMapper");
    }
}

```



2. 配置中使用ref属性注入

``` xml
<bean id="bookMapper" class="com.ymx.mapper.impl.BookMapperImpl6"/>
<bean id="userMapper" class="com.ymx.mapper.impl.UserMapperImpl6">
    <property name="id" value="1"/>
    <property name="name" value="cxk"/>
</bean>
<bean id="bookService" class="com.ymx.service.impl.BookServiceImpl">
    <property name="bookMapper" ref="bookMapper"/>
    <property name="userMapper" ref="userMapper"/>
</bean>
```



配置userMapper使用基本类型

配置bookService使用引用类型



### 3.2 构造器注入



创建类

``` java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookServiceImpl implements BookService {

    private BookMapper bookMapper;
    private UserMapper userMapper;
    private UserMapper userMapper2;


    @Override
    public void save() {
        System.out.println("bservice running");
        bookMapper.save();
        userMapper.save();
        userMapper2.save();
    }
}
```



``` java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMapperImpl implements UserMapper {
    private int id;
    private String name;

    @Override
    public void save() {
        System.out.println("userMapper" + id + name);
    }
}
```



``` java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMapperImpl implements UserMapper {
    private int id;
    private String name;

    @Override
    public void save() {
        System.out.println("userMapper2" + id + name);
    }
}
```



配置bean

```xml
<bean id="bookMapper" class="com.ymx.mapper.impl.BookMapperImpl"/>
<bean id="userMapper" class="com.ymx.mapper.impl.UserMapperImpl">
    <!--通过类型注入-->
    <constructor-arg type="int" value="2"/>
    <constructor-arg type="java.lang.String" value="蔡徐坤"/>
</bean>
<bean id="userMapper2" class="com.ymx.mapper.impl.UserMapperImpl2">
    <!--通过位置来解决-->
    <constructor-arg index="0" value="3"/>
    <constructor-arg index="1" value="鸡哥"/>
</bean>
<bean id="bookService" class="com.ymx.service.impl.BookServiceImpl">
    <!--构造器注入的name识别的是形参的值-->
    <constructor-arg name="bookMapper" ref="bookMapper"/>
    <constructor-arg name="userMapper" ref="userMapper"/>
    <constructor-arg name="userMapper2" ref="userMapper"/>
</bean>
```



type通过类型，name通过有参构造的形参名，index通过位置



### 3.3 自动装配



创建类



``` java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookServiceImpl implements BookService {

    private BookMapper bookMapper;
    @Override
    public void save() {
        System.out.println("bookService");
        bookMapper8.save();
    }
}
```



``` java
public class BookMapperImpl implements BookMapper {

    @Override
    public void save() {
        System.out.println("bookMapper");
    }
}
```



配置bean



``` xml
<!--自动装配-->
<bean id="bookMapper" class="com.ymx.mapper.impl.BookMapperImpl"/>
<bean id="bookService" class="com.ymx.service.impl.BookServiceImpl" autowire="byName"/>
```



用两种类型一种byType，一种byName，推荐是用byType，如果有两个类型相同的bean只能使用按名字装配，用于引用类型，自动装配优先级低于set注入和构造器注入



### 3.4 集合注入



只使用简单类型，特殊类型将和之前的类似，通过ref进行引用



创建类



``` java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookMapperImpl implements BookMapper {

    private int[] array;
    private List<String> list;
    private Set<String> set;
    private Map<String,String> map;
    private Properties properties;

    @Override
    public void save() {
        System.out.println("bookMapper");
    }
}
```



xml配置

``` xml
<bean id="bookMapper" class="com.ymx.mapper.impl.BookMapperImpl">
    <property name="array">
        <array>
            <value>100</value>
            <value>100</value>
            <value>100</value>
        </array>
    </property>
    <property name="list">
        <list>
            <value>100</value>
            <value>100</value>
            <value>200</value>
        </list>
    </property>
    <property name="set">
        <set>
            <value>100</value>
            <value>200</value>
            <value>100</value>
            <value>200</value>
        </set>
    </property>
    <property name="map">
        <map>
            <entry key="国家" value="中国"></entry>
            <entry key="省份" value="河南"></entry>
            <entry key="所在市" value="郑州"></entry>
        </map>
    </property>
    <property name="properties">
        <props>
            <prop key="国家">中国</prop>
            <prop key="省份">河南</prop>
            <prop key="所在市">郑州</prop>
        </props>
    </property>
</bean>
```



测试



```java
ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
BookMapper mapper = (BookMapper) context.getBean("bookMapper9");
mapper.save();
System.out.println(mapper.toString());
```



### 3.5 数据源注入



导入坐标

``` xml
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.2.11</version>
</dependency>
```



配置xml

```xml
<!--配置druid数据源对象-->
<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
    <property name="username" value="root"/>
    <property name="url" value="jdbc:mysql://localhost:3306/spring_db"/>
    <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
    <property name="password" value="123456"/>
</bean>
```



测试

```java
public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    DataSource dataSource = (DataSource) context.getBean("dataSource1");
    System.out.println(dataSource);
}
```



![image-20220920195725769](C:\Users\panda\AppData\Roaming\Typora\typora-user-images\image-20220920195725769.png)



1. 开启context命名空间

2. 加载properties



``` xml
<context:property-placeholder location="classpath:*.properties"/>
```



3. 配置jdbc



``` properties
jdbc.driver=com.mysql.cj.jdbc.Driver
jdbc.username=root
jdbc.password=123456
jdbc.url=jdbc:mysql://localhost:3306/spring_db
```



4. 修改配置



```xml
<!--配置druid数据源对象-->
<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
    <property name="username" value="${jdbc.username}"/>
    <property name="url" value="${jdbc.url}"/>
    <property name="driverClassName" value="${jdbc.driver}"/>
    <property name="password" value="${jdbc.password}"/>
</bean>
```



5. 创建类



``` java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookMapperImpl implements BookMapper {

    private String name;

    @Override
    public void save() {
        System.out.println("bookMapper");
    }
}
```



6. bean配置查看



```xml
<bean id="bookMapper" class="com.ymx.mapper.impl.BookMapperImpl">
    <property name="name" value="${jdbc.username}"/>
</bean>
```



7. 测试



``` java
ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
DataSource dataSource = (DataSource) context.getBean("dataSource");
BookMapper mapper = (BookMapper) context.getBean("bookMapper");
System.out.println(mapper);
System.out.println(dataSource);
```



### 3.6 加载方式



三种方式



+ ```java
  BookMapper mapper = (BookMapper) context.getBean("bean的名称");
  ```

+ ``` java
  BookMapper mapper = context.getBean("bookMapper",类名.class);
  ```

+ ``` java
  BookMapper mapper = context.getBean(类名.class);
  ```



第三种不能多个相同类型的bean



## 四.注解



### 4.1 注解开发定义bean



xml配置



``` xml
<context:component-scan base-package="com.ymx"/>
```



创建类



``` java
@Component("bookMapper")
public class BookMapperImpl implements BookMapper {
    @Override
    public void save() {
        System.out.println("bookMapper");
    }
}
```



```java
@Component("bookService")
public class BookServiceImpl implements BookService {

    private BookMapper bookMapper ;

    @Override
    public void save() {
        System.out.println("bookService");
        bookMapper.save();
    }
}
```



@Component因为我是放在一个模块里的使用数字来进行编号，所以必须指定名字

还可以使用三个衍生注解

+ @Controller 用于控制层
+ @Service 用于业务层
+ @Repository 用于持久层

在后面加括号可以指定id



测试

``` java
ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
BookMapper mapper = (BookMapper) context.getBean("bookMapper");
System.out.println(mapper);
BookService bookService11 = (BookService) context.getBean("bookService");
System.out.println(bookService);
```



### 4.2 纯注解开发



创建类



``` java
@Configuration
@ComponentScan("com.ymx")
public class SpringConfig {
}
```



删除xml中的注解配置





测试



``` java
ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
BookMapper mapper = (BookMapper) context.getBean("bookMapper11");
System.out.println(mapper);
BookService bookService11 = (BookService) context.getBean("bookService11");
System.out.println(bookService11);
```



![image-20220920211914367](C:\Users\panda\AppData\Roaming\Typora\typora-user-images\image-20220920211914367.png)



如果需要添加多个目录，需要使用数组来进行添加



### 4.3 其他的使用



可以改一下以前的代码变成纯注解模式



@Scope来控制是否为单例模式

@Autowired 默认按照类型装配如果需要按照名字，需要配合使用来指定名字

```java
@Autowired
@Qualifier("bookMapper")
```

@Value 来给一些普通的值

@Bean将这个设置为bean

@PropertySource来导入文件

@Import



### 4.4 管理第三方bean



新建类

``` java
@Configuration
public class JdbcConfig {
    @Bean
    public DataSource dataSource(){
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/spring_db");
        ds.setUsername("root");
        ds.setName("123456");
        return ds;
    }
}
```



如果导入基本类型，需要赋值



```java
@Value("com.mysql.cj.jdbc.Driver")
private String driver;
@Value("jdbc:mysql://localhost:3306/spring_db")
private String url;
@Value("root")
private String username;
@Value("123456")
private String password;
@Bean
public DataSource dataSource(){
    DruidDataSource ds = new DruidDataSource();
    ds.setDriverClassName(driver);
    ds.setUrl(url);
    ds.setUsername(username);
    ds.setPassword(password);
    return ds;
}
```



``` java
@Bean
public DataSource dataSource(BookMapper bookMapper){
    System.out.println(bookMapper);
    DruidDataSource ds = new DruidDataSource();
    ds.setDriverClassName(driver);
    ds.setUrl(url);
    ds.setUsername(username);
    ds.setName(password);
    return ds;
}
```



基础类型用变量，引用类型用形参



### 4.5 整合mybatis



创建mybatis配置类



``` java
public class MybatisConfig {
    @Bean
    public SqlSessionFactoryBean sessionFactoryBean(DataSource dataSource){
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setTypeAliasesPackage("com.ymx.pojo");
        factoryBean.setDataSource(dataSource);
        return factoryBean;
    }
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer msc = new MapperScannerConfigurer();
        msc.setBasePackage("com.ymx.mapper");
        return msc;
    }
}
```



导入到SpringConfig中



创建实体类，mapper类和service类和service实现类



```java
@Data
public class Account implements Serializable {
    private Integer id;
    private String name;
    private Integer money;
}
```



``` jav
public interface AccountMapper  {
    @Select("select * from spring_db.tbl_account where id = #{id}")
    Account selectById(int id);
}
```



``` java
public interface AccountService {
    Account findByid(Integer id);
}

```



``` java
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public Account findByid(Integer id) {
        return accountMapper.selectById(id);
    }
}
```



测试



``` java
@Test
public void test() {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    AccountService as = context.getBean(AccountService.class);
    Account account = as.findByid(2);
    System.out.println(account);
}
```



## 五 AOP



在不惊动原始设计的基础上进行 功能增强



连接点：程序执行的任意位置，理解为方法的执行

切入点：匹配连接点的式子

通知：在切入点执行的操作

切面：描述通知和切入点的对应关系

通知类：定义通知的类



导包



``` xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-aop</artifactId>
    <version>5.3.22</version>
</dependency>
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.9.9.1</version>
</dependency>
```



创建表



``` java
public interface BookMapper  {
    public void save();
    public void update();
}
```



``` java
@Repository
public class BookMapperImpl implements BookMapper {
    @Override
    public void save() {
        System.out.println(System.currentTimeMillis());
        System.out.println("bookMapper");
    }

    @Override
    public void update() {
        System.out.println("book update");
    }
}
```



``` java
@Component
@Aspect
public class MyAdvice {
    @Pointcut("execution(void com.ymx.mapper.BookMapper.update())")
    private void pt(){

    }
    @Before("pt()")
    public void method(){
        System.out.println(System.currentTimeMillis());
    }
}
```



``` java
@Configuration
@ComponentScan("com.ymx")
@EnableAspectJAutoProxy
public class SpringConfig {
}
```



``` java
@Component
@Aspect
public class MyAdvice {
    @Pointcut("execution(void com.ymx.mapper.BookMapper.update())")
    private void pt(){
    }
    @Before("pt()")
    public void method(){
        System.out.println(System.currentTimeMillis());
    }
}
```



AOP是通过代理来实现的



``` java
execution(void com.ymx.mapper.BookMapper.update())
execution(void com.ymx.mapper.impl.BookMapperimpl.update())
```



*表示单个独立的任意符号，可以独立出现，也可以作为前缀或者后缀的通配符出现



``` java
execution(public * com.ymx.*.UserService.find*(*))
```



..:多个连续的任意符号，用于简化包名和参数的书写



``` java
execution(public User com..UserService.findById(..))
```



+:专用匹配子类类型



``` java
execution(* *..Service+.*(..))
```



代码按照标准规范开发

切入点通常描述接口

访问控制修饰符开发采用public

返回值类型对于增删改使用精准类型，查询使用*

包名不适用..，用*做单个包匹配

接口名/类名采用UserService写成*Service来绑定



``` java
@Component
@Aspect
public class MyAdvice {

    // 将这个方法定义为切入点
    @Pointcut("execution(void com.ymx.mapper.BookMapper.update())")
    private void pt() {
    }

    @Before("pt()")
    public void before() {
        System.out.println("before");
    }
    @After("pt()")
    public void after() {
        System.out.println("after");
    }
    @Around("pt()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("around before");
        pjp.proceed();
        System.out.println("around after");
        return 200;
    }
    @AfterReturning("pt()")
    public void afterReturning(){
        System.out.println("afterReturning");
    }
    @AfterThrowing("pt()")
    public void afterThrow(){
        System.out.println("afterThrow");
    }
}
```



## 六. 事务



pom文件



``` xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.30</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
    <version>5.3.22</version>
</dependency>
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.5.11</version>
</dependency>

<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis-spring</artifactId>
    <version>2.0.7</version>
</dependency>
```



java类



``` java
public interface AccountService {
    // 添加事务
    @Transactional
    public void transfer(String out,String in,Integer money);
}
```



``` java
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;
    @Override
    public void transfer(String out, String in, Integer money) {
        accountMapper.outMoney(out,money);
        accountMapper.inMoney(in,money);
    }
}
```



``` java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private Integer id;
    private String name;
    private Integer money;
}
```



``` java
@Repository
public interface AccountMapper {

    @Update("update tbl_account set money = money + #{money} where name = #{name}")
    void inMoney(@Param("name") String name,@Param("money") Integer money);

    @Update("update tbl_account set money = money - #{money} where name = #{name}")
    void outMoney(@Param("name") String name,@Param("money") Integer money);
}
```



``` java
@Configuration
@ComponentScan("com.ymx")
@PropertySource("jdbc.properties")
@Import({JDBCConfig.class, MybatisConfig.class})
@EnableTransactionManagement
public class SpringConfig {

}
```



``` java
@Configuration
public class JDBCConfig {
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Bean
    public DataSource dataSource(){
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource){
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(dataSource);
        return manager;
    }
}
```





