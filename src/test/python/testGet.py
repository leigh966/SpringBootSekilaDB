import requests
import tools
def get_actor_by_id(root, id):
    pload = {"id":id}
    return requests.get(root+"get_actor", params=pload).json()

def get_film_by_id(root, id):
    pload = {"id":id}
    return requests.get(root+"get_film", params=pload).json()

def test_get_actor_by_id(root):
    actors = get_actor_by_id(root, 1)
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

def test_get_null_actor(root):
    actors = get_actor_by_id(root, 500)
    if len(actors) == 0:
        return "test passed: no actors pulled as there are no actors of id 500"
    return tools.create_fail_message("id 500 should return no actors", "[]", actors)


def verify_link(root, actor_id, film_id):
    film = get_film_by_id(root, film_id)[0]
    actors = film["actor"]
    for actor in actors:
        if actor["actor_id"] == actor_id:
            return "test passed: actor_id("+str(actor_id)+") and film_id("+str(film_id)+") are linked"
    return "test failed: actor_id("+str(actor_id)+") and film_id("+str(film_id)+") are not linked"

def verify_no_link(root, actor_id, film_id):
    film = get_film_by_id(root, film_id)[0]
    actors = film["actor"]
    for actor in actors:
        if actor["actor_id"] == actor_id:
            return "test failed: actor_id("+str(actor_id)+") and film_id("+str(film_id)+") are linked"
    return "test passed: actor_id("+str(actor_id)+") and film_id("+str(film_id)+") are not linked"