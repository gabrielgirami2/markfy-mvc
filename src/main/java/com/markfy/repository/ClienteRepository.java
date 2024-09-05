package com.markfy.repository;

import com.markfy.models.Cliente;
import com.markfy.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query("SELECT c FROM Cliente c WHERE c.loja.idLoja = :idLoja")
    List<Cliente> findClientesByLojaId(@Param("idLoja") Long idLoja);
}
