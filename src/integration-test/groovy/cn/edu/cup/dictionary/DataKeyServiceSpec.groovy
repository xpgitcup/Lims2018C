package cn.edu.cup.dictionary

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class DataKeyServiceSpec extends Specification {

    DataKeyService dataKeyService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new DataKey(...).save(flush: true, failOnError: true)
        //new DataKey(...).save(flush: true, failOnError: true)
        //DataKey dataKey = new DataKey(...).save(flush: true, failOnError: true)
        //new DataKey(...).save(flush: true, failOnError: true)
        //new DataKey(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //dataKey.id
    }

    void "test get"() {
        setupData()

        expect:
        dataKeyService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<DataKey> dataKeyList = dataKeyService.list(max: 2, offset: 2)

        then:
        dataKeyList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        dataKeyService.count() == 5
    }

    void "test delete"() {
        Long dataKeyId = setupData()

        expect:
        dataKeyService.count() == 5

        when:
        dataKeyService.delete(dataKeyId)
        sessionFactory.currentSession.flush()

        then:
        dataKeyService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        DataKey dataKey = new DataKey()
        dataKeyService.save(dataKey)

        then:
        dataKey.id != null
    }
}
