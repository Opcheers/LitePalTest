### 使用LitePal操作数据库

LitePal采取的是**对象映射关系ORM模式**，我们使用的编程语言是面向对象语言，使用的数据库是关系型数据库，那么面向对象的语言和面向关系的数据库之间建立一种映像关系就是对象关系映射。
#### 准备

https://github.com/guolindev/LitePal

https://www.ngui.cc/article/show-330979.html

#### 创建数据库

1.创建模型类

2.添加到litepal.xml中（app/src/main->new->directory->assets->litepal.xml）

```xml
<?xml version="1.0" encoding="utf-8"?>
<litepal>
    <dbname value="BookStore" />
    <version value="1" />
    <list>
        <mapping class = "com.example.litepaltest.Book"></mapping>
    </list>
</litepal>
```

<mapping>标签就是用于声明我们要配置的映射模型类，一定要使用完整的类名。

3.Connector.getDatabase();//创建数据库

#### 升级数据库

1.新建表：直接新建一个模型类，然后加到litepal.xml中

2.新增列：直接修改模型类

记得修改版本号，Litepal会自动保留之前表中的数据

#### CRUD操作

CRUD操作中的模型类必须继承自LitePalSupport

```Java
book.save();//添加数据
```

```Java
//方式一
Book book = createBook();
book.save();//添加数据
book.setPrice(7.7);//上面的数据被修改
book.save();
//方式二
Book book = new Book();
book.setPrice(14.95);
book.setAuthor("wt");
book.updateAll("pages = ? and price = ?", "100", "7.7");//更新数据
book.setToDefault("name");//把某个字段的值更新成默认值
```

```Java
LitePal.deleteAll(Book.class, "price = ?", "14.95");//条件删除
```

```Java
List<Book> books = LitePal.findAll(Book.class);//查询数据
Book firstBook = LitePal.findFirst(Book.class);
Book lastBook = LitePal.findLast(Book.class);
List<Book> selectBook = LitePal.select("name", "author").find(Book.class);
List<Book> whereBook = LitePal.where("pages > 100").find(Book.class);
List<Book> orderBook = LitePal.order("pages desc").find(Book.class);
List<Book> limitBook = LitePal.limit(3).find(Book.class);//查询前三条
List<Book> offsetBook = LitePal.limit(3).offset(1).find(Book.class);//查询2,3,4
```

