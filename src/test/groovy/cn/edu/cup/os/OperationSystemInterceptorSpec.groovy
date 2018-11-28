package cn.edu.cup.os

import grails.testing.web.interceptor.InterceptorUnitTest
import spock.lang.Specification

class OperationSystemInterceptorSpec extends Specification implements InterceptorUnitTest<OperationSystemInterceptor> {

    def setup() {
    }

    def cleanup() {

    }

    void "Test operationSystem interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"operationSystem")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
