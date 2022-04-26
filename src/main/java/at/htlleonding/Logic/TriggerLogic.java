package at.htlleonding.Logic;

import at.htlleonding.persistence.AuditLog;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Date;


@ApplicationScoped
public class TriggerLogic {
    @Inject
    EntityManager entityManager;

    public void createAuditLog(String entity, String action, String userId) {
        AuditLog auditLog = new AuditLog();
        auditLog.setEntity(entity);
        auditLog.setAction(action);
        auditLog.setTimestamp(new Date());
        entityManager.persist(auditLog);
    }

    //create a trigger to log all changes to the database with the createAuditLog method






}
