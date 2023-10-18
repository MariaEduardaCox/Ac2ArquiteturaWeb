package com.example.ac1.repository;

import com.example.ac1.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    List<Categoria> findByNomeLike(String nome);

    @Query("select c from Categoria c left join fetch c.produtos p where c.id = :id ")
    Categoria findCategoriaCursoFetchCursos(@Param("id") Long id);
}
