/**
 * Created by jose on 11/15/17.
 */
package platform.windows

import java.io.BufferedReader
import java.io.InputStreamReader

class windows {

    private val regusers = Regex("User accounts for.*")
    private val regdashs = Regex("-----------------.*")
    private val regcompl = Regex("The command compl.*")

    fun console(cmd : String): BufferedReader {
        val output = BufferedReader(
                InputStreamReader(
                        Runtime.getRuntime().exec(cmd)
                                .inputStream))
        return output

    }

    fun userData(output: BufferedReader): MutableList<String> {
        val users = mutableListOf<String>()
        output.lines().forEach line@ {
            when {
                it.isBlank()         -> return@line
                it.isEmpty()         -> return@line
                regcompl.matches(it) -> return@line
                regusers.matches(it) -> return@line
                regdashs.matches(it) -> return@line
                else -> {
                    it.split("            ").forEach {
                        val name = it.replace("\\s".toRegex(), "")
                        if ( it.isNotBlank()){
                            //println("\"$name\"")
                            users.add(name)
                        }
                    }
                }
            }
        }

        return users
    }

    //this function takes in a list of users, and returns its corresponding groups
    fun groupData(users: MutableList<String>): MutableMap<String, MutableList<String>> {
        //only lines we care about are username and local and global groups
        //val regexusr = Regex("User name.*")
        val rexgroup = Regex(".*Group.*")
        val groups = mutableMapOf<String, MutableList<String>>()

        users.forEach {
            println("This is the user: $it")

            //run command to get output
            val user_group_data = console("net user $it")
            val lsGroupData = mutableListOf<String>()
            val name = it


            user_group_data.lines().forEach line@ {
                var strGroupData = ""
                when {
                    it.isBlank() -> return@line
                    it.isEmpty() -> return@line
                    rexgroup.matches(it) -> {
                        // NOTE, each user group starts with an asterisk. We split on that and drop
                        // the first element since it's just group category name
                        // groups.put(it, it.replace("\\s".toRegex(), "").split("*").drop(1))
                        //strGroupData = it.replace("\\s".toRegex(), "")
                        strGroupData = it.replace("\\s".toRegex(), " ").drop(1)
                        strGroupData.split("*").drop(1).forEach {
                            lsGroupData.add(it.trim())
                        }
                    }
                }
            }
            groups.put(name, lsGroupData)
        }
        println(groups)
        return groups
    }

    fun jsonify(groups_data: MutableMap<String, MutableList<String>> ): String {

        var jsonData = "{ \"userdata\": {"

        groups_data.forEach {
            var groupData = "\"${it.key}\": ["

            it.value.forEach {
                groupData += "\"${it}\","
            }

            groupData = groupData.dropLast(1)
            groupData += "],"
            jsonData += groupData
        }

        jsonData = jsonData.dropLast(1)
        jsonData += "}}"

        println(jsonData)
        return "FOO"
    }

    fun getFinishedData(){
        val output = console("net user")
        val usersd = userData(output)
        val groupd = groupData(usersd)

        println(jsonify(groupd))
    }


}