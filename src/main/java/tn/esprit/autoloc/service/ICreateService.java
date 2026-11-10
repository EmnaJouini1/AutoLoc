package tn.esprit.autoloc.service;

public interface ICreateService<T> {

    T create(T entity);
}