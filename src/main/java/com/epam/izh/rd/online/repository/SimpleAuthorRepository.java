package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;
import java.util.stream.Stream;

public class SimpleAuthorRepository implements AuthorRepository{
    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) != null) {
            return false;
        }
        authors = Stream.concat(Arrays.stream(authors), Stream.of(author)).toArray(Author[]::new);
        return true;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author author: authors) {
            if (author.getName().equals(name) && author.getLastName().equals(lastname)) {
                return author;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        int beforeLength = authors.length;
        authors = Arrays.stream(authors)
                .filter(auth -> !auth.equals(author))
                .toArray(Author[]::new);
        return authors.length != beforeLength;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
