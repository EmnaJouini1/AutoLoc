package tn.esprit.autoloc.service;

public interface IGenericService<T, ID>
        extends ICheckService<T>,
        IWriteService<T, ID>,
        IReadService<T, ID> {
}