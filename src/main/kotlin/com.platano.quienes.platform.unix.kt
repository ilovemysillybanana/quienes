/**
 * Created by jose on 11/18/17.
 */
package platform.unix

import java.io.BufferedReader
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader

class unix {
    fun getUserData(): MutableList<String> {
        val usersFile = "/etc/passwd"
        val userNames = mutableListOf<String>()
        val inputStream: InputStream = File(usersFile).inputStream()

        inputStream.bufferedReader().useLines { lines -> lines.forEach { userNames.add(it.split(":")[0]) } }

        //for a more complete list of users, we'll scan the groups list for additional users as well
        val groupsFile = "/etc/group"
        val groupStream: InputStream = File(groupsFile).inputStream()

        groupStream.bufferedReader().useLines { lines ->
            lines.forEach({
                val a_names = it.split(":").drop(3)
                val s_names = mutableListOf<String>()

                //split any entries with a comma, remove them and add them individually
                a_names.forEach {
                    it.split(",").forEach {
                        s_names.add(it)
                    }
                }

                s_names.forEach line@ {
                    when {
                        it.isBlank() -> return@line
                        it.isEmpty() -> return@line
                        else -> {
                            if (!(it in userNames)) {
                                userNames.add(it)
                            }
                        }
                    }
                }
            })
        }

        return userNames
    }

    fun getGroupData(): MutableList<String> {
        val groupsFile = "/etc/group"
        val groupData = mutableListOf<String>()
        val inputStream: InputStream = File(groupsFile).inputStream()

        inputStream.bufferedReader().useLines { lines ->
            lines.forEach {
                groupData.add(it)
            }
        }

        return groupData
    }

    fun console(cmd: String): BufferedReader {
        val output = BufferedReader(
                InputStreamReader(
                        Runtime.getRuntime().exec(cmd)
                                .inputStream))
        return output

    }

    fun getFinishedData(){
        val users = getUserData()
        val groups = getGroupData()
        var users_data = mutableMapOf<String, List<String>>()


        users.forEach {
            var groups_data = mutableListOf<String>()

            if ( !(it in users_data) ) {
                users_data.put(it, emptyList())
                var current_user = it
                groups.forEach {
                    var groups_on_line = it.split(":").drop(3) // get the members of a group
                    var groups_name = it.split(":")[0]         // get the name of said group
                    if ( (current_user in groups_on_line) ){
                        groups_on_line.forEach {
                            if ( (it in groups_on_line) ){
                                groups_data.add(groups_name)
                            }
                        }
                    }

                    if ( (groups_name.equals(current_user) ) ){
                        var groups_cmd = Runtime.getRuntime().exec("groups $current_user")
                        //if the user command fails, do not add user to groups data
                        if ( !(groups_cmd.toString().contains("no such user")) ){
                            groups_data.add(groups_name)
                        }
//                        System.exit(0)
                    }
                }
            }

            users_data.put(it, groups_data)

        }

        jsonify(users_data)

    }

    fun jsonify(user_data: MutableMap<String, List<String>>): String {
        var json_data = "{ \"userdata\": {"
        user_data.forEach {
            var json_group_data = ""
            var name = it.key
            var groups = it.value
            json_data += "\"${name}\": ["

            groups.forEach {
                //                println("THIS IS THE DATA: $it")
                json_group_data += "\"${it}\","
            }

            json_data += json_group_data.dropLast(1) + "],"
        }

        println(json_data.dropLast(1) + "}}")
        return json_data.dropLast(1) + "}}"
    }


}