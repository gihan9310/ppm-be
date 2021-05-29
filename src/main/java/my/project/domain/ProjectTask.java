package my.project.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Date;

/**
 * |** @author 'Gihan Rathnayaka'**|
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProjectTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(updatable = false)
    private  String projectSeq;
    @NotBlank(message = "Please Include project summary.")
    private String summary;
    private String acceptanceCriteria;
    private String status;
    private Integer priority;

//    Many to one With back Log
    @Column(updatable = false)
    private String projectId;

    private LocalDate dueDate;
    private LocalDate create_At;
    private LocalDate update_At;

    @PrePersist
    protected  void onCreate(){
        this.create_At =  LocalDate.now();
    }

    @PreUpdate
    protected  void onUpdate(){
        this.update_At =  LocalDate.now();
    }
}
