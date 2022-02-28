import requests
import tools
import testGet

def test_put_actor_name(root):
    params = {"id": 1, "first_name" : "Some", "last_name" : "guy"}
    r = requests.put(root+"update_actor", params)
    if r.text != "saved":
        return tools.create_fail_message("Server failed to say that update was successful","updated", r.text)
    new_actor = testGet.get_actor_by_id(root,params["id"])[0]
    full_added_name = (params["first_name"]+ " "+params["last_name"]).upper()
    full_new_actor_name = new_actor["first_name"]+" "+new_actor["last_name"]
    if full_added_name != full_new_actor_name:
        return tools.create_fail_message("Actor not updated as expected", full_added_name, full_new_actor_name)
    return "test passed: actor updated successfully"

