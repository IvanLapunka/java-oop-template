package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository{
    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) != null) {
            return false;
        }
        resize();
        authors[authors.length - 1] = author;
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
        for (int i = 0; i < authors.length; i++) {
            if (authors[i].equals(author)) {
                authors[i] = null;
                resize();
                return true;
            }
        }
        return false;
    }

    //можно ли завязываться на null? Например если что-то не нашли, то вернуть null?
    private void resize() {
        int removeCell = authors.length;
        for (int i = 0; i < authors.length; i++) {
            if (authors[i] == null) {
                removeCell = i;
                break;
            }
        }
        Author[] newAuthors;
        if (removeCell == authors.length) {
            newAuthors = new Author[authors.length + 1];
            System.arraycopy(authors, 0, newAuthors, 0, authors.length);
        } else if (authors.length == 1){
            newAuthors = new Author[0];
        } else {
            newAuthors = new Author[authors.length - 1];
            System.arraycopy(authors, 0, newAuthors, 0, removeCell);
            System.arraycopy(authors, removeCell + 1, newAuthors, removeCell, authors.length - removeCell - 1);
        }
        authors = newAuthors;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
