package cn.edu.cup.userdef

class UserClassMethod {

    String name
    String description

    static belongsTo = [userClass: UserClass]

    static constraints = {
    }

    String toString() {
        return "${name}"
    }
}
