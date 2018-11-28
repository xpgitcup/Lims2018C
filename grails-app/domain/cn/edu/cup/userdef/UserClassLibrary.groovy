package cn.edu.cup.userdef

class UserClassLibrary {

    String name
    String description
    String fileName
    String developer
    Date   uploadDate

    static belongsTo = [userDefinedFunction: UserDefinedFunction]
    static hasMany = [userClass: UserClass]

    static constraints = {
        name(unique: true)
        description(nullable: true)
        developer(nullable: true)
        uploadDate(nullable: true)
        fileName(nullable: true)
    }

    String toString() {
        return "${userDefinedFunction}.${name}"
    }
}
