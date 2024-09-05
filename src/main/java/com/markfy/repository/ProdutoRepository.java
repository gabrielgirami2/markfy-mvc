package com.markfy.repository;

import com.markfy.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    @Query("SELECT p FROM Produto p WHERE p.loja.idLoja = :idLoja")
    List<Produto> findProdutosByLojaId(@Param("idLoja") Long idLoja);
}
