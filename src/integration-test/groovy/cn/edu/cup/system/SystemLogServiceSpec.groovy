package cn.edu.cup.system

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SystemLogServiceSpec extends Specification {

    SystemLogService systemLogService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new SystemLog(...).save(flush: true, failOnError: true)
        //new SystemLog(...).save(flush: true, failOnError: true)
        //SystemLog systemLog = new SystemLog(...).save(flush: true, failOnError: true)
        //new SystemLog(...).save(flush: true, failOnError: true)
        //new SystemLog(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //systemLog.id
    }

    void "test get"() {
        setupData()

        expect:
        systemLogService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<SystemLog> systemLogList = systemLogService.list(max: 2, offset: 2)

        then:
        systemLogList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        systemLogService.count() == 5
    }

    void "test delete"() {
        Long systemLogId = setupData()

        expect:
        systemLogService.count() == 5

        when:
        systemLogService.delete(systemLogId)
        sessionFactory.currentSession.flush()

        then:
        systemLogService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        SystemLog systemLog = new SystemLog()
        systemLogService.save(systemLog)

        then:
        systemLog.id != null
    }
}
