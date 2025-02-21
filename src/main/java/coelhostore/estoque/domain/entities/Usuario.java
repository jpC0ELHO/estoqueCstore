package coelhostore.estoque.domain.entities;

import coelhostore.estoque.domain.entities.enums.BrEstados;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateTimeSerializerBase;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_usuarios")
@EqualsAndHashCode(callSuper = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@EntityListeners(AuditingEntityListener.class)
public class Usuario extends Entidade{
    @Column(name = "nome",nullable = false)
    private String name;
    @Column(name = "cpf",nullable = false,unique = true)
    @CPF
    private String cpf;
    @Column(name = "data_nascimento",nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
    @JsonSerialize(using = DateTimeSerializerBase.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate dataNasci;
    @ElementCollection
    @CollectionTable(name = "tb_usuario_email",
            joinColumns = @JoinColumn(name = "tb_usuario", referencedColumnName = "cpf"))
    @Column(name = "emails", nullable = false)
    private Set<String> email;

    @Column(nullable = false)
    @JoinColumn(name = "tb_usuario", referencedColumnName = "cpf")
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Set<String> telefone;
    @Enumerated
    private BrEstados estado;
    @Column
    private String status;
}
