package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.BookRepository;

public class SimpleSchoolBookService implements BookService<SchoolBook> {

    BookRepository<SchoolBook> repository;
    AuthorService authorService;

    public SimpleSchoolBookService(BookRepository<SchoolBook> repository, AuthorService authorService) {
        this.repository = repository;
        this.authorService = authorService;
    }

    public SimpleSchoolBookService() {
    }

    @Override
    public boolean save(SchoolBook book) {
        final Author byFullName = authorService.findByFullName(book.getAuthorName(), book.getAuthorLastName());
        if (byFullName == null) {
            return false;
        }
        return repository.save(book);
    }

    @Override
    public SchoolBook[] findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public int getNumberOfBooksByName(String name) {
        return repository.findByName(name).length;
    }

    @Override
    public boolean removeByName(String name) {
        return repository.removeByName(name);
    }

    @Override
    public int count() {
        return repository.count();
    }

    @Override
    public Author findAuthorByBookName(String name) {
        final SchoolBook[] byName = repository.findByName(name);
        if (byName.length == 0) {
            return null;
        }
        SchoolBook book = byName[0];
        return authorService.findByFullName(book.getAuthorName(), book.getAuthorLastName());
    }
}
