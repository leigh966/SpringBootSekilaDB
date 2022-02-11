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
