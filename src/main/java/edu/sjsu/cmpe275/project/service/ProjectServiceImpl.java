package edu.sjsu.cmpe275.project.service;

import edu.sjsu.cmpe275.project.dao.IProjectDao;
import edu.sjsu.cmpe275.project.model.Invitation;
import edu.sjsu.cmpe275.project.model.Project;
import edu.sjsu.cmpe275.project.model.Task;
import edu.sjsu.cmpe275.project.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Naks
 *         Handler class for Project. The class intercept REST call to persist or retrieve data.
 *         Service annotation to mark the class as service class in application context
 *         Transactional annotation to make the class transactional entity i.e. it will be
 *         counted as a single transaction
 */
@Service
public class ProjectServiceImpl implements IProjectService {

    /**
     * Object to log the values on console.
     */
    private static final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);

    /**
     * Autowire the Project DAO interface object in this class
     */
    @Autowired
    private IProjectDao projectDao;

    @Transactional(value = "transManager")
    @Override
    public long createProject(Project project) {
        return projectDao.addProject(project);
    }

    @Transactional(value = "transManager")
    @Override
    public boolean editProject(Project project) {
        return projectDao.updateProject(project);
    }

    @Transactional(value = "transManager")
    @Override
    public boolean removeProject(long id) {
        Project project = projectDao.getProject(id);
        return projectDao.deleteProject(project);
    }

    @Transactional(value = "transManager")
    @Override
    public Project getProject(long id) {
        return projectDao.getProject(id);
    }

    @Transactional("transManager")
    @Override
    public List<User> getParticipantList(long projectId) {
        List<User> participantList=new ArrayList<>();
        List<Invitation> invitationList = projectDao.getProjectParticipantList(projectId);
        for (Invitation invitation : invitationList) {
            participantList.add(invitation.getParticipant());
        }
        if(participantList.size()>0)
            logger.info("User list returned for a project id "+projectId);
        return participantList;
    }

    @Transactional(value = "transManager")
    @Override
    public List<Task> getTaskByProjectId(long projectId) {
        return projectDao.getTaskByProjectId(projectId);
    }

}

