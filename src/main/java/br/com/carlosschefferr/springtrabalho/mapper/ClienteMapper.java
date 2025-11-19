package br.com.carlosschefferr.springtrabalho.mapper;

import br.com.carlosschefferr.springtrabalho.domain.Cliente;
import br.com.carlosschefferr.springtrabalho.domain.enums.Perfil;
import br.com.carlosschefferr.springtrabalho.dto.ClienteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(target = "perfis", source = "perfis")
    Cliente toEntity(ClienteDTO dto);

    @Mapping(target = "perfis", source = "perfis")
    ClienteDTO toDTO(Cliente entity);

    List<ClienteDTO> toDTOList(List<Cliente> entityList);

    @Mapping(target = "perfis", source = "perfis")
    void updateEntityFromDto(ClienteDTO dto, @MappingTarget Cliente entity);

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
