package cn.edu.cup.system

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SystemChatServiceSpec extends Specification {

    SystemChatService systemChatService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new SystemChat(...).save(flush: true, failOnError: true)
        //new SystemChat(...).save(flush: true, failOnError: true)
        //SystemChat systemChat = new SystemChat(...).save(flush: true, failOnError: true)
        //new SystemChat(...).save(flush: true, failOnError: true)
        //new SystemChat(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //systemChat.id
    }

    void "test get"() {
        setupData()

        expect:
        systemChatService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<SystemChat> systemChatList = systemChatService.list(max: 2, offset: 2)

        then:
        systemChatList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        systemChatService.count() == 5
    }

    void "test delete"() {
        Long systemChatId = setupData()

        expect:
        systemChatService.count() == 5

        when:
        systemChatService.delete(systemChatId)
        sessionFactory.currentSession.flush()

        then:
        systemChatService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        SystemChat systemChat = new SystemChat()
        systemChatService.save(systemChat)

        then:
        systemChat.id != null
    }
}
