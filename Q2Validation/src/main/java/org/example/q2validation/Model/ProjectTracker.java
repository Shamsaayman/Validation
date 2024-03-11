package org.example.q2validation.Model;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class ProjectTracker {
    @NotEmpty(message = "id cannot be empty")
    @Size(min=3, message="id should be more then 2 characters")
    private String id;
    @NotEmpty(message = "title cannot be empty")
    @Size(min=9, message="title should be more then 8 characters")
    private String title;
    @NotEmpty(message = "description cannot be empty")
    @Size(min=16, message="description should be more then 15 characters")
    private String description;
    @NotEmpty(message = "status cannot be empty")
    @Pattern(regexp = "^(Not Started|in Progress|Completed)$")
    private String status;
    @NotEmpty(message = "companyName cannot be empty")
    @Size(min=7, message="companyName should be more then 6 characters")
    private String companyName;
}
