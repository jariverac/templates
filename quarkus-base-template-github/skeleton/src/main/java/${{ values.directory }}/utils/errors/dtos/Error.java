package ${{ values.package }}.utils.errors.dtos;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@RegisterForReflection
@AllArgsConstructor
@NoArgsConstructor
public class Error {
    private int code;
    private String message;
}
