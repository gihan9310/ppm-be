package my.project.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Project name is required")
    private String projectName;
    @NotBlank(message = "Project identifier required")
    @Size(min = 4,max = 5 ,message = "Please use 4 to 5 characters")
    @Column(updatable = false,unique = true)
    private String projectId;
    @NotBlank(message = "Project Description can not empty.")
    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    @Column(updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date created_at;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updated_at;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "project")
    private BackLog backLog;

    @PrePersist
    protected void onCreate(){
        this.created_at= new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updated_at= new Date();
    }
}
