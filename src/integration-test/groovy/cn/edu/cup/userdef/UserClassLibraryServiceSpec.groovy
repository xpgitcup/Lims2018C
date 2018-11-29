package cn.edu.cup.userdef

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class UserClassLibraryServiceSpec extends Specification {

    UserClassLibraryService userClassLibraryService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new UserClassLibrary(...).save(flush: true, failOnError: true)
        //new UserClassLibrary(...).save(flush: true, failOnError: true)
        //UserClassLibrary userClassLibrary = new UserClassLibrary(...).save(flush: true, failOnError: true)
        //new UserClassLibrary(...).save(flush: true, failOnError: true)
        //new UserClassLibrary(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //userClassLibrary.id
    }

    void "test get"() {
        setupData()

        expect:
        userClassLibraryService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<UserClassLibrary> userClassLibraryList = userClassLibraryService.list(max: 2, offset: 2)

        then:
        userClassLibraryList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        userClassLibraryService.count() == 5
    }

    void "test delete"() {
        Long userClassLibraryId = setupData()

        expect:
        userClassLibraryService.count() == 5

        when:
        userClassLibraryService.delete(userClassLibraryId)
        sessionFactory.currentSession.flush()

        then:
        userClassLibraryService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        UserClassLibrary userClassLibrary = new UserClassLibrary()
        userClassLibraryService.save(userClassLibrary)

        then:
        userClassLibrary.id != null
    }
}
