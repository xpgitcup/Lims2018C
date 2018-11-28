package cn.edu.cup.system

class SystemLog {
    String sessionId
    String userName
    String doing
    Date actionDate
    String hostIP
    
    static constraints = {
        sessionId()
        hostIP()
        userName()
        doing()
        actionDate()
    }
    
    static mapping = {
        sort actionDate: "desc"
    }

    String toString() {
        return "${hostIP}/${actionDate}"
    }
}
