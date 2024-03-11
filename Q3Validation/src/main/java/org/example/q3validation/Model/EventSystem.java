package org.example.q3validation.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;
@Data
@AllArgsConstructor
public class EventSystem {
    @NotEmpty(message = "id cannot be empty")
    @Size(min=3, message="id should be more then 2 characters")
    private String id;
    @NotEmpty(message = "description cannot be empty")
    @Size(min=16, message="description should be more then 15 characters")
    private String description;
    @NotNull(message = "capacity cannot be empty")
    @Digits(integer = Integer.MAX_VALUE, fraction = 0, message = " capacity must be a number")
    @Min(value = 26, message = "must be greater than 25")
    private Integer capacity;
    @JsonFormat(pattern="yyyy-mm-dd")
    private LocalDate startData;
    @JsonFormat(pattern="yyyy-mm-dd")
    private LocalDate endData;
}
