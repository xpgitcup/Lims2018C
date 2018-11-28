package cn.edu.cup.dictionary

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class DataDictionaryServiceSpec extends Specification {

    DataDictionaryService dataDictionaryService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new DataDictionary(...).save(flush: true, failOnError: true)
        //new DataDictionary(...).save(flush: true, failOnError: true)
        //DataDictionary dataDictionary = new DataDictionary(...).save(flush: true, failOnError: true)
        //new DataDictionary(...).save(flush: true, failOnError: true)
        //new DataDictionary(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //dataDictionary.id
    }

    void "test get"() {
        setupData()

        expect:
        dataDictionaryService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<DataDictionary> dataDictionaryList = dataDictionaryService.list(max: 2, offset: 2)

        then:
        dataDictionaryList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        dataDictionaryService.count() == 5
    }

    void "test delete"() {
        Long dataDictionaryId = setupData()

        expect:
        dataDictionaryService.count() == 5

        when:
        dataDictionaryService.delete(dataDictionaryId)
        sessionFactory.currentSession.flush()

        then:
        dataDictionaryService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        DataDictionary dataDictionary = new DataDictionary()
        dataDictionaryService.save(dataDictionary)

        then:
        dataDictionary.id != null
    }
}
