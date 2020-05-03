package br.com.algaworks.gerenciamentoapp.model.enumns;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class TipoLancamentoConverter implements AttributeConverter<TipoLancamento, String> {

    //vai pro banco
    @Override
    public String convertToDatabaseColumn(TipoLancamento atributo) {
        return atributo == null ? null : atributo.getDescricao();
    }

    //volta do banco
    @Override
    public TipoLancamento convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return Stream.of(TipoLancamento.values())
                .filter(s -> s.getDescricao().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }
}
