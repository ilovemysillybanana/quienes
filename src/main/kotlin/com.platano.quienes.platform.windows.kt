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
        var output = BufferedReader(
                InputStreamReader(
                        Runtime.getRuntime().exec(cmd)
                                .inputStream))
        return output

    }

    fun userData(output: BufferedReader): MutableList<String> {
        var users = mutableListOf<String>()
        output.lines().forEach line@ {
            when {
                it.isBlank()         -> return@line
                it.isEmpty()         -> return@line
                regcompl.matches(it) -> return@line
                regusers.matches(it) -> return@line
                regdashs.matches(it) -> return@line
                else -> {
                    it.split("            ").forEach {
                        var name = it.replace("\\s".toRegex(), "")
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
        val regexusr = Regex("User name.*")
        val rexgroup = Regex(".*Group.*")
        var groups = mutableMapOf<String, MutableList<String>>()

        users.forEach {
            println("This is the user: $it")

            //run command to get output
            var user_group_data = console("net user $it")
            var lsGroupData = mutableListOf<String>()
            var name = it


            user_group_data.lines().forEach line@ {
                var strGroupData = ""
                when {
                    it.isBlank() -> return@line
                    it.isEmpty() -> return@line
                    rexgroup.matches(it) -> {
                        // NOTE, each user group starts with an asterisk. We split on that and drop
                        // the first element since it's just group category name
                        // groups.put(it, it.replace("\\s".toRegex(), "").split("*").drop(1))
                        strGroupData = it.replace("\\s".toRegex(), "")
                        strGroupData.split("*").drop(1).forEach {
                            lsGroupData.add(it)
                        }
                    }
                }
            }
            groups.put(name, lsGroupData)
        }
        println(groups)
        return groups
    }

    fun getFinishedData(){
        var output = console("net user")
        var usersd = userData(output)
        var groupd = groupData(usersd)

    }


}