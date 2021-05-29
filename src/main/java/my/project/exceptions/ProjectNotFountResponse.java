package my.project.exceptions;

/**
 * |** @author 'Gihan Rathnayaka'**|
 */
public class ProjectNotFountResponse {
    private String projectNotFound;

    public ProjectNotFountResponse(String projectNotFound) {
        this.projectNotFound = projectNotFound;
    }

    public String getProjectNotFound() {
        return projectNotFound;
    }

    public void setProjectNotFound(String projectNotFound) {
        this.projectNotFound = projectNotFound;
    }
}
