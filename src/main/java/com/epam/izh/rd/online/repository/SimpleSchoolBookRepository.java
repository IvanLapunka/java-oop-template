package com.epam.izh.rd.online.repository;


import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    SchoolBook[] books = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] newBooks = new SchoolBook[books.length + 1];
        System.arraycopy(books, 0, newBooks, 0, books.length);
        newBooks[books.length] = book;
        books = newBooks;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        return Arrays.stream(books)
                .filter(book -> book.getName().equals(name))
                .toArray(SchoolBook[]::new);
    }

    @Override
    public boolean removeByName(String name) {
        int countRemove = 0;
        for (int i = 0;  i < books.length; i++){
            if (books[i].getName().equals(name)) {
                System.arraycopy(books, i + 1, books, i, books.length - i - 1);
                countRemove++;
            }
        }
        if (countRemove > 0) {
            SchoolBook[] newBooks = new SchoolBook[books.length - countRemove];
            System.arraycopy(books, 0, newBooks, 0, books.length - countRemove);
            books = newBooks;
            return true;
        }
        return false;
    }

    @Override
    public int count() {
        return books.length;
    }
}
