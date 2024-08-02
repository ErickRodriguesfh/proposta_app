package com.ebr.proposta_app.repository;

import com.ebr.proposta_app.entity.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {



}
