package one.digitalinovation.gof.model;


import java.util.Optional;

public interface CrudRepository<T, ID>  {
    <S extends T> void save (S entity);

    <S extends T> Iterable<S> saveAll(Iterable<S> entities);

    Optional<T> findById(ID id);

}
