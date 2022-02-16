import requests
import tools
def test_get_actor_by_id(root):
    pload = {"id":1}
    actors = requests.get(root+"get_actor", params=pload).json()
    if len(actors) != 1:
        return tools.create_fail_message("Getting by id should only give 1 actor", 1, len(actors))
    if actors[0]["actor_id"] != 1:
        return tools.create_fail_message("Different actor_id received than expected",1, actors[0]["actor_id"])
    return "test passed: successfully pulled expected actor by id"

def test_actor_query(query,root):
    pload = {'nameQuery':str(query)}
    actors = requests.get(root+"get_actor", params=pload).json()
    for actor in actors:
        full_name = actor["first_name"] + " " + actor["last_name"]
        if query.upper() not in full_name:
            return "test failed: pulled actor '" + full_name + "' does not contain query '" + query + "'"
    return "test passed: all actors fit the name query when querying " + query

def test_get_actor_upper(root):
    return test_actor_query('PEN', root)

def test_get_actor_lower(root):
    return test_actor_query('aca', root)




