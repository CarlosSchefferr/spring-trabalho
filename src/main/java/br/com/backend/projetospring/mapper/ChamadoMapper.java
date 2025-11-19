package br.com.backend.projetospring.mapper;

import br.com.backend.projetospring.domain.Chamado;
import br.com.backend.projetospring.dto.ChamadoDTO;
import br.com.backend.projetospring.domain.enums.Prioridade;
import br.com.backend.projetospring.domain.enums.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ChamadoMapper {
    ChamadoMapper INSTANCE = Mappers.getMapper(ChamadoMapper.class);

    @Mapping(target = "tecnico", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    Chamado toEntity(ChamadoDTO dto);

    @Mapping(source = "tecnico.id", target = "tecnico")
    @Mapping(source = "cliente.id", target = "cliente")
    @Mapping(source = "tecnico.nome", target = "nomeTecnico")
    @Mapping(source = "cliente.nome", target = "nomeCliente")
    @Mapping(source = "prioridade.codigo", target = "prioridade")
    @Mapping(source = "status.codigo", target = "status")
    ChamadoDTO toDto(Chamado entity);

    default Prioridade mapPrioridade(Integer value) {
        return Prioridade.toEnum(value);
    }

    default Status mapStatus(Integer value) {
        return Status.toEnum(value);
    }
}
