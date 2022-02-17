import re
import requests;
import json;
import testGet
import tools
import testPost
import testDelete
import testState
import sys
root = "http://localhost:8080/home/"



if len(sys.argv) > 1:
    root = "http://" + sys.argv[1] + "/home/"

print("Root: " + root)

print(testPost.add_film_min(root))


print(testState.test_films_all_upper(root))


print(testState.test_actors_all_upper(root))
"""
def test_delete_actor():
    r = requests.delete(root+"delete_actor")
    if not r.ok:
        print(r.text)
        return "test failed: bad request"
"""


print(testDelete.test_delete_film(root))

print(testGet.test_get_actor_by_id(root))

print(testGet.test_get_actor_upper(root))

print(testGet.test_get_actor_lower(root))

print(testGet.test_get_null_actor(root))
    