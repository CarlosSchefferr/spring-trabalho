package br.com.carlosschefferr.springtrabalho.mapper;

import br.com.carlosschefferr.springtrabalho.domain.Tecnico;
import br.com.carlosschefferr.springtrabalho.domain.enums.Perfil;
import br.com.carlosschefferr.springtrabalho.dto.TecnicoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TecnicoMapper {

    @Mapping(target = "perfis", source = "perfis")
    Tecnico toEntity(TecnicoDTO dto);

    @Mapping(target = "perfis", source = "perfis")
    TecnicoDTO toDTO(Tecnico entity);

    List<TecnicoDTO> toDTOList(List<Tecnico> entityList);

    @Mapping(target = "perfis", source = "perfis")
    void updateEntityFromDto(TecnicoDTO dto, @MappingTarget Tecnico entity);

    default Set<Perfil> mapIntegerSetToPerfilSet(Set<Integer> integerSet) {
        if (integerSet == null) {
            return null;
        }
        return integerSet.stream()
                .map(Perfil::toEnum)
                .collect(Collectors.toSet());
    }

    default Set<Integer> mapPerfilSetToIntegerSet(Set<Perfil> perfilSet) {
        if (perfilSet == null) {
            return null;
        }
        return perfilSet.stream()
                .map(Perfil::getCodigo)
                .collect(Collectors.toSet());
    }
}
