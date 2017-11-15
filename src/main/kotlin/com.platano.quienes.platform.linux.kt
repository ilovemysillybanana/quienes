/**
 * Created by jose on 10/26/17.
 */

package platform.linux

import java.io.File
import java.io.InputStream


class linux {
    fun getUserData(): MutableList<String> {

        val usersFile = "/etc/passwd"
        val userNames = mutableListOf<String>()
        val inputStream: InputStream = File(usersFile).inputStream()

        inputStream.bufferedReader().useLines { lines -> lines.forEach { userNames.add(it.split(":")[0]) } }

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

        println(json_data + "}")
        return json_data + "}"
    }

}