package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.repository.AuthorRepository;

public class SimpleAuthorService implements AuthorService{
    AuthorRepository repository;

    public SimpleAuthorService() {
    }

    public SimpleAuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean save(Author author) {
        return repository.save(author);
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        return repository.findByFullName(name, lastname);
    }

    @Override
    public boolean remove(Author author) {
        return repository.remove(author);
    }

    @Override
    public int count() {
        return repository.count();
    }
}
