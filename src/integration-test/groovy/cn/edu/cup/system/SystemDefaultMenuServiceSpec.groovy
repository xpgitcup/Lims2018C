package cn.edu.cup.system

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SystemDefaultMenuServiceSpec extends Specification {

    SystemDefaultMenuService systemDefaultMenuService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new SystemDefaultMenu(...).save(flush: true, failOnError: true)
        //new SystemDefaultMenu(...).save(flush: true, failOnError: true)
        //SystemDefaultMenu systemDefaultMenu = new SystemDefaultMenu(...).save(flush: true, failOnError: true)
        //new SystemDefaultMenu(...).save(flush: true, failOnError: true)
        //new SystemDefaultMenu(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //systemDefaultMenu.id
    }

    void "test get"() {
        setupData()

        expect:
        systemDefaultMenuService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<SystemDefaultMenu> systemDefaultMenuList = systemDefaultMenuService.list(max: 2, offset: 2)

        then:
        systemDefaultMenuList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        systemDefaultMenuService.count() == 5
    }

    void "test delete"() {
        Long systemDefaultMenuId = setupData()

        expect:
        systemDefaultMenuService.count() == 5

        when:
        systemDefaultMenuService.delete(systemDefaultMenuId)
        sessionFactory.currentSession.flush()

        then:
        systemDefaultMenuService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        SystemDefaultMenu systemDefaultMenu = new SystemDefaultMenu()
        systemDefaultMenuService.save(systemDefaultMenu)

        then:
        systemDefaultMenu.id != null
    }
}
