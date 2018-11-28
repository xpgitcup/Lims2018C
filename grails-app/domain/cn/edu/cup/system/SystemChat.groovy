package cn.edu.cup.system

class SystemChat {

    String speaker
    String speakTo
    String message
    boolean haveRead = false
    SystemChat upTopic
    Date startTime

    static hasMany = [subTopics: SystemChat]

    static constraints = {
        speaker()
        speakTo()
        message()
        upTopic(nullable: true)
        startTime()
    }

    static mapping = {
        sort startTime: "desc"
    }

    String toString() {
        return "${speaker}:${speakTo}=${message}"
    }
}
