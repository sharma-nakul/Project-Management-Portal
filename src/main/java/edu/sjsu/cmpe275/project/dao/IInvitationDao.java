package edu.sjsu.cmpe275.project.dao;

import edu.sjsu.cmpe275.project.model.Invitation;
import edu.sjsu.cmpe275.project.model.Project;
import edu.sjsu.cmpe275.project.model.User;

import java.util.List;

/**
 * @author Naks
 * Invitation Dao interface to perform database operation
 */
public interface IInvitationDao {
    boolean updateInvitation (Invitation invitation);
    boolean removeInvitation (Invitation invitation);
    Invitation getInvitation (long id);
    long saveInvitation (Invitation invitation);
    List<Invitation> getInvitations (long id);
    List<User> getAllUser ();
    List<Invitation> getProjectParticipantList (long projectId);
    Project getProject(long id);
}
