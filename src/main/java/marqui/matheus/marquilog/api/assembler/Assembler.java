package marqui.matheus.marquilog.api.assembler;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Assembler<Tipo> {
    private final ModelMapper modelMapper;

    public <Tipo> Tipo toModel(Object origem, Class<Tipo> destino) {
        return modelMapper.map(origem, destino);
    }

    public List<Tipo> toCollectionModel(List<? extends Object> listaOrigem,
                                        Class<Tipo> destino) {
        return listaOrigem.stream()
                .map(o -> toModel(o, destino))
                .collect(Collectors.toList());
    }
}
