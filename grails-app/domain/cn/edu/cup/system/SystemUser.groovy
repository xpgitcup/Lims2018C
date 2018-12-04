package cn.edu.cup.system

class SystemUser {
    
    String  userName
    String  password
    String  roleAttribute = "公共服务"
    String  appendAttribute = ""

    static mapping = {
    }
    
    static constraints = {
        userName(unique:true)
        password(password:true)
        roleAttribute(nullable: true)
        appendAttribute(nullable: true)
    }
 
    def beforeInsert() {
        //println("insert  -- ${password}")
        password = password.encodeAsMD5()
        //println "before Insert ${password}"
    }
    
    def beforeUpdate() {
        //如果密码有更新，就重新计算
        //println("update -- ${password}")
        if (isDirty('password')) {
            password = password.encodeAsMD5()
        }
        //println "before Update ${this.password}"
    }
    
    String toString() {
        return "${userName}"
    }

    //------------------------------------------------------------------------------------------------------------------
    def userRoles() {
        return roleAttribute?.split()
    }

    def hasRole (roleName) {
        def roles = userRoles()
        return roles.contains(roleName)
    }
}
