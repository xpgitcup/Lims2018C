package cn.edu.cup.userdef

class UserDefinedFunction {

    String name
    String description

    static hasMany = [userClassLibrary: UserClassLibrary]

    static constraints = {
        name(unique: true)
        description(nullable: true)
    }

    String toString() {
        return name
    }
}
