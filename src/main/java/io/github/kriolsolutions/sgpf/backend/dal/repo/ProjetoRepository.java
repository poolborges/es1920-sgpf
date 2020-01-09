/*
 * Copyright 2019 kriolSolutions.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.kriolsolutions.sgpf.backend.dal.repo;

import io.github.kriolsolutions.sgpf.backend.dal.entidades.projeto.Projeto;
import java.util.List;
import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.data.api.FirstResult;
import org.apache.deltaspike.data.api.MaxResults;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

/**
 *
 * @author pauloborges
 */
@Repository(forEntity = Projeto.class)
public abstract class ProjetoRepository extends AbstractEntityRepository<Projeto, Long>{ 
    
    @Query("select p from Projeto p where p.projNumero LIKE CONCAT('%',?1,'%')")
    public abstract List<Projeto> findByProjNumero(String projNumero);
    
    public abstract List<Projeto> findByProjNumeroLike(String projNumero, @FirstResult int offset, @MaxResults int pageSize);
}
