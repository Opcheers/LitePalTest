package com.example.litepaltest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button createBtn = this.findViewById(R.id.create_database);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Connector.getDatabase();//创建数据库
            }
        });

        Button addBtn = this.findViewById(R.id.add_data);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = createBook();
                book.save();//添加数据

                Book book1 = new Book();
                book1.setName("Why isn't it time for sleeping?");
                book1.setAuthor("wtyxq");
                book1.setPages(110);
                book1.setPrice(2.22);
                book1.save();
            }
        });

        Button updateBtn = this.findViewById(R.id.update_data);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = createBook();
                book.save();
//                book.setPrice(7.7);
//                book.save();
//                //方法二：好使
//                Book book1 = new Book();
//                book1.setPrice(14.95);
//                book1.setAuthor("wt");
//                book1.updateAll("pages = ? and price = ?", "100", "7.7");
            }
        });


        Button deleteBtn = this.findViewById(R.id.delete_data);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LitePal.deleteAll(Book.class, "price = ?", "14.95");
            }
        });

        Button queryBtn = this.findViewById(R.id.query_data);
        queryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Book> books = LitePal.findAll(Book.class);
                for (Book book : books) {
                    System.out.println(book.toString());
                }
                Book firstBook = LitePal.findFirst(Book.class);
                System.out.println("firstBook --> " + firstBook);
                Book lastBook = LitePal.findLast(Book.class);
                System.out.println("lastBook --> " + lastBook);
                List<Book> selectBook = LitePal.select("name", "author").find(Book.class);
                System.out.println("selectBook --> " + selectBook);
                List<Book> whereBook = LitePal.where("pages > 100").find(Book.class);
                System.out.println("whereBook --> " + whereBook);
                List<Book> orderBook = LitePal.order("pages desc").find(Book.class);
                System.out.println("orderBook --> " + orderBook);
                List<Book> limitBook = LitePal.limit(3).find(Book.class);//查询前三条
                System.out.println("limitBook --> " + limitBook);
                List<Book> offsetBook = LitePal.limit(3).offset(1).find(Book.class);//查询2,3,4
                System.out.println("offsetBook --> " + offsetBook);
            }
        });

    }

    @NonNull
    private Book createBook() {
        Book book = new Book();
        book.setName("Why isn't it time for dinner?");
        book.setAuthor("yxq");
        book.setPages(100);
        book.setPrice(2.22);
        return book;
    }

}