package cn.edu.cup.system

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SystemProcedureServiceSpec extends Specification {

    SystemProcedureService systemProcedureService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new SystemProcedure(...).save(flush: true, failOnError: true)
        //new SystemProcedure(...).save(flush: true, failOnError: true)
        //SystemProcedure systemProcedure = new SystemProcedure(...).save(flush: true, failOnError: true)
        //new SystemProcedure(...).save(flush: true, failOnError: true)
        //new SystemProcedure(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //systemProcedure.id
    }

    void "test get"() {
        setupData()

        expect:
        systemProcedureService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<SystemProcedure> systemProcedureList = systemProcedureService.list(max: 2, offset: 2)

        then:
        systemProcedureList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        systemProcedureService.count() == 5
    }

    void "test delete"() {
        Long systemProcedureId = setupData()

        expect:
        systemProcedureService.count() == 5

        when:
        systemProcedureService.delete(systemProcedureId)
        sessionFactory.currentSession.flush()

        then:
        systemProcedureService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        SystemProcedure systemProcedure = new SystemProcedure()
        systemProcedureService.save(systemProcedure)

        then:
        systemProcedure.id != null
    }
}
