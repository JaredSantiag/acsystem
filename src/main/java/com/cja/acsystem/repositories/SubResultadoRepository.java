package com.cja.acsystem.repositories;

import com.cja.acsystem.entities.Accion;
import com.cja.acsystem.entities.Resultado;
import com.cja.acsystem.entities.SubResultado;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface SubResultadoRepository extends JpaRepository<SubResultado, Long> {
    public List<SubResultado> findResultadoById(Long resultadoId);
}
