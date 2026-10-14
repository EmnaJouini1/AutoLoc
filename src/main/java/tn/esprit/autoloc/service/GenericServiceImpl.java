package tn.esprit.autoloc.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.autoloc.exception.ResourceNotFoundException;

public abstract class GenericServiceImpl<T, ID>
        implements IGenericService<T, ID> {

    protected final JpaRepository<T, ID> repository;

    protected GenericServiceImpl(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    protected abstract String getEntityName();

    protected abstract ID getId(T entity);

    protected abstract void setFields(T source, T target);

    @Override
    public boolean check(T entity, boolean isNew) {
        if (entity == null) {
            return false;
        }

        ID id = getId(entity);

        return isNew ? id == null : id != null;
    }

    @Override
    public T create(T entity) {
        if (entity == null) {
            throw new IllegalArgumentException(
                    "L'entité ne doit pas être null");
        }

        if (!check(entity, true)) {
            throw new IllegalArgumentException(
                    "Les données de l'entité sont invalides");
        }

        return repository.save(entity);
    }

    @Override
    public T findById(ID id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                getEntityName()
                                        + " introuvable avec l'id : " + id));
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T update(ID id, T entity) {
        if (entity == null) {
            throw new IllegalArgumentException(
                    "L'entité ne doit pas être null");
        }

        T entityExistante = findById(id);

        setFields(entity, entityExistante);

        return repository.save(entityExistante);
    }

    @Override
    public void deleteById(ID id) {
        T entity = findById(id);
        repository.delete(entity);
    }
}